/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardgameproject;

import java.io.Serializable;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author mlecoeuvre
 */
public class Round extends Application implements Serializable {

    private final int NB_TURN_MAX = 30;
    private int nbTurn;
    private boolean putBuilding = true;
    public Scene scene;

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("View/MenuView.fxml"));

        scene = new Scene(root);

        stage.setTitle("Jeu");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        launch(args);
    }

    /**
     *
     * @return the max number of turn
     */
    public int getNbTurnMax() {
        return NB_TURN_MAX;
    }

    /**
     *
     * @return the current turn
     */
    public int getNbTurn() {
        return nbTurn;
    }

    /**
     *
     * @return the scene
     */
    public Scene getScene() {
        return scene;
    }

    /**
     *
     * @return true if the player is allowed to put a building on the board
     */
    public boolean getPutBuilding() {
        return putBuilding;
    }

    /**
     * this method will change putBuilding to make it know if the player already
     * built a building this turn or not
     *
     * @param b
     */
    public void setPutBuilding(boolean b) {
        putBuilding = b;
    }

    /**
     * This method will increase the number of turn
     */
    public void endTurn() {
        nbTurn++;
        System.out.println("nbTurn:" + nbTurn);
    }

}
