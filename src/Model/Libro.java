package Model;

public class Libro {
    String ISBN;
    String titolo;
    String autore;
    float prezzo;
    String descrizione;
    int punti;
    int copie_vendute;
    String genere;

    public Libro(String ISBN, String titolo, String autore, float prezzo, String descrizione, int punti, String genere) {
        this.ISBN = ISBN;
        this.titolo = titolo;
        this.autore = autore;
        this.prezzo = prezzo;
        this.descrizione = descrizione;
        this.punti = punti;
        this.copie_vendute = 0;
        this.genere = genere;
    }

    public Libro(String ISBN, String titolo, String autore, float prezzo, String descrizione, int copie_vendute, int punti,String genere) {
        this.ISBN = ISBN;
        this.titolo = titolo;
        this.autore = autore;
        this.prezzo = prezzo;
        this.descrizione = descrizione;
        this.punti = punti;
        this.copie_vendute = copie_vendute;
        this.genere = genere;
    }


    public String getISBN() {
        return ISBN;
    }

    public String getTitolo() {
        return titolo;
    }

    public String getAutore() {
        return autore;
    }

    public float getPrezzo() {
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

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public void setPrezzo(float prezzo) {
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
                "ISBN='" + ISBN + '\'' +
                ", titolo='" + titolo + '\'' +
                ", autore='" + autore + '\'' +
                ", prezzo=" + prezzo +
                ", descrizione='" + descrizione + '\'' +
                ", punti=" + punti +
                ", copie_vendute=" + copie_vendute +
                ", genere='" + genere + '\'' +
                '}';
    }
}
