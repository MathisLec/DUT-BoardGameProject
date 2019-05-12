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

    public abstract void buildingShape(int x, int y);

    protected abstract void buildingRole();

    public abstract void rotateBuilding();

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
