/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatbox;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 *
 * @author Tom
 */
public class PAHOClient {

    int qos = 2;
    String broker = "tcp://localhost:1883";
    String clientId = "JavaSample";
    MemoryPersistence persistence = new MemoryPersistence();
    MqttClient sampleClient;

    public PAHOClient(String broker, String clientID) {
        try {
            this.broker = broker;
            this.clientId = clientID;

            sampleClient = new MqttClient(broker, clientId, persistence);

        } catch (MqttException ex) {
            Logger.getLogger(PAHOClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Publish(String topic, String messagetext) {
        try {
            System.out.println("Publishing message: " + messagetext);
            MqttMessage message = new MqttMessage(messagetext.getBytes());
            message.setQos(qos);
            sampleClient.publish(topic, message);
            System.out.println("Message published");
        } catch (MqttException ex) {
            System.out.println(ex);
        }
    }

    public void Disconnect() {
        try {
            sampleClient.disconnect();
            System.out.println("Disconnected");
        } catch (MqttException ex) {
            System.out.println(ex);
        }
    }

    public void Subscribe(String topic, ChatCallback cb) {
        try {
            sampleClient.subscribe(topic);
            sampleClient.setCallback(new ChatCallback());
        } catch (MqttException ex) {
            System.out.println(ex);
        }
    }

    public void Connect() {
        try {

            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            System.out.println("Connecting to broker: " + broker);
            sampleClient.connect(connOpts);
            System.out.println("Connected");

        } catch (MqttException ex) {
            System.out.println("Error while connecting. \n stacktrace was:\n" + ex);
        }
    }
}
