/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardgameproject;

import boardgameproject.Buildings.Building;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mlecoeuvre
 */
public class Player {

    private final int DEFAULT_NB_ENERGY = 16;
    private final int DEFAULT_NB_WORKERS = 8;
    private final int DEFAULT_NB_MATERIALS = 8;
    private final int NB_BUILDINGS_START = 5;
    private final int DEFAULT_NB_WORKER_TO_PLACE = 1;
    private final int LIMIT_NB_BUILDINGS_IN_HAND = 5;

    private int nbEnergy;
    private int nbWorkers;
    private int nbMaterials;
    private static Pile pile = new Pile();
    private List<Building> buildings;
    private int nbWorkerToPlace;
    private boolean isAllowToReturnBuilding;
    private Building buildingToReturn;

    public Player() {
        this.nbEnergy = DEFAULT_NB_ENERGY;
        this.nbWorkers = DEFAULT_NB_WORKERS;
        this.nbMaterials = DEFAULT_NB_MATERIALS;
        this.buildings = new ArrayList<>();
        this.nbWorkerToPlace = DEFAULT_NB_WORKER_TO_PLACE;
        this.isAllowToReturnBuilding = false;
        this.buildingToReturn = null;
        startingHand();
    }

    private void startingHand() {
        for (int i = 0; i < NB_BUILDINGS_START; i++) {
            drawBuilding();
        }
    }

    public void placeBuilding(Building building) {
        buildings.remove(building);
        nbMaterials -= building.getMaterialCost();
    }

    public void addWorkerInHand(int nb) {
        nbWorkers += nb;
    }

    public void placeWorker() {
        nbWorkers--;
    }

    public int getNbEnergy() {
        return nbEnergy;
    }

    public int getNbWorkers() {
        return nbWorkers;
    }

    public int getNbMaterials() {
        return nbMaterials;
    }

    public void consummeEnergy(int nb) {
        nbEnergy -= nb;
    }

    public void consummeMaterial(int nb) {
        nbMaterials -= nb;
    }

    public void addEnergy(int nb) {
        nbEnergy += nb;
    }

    public void addMaterial(int nb) {
        nbMaterials += nb;
    }

    public void increaseNbWorkerToPlace() {
        nbWorkerToPlace++;
    }

    public void decreaseNbWorkerToPlace() {
        nbWorkerToPlace--;
    }

    public void setNbWorkerToPlaceByDefault() {
        nbWorkerToPlace = DEFAULT_NB_WORKER_TO_PLACE;
    }

    public List<Building> getBuildings() {
        return buildings;
    }

    public void drawBuilding() {
        //We fix the number of buildings in hand to 5 by lack of display
        if (buildings.size() < LIMIT_NB_BUILDINGS_IN_HAND) {
            buildings.add(pile.drawBuilding());
        }
    }

    public void putBuildingFromHandToPile(Building building) {
        pile.getPile().add(building);
        buildings.remove(building);
    }

    public boolean isAllowToReturnCard() {
        return isAllowToReturnBuilding;
    }

    public void allowToReturnCard() {
        isAllowToReturnBuilding = true;
    }

    public void disallowToReturnCard() {
        isAllowToReturnBuilding = false;
    }

    public void setBuildingToReturn(Building building) {
        buildingToReturn = building;
    }

    public Building getBuildingToReturn() {
        return buildingToReturn;
    }

}
