/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardgameproject;

import boardgameproject.Buildings.Building;
import boardgameproject.Exceptions.InsufficientRessourcesException;
import boardgameproject.Exceptions.InvalidLocationException;
import java.io.Serializable;
import java.util.ArrayList;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

/**
 *
 * @author mlecoeuvre
 */
public final class Board implements Serializable {

    private Player player;
    private final Round round;
    public final Cell[][] board;
    private final ArrayList<Building> buildings;

    public Board(Round round, Player player) {
        this.board = new Cell[20][10];
        this.round = round;
        this.player = player;
        this.buildings = new ArrayList<>();
        initializeBoard();
    }

    public void initializeBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new Cell(i, j);
            }
        }
    }

    public void drawBoard(Canvas c) {
        for (Cell cell : boardToList()) {
            cell.drawCell(c, cell.getY(), cell.getX(), Color.WHITE);
        }
    }

    public void addBuilding(Building building, int x, int y) throws InvalidLocationException {
        if (checkAddBuilding(building, x, y)) {
            for (Cell c : building.getPreviewsShape(this, x, y)) {
                c.changeBuildingStatus(building);
            }
            building.putPreviewsCellsInList(this, x, y);
            buildings.add(building);
            player.placeBuilding(building);
        } else {
            throw new InvalidLocationException();
        }

    }

    public void addWorker(int x, int y) throws InvalidLocationException {
        try {
            // x is vertical axe and y is horizontal axe
            Cell selectedCell = getCell(x, y);
            if (checkAddWorker(selectedCell)) {
                selectedCell.changeWorkerStatus();
                player.placeWorker();
            } else {
                throw new InvalidLocationException();
            }
        } catch (NullPointerException ex) {
            System.out.println("Emplacement non valide");
        }
    }

    public void removeWorker(int x, int y) {
        // x is vertical axe and y is horizontal axe
        getCell(x, y).changeWorkerStatus();
    }

    private boolean checkAddBuilding(Building building, int x, int y) {
        boolean isValid = true;
        for (Cell c : building.getPreviewsShape(this, x, y)) {
            if (c.hasBuilding()) {
                isValid = false;
            }
        }
        if (player.getNbMaterials() < building.getMaterialCost()) {
            isValid = false;
        }
        return isValid;
    }

    private boolean checkAddWorker(Cell cell) {
        return !cell.hasWorker()
                && player.getNbWorkers() > 0
                && cell.hasBuilding();
    }

    private void removeWorker(Cell cell) {
        cell.changeWorkerStatus();
    }

    public Cell[][] getBoard() {
        return board;
    }

    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public ArrayList<Cell> boardToList() {
        ArrayList<Cell> cells = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                cells.add(board[i][j]);
            }
        }
        return cells;
    }

    public void endTurn() {
        for (Building b : buildings) {
            for (int i = 0; i < b.getNbWorker(); i++) {
                try {
                    b.buildingRole(player, this, round);
                } catch (InsufficientRessourcesException ex) {
                    //Remove a worker from the board and return it to the player's hand
                    b.removeWorker();
                    player.addWorkerInHand(1);
                }
            }
        }
        //Draw a building if the hand is empty
        if (player.getBuildings().isEmpty()) {
            player.drawBuilding();
        }
    }

    public void changePlayer(Player player) {
        this.player = player;
    }

    public Cell getCell(int x, int y) {
        Cell cell = null;
        for (Cell c : boardToList()) {
            if (c.getX() == x && c.getY() == y) {
                cell = c;
            }
        }
        if (cell == null) {
            throw new NullPointerException();
        }
        return cell;
    }

    public int getScore() {
        int score = 0;
        final int nbPointsWorkerInHand = 2;
        final int nbPointWorkerOnBoard = 4;
        final int nbPointBuildingOnBoard = 8;
        final int nbPointResearch = 4;
        final int nbPointTurnSpacePort = 2;

        // Material and Energy = 1 Point
        score += player.getNbMaterials() + player.getNbEnergy();
        // Worker in hand = 2 Points
        for (int i = 0; i < player.getNbWorkers(); i++) {
            score += nbPointsWorkerInHand;
        }
        // Worker on Board = 4 Points
        for (Building b : buildings) {
            for (int i = 0; i < b.getNbWorker(); i++) {
                score += nbPointWorkerOnBoard;
            }
        }
        // Building on Board = 8 Points
        for (Building b : buildings) {
            score += nbPointBuildingOnBoard;
        }
        // Money = 1 Point
        score += player.getNbMoney();
        // Research = 4 Points
        for (int i = 0; i < player.getNbResearch(); i++) {
            score += nbPointResearch;
        }
        // SpacePort = 2 Points
        for (int i = 0; i < player.getNbTurnSpacePort(); i++) {
            score += nbPointTurnSpacePort;
        }
        return score;
    }

}
