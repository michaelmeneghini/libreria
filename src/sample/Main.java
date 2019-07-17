package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

        Connection db = DBConnector.getConnection();
        Statement st = db.createStatement();
        ResultSet rs = st.executeQuery("Select * from libro");
        while(rs.next()){
            System.out.println(rs.getStatement().toString());
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
