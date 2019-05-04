/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardgameproject;

import boardgameproject.Buildings.*;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

/**
 *
 * @author mlecoeuvre
 */
public final class Pile {
    private int nbBuildingsPile;
    private List<Building> pile;

    public Pile() {
        this.pile = InitializePile();
        this.nbBuildingsPile = pile.size();
    }
    
    
    private void randomizePile(ArrayList<Building> list){
        Collections.shuffle(list);
    }
    public ArrayList<Building> InitializePile(){
        ArrayList<Building> list = new ArrayList<>();
        for(int i=0;i<25;i++){
            Building buildI = new IBlock();
            list.add(buildI);
        }
        for(int i=0;i<10;i++){
            Building buildO = new OBlock();
            list.add(buildO);
        }
        for(int i=0;i<30;i++){
            Building buildT = new TBlock();
            list.add(buildT);
        }
        for(int i=0;i<20;i++){
            Building buildL = new LBlock();
            list.add(buildL);
        }
        for(int i=0;i<10;i++){
            Building buildJ = new JBlock();
            list.add(buildJ);
        }
        for(int i=0;i<30;i++){
            Building buildS = new SBlock();
            list.add(buildS);
        }
        for(int i=0;i<10;i++){
            Building buildZ = new ZBlock();
            list.add(buildZ);
        }
        randomizePile(list);
        return list;
    }
    public List<Building> getPile(){
        return pile;
    }

    public int getNbBuildingsPile() {
        return nbBuildingsPile;
    }
    
    public void deleteBuilding(Building building){
        pile.remove(building);
        updateNbBuildingPile();
    }
    
    private void updateNbBuildingPile(){
        nbBuildingsPile = pile.size();
    }
}
