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
public class Pile {
    private int nbBuildingsPile;
    private List<Building> pile;

    public Pile() {
        this.nbBuildingsPile = 0;
        this.pile = new ArrayList<>();
    }
    
    
    private void randomizePile(){
        
    }
    public void InitializePile(){
        
    }
    public List<Building> getPile(){
        return pile;
    }
    
}
