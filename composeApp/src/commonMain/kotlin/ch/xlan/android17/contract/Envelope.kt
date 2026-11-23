package ch.xlan.android17.contract

import ch.xlan.android17.model.UmddJson
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject
import kotlin.time.ExperimentalTime
import kotlin.time.Instant
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Serializable
data class Envelope(
    val v: Int,
    val id: String,
    val from: String,
    val to: String,
    val ts: String,
    val type: String,
    val payload: JsonObject,
) {
    @OptIn(ExperimentalUuidApi::class, ExperimentalTime::class)
    fun validate() {
        require(v == 1) { "v doit valoir 1 (reçu $v)" }
        require(runCatching { Uuid.parse(id) }.isSuccess) { "id n'est pas un UUID : $id" }
        require(from.isNotEmpty()) { "from est vide" }
        require(to.isNotEmpty()) { "to est vide" }
        require(runCatching { Instant.parse(ts) }.isSuccess) { "ts n'est pas une date ISO 8601 : $ts" }
        require(type in TYPES) { "type inconnu : $type" }
    }

    fun isFor(node: String): Boolean = to == "*" || to == node

    companion object {
        val TYPES = setOf("time", "log")
    }
}

fun decodeEnvelope(text: String): Envelope =
    UmddJson.decodeFromString(Envelope.serializer(), text).also(Envelope::validate)
