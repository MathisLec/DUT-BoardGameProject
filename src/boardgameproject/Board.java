/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardgameproject;

import boardgameproject.Buildings.Building;
import java.util.ArrayList;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

/**
 *
 * @author mlecoeuvre
 */
public final class Board {

    private final Player player;
    private final Round round;
    private final Cell[][] board;
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

    public void addBuilding(Building building, int x, int y) {
        if (checkAddBuilding(building, x, y)) {
            for (Cell c : building.getPreviewsShape(x, y)) {
                c.changeBuildingStatus(building);
            }
            buildings.add(building);
        }
    }

    public void addWorker(Cell cell) {
        if (checkAddWorker(cell)) {
            cell.changeWorkerStatus();
        }
    }

    private boolean checkAddBuilding(Building building, int x, int y) {
        boolean isValid = true;
        for (Cell c : building.getPreviewsShape(x, y)) {
            if (c.hasBuilding()) {
                isValid = false;
            }
        }
        return isValid;
    }

    private boolean checkAddWorker(Cell cell) {
        return !cell.hasWorker()
                && player.getNbWorkers() > 0
                && cell.hasBuilding();
    }

    public void checkRemoveWorker() {
        for (Cell c : boardToList()) {
            if (c.hasWorker()) {
                if (c.getBuildingType().getEnergyConsume() > player.getNbEnergy()) {
                    removeWorker(c);
                }
            }
        }
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
            b.buildingRole(player, this);
        }
    }
}
