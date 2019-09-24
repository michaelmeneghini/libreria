package Controller;

import Model.DBConnector;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ControllerVerificaOrdiniNR{

    @FXML
    private AnchorPane anchor_pane;

    @FXML
    private JFXTextField id_ordine;

    @FXML
    private Button cerca;

    @FXML
    private Label errore;

    @FXML
    private Label data;

    @FXML
    private Label data_content;

    @FXML
    private Label nome;

    @FXML
    private Label nome_content;

    @FXML
    private Label cognome;

    @FXML
    private Label cognome_content;

    @FXML
    private Label costo;

    @FXML
    private Label costo_content;

    @FXML
    private Label stato;

    @FXML
    private Label stato_content;


    @FXML
    public void searchOrder() throws SQLException {

        initialize();

        try{
            Integer.parseInt(id_ordine.getText());
        }
        catch(NumberFormatException e){
            errore.setVisible(true);
            return;
        }

        if( !id_ordine.getText().equals("")) {

            //cerco l'ordine nel database
            Connection db = DBConnector.getConnection();
            PreparedStatement ps = db.prepareStatement("SELECT email, data, nr_nome, nr_cognome, prezzo, stato FROM ordine WHERE id = ?");
            ps.setInt(1, Integer.parseInt(id_ordine.getText()));


            ResultSet rs = ps.executeQuery();
            if(rs.next()){

                //se la mail esiste è di un utente registrato
                if(!(rs.getString(1) == null)){
                    errore.setVisible(true);
                    return;
                }

                errore.setVisible(false);
                data_content.setText( rs.getDate(2).toString() );
                nome_content.setText( rs.getString(3) );
                cognome_content.setText( rs.getString(4) );
                costo_content.setText( String.valueOf(rs.getFloat(5)) );
                stato_content.setText( rs.getString(6) );

                ps.close();
                rs.close();
                db.close();

            } else {
                //se non trovo risultati
                errore.setVisible(true);
                return;
            }

            data.setVisible(true);
            data_content.setVisible(true);
            nome.setVisible(true);
            nome_content.setVisible(true);
            cognome.setVisible(true);
            cognome_content.setVisible(true);
            costo.setVisible(true);
            costo_content.setVisible(true);
            stato.setVisible(true);
            stato_content.setVisible(true);

        } else {

            errore.setVisible(true);

        }



    }

    private void initialize() {

        data.setVisible(false);
        data_content.setVisible(false);
        nome.setVisible(false);
        nome_content.setVisible(false);
        cognome.setVisible(false);
        cognome_content.setVisible(false);
        costo.setVisible(false);
        costo_content.setVisible(false);
        stato.setVisible(false);
        stato_content.setVisible(false);
        errore.setVisible(false);

    }


}
