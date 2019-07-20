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

import java.sql.*;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

public class ControllerRegister  {

    @FXML
    private TextField register_name;

    @FXML
    private TextField register_surname;

    @FXML
    private TextField register_address;

    @FXML
    private TextField register_city;

    @FXML
    private TextField register_cap;

    @FXML
    private TextField register_phone_number;

    @FXML
    private TextField register_email;

    @FXML
    private PasswordField register_password;

    @FXML
    private PasswordField register_confirm_password;

    @FXML
    public void registerUser(ActionEvent event) throws SQLException, Exception{

        String name = register_name.getText();
        String surname = register_surname.getText();
        String address = register_address.getText();
        String city = register_city.getText();
        String cap = register_cap.getText();
        String phoneNumber = register_phone_number.getText();

        if(name.length() == 0 || surname.length() == 0 || address.length() == 0 || city.length() == 0 || cap.length() == 0 || phoneNumber.length() == 0){
            Alert errorAlert = new Alert(Alert.AlertType.WARNING);
            errorAlert.setHeaderText("Alcuni dati non sono stati inserti\n Si prega di porcedere con l'inserimento!");
            errorAlert.showAndWait();
        }
        else {

            String email = null;
            if (isValidEmail(register_email.getText())) {
                email = register_email.getText();
                if (isEmailTaken(email)){
                    //TODO: cambia la label
                }
            } else {
                Alert errorAlert = new Alert(Alert.AlertType.WARNING);
                errorAlert.setHeaderText("Password o Email non inserite!\nInserire i dati!");
                errorAlert.showAndWait();
            }
            String password = null;
            if (register_password.getText().matches(register_confirm_password.getText()))
                password = register_password.getText();
            else {
                Alert errorAlert = new Alert(Alert.AlertType.WARNING);
                errorAlert.setHeaderText("Password o Email non inserite!\nInserire i dati!");
                errorAlert.showAndWait();
            }

            if(email != null && password != null) {

                Connection db = DBConnector.getConnection();
                Statement st = db.createStatement();
                ResultSet rs1 = null;
                int libroCardId = 0;
                int rs = st.executeUpdate("INSERT INTO libro_card (punti) VALUES(0);");
                if (rs != 0) {
                    rs1 = st.executeQuery("SELECT id FROM libro_card ORDER BY id DESC LIMIT 1 ;");
                    while (rs1.next()) {
                        libroCardId = rs1.getInt(1);
                    }
                } else
                    System.out.println("Nada");


                PreparedStatement ps = db.prepareStatement("INSERT INTO public.utente (email, nome, cognome, indirizzo, cap, telefono, \"password\", libro_card, responsabile, città) VALUES(?, ?, ?, ?, ?, ?, ?, ?, false, ?);");
                ps.setString(1, email);
                ps.setString(2, name);
                ps.setString(3, surname);
                ps.setString(4, address);
                ps.setString(7, password);
                ps.setString(9, city);
                ps.setString(5, cap);
                ps.setString(6, phoneNumber);
                ps.setInt(8, libroCardId);

                rs = st.executeUpdate(ps.toString());

                if (rs != 0) {
                    System.out.println("Utente registrato correttamente");
                } else {
                    System.out.println("Nada");
                }

                Parent root = FXMLLoader.load(getClass().getResource("../View/LoginFrame.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Login");
                stage.setScene(new Scene(root));
                stage.show();


                Stage oldstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                oldstage.close();
            }

        }
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

    private boolean isEmailTaken(String email) throws SQLException {

        Connection db = DBConnector.getConnection();
        Statement st = db.createStatement();
        ResultSet rs = st.executeQuery("SELECT email FROM utente");
        while(rs.next()){
            if( email.equals(rs.getString(1))){
                return true;
            }
        }
        db.close();
        return false;

    }


}
