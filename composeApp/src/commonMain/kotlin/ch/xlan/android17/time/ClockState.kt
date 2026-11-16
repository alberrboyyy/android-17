package ch.xlan.android17.time

sealed interface ClockState {
    data object Connecting : ClockState
    data class Unavailable(val reason: String) : ClockState
    data class Synced(val unixTimeStamp: Long) : ClockState
}
