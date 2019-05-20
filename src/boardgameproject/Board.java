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

    private Player player;
    private final Round Round;
    private final Cell[][] Board;

    public Board(Round round, Player player) {
        this.Board = new Cell[20][10];
        this.Round = round;
        this.player = player;
        initializeBoard();
    }

    public void initializeBoard() {
        for (int i = 0; i < Board.length; i++) {
            for (int j = 0; j < Board[i].length; j++) {
                Board[i][j] = new Cell(i, j);
            }
        }
    }

    public void drawBoard(Canvas c) {
        for(Cell cell : boardToList()){
            cell.drawCell(c, cell.getY(), cell.getX(),Color.WHITE);
        }
    }

    public void addBuilding(Building building, int x, int y) {
        if (checkAddBuilding(building, x, y)) {
            for (Cell c : building.getPreviewsShape(x, y)) {
                c.changeBuildingStatus(building);
            }
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
        return Board;
    }

    public ArrayList<Cell> boardToList() {
        ArrayList<Cell> cells = new ArrayList<>();
        for (int i = 0; i < Board.length; i++) {
            for (int j = 0; j < Board[i].length; j++) {
                cells.add(Board[i][j]);
            }
        }
        return cells;
    }

}
