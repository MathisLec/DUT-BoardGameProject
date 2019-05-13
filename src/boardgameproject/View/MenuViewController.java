package boardgameproject.View;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuViewController implements Initializable {

    @FXML
    private Button PlayButton;

    @FXML
    void onPlayClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) PlayButton.getScene().getWindow();
        stage.close(); 
        
        Stage stage2 = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("GameView.fxml"));

        Scene scene = new Scene(root);

        stage2.setScene(scene);
        stage2.show();
    }

    @FXML
    void onQuitClick(ActionEvent event) {
        System.exit(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
