/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardgameproject;

import boardgameproject.Buildings.Building;
import boardgameproject.Buildings.IBlock;
import boardgameproject.Buildings.SBlock;
import boardgameproject.Exceptions.InsufficientRessourcesException;
import boardgameproject.Exceptions.InvalidLocationException;
import java.util.ArrayList;
import javafx.scene.canvas.Canvas;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mlecoeuvre
 */
public class BoardTest {

    Round round;
    Player player;

    public BoardTest() {
        round = new Round();
        player = new Player();
    }

    /**
     * Test of addBuilding method, of class Board.
     */
    @Test
    public void testAddBuilding() throws Exception {
        System.out.println("addBuilding");
        Building building = new IBlock();
        int x = 0;
        int y = 0;
        Board instance = new Board(round, player);
        instance.addBuilding(building, x, y);
        assertEquals(instance.getBuildings().size(), 1);
    }

    /**
     * Test of addWorker method, of class Board.
     */
    @Test
    public void testAddWorker() throws Exception {
        System.out.println("addWorker");
        Building building = new IBlock();
        int x = 0;
        int y = 0;
        Board instance = new Board(round, player);
        instance.addBuilding(building, x, y);
        instance.addWorker(x, y);
        assertEquals(building.getNbWorker(), 1);
    }

    /**
     * Test of moveWorker method, of class Board.
     *
     */
    @Test
    public void testMoveWorker() throws Exception {
        System.out.println("moveWorker");
        Building building = new IBlock();
        int x = 0;
        int x2 = 1;
        int y = 0;
        Board instance = new Board(round, player);
        instance.addBuilding(building, x, y);
        instance.addWorker(x, y);
        instance.endTurn();
        instance.removeWorker(x, y);
        instance.moveWorker(x2, y);
        assertEquals(player.getNbWorkers(), 7);
    }

    /**
     * Test of removeWorker method, of class Board.
     */
    @Test
    public void testRemoveWorker() throws InsufficientRessourcesException,
            InvalidLocationException {
        System.out.println("removeWorker");
        Building building = new IBlock();
        int x = 0;
        int y = 0;
        Board instance = new Board(round, player);
        instance.addBuilding(building, x, y);
        instance.addWorker(x, y);
        assertEquals(building.getNbWorker(), 1);
        instance.removeWorker(x, y);
        assertEquals(building.getNbWorker(), 0);
    }

    /**
     * Test of getNbBuildingsOfEachType method, of class Board.
     */
    @Test
    public void testGetNbBuildingsOfEachType()
            throws InsufficientRessourcesException, InvalidLocationException {
        System.out.println("getNbBuildingsOfEachType");
        Board instance = new Board(round, player);
        instance.addBuilding(new SBlock(), 5, 5);
        instance.addBuilding(new SBlock(), 10, 5);
        for (Character c : instance.getNbBuildingsOfEachType().keySet()) {
            System.out.println(c + "Block = " + instance.getNbBuildingsOfEachType().get(c));
        }

    }

    /**
     * Test of getNbWorkerOnBoard method, of class Board.
     */
    @Test
    public void testGetNbWorkerOnBoard()
            throws InsufficientRessourcesException, InvalidLocationException {
        System.out.println("getNbWorkerOnBoard");
        Board instance = new Board(round, player);
        instance.addBuilding(new SBlock(), 5, 5);
        instance.addWorker(5, 5);
        assertEquals(instance.getNbWorkerOnBoard(), 1);
    }

}
