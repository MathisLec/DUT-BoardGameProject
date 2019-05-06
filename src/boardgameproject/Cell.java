/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardgameproject;

import boardgameproject.Buildings.Building;

/**
 *
 * @author mlecoeuvre
 */
public class Cell {

    private Integer idCell;
    private boolean hasWorker;
    private boolean hasBuilding;
    private Building buildingType;

    public Cell() {
        this.idCell = null;
        this.hasWorker = false;
        this.hasBuilding = false;
        this.buildingType = null;
    }

    public void setIdBuilding(Integer idCell) {
        this.idCell = idCell;
    }

    public void changeWorkerStatus() {
        hasWorker = !hasWorker;
    }

    public void changeBuildingStatus() {
        hasBuilding = !hasBuilding;
    }

    public boolean hasWorker() {
        return hasWorker;
    }

    public boolean hasBuilding() {
        return hasBuilding;
    }

    public Building getBuildingType() {
        return buildingType;
    }

}
