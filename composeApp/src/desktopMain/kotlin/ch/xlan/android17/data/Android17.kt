package ch.xlan.android17.data

import ch.xlan.android17.model.Business
import ch.xlan.android17.model.Product
import ch.xlan.android17.model.imageDataUri

fun android17(): Business = Business(
    id = "COM-0017",
    name = "Android 17",
    logo = resourceImage("images/logo.png"),
    special = Product(
        id = "PROD-0017",
        name = "Cinnamon Bun",
        picture = resourceImage("images/cinnamon-bun.jpg"),
        priceCents = 450,
    ),
)

private fun resourceImage(path: String): String {
    val bytes = Thread.currentThread().contextClassLoader.getResourceAsStream(path)
        ?.use { it.readBytes() }
        ?: error("Image introuvable dans les ressources : $path")
    val mimeType = when (path.substringAfterLast('.').lowercase()) {
        "png" -> "image/png"
        "jpg", "jpeg" -> "image/jpeg"
        "webp" -> "image/webp"
        else -> error("Format d'image non supporté : $path")
    }
    return imageDataUri(mimeType, bytes)
}
