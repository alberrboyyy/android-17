package ch.xlan.android17

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ch.xlan.android17.model.Business
import ch.xlan.android17.model.decodeBusiness
import ch.xlan.android17.time.ClockState
import ch.xlan.android17.ui.BusinessView
import ch.xlan.android17.ui.ClockView

@Composable
fun App(
    business: Business,
    clock: ClockState,
    onExport: (Business) -> String,
    onImport: () -> String,
) {
    var displayed by remember { mutableStateOf(business) }
    var status by remember { mutableStateOf<String?>(null) }
    val showingOther = displayed != business

    MaterialTheme {
        Surface(Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState()).padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp),
            ) {
                ClockView(clock)
                BusinessView(displayed)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Button(onClick = {
                        status = runCatching { "Exporté dans ${onExport(business)}" }
                            .getOrElse { "Échec de l'export : ${it.message}" }
                    }) {
                        Text("Exporter")
                    }
                    OutlinedButton(onClick = {
                        status = runCatching { decodeBusiness(onImport()) }
                            .onSuccess { displayed = it }
                            .fold(
                                onSuccess = { "Importé : ${it.id} ${it.name}" },
                                onFailure = { "Échec de l'import : ${it.message?.lineSequence()?.first()}" },
                            )
                    }) {
                        Text("Importer")
                    }
                    if (showingOther) {
                        OutlinedButton(onClick = {
                            displayed = business
                            status = null
                        }) {
                            Text("Ma BP")
                        }
                    }
                }
                status?.let { Text(it, style = MaterialTheme.typography.body2) }
            }
        }
    }
}
