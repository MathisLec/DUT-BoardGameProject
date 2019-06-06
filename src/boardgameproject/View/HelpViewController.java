/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardgameproject.View;

import boardgameproject.Buildings.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
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
    @FXML
    private Canvas OBlockCanvas;
    @FXML
    private Canvas IBlockTCanvas;
    @FXML
    private Canvas IBlockHCanvas;
    @FXML
    private Canvas SBlockTCanvas;
    @FXML
    private Canvas SBlockHCanvas;
    @FXML
    private Canvas ZBlockTCanvas;
    @FXML
    private Canvas ZBlockHCanvas;
    @FXML
    private Canvas LBlockTCanvas;
    @FXML
    private Canvas LBlockRCanvas;
    @FXML
    private Canvas LBlockLCanvas;
    @FXML
    private Canvas LBlockDCanvas;
    @FXML
    private Canvas JBlockTCanvas;
    @FXML
    private Canvas JBlockRCanvas;
    @FXML
    private Canvas JBlockDCanvas;
    @FXML
    private Canvas JBlockLCanvas;
    @FXML
    private Canvas TBlockTCanvas;
    @FXML
    private Canvas TBlockRCanvas;
    @FXML
    private Canvas TBlockLCanvas;
    @FXML
    private Canvas TBlockDCanvas;
        
    private IBlock I = new IBlock();
    private JBlock J = new JBlock();
    private LBlock L = new LBlock();
    private OBlock O = new OBlock();
    private SBlock S = new SBlock();
    private TBlock T = new TBlock();
    private ZBlock Z = new ZBlock();
    int cellSize = 15;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        O.drawBuilding(OBlockCanvas,cellSize);
        OBlock.setText("O-Block : \n\t Cost : 8 materials \n\t Action : Park \n\t"
                + " Allows the player to freely move any one (1)\n\t worker to an available slot");
       
        I.rotateBuildingRight();
        I.drawBuilding(IBlockHCanvas, cellSize);
        IBlockH.setText("I-Block, Horizontal : \n\t Cost : 4 Materials \n\t Action : Factory \n\t"
                + " Consumes 1 Energy and produces \n\t 2 Materials each turn");
        I.rotateBuildingRight();
        I.drawBuilding(IBlockTCanvas, cellSize);
        IBlockT.setText("I-Block, Vertical : \n\t Cost : 4 Materials \n\t Action : High-Tech Factory \n\t"
                + " Consumes research level units of energy,\n\t produces 2 times the research level materials.");
        
        S.rotateBuildingRight();
        S.drawBuilding(SBlockHCanvas, cellSize);
        SBlockH.setText("S-Block, Horizontal : \n\t Cost : 2 Materials \n\t Action : Mine \n\t"
                + " Produces 1 Materials.");
        S.rotateBuildingRight();
        S.drawBuilding(SBlockTCanvas, cellSize);
        SBlockT.setText("S-Block, Top : \n\t Cost : 2 Materials \n\t Action : Synthesis Shop \n\t"
                + " Consumes 2 Energy, 1 money and produces Research materials\n\t and 1 Stellarium when the research level is at maximum(8).");
        
        Z.rotateBuildingRight();
        Z.drawBuilding(ZBlockHCanvas, cellSize);
        ZBlockH.setText("Z-Block, Horizontal : \n\t Cost : 2 Materials \n\t Action : Junkyard \n\t"
                + " Consumes 1 Energy and discards a building chosen from the\n\t player's deck (returning it to the bottom of the pile),\n\t then draws another from the pile.");
        Z.rotateBuildingRight();
        Z.drawBuilding(ZBlockTCanvas, cellSize);
        ZBlockT.setText("Z-Block, Top : \n\t Cost : 2 Materials \n\t Action : Spaceport \n\t"
                + " Consumes 1 Money and add 2 additional points per turn \n\t that a worker has spent on the Spaceport.");
        
        L.drawBuilding(LBlockTCanvas, cellSize);
        LBlockT.setText("L-Block, Top : \n\t Cost : 8 Materials \n\t Action : Apartement \n\t"
                + " Consumes 1 Energy ; for each 2 Workers\n\t non the same building performing this action, \n\t "
                + "returns them to the player and add 1 third Worker, \n\t placed outside of the board");
        L.rotateBuildingRight();
        L.drawBuilding(LBlockRCanvas, cellSize);
        LBlockR.setText("L-Block, Right : \n\t Cost : 8 Materials \n\t Action : Kitchen\n\t"
                + " Consumes 1 Energy and produces 4 Money.");
        L.rotateBuildingRight();
        L.drawBuilding(LBlockDCanvas, cellSize);
        LBlockD.setText("L-Block, Down : \n\t Cost : 8 Materials \n\t Action : Recruit Office\n\t"
                + " Consumes 4 Money and creates a worker\n\t for the player, placed outside the board.");
        L.rotateBuildingRight();
        L.drawBuilding(LBlockLCanvas, cellSize);
        LBlockL.setText("L-Block, Left : \n\t Cost : 8 Materials \n\t Action : Tax Office \n\t"
                + " Produces 2 Money");
        
        
        J.drawBuilding(JBlockTCanvas, cellSize);
        JBlockT.setText("J-Block, Top : \n\t Cost : 8 Materials \n\t Action : Laboratory \n\t"
                + " Consumes 4 Energy and draws a building\n\t from the pile.");
        J.rotateBuildingRight();
        J.drawBuilding(JBlockRCanvas, cellSize);
        JBlockR.setText("J-Block, Right: \n\t Cost : 8 Materials \n\t Action : Occult Laboratory \n\t"
                + " Produces 4 Energy and 4 Money, and consumes the worker.");
        J.rotateBuildingRight();
        J.drawBuilding(JBlockDCanvas, cellSize);
        JBlockD.setText("J-Block, Down: \n\t Cost : 8 Materials \n\t Action : Robotics Laboratory \n\t"
                + " Consumes Research Money and Research Energy \n\t and creates Research divided by 2 (rounded down) workers outside of the board.");
        J.rotateBuildingRight();
        J.drawBuilding(JBlockLCanvas, cellSize);
        JBlockL.setText("J-Block, Left: \n\t Cost : 8 Materials \n\t Action : Research Laboratory \n\t"
                + " Consumes 2 Energy and 2 Money to advance\n\t the Research level by 1 - up to a maximum of 8.");
        
        
        T.drawBuilding(TBlockTCanvas, cellSize);
        TBlockT.setText("T-Block, Top : \n\t Cost : 2 Materials \n\t Action : Solar Panel \n\t"
                + " Produce 2 Energy on even turn");
        T.rotateBuildingRight();
        T.drawBuilding(TBlockRCanvas, cellSize);
        TBlockR.setText("T-Block, Right : \n\t Cost : 2 Materials \n\t Action : Cycle Plant \n\t"
                + " Produce 1 Energy.");
        T.rotateBuildingRight();
        T.drawBuilding(TBlockDCanvas, cellSize);
        TBlockD.setText("T-Block, Down : \n\t Cost : 2 Materials \n\t Action : Wind Farm \n\t"
                + " Produce 2 Energy on odd turn");
        T.rotateBuildingRight();
        T.drawBuilding(TBlockLCanvas, cellSize);
        TBlockL.setText("T-Block, Left : \n\t Cost : 2 Materials \n\t Action : Incinerator\n\t"
                + " Consumes 1 Material and produces 2 Energy.");
        
    }    
    
}
