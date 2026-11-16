package ch.xlan.android17.model

import kotlin.io.encoding.Base64

fun imageDataUri(mimeType: String, bytes: ByteArray): String =
    "data:$mimeType;base64,${Base64.encode(bytes)}"

fun decodeDataUri(dataUri: String): ByteArray? =
    runCatching { Base64.decode(dataUri.substringAfter("base64,")) }.getOrNull()
