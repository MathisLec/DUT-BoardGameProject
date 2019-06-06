/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardgameproject;

import boardgameproject.Buildings.Building;
import boardgameproject.Exceptions.InsufficientRessourcesException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mlecoeuvre
 */
public class Player implements Serializable {

    private final int DEFAULT_NB_ENERGY = 16;
    private final int DEFAULT_NB_WORKERS = 8;
    private final int DEFAULT_NB_MATERIALS = 8;
    private final int DEFAULT_NB_MONEY = 5;
    private final int DEFAULT_NB_RESEARCH = 0;
    private final int LIMIT_NB_RESEARCH = 8;
    private final int DEFAULT_NB_STELLARIUM = 8;
    private final int NB_BUILDINGS_START = 5;
    private final int DEFAULT_NB_WORKER_TO_PLACE = 1;
    private final int LIMIT_NB_BUILDINGS_IN_HAND = 5;

    private int nbEnergy;
    private int nbWorkers;
    private int nbMaterials;
    private int nbMoney;
    private int nbResearch;
    private int nbStellarium;
    private int nbTurnSpacePort;
    private static Pile pile = new Pile();
    private List<Building> buildings;
    private int nbWorkerToPlace;
    private boolean isAllowToPlaceWorker;
    private boolean isAllowToReturnBuilding;
    private boolean isAllowToMoveWorker;

    public Player() {
        this.nbEnergy = DEFAULT_NB_ENERGY;
        this.nbWorkers = DEFAULT_NB_WORKERS;
        this.nbMaterials = DEFAULT_NB_MATERIALS;
        this.nbMoney = DEFAULT_NB_MONEY;
        this.nbResearch = DEFAULT_NB_RESEARCH;
        this.nbStellarium = DEFAULT_NB_STELLARIUM;
        this.buildings = new ArrayList<>();
        this.nbWorkerToPlace = DEFAULT_NB_WORKER_TO_PLACE;
        this.nbTurnSpacePort = 0;
        this.isAllowToPlaceWorker = false;
        this.isAllowToReturnBuilding = false;
        this.isAllowToMoveWorker = false;
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

    public int getNbWorkerToPlace() {
        return nbWorkerToPlace;
    }

    public int getNbMoney() {
        return nbMoney;
    }

    public int getNbResearch() {
        return nbResearch;
    }

    public int getNbStellarium() {
        return nbStellarium;
    }

    public int getLimitNbResearch() {
        return LIMIT_NB_RESEARCH;
    }

    public int getNbTurnSpacePort() {
        return nbTurnSpacePort;
    }

    public void consummeEnergy(int nb) throws InsufficientRessourcesException {
        if (nbEnergy > nb) {
            nbEnergy -= nb;
        } else {
            throw new InsufficientRessourcesException();
        }
    }

    public void consummeMaterial(int nb) throws InsufficientRessourcesException {
        if (nbMaterials > nb) {
            nbMaterials -= nb;
        } else {
            throw new InsufficientRessourcesException();
        }
    }

    public void consummeMoney(int nb) throws InsufficientRessourcesException {
        if (nbMoney > nb) {
            nbMoney -= nb;
        } else {
            throw new InsufficientRessourcesException();
        }
    }

    public void consummeResearch(int nb) throws InsufficientRessourcesException {
        if (nbResearch > nb) {
            nbResearch -= nb;
        } else {
            throw new InsufficientRessourcesException();
        }
    }

    public void consummeStellarium(int nb) throws InsufficientRessourcesException {
        if (nbStellarium > nb) {
            nbStellarium -= nb;
        } else {
            throw new InsufficientRessourcesException();
        }
    }

    public void addEnergy(int nb) {
        nbEnergy += nb;
    }

    public void addMaterial(int nb) {
        nbMaterials += nb;
    }

    public void addMoney(int nb) {
        nbMoney += nb;
    }

    public void addResearch(int nb) {
        if (nbResearch + nb <= LIMIT_NB_RESEARCH) {
            nbResearch += nb;
        }
    }

    public void addStellarium(int nb) {
        nbStellarium += nb;
    }

    public void addTurnSpacePort() {
        nbTurnSpacePort += 1;
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

    public boolean isAllowToReturnBuilding() {
        return isAllowToReturnBuilding;
    }

    public void allowToReturnBuilding() {
        isAllowToReturnBuilding = true;
    }

    public void disallowToReturnBuilding() {
        isAllowToReturnBuilding = false;
    }

    public boolean isAllowToPlaceWorker() {
        return isAllowToPlaceWorker;
    }

    public void allowToPlaceWorker() {
        isAllowToPlaceWorker = true;
    }

    public void disallowToPlaceWorker() {
        isAllowToPlaceWorker = false;
    }

}
