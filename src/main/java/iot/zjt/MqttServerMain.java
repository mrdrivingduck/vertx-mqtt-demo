package iot.zjt;

import io.vertx.core.Vertx;
import io.vertx.mqtt.MqttServer;

public class MqttServerMain {

    public static void main(String[] args) {
        
        Vertx vertx = Vertx.vertx();
        MqttServer mqttServer = MqttServer.create(vertx);

        mqttServer.endpointHandler(endPoint -> {

            System.out.println("Get MQTT client");
            
            System.out.println(endPoint.clientIdentifier());
            System.out.println(endPoint.isCleanSession());

            endPoint.accept(false);
            endPoint.disconnectHandler(v -> {
                System.out.println("MQTT client disconnecting");
            });

        }).listen(12888, "localhost", status -> {

            if (status.succeeded()) {
                System.out.println("MQTT server is listening");
            } else {
                System.out.println("MQTT server starting error");
                status.cause().printStackTrace();
            }

        });
    }
}