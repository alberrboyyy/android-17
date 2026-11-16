package ch.xlan.android17.ui

import androidx.compose.ui.graphics.ImageBitmap

expect fun decodeImage(bytes: ByteArray): ImageBitmap?
