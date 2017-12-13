/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatbox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.EventListenerList;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 *
 * @author Tom & Herm
 */
public class ChatCallback implements MqttCallback {

    
    @Override
    public void connectionLost(Throwable thrwbl) { 
        try {
            throw thrwbl;
        } catch (Throwable ex) {
            Logger.getLogger(ChatCallback.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    EventListenerList xxxListeners = new EventListenerList();

    public void addListener(EventListener listener) {
        xxxListeners.add(EventListener.class, listener);
    }

    public void removeListener(EventListener listener) {
        xxxListeners.remove(EventListener.class, listener);
    }

}
