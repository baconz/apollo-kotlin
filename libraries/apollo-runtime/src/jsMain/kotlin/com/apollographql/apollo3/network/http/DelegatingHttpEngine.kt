package com.apollographql.apollo3.network.http

import com.apollographql.apollo3.api.http.HttpRequest
import com.apollographql.apollo3.api.http.HttpResponse

actual class DefaultHttpEngine(
    connectTimeoutMillis: Long,
    readTimeoutMillis: Long,
    useBrowserOnlyJsEngine: Boolean = false,
) : HttpEngine {
  actual constructor(timeoutMillis: Long, useBrowserOnlyJsEngine: Boolean) : this(timeoutMillis, timeoutMillis, useBrowserOnlyJsEngine)

  private val engine = if (useBrowserOnlyJsEngine) FastJsHttpEngine() else KtorHttpEngine(connectTimeoutMillis, readTimeoutMillis)

  override suspend fun execute(request: HttpRequest): HttpResponse {
    return engine.execute(request)
  }

  override fun dispose() {
    engine.dispose()
  }
}
