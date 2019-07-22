package Model;

import java.sql.Date;

public class Libro_CardTable {

    int  id;
    int punti;
    String nome;
    String cognome;
    String email;

    public Libro_CardTable(int id, String nome, String cognome, String email, int punti) {
        this.id = id;
        this.punti = punti;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPunti() {
        return punti;
    }

    public void setPunti(int punti) {
        this.punti = punti;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
