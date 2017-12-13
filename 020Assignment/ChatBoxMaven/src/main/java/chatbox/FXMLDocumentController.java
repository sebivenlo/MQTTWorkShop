/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatbox;

import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

/**
 *
 * @author Tom & Herm
 */
public class FXMLDocumentController implements Initializable, ActionListener {

    @FXML
    Button btnSend;
    
    @FXML
    Button btnConnect;
    
    @FXML
    Button btnDisconnect;
    
    @FXML
    Button btnJoin;
    
    @FXML
    TextField tbTopic;
    
    @FXML
    TextField tbUserId;
    
    @FXML
    TextField tbIpAdress;

    @FXML
    TextArea tbMessage;

    @FXML
    private Label label;

    @FXML
    private ListView lvChatLog;

    PAHOClient client;
    ChatCallback chatCallBack;
    
    
    /**
     * Connect to the PAHOClient which you just implemented. 
     * @param event event is being parsed by the GUI.
     * use tbIpAdress and tbUserId from the gui to initialize the client.
     * check if IpAdress is empty before trying to connect. (prevent crash).
     */
    @FXML
    private void Connect(ActionEvent event) {
        //TODO 2.0 implement connect. 
        switchButtons(true);
    }
    
    /**
     * Disconnect from the PAHOClient.
     * @param event event is being parsed by the GUI. 
     */
    @FXML
    private void Disconnect(ActionEvent event) {
        //TODO 2.1 same as in the previous disconnect just call the method.
        switchButtons(false);
    }
    
    /**
     * Subscribe to a Topic.
     * @param event event is being parsed by the GUI.
     * Subscribe to the topic that is set in the gui. This is stored in the tbTopic variable.
     * Just like with the connect implementation, please check if the topic is filled before subscribing to null.
     */
    @FXML
    private void Subscribe(ActionEvent event) {
        //TODO 2.2 implement subscribe
    }
    
    /**
     * Publish to an topic.
     * @param event event is being parsed by the GUI.
     * publish to the topic entered. 
     * make sure the text in tbMessage and tbTopic is not empty.
     */
    @FXML
    private void Publish(ActionEvent event) {
        //TODO 2.3 implement publish
        
    }
    /**
     * function disabling buttons whenever needed.
     * @param enabled 
     */
    private void switchButtons(boolean enabled ) {
        this.btnConnect.disableProperty().set(enabled);
        this.btnDisconnect.disableProperty().set(!enabled);
        this.btnSend.disableProperty().set(!enabled);
        this.btnJoin.disableProperty().set(!enabled);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        switchButtons(false);
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        MessageEvent ev = (MessageEvent) e;

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                lvChatLog.getItems().add(ev.topic + " : " + ev.message);
            }
        }
        );

    }

}
