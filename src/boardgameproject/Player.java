/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardgameproject;

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
    
    
    
    private boolean checkAddBuilding(){
        return false;
    }
    private boolean checkAddWorker(){
        return false;
    }
    public void addBuilding(){
        
    }
    public void addWorker(){
        
    }
}
