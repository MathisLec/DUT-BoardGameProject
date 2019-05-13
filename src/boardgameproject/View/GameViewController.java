/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardgameproject.View;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;

/**
 *
 * @author adesaleneuve
 */
public class GameViewController implements Initializable{

    @FXML
    private Canvas GameBoard;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
    }
    
}
