package ch.xlan.android17.contract

import ch.xlan.android17.model.UmddJson
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TimePayload(
    val timestamp: Long,
)

@Serializable
private data class LegacyTimeMessage(
    @SerialName("UnixTimeStamp") val unixTimeStamp: Long,
)

fun decodeTimestamp(text: String): Long? =
    decodeEnvelopeTimestamp(text) ?: decodeLegacyTimestamp(text)

private fun decodeEnvelopeTimestamp(text: String): Long? = runCatching {
    val envelope = UmddJson.decodeFromString(Envelope.serializer(), text)
    require(envelope.v == 1)
    require(envelope.type == "time")
    UmddJson.decodeFromJsonElement(TimePayload.serializer(), envelope.payload).timestamp
}.getOrNull()

private fun decodeLegacyTimestamp(text: String): Long? = runCatching {
    UmddJson.decodeFromString(LegacyTimeMessage.serializer(), text).unixTimeStamp
}.getOrNull()
