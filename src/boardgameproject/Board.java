/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardgameproject;

import java.util.ArrayList;

/**
 *
 * @author mlecoeuvre
 */
public class Board {

    private Player player;
    private final Round Round;
    private final Cell[][] Board;

    public Board(Round round, Player player) {
        this.Board = new Cell[10][20];
        this.Round = round;
        this.player = player;
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
            for (int y = 0; y < Board[i].length - 1; y++) {
                cells.add(Board[i][y]);
            }
        }
        return cells;
    }

}
