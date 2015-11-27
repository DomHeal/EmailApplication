package com;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import org.controlsfx.control.MasterDetailPane;

import javax.mail.Message;
import javax.mail.MessagingException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Dominic on 26-Nov-15.
 */
public class MainController implements Initializable{
    @FXML
    MasterDetailPane pane;
    TableView x = new TableView();
    TextArea y = new TextArea();
    private TableColumn contentCol;
    private TableColumn emailCol;
    private TableColumn subjectCol;
    private TableColumn numCol;
    private static final ObservableList<MessageHolder> data = FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        numCol = new TableColumn("#");
        subjectCol = new TableColumn("Subject");
        emailCol = new TableColumn("Email");
        contentCol = new TableColumn("Content");
        x.getColumns().addAll(numCol, subjectCol, emailCol, contentCol);
        x.setEditable(false);
        x.setItems(data);


        pane.setDetailNode(x);
        pane.setMasterNode(y);

    }

    public static void printMessages(Message[] messages) throws MessagingException, IOException {
        System.out.println("messages.length---" + messages.length);

        for (int i = 0, n = messages.length; i < n; i++) {
            Message message = messages[i];
            System.out.println("---------------------------------");

            data.add(new MessageHolder(Integer.toString(i + 1), message.getFrom()[0].toString(), message.getSubject().toString(), message.getContent().toString()));

            System.out.println("From: " + message.getFrom()[0]);
            System.out.println("Subject: " + message.getSubject());
            System.out.println("Text: " + message.getContent().toString());
        }
    }
}
