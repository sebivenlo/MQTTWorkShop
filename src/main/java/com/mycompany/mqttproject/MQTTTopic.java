/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mqttproject;

import java.util.ArrayList;

/**
 *
 * @author herm7
 */
class MQTTTopic {

    ArrayList<Client> members;
    String groupId;

    public MQTTTopic(String id) {
        members = new ArrayList<>();
        groupId = id;
    }

    void dispose() {
        members = null;
    }

    void join(MQTTClient c) {
        if (!members.contains(c)) {
            members.add(c);
        }
    }

    void leave(MQTTClient c) {
        if (members.contains(c)) {
            members.remove(c);
        }
    }
    
    
}
