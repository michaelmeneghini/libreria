package Model;

import java.sql.Time;

public class Libro_Card {
    int  id;
    Time data_emissione;
    int punti;

    public Libro_Card(int id, Time data_emissione, int punti) {
        this.id = id;
        this.data_emissione = data_emissione;
        this.punti = punti;
    }

    public int getId() {
        return id;
    }

    public Time getData_emissione() {
        return data_emissione;
    }

    public int getPunti() {
        return punti;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setData_emissione(Time data_emissione) {
        this.data_emissione = data_emissione;
    }

    public void setPunti(int punti) {
        this.punti = punti;
    }
}
