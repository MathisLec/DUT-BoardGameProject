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

    public void addBuilding(Building building, int x, int y) {
        if (checkAddBuilding(building, x, y)) {
            for (Cell c : building.getPreviewsShape(this, x, y)) {
                c.changeBuildingStatus(building);
            }
            building.putPreviewsCellsInList(this, x, y);
            buildings.add(building);
            player.placeBuilding(building);
        }
    }

    public void addWorker(int x, int y) {
        try {
            Cell selectedCell = getCell(x, y);
            if (checkAddWorker(selectedCell)) {
                selectedCell.changeWorkerStatus();
                player.placeWorker();
            } else {
                System.out.println("Marche pas");
            }
        } catch (NullPointerException ex) {
            System.out.println("Emplacement non valide");
        }
    }

    private boolean checkAddBuilding(Building building, int x, int y) {
        boolean isValid = true;
        for (Cell c : building.getPreviewsShape(this, x, y)) {
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
//                if (c.getBuildingType().getEnergyConsume() > player.getNbEnergy()) {
//                    removeWorker(c);
//                }
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
            if (player.getNbEnergy() > b.getEnergyConsume() && b.getNbWorker() > 0) {
                b.buildingRole(player, this);
            } else {
                player.addWorkerInHand(b.getNbWorker());
                b.clearWorkers();
            }
        }
    }

    public void changePlayer(Player player) {
        this.player = player;
    }

    private Cell getCell(int x, int y) {
        Cell cell = null;
        for (Cell c : boardToList()) {
            if (c.getX() == y && c.getY() == x) {
                cell = c;
            }
        }
        if (cell == null) {
            throw new NullPointerException();
        }
        return cell;
    }

}
