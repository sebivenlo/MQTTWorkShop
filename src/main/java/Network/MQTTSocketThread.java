/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import com.mycompany.mqttproject.MQTTBroker;
import com.mycompany.mqttproject.MQTTClient;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author herm7
 */
public class MQTTSocketThread implements Runnable {

    MQTTBroker broker = null;

    public MQTTSocketThread(MQTTBroker broker) {
        this.broker = broker;
    }

    private void handle() throws IOException {
        while (true) {
            Socket t = broker.server.CheckConnection();
            broker.connect(new MQTTClient(t,"",""));
            System.out.println(t.getInetAddress().toString());
        }
    }

    @Override
    public void run() {
        try {
            handle();
        } catch (IOException ex) {
            Logger.getLogger(MQTTInputstreamHandler.class.getName()).log(
                    Level.INFO, null, ex);
        }
    }

}
