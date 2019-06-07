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

    // this boolean will permit to know if the game is a saved one or a new one
    public static boolean mustResume = false;

    @FXML
    private Button PlayButton;
    @FXML
    private Button ResumeButton;
    
    /**
     * This will permit to start a new game by clicking on the PlayButton on the menu 
     */
    @FXML
    void onPlayClick(ActionEvent event) throws IOException {
        mustResume = false;
        Stage stage = (Stage) PlayButton.getScene().getWindow();
        stage.close();

        Stage stage2 = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("GameView.fxml"));

        Scene scene = new Scene(root);

        stage2.setScene(scene);
        stage2.setResizable(false);
        stage2.show();
    }
    
    /**
     * This will permit to resume a game by clicking on the ResumeButton on the menu 
     */
    @FXML
    void onResumeClick(ActionEvent event) throws IOException {
        mustResume = true;
        Stage stage = (Stage) ResumeButton.getScene().getWindow();
        stage.close();

        Stage stage2 = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("GameView.fxml"));

        Scene scene = new Scene(root);

        stage2.setScene(scene);
        stage2.setResizable(false);
        stage2.show();
    }
    
    /**
     * This will permit to quit the program by clicking on the QuitButton on the menu
     */
    @FXML
    void onQuitClick(ActionEvent event) {
        System.exit(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
