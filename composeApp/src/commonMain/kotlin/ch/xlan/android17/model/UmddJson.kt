package ch.xlan.android17.model

import kotlinx.serialization.json.Json

val UmddJson = Json {
    prettyPrint = true
    encodeDefaults = true
    ignoreUnknownKeys = true
}

fun Business.toJson(): String = UmddJson.encodeToString(Business.serializer(), this)

fun decodeBusiness(text: String): Business = UmddJson.decodeFromString(Business.serializer(), text)
