/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardgameproject;

/**
 *
 * @author mlecoeuvre
 */
public class Cell {
    Integer idBuilding;
    boolean hasWorker;

    public Cell() {
        this.idBuilding = null;
        hasWorker = false;
    }

    public void setIdBuilding(Integer idBuilding) {
        this.idBuilding = idBuilding;
    }
    
    public void changeWorkerStatus(){
        hasWorker = !hasWorker;
    }

    public boolean isHasWorker() {
        return hasWorker;
    }

    
}
