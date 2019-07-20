package Controller;

import Model.DBConnector;
import Model.Libro;

import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class ControllerMainPage {

    ArrayList<Libro> lista;

    public void openWindow(ActionEvent event) throws SQLException, Exception {
        Connection db = DBConnector.getConnection();
        Statement st = db.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM libro;");

        while(rs.next()){
            lista.add(new Libro(rs.getString(1),rs.getString(2),rs.getString(3),rs.getFloat(4),rs.getString(5),rs.getInt(6),rs.getInt(7)));
        }

    }

}
