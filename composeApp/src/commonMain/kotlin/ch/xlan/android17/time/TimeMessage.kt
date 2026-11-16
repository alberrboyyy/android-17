package ch.xlan.android17.time

import ch.xlan.android17.model.UmddJson
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TimeMessage(
    @SerialName("UnixTimeStamp") val unixTimeStamp: Long,
)

fun decodeTimeMessage(text: String): TimeMessage? =
    runCatching { UmddJson.decodeFromString(TimeMessage.serializer(), text) }.getOrNull()
