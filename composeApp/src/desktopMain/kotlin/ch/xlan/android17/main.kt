package ch.xlan.android17

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import ch.xlan.android17.data.android17
import ch.xlan.android17.data.businessFile
import ch.xlan.android17.data.readClipboardText
import ch.xlan.android17.data.saveBusiness
import ch.xlan.android17.time.ClockState
import ch.xlan.android17.time.MqttConfig
import ch.xlan.android17.time.MqttTimeSource
import kotlinx.coroutines.flow.MutableStateFlow

fun main() {
    val business = android17()

    val config = MqttConfig.load()
    val timeSource = config.getOrNull()?.let(::MqttTimeSource)?.apply { start() }
    val clockFlow = timeSource?.state
        ?: MutableStateFlow(ClockState.Unavailable(config.exceptionOrNull()?.message ?: "configuration MQTT invalide"))

    application {
        val clock by clockFlow.collectAsState()

        Window(
            onCloseRequest = {
                timeSource?.stop()
                exitApplication()
            },
            title = "Android 17",
            state = rememberWindowState(width = 900.dp, height = 800.dp),
        ) {
            App(
                business,
                clock,
                onExport = {
                    saveBusiness(it)
                    businessFile.toString()
                },
                onImport = ::readClipboardText,
            )
        }
    }
}
