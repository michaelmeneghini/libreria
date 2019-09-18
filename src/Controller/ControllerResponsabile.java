package Controller;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;


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

        Stage window=(Stage)(((Node)event.getSource()).getScene().getWindow());
        window.setResizable(false);
        window.setMaximized(false);

        JFXButton source = (JFXButton) event.getSource();
        String pageToOpen=null;
        switch (source.getText()){
            case "Aggiungi Libro":
                window.setX(7);
                window.setWidth(1350);
                window.setHeight(640);
                pageToOpen="AggiungiLibro";
                break;
            case "Verifica stato ordini":
                window.setWidth(1300);
                window.setHeight(640);
                window.setX(250);
                window.setY(60);
                pageToOpen="VerificaOrdiniResp";
                break;
            case "Verifica libro card":
                window.setWidth(830);
                window.setHeight(640);
                window.setX(250);
                window.setY(60);
                pageToOpen="VerificaLibroCard";
                break;


        }
        root = FXMLLoader.load(getClass().getResource("../View/"+pageToOpen+".fxml"));
        anchor_pane.getChildren().setAll(root);
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {

        Parent registerFrameParent=FXMLLoader.load(getClass().getResource("../View/LoginFrame.fxml"));
        Scene registerFrame=new Scene(registerFrameParent);
        Stage window=(Stage)(((Node)event.getSource()).getScene().getWindow());
        window.setHeight(400);
        window.setWidth(640);
        window.setMaximized(false);
        window.setScene(registerFrame);
        window.show();

    }

}
