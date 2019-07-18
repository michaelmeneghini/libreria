package Controller;

import Model.DBConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
        public void registerButtonClick(ActionEvent event) throws IOException {

            Parent root = FXMLLoader.load(getClass().getResource("../View/RegisterFrame.fxml"));
            Stage stage=new Stage();
            stage.setTitle("Register");
            stage.setScene(new Scene(root));
            stage.show();


            Stage oldstage = (Stage)((Node) event.getSource()).getScene().getWindow();
            oldstage.close();

        }

        @FXML
        public void loginButtonClick(ActionEvent event) throws IOException, SQLException {

            String emailRecieved=login_email.getText();
            
            if (!isValidEmail(emailRecieved)) {

                Alert errorAlert = new Alert(Alert.AlertType.WARNING);
                errorAlert.setHeaderText("Email inserita non valida!\nReinserire email");
                errorAlert.showAndWait();
            }


            String passwordRecieved= login_password.getText();

            if(!isValidPassword(emailRecieved,passwordRecieved)){
                Alert errorAlert = new Alert(Alert.AlertType.WARNING);
                errorAlert.setHeaderText("Password o Email sbagliate!\nReinserire i dati!");
                errorAlert.showAndWait();
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
