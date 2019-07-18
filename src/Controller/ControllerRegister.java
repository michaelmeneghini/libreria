package Controller;

import Model.DBConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.*;
import java.util.regex.Pattern;

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
    public void registerUser(ActionEvent event) throws SQLException{

        //prendo le stringhe dei dati
        String name=register_name.getText();
        String surname=register_surname.getText();
        String address=register_address.getText();
        String city=register_city.getText();
        int cap=Integer.parseInt(register_cap.getText());
        int phoneNumber=Integer.parseInt(register_phone_number.getText());

        String email=null;
        if(isValidEmail(register_email.getText()))
            email=register_email.getText();

        String password=null;
        if(register_password.getText().matches(register_confirm_password.getText()))
            password=register_password.getText();

        //Da provare non riesco ad andare più avanti della registrazione !!!!!!!!!!!!!
        /*
        Connection db = DBConnector.getConnection();
        Statement st = db.createStatement();
        ResultSet rs1 = null;
        ResultSet rs = st.executeQuery("INSERT INTO public.libro_card (data_emissione, punti) VALUES(CURRENT_DATE, 0);");
        if(rs.next()){
            rs1 = st.executeQuery("SELECT id FROM libro_card ORDER BY id DESC LIMIT 1 ;");
        }
        else
            System.out.println("Nada");

        int libroCardId = rs1.getInt(1);

        PreparedStatement ps = db.prepareStatement("INSERT INTO public.utente (email, nome, cognome, indirizzo, cap, telefono, \"password\", libro_card, responsabile, città) VALUES(?, ?, ?, ?, ?, ?, ?, ?, false, ?);");
        ps.setString(1,email);
        ps.setString(2,name);
        ps.setString(3,surname);
        ps.setString(4,address);
        ps.setString(7,password);
        ps.setString(9,city);
        ps.setInt(5,cap);
        ps.setInt(6,phoneNumber);
        ps.setInt(8,libroCardId);

        rs = st.executeQuery(ps.toString());

        if(rs.next()){
            System.out.println("Utente registrato correttamente");
        }
        else{
            System.out.println("Nada");
        }


         */

        //TODO:aprire la schermata principale
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




}
