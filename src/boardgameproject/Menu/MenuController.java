package boardgameproject.Menu;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class MenuController implements Initializable {


    @FXML
    void onPlayClick(ActionEvent event) throws IOException {


    }

    @FXML
    void onQuitClick(ActionEvent event) {
        System.exit(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
