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

    /**
     * launch a new game
     */
    private void newGame() {
        round = new Round();
        player = new Player();
        board = new Board(round, player);

        board.drawBoard(GameBoard, cellSize);
    }

    /**
     * update the game each time an action is performed
     */
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

    /**
     * This method will return a color for each cell of the gameboard
     *
     * @param cell the current cell
     * @param color the default color
     * @return
     */
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

    /**
     * draw an red rectangle around the selected building
     */
    private void drawStrokes() {
        for (Building b : buildingsInHand.values()) {
            GraphicsContext gc1 = getAssociatedCanvas(b).getGraphicsContext2D();
            gc1.clearRect(0, 0, 125, 125);
            gc1.setLineWidth(1);
            if (b == selectedBuilding && b != null) {
                gc1.setStroke(Color.RED);
                gc1.strokeRect(0, 0, getAssociatedCanvas(b).getWidth(),
                        getAssociatedCanvas(b).getHeight());
            }
        }
    }

    /**
     * draw the buildings in the canvas which represent the player's hand
     */
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

    /**
     * This method will permit the player to change a building from his hand
     * with a new one from the pile depending of how much workers the ZBlock has
     * in
     *
     * @param building
     */
    private void zBlockRole(Building building) {
        player.putBuildingFromHandToPile(building);
        player.drawBuilding();
        player.decreaseNbBuildingToReturn();
        selectedBuilding = null;
    }

    /**
     * This method will rotate the selected building to the right by clicking on
     * the rotateRight button
     */
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

    /**
     * This method will rotate the selected building to the left by clicking on
     * the rotateLeft button
     */
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

    /**
     * This method allow the player to interact with the gameBoard by clicking
     * on it
     */
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
                    board.moveWorker(mouseY, mouseX);
                    selectedWorker = null;
                }
                if (selectedBuilding != null && !selectedBuilding.getPreviewsShape(board, mouseY, mouseX).isEmpty()) {
                    try {
                        board.addBuilding(selectedBuilding, mouseY, mouseX);
                        selectedBuilding = null;
                        round.setHasPlayerPlaceBuilding(false);
                    } catch (InsufficientRessourcesException | InvalidLocationException ex) {
                    }
                }
            }
        } catch (NullPointerException | InvalidLocationException ex) {
            System.err.println("Emplacement invalide");
        }
        update();

    }

    /**
     * This method allow the player to interact with the gameBoard by being over
     * it
     */
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

    /**
     * This is the buildingHand number 1 of the player
     */
    @FXML
    private void Hand1(MouseEvent event) {
        if (round.hasPlayerPlaceBuilding()) {
            selectedBuilding = buildingsInHand.get(hand1);
            if (player.getNbBuildingToReturn() > 0 && selectedBuilding != null) {
                zBlockRole(selectedBuilding);
            }
        }
        update();
    }

    /**
     * This is the buildingHand number 2 of the player
     */
    @FXML
    private void Hand2(MouseEvent event) {
        if (round.hasPlayerPlaceBuilding()) {
            selectedBuilding = buildingsInHand.get(hand2);
            if (player.getNbBuildingToReturn() > 0 && selectedBuilding != null) {
                zBlockRole(selectedBuilding);
            }
        }
        update();
    }

    /**
     * This is the buildingHand number 3 of the player
     */
    @FXML
    private void Hand3(MouseEvent event) {
        if (round.hasPlayerPlaceBuilding()) {
            selectedBuilding = buildingsInHand.get(hand3);
            if (player.getNbBuildingToReturn() > 0 && selectedBuilding != null) {
                zBlockRole(selectedBuilding);
            }
        }
        update();
    }

    /**
     * This is the buildingHand number 4 of the player
     */
    @FXML
    private void Hand4(MouseEvent event) {
        if (round.hasPlayerPlaceBuilding()) {
            selectedBuilding = buildingsInHand.get(hand4);
            if (player.getNbBuildingToReturn() > 0 && selectedBuilding != null) {
                zBlockRole(selectedBuilding);
            }
        }
        update();
    }

    /**
     * This is the buildingHand number 5 of the player
     */
    @FXML
    private void Hand5(MouseEvent event) {
        if (round.hasPlayerPlaceBuilding()) {
            selectedBuilding = buildingsInHand.get(hand5);
            if (player.getNbBuildingToReturn() > 0 && selectedBuilding != null) {
                zBlockRole(selectedBuilding);
            }
        }
        update();
    }

    /**
     * This will return the Canvas where the building needs to be drawn
     *
     * @param building
     * @return
     */
    private Canvas getAssociatedCanvas(Building building) {
        Canvas canvasToReturn = null;
        for (Canvas c : buildingsInHand.keySet()) {
            if (buildingsInHand.get(c) == building) {
                canvasToReturn = c;
            }
        }
        return canvasToReturn;
    }

    /**
     * This method allow the player to place a worker by clicking on the
     * placeAWorker button if he still has movement left
     */
    @FXML
    private void placeAWorker(ActionEvent event) {
        if (player.getNbWorkerToPlace() > 0 && !board.getBuildings().isEmpty()) {
            player.allowToPlaceWorker();
        }
    }

    /**
     * This method permits the player to resume his game by clicking on the
     * Resume button on the menu
     */
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

    /**
     * This method permits the player to save his current game
     */
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

    /**
     * This method will display the Help menu by clicking on the Help button
     */
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

    /**
     * This method will display the Score menu by clicking on the Score button
     */
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

    /**
     * This button triggers the end of the turn actions and end the program if
     * the game is finished
     */
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
            round.setHasPlayerPlaceBuilding(true);
            update();
        }
    }

    /**
     * This method quit the program by clicking on the quit button
     */
    @FXML
    private void quitGame(ActionEvent event) {
        System.exit(0);
    }

}
