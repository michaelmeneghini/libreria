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

        private static String loggedAs=null;


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

            String emailReceived=login_email.getText();
            String passwordReceived= login_password.getText();

            if(!isValidPassword(emailReceived,passwordReceived)){
                login_error.setText("Email/Password insertite non valide!");
            }
            else {

                Parent registerFrameParent = null;
                //se è un responsabile apro la pagina da responsabile altrimenti apro la pagina da utente comune
                if (checkResponsabile(emailReceived)) {
                    registerFrameParent = FXMLLoader.load(getClass().getResource("../View/Responsabile.fxml"));
                    loggedAs=emailReceived;
                } else {
                    registerFrameParent = FXMLLoader.load(getClass().getResource("../View/UtenteRegistrato.fxml"));
                    loggedAs=emailReceived;
                }
                Scene registerFrame = new Scene(registerFrameParent);
                Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
                window.setScene(registerFrame);
                window.setMaximized(true);
                window.show();
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
            if(email.length() == 0 || password.length() == 0){
                return false;
            }
            Connection db = DBConnector.getConnection();
             Statement st = db.createStatement();
             PreparedStatement ps = db.prepareStatement("SELECT * FROM utente WHERE email ILIKE ? AND password LIKE ?");
             ps.setString(1,email);
             ps.setString(2,password);
             ResultSet rs = st.executeQuery(ps.toString());
             if(rs.next()) {
                 db.close();
                 st.close();
                 return true;
             }
             else{
                 db.close();
                 st.close();
                 return false;
             }
        }

        private boolean checkResponsabile(String email) throws SQLException {
            if(email.length() == 0)
                return false;
            Connection db = DBConnector.getConnection();
            Statement st = db.createStatement();
            PreparedStatement ps = db.prepareStatement("SELECT responsabile FROM utente WHERE email ILIKE ?");
            ps.setString(1, email);
            ResultSet rs = st.executeQuery(ps.toString());
            db.close();

            rs.next();
            return rs.getBoolean(1);

        }

        public static String getEmailLoggedas(){
            return loggedAs;
        }

}
