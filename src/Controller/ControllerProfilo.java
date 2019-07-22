package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerProfilo implements Initializable {

    @FXML
    private AnchorPane anchor_pane;

    @FXML
    private Label label_nome;

    @FXML
    private Label lable_cognome;

    @FXML
    private Label lable_cap;

    @FXML
    private Label lable_email;

    @FXML
    private Label lable_città;

    @FXML
    private Label lable_cellulare;

    @FXML
    private Label lable_libroCard;

    private String email = ControllerLogin.getEmailLoggedas();

    @FXML
    public void modificaDatiButtonClicked(ActionEvent event){


    }

    public void initialize(URL location, ResourceBundle resources){

    }


}
