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
public class Board {
    private Cell[][] Board;

    public Board() {
        Board = new Cell[9][19];
    }
    
    public void removeWorker(){
        
    }

    public Cell[][] getBoard() {
        return Board;
    }
    
}
