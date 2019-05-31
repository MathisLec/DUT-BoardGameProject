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
            // x is vertical axe and y is horizontal axe
            Cell selectedCell = getCell(y, x);
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
                if (player.getNbEnergy() >= b.getEnergyConsume()) {
                    b.buildingRole(player, this, round);
                    //LBlock  and TBlock have a special process to consumme energy
                    //It is manage in their class
                    if (b.getRole() != 'L' && b.getRole() != 'T') {
                        player.consummeEnergy(b.getEnergyConsume());
                    }
                } else {
                    //Remove a worker from the board and return it to the player's hand
                    b.removeWorker();
                    player.addWorkerInHand(1);
                }
            }
        }
        //Draw a building if the deck is empty
        if (player.getBuildings().isEmpty()) {
            player.drawBuilding();
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
