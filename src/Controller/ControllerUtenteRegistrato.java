package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerUtenteRegistrato {

    @FXML
    private HBox hbox_principale;

    @FXML
    private Button log_out;

    @FXML
    private AnchorPane anchor_pane;

    @FXML
    void dynamicPageUpdater(ActionEvent event) throws IOException {

        Stage window=(Stage)(((Node)event.getSource()).getScene().getWindow());
        window.setResizable(false);
        window.setMaximized(false);
        Button source = (Button) event.getSource();

        switch (source.getText()){
            case "Carrello":
                window.setWidth(964);
                window.setHeight(650);
                window.setX(250);
                window.setY(60);
                hbox_principale.setPrefWidth(964);
                break;
            case"Profilo":
                window.setX(250);
                window.setY(60);
                window.setWidth(795);
                window.setHeight(600);
                hbox_principale.setPrefWidth(795);
                break;
            case"Ordini":
                System.out.println("ORDINI");

                Parent root = FXMLLoader.load(getClass().getResource("../View/VerificaOrdini.fxml"));
                anchor_pane.getChildren().setAll(root);
                return;


            case"Classifiche":

                window.setX(20);
                window.setY(60);
                window.setWidth(1325);
                window.setHeight(600);
                hbox_principale.setPrefWidth(795);

                break;
            case"Libri":
                window.setX(7);
                window.setWidth(1350);
                window.setHeight(600);
                hbox_principale.setPrefWidth(1390);

                break;
            default:
                System.out.println("DEFAULT");

                break;
        }

        Parent root = FXMLLoader.load(getClass().getResource("../View/"+source.getText()+".fxml"));
        anchor_pane.getChildren().setAll(root);

    }

    @FXML
    private void logout(ActionEvent event) throws IOException {

        Parent registerFrameParent= FXMLLoader.load(getClass().getResource("../View/LoginFrame.fxml"));
        Scene registerFrame=new Scene(registerFrameParent);
        Stage window=(Stage)(((Node)event.getSource()).getScene().getWindow());
        window.setX(250);
        window.setY(60);
        window.setHeight(400);
        window.setWidth(640);
        window.setScene(registerFrame);
        window.setMaximized(false);
        window.show();

    }

}
