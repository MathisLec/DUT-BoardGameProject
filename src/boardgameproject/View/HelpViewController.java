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

/**
 * FXML Controller class
 *
 * @author adasa
 */
public class HelpViewController implements Initializable {

    @FXML
    private Label OBlock;
    @FXML
    private Label IBlockH;
    @FXML
    private Label TBlockT;
    @FXML
    private Label LBlockT;
    @FXML
    private Label JBlockT;
    @FXML
    private Label SBlockH;
    @FXML
    private Label ZBlockH;
    @FXML
    private Label IBlockT;
    @FXML
    private Label TBlockD;
    @FXML
    private Label TBlockR;
    @FXML
    private Label TBlockL;
    @FXML
    private Label LBlockD;
    @FXML
    private Label LBlockR;
    @FXML
    private Label LBlockL;
    @FXML
    private Label JBlockD;
    @FXML
    private Label JBlockR;
    @FXML
    private Label JBlockL;
    @FXML
    private Label SBlockT;
    @FXML
    private Label ZBlockT;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        IBlockH.setText("I-Block, Horizontal : \n\t Cost : 4 materials \n\t Action : Factory \n\t"
                + " Consumes 1 Energy and produces 2 Materials each turn");
        IBlockT.setText("I-Block, Vertical : \n\t Cost : 4 materials \n\t Action : High-Tech Factory \n\t"
                + " Consumes research level units of energy, produces 2 times the research level materials.");
        
        
        TBlockT.setText("T-Block, Top : \n\t Cost : 2 Materials \n\t Action : Solar Panel \n\t"
                + " Produce 2 Energy on even turn");
        TBlockR.setText("T-Block, Right : \n\t Cost : 2 Materials \n\t Action : Cycle Plant \n\t"
                + " Produce 1 Energy.");
        TBlockL.setText("T-Block, Left : \n\t Cost : 2 Materials \n\t Action : Incinerator\n\t"
                + " Consumes 1 Material and produces 2 Energy.");
        TBlockD.setText("T-Block, Down : \n\t Cost : 2 Materials \n\t Action : Wind Farm \n\t"
                + " Produce 2 Energy on odd turn");
        
        
        LBlockT.setText("L-Block, Top : \n\t Cost : 8 materials \n\t Action : Apartement \n\t"
                + " Consumes 1 Energy ; for each 2 Workers on the same building performing this action, \n"
                + "returns them to the player and add 1 third Worker, placed outside of the board");
        LBlockR.setText("L-Block, Right : \n\t Cost : 8 materials \n\t Action : Kitchen\n\t"
                + " Consumes 1 Energy and produces 4 Money.");
        LBlockL.setText("L-Block, Left : \n\t Cost : 8 materials \n\t Action : Tax Office \n\t"
                + " Produces 2 Money");
        LBlockD.setText("L-Block, Down : \n\t Cost : 8 materials \n\t Action : Recruit Office\n\t"
                + " Consumes 4 Money and creates a worker for the player, placed outside the board.");
        
        
        JBlockT.setText("J-Block, Top : \n\t Cost : 8 materials \n\t Action : Laboratory \n\t"
                + " Consumes 4 Energy and draws a building from the pile.");
        JBlockT.setText("J-Block, Right: \n\t Cost : 8 materials \n\t Action : Occult Laboratory \n\t"
                + " Produces 4 Energy and 4 Money, and consumes the worker.");
        JBlockT.setText("J-Block, Left: \n\t Cost : 8 materials \n\t Action : Research Laboratory \n\t"
                + " Consumes 2 Energy and 2 Money to advance the Research level by 1 - up to a maximum of 8.");
        JBlockT.setText("J-Block, Down: \n\t Cost : 8 materials \n\t Action : Robotics Laboratory \n\t"
                + " Consumes Research Money and Research Energy and creates Research divided by 2 (rounded down) workers outside of the board.");
        
        
        SBlockT.setText("S-Block : \n\t Cost : 2 materials \n\t Action : Mine \n\t"
                + " Consumes 1 energy and produces 2 materials each turn");
        ZBlockT.setText("Z-Block : \n\t Cost : 2 materials \n\t Action : Junkyard \n\t"
                + " Consumes 1 energy and produces 2 materials each turn");
        
                
        OBlock.setText("O-Block : \n\t Cost : 8 materials \n\t Action : Park \n\t"
                + " Allows the player to freely move any one (1) worker to an available slot");
        
    }    
    
}
