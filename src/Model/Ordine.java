package Model;

import java.sql.Date;

public class Ordine {
    int id;
    String email;
    float prezzo;
    String pagamento;
    int punti;
    String indirizzo;
    String cap;
    String città;
    String nr_nome;
    String nr_cognome;
    String stato;
    Date data;

    public Ordine(int id, String email, float prezzo, String pagamento, int punti, String indirizzo, String cap, String città, String nr_nome, String nr_cognome, String stato, Date data) {
        this.id = id;
        this.email = email;
        this.prezzo = prezzo;
        this.pagamento = pagamento;
        this.punti = punti;
        this.indirizzo = indirizzo;
        this.cap = cap;
        this.città = città;
        this.nr_nome = nr_nome;
        this.nr_cognome = nr_cognome;
        this.stato = stato;
        this.data = data;
    }

    public Ordine(int id, Date data, String stato, int punti, float costo){
        this.id = id;
        this.data = data;
        this.stato = stato;
        this.punti = punti;
        this.prezzo = costo;
    }

    public int getCodice() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public float getCosto() {
        return prezzo;
    }

    public String getPagamento() {
        return pagamento;
    }

    public int getPunti() {
        return punti;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public String getCap() {
        return cap;
    }

    public String getCittà() {
        return città;
    }

    public String getNr_nome() {
        return nr_nome;
    }

    public String getNr_cognome() {
        return nr_cognome;
    }

    public void setCodice(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCosto(float prezzo) {
        this.prezzo = prezzo;
    }

    public void setPagamento(String pagamento) {
        this.pagamento = pagamento;
    }

    public void setPunti(int punti) {
        this.punti = punti;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public void setCittà(String città) {
        this.città = città;
    }

    public void setNr_nome(String nr_nome) {
        this.nr_nome = nr_nome;
    }

    public void setNr_cognome(String nr_cognome) {
        this.nr_cognome = nr_cognome;
    }

    public Date getData() {return data; }

    public void setData(Date data) { this.data = data; }

    public String getStato() { return stato; }

    public void setStato(String stato) { this.stato = stato; }

    @Override
    public String toString() {
        return "Ordine{" +
                "id=" + id +
                ", prezzo=" + prezzo +
                ", punti=" + punti +
                ", stato='" + stato + '\'' +
                ", data=" + data +
                '}';
    }
}
