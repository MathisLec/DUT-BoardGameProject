/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardgameproject;

import boardgameproject.Buildings.Building;
import java.util.ArrayList;

/**
 *
 * @author mlecoeuvre
 */
public final class Board {

    private Player player;
    private final Round Round;
    private final Cell[][] Board;

    public Board(Round round, Player player) {
        this.Board = new Cell[10][20];
        this.Round = round;
        this.player = player;
        initializeBoard();
    }
    
    public void initializeBoard(){
        for (int i = 0; i < Board.length - 1; i++) {
            for (int j = 0; j < Board[i].length - 1; j++) {
                Board[i][j] = new Cell(i,j);
            }
        }
    }
    
    public void addBuilding(Building building, int x, int y){
        
    }
    
    public void addWorker(Cell cell){
        cell.changeWorkerStatus();
    }
    
    private boolean checkAddBuilding() {
        boolean isValid = false;
        
        return isValid;
    }

    private boolean checkAddWorker(Cell cell) {
        boolean isValid = true;
        if (cell.hasWorker() || player.getNbWorkers() >= 0 || !cell.hasBuilding()) {
            isValid = !isValid;
        }
        return isValid;
    }
    
    public void checkRemoveWorker() {
        for (Cell c : boardToList()) {
            if (c.hasWorker()) {
                if (c.getBuildingType().getEnergyConsume() > player.getNbEnergy()) {
                    removeWorker(c);
                }
            }
        }
    }

    private void removeWorker(Cell cell) {
        cell.changeWorkerStatus();
    }

    public Cell[][] getBoard() {
        return Board;
    }
    

    private ArrayList<Cell> boardToList() {
        ArrayList<Cell> cells = new ArrayList<>();
        for (int i = 0; i < Board.length - 1; i++) {
            for (int j = 0; j < Board[i].length - 1; j++) {
                cells.add(Board[i][j]);
            }
        }
        return cells;
    }

}
