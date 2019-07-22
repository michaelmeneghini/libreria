package Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class ControllerResponsabile {

    @FXML
    private JFXButton aggiungi_libro;

    @FXML
    private JFXButton verifica_ordini;

    @FXML
    private JFXButton verifica_libro_card;

    @FXML
    private AnchorPane anchor_pane;

    @FXML
    private void dynamicUpdateHandler(ActionEvent event) throws IOException {

        Parent root = null;

        JFXButton source = (JFXButton) event.getSource();
        switch (source.getText()){
            case "Aggiungi Libro":
                root = FXMLLoader.load(getClass().getResource("../View/AggiungiLibro.fxml"));
                break;
            case "Verifica stato ordini":
                root = FXMLLoader.load(getClass().getResource("../View/VerificaOrdini.fxml"));
                break;
            case "Verifica libro card":
                root = FXMLLoader.load(getClass().getResource("../View/VerificaLibroCard.fxml"));
                break;

        }


        anchor_pane.getChildren().setAll(root);
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {

        Parent registerFrameParent=FXMLLoader.load(getClass().getResource("../View/LoginFrame.fxml"));
        Scene registerFrame=new Scene(registerFrameParent);
        Stage window=(Stage)(((Node)event.getSource()).getScene().getWindow());
        window.setScene(registerFrame);
        window.show();

    }

}
