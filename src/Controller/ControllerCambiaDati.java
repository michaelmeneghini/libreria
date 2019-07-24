package Controller;

import Model.DBConnector;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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
    void fattoButtonClicked(ActionEvent event) throws IOException {

        //controlli

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
        catch(Exception e){};
    }

}
