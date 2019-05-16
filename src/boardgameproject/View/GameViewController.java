/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardgameproject.View;

import boardgameproject.Board;
import boardgameproject.Buildings.Building;
import boardgameproject.Buildings.*;
import boardgameproject.Player;
import boardgameproject.Round;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;

/**
 *
 * @author adesaleneuve
 */
public class GameViewController implements Initializable {

    private Player player = new Player();
    private Round Round;
    Board board = new Board(Round, player);
    Building I = new IBlock();
    Building J = new JBlock();
    Building L = new LBlock();
    Building O = new OBlock();

    @FXML
    private Canvas GameBoard;
    @FXML
    private Canvas BuildingHand1;
    @FXML
    private Canvas BuildingHand2;
    @FXML
    private Canvas BuildingHand3;
    @FXML
    private Canvas BuildingHand4;
    @FXML
    private Canvas BuildingHand5;
    @FXML
    private Canvas BuildingHand6;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        board.drawBoard(GameBoard);
        
        GraphicsContext gc = BuildingHand1.getGraphicsContext2D();
        gc.clearRect(0, 0, 100, 100);
        nbEnergy.setText(Integer.toString(player.getNbEnergy()));
        nbMaterials.setText(Integer.toString(player.getNbMaterials()));
        nbWorkers.setText(Integer.toString(player.getNbWorkers()));
        O.drawBuilding(BuildingHand1);
        O.rotateBuildingLeft();
        O.drawBuilding(BuildingHand2);
        O.rotateBuildingLeft();
        O.drawBuilding(BuildingHand3);
        O.rotateBuildingLeft();
        O.drawBuilding(BuildingHand4);
        O.rotateBuildingLeft();
        O.drawBuilding(BuildingHand5);

    }

    @FXML
    private Label nbEnergy;
    @FXML
    private Label nbMaterials;
    @FXML
    private Label nbWorkers;

    @FXML
    private void endTurn(ActionEvent event) {

    }

    @FXML
    private void RotateRight(ActionEvent event) {
    }

    @FXML
    private void RotateLeft(ActionEvent event) {
        I.rotateBuildingLeft();
        
    }

    @FXML
    private void QuitGame(ActionEvent event) {
        System.exit(0);
    }

}
