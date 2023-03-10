package com.apollographql.apollo3.api.json

import com.apollographql.apollo3.api.http.HttpResponse

actual fun HttpResponse.jsonReader(): JsonReader {
  return if (bodyDynamic != null) {
    DynamicJsJsonReader(bodyDynamic)
  } else {
    defaultJsonReader()
  }
}
