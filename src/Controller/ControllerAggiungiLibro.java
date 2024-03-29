package Controller;

import Model.DBConnector;
import Model.Libro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ControllerAggiungiLibro implements Initializable {

    @FXML
    private TextField isbnField;

    @FXML
    private TextField titoloField;

    @FXML
    private TextField autoreField;

    @FXML
    private TextField prezzoField;

    @FXML
    private TextField descrizioneField;

    @FXML
    private TextField copieVenduteField;

    @FXML
    private TextField puntiField;

    @FXML
    private TextField genereField;

    @FXML
    private TableView<Libro> table;

    @FXML
    private TableColumn<Libro, String> ISBN;

    @FXML
    private TableColumn<Libro, String> titolo;

    @FXML
    private TableColumn<Libro, String> autore;

    @FXML
    private TableColumn<Libro, String> prezzo;

    @FXML
    private TableColumn<Libro, String> descrizione;

    @FXML
    private TableColumn<Libro, String> copie_vendute;

    @FXML
    private TableColumn<Libro, String> punti;

    @FXML
    private TableColumn<Libro, String> genere;

    private ObservableList<Libro> libri = FXCollections.observableArrayList();

    @FXML
    public void addButtonClick() throws Exception {
        String isbn = isbnField.getText();
        String titolo = titoloField.getText();
        String autore = autoreField.getText();
        String prezzo = prezzoField.getText();
        String descrizione = descrizioneField.getText();
        String copieVendute = copieVenduteField.getText();
        String punti = puntiField.getText();
        String genere = genereField.getText();

        Float prezzoNumber;
        Integer copieVenduteNumber;
        Integer puntiNumber;
        try{
            prezzoNumber=Float.parseFloat(prezzo);
            copieVenduteNumber=Integer.parseInt(copieVendute);
            puntiNumber=Integer.parseInt(punti);
        }
        catch(NumberFormatException e){
            prezzoNumber=0f;
            copieVenduteNumber=0;
            puntiNumber=0;
        }

        if(isbn.length() > 0 && titolo.length() > 0 && autore.length() > 0 && prezzoNumber > 0f  && descrizione.length() > 0 && copieVenduteNumber>=0 && puntiNumber>= 0) {
            Connection db = DBConnector.getConnection();
            Statement st = db.createStatement();
            PreparedStatement ps = db.prepareStatement("INSERT INTO public.libro (\"ISBN\", titolo, autore, prezzo, descrizione, copie_vendute, punti, genere) VALUES(?, ?, ?, ?, ?, ?, ?, ?);");
            ps.setString(1, isbn);
            ps.setString(2, titolo);
            ps.setString(3, autore);
            ps.setFloat(4, prezzoNumber);
            ps.setString(5, descrizione);
            ps.setInt(6, copieVenduteNumber);
            ps.setInt(7, puntiNumber);
            ps.setString(8, genere);

            int result = st.executeUpdate(ps.toString());

            if (result != 0)
                System.out.println("Libro inserito correttamente");
            else
                System.out.println("Nada");

            db.close();
            st.close();
        }
        else{
            Alert errorAlert = new Alert(Alert.AlertType.WARNING);
            errorAlert.setHeaderText("Ci sono dati mancanti e/o errati!");
            errorAlert.showAndWait();
        }

        loadData();

        isbnField.setText("");
        titoloField.setText("");
        autoreField.setText("");
        prezzoField.setText("");
        descrizioneField.setText("");
        copieVenduteField.setText("");
        puntiField.setText("");
        genereField.setText("");

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        initCols();
        loadData();

    }

    private void initCols(){

        ISBN.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        titolo.setCellValueFactory(new PropertyValueFactory<>("titolo"));
        autore.setCellValueFactory(new PropertyValueFactory<>("autore"));
        prezzo.setCellValueFactory(new PropertyValueFactory<>("prezzo"));
        descrizione.setCellValueFactory(new PropertyValueFactory<>("descrizione"));
        copie_vendute.setCellValueFactory(new PropertyValueFactory<>("copie_vendute"));
        punti.setCellValueFactory(new PropertyValueFactory<>("punti"));
        //genere.setCellValueFactory(new PropertyValueFactory<>("genere"));

    }

    private void loadData() {

        libri.clear();
        Connection db = DBConnector.getConnection();
        try {
            Statement st = db.createStatement();
            ResultSet rs = st.executeQuery("Select * from libro;");
            while (rs.next()) {
                libri.add( new Libro(rs.getString(1), rs.getString(2), rs.getString(3), rs.getFloat(4),
                        rs.getString(5),  rs.getInt(6), rs.getInt(7), rs.getString(8)));
            }
            st.close();
            db.close();
        }
        catch(Exception e){e.printStackTrace();}
        table.setItems(libri);
    }
}
