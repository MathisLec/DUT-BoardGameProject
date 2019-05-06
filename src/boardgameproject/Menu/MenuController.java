package boardgameproject.Menu;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MenuController implements Initializable {
    
    @FXML
    private Button playButton;
    @FXML
    private Button quitButton;
    @FXML
    private Label titre;
    
    @FXML
    void onPlayClick(ActionEvent event) {
        
    }
    
    @FXML
    void onQuitClick(ActionEvent event) {
        System.exit(0);
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        titre.setText("JEU PLATEAU BATIMENTS");
    }
    
    public void aaaa() {
        
    }
    
}
