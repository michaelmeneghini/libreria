package Controller;

import Model.DBConnector;
import Model.Libro_Card;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ControllerVerificaLibroCard {

    private ArrayList<Libro_Card> listaLibroCard;

    public void onUpdate() throws SQLException {

        listaLibroCard = new ArrayList<>();

        Connection db = DBConnector.getConnection();
        Statement st = db.createStatement();
        ResultSet rs = st.executeQuery("Select * from libro_card ;");
        while(rs.next())
            listaLibroCard.add(new Libro_Card(rs.getInt(1),rs.getDate(2),rs.getInt(3)));

        db.close();
        st.close();
    }


}
