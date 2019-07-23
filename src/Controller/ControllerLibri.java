package Controller;

import Model.DBConnector;
import Model.Libro;
import Model.LibroTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class ControllerLibri implements Initializable {

    @FXML
    private AnchorPane anchor_pane;

    @FXML
    private TableView<LibroTable> table_libri;

    @FXML
    private TableColumn<LibroTable, String> col_ISBN;

    @FXML
    private TableColumn<LibroTable, String> col_titolo;

    @FXML
    private TableColumn<LibroTable, String> col_autore;

    @FXML
    private TableColumn<LibroTable, String> col_prezzo;

    @FXML
    private TableColumn<LibroTable, String> col_desc;

    @FXML
    private TableColumn<LibroTable, String> col_punti;

    @FXML
    private TableColumn<LibroTable, Button> col_button;

    public static ObservableList<LibroTable> libri;

    public static ObservableList<LibroTable> cart = FXCollections.observableArrayList();

    public void initialize(URL location, ResourceBundle resources) {
        initTable();
        loadData();
    }

    private void initTable(){
        initCols();
    }

    private void initCols(){

        col_ISBN.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        col_titolo.setCellValueFactory(new PropertyValueFactory<>("titolo"));
        col_autore.setCellValueFactory(new PropertyValueFactory<>("autore"));
        col_prezzo.setCellValueFactory(new PropertyValueFactory<>("prezzo"));
        col_desc.setCellValueFactory(new PropertyValueFactory<>("descrizione"));
        col_punti.setCellValueFactory(new PropertyValueFactory<>("punti"));
        col_button.setCellValueFactory(new PropertyValueFactory<>("addCart"));

    }

    private void loadData(){
        libri = FXCollections.observableArrayList();


        Connection db = DBConnector.getConnection();
        try {
            Statement st = db.createStatement();
            ResultSet rs = st.executeQuery("Select * FROM libro;");
            while (rs.next()) {
                libri.add(new LibroTable(rs.getString("titolo"),rs.getString("autore"),rs.getString("descrizione"),rs.getFloat("prezzo"),rs.getInt("punti"),new Button("Ordina"),rs.getString(1)));
            }
            st.close();
            db.close();
        }
        catch(Exception e){e.printStackTrace();}
        table_libri.setItems(libri);
    }
}

