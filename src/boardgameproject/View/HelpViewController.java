/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardgameproject.View;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author adasa
 */
public class HelpViewController implements Initializable {

    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private Label IBlock;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        IBlock.setText("I-Block : \n\t Cost : 4 materials \n\t Action : Factory \n\t Cosumes 1 energy and produces 2 materials each turn.");
    }    
    
}
