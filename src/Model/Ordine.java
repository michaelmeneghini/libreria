package Model;

public class Ordine {
    int id;
    String email;
    int prezzo;
    String pagamento;
    int punti;
    String indirizzo;
    int cap;
    String città;
    String nr_nome;
    String nr_cognome;

    public Ordine(int id, String email, int prezzo, String pagamento, int punti, String indirizzo, int cap, String città, String nr_nome, String nr_cognome) {
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
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public int getPrezzo() {
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

    public int getCap() {
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

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPrezzo(int prezzo) {
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

    public void setCap(int cap) {
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
}
