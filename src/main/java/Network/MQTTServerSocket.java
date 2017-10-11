/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author herm7
 */
public class MQTTServerSocket {

    ServerSocket myService; //new ServerSocket(port);
    int port = 4444;
    Socket clientSocket = null;
    public DataInputStream input = null;
    public PrintStream output = null;
    

    public MQTTServerSocket() throws IOException {
        myService = new ServerSocket(port);
    }

    public Socket CheckConnection() throws IOException {

        clientSocket = myService.accept();
        //init streams
        receiveData();
        sendData();
        return clientSocket;
    }

    public void receiveData() throws IOException {
        input = new DataInputStream(clientSocket.getInputStream());
    }

    public void sendData() throws IOException {
        output = new PrintStream(clientSocket.getOutputStream());
    }
    
}
