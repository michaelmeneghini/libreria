package Controller;

import Model.DBConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ControllerProfilo implements Initializable {

    @FXML
    private AnchorPane anchor_pane;

    @FXML
    private Label label_nome;

    @FXML
    private Label label_cognome;

    @FXML
    private Label label_indirizzo;

    @FXML
    private Label label_cap;

    @FXML
    private Label label_email;

    @FXML
    private Label label_città;

    @FXML
    private Label label_cellulare;

    @FXML
    private Label label_libroCard;

    private String email = ControllerLogin.getEmailLoggedas();

    @FXML
    public void modificaDatiButtonClicked(ActionEvent event){


    }

    public void initialize(URL location, ResourceBundle resources){
        int idCard = 0;
        try{
            Connection db = DBConnector.getConnection();
            Statement st = db.createStatement();
            PreparedStatement ps = db.prepareStatement("SELECT * FROM utente WHERE email ILIKE ? ");
            ps.setString(1, email);
            ResultSet rs = st.executeQuery(ps.toString());
            while(rs.next()){
                label_email.setText(email);
                label_nome.setText(rs.getString(2));
                label_cognome.setText(rs.getString(3));
                label_indirizzo.setText(rs.getString(4));
                label_cap.setText(rs.getString(5));
                label_cellulare.setText(rs.getString(6));
                label_città.setText(rs.getString(10));
                idCard = rs.getInt(8);
            }

            ps = db.prepareStatement("SELECT punti FROM libro_card where id = ?");
            ps.setInt(1,idCard);
            rs = st.executeQuery(ps.toString());
            while(rs.next()){
                label_libroCard.setText(String.valueOf(rs.getInt(1)));
            }
            db.close();
            st.close();
        }
        catch(Exception e){};
    }


}
