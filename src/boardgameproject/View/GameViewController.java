/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardgameproject.View;

import boardgameproject.Board;
import boardgameproject.Buildings.Building;
import boardgameproject.Cell;
import boardgameproject.Exceptions.InsufficientRessourcesException;
import boardgameproject.Exceptions.InvalidLocationException;
import boardgameproject.Player;
import boardgameproject.Round;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author adesaleneuve
 */
public class GameViewController implements Initializable {

    static Round round = new Round();
    static Player player = new Player();
    static Board board = new Board(round, player);

    Building selectedBuilding;

    Cell selectedWorker;

    HashMap<Canvas, Building> buildingsInHand = new HashMap<>();

    public int cellSize = 30;
    @FXML
    private Canvas GameBoard, hand1, hand2, hand3, hand4, hand5;
    @FXML
    private Label nbEnergyLabel, nbMaterialsLabel, nbWorkersLabel, nbTurn, nbMoney,
            nbResearch, nbStellarium;
    @FXML
    private Button EndTurn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (!MenuViewController.mustResume) {
            newGame();
        } else {
            resumeGame();
        }
        update();
    }

    private void newGame() {
        round = new Round();
        player = new Player();
        board = new Board(round, player);

        board.drawBoard(GameBoard, cellSize);
    }

    public void update() {
        // Clear the board
        GraphicsContext gc = GameBoard.getGraphicsContext2D();
        gc.clearRect(0, 0, 1500, 1500);
        // Draw the board
        Color color = Color.WHITE;
        for (Cell c : board.boardToList()) {
            color = colorSelector(c, color);
            c.drawCell(GameBoard, c.getY(), c.getX(), color, cellSize);
            if (c.hasWorker()) {
                gc.setFill(Color.BLACK);
                gc.fillOval(c.getY() * cellSize + 5, c.getX() * cellSize + 5, 20, 20);
            }
        }
        // Clear and redraw Strokes
        drawStrokes();
        // Draw Buildings
        drawPlayerBuildingsInHandCanvas();
        // Set Labels
        nbTurn.setText("Tour : " + Integer.toString(round.getNbTurn()));
        nbEnergyLabel.setText(Integer.toString(player.getNbEnergy()));
        nbMaterialsLabel.setText(Integer.toString(player.getNbMaterials()));
        nbWorkersLabel.setText(Integer.toString(player.getNbWorkers()));
        nbMoney.setText(Integer.toString(player.getNbMoney()));
        nbResearch.setText(Integer.toString(player.getNbResearch()));
        nbStellarium.setText(Integer.toString(player.getNbStellarium()));

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
                color = Color.DEEPSKYBLUE;
                break;
        }
        return color;
    }

    private void drawStrokes() {
        for (Building b : buildingsInHand.values()) {
            GraphicsContext gc1 = getAssociatedCanvas(b).getGraphicsContext2D();
            gc1.clearRect(-2, -2, 125, 125);
            gc1.setLineWidth(1);
            if (b == selectedBuilding && b != null) {
                gc1.setStroke(Color.RED);
                gc1.strokeRect(0, 0, getAssociatedCanvas(b).getWidth(),
                        getAssociatedCanvas(b).getHeight());
            }
        }
    }

    private void drawPlayerBuildingsInHandCanvas() {
        Canvas[] canvas = {hand1, hand2, hand3, hand4, hand5};
        for (int i = 0; i < canvas.length; i++) {
            Building b;
            try {
                b = player.getBuildings().get(i);
                b.drawBuilding(canvas[i], cellSize);
            } catch (IndexOutOfBoundsException ex) {
                b = null;
            }
            buildingsInHand.put(canvas[i], b);
        }
    }

    private void zBlockRole(Building building) {
        player.putBuildingFromHandToPile(building);
        player.drawBuilding();
        player.decreaseNbBuildingToReturn();
        selectedBuilding = null;
    }

    @FXML
    private void rotateRight(ActionEvent event) {
        try {
            GraphicsContext gc = getAssociatedCanvas(selectedBuilding).getGraphicsContext2D();
            gc.clearRect(0, 0, 121, 121);
            selectedBuilding.rotateBuildingRight();
            gc.setStroke(Color.BLACK);
            gc.strokeRect(0, 0, getAssociatedCanvas(selectedBuilding).getWidth(), getAssociatedCanvas(selectedBuilding).getHeight());
            selectedBuilding.drawBuilding(getAssociatedCanvas(selectedBuilding), cellSize);

            update();
        } catch (NullPointerException e) {
            System.err.println("Pas de Building selectionné");
        }
    }

    @FXML
    private void rotateLeft(ActionEvent event) {
        try {
            GraphicsContext gc = getAssociatedCanvas(selectedBuilding).getGraphicsContext2D();
            gc.clearRect(0, 0, 120, 120);
            selectedBuilding.rotateBuildingLeft();
            gc.setStroke(Color.BLACK);
            gc.strokeRect(0, 0, getAssociatedCanvas(selectedBuilding).getWidth(), getAssociatedCanvas(selectedBuilding).getHeight());
            selectedBuilding.drawBuilding(getAssociatedCanvas(selectedBuilding), cellSize);

            update();
        } catch (NullPointerException e) {
            System.err.println("Pas de Building selectionné");
        }
    }

    @FXML
    private void clickOnBoard(MouseEvent event) {

        int mouseX = (int) event.getX() / 30;
        int mouseY = (int) event.getY() / 30;
        try {
            if (player.isAllowToPlaceWorker()) {
                board.addWorker(mouseY, mouseX);
                player.disallowToPlaceWorker();
            } else {
                if (board.getCell(mouseY, mouseX).hasWorker()) {
                    selectedWorker = board.getCell(mouseY, mouseX);
                }
                if (selectedWorker != null
                        && selectedWorker.hasWorker()
                        && !board.getCell(mouseY, mouseX).hasWorker()
                        && player.getNbWorkerToPlace() > 0
                        && selectedBuilding == null) {
                    // Here x is vertical axe and y is horizontal axe
                    board.removeWorker(selectedWorker.getX(), selectedWorker.getY());
                    // Here x is horizontal axe and y is vertical axe
                    board.addWorker(mouseY, mouseX);
                    selectedWorker = null;
                }
                if (selectedBuilding != null && !selectedBuilding.getPreviewsShape(board, mouseY, mouseX).isEmpty()) {
                    try {
                        board.addBuilding(selectedBuilding, mouseY, mouseX);
                        selectedBuilding = null;
                        round.setPutBuilding(false);
                    } catch (InsufficientRessourcesException | InvalidLocationException ex) {
                    }
                }
            }
        } catch (NullPointerException | InvalidLocationException ex) {
            System.err.println("Emplacement invalide");
        }
        update();

    }

    @FXML
    private void mouseHoverBoard(MouseEvent event) {
        if (selectedBuilding != null) {
            int MouseX = (int) event.getX() / 30;
            int MouseY = (int) event.getY() / 30;
            if (!player.isAllowToPlaceWorker()) {
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
    }

    @FXML
    private void Hand1(MouseEvent event) {
        if (round.getPutBuilding()) {
            selectedBuilding = buildingsInHand.get(hand1);
            if (player.getNbBuildingToReturn() > 0 && selectedBuilding != null) {
                zBlockRole(selectedBuilding);
            }
        }
        update();
    }

    @FXML
    private void Hand2(MouseEvent event) {
        if (round.getPutBuilding()) {
            selectedBuilding = buildingsInHand.get(hand2);
            if (player.getNbBuildingToReturn() > 0 && selectedBuilding != null) {
                zBlockRole(selectedBuilding);
            }
        }
        update();
    }

    @FXML
    private void Hand3(MouseEvent event) {
        if (round.getPutBuilding()) {
            selectedBuilding = buildingsInHand.get(hand3);
            if (player.getNbBuildingToReturn() > 0 && selectedBuilding != null) {
                zBlockRole(selectedBuilding);
            }
        }
        update();
    }

    @FXML
    private void Hand4(MouseEvent event) {
        if (round.getPutBuilding()) {
            selectedBuilding = buildingsInHand.get(hand4);
            if (player.getNbBuildingToReturn() > 0 && selectedBuilding != null) {
                zBlockRole(selectedBuilding);
            }
        }
        update();
    }

    @FXML
    private void Hand5(MouseEvent event) {
        if (round.getPutBuilding()) {
            selectedBuilding = buildingsInHand.get(hand5);
            if (player.getNbBuildingToReturn() > 0 && selectedBuilding != null) {
                zBlockRole(selectedBuilding);
            }
        }
        update();
    }

    private Canvas getAssociatedCanvas(Building building) {
        Canvas canvasToReturn = null;
        for (Canvas c : buildingsInHand.keySet()) {
            if (buildingsInHand.get(c) == building) {
                canvasToReturn = c;
            }
        }
        return canvasToReturn;
    }

    @FXML
    private void placeAWorker(ActionEvent event) {
        if (player.getNbWorkerToPlace() > 0 && !board.getBuildings().isEmpty()) {
            player.allowToPlaceWorker();
        }
    }

    private void resumeGame() {
        FileInputStream fis;
        ObjectInputStream ois;
        try {
            fis = new FileInputStream("save.ser");
            ois = new ObjectInputStream(fis);

            round = (Round) ois.readObject();
            player = (Player) ois.readObject();
            board = (Board) ois.readObject();
            ois.close();

        } catch (IOException ex) {
            System.out.println("Fichier non trouvé");
        } catch (ClassNotFoundException ex) {
            System.out.println("Fichier erroné");
        }
    }

    @FXML
    private void saveGame(ActionEvent event) {
        FileOutputStream fos;
        ObjectOutputStream oos;
        try {
            fos = new FileOutputStream("save.ser");
            oos = new ObjectOutputStream(fos);

            oos.writeObject(round);
            oos.writeObject(player);
            oos.writeObject(board);

            oos.close();
            System.out.println("Sauvegarde effectuée");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    private void displayHelp(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("HelpView.fxml"));

        Scene scene = new Scene(root);
        scene.setRoot(root);

        stage.setTitle("Help Menu");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void displayScore(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("ScoreView.fxml"));

        Scene scene = new Scene(root);
        scene.setRoot(root);

        stage.setTitle("Score Menu");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void endTurn(ActionEvent event) throws IOException {
        if (round.getNbTurn() > 29) {
            Stage stage2 = (Stage) EndTurn.getScene().getWindow();
            stage2.close();

            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("ScoreView.fxml"));

            Scene scene = new Scene(root);
            scene.setRoot(root);

            stage.setTitle("Fin de Partie");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } else {
            round.endTurn();
            board.endTurn();
            round.setPutBuilding(true);
            update();
        }
    }

    @FXML
    private void quitGame(ActionEvent event) {
        System.exit(0);
    }

}
