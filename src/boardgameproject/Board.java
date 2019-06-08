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
import java.util.HashMap;
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

    /**
     * Constructor
     */
    public Board(Round round, Player player) {
        this.board = new Cell[20][10];
        this.round = round;
        this.player = player;
        this.buildings = new ArrayList<>();
        initializeBoard();
    }

    /**
     * This method initialize all cells of the board
     */
    private void initializeBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new Cell(i, j);
            }
        }
    }

    /**
     * This method will draw the board
     *
     * @param c The Canvas where the board is
     * @param cellSize the size of the cells
     */
    public void drawBoard(Canvas c, int cellSize) {
        for (Cell cell : boardToList()) {
            cell.drawCell(c, cell.getY(), cell.getX(), Color.WHITE, cellSize);
        }
    }

    /**
     * This method will add a building to the board
     *
     * @param building the building which will be placed on the board
     * @param x his posX
     * @param y his posY
     * @throws InsufficientRessourcesException This Exception will occur if the
     * player doesn't have enough ressources
     * @throws InvalidLocationException This Exception will occur if the player
     * doesn't place the building well
     */
    public void addBuilding(Building building, int x, int y) throws
            InsufficientRessourcesException, InvalidLocationException {
        try {
            if (checkAddBuilding(building, x, y)) {
                for (Cell c : building.getPreviewsShape(this, x, y)) {
                    c.changeBuildingStatus(building);
                }
                building.putPreviewsCellsInList(this, x, y);
                buildings.add(building);
                player.placeBuilding(building);
            }
        } catch (InsufficientRessourcesException ex) {
            System.err.println("Ressources insuffisantes");
            throw new InsufficientRessourcesException();
        } catch (InvalidLocationException ex) {
            System.err.println("Emplacement non valide");
            throw new InvalidLocationException();
        }
    }

    /**
     * This method will permit the player to place a worker in a building
     *
     * @param x PosX of the worker
     * @param y PosY of the worker
     * @throws InvalidLocationException This Exception will occur if the player
     * doesn't place the worker well
     */
    public void addWorker(int x, int y) throws InvalidLocationException {
        try {
            // x is vertical axe and y is horizontal axe
            Cell selectedCell = getCell(x, y);
            if (checkAddWorker(selectedCell)) {
                selectedCell.setHasWorkerStatus();
                player.placeWorker();
                player.decreaseNbWorkerToPlace();
            } else {
                player.disallowToPlaceWorker();
                throw new InvalidLocationException();
            }
        } catch (NullPointerException ex) {
            System.out.println("Emplacement non valide");
            player.disallowToPlaceWorker();
        }
    }

    public void moveWorker(int x, int y) throws InvalidLocationException {
        try {
            // x is vertical axe and y is horizontal axe
            Cell selectedCell = getCell(x, y);
            if (checkAddWorker(selectedCell)) {
                selectedCell.setHasWorkerStatus();
                player.decreaseNbWorkerToPlace();
            } else {
                player.disallowToPlaceWorker();
                throw new InvalidLocationException();
            }
        } catch (NullPointerException ex) {
            System.out.println("Emplacement non valide");
            player.disallowToPlaceWorker();
        }
    }

    /**
     * This worker will remove a worker if the player doesn't have enough
     * ressources to trigger the action of the building
     *
     * @param x PosX of the worker
     * @param y PosY of the worker
     */
    public void removeWorker(int x, int y) {
        // x is vertical axe and y is horizontal axe
        getCell(x, y).setHasNotWorkerStatus();
    }

    /**
     * This method will check if you can place a building to the location ur
     * trying to build it
     *
     * @param building The building the player is trying to build
     * @param x his PosX
     * @param y his PoY
     * @return it will return true if you can build it and false if not
     * @throws InsufficientRessourcesException This Exception will occur if the
     * player doesn't have enough ressources
     * @throws InvalidLocationException This Exception will occur if the player
     * doesn't place the building well
     */
    private boolean checkAddBuilding(Building building, int x, int y) throws
            InvalidLocationException, InsufficientRessourcesException {
        boolean isValid = true;
        for (Cell c : building.getPreviewsShape(this, x, y)) {
            if (c.hasBuilding()) {
                isValid = false;
                throw new InvalidLocationException();
            }
        }
        if (player.getNbMaterials() < building.getMaterialCost()) {
            isValid = false;
            throw new InsufficientRessourcesException();
        }
        return isValid;
    }

    /**
     * this will verify if u can add a worker to a building
     *
     * @param cell the cell u try to add a worker
     * @return if u can place it on this cell
     */
    private boolean checkAddWorker(Cell cell) {
        return !cell.hasWorker()
                && player.getNbWorkers() > 0
                && cell.hasBuilding();
    }

    /**
     *
     * @return the GameBoard
     */
    public Cell[][] getBoard() {
        return board;
    }

    /**
     *
     * @return the buildings of the Board
     */
    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    /**
     *
     * @return the Cells of the Board
     */
    public ArrayList<Cell> boardToList() {
        ArrayList<Cell> cells = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                cells.add(board[i][j]);
            }
        }
        return cells;
    }

    /**
     * This method trigger all the roles of the building and the workers on the
     * board
     */
    public void endTurn() {
        player.setNbWorkerToPlaceByDefault();
        for (Building b : buildings) {
            for (int i = 0; i < b.getNbWorker(); i++) {
                try {
                    b.buildingRole(player, this, round);
                } catch (InsufficientRessourcesException ex) {
                    //Remove a worker from the board and return it to the player's hand
                    player.addWorkerInHand(b.getNbWorker());
                    b.clearWorkers();
                }
            }
        }
        //Draw a building if the hand is empty
        if (player.getBuildings().isEmpty()) {
            player.drawBuilding();
        }
    }

    /**
     *
     * @param x posX of the Cell
     * @param y posY of the Cell
     * @return a Cell from the Board
     */
    public Cell getCell(int x, int y) {
        Cell cell = null;
        for (Cell c : boardToList()) {
            if (c.getX() == x && c.getY() == y) {
                cell = c;
            }
        }
        return cell;
    }

    /**
     *
     * @return the Score of the player
     */
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

    /**
     * Return an HashMap with in key the letter associate to the building and in
     * value, the number of it letter
     *
     * @return
     */
    public HashMap<Character, Integer> getNbBuildingsOfEachType() {
        HashMap<Character, Integer> nbBuildings = new HashMap<>();
        for (Building b : buildings) {
            if (!nbBuildings.containsKey(b.getRole())) {
                nbBuildings.put(b.getRole(), 1);
            } else {
                int nb = nbBuildings.get(b.getRole());
                nb++;
                nbBuildings.put(b.getRole(), nb);
            }
        }
        return nbBuildings;
    }

    /**
     * return the number of workers on the board
     *
     * @return
     */
    public int getNbWorkerOnBoard() {
        int nb = 0;
        for (Building b : buildings) {
            nb += b.getNbWorker();
        }
        return nb;
    }

}
