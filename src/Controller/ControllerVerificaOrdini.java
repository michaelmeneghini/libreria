package Controller;

import Model.DBConnector;
import Model.Ordine;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ControllerVerificaOrdini {

    private ArrayList<Ordine> listaOrdini;

    public void onUpdate() throws SQLException, Exception {
        listaOrdini = new ArrayList<>();
        Connection db = DBConnector.getConnection();
        Statement st = db.createStatement();
        ResultSet rs = st.executeQuery("Select * from Ordine");
        while (rs.next()){
            listaOrdini.add(new Ordine(rs.getInt(1),rs.getString(2),rs.getFloat(3),rs.getString(4),rs.getInt(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10)));
        }

    }
}
