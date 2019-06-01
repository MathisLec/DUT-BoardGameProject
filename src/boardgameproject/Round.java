/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardgameproject;

import boardgameproject.Buildings.Building;
import boardgameproject.Buildings.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author mlecoeuvre
 */
public class Round extends Application {

    private final int NB_TURN_MAX = 30;
    private int nbTurn = 1;
    private int nbPlayer;
    private int nbWorkerToPlace;
    private boolean putBuilding = true;
    public Scene scene;

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("View/MenuView.fxml"));

        scene = new Scene(root);

        stage.setTitle("Jeu");
        stage.setScene(scene);
        stage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        launch(args);
//        Player player = new Player();
//        Round round = new Round();
//        Board board = new Board(round, player);
//        Building b = new IBlock();
//        board.addBuilding(b, 5, 5);
//        board.addWorker(5, 5);
//        board.addWorker(5, 6);
//        board.endTurn();
    }

    public int getNbTurnMax() {
        return NB_TURN_MAX;
    }

    public int getNbTurn() {
        return nbTurn;
    }

    public int getNbPlayer() {
        return nbPlayer;
    }

    public int getNbWorkerToPlace() {
        return nbWorkerToPlace;
    }

    public Scene getScene() {
        return scene;
    }
    
    public boolean getPutBuilding(){
        return putBuilding;
    }
    
    public boolean setPutBuilding(){
        return putBuilding;
    }

    public void endTurn() {
        nbTurn++;
        System.out.println("nbTurn:" + nbTurn);
    }
    
}
