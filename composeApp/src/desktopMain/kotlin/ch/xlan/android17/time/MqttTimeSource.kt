package ch.xlan.android17.time

import com.hivemq.client.mqtt.MqttClient
import com.hivemq.client.mqtt.datatypes.MqttQos
import com.hivemq.client.mqtt.mqtt3.Mqtt3AsyncClient
import com.hivemq.client.mqtt.mqtt3.message.publish.Mqtt3Publish
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.UUID

class MqttTimeSource(private val config: MqttConfig) {
    private val _state = MutableStateFlow<ClockState>(ClockState.Connecting)
    val state: StateFlow<ClockState> = _state.asStateFlow()

    private val client: Mqtt3AsyncClient = MqttClient.builder()
        .useMqttVersion3()
        .identifier("android17-backoffice-${UUID.randomUUID()}")
        .serverHost(config.host)
        .serverPort(config.port)
        .automaticReconnectWithDefaultConfig()
        .addConnectedListener { subscribe() }
        .addDisconnectedListener { context ->
            _state.value = ClockState.Unavailable(context.cause.message ?: "déconnecté du broker")
        }
        .buildAsync()

    fun start() {
        client.connectWith()
            .simpleAuth()
            .username(config.user)
            .password(config.password.toByteArray())
            .applySimpleAuth()
            .send()
    }

    fun stop() {
        client.disconnect()
    }

    private fun subscribe() {
        client.subscribeWith()
            .topicFilter(config.topic)
            .qos(MqttQos.AT_MOST_ONCE)
            .callback(::onMessage)
            .send()
    }

    private fun onMessage(publish: Mqtt3Publish) {
        val text = publish.payloadAsBytes.decodeToString()
        val message = decodeTimeMessage(text)
        if (message == null) {
            System.err.println("Message time invalide ignoré sur ${publish.topic} : $text")
            return
        }
        _state.value = ClockState.Synced(message.unixTimeStamp)
    }
}
