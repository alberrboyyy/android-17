package ch.xlan.android17.model

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

@OptIn(ExperimentalSerializationApi::class)
@Serializable
data class Product(
    @SerialName("Id") @JsonNames("ID") val id: String,
    @SerialName("Name") val name: String,
    @SerialName("Picture") val picture: String,
    @SerialName("Price") val priceCents: Int,
)
