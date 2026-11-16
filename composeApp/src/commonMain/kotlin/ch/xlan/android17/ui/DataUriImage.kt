package ch.xlan.android17.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import ch.xlan.android17.model.decodeDataUri

@Composable
fun DataUriImage(dataUri: String, description: String, modifier: Modifier = Modifier) {
    val bitmap = remember(dataUri) { decodeDataUri(dataUri)?.let(::decodeImage) }
    if (bitmap != null) {
        Image(bitmap, description, contentScale = ContentScale.Crop)
    } else {
        Box(contentAlignment = Alignment.Center) {
            Text("Image illisible")
        }
    }
}
