package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;


public class ControllerLogin {

        @FXML
        private TextField login_email;

        @FXML
        private PasswordField login_password;

        @FXML
        private TextField accedi_nome;

        @FXML
        private TextField accedi_cognome;

        @FXML
        public void registerButtonClick(ActionEvent event) throws IOException {

            Stage oldstage = (Stage)((Node) event.getSource()).getScene().getWindow();
            oldstage.close();

            Parent root = FXMLLoader.load(getClass().getResource("../View/RegisterFrame.fxml"));
            Stage stage=new Stage();
            stage.setTitle("Register");
            stage.setScene(new Scene(root));
            stage.show();

        }

        @FXML
        public void loginButtonClick(ActionEvent event) throws IOException {

            String emailRecieved= login_email.getText();
            String passwordRecieved= login_password.getText();
            System.out.println("Email:"+ emailRecieved+"\n"+"password:"+ passwordRecieved +"\n");
        }

        @FXML
        public void accediButtonClick(ActionEvent event) throws IOException {

            String nameRecieved =accedi_nome.getText();
            String surnameRecieved=accedi_cognome.getText();

            System.out.println("Nome:"+ nameRecieved+"\n"+"Cognome:"+ surnameRecieved +"\n");

        }
}
