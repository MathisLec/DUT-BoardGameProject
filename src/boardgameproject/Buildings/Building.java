/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardgameproject.Buildings;

import boardgameproject.Board;
import boardgameproject.Cell;
import java.util.ArrayList;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

/**
 *
 * @author mlecoeuvre
 */
public abstract class Building {

    protected char role;
    protected int nbWorkers;
    protected int energyCost;
    protected int energyConsume;
    protected ArrayList<Cell> cells;
    // L'axe des abscisses est représenté par x et l'axe des ordonnées par y
    protected int origineX, origineY;
    protected State state;
    protected Board board;

    public Building() {
        this.nbWorkers = 0;
        this.cells = new ArrayList<>();
        this.state = State.TOP;
        this.board = null;
    }

    public Building(Board board) {
        this.nbWorkers = 0;
        this.cells = new ArrayList<>();
        this.state = State.TOP;
        this.board = board;
    }

    public Building(ArrayList<Cell> list) {
        this.nbWorkers = 0;
        this.cells = list;
        this.state = State.TOP;
    }

    public abstract void buildingShape(int x, int y);

    protected abstract void buildingRole();

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
            s.drawCell(c, s.getX(), s.getY(),Color.WHITE);
        }
    }

    public int getNbWorkerIn() {
        return nbWorkers;
    }

    public int getEnergyConsume() {
        return energyConsume;
    }

    public void addWorker() {
        nbWorkers++;
    }

    public void removeWorker() {
        nbWorkers--;
    }

    public ArrayList<Cell> getCells() {
        return cells;
    }

    // Faire attention ici car l'axe des abscisses et des ordonnées sont inversés dans board
    public abstract ArrayList<Cell> getPreviewsShape(int x, int y) throws ArrayIndexOutOfBoundsException;

}
