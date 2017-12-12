/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatbox;

import java.awt.event.ActionEvent;

/**
 *
 * @author Tom & Herm
 */
public class MessageEvent extends ActionEvent{
    
    public String topic;
    public String message;
    
    public MessageEvent(Object source, int id, String command, String topic, String message) {
        super(source, id, command);
        this.topic = topic;
        this.message = message;        
    }
    

    
}
