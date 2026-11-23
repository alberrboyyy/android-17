package ch.xlan.android17.contract

import ch.xlan.android17.model.UmddJson
import kotlinx.serialization.Serializable

@Serializable
data class TimePayload(
    val timestamp: Long,
)

fun decodeTimestamp(text: String, node: String): Result<Long> = runCatching {
    val envelope = decodeEnvelope(text)
    require(envelope.type == "time") { "type ${envelope.type} reçu sur le topic du temps" }
    require(envelope.isFor(node)) { "destiné à ${envelope.to}" }
    UmddJson.decodeFromJsonElement(TimePayload.serializer(), envelope.payload).timestamp
}
