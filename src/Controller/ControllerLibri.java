package Controller;

import Model.DBConnector;
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
import java.util.ResourceBundle;

public class ControllerLibri implements Initializable {

    @FXML
    private AnchorPane anchor_pane;

    @FXML
    private TableView<LibroTable> table_libri;

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

    public void initialize(URL location, ResourceBundle resources) {
        initTable();
        loadData();
    }

    private void initTable(){
        initCols();
    }

    private void initCols(){

        col_titolo.setCellValueFactory(new PropertyValueFactory<>("Titolo"));
        col_autore.setCellValueFactory(new PropertyValueFactory<>("Autore"));
        col_prezzo.setCellValueFactory(new PropertyValueFactory<>("Prezzo"));
        col_desc.setCellValueFactory(new PropertyValueFactory<>("Descrizione"));
        col_punti.setCellValueFactory(new PropertyValueFactory<>("Punti"));
        col_button.setCellValueFactory(new PropertyValueFactory<>("addCart"));

        editableCols();
    }

    private void editableCols(){
        col_titolo.setCellFactory(TextFieldTableCell.forTableColumn());

        col_titolo.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setTitolo(e.getNewValue());
        });

        col_autore.setCellFactory(TextFieldTableCell.forTableColumn());

        col_autore.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setAutore(e.getNewValue());
        });

        col_prezzo.setCellFactory(TextFieldTableCell.forTableColumn());

        col_prezzo.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setPrezzo(e.getNewValue());
        });

        col_desc.setCellFactory(TextFieldTableCell.forTableColumn());

        col_desc.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setDescrizione(e.getNewValue());
        });

        col_punti.setCellFactory(TextFieldTableCell.forTableColumn());

        col_punti.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setPunti(e.getNewValue());
        });

        //table_libri.setEditable(true);
    }

    private void loadData(){
        ObservableList<LibroTable> libri = FXCollections.observableArrayList();

        Connection db = DBConnector.getConnection();
        try {
            Statement st = db.createStatement();
            ResultSet rs = st.executeQuery("Select titolo,autore,prezzo,descrizione,punti from libro;");
            while (rs.next()) {
                libri.add(new LibroTable(rs.getString(1), rs.getString(2), String.valueOf(rs.getFloat(3)), rs.getString(4), String.valueOf(rs.getString(5)), new Button("AddToCart")));
            }
            db.close();
            st.close();
        }
        catch(Exception e){e.printStackTrace();}
        table_libri.setItems(libri);
    }
}
