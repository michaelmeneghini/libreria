package Controller;

import Model.DBConnector;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ControllerAggiungiLibro {

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
    public void addButtonClick() throws SQLException, Exception {
        String isbn = isbnField.getText();
        String titolo = titoloField.getText();
        String autore = autoreField.getText();
        String prezzo = prezzoField.getText();
        String descrizione = descrizioneField.getText();
        String copieVendute = copieVenduteField.getText();
        String punti = puntiField.getText();

        if(isbn.length() > 0 && titolo.length() > 0 && autore.length() > 0 && prezzo.length()> 0 && descrizione.length() > 0 && copieVendute.length() > 0 && punti.length() > 0) {
            Connection db = DBConnector.getConnection();
            Statement st = db.createStatement();
            PreparedStatement ps = db.prepareStatement("INSERT INTO public.libro (\"ISBN\", titolo, autore, prezzo, descrizione, copie_vendute, punti) VALUES(?, ?, ?, ?, ?, ?, ?);");
            ps.setString(1, isbn);
            ps.setString(2, titolo);
            ps.setString(3, autore);
            ps.setFloat(4, Float.parseFloat(prezzo));
            ps.setString(5, descrizione);
            ps.setInt(6, Integer.parseInt(copieVendute));
            ps.setInt(7, Integer.parseInt(punti));

            int result = st.executeUpdate(ps.toString());

            if (result != 0)
                System.out.println("Libro inserito correttamente");
            else
                System.out.println("Nada");
        }
        else{
            System.out.println("Inserire tutto correttamente");
        }

    }




}
