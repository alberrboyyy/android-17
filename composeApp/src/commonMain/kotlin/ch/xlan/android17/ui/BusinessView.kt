package ch.xlan.android17.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ch.xlan.android17.model.Business
import ch.xlan.android17.model.formatPrice

@Composable
fun BusinessView(business: Business, modifier: Modifier = Modifier) {
    Column(modifier, verticalArrangement = Arrangement.spacedBy(24.dp)) {
        GridRow(
            image = { DataUriImage(business.logo, business.name, Modifier.fillMaxSize()) },
            text = {
                Field(business.id)
                Title(business.name)
            },
        )
        GridRow(
            image = {
                DataUriImage(
                    business.special.picture,
                    business.special.name,
                    Modifier.fillMaxHeight().aspectRatio(1f),
                )
            },
            text = {
                Field(business.special.id)
                Title(business.special.name)
                Field("Prix", formatPrice(business.special.priceCents))
            },
        )
    }
}

@Composable
private fun GridRow(image: @Composable () -> Unit, text: @Composable () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(Modifier.weight(1f).aspectRatio(16f / 9f), contentAlignment = Alignment.Center) {
            image()
        }
        Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            text()
        }
    }
}

@Composable
private fun Field(label: String, value: String) {
    Column {
        Text(label, style = MaterialTheme.typography.caption)
        Text(value, style = MaterialTheme.typography.body1)
    }
}

@Composable
private fun Field(value: String) {
    Text(value, style = MaterialTheme.typography.body1)
}

@Composable
private fun Title(value: String) {
    Text(value, style = MaterialTheme.typography.h5)
}
