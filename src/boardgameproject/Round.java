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

    private int nbTurnMax;
    private int nbTurn;
    private int nbPlayer;
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
    public static void main(String[] args) {
        launch(args);
    }

    public int getNbTurnMax() {
        return nbTurnMax;
    }

    public int getNbTurn() {
        return nbTurn;
    }

    public int getNbPlayer() {
        return nbPlayer;
    }

    public Scene getScene() {
        return scene;
    }
    
    public void endTurn(){
        nbTurn++;
        System.out.println("nbTurn:"+nbTurn);
    }

}
