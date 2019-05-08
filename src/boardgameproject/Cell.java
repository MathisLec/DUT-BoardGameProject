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

    private boolean hasWorker;
    private boolean hasBuilding;
    private Building buildingType;
    private int x, y;

    public Cell() {
        this.hasWorker = false;
        this.hasBuilding = false;
        this.buildingType = null;
        this.x = 0;
        this.y = 0;
    }
    
        public Cell(int x, int y) {
        this.hasWorker = false;
        this.hasBuilding = false;
        this.buildingType = null;
        this.x = x;
        this.y = y;
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

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

}
