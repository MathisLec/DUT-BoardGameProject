/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardgameproject.Buildings;

/**
 *
 * @author mlecoeuvre
 */
public abstract class Building {

    protected int id;
    protected char role;
    protected int nbWorkers;
    protected int energyConsume;

    public void buildingShape() {

    }

    protected void buildingRole() {

    }

    public void rotateBuilding() {

    }

    public int nbWorkerIn() {
        return 0;
    }

    public int getEnergyConsume() {
        return energyConsume;
    }

}
