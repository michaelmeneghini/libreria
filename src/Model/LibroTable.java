package Model;

import Controller.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;

public class LibroTable {
    private String titolo,autore,descrizione,ISBN;
    private int punti;
    private float prezzo;

    private Button addCart;
    private Button delete;

    public LibroTable(String titolo, String autore, String descrizione, float prezzo, int punti, Button addCart, String ISBN) {
        this.titolo = titolo;
        this.autore = autore;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.punti = punti;
        this.addCart = addCart;
        this.ISBN = ISBN;

        addLibroCartOnEvent();
    }

    public LibroTable(String titolo, String autore, String descrizione, float prezzo, int punti, String ISBN, Button delete) {
        this.titolo = titolo;
        this.autore = autore;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.punti = punti;
        this.ISBN = ISBN;
        this.delete = delete;

        deleteLibroCartOnEvent();
    }

    public LibroTable() {
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }

    public int getPunti() {
        return punti;
    }

    public void setPunti(int punti) {
        this.punti = punti;
    }

    public Button getAddCart() {
        return addCart;
    }

    public void setAddCart(Button addCart) {
        this.addCart = addCart;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public Button getDelete() {
        return delete;
    }

    public void setDelete(Button delete) {
        this.delete = delete;
    }

    public void addLibroCartOnEvent(){
        addCart.setOnAction(e->{
            for(LibroTable libro: ControllerLibri.libri){
                if(libro.getAddCart() == addCart) {
                    ControllerLibri.cart.add(new LibroTable(libro.getTitolo(),libro.getAutore(),libro.getDescrizione(),libro.getPrezzo(),libro.getPunti(),libro.getISBN(),new Button("delete")));
                }
            }
        });
    }

    private void deleteLibroCartOnEvent() {
        delete.setOnAction(e->{
            for(LibroTable libro: ControllerLibri.cart){
                if(libro.getDelete() == delete) {
                    ControllerLibri.cart.remove(libro);
                    break;
                }
            }
        });
    }

}
