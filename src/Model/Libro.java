package Model;

public class Libro {
    int ISBN;
    String titolo;
    String autore;
    int prezzo;
    String descrizione;
    int copie_vendute;
    int punti;

    public Libro(int ISBN, String titolo, String autore, int prezzo, String descrizione, int punti) {
        this.ISBN = ISBN;
        this.titolo = titolo;
        this.autore = autore;
        this.prezzo = prezzo;
        this.descrizione = descrizione;
        this.copie_vendute = 0;
        this.punti = punti;
    }

    public Libro(int ISBN, String titolo, String autore, int prezzo, String descrizione, int copie_vendute, int punti) {
        this.ISBN = ISBN;
        this.titolo = titolo;
        this.autore = autore;
        this.prezzo = prezzo;
        this.descrizione = descrizione;
        this.copie_vendute = copie_vendute;
        this.punti = punti;
    }

    public int getISBN() {
        return ISBN;
    }

    public String getTitolo() {
        return titolo;
    }

    public String getAutore() {
        return autore;
    }

    public int getPrezzo() {
        return prezzo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public int getCopie_vendute() {
        return copie_vendute;
    }

    public int getPunti() {
        return punti;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public void setPrezzo(int prezzo) {
        this.prezzo = prezzo;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setCopie_vendute(int copie_vendute) {
        this.copie_vendute = copie_vendute;
    }

    public void setPunti(int punti) {
        this.punti = punti;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "ISBN=" + ISBN +
                ", titolo='" + titolo + '\'' +
                ", autore='" + autore + '\'' +
                ", prezzo=" + prezzo +
                ", descrizione='" + descrizione + '\'' +
                ", copie_vendute=" + copie_vendute +
                ", punti=" + punti +
                '}';
    }
}