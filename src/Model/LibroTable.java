package Model;

import Controller.*;
import javafx.scene.control.Button;

public class LibroTable {
    private String titolo,autore,descrizione,prezzo,punti,ISBN;

    private Button addCart;
    private Button delete;

    public LibroTable(String titolo, String autore, String descrizione, String prezzo, String punti, Button addCart, String ISBN) {
        this.titolo = titolo;
        this.autore = autore;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.punti = punti;
        this.addCart = addCart;
        this.ISBN = ISBN;

        addLibroCartOnEvent();
    }

    public LibroTable(String titolo, String autore, String descrizione, String prezzo, String punti, String ISBN, Button delete) {
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

    public String getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(String prezzo) {
        this.prezzo = prezzo;
    }

    public String getPunti() {
        return punti;
    }

    public void setPunti(String punti) {
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
                    System.out.println("Libro trovato: " + libro.getISBN());
                    ControllerLibri.cart.add(new LibroTable(libro.getTitolo(),libro.getAutore(),libro.getDescrizione(),libro.getPrezzo(),libro.getPunti(),libro.getISBN(),new Button("delete")));
                }
            }
        });
    }

    private void deleteLibroCartOnEvent() {
        delete.setOnAction(e->{
            for(LibroTable libro: ControllerLibri.libri){
                if(libro.getDelete() == delete) {
                    ControllerLibri.cart.remove(libro);
                }
            }
        });
    }

}
