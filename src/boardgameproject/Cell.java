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
    Integer idCell;
    boolean hasWorker;

    public Cell() {
        this.idCell = null;
        hasWorker = false;
    }

    public void setIdBuilding(Integer idCell) {
        this.idCell = idCell;
    }
    
    public void changeWorkerStatus(){
        hasWorker = !hasWorker;
    }

    public boolean isHasWorker() {
        return hasWorker;
    }

    
}
