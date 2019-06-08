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
    private final int DEFAULT_NB_STELLARIUM = 0;
    private final int NB_BUILDINGS_START = 5;
    private final int DEFAULT_NB_WORKER_TO_PLACE = 1;
    private final int DEFAULT_NB_BUILDING_TO_RETURN = 0;
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
    private int nbBuildingToReturn;
    private boolean isAllowToPlaceWorker;

    /**
     * Constructor
     */
    public Player() {
        this.nbEnergy = DEFAULT_NB_ENERGY;
        this.nbWorkers = DEFAULT_NB_WORKERS;
        this.nbMaterials = DEFAULT_NB_MATERIALS;
        this.nbMoney = DEFAULT_NB_MONEY;
        this.nbResearch = DEFAULT_NB_RESEARCH;
        this.nbStellarium = DEFAULT_NB_STELLARIUM;
        this.buildings = new ArrayList<>();
        this.nbWorkerToPlace = DEFAULT_NB_WORKER_TO_PLACE;
        this.nbBuildingToReturn = DEFAULT_NB_BUILDING_TO_RETURN;
        this.nbTurnSpacePort = 0;
        this.isAllowToPlaceWorker = false;
        startingHand();
    }

    /**
     * Draw the building for the starting of Player
     */
    private void startingHand() {
        for (int i = 0; i < NB_BUILDINGS_START; i++) {
            drawBuilding();
        }
    }

    /**
     * This method place a building for the player using the materials of the
     * player.
     *
     * @param building
     */
    public void placeBuilding(Building building) {
        buildings.remove(building);
        nbMaterials -= building.getMaterialCost();
    }

    /**
     * Add a worker for the player
     *
     * @param nb
     */
    public void addWorkerInHand(int nb) {
        nbWorkers += nb;
    }

    /**
     * Retire a worker for the player
     */
    public void placeWorker() {
        nbWorkers--;
    }

    /**
     *
     * @return How much Energy the Player have
     */
    public int getNbEnergy() {
        return nbEnergy;
    }

    /**
     *
     * @return How much Workers the Player have
     */
    public int getNbWorkers() {
        return nbWorkers;
    }

    /**
     *
     * @return How much Materials the Player have
     */
    public int getNbMaterials() {
        return nbMaterials;
    }

    /**
     *
     * @return How much Workers the Player can place
     */
    public int getNbWorkerToPlace() {
        return nbWorkerToPlace;
    }

    /**
     *
     * @return How much Money the Player have
     */
    public int getNbMoney() {
        return nbMoney;
    }

    /**
     *
     * @return How much ResearchPoints the Player have
     */
    public int getNbResearch() {
        return nbResearch;
    }

    /**
     *
     * @return How much Stellarium the Player have
     */
    public int getNbStellarium() {
        return nbStellarium;
    }

    /**
     *
     * @return The limit of Research Point can have
     */
    public int getLimitNbResearch() {
        return LIMIT_NB_RESEARCH;
    }

    /**
     *
     * @return How much times workers spent in the spacePort
     */
    public int getNbTurnSpacePort() {
        return nbTurnSpacePort;
    }

    /**
     * This method will consume the Energy used by the player
     *
     * @param nb how much energy is consumed
     * @throws InsufficientRessourcesException
     */
    public void consumeEnergy(int nb) throws InsufficientRessourcesException {
        if (nbEnergy > nb) {
            nbEnergy -= nb;
        } else {
            throw new InsufficientRessourcesException();
        }
    }

    /**
     * This method will consume the Materials used by the player
     *
     * @param nb how much materials is consumed
     * @throws InsufficientRessourcesException
     */
    public void consumeMaterial(int nb) throws InsufficientRessourcesException {
        if (nbMaterials > nb) {
            nbMaterials -= nb;
        } else {
            throw new InsufficientRessourcesException();
        }
    }

    /**
     * This method will consume the Materials used by the player
     *
     * @param nb how much Money is consumed
     * @throws InsufficientRessourcesException
     */
    public void consumeMoney(int nb) throws InsufficientRessourcesException {
        if (nbMoney > nb) {
            nbMoney -= nb;
        } else {
            throw new InsufficientRessourcesException();
        }
    }

    /**
     * This method will consume the Research used by the player
     *
     * @param nb how much Research is consumed
     * @throws InsufficientRessourcesException
     */
    public void consumeResearch(int nb) throws InsufficientRessourcesException {
        if (nbResearch > nb) {
            nbResearch -= nb;
        } else {
            throw new InsufficientRessourcesException();
        }
    }

    /**
     * This method will consume the Stellarium used by the player
     *
     * @param nb how much Stellarium is consumed
     * @throws InsufficientRessourcesException
     */
    public void consumeStellarium(int nb) throws InsufficientRessourcesException {
        if (nbStellarium > nb) {
            nbStellarium -= nb;
        } else {
            throw new InsufficientRessourcesException();
        }
    }

    /**
     * This method add Energy to the player
     *
     * @param nb how much Energy will be added
     */
    public void addEnergy(int nb) {
        nbEnergy += nb;
    }

    /**
     * This method add Materials to the player
     *
     * @param nb how much Materials will be added
     */
    public void addMaterial(int nb) {
        nbMaterials += nb;
    }

    /**
     * This method add Money to the player
     *
     * @param nb how much Money will be added
     */
    public void addMoney(int nb) {
        nbMoney += nb;
    }

    /**
     * This method add Research Points to the player
     *
     * @param nb how much Research Points will be added
     */
    public void addResearch(int nb) {
        if (nbResearch + nb <= LIMIT_NB_RESEARCH) {
            nbResearch += nb;
        }
    }

    /**
     * This method add Stellarium to the player
     *
     * @param nb how much Stellarium will be added
     */
    public void addStellarium(int nb) {
        nbStellarium += nb;
    }

    /**
     * This method add 1 each time a worker spent his turn on the spacePort
     */
    public void addTurnSpacePort() {
        nbTurnSpacePort += 1;
    }

    /**
     * This method will increase the number of worker that the player will be
     * able to place during his turn
     */
    public void increaseNbWorkerToPlace() {
        nbWorkerToPlace++;
    }

    /**
     *
     */
    public void decreaseNbWorkerToPlace() {
        nbWorkerToPlace--;
    }

    /**
     * This method will decrease the number of worker that the player will be
     * able to place during his turn
     */
    public void setNbWorkerToPlaceByDefault() {
        nbWorkerToPlace = DEFAULT_NB_WORKER_TO_PLACE;
    }

    /**
     *
     * @return the Player's list of Buildings
     */
    public List<Building> getBuildings() {
        return buildings;
    }

    /**
     * This makes the player draw a Building
     */
    public void drawBuilding() {
        //We fix the number of buildings in hand to 5 by lack of display
        if (buildings.size() < LIMIT_NB_BUILDINGS_IN_HAND) {
            buildings.add(pile.drawBuilding());
        }
    }

    /**
     * This put a Building from the hand to the pile (used to for the ZBlock)
     *
     * @param building
     */
    public void putBuildingFromHandToPile(Building building) {
        pile.getPile().add(building);
        buildings.remove(building);
    }

    /**
     * Increase the number of building to return
     */
    public void increaseNbBuildingToReturn() {
        nbBuildingToReturn++;
    }

    /**
     * Decrease the number of building to return
     */
    public void decreaseNbBuildingToReturn() {
        if (nbBuildingToReturn != 0) {
            nbBuildingToReturn--;
        }
    }

    /**
     *
     * @return the number of building to return
     */
    public int getNbBuildingToReturn() {
        return nbBuildingToReturn;
    }

    /**
     * Set the number of building to return to his default value
     */
    public void setNbBuildingToReturnByDefault() {
        nbBuildingToReturn = DEFAULT_NB_BUILDING_TO_RETURN;
    }

    /**
     *
     * @return true if the player is allowed to place a worker
     */
    public boolean isAllowToPlaceWorker() {
        return isAllowToPlaceWorker;
    }

    /**
     * Allow the player to place a worker
     */
    public void allowToPlaceWorker() {
        isAllowToPlaceWorker = true;
    }

    /**
     * Disallow the player to place a worker
     */
    public void disallowToPlaceWorker() {
        isAllowToPlaceWorker = false;
    }

    /**
     * Return the number of builings in the pile
     *
     * @return
     */
    public int getPileSize() {
        return pile.getNbBuildingsPile();
    }

}
