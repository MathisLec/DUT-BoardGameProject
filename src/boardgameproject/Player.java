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

    private int nbEnergy;
    private int nbWorkers;
    private int nbMaterials;
    private int nbBuildings;
    private List<Building> Buildings;
    public static List<Building> Pile;

    public Player() {
        this.nbEnergy = 16;
        this.nbWorkers = 8;
        this.nbMaterials = 8;
        this.nbBuildings = 5;
        this.Buildings = new ArrayList<>();
    }

    public void placeBuilding(Building building) {
        Buildings.remove(building);
    }

    public void placeWorker(Cell cell) {
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

    public int getNbBuildings() {
        return nbBuildings;
    }

    public List<Building> getBuildings() {
        return Buildings;
    }

}
