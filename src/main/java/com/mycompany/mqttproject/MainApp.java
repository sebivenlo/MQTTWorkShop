package com.mycompany.mqttproject;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MainApp extends Application {

    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");

        stage.setTitle("JavaFX and Maven");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MqttException, MqttException {
        MqttClient client = new MqttClient("tcp://localhost:4444", MqttClient.generateClientId());
        client.connect();
        MqttMessage message = new MqttMessage();
        message.setPayload("Hello world from Java".getBytes());
        client.publish("iot_data", message);
        client.disconnect();
//        MQTTClient client1 = new MQTTClient("client-1","herm","123");
//        MQTTClient client2 = new MQTTClient("client-2","tom","123");
//        MQTTClient client3 = new MQTTClient("client-3","loek","123");
//        broker.connect(client1);
//        broker.connect(client2);
//        broker.connect(client3);
//        MQTTTopic topic1 = new MQTTTopic("Groepsapp 1");
//        MQTTTopic topic2 = new MQTTTopic("Groepsapp 2");
//        
//        broker.subscribe(client1 , topic1);
//        broker.subscribe(client2 , topic1);
//        broker.subscribe(client3 , topic1);
//        
//        broker.subscribe(client2 , topic2);
//        broker.subscribe(client3 , topic2);
//        broker.publish("hallo ik ben gek", topic1, client1);
    }

}
