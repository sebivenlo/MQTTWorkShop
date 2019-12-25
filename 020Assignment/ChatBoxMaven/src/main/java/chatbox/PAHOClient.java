package chatbox;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
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
    MqttClient client;

    /**
     * Constructor for the MQTTClient. the MqttClient should be initialized
     * using the ipAdress string and clientID string. please note, that the
     * ipAdress should be prefixed with "tcp://" and has to connect to port
     * 1883. (if you want to connect to a different host it might be possible
     * that the port has to change as well!)
     *
     * @param ipAdress ip address the client should connect to
     * @param clientID the name the user gets.
     */
    public PAHOClient( String ipAdress, String clientID ) {
        this.ipAdress = "tcp://" + ipAdress + ":1883";
        this.clientId = clientID;
        try {
            //TODO 1.0 implement Constructor
            client = new MqttClient( this.ipAdress, clientID, persistence );
        } catch ( MqttException ex ) {
            Logger.getLogger( PAHOClient.class.getName() ).log( Level.SEVERE, null, ex );
        }
    }

    /**
     * connect to the client. a connection needs connection options Tip : create
     * a clean session ( using the MqttConnectOptions )
     */
    public void connect() {
        try {
            //TODO 1.1 connect to the client
            client.connect();
        } catch ( MqttException ex ) {
            Logger.getLogger( PAHOClient.class.getName() ).log( Level.SEVERE, null, ex );
        }
    }

    /**
     * dicconnect from the client. This is a straight-forward method. don't over
     * think just close the connection.
     */
    public void dicconnect() {
        try {
            //TODO 1.2 implement dicconnect
            client.disconnect();
        } catch ( MqttException ex ) {
            Logger.getLogger( PAHOClient.class.getName() ).log( Level.SEVERE, null, ex );
        }
    }

    /**
     * subscribe to a Topic.
     *
     * @param topic    topic to subscribe to
     * @param callBack ChatCallBack, to handle messages that arrive from that
     *                 topic. make sure that the client is subscribed the topic. don't forget to
     *                 add a callback to that client for new messages.
     */
    public void subscribe( String topic, ChatCallback callBack ) {
        try {
            //TODO 1.3 implement subscribe
            client.setCallback( callBack );
            client.subscribe( topic );
        } catch ( MqttException ex ) {
            Logger.getLogger( PAHOClient.class.getName() ).log( Level.SEVERE, null, ex );
        }
    }

    /**
     * Method to publish a message to a topic.
     *
     * @param topic       group in which the message should be sent.
     * @param messagetext actual message that should be sent. Please make sure
     *                    that your message will look like:
     * <TOPIC> : <CLIENTID> : <MESSAGE>
     */
    public void publish( String topic, String messagetext ) {
        try {
            MqttMessage message= buildMessage( topic, messagetext );
            //TODO 1.4 Implement publish
            
            client.publish( topic ,message);
        } catch ( MqttException ex ) {
            Logger.getLogger( PAHOClient.class.getName() ).log( Level.SEVERE, null, ex );
        }
    }

    MqttMessage buildMessage( String topic, String messagetext ) {
        MqttMessage message= new MqttMessage((topic+":"+clientId+ ":"+messagetext).getBytes());
        return message;
    }

}
