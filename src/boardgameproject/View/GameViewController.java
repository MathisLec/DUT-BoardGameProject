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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author adesaleneuve
 */
public class GameViewController implements Initializable {

    private Player player = new Player();
    private Round Round;
    Board board = new Board(Round, player);
    Building O = new OBlock();
    Building I = new IBlock();
    Building J = new JBlock();
    Building S = new SBlock();
    Building T = new TBlock();
    boolean valide1 = false;
    boolean valide2 = false;
    boolean valide3 = false;
    boolean valide4 = false;
    boolean valide5 = false;
    
    
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
    @FXML
    private Button buttonHand1;
    @FXML
    private Button buttonHand2;
    @FXML
    private Button buttonHand3;
    @FXML
    private Button buttonHand4;
    @FXML
    private Button buttonHand5;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        board.drawBoard(GameBoard);
        

        GraphicsContext gc = BuildingHand1.getGraphicsContext2D();
        gc.clearRect(0, 0, 100, 100);
        
        nbEnergy.setText(Integer.toString(player.getNbEnergy()));
        nbMaterials.setText(Integer.toString(player.getNbMaterials()));
        nbWorkers.setText(Integer.toString(player.getNbWorkers()));
        
        O.drawBuilding(BuildingHand1);
        I.drawBuilding(BuildingHand2);
        J.drawBuilding(BuildingHand3);
        S.drawBuilding(BuildingHand4);
        T.drawBuilding(BuildingHand5);


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
        if(valide1){
            GraphicsContext gc = BuildingHand1.getGraphicsContext2D();
            gc.clearRect(0, 0, 100, 100);
            O.rotateBuildingRight();
            O.drawBuilding(BuildingHand1);
        }
        if(valide2){
            GraphicsContext gc = BuildingHand2.getGraphicsContext2D();
            gc.clearRect(0, 0, 100, 100);
            I.rotateBuildingRight();
            I.drawBuilding(BuildingHand2);
        }
        if(valide3){
            GraphicsContext gc = BuildingHand3.getGraphicsContext2D();
            gc.clearRect(0, 0, 100, 100);
            J.rotateBuildingRight();
            J.drawBuilding(BuildingHand3);
        }
        if(valide4){
            GraphicsContext gc = BuildingHand4.getGraphicsContext2D();
            gc.clearRect(0, 0, 100, 100);
            S.rotateBuildingRight();
            S.drawBuilding(BuildingHand4);
        }
        if(valide5){
            GraphicsContext gc = BuildingHand5.getGraphicsContext2D();
            gc.clearRect(0, 0, 100, 100);
            T.rotateBuildingRight();
            T.drawBuilding(BuildingHand5);
        }
    }

    @FXML
    private void RotateLeft(ActionEvent event) {
        if(valide1){
            GraphicsContext gc = BuildingHand1.getGraphicsContext2D();
            gc.clearRect(0, 0, 100, 100);
            O.rotateBuildingLeft();
            O.drawBuilding(BuildingHand1);
        }
        if(valide2){
            GraphicsContext gc = BuildingHand2.getGraphicsContext2D();
            gc.clearRect(0, 0, 100, 100);
            I.rotateBuildingLeft();
            I.drawBuilding(BuildingHand2);
        }
        if(valide3){
            GraphicsContext gc = BuildingHand3.getGraphicsContext2D();
            gc.clearRect(0, 0, 100, 100);
            J.rotateBuildingLeft();
            J.drawBuilding(BuildingHand3);
        }
        if(valide4){
            GraphicsContext gc = BuildingHand4.getGraphicsContext2D();
            gc.clearRect(0, 0, 100, 100);
            S.rotateBuildingLeft();
            S.drawBuilding(BuildingHand4);
        }
        if(valide5){
            GraphicsContext gc = BuildingHand5.getGraphicsContext2D();
            gc.clearRect(0, 0, 100, 100);
            T.rotateBuildingLeft();
            T.drawBuilding(BuildingHand5);
        }

    }

    @FXML
    private void QuitGame(ActionEvent event) {
        System.exit(0);
    }

    private void placeBuilding(MouseEvent event) {
        O.drawBuilding(GameBoard);
    }

    @FXML
    private void moveCanvasEnd(MouseEvent event) {
    }

    @FXML
    private void buildingOnBoard(MouseEvent event) {
        
    }

    @FXML
    private void Select1(MouseEvent event) {
        valide1 = true;
        valide2 = false;
        valide3 = false;
        valide4 = false;
        valide5 = false;
    }
    
    @FXML
    private void Select2(MouseEvent event) {
        valide1 = false;
        valide2 = true;
        valide3 = false;
        valide4 = false;
        valide5 = false;
    }

    @FXML
    private void Select3(MouseEvent event) {
        valide1 = false;
        valide2 = false;
        valide3 = true;
        valide4 = false;
        valide5 = false;
    }

    @FXML
    private void Select4(MouseEvent event) {
        valide1 = false;
        valide2 = false;
        valide3 = false;
        valide4 = true;
        valide5 = false;
    }

    @FXML
    private void Select5(MouseEvent event) {
        valide1 = false;
        valide2 = false;
        valide3 = false;
        valide4 = false;
        valide5 = true;
    }

    

}
