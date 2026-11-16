package ch.xlan.android17.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Business(
    @SerialName("ID") val id: String,
    @SerialName("Name") val name: String,
    @SerialName("Logo") val logo: String,
    @SerialName("Special") val special: Product,
)
