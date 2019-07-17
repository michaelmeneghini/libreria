package Model;

public class UtenteRegistrato extends Utente {

    String indirizzo;
    String città;
    int cap;
    int libro_card;

    public UtenteRegistrato(String email, String nome, String cognome, int telefono, String password, String indirizzo, String città, int cap, int libro_card) {
        super(email, nome, cognome, telefono, password);
        this.indirizzo = indirizzo;
        this.città = città;
        this.cap = cap;
        this.libro_card = libro_card;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public String getCittà() {
        return città;
    }

    public int getCap() {
        return cap;
    }

    public int getLibro_card() {
        return libro_card;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public void setCittà(String città) {
        this.città = città;
    }

    public void setCap(int cap) {
        this.cap = cap;
    }

    public void setLibro_card(int libro_card) {
        this.libro_card = libro_card;
    }

    @Override
    public String toString() {
        return "UtenteRegistrato{" + super.toString() +
                "indirizzo='" + indirizzo + '\'' +
                ", città='" + città + '\'' +
                ", cap=" + cap +
                ", libro_card=" + libro_card +
                '}';
    }
}
