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

    public Building() {
        this.cells = new ArrayList<>();
        this.state = State.TOP;
        this.isSelectedBuilding = false;
        buildingShape(origineX, origineY);
    }

    public abstract void buildingShape(int x, int y);

    public abstract void buildingRole(Player player, Board board, Round round) throws InsufficientRessourcesException;

    public char getRole() {
        return role;
    }

    public abstract void rotateBuildingLeft();

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

    public abstract void rotateBuildingRight();

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

    public void drawBuilding(Canvas c) {
        for (Cell s : cells) {
            s.drawCell(c, s.getX(), s.getY(), Color.WHITE);
        }
    }

    public int getMaterialCost() {
        return materialCost;
    }

    public int getNbWorker() {
        int nbWorkers = 0;
        for (Cell c : cells) {
            if (c.hasWorker()) {
                nbWorkers++;
            }
        }
        return nbWorkers;
    }

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

    public void clearWorkers() {
        for (Cell c : cells) {
            if (c.hasWorker()) {
                c.setHasNotWorkerStatus();
            }
        }
    }

    public ArrayList<Cell> getCells() {
        return cells;
    }

    public void setToSelectedBuilding() {
        isSelectedBuilding = true;
    }

    public void unsetToSelectedBuilding() {
        isSelectedBuilding = false;
    }

    public boolean isSelectedBuilding() {
        return isSelectedBuilding;
    }

    //Warning here, vertical and horizontal axis are reversed (x is vertical axe and y is horizontal axe)
    public abstract ArrayList<Cell> getPreviewsShape(Board board, int x, int y) throws ArrayIndexOutOfBoundsException;

    public void putPreviewsCellsInList(Board board, int x, int y) {
        cells.addAll(getPreviewsShape(board, x, y));
    }
}
