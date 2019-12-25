/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatbox;

import java.util.EventListener;
import javax.swing.event.EventListenerList;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * call back for the chat box.
 * provides functionality for updating the chat box when new messages arrive.
 * @author Tom Verstraten & Herm Lecluse
 */
public class ChatCallback implements MqttCallback {

    /**
     * EventListnerList
     */
    EventListenerList xxxListeners = new EventListenerList();
    
    @Override
    public void connectionLost(Throwable thrwbl) {
        System.out.println("Connection was lost");
    }

    @Override
    public void messageArrived(String string, MqttMessage mm) throws Exception {
        System.out.println(string + " : " + mm.toString());
        MessageEvent me = new MessageEvent(this, 0, "", string, mm.toString());
         
        FXMLDocumentController Listener = (FXMLDocumentController)xxxListeners.getListenerList()[1];
        Listener.actionPerformed(me);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken imdt) {
        
    }


    public void addListener(EventListener listener) {
        xxxListeners.add(EventListener.class, listener);
    }

    public void removeListener(EventListener listener) {
        xxxListeners.remove(EventListener.class, listener);
    }

}
