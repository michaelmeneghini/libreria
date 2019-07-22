package Model;

import javafx.scene.control.Button;

public class LibroTable {
    private String titolo,autore,descrizione,prezzo,punti;

    private Button addCart;

    public LibroTable(String titolo, String autore, String descrizione, String prezzo, String punti, Button addCart) {
        this.titolo = titolo;
        this.autore = autore;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.punti = punti;
        this.addCart = addCart;
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
}
