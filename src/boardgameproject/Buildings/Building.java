/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardgameproject.Buildings;

import boardgameproject.Board;
import boardgameproject.Cell;
import boardgameproject.Exceptions.InsufficientRessourcesException;
import boardgameproject.Player;
import boardgameproject.Round;
import java.io.Serializable;
import java.util.ArrayList;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

/**
 *
 * @author mlecoeuvre
 */
public abstract class Building implements Serializable {

    protected char role;
    protected int materialCost;
    protected ArrayList<Cell> cells;
    // Horizontal axe is x, Vertical axe is Y
    protected int origineX, origineY;
    protected State state;
    protected boolean isSelectedBuilding;

    /**
     * Constructor
     */
    public Building() {
        this.cells = new ArrayList<>();
        this.state = State.TOP;
        this.isSelectedBuilding = false;
        buildingShape(origineX, origineY);
    }

    /**
     * 
     * @param x
     * @param y
     */
    public abstract void buildingShape(int x, int y);

    /**
     * This method will give a role to the building
     * @param player
     * @param board
     * @param round
     * @throws InsufficientRessourcesException
     */
    public abstract void buildingRole(Player player, Board board, Round round) throws InsufficientRessourcesException;

    /**
     *
     * @return
     */
    public char getRole() {
        return role;
    }

    /**
     * This method will rotate the selected building to the left
     */
    public abstract void rotateBuildingLeft();

    /**
     * This method will change the state of the building when rotated 
     * to the left
     */
    protected void changeStateRotateLeft() {
        switch (state) {
            case TOP:
                state = State.LEFT;
                break;
            case BOTTOM:
                state = State.RIGHT;
                break;
            case LEFT:
                state = State.BOTTOM;
                break;
            case RIGHT:
                state = State.TOP;
                break;
        }
    }

    /**
     * This method will rotate the selected building to the right
     */
    public abstract void rotateBuildingRight();

    /**
     * This method will change the state of the building when rotated 
     * to the right
     */
    protected void changeStateRotateRight() {
        switch (state) {
            case TOP:
                state = State.RIGHT;
                break;
            case BOTTOM:
                state = State.LEFT;
                break;
            case LEFT:
                state = State.TOP;
                break;
            case RIGHT:
                state = State.BOTTOM;
                break;
        }
    }

    /**
     * this method will draw the building
     * @param c the Canvas where the building will be drawn
     * @param cellSize the size of the Building's Cells
     */
    public void drawBuilding(Canvas c, int cellSize) {
        for (Cell s : cells) {
            s.drawCell(c, s.getX(), s.getY(), Color.WHITE, cellSize);
        }
    }

    /**
     *
     * @return The Material cost of the building
     */
    public int getMaterialCost() {
        return materialCost;
    }

    /**
     *
     * @return the number of workers in a building
     */
    public int getNbWorker() {
        int nbWorkers = 0;
        for (Cell c : cells) {
            if (c.hasWorker()) {
                nbWorkers++;
            }
        }
        return nbWorkers;
    }

    /**
     * remove the worker from the building if the player doesn't have enough 
     * ressources to trigger the action
     */
    public void removeWorker() {
        int limit = 1;
        int currentCounter = 0;
        for (Cell c : cells) {
            // Check if a worker has already been removed
            if (c.hasWorker() && currentCounter < limit) {
                c.setHasNotWorkerStatus();
                currentCounter++;
            }
        }
    }

    /**
     * Clear the building of all his workers
     */
    public void clearWorkers() {
        for (Cell c : cells) {
            if (c.hasWorker()) {
                c.setHasNotWorkerStatus();
            }
        }
    }

    /**
     *
     * @return the current building's list of cells
     */
    public ArrayList<Cell> getCells() {
        return cells;
    }

    /**
     * set the selectedBuilding attribute to true 
     */
    public void setToSelectedBuilding() {
        isSelectedBuilding = true;
    }

    /**
     * set the selectedBuilding attribute to false 
     */
    public void unsetToSelectedBuilding() {
        isSelectedBuilding = false;
    }

    /**
     *
     * @return true if the building is the selected One
     */
    public boolean isSelectedBuilding() {
        return isSelectedBuilding;
    }

    //Warning here, vertical and horizontal axis are reversed (x is vertical axe and y is horizontal axe)
    /**
     * This method will return the positions of the cells where we will build the selected building
     * @param board the gameboard
     * @param x the PosX of the building
     * @param y the PosY of the building 
     * @return
     * @throws ArrayIndexOutOfBoundsException
     */
    public abstract ArrayList<Cell> getPreviewsShape(Board board, int x, int y) throws ArrayIndexOutOfBoundsException;

    /**
     * put the previews cells in a list
     * @param board gameboard
     * @param x the PosX of the building
     * @param y the PosY of the building 
     */
    public void putPreviewsCellsInList(Board board, int x, int y) {
        cells.addAll(getPreviewsShape(board, x, y));
    }
}
