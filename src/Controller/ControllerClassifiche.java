package Controller;

import Model.DBConnector;
import Model.Libro;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ControllerClassifiche implements Initializable {

    @FXML
    private AnchorPane anchor_pane;

    @FXML
    private ComboBox combo_box;

    @FXML
    private TableView<Libro> table;

    @FXML
    private TableColumn<Libro, String> titolo;

    @FXML
    private TableColumn<Libro, String> autore;

    @FXML
    private TableColumn<Libro, String> copie_vendute;

    @FXML
    private TableColumn<Libro, String> descrizione;

    @FXML
    private TableColumn<Libro, String> prezzo;

    @FXML
    private TableColumn<Libro, String> punti;

    @FXML
    private TableColumn<Libro, String> ISBN;
    
    private ObservableList<Libro> chosen_genre = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        initCols();

        combo_box.getItems().add("Narrativa");
        combo_box.getItems().add("Fantasy");
        combo_box.getItems().add("Fantascienza");
        combo_box.getItems().add("Giallo");
        combo_box.getItems().add("Horror");
        combo_box.getItems().add("Rosa");
        combo_box.getItems().add("Avventura");
        combo_box.getItems().add("Biografia");
        combo_box.getItems().add("Filosofia");
        combo_box.getItems().add("Storia");

        combo_box.setOnAction(this::choiceHandler);

    }

    private void choiceHandler(Event event) {

        String choice = combo_box.getValue().toString().toLowerCase();
        loadData(choice);

    }

    private void initCols(){

        ISBN.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        titolo.setCellValueFactory(new PropertyValueFactory<>("titolo"));
        autore.setCellValueFactory(new PropertyValueFactory<>("autore"));
        prezzo.setCellValueFactory(new PropertyValueFactory<>("prezzo"));
        descrizione.setCellValueFactory(new PropertyValueFactory<>("descrizione"));
        copie_vendute.setCellValueFactory(new PropertyValueFactory<>("punti"));
        punti.setCellValueFactory(new PropertyValueFactory<>("copie_vendute"));

    }

    private void loadData(String choice) {

        chosen_genre.clear();
        Connection db = DBConnector.getConnection();

        try {
            Statement st = db.createStatement();
            PreparedStatement ps = db.prepareStatement("Select * from libro WHERE genere ILIKE ? ORDER BY copie_vendute DESC;");
            ps.setString(1, choice);
            ResultSet rs = st.executeQuery(ps.toString());
            while (rs.next()) {
                chosen_genre.add( new Libro(rs.getString(1), rs.getString(2), rs.getString(3), rs.getFloat(4),
                        rs.getString(5),  rs.getInt(6), rs.getInt(7), rs.getString(8)));
            }
            st.close();
            db.close();
        }
        catch(Exception e){e.printStackTrace();}

        table.setItems(chosen_genre);

    }

}
