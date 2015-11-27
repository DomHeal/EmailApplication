package com;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.control.MasterDetailPane;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Dominic on 26-Nov-15.
 */
public class MainController implements Initializable{
    @FXML
    MasterDetailPane pane;
    static TableView master = new TableView();
    static TextArea detail = new TextArea();
    private TableColumn contentCol;
    private TableColumn emailCol;
    private TableColumn subjectCol;
    private TableColumn numCol;
    private static final ObservableList<MessageHolder> data = FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        numCol = new TableColumn("#");
        numCol.setPrefWidth(50);
        subjectCol = new TableColumn("Subject");
        subjectCol.setPrefWidth(400);
        emailCol = new TableColumn("Email");
        emailCol.setPrefWidth(200);
        contentCol = new TableColumn("Content");
        emailCol.setPrefWidth(500);

        numCol.setCellValueFactory(
                new PropertyValueFactory<MessageHolder, String>("emailNumber"));
        subjectCol.setCellValueFactory(
                new PropertyValueFactory<MessageHolder, String>("subject"));
        emailCol.setCellValueFactory(
                new PropertyValueFactory<MessageHolder, String>("emailAddress"));
        contentCol.setCellValueFactory(
                new PropertyValueFactory<MessageHolder, String>("content"));

        master.getColumns().addAll(numCol, subjectCol, emailCol, contentCol);
        master.setEditable(false);
        master.setItems(data);

        pane.setDetailNode(detail);
        pane.setMasterNode(master);

    }

    public static void printMessages(Message[] messages) throws MessagingException, IOException {

        for (int i = 0, n = messages.length; i < n; i++) {
            Message message = messages[i];
            data.add(new MessageHolder(Integer.toString(i + 1),
                    message.getFrom()[0].toString(),
                    message.getSubject(),
                    message.getContent().toString()));

        }
    }
}
