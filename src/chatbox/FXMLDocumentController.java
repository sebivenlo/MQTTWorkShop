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
 * @author Tom
 */
public class FXMLDocumentController implements Initializable, ActionListener {

    @FXML
    TextField tbTopic;

    @FXML
    TextArea tbMessage;

    PAHOClient paho;
    @FXML
    private Label label;

    @FXML
    private ListView lvChatLog;

    int counter;

    @FXML
    private void Publish(ActionEvent event) {
        if (!tbTopic.getText().isEmpty()) {
            counter++;
            paho.Publish(tbTopic.getText(), tbMessage.getText());
            //lvChatLog.getItems().add(tbMessage.getText());
            tbMessage.clear();
        } else {
            System.out.println("Please enter a topic");
        }
    }

    ChatCallback cbc;

    @FXML
    private void Subscribe(ActionEvent event) {
        cbc = new ChatCallback();
        paho.Subscribe(tbTopic.getText(), cbc);
        cbc.addListener(this);
    }

    @FXML
    private void Disconnect(ActionEvent event) {
        paho.Disconnect();
    }

    @FXML
    private void Connect(ActionEvent event) {

        paho = new PAHOClient("tcp://localhost:1883", "Henk");
        paho.Connect();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
