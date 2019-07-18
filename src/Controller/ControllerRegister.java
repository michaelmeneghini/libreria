package Controller;

import Model.DBConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.*;
import java.util.regex.Pattern;

import static java.lang.Integer.*;
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
    public void registerUser(ActionEvent event) throws SQLException{

        //prendo le stringhe dei dati
        String name = register_name.getText();
        String surname = register_surname.getText();
        String address = register_address.getText();
        String city = register_city.getText();
        int cap = parseInt(register_cap.getText());
        String phoneNumber = register_phone_number.getText();

        String email=null;
        if(isValidEmail(register_email.getText()))
            email=register_email.getText();

        String password=null;
        if(register_password.getText().matches(register_confirm_password.getText()))
            password=register_password.getText();


        Connection db = DBConnector.getConnection();
        Statement st = db.createStatement();
        ResultSet rs1 = null;
        int libroCardId = 0;
        int rs = st.executeUpdate("INSERT INTO libro_card (punti) VALUES(0);");
        if(rs != 0){
            rs1 = st.executeQuery("SELECT id FROM libro_card ORDER BY id DESC LIMIT 1 ;");
            while (rs1.next()){
                libroCardId = rs1.getInt(1);
            }
        }
        else
            System.out.println("Nada");


        PreparedStatement ps = db.prepareStatement("INSERT INTO public.utente (email, nome, cognome, indirizzo, cap, telefono, \"password\", libro_card, responsabile, città) VALUES(?, ?, ?, ?, ?, ?, ?, ?, false, ?);");
        ps.setString(1,email);
        ps.setString(2,name);
        ps.setString(3,surname);
        ps.setString(4,address);
        ps.setString(7,password);
        ps.setString(9,city);
        ps.setInt(5,cap);
        ps.setString(6,phoneNumber);
        ps.setInt(8,libroCardId);

        rs = st.executeUpdate(ps.toString());

        if(rs != 0){
            System.out.println("Utente registrato correttamente");
        }
        else{
            System.out.println("Nada");
        }

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
