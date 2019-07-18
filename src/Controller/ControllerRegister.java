package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ControllerRegister {

    @FXML
    private TextField register_name;

    @FXML
    private TextField register_surname;

    @FXML
    private TextField register_addres;

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
    public void registerUser(ActionEvent event){

        //prendo le stringhe dei dati
        String name=register_name.getText();
        String surname=register_surname.getText();
        String addres=register_addres.getText();
        String city=register_city.getText();
        String cap=register_cap.getText();
        String phone_number=register_phone_number.getText();
        String email=register_email.getText();

        //controllo che le due password coincidano
        String password=null;
        if(register_password.getText().matches(register_confirm_password.getText()))
            password=register_password.getText();

        //TODO:Mettere i dati nel database


        //TODO:aprire la schermata di login
    }




}
