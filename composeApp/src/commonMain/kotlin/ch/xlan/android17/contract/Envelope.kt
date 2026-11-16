package ch.xlan.android17.contract

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class Envelope(
    val v: Int,
    val id: String,
    val from: String,
    val to: String,
    val ts: String,
    val type: String,
    val payload: JsonObject,
)
