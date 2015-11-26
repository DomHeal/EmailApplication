package com;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import org.controlsfx.control.MasterDetailPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Dominic on 26-Nov-15.
 */
public class MainController implements Initializable{
    @FXML
    MasterDetailPane pane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pane.setDetailNode(new TableView());
        pane.setMasterNode(new TextArea());
    }
}
