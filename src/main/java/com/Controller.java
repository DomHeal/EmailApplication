package com;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.event.ActionEvent;


public class Controller implements Initializable {

    @FXML private ChoiceBox dropMenuServer;
    @FXML private PasswordField passwordField;
    @FXML private TextField emailField;
    @FXML private ImageView imageLogo;


    public void connectButtonAction(ActionEvent event) throws IOException {
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

        Parent home_page_parent = FXMLLoader.load(getClass().getClassLoader().getResource("EmailView.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.show();

//        Thread y = new Thread(new ThreadWorker(host, mailStoreType, username, password));
//        y.start();
    }

    public String serverSelected(){

        System.out.println("Picking Server");
        String server = null;
        switch (dropMenuServer.getSelectionModel().getSelectedIndex()){
            case 0: server = "";
                break;
            case 1: server = "pop.gmail.com";
                break;
            case 2: server = "";
        }

        System.out.println("Sever: " + server);
        return server;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imageLogo.setImage(new Image("images/emailapp.png"));
        Soundboard x = new Soundboard();
        x.playSound();
        System.out.println("PLAY SOUND?");
    }
}
