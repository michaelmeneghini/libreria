package Controller;

import Model.DBConnector;
import Model.Ordine;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ControllerVerificaOrdiniResp implements Initializable {

    @FXML
    private AnchorPane anchor_pane;

    @FXML
    private TableView<Ordine> table;

    @FXML
    private TableColumn<Ordine, Integer> codice;

    @FXML
    private TableColumn<Ordine, String> email;

    @FXML
    private TableColumn<Ordine, Date> data;

    @FXML
    private TableColumn<Ordine, Float> costo;

    @FXML
    private TableColumn<Ordine, String> stato;

    @FXML
    private TableColumn<Ordine, Integer> punti;

    @FXML
    private TableColumn<Ordine, String> nome;

    @FXML
    private TableColumn<Ordine, String> cognome;

    @FXML
    private TableColumn<Ordine, String> pagamento;

    public static ObservableList<Ordine> ordini;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        initTable();
        loadData();

    }

    private void initTable(){

        codice.setCellValueFactory(new PropertyValueFactory<>("Codice"));
        data.setCellValueFactory(new PropertyValueFactory<>("Data"));
        costo.setCellValueFactory(new PropertyValueFactory<>("Costo"));
        stato.setCellValueFactory(new PropertyValueFactory<>("Stato"));
        punti.setCellValueFactory(new PropertyValueFactory<>("Punti"));
        pagamento.setCellValueFactory(new PropertyValueFactory<>("Pagamento"));
        email.setCellValueFactory(new PropertyValueFactory<>("Email"));
        nome.setCellValueFactory(new PropertyValueFactory<>("nr_nome"));
        cognome.setCellValueFactory(new PropertyValueFactory<>("nr_cognome"));

    }

    private void loadData() {

        ordini = FXCollections.observableArrayList();

        Connection db = DBConnector.getConnection();
        try {
            Statement st = db.createStatement();
            ResultSet rs = st.executeQuery("SELECT id, data, prezzo, stato, punti, pagamento, email, nr_nome, nr_cognome FROM ordine ORDER by data DESC");

            while (rs.next()) {

                // se l'email è nulla
                if (rs.getString(7) != null) {
                    ordini.add(new Ordine(rs.getInt(1), rs.getString(7), rs.getFloat(3), rs.getString(6), rs.getInt(5), rs.getString(4), rs.getDate(2)));
                } else {
                    ordini.add(new Ordine(rs.getInt(1), rs.getFloat(3), rs.getString(6), rs.getString(8), rs.getString(9), rs.getString(4), rs.getDate(2)));
                }

            }
        } catch( SQLException e){
            e.printStackTrace();
        }

        table.setItems(ordini);

    }

}


