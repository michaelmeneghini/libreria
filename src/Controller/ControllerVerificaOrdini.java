package Controller;

import Model.DBConnector;
import Model.Ordine;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ControllerVerificaOrdini implements Initializable {

    @FXML
    private AnchorPane anchor_pane;

    @FXML
    private TableView<Ordine> table;

    @FXML
    private TableColumn<Ordine, Integer> codice;

    @FXML
    private TableColumn<Ordine, Date> data;

    @FXML
    private TableColumn<Ordine, String> stato;

    @FXML
    private TableColumn<Ordine, Integer> punti;

    @FXML
    private TableColumn<Ordine, Float> prezzo;

    public static ObservableList<Ordine> ordini;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        initTable();
        loadData();

    }

    private void initTable() {

        codice.setCellValueFactory(new PropertyValueFactory<>("Codice"));
        data.setCellValueFactory(new PropertyValueFactory<>("Data"));
        stato.setCellValueFactory(new PropertyValueFactory<>("Stato"));
        punti.setCellValueFactory(new PropertyValueFactory<>("Punti"));
        prezzo.setCellValueFactory(new PropertyValueFactory<>("Costo"));

    }

    private void loadData() {

        ordini = FXCollections.observableArrayList();

        Connection db = DBConnector.getConnection();
        try {
            PreparedStatement ps = db.prepareStatement("Select id, data, stato, punti, prezzo FROM ordine WHERE email = ? ORDER BY data DESC;");
            ps.setString(1, ControllerLogin.getEmailLoggedas());
            Statement st = db.createStatement();
            ResultSet rs = st.executeQuery(ps.toString());

            while (rs.next()) {
                ordini.add(new Ordine(rs.getInt("id"),rs.getDate("data"),rs.getString("stato"),rs.getInt("punti"),rs.getFloat("prezzo")));
            }

            rs.close();
            ps.close();
            st.close();
            db.close();
        }
        catch(Exception e){e.printStackTrace();}
        table.setItems(ordini);


    }


}
