/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mqttproject;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author herm7
 */
public class MQTTClient implements Client {

    Socket socket;
    String userName, passWord;
    boolean Online;
    int keepAlive;
    MQTTBroker broker;
    public PrintStream output = null;
    public DataInputStream input = null;

    public DataInputStream getInput() {
        return input;
    }

    public void setInput(DataInputStream input) {
        this.input = input;
    }

    public MQTTClient(Socket socket, String userName, String passWord) {
        this.socket = socket;
        this.userName = userName;
        this.passWord = passWord;
    }

    public void Connect(MQTTBroker broker) {
        this.broker = broker;
        this.broker.connect(this);
    }

    public void Disconnect(MQTTBroker broker) {
        broker.disconnect(this);
    }

    @Override
    public String getIP() {
        return this.socket.getInetAddress().toString();
    }

    public void setOutput(PrintStream output) {
        this.output = output;
    }

    public PrintStream getOutput() {
        return output;
    }

    public void print(String message) {
            
        this.output.print(message);
        this.output.flush();
        
    }

    @Override
    public void setOnlineStatus(boolean online) {
        this.Online = online;
    }

    public void ReceiveFromBroker(String text, MQTTTopic topic, Client c) {
        System.out.println(c.getUserName() + " says : " + text + " in group : " + topic.groupId);
    }

    public void SendToBroker(String text, MQTTTopic topic) {
        broker.publish(text, topic, this);
    }

    @Override
    public String getUserName() {
        return this.userName;
    }

    public void terminate() {
        try {
            this.socket.shutdownOutput();
            this.socket.close();
        } catch (IOException ex) {
            Logger.getLogger(MQTTClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

