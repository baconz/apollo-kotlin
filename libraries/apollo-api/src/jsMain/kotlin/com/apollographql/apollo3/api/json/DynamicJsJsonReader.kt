package com.apollographql.apollo3.api.json


@Suppress("NOTHING_TO_INLINE")
private inline fun objectKeys(json: dynamic) = js("Object").keys(json).unsafeCast<Array<String>>()

/**
 * A [JsonReader] that reads data from a regular JS `dynamic` object.
 *
 * Dynamic values should be any of:
 * - String
 * - Int
 * - Double
 * - Long
 * - JsonNumber
 * - null
 * - `dynamic` where values are any of these values recursively
 * - Array<Any?> where values are any of these values recursively
 *
 * Anything else is undefined
 *
 * TODO Note: We can probably safely remove the non-JS primitives (ie Long, Double, JsonNumber)
 *
 */
class DynamicMapEntry<K, V>(
    override val key: K,
    override val value: V
) : Map.Entry<K, V>

class DynamicJsJsonReader constructor(
    root: dynamic,
    pathRoot: List<Any> = emptyList(),
) : MapishJsonReader<dynamic>(
    root = root,
    pathRoot = pathRoot
) {
  override fun anyToToken(any: Any?) = when (any) {
    null -> JsonReader.Token.NULL
    is Array<*> -> JsonReader.Token.BEGIN_ARRAY
    is Int -> JsonReader.Token.NUMBER
    is Long -> JsonReader.Token.LONG
    is Double -> JsonReader.Token.NUMBER
    is JsonNumber -> JsonReader.Token.NUMBER
    is String -> JsonReader.Token.STRING
    is Boolean -> JsonReader.Token.BOOLEAN
    else -> {
      if (objectKeys(any).isNotEmpty()) {
        JsonReader.Token.BEGIN_OBJECT
      } else {
        JsonReader.Token.ANY
      }
    }
  }

  override fun getArrayIterator(container: Any): Iterator<Any?> {
    @Suppress("UNCHECKED_CAST")
    return (container as Array<Any?>).iterator()
  }

  override fun getMapIterator(container: dynamic): Iterator<Map.Entry<String, Any?>> {
    return objectKeys(container).map { key -> DynamicMapEntry(key, container[key]) }.iterator()
  }
}
