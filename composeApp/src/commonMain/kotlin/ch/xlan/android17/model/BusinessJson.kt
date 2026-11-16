package ch.xlan.android17.model

import kotlinx.serialization.json.Json

val BusinessJson = Json {
    prettyPrint = true
    encodeDefaults = true
}

fun Business.toJson(): String = BusinessJson.encodeToString(Business.serializer(), this)
