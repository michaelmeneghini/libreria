package Controller;

import Model.DBConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

import java.sql.*;
import java.util.regex.Pattern;


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
        private Label login_error;

        @FXML
        public void registerButtonClick(ActionEvent event) throws IOException {

            Parent registerFrameParent=FXMLLoader.load(getClass().getResource("../View/RegisterFrame.fxml"));
            Scene registerFrame=new Scene(registerFrameParent);
            Stage window=(Stage)(((Node)event.getSource()).getScene().getWindow());
            window.setScene(registerFrame);
            window.show();

        }

        @FXML
        public void loginButtonClick(ActionEvent event) throws IOException, SQLException {

            login_error.setText("");

            String emailRecieved=login_email.getText();
            String passwordRecieved= login_password.getText();

            if(!isValidPassword(emailRecieved,passwordRecieved)){
                login_error.setText("Email non valida!");
            }

        }

        @FXML
        public void accediButtonClick(ActionEvent event) throws IOException {

            String nameRecieved =accedi_nome.getText();
            String surnameRecieved=accedi_cognome.getText();

            //TODO: Implementare apertura della schermata principale
        }

        private boolean isValidEmail(String email) {

            String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                                   "[a-zA-Z0-9_+&*-]+)*@" +
                              "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";

            Pattern pat = Pattern.compile(emailRegex);

            if (email == null)
                return false;

            return pat.matcher(email).matches();
        }

        //Metodo controllo validità coppia email-password
         private boolean isValidPassword(String email,String password) throws SQLException {
             Connection db = DBConnector.getConnection();
             Statement st = db.createStatement();
             PreparedStatement ps = db.prepareStatement("SELECT * FROM utente WHERE email ILIKE ? AND password LIKE ?");
             ps.setString(1,email);
             ps.setString(2,password);
             ResultSet rs = st.executeQuery(ps.toString());

             if(rs.next())
                 return true;
             else
                 return false;
        }

}
