/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardgameproject.View;

import boardgameproject.Board;
import boardgameproject.Buildings.Building;
import boardgameproject.Buildings.*;
import boardgameproject.Cell;
import boardgameproject.Player;
import boardgameproject.Round;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 *
 * @author adesaleneuve
 */
public class GameViewController implements Initializable {

    private Player player = new Player();
    private Round round;
    Board board = new Board(round, player);

    boolean valide1 = false;
    boolean valide2 = false;
    boolean valide3 = false;
    boolean valide4 = false;
    boolean valide5 = false;
    boolean testDepl = false;

    @FXML
    private Canvas GameBoard;
    @FXML
    private Canvas BuildingHand1;
    @FXML
    private Canvas BuildingHand3;
    @FXML
    private Canvas BuildingHand4;
    @FXML
    private Canvas BuildingHand5;

    IBlock testI = new IBlock(board, 19, 4, GameBoard);
    Cell c = new Cell(21, 4);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        IBlock iblock = new IBlock(board);
        board.addBuilding(iblock, 0, 0);
        
        board.drawBoard(GameBoard);

        nbEnergy.setText(Integer.toString(player.getNbEnergy()));
        nbMaterials.setText(Integer.toString(player.getNbMaterials()));
        nbWorkers.setText(Integer.toString(player.getNbWorkers()));

    }

    public void update() {

        GraphicsContext gc = GameBoard.getGraphicsContext2D();
        gc.clearRect(0, 0, 1500, 1500);
        for (Cell efg : board.boardToList()) {
            char role = efg.getBuildingType();
            if (role == 'I') {
                efg.drawCell(GameBoard, efg.getY(), efg.getX(), Color.BLACK);
            } else {
                efg.drawCell(GameBoard, efg.getY(), efg.getX(), Color.WHITE);
            }
        }
        for (Building ef : board.getBuildings()) {
            ef.drawBuilding(GameBoard);
        }

    }

    @FXML
    private Label nbEnergy;
    @FXML
    private Label nbMaterials;
    @FXML
    private Label nbWorkers;
    
    @FXML
    private void endTurn(ActionEvent event) {
        round.endTurn();
        board.endTurn();
    }

    @FXML
    private void RotateRight(ActionEvent event) {
    }

    @FXML
    private void RotateLeft(ActionEvent event) {
    }

    @FXML
    private void QuitGame(ActionEvent event) {
        System.exit(0);
    }

    Building selectedBuilding = new IBlock();

    @FXML
    private void Testclick(MouseEvent event) {

        GraphicsContext gc = GameBoard.getGraphicsContext2D();

//        if ((int) event.getX() > 0 && (int) event.getX() < 300
//                && (int) event.getY() > 0 && (int) event.getY() < 600 && testDepl) {
//            
//        }
//        
//        for (Cell cc : testI.getCells()) {
//            if ((int) event.getX() / 30 == cc.getX() &&
//                    (int)event.getY() /30 == cc.getY()) {
//                testDepl = !testDepl;
//                
//            }
//        }
////        for (Building mm : board.getBuildings()) {
////            for (Cell mmm : mm.getCells()) {
////
////                if ((int) event.getX() > mmm.getX() * 30 && (int) event.getX() < mmm.getX() * 30 + mmm.getCellShape()
////                        && (int) event.getY() > mmm.getY() * 30 && (int) event.getY() < mmm.getY() * 30 + mmm.getCellShape()) {
////
////                    selectedBuilding = mm;
////                    mm.setSelectedBuilding(!mm.isSelectedBuilding());
////
////                }
////            }
////        }
        System.out.println((int) event.getX());

        System.out.println((int) event.getY());

    }

    @FXML
    private void MoveBuilding(MouseEvent event) {

        for (Cell mlk : selectedBuilding.getCells()) {
            mlk.deplaceCell(event.getX() / 30, event.getY() / 30);
        }

//        GraphicsContext gc = GameBoard.getGraphicsContext2D();
//        
//          if (testDepl && (int) event.getX() > c.getX()*30 && (int) event.getX() < 300
//                && (int) event.getY() > 0 && (int) event.getY() < 600) {
//            
//            testI.deplace(event.getX(), event.getY());
//        }
        update();
    }

}
