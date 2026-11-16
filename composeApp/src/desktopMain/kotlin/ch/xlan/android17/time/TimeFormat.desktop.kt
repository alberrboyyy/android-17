package ch.xlan.android17.time

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

private val zone = ZoneId.systemDefault()
private val timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss").withZone(zone)
private val dateFormatter = DateTimeFormatter.ofPattern("EEEE d MMMM yyyy", Locale.FRENCH).withZone(zone)

actual fun formatTime(unixTimeStamp: Long): String =
    timeFormatter.format(Instant.ofEpochSecond(unixTimeStamp))

actual fun formatDate(unixTimeStamp: Long): String =
    dateFormatter.format(Instant.ofEpochSecond(unixTimeStamp))
