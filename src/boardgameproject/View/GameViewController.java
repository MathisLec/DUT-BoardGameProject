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
import java.util.logging.Level;
import java.util.logging.Logger;
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

    private Round round = new Round();
    Player player = new Player();
    Board board = new Board(round, player);

    Building selectedBuilding;

    Cell selectedWorker;

    HashMap<Canvas, Building> buildingsInHand = new HashMap<>();

    @FXML
    private Canvas GameBoard, main1, main2, main3, main4, main5;
    @FXML
    private Label nbEnergyLabel;
    @FXML
    private Label nbMaterialsLabel;
    @FXML
    private Label nbWorkersLabel;
    @FXML
    private Label nbTurn;
    @FXML
    private Button HelpButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (!MenuViewController.mustResume) {
            newGame();
        } else {
            resumeGame();
        }
        update();
    }

    public void update() {
        // Clear the board
        GraphicsContext gc = GameBoard.getGraphicsContext2D();
        gc.clearRect(0, 0, 1500, 1500);
        // Draw the board
        Color color = Color.WHITE;
        for (Cell c : board.boardToList()) {
            color = colorSelector(c, color);
            c.drawCell(GameBoard, c.getY(), c.getX(), color);
            if (c.hasWorker()) {
                gc.setFill(Color.BLACK);
                gc.fillOval(c.getY() * c.getCellShape(), c.getX() * c.getCellShape(), c.getCellShape(), c.getCellShape());
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
        Canvas[] canvas = {main1, main2, main3, main4, main5};
        for (int i = 0; i < canvas.length; i++) {
            Building b;
            try {
                b = player.getBuildings().get(i);
                b.drawBuilding(canvas[i]);
            } catch (IndexOutOfBoundsException ex) {
                b = null;
            }
            buildingsInHand.put(canvas[i], b);
        }
    }

    @FXML
    private void endTurn(ActionEvent event) {
        round.endTurn();
        board.endTurn();
        round.setPutBuilding(true);
        update();
    }

    @FXML
    private void RotateRight(ActionEvent event) {
        try {
            GraphicsContext gc = getAssociatedCanvas(selectedBuilding).getGraphicsContext2D();
            gc.clearRect(0, 0, 121, 121);
            selectedBuilding.rotateBuildingRight();
            gc.setStroke(Color.BLACK);
            gc.strokeRect(0, 0, getAssociatedCanvas(selectedBuilding).getWidth(), getAssociatedCanvas(selectedBuilding).getHeight());
            selectedBuilding.drawBuilding(getAssociatedCanvas(selectedBuilding));

            update();
        } catch (NullPointerException e) {
            System.err.println("Pas de Building selectionné");
        }
    }

    @FXML
    private void RotateLeft(ActionEvent event) {
        try {
            GraphicsContext gc = getAssociatedCanvas(selectedBuilding).getGraphicsContext2D();
            gc.clearRect(0, 0, 120, 120);
            selectedBuilding.rotateBuildingLeft();
            gc.setStroke(Color.BLACK);
            gc.strokeRect(0, 0, getAssociatedCanvas(selectedBuilding).getWidth(), getAssociatedCanvas(selectedBuilding).getHeight());
            selectedBuilding.drawBuilding(getAssociatedCanvas(selectedBuilding));

            update();
        } catch (NullPointerException e) {
            System.err.println("Pas de Building selectionné");
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
        try {
            if (player.isAllowToPlaceWorker()) {
                board.addWorker(mouseY, mouseX);
                player.decreaseNbWorkerToPlace();
                player.disallowToPlaceWorker();
            } else {
                if (board.getCell(mouseY, mouseX).hasWorker()) {
                    selectedWorker = board.getCell(mouseY, mouseX);
                }
                if (selectedWorker != null && selectedWorker.hasWorker() && !board.getCell(mouseY, mouseX).hasWorker()) {
                    board.removeWorker(selectedWorker.getY(), selectedWorker.getX());
                    board.addWorker(mouseY, mouseX);
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
    private void MoveBuilding(MouseEvent event) {
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
    private void NewGame(ActionEvent event) {
        newGame();
    }

    private void newGame() {
        round = new Round();
        player = new Player();
        board = new Board(round, player);

        board.drawBoard(GameBoard);
    }

    @FXML
    private void hand1(MouseEvent event) {
        if (round.getPutBuilding()) {
            selectedBuilding = buildingsInHand.get(main1);
            if (player.isAllowToReturnBuilding() && selectedBuilding != null) {
                zBlockRole(selectedBuilding);
            }
        }
        update();
    }

    @FXML
    private void hand2(MouseEvent event) {
        if (round.getPutBuilding()) {
            selectedBuilding = buildingsInHand.get(main2);
            if (player.isAllowToReturnBuilding() && selectedBuilding != null) {
                zBlockRole(selectedBuilding);
            }
        }
        update();
    }

    @FXML
    private void hand3(MouseEvent event) {
        if (round.getPutBuilding()) {
            selectedBuilding = buildingsInHand.get(main3);
            if (player.isAllowToReturnBuilding() && selectedBuilding != null) {
                zBlockRole(selectedBuilding);
            }
        }
        update();
    }

    @FXML
    private void hand4(MouseEvent event) {
        if (round.getPutBuilding()) {
            selectedBuilding = buildingsInHand.get(main4);
            if (player.isAllowToReturnBuilding() && selectedBuilding != null) {
                zBlockRole(selectedBuilding);
            }
        }
        update();
    }

    @FXML
    private void hand5(MouseEvent event) {
        if (round.getPutBuilding()) {
            if (buildingsInHand.get(main5) != null) {
                selectedBuilding = buildingsInHand.get(main5);
            }
            if (player.isAllowToReturnBuilding() && selectedBuilding != null) {
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
    private void MethodeWorker(ActionEvent event) {
        if (player.getNbEnergy() > 0) {
            player.allowToPlaceWorker();
        }
    }

    private void zBlockRole(Building building) {
        player.putBuildingFromHandToPile(building);
        player.disallowToReturnBuilding();
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
    private void openHelp(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("HelpView.fxml"));

        Scene scene = new Scene(root);
        scene.setRoot(root);

        stage.setScene(scene);
        stage.show();
    }

}
