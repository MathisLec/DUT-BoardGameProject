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
import java.util.HashMap;
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
    @FXML
    private Canvas main1;
    @FXML
    private Canvas main2;
    @FXML
    private Canvas main3;
    @FXML
    private Canvas main4;
    @FXML
    private Canvas main5;
    @FXML
    private Label nbEnergyLabel;
    @FXML
    private Label nbMaterialsLabel;
    @FXML
    private Label nbWorkersLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        newGame();

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
            case 'P':
                color = Color.GREY;
                break;
            default:
                color = Color.WHITE;
                break;
        }
        return color;
    }

    @FXML
    private void endTurn(ActionEvent event) {
        round.endTurn();
        board.endTurn();
    }

    @FXML
    private void RotateRight(ActionEvent event) {
        try {
            GraphicsContext gc = selectedBuilding.getCanvas().getGraphicsContext2D();
            selectedBuilding.rotateBuildingRight();
            gc.clearRect(0, 0, 121, 121);
            gc.setFill(Color.BLACK);
            gc.strokeRect(0, 0, selectedBuilding.getCanvas().getWidth(), selectedBuilding.getCanvas().getHeight());
            selectedBuilding.drawBuilding(selectedBuilding.getCanvas());

            update();
        } catch (NullPointerException e) {
            System.out.println("Pas de Building selectionné");
        }
    }

    @FXML
    private void RotateLeft(ActionEvent event) {
        try {
            GraphicsContext gc = selectedBuilding.getCanvas().getGraphicsContext2D();
            selectedBuilding.rotateBuildingLeft();
            gc.clearRect(0, 0, 121, 121);
            gc.setFill(Color.BLACK);
            gc.strokeRect(0, 0, selectedBuilding.getCanvas().getWidth(), selectedBuilding.getCanvas().getHeight());
            selectedBuilding.drawBuilding(selectedBuilding.getCanvas());

            update();
        } catch (NullPointerException e) {
            System.out.println("Pas de Building selectionné");
        }
    }

    @FXML
    private void QuitGame(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void Testclick(MouseEvent event) {

        int mouseX = (int) event.getX() / 30;
        int mouseY = (int) event.getY() / 30;
        if (player.isAllowToPlaceWorker()) {
            board.addWorker(mouseY, mouseX);
            player.disallowToPlaceWorker();
        } else {
            board.addBuilding(selectedBuilding, mouseY, mouseX);
        }
        update();

    }

    @FXML
    private void MoveBuilding(MouseEvent event) {
        if (selectedBuilding != null) {
            int MouseX = (int) event.getX() / 30;
            int MouseY = (int) event.getY() / 30;
            ArrayList<Cell> previewShape = selectedBuilding.getPreviewsShape(board, MouseY, MouseX);
            for (Cell c : previewShape) {
                if (c.getBuildingType() == 'B') {
                    c.changeBuildingStatus('P');
                }
            }
            update();
            for (Cell c : previewShape) {
                if (c.getBuildingType() == 'P') {
                    c.changeBuildingStatus('B');
                }
            }
        }
    }

    @FXML
    private void NewGame(ActionEvent event) {
        newGame();
    }

    private void newGame() {
        round = new Round();
        player = new Player();
        board = new Board(round, player);

        GraphicsContext gc1 = main1.getGraphicsContext2D();
        gc1.clearRect(0, 0, 120, 120);
        GraphicsContext gc2 = main2.getGraphicsContext2D();
        gc2.clearRect(0, 0, 120, 120);
        GraphicsContext gc3 = main3.getGraphicsContext2D();
        gc3.clearRect(0, 0, 120, 120);
        GraphicsContext gc4 = main4.getGraphicsContext2D();
        gc4.clearRect(0, 0, 120, 120);
        GraphicsContext gc5 = main5.getGraphicsContext2D();
        gc5.clearRect(0, 0, 120, 120);

        Canvas[] canvas = {main1, main2, main3, main4, main5};

        gc1.setFill(Color.BLACK);
        gc1.strokeRect(0, 0, main1.getWidth(), main1.getHeight());
        gc2.setFill(Color.BLACK);
        gc2.strokeRect(0, 0, main2.getWidth(), main2.getHeight());
        gc3.setFill(Color.BLACK);
        gc3.strokeRect(0, 0, main3.getWidth(), main3.getHeight());
        gc4.setFill(Color.BLACK);
        gc4.strokeRect(0, 0, main4.getWidth(), main4.getHeight());
        gc5.setFill(Color.BLACK);
        gc5.strokeRect(0, 0, main5.getWidth(), main5.getHeight());

        int i = 0;
        for (Building b : player.getBuildings()) {
            b.buildingShape(0, 0);
            b.drawBuilding(canvas[i]);
            b.setCanvas(canvas[i]);
            i++;
        }

        board.drawBoard(GameBoard);
    }

    @FXML
    private void hand1(MouseEvent event) {
        for (Building b : player.getBuildings()) {
            if (b.getCanvas().equals(main1)) {
                selectedBuilding = b;
            }
        }
        if (!player.isAllowToReturnCard()) {
            GraphicsContext gc = selectedBuilding.getCanvas().getGraphicsContext2D();
            gc.clearRect(0, 0, 121, 121);

            selectedBuilding.drawBuilding(selectedBuilding.getCanvas());
            gc.setFill(Color.YELLOW);
            gc.strokeRect(0, 0, selectedBuilding.getCanvas().getWidth(), selectedBuilding.getCanvas().getHeight());
        } else {

            Building zBlockWhichPermormed = null;
            for (Building b : player.getBuildings()) {
                if (b.getCanvas().equals(main1)) {
                    selectedBuilding = b;
                }
            }
            player.setBuildingToReturn(selectedBuilding);
            player.disallowToReturnCard();
            zBlockRole();
        }
    }

    @FXML
    private void hand2(MouseEvent event) {
        for (Building b : player.getBuildings()) {
            if (b.getCanvas().equals(main2)) {
                selectedBuilding = b;
            }
        }
    }

    @FXML
    private void hand3(MouseEvent event) {
        for (Building b : player.getBuildings()) {
            if (b.getCanvas().equals(main3)) {
                selectedBuilding = b;
            }
        }
    }

    @FXML
    private void hand4(MouseEvent event) {
        for (Building b : player.getBuildings()) {
            if (b.getCanvas().equals(main4)) {
                selectedBuilding = b;
            }
        }
    }

    @FXML
    private void hand5(MouseEvent event) {
        for (Building b : player.getBuildings()) {
            if (b.getCanvas().equals(main5)) {
                selectedBuilding = b;
            }
        }
    }

    @FXML
    private void MethodeWorker(ActionEvent event) {
        player.allowToPlaceWorker();
    }

    private void zBlockRole() {
        Building chosenBuilding = player.getBuildingToReturn();
        player.putBuildingFromHandToPile(chosenBuilding);
    }

}
