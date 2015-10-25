package sample;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.mail.*;
import javax.mail.search.StringTerm;
import java.util.Properties;

public class Controller {

    @FXML private ChoiceBox dropMenuServer;
    @FXML private PasswordField passwordField;
    @FXML private TextField emailField;

    public void connectButtonAction() {
        System.out.println("-----------------------");
        System.out.println("Attempting to Connect");
        System.out.println("-----------------------");
        String mailStoreType = "pop3";
        String username = emailField.getText();
        String password = passwordField.getText();
        System.out.println("Server: " + dropMenuServer.getSelectionModel().getSelectedIndex());
        String host = serverSelected();

        System.out.println("Credentials Acquired!");
        System.out.println("Email: " + username);
        System.out.println("Password: " + password);
        System.out.println("Server: " + dropMenuServer.getSelectionModel().getSelectedIndex());

        System.out.println("-----------------------");
        System.out.println("Starting Thread....");
        Thread y = new Thread(new ThreadWorker(host, mailStoreType, username, password));
        y.start();
    }

    public String serverSelected(){

        System.out.println("Picking Server");
        String server = null;
        switch (dropMenuServer.getSelectionModel().getSelectedIndex()){
            case 0: server = "";
                break;
            case 1: server = "pop.gmail.com";
                ;
                break;
            case 2: server = "";
        }

        System.out.println("Sever: " + server);
        return server;
    }

}
