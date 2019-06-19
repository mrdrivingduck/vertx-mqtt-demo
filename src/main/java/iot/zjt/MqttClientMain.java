package iot.zjt;

import io.vertx.core.Vertx;
import io.vertx.mqtt.MqttClient;

public class MqttClientMain {

    public static void main(String[] args) {
        
        Vertx vertx = Vertx.vertx();
        MqttClient mqttClient = MqttClient.create(vertx);

        mqttClient.connect(12888, "localhost", conn -> {
            
            if (conn.succeeded()) {
                System.out.println("Client connect success");
            } else if (conn.failed()) {
                System.out.println("Client connect failed");
            }

            mqttClient.disconnect();
            vertx.close(v -> {
                System.out.println("MQTT client terminated");
            });

        });
    }
}