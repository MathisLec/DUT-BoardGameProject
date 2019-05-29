/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardgameproject.Buildings;

import boardgameproject.Board;
import boardgameproject.Cell;
import boardgameproject.Player;
import java.util.ArrayList;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

/**
 *
 * @author mlecoeuvre
 */
public abstract class Building {

    protected char role;
    protected int materialCost;
    protected int energyConsume;
    protected ArrayList<Cell> cells;
    // L'axe des abscisses est représenté par x et l'axe des ordonnées par y
    protected int origineX, origineY;
    protected State state;
    protected boolean selectedBuilding;
    protected Canvas canvas;

    public Building() {
        this.cells = new ArrayList<>();
        this.state = State.TOP;
        this.canvas = null;
        this.selectedBuilding = false;
    }

    public Building(ArrayList<Cell> list) {
        this.cells = list;
        this.state = State.TOP;
        this.selectedBuilding = false;
    }

    public abstract void buildingShape(int x, int y);

    public abstract void buildingRole(Player player, Board board);

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

    
    
    public int getEnergyConsume() {
        return energyConsume;
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

    public void clearWorkers() {
        for (Cell c : cells) {
            if (c.hasWorker()) {
                c.changeWorkerStatus();
            }
        }
    }

    public ArrayList<Cell> getCells() {
        return cells;
    }
    
    public void setSelectedBuilding(boolean selectedBuilding) {
        this.selectedBuilding = selectedBuilding;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public boolean isSelectedBuilding() {
        return selectedBuilding;
    }

    // Faire attention ici car l'axe des abscisses et des ordonnées sont inversés dans board
    public abstract ArrayList<Cell> getPreviewsShape(Board board, int x, int y) throws ArrayIndexOutOfBoundsException;

    public void putPreviewsCellsInList(Board board, int x, int y) {
        cells.addAll(getPreviewsShape(board, x, y));
    }
}
