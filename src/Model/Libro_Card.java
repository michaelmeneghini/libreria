package Model;

import java.sql.Date;

public class Libro_Card {
    int  id;
    Date data_emissione;
    int punti;

    public Libro_Card(int id, Date data_emissione, int punti) {
        this.id = id;
        this.data_emissione = data_emissione;
        this.punti = punti;
    }

    public int getId() {
        return id;
    }

    public Date getData_emissione() {
        return data_emissione;
    }

    public int getPunti() {
        return punti;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setData_emissione(Date data_emissione) {
        this.data_emissione = data_emissione;
    }

    public void setPunti(int punti) {
        this.punti = punti;
    }

}
