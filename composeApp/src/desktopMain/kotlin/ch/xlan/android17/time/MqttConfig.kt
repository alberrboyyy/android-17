package ch.xlan.android17.time

import kotlin.io.path.Path
import kotlin.io.path.exists
import kotlin.io.path.readLines

data class MqttConfig(
    val host: String,
    val port: Int,
    val user: String,
    val password: String,
    val topic: String,
) {
    companion object {
        fun load(): Result<MqttConfig> = runCatching {
            val values = readDotEnv() + System.getenv()
            fun value(vararg keys: String) = keys.firstNotNullOfOrNull { values[it]?.takeIf(String::isNotBlank) }
            MqttConfig(
                host = value("MQTT_HOST") ?: "mqtt.blue.section-inf.ch",
                port = value("MQTT_PORT")?.toInt() ?: 1883,
                user = value("MQTT_USER") ?: error("MQTT_USER missing"),
                password = value("MQTT_PASSWORD", "MQTT_PASS") ?: error("MQTT_PASSWORD missing"),
                topic = value("MQTT_TOPIC") ?: "umddcmid3b/time",
            )
        }

        private fun readDotEnv(): Map<String, String> {
            val file = listOf(Path(".env"), Path("..", ".env")).firstOrNull { it.exists() } ?: return emptyMap()
            return file.readLines()
                .map(String::trim)
                .filter { it.isNotEmpty() && !it.startsWith("#") && "=" in it }
                .associate { line ->
                    val (key, value) = line.split("=", limit = 2)
                    key.trim() to value.trim().removeSurrounding("\"")
                }
        }
    }
}
