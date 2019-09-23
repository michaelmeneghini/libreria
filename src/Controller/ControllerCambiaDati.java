package Controller;

import Model.DBConnector;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ControllerCambiaDati implements Initializable {

    @FXML
    private AnchorPane anchor_pane;

    @FXML
    private JFXTextField mod_indirizzo;

    @FXML
    private JFXTextField mod_cap;

    @FXML
    private JFXTextField mod_nome;

    @FXML
    private JFXTextField mod_cognome;

    @FXML
    private JFXTextField mod_città;

    @FXML
    private JFXTextField mod_cellulare;

    private String email = ControllerLogin.getEmailLoggedas();

    @FXML
    void fattoButtonClicked(ActionEvent event) throws IOException, SQLException {
        String indirizzo=mod_indirizzo.getText();
        String cellulare=mod_cellulare.getText();
        String cap=mod_cap.getText();
        String nome=mod_nome.getText();
        String cognome=mod_cognome.getText();
        String città=mod_città.getText();

        if(indirizzo.isEmpty() || cellulare.isEmpty() || cap.isEmpty() || nome.isEmpty() || cognome.isEmpty() || città.isEmpty()) {
            Alert errorAlert = new Alert(Alert.AlertType.WARNING);
            errorAlert.setHeaderText("Riempire tutti i campi!");
            errorAlert.showAndWait();
            return;
        }

        //Aggiorno i dati nel Database
        Connection db = DBConnector.getConnection();
        PreparedStatement ps = db.prepareStatement("UPDATE utente SET nome = ?, cognome = ?, indirizzo = ?, cap = ?, telefono = ?, città = ? WHERE email = ?");
        ps.setString(1, nome);
        ps.setString(2, cognome);
        ps.setString(3, indirizzo);
        ps.setString(4, cap);
        ps.setString(5, cellulare);
        ps.setString(6, città);
        ps.setString(7, email);

        Statement st = db.createStatement();
        st.executeUpdate(ps.toString());


        ps.close();
        st.close();
        db.close();

        //Ritorno alla schermata di profilo
        Parent root = FXMLLoader.load(getClass().getResource("../View/Profilo.fxml"));
        anchor_pane.getChildren().setAll(root);

    }

    public void initialize(URL location, ResourceBundle resources){
        try{
            Connection db = DBConnector.getConnection();
            Statement st = db.createStatement();
            PreparedStatement ps = db.prepareStatement("SELECT * FROM utente WHERE email ILIKE ? ");
            ps.setString(1, email);
            ResultSet rs = st.executeQuery(ps.toString());
            while(rs.next()){
                mod_nome.setText(rs.getString(2));
                mod_cognome.setText(rs.getString(3));
                mod_indirizzo.setText(rs.getString(4));
                mod_cap.setText(rs.getString(5));
                mod_cellulare.setText(rs.getString(6));
                mod_città.setText(rs.getString(10));
            }
            db.close();
            st.close();
        }
        catch(Exception e){}
    }

}
