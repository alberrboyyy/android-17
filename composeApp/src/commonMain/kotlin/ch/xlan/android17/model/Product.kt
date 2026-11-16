package ch.xlan.android17.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Product(
    @SerialName("ID") val id: String,
    @SerialName("Name") val name: String,
    @SerialName("Picture") val picture: String,
    @SerialName("Price") val priceCents: Int,
)
