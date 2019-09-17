package Controller;

import Model.DBConnector;
import Model.Libro_CardTable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ControllerVerificaLibroCardResp implements Initializable {

    @FXML
    private TableView<Libro_CardTable> table;

    @FXML
    private TableColumn<Libro_CardTable, String> codice;

    @FXML
    private TableColumn<Libro_CardTable, String> nome;

    @FXML
    private TableColumn<Libro_CardTable, String> cognome;

    @FXML
    private TableColumn<Libro_CardTable, String> email;

    @FXML
    private TableColumn<Libro_CardTable, String> punti;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        initCols();
        loadData();
        Connection db = DBConnector.getConnection();



        try {
            db.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void initCols() {

        codice.setCellValueFactory(new PropertyValueFactory<>("id"));
        nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        cognome.setCellValueFactory(new PropertyValueFactory<>("cognome"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        punti.setCellValueFactory(new PropertyValueFactory<>("punti"));

    }


    private void loadData(){

        Connection db = DBConnector.getConnection();
        try {
            Statement st = db.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM libro_card INNER JOIN utente ON libro_card.id = utente.libro_card ORDER BY punti DESC");
            while (rs.next()) {
                Libro_CardTable lc = new Libro_CardTable(rs.getInt(1), rs.getString(5), rs.getString(6), rs.getString(4), rs.getInt(3));
                table.getItems().add(lc);
            }

            db.close();
        }
        catch(Exception e){e.printStackTrace();}

    }


}
