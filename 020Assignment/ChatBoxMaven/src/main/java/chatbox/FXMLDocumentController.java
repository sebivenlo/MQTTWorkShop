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
     * connect to the PAHOClient which you just implemented.
     *
     * @param event event is being parsed by the GUI.
     *              use tbIpAdress and tbUserId from the gui to initialize the client.
     *              check if IpAdress is empty before trying to connect. (prevent crash).
     */
    @FXML
    private void connect( ActionEvent event ) {
        //TODO 2.0 implement connect. 
        String ipAdress = tbIpAdress.getText();
        String clientId = tbUserId.getText();
        client = new PAHOClient( ipAdress, clientId );
        switchButtons( true );
    }

    /**
     * dicconnect from the PAHOClient.
     *
     * @param event event is being parsed by the GUI.
     */
    @FXML
    private void disConnect( ActionEvent event ) {
        //TODO 2.1 same as in the previous disconnect just call the method.
        client.dicconnect();
        switchButtons( false );
    }

    /**
     * subscribe to a Topic.
     *
     * @param event event is being parsed by the GUI.
              subscribe to the topic that is set in the gui. This is stored in the
              tbTopic variable.
              Just like with the connect implementation, please check if the topic is
              filled before subscribing to null.
     */
    @FXML
    private void subscribe( ActionEvent event ) {
        //TODO 2.2 implement subscribe
        String topic = tbTopic.getText();
        chatCallBack = new ChatCallback();
        chatCallBack.addListener( this );
        client.subscribe( topic, chatCallBack );
    }

    /**
     * publish to an topic.
     *
     * @param event event is being parsed by the GUI.
     *              publish to the topic entered.
     *              make sure the text in tbMessage and tbTopic is not empty.
     */
    @FXML
    private void publish( ActionEvent event ) {
        //TODO 2.3 implement publish
        String messagetext = tbMessage.getText();
        String topic = tbTopic.getText();
        client.publish( topic, messagetext );

    }

    /**
     * function disabling buttons whenever needed.
     *
     * @param enabled
     */
    private void switchButtons( boolean enabled ) {
        this.btnConnect.disableProperty().set( enabled );
        this.btnDisconnect.disableProperty().set( !enabled );
        this.btnSend.disableProperty().set( !enabled );
        this.btnJoin.disableProperty().set( !enabled );
    }

    @Override
    public void initialize( URL url, ResourceBundle rb ) {
        switchButtons( false );
    }

    @Override
    public void actionPerformed( java.awt.event.ActionEvent e ) {
        MessageEvent ev = ( MessageEvent ) e;

        Platform.runLater( new Runnable() {
            @Override
            public void run() {
                lvChatLog.getItems().add( ev.topic + " : " + ev.message );
            }
        }
        );

    }

}
