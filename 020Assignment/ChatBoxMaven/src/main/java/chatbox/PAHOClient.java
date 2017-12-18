/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatbox;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 *
 * @author Tom Verstraten & Herm Lecluse
 */
public class PAHOClient {

    int qos = 2;
    String ipAdress, clientId;
    MemoryPersistence persistence = new MemoryPersistence();
    MqttClient Client;

    /**
     * Constructor for the MQTTClient. the MqttClient should be initialized
     * using the ipAdress string and clientID string. please note, that the
     * ipAdress should be prefixed with "tcp://" and has to connect to port
     * 1883. (if you want to connect to a different host it might be possible
     * that the port has to change as well!)
     * @param ipAdress ip address the client should connect to
     * @param clientID the name the user gets.
     */
    public PAHOClient(String ipAdress, String clientID) {

        //TODO 1.0 implement Constructor
    }

    /**
     * Connect to the Client. a connection needs connection options Tip : create
     * a clean session ( using the MqttConnectOptions )
     */
    public void Connect() {
        //TODO 1.1 connect to the client

    }

    /**
     * Disconnect from the Client. This is a straight-forward method. don't over
     * think just close the connection.
     */
    public void Disconnect() {
        //TODO 1.2 implement Disconnect
    }

    /**
     * Subscribe to a Topic.
     * @param topic topic to subscribe to
     * @param callBack ChatCallBack, to handle messages that arrive from that
     * topic. make sure that the client is subscribed the topic. don't forget to
     * add a callback to that client for new messages.
     */
    public void Subscribe(String topic, ChatCallback callBack) {
        //TODO 1.3 implement Subscribe
    }
    
    /**
     * Method to publish a message to a topic.
     *
     * @param topic group in which the message should be sent.
     * @param messagetext actual message that should be sent. Please make sure
     * that your message will look like:
     * <TOPIC> : <CLIENTID> : <MESSAGE>
     */
    public void Publish(String topic, String messagetext) {
        MqttMessage message;
        //TODO 1.4 Implement Publish   
    }

    

}
