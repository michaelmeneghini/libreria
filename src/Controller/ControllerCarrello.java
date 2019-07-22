package Controller;

import Model.DBConnector;
import Model.Libro;
import Model.LibroTable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.postgresql.jdbc.EscapedFunctions2;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import static Controller.ControllerLibri.cart;


public class ControllerCarrello implements Initializable {

    @FXML
    private AnchorPane anchor_pane;

    @FXML
    private TableView<LibroTable> table_cart;

    @FXML
    private TableColumn<Libro, String> col_titolo;

    @FXML
    private TableColumn<Libro, String> col_autore;

    @FXML
    private TableColumn<Libro, String> col_prezzo;

    @FXML
    private TableColumn<Libro, String> col_punti;

    @FXML
    private  TableColumn<LibroTable,Button> col_delete;

    @FXML
    private Label saldoLabel;

    @FXML
    private Label puntiLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTable();
        loadData();
    }

    private void initTable() {initCols();}

    private void initCols() {

        col_titolo.setCellValueFactory(new PropertyValueFactory<>("Titolo"));
        col_autore.setCellValueFactory(new PropertyValueFactory<>("Autore"));
        col_prezzo.setCellValueFactory(new PropertyValueFactory<>("Descrizione"));
        col_punti.setCellValueFactory(new PropertyValueFactory<>("Punti"));
        col_delete.setCellValueFactory(new PropertyValueFactory<>("Delete"));

    }


    private void loadData(){
        float saldo = 0;
        int punti = 0;
        try {
            Connection db = DBConnector.getConnection();
            PreparedStatement ps = db.prepareStatement("SELECT prezzo,punti FROM libro WHERE \"ISBN\" ILIKE ?;");
            Statement st = db.createStatement();
            for (LibroTable libro: cart){
                ps.setString(1,libro.getISBN());
                ResultSet rs = st.executeQuery(ps.toString());
                while(rs.next()) {
                    saldo += rs.getFloat(1);
                    punti += rs.getInt(2);
                }
            }
            db.close();
            st.close();
        }
        catch(Exception e){
            System.out.println(e.toString());
        }

        saldoLabel.setText(String.valueOf(saldo));
        puntiLabel.setText(String.valueOf(punti));

        table_cart.setItems(cart);
    }
}