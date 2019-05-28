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
import boardgameproject.Pile;
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

    private Round round = new Round();
    Player player = new Player();
    Board board = new Board(round, player);
    ArrayList<Building> buildings = new ArrayList<>();

    Building selectedBuilding;

    @FXML
    private Canvas GameBoard;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        selectedBuilding = new ZBlock();
        buildings.add(selectedBuilding);
        board.addBuilding(selectedBuilding, 1, 3);

        Building bl = new IBlock(board, 17, 7, GameBoard);
        buildings.add(bl);

        board.drawBoard(GameBoard);

        nbEnergyLabel.setText(Integer.toString(player.getNbEnergy()));
        nbMaterialsLabel.setText(Integer.toString(player.getNbMaterials()));
        nbWorkersLabel.setText(Integer.toString(player.getNbWorkers()));

        update();
    }

    public void update() {

        GraphicsContext gc = GameBoard.getGraphicsContext2D();
        gc.clearRect(0, 0, 1500, 1500);
        Color color = Color.WHITE;
        for (Cell efg : board.boardToList()) {
            color = colorSelector(efg, color);
            efg.drawCell(GameBoard, efg.getY(), efg.getX(), color);
        }
        for (Building ef : buildings) {
            ef.drawBuilding(GameBoard);
        }
    }

    private Color colorSelector(Cell cell, Color color) {
        char role = cell.getBuildingType();
        switch (role) {
            case 'I':
                color = Color.CYAN;
                break;
            case 'J':
                color = Color.BLUE;
                break;
            case 'L':
                color = Color.ORANGE;
                break;
            case 'O':
                color = Color.YELLOW;
                break;
            case 'S':
                color = Color.GREEN;
                break;
            case 'T':
                color = Color.PURPLE;
                break;
            case 'Z':
                color = Color.RED;
                break;
            default:
                color = Color.WHITE;
                break;
        }
        return color;
    }

    @FXML
    private Label nbEnergyLabel;
    @FXML
    private Label nbMaterialsLabel;
    @FXML
    private Label nbWorkersLabel;

    @FXML
    private void endTurn(ActionEvent event) {
        round.endTurn();
        board.endTurn();
    }

    @FXML
    private void RotateRight(ActionEvent event) {
        selectedBuilding.rotateBuildingRight();
        update();
    }

    @FXML
    private void RotateLeft(ActionEvent event) {
        selectedBuilding.rotateBuildingLeft();
        update();
    }

    @FXML
    private void QuitGame(ActionEvent event) {
        System.exit(0);
    }

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
        for (Building mm : buildings) {
            for (Cell mmm : mm.getCells()) {

                if ((int) event.getX() > mmm.getX() * 30 && (int) event.getX() < mmm.getX() * 30 + mmm.getCellShape()
                        && (int) event.getY() > mmm.getY() * 30 && (int) event.getY() < mmm.getY() * 30 + mmm.getCellShape()) {
                    gc.setFill(Color.PURPLE);
                    gc.strokeRect(mmm.getX() * mmm.getCellShape(),mmm.getY() * mmm.getCellShape(), mmm.getCellShape(), mmm.getCellShape());
                    selectedBuilding = mm;
                    mm.setSelectedBuilding(true);

                }
            }
        }
        System.out.println((int) event.getX());

        System.out.println((int) event.getY());

    }

    @FXML
    private void MoveBuilding(MouseEvent event) {

        for (Building b : buildings) {
            if (b.isSelectedBuilding()) {
                for (Cell mlk : b.getCells()) {
                    mlk.deplaceCell(event.getX() / 30, event.getY() / 30);
                }
            }
        }

//        GraphicsContext gc = GameBoard.getGraphicsContext2D();
//        
//          if (testDepl && (int) event.getX() > c.getX()*30 && (int) event.getX() < 300
//                && (int) event.getY() > 0 && (int) event.getY() < 600) {
//            
//            testI.deplace(event.getX(), event.getY());
//        }
        //update();
    }

    @FXML
    private void ReleaseBuilding(MouseEvent event) {

        for (Building mm : buildings) {
            for (Cell mmm : mm.getCells()) {

                if ((int) event.getX() > mmm.getX() * 30 && (int) event.getX() < mmm.getX() * 30 + mmm.getCellShape()
                        && (int) event.getY() > mmm.getY() * 30 && (int) event.getY() < mmm.getY() * 30 + mmm.getCellShape()) {
                    mm.setSelectedBuilding(false);

                }
            }
        }
    }

}
