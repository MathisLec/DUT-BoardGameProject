/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardgameproject.Buildings;

import boardgameproject.Cell;
import java.util.ArrayList;

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
    State state;

    public Building() {
        this.nbWorkers = 0;
        this.cells = new ArrayList<>();
        this.state = State.TOP;
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

}
