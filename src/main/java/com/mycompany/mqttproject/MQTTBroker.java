/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mqttproject;

import Network.MQTTSocketThread;
import Network.MQTTServerSocket;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author herm7
 */
public class MQTTBroker {

    //ArrayList<Client> clientList = new ArrayList<>();
    ArrayList<MQTTTopic> topicList = new ArrayList<>();
    CopyOnWriteArrayList<Client> clientList = new CopyOnWriteArrayList<>();
    public MQTTServerSocket server;

    public MQTTBroker() {
        try {
            server = new MQTTServerSocket();
            new Thread(new MQTTSocketThread(this)).start();
        } catch (IOException ex) {
            Logger.getLogger(MQTTBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void connect(Client fClient) {
        setClientStreams(fClient);
        if (!CheckIfAlreadyOnline(fClient)) {
            fClient.setOnlineStatus(true);
            fClient.print("Welcome " + fClient.getIP());
            clientList.add(fClient);
        }
        else{
            TerminateClient(fClient);
        }
    }

    private void setClientStreams(Client fClient) {
        fClient.setOutput(server.output);
        fClient.setInput(server.input);
    }

    /**
     * method to check if IP of fClient is already in clientList
     * @param fClient incomming client
     * @return true if already in list, false if not.
     */
    private boolean CheckIfAlreadyOnline(Client fClient) {
        for (Client client : clientList) {
            if (client.getIP() == null ? fClient.getIP() == null : client.getIP().equals(fClient.getIP())) {
                return true;
            }
        }
        return false;
    }

    public void disconnect(Client fClient) {
        Client c = clientList.get(clientList.indexOf(fClient));
        if (c != null) {
            c.setOnlineStatus(false);
        }
    }

    public void subscribe(Client fClient, MQTTTopic topic) {
        if (!topic.members.contains(fClient)) {
            topic.members.add(fClient);
        }
    }

    public void subscribe(Client fClient, MQTTClient client) {
        throw new UnsupportedOperationException("not yet done");
    }

    public void unsubscribe(Client fClient, MQTTTopic topic) {
        if (topic.members.contains(fClient)) {
            topic.members.remove(fClient);
        }
    }

    public void publish(String text, MQTTTopic topic, Client c) {
        for (Client client : topic.members) {
            if (!c.getIP().equals(client.getIP())) {
                client.ReceiveFromBroker(text, topic, c);
            }
        }
    }

    public void unsubscribeAllTopics(Client fClient) {
        ArrayList<MQTTTopic> topic = checkIfExistsInTopics(fClient);
        for (MQTTTopic mQTTTopic : topic) {
            topic.remove(mQTTTopic);
        }

    }

    private ArrayList<MQTTTopic> checkIfExistsInTopics(Client fClient) {
        ArrayList<MQTTTopic> result = new ArrayList<>();

        for (MQTTTopic topic : topicList) {
            if (topic.members.contains(fClient)) {
                result.add(topic);
            }
        }
        return result;
    }
    private void TerminateClient(Client fClient){
        fClient.print("You are already signed in, please close your other session");
        fClient.print("#$%");
        fClient.terminate();
    }
}
