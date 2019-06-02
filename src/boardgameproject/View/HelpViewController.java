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
    private Label IBlock;
    @FXML
    private Label OBlock;
    @FXML
    private Label TBlock;
    @FXML
    private Label LBlock;
    @FXML
    private Label JBlock;
    @FXML
    private Label SBlock;
    @FXML
    private Label ZBlock;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        IBlock.setText("I-Block : \n\t Cost : 4 materials \n\t Action : Factory \n\t"
                + " Consumes 1 energy and produces 2 materials each turn");
        OBlock.setText("O-Block : \n\t Cost : 8 materials \n\t Action : Park \n\t"
                + " Allows the player to freely move any one (1) worker to an available slot");
        TBlock.setText("T-Block : \n\t Cost : 2 materials \n\t Action : Solar Panel \n\t"
                + " Produce 2 energy on even turn");
        LBlock.setText("L-Block : \n\t Cost : 8 materials \n\t Action : Apartement \n\t"
                + " Cosumes 1 energy ; for each two workers on the same building performing this action, \n"
                + "returns them to the player and add 1 third worker, placed outside of the board");
        JBlock.setText("J-Block : \n\t Cost : 8 materials \n\t Action : Laboratory \n\t"
                + " Cosumes 1 energy and produces 2 materials each turn");
        SBlock.setText("S-Block : \n\t Cost : 2 materials \n\t Action : Mine \n\t"
                + " Cosumes 1 energy and produces 2 materials each turn");
        ZBlock.setText("Z-Block : \n\t Cost : 2 materials \n\t Action : Junkyard \n\t"
                + " Cosumes 1 energy and produces 2 materials each turn");
        
    }    
    
}
