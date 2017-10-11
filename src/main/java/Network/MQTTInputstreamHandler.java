/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author herm7
 */
public class MQTTInputstreamHandler implements Runnable {

    Socket clientSocket = null;

    public MQTTInputstreamHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    private void handle() throws IOException {
        DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());
        while (true) {
            
            if (inputStream.available() != 0) {
                System.out.println(inputStream.readLine());
            }
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
