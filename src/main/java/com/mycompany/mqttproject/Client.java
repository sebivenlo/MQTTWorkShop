/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mqttproject;

import java.io.DataInputStream;
import java.io.PrintStream;

/**
 *
 * @author herm7
 */
public interface Client {
    public void Connect(MQTTBroker broker);
    public void Disconnect(MQTTBroker broker);
    
    public void setOnlineStatus(boolean online);
    public String getUserName();
    public String getIP();
    public void ReceiveFromBroker(String text, MQTTTopic topic, Client c);
    public void SendToBroker(String text, MQTTTopic topic);
    public void setOutput(PrintStream output);
    public PrintStream getOutput();
    public void print(String message);
    public void setInput(DataInputStream output);
    public DataInputStream getInput();

    public void terminate();
}
