package Controller;

import Model.DBConnector;
import Model.Libro;
import Model.LibroTable;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.*;
import java.util.Random;
import java.util.ResourceBundle;

import static Controller.ControllerLibri.cart;


public class
ControllerCarrello implements Initializable {

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
    private  TableColumn<LibroTable,Button> col_delete;

    @FXML
    private Label saldoLabel;

    @FXML
    private Label puntiLabel;

    @FXML
    private Button aggiorna_carrello;

    @FXML
    private ComboBox pagamento;

    @FXML
    private JFXCheckBox check_indirizzo;

    @FXML
    private JFXTextField indirizzoField;

    @FXML
    private JFXTextField cittàField;

    @FXML
    private JFXTextField capField;

    private boolean paymentMethodSelected=false;

    private Float saldo = new Float(0);
    private int punti = 0;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        pagamento.getItems().add("Carta di credito");
        pagamento.getItems().add("Paypal");
        pagamento.getItems().add("Contrassegno");

        pagamento.setOnAction(this::paymentHandler);


        initTable();
        loadData();
    }

    private void paymentHandler(Event event) {
        paymentMethodSelected=true;
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
        puntiLabel.setText(String.valueOf(punti));

    }

    @FXML
    private void updateLabels(){
        loadData();
    }

    @FXML
    private void addressHandler(){

        if(check_indirizzo.isSelected() ){
            indirizzoField.setDisable(true);
            cittàField.setDisable(true);
            capField.setDisable(true);
        } else {
            indirizzoField.setDisable(false);
            cittàField.setDisable(false);
            capField.setDisable(false);
        }

    }

    @FXML
    private void placeOrder() throws SQLException {

        String paymentValue;

        if(!check_indirizzo.isSelected() && (indirizzoField.getText().isEmpty() ||  cittàField.getText().isEmpty() || capField.getText().isEmpty())){
            checkOutError.setText("Completare campi!");
            return;
        }

        try{
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
        if(check_indirizzo.isSelected()){
            //Indirizzo predefinito
            ps = db.prepareStatement("INSERT INTO public.ordine  (email, prezzo, pagamento, punti, stato, data)  VALUES (?, ?, ?, ?, ?, ?);");
            ps.setString(1,ControllerLogin.getEmailLoggedas());
            ps.setFloat(2,Float.parseFloat(saldoDB));
            ps.setString(3, paymentValue);
            ps.setInt(4,Integer.parseInt(puntiLabel.getText()));
            ps.setString(5, stato_ordine);
            ps.setDate(6, new Date(System.currentTimeMillis()));
        }
        else{
            //Check Indirizzo Field
            ps = db.prepareStatement("INSERT INTO public.ordine  (email, prezzo, pagamento, punti, indirizzo, cap, citta, stato, data)  VALUES(?,?, ?, ?, ?, ?, ?, ?, ?);");
            ps.setString(1,ControllerLogin.getEmailLoggedas());
            ps.setFloat(2,Float.parseFloat(saldoDB));
            ps.setString(3,paymentValue);
            ps.setInt(4,Integer.parseInt(puntiLabel.getText()));
            ps.setString(5, indirizzoField.getText());
            ps.setString(6, capField.getText());
            ps.setString(7, cittàField.getText());
            ps.setString(8, stato_ordine);
            ps.setDate(9, new Date(System.currentTimeMillis()));
        }
        Statement st = db.createStatement();
        int success = st.executeUpdate(ps.toString());
        if(success == 1){
            int id;
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

        //Aumento i punti della libro card dell'utente
        ps = db.prepareStatement("UPDATE libro_card SET punti = punti + ? WHERE id = ?");
        ps.setInt(1, Integer.parseInt(puntiLabel.getText()));

        PreparedStatement lc = db.prepareStatement("SELECT libro_card FROM utente WHERE email ILIKE ?");
        lc.setString(1, ControllerLogin.getEmailLoggedas());
        ResultSet resultSet = st.executeQuery(lc.toString());
        resultSet.next();
        lc.close();

        ps.setInt(2, resultSet.getInt(1));
        st.executeUpdate(ps.toString());

        //pulisco il carrello
        cart.clear();

        System.out.println("Ordine piazzato");
        saldo=0f;

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