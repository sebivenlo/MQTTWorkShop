/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatbox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;
import javax.swing.event.EventListenerList;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 *
 * @author Tom
 */
public class ChatCallback implements MqttCallback {

    
    @Override
    public void connectionLost(Throwable thrwbl) {
        //System.out.println("Connection was lost");
    }

    @Override
    public void messageArrived(String string, MqttMessage mm) throws Exception {
        System.out.println(string + " : " + mm.toString());
        MessageEvent me = new MessageEvent(this, 0, "", string, mm.toString());
        //hier crasht de app bij het doorsturen. waarschijnlijk gaat er iets mis in het vuren van dit event
        
        FXMLDocumentController Listener = (FXMLDocumentController)xxxListeners.getListenerList()[1];
        Listener.actionPerformed(me);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken imdt) {
        System.out.println("Message Delivery Completed");
        
    }

    EventListenerList xxxListeners = new EventListenerList();

    public void addListener(EventListener listener) {
        xxxListeners.add(EventListener.class, listener);
    }

    public void removeListener(EventListener listener) {
        xxxListeners.remove(EventListener.class, listener);
    }

}
