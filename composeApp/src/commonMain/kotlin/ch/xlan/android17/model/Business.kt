package ch.xlan.android17.model

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

@OptIn(ExperimentalSerializationApi::class)
@Serializable
data class Business(
    @SerialName("Id") @JsonNames("ID") val id: String,
    @SerialName("Name") val name: String,
    @SerialName("Logo") val logo: String,
    @SerialName("Special") val special: Product,
)
