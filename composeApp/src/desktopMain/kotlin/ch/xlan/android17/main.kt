package ch.xlan.android17

import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import ch.xlan.android17.data.android17
import ch.xlan.android17.data.businessFile
import ch.xlan.android17.data.saveBusiness

fun main() {
    val business = android17()

    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "Android 17",
            state = rememberWindowState(width = 900.dp, height = 700.dp),
        ) {
            App(business, onExport = {
                saveBusiness(it)
                businessFile.toString()
            })
        }
    }
}
