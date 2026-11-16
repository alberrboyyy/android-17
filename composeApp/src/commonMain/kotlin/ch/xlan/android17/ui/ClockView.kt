package ch.xlan.android17.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ch.xlan.android17.time.ClockState
import ch.xlan.android17.time.formatDate
import ch.xlan.android17.time.formatTime

@Composable
fun ClockView(clock: ClockState, modifier: Modifier = Modifier) {
    Column(modifier) {
        when (clock) {
            ClockState.Connecting -> Text("Connexion à la source de temps…", style = MaterialTheme.typography.body1)
            is ClockState.Unavailable -> Text(
                "Heure indisponible : ${clock.reason}",
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.error,
            )
            is ClockState.Synced -> {
                Text(formatTime(clock.unixTimeStamp), style = MaterialTheme.typography.h4)
                Text(formatDate(clock.unixTimeStamp), style = MaterialTheme.typography.caption)
            }
        }
    }
}
