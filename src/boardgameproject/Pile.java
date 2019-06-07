/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardgameproject;

import boardgameproject.Buildings.Building;
import boardgameproject.Buildings.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

/**
 *
 * @author mlecoeuvre
 */
public final class Pile implements Serializable {

    private int nbBuildingsPile;
    private List<Building> pile;
    private int firstIndex;

    /**
     * Constructor
     */
    public Pile() {
        this.pile = InitializePile();
        this.nbBuildingsPile = pile.size();
        this.firstIndex = 0;
    }

    /**
     *  This method will shuffle the Pile at the start of the game
     * @param list The list which will be shuffled
     */
    private void randomizePile(ArrayList<Building> list) {
        Collections.shuffle(list);
    }

    /**
     * This method will fill the Pile with Buildings
     * @return A pile of buildings
     */
    private ArrayList<Building> InitializePile() {
        ArrayList<Building> list = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            Building buildI = new IBlock();
            list.add(buildI);
        }
        for (int i = 0; i < 10; i++) {
            Building buildO = new OBlock();
            list.add(buildO);
        }
        for (int i = 0; i < 30; i++) {
            Building buildT = new TBlock();
            list.add(buildT);
        }
        for (int i = 0; i < 20; i++) {
            Building buildL = new LBlock();
            list.add(buildL);
        }
        for (int i = 0; i < 10; i++) {
            Building buildJ = new JBlock();
            list.add(buildJ);
        }
        for (int i = 0; i < 30; i++) {
            Building buildS = new SBlock();
            list.add(buildS);
        }
        for (int i = 0; i < 10; i++) {
            Building buildZ = new ZBlock();
            list.add(buildZ);
        }
        randomizePile(list);
        return list;
    }

    /**
     *
     * @return the a List of Building which represents the Pile
     */
    public List<Building> getPile() {
        return pile;
    }

    /**
     *
     * @return the number of Buildings in the Pile
     */
    public int getNbBuildingsPile() {
        return nbBuildingsPile;
    }

    /**
     * Delete the building which is drawn
     * @param building 
     */
    private void deleteBuilding(Building building) {
        pile.remove(building);
        updateNbBuildingPile();
    }

    /**
     * Update the Size of the pile
     */
    private void updateNbBuildingPile() {
        nbBuildingsPile = pile.size();
    }

    /**
     * This method will Draw a Building from the Pile
     * @return the Building drawn
     */
    public Building drawBuilding() {
        Building building = pile.get(firstIndex);
        deleteBuilding(building);
        return building;
    }
}
