package Controller;

import Model.DBConnector;
import Model.Libro;
import Model.LibroTable;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.*;
import java.util.Random;
import java.util.ResourceBundle;

import static Controller.ControllerLibri.cart;

public class ControllerCarrelloNR implements Initializable {

    @FXML
    private Label checkOutError;

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
    private  TableColumn<LibroTable, Button> col_delete;

    @FXML
    private Label saldoLabel;

    @FXML
    private Button aggiorna_carrello;

    @FXML
    private ComboBox pagamento;

    @FXML
    private Label codice_ordine;

    @FXML
    private JFXTextField indirizzoField;

    @FXML
    private JFXTextField cittàField;

    @FXML
    private JFXTextField capField;

    private Float saldo = new Float(0);
    private int punti = 0;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //combobox
        pagamento.getItems().add("Carta di credito");
        pagamento.getItems().add("Paypal");
        pagamento.getItems().add("Contrassegno");




        initTable();
        loadData();
    }

    private void initTable() {initCols();}

    private void initCols() {

        col_titolo.setCellValueFactory(new PropertyValueFactory<>("Titolo"));
        col_autore.setCellValueFactory(new PropertyValueFactory<>("Autore"));
        col_prezzo.setCellValueFactory(new PropertyValueFactory<>("Prezzo"));
        col_punti.setCellValueFactory(new PropertyValueFactory<>("Punti"));
        col_delete.setCellValueFactory(new PropertyValueFactory<>("Delete"));

    }


    private void loadData(){

        table_cart.setItems(cart);

        codice_ordine.setVisible(false);

        saldo = 0f;
        punti = 0;

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

        saldoLabel.setText(String.format("%5.2f",saldo));

    }

    @FXML
    private void updateLabels(){
        loadData();
    }

    @FXML
    private void placeOrder() throws SQLException {

        String paymentValue;

        try{

            if(cittàField.getText().isEmpty() || capField.getText().isEmpty() || indirizzoField.getText().isEmpty()) {
                checkOutError.setText("Completare campi!");
                return;
            }
            paymentValue=pagamento.getValue().toString();
            if(saldo==0f){
                checkOutError.setText("Ordine vuoto!");
                return;
            }
        }
        catch (NullPointerException e){
            checkOutError.setText("Inserire pagamento!");
            return;
        }

        checkOutError.setText("");

        //Aggiorno il carrello nel caso non venisse fatto prima di effettuare l'ordine
        loadData();

        Connection db = DBConnector.getConnection();
        Random r = new Random();
        String stato_ordine = generateStatus( r.nextInt(3));
        PreparedStatement ps;
        String saldoDB = saldoLabel.getText().replace(',','.');
        //Check Indirizzo Field
        ps = db.prepareStatement("INSERT INTO public.ordine  (prezzo, pagamento, indirizzo, cap, citta, stato, nr_nome, nr_cognome, data)  VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);");
        ps.setFloat(1,Float.parseFloat(saldoDB));
        ps.setString(2,paymentValue);
        ps.setString(3, indirizzoField.getText());
        ps.setString(4, capField.getText());
        ps.setString(5, cittàField.getText());
        ps.setString(6, stato_ordine);
        ps.setString(7, ControllerLogin.getNomeNR());
        ps.setString(8, ControllerLogin.getCognomeNR());
        ps.setDate(9, new Date(System.currentTimeMillis()));

        int id = 0;
        Statement st = db.createStatement();
        int success = st.executeUpdate(ps.toString());
        if(success == 1){

            ResultSet rs = st.executeQuery("SELECT id FROM ordine ORDER BY id DESC LIMIT 1 ;");
            rs.next();
            id = rs.getInt(1);
            //aggiunta dei libri in tabella
            for(LibroTable l: ControllerLibri.cart){
                PreparedStatement pss = db.prepareStatement("INSERT INTO public.ordine_libro (\"ISBN\",id) VALUES(?,?);");
                pss.setString(1,l.getISBN());
                pss.setInt(2,id);
                st.executeUpdate(pss.toString());
                pss.close();

                //Aumento le copie vendute dei libri acquistati
                ps = db.prepareStatement("UPDATE libro SET copie_vendute = copie_vendute+1 WHERE \"ISBN\" ILIKE ?");
                ps.setString(1, l.getISBN());
                st.executeUpdate(ps.toString());

            }
        }

        //pulisco il carrello
        cart.clear();

        codice_ordine.setText(codice_ordine.getText() + id);
        codice_ordine.setVisible(true);

        System.out.println("Ordine piazzato");

        st.close();
        db.close();
        ps.close();


    }

    private String generateStatus(int stato) {

        String res = "";
        switch ( stato ){
            case 0:
                res = "In elaborazione";
                break;
            case 1:
                res = "In transito";
                break;
            case 2:
                res = "Consegnato";
                break;

        }
        return res;
    }

}
