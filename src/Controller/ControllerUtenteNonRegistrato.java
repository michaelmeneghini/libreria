package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerUtenteNonRegistrato {

    @FXML
    private AnchorPane anchor_pane;

    @FXML
    void dynamicPageUpdater(ActionEvent event) throws IOException {

        Button source = (Button) event.getSource();
        Parent root = FXMLLoader.load(getClass().getResource("../View/"+source.getText()+".fxml"));
        anchor_pane.getChildren().setAll(root);

    }

    @FXML
    private void logout(ActionEvent event) throws IOException {

        Parent registerFrameParent= FXMLLoader.load(getClass().getResource("../View/LoginFrame.fxml"));
        Scene registerFrame=new Scene(registerFrameParent);
        Stage window=(Stage)(((Node)event.getSource()).getScene().getWindow());
        window.setHeight(400);
        window.setWidth(640);
        window.setScene(registerFrame);
        window.setMaximized(false);
        window.show();

    }
}
