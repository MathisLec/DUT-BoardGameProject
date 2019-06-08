/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardgameproject.View;

import boardgameproject.Buildings.Building;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author adesaleneuve
 */
public class ScoreViewController implements Initializable {

    @FXML
    private Label scoreJoueur, WorkerInhand, WorkerOnBoard, BuildingOnBoard,
            ResearchPoints, TurnInSpacePort, nbMaterials, nbEnergy, nbMoney,
            nbWorkersOnBoard, sizeOfBuildingPile, buildingsOnBoard;

    /**
     * Initializes the controller class. And write every value of the score and
     * the player's score
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        scoreJoueur.setText("Your score : " + Integer.toString(GameViewController.board.getScore()));
        WorkerInhand.setText("The Workers in hand value = 2 pts.");
        WorkerOnBoard.setText("The Workers on board value = 4 pts.");
        BuildingOnBoard.setText("The Buildings on board value = 8 pts.");
        ResearchPoints.setText("The Research Points value = 4 pts.");
        TurnInSpacePort.setText("The Turn in the spaceport value = 2 pts.");
        nbMaterials.setText("The Materials value = 1 pt.");
        nbEnergy.setText("The Energy value = 1 pt.");
        nbMoney.setText("The Money value = 1 pt.");
        int i = 0;
        for (Building b : GameViewController.board.getBuildings()) {
            i += b.getNbWorker();
        }
        nbWorkersOnBoard.setText("There is " + i + " Workers on the board.");
        sizeOfBuildingPile.setText("There is " +
                GameViewController.player.getPileSize() + " Buildings left in the pile.");
        String s = "";
        for (Character b : GameViewController.board.getNbBuildingsOfEachType().keySet()) {
            s += "There is " + GameViewController.board.getNbBuildingsOfEachType().get(b)
                    + " " + b + "Block on the board.\n";
        }
        buildingsOnBoard.setText(s);

    }

}
