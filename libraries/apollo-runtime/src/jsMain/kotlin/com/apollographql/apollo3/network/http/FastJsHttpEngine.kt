package com.apollographql.apollo3.network.http

import com.apollographql.apollo3.api.http.HttpHeader
import com.apollographql.apollo3.api.http.HttpMethod
import com.apollographql.apollo3.api.http.HttpRequest
import com.apollographql.apollo3.api.http.HttpResponse
import com.apollographql.apollo3.exception.ApolloNetworkException
import io.ktor.client.fetch.AbortController
import io.ktor.client.fetch.AbortSignal
import io.ktor.client.fetch.RequestInit
import io.ktor.client.fetch.fetch
import io.ktor.http.HttpHeaders
import io.ktor.util.PlatformUtils
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.await
import okio.Buffer
import org.w3c.fetch.Headers

fun Headers.toHeadersList(): List<HttpHeader> {
  return js("Array").from(this.asDynamic().entries()).unsafeCast<Array<Array<String>>>().map { HttpHeader(it[0], it[1]) }
}

internal fun <T> buildObject(block: T.() -> Unit): T = (js("{}") as T).apply(block)

internal fun AbortController(): AbortController {
  return if (PlatformUtils.IS_BROWSER) {
    js("new AbortController()")
  } else {
    @Suppress("UNUSED_VARIABLE")
    val controller = js("eval('require')('abort-controller')")
    js("new controller()")
  }
}

fun HttpRequest.toRaw(abortSignal: AbortSignal): RequestInit {
  val bodyBuffer = Buffer()

  val rawHeaders = headers.map { arrayOf(it.name, it.value) }.toMutableList()

  body?.let {
    it.writeTo(bodyBuffer)
    rawHeaders.add(arrayOf(HttpHeaders.ContentType, it.contentType))
  }

  return buildObject {
    body = bodyBuffer.readUtf8()
    headers = rawHeaders.toTypedArray()
    method = when (this@toRaw.method) {
      HttpMethod.Get -> io.ktor.http.HttpMethod.Get.value
      HttpMethod.Post -> io.ktor.http.HttpMethod.Post.value
    }
    signal = abortSignal
  }
}

class FastJsHttpEngine : HttpEngine {
  private var disposed = false
  private val controller = AbortController()

  init {
    if (!PlatformUtils.IS_BROWSER) {
      throw RuntimeException("FastHttpEngine is not available outside of the browser.")
    }
  }

  override suspend fun execute(request: HttpRequest): HttpResponse {
    try {
      val fetchResponse = fetch(request.url, request.toRaw(controller.signal)).await()
      val json = fetchResponse.json().await()
      return HttpResponse.Builder(statusCode = fetchResponse.status.toInt())
          .dynamicBody(json)
          .addHeaders(fetchResponse.headers.toHeadersList())
          .build()
    } catch (e: CancellationException) {
      // Cancellation Exception is passthrough
      throw e
    } catch (t: Throwable) {
      throw ApolloNetworkException(t.message, t)
    }
  }

  override fun dispose() {
    if (!disposed) {
      controller.abort()
      disposed = true
    }
  }
}
