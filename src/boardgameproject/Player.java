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

    private final int NB_BUILDINGS_START = 5;
    private int nbEnergy;
    private int nbWorkers;
    private int nbMaterials;
    private static Pile pile = new Pile();
    private List<Building> buildings;

    public Player() {
        this.nbEnergy = 100;
        this.nbWorkers = 100;
        this.nbMaterials = 100;
        this.buildings = new ArrayList<>();
        startingHand();
    }

    private void startingHand() {
        for (int i = 0; i < NB_BUILDINGS_START; i++) {
            drawBuilding();
        }
    }

    public void placeBuilding(Building building) {
        buildings.remove(building);
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

    public List<Building> getBuildings() {
        return buildings;
    }

    public void drawBuilding() {
        buildings.add(pile.drawBuilding());
    }

}
