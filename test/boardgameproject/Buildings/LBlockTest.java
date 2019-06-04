/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardgameproject.Buildings;

import boardgameproject.Board;
import boardgameproject.Cell;
import boardgameproject.Player;
import boardgameproject.Round;
import java.util.ArrayList;
import javafx.scene.canvas.Canvas;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author TheThisma
 */
public class LBlockTest {

    Player player;
    Board board;
    Round round;

    public LBlockTest() {
        player = new Player();
        round = new Round();
        board = new Board(round, player);
    }

    /**
     * Test of buildingShape method, of class LBlock.
     */
    @Test
    public void testBuildingShape() {
        System.out.println("buildingShape");
        int x = 0;
        int y = 0;
        LBlock instance = new LBlock();
        instance.buildingShape(x, y);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of drawBuilding method, of class LBlock.
     */
    @Test
    public void testDrawBuilding() {
        System.out.println("drawBuilding");
        Canvas c = null;
        LBlock instance = new LBlock();
        instance.drawBuilding(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buildingRole method, of class LBlock with top orientation.
     */
    @Test
    public void testBuildingRoleTop() throws Exception {
        System.out.println("buildingRole");
        LBlock instance = new LBlock();
        player.getBuildings().add(instance);

        board.addBuilding(instance, 1, 1);
        board.addWorker(1, 1);
        board.endTurn();

        assertEquals(player.getNbWorkers(), 7);
        board.addWorker(2, 1);
        board.endTurn();
        assertEquals(player.getNbWorkers(), 9);
    }

    /**
     * Test of buildingRole method, of class LBlock with left orientation.
     */
    @Test
    public void testBuildingRoleLeft() throws Exception {
        System.out.println("buildingRole");
        LBlock instance = new LBlock();
        player.getBuildings().add(instance);
        instance.rotateBuildingLeft();

        board.addBuilding(instance, 2, 2);
        board.addWorker(2, 2);
        board.endTurn();

        assertEquals(player.getNbMoney(), 7); // 5 + 2
    }

    /**
     * Test of buildingRole method, of class LBlock with bottom orientation.
     */
    @Test
    public void testBuildingRoleBottom() throws Exception {
        System.out.println("buildingRole");
        LBlock instance = new LBlock();
        player.getBuildings().add(instance);
        instance.rotateBuildingLeft();
        instance.rotateBuildingLeft();

        board.addBuilding(instance, 2, 2);
        board.addWorker(2, 2);
        board.endTurn();

        assertEquals(player.getNbMoney(), 1); // 5 - 4
        assertEquals(player.getNbWorkers(), 8); // 8 - 1 + 1
    }
    
    /**
     * Test of buildingRole method, of class LBlock with right orientation.
     */
    @Test
    public void testBuildingRoleRight() throws Exception {
        System.out.println("buildingRole");
        LBlock instance = new LBlock();
        player.getBuildings().add(instance);
        instance.rotateBuildingRight();

        board.addBuilding(instance, 2, 2);
        board.addWorker(2, 2);
        board.endTurn();

        assertEquals(player.getNbMoney(), 9); // 5 + 4
        assertEquals(player.getNbEnergy(), 15); // 16 - 1
    }

    /**
     * Test of rotateBuildingLeft method, of class LBlock.
     */
    @Test
    public void testRotateBuildingLeft() {
        System.out.println("rotateBuildingLeft");
        LBlock instance = new LBlock();
        instance.rotateBuildingLeft();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of rotateBuildingRight method, of class LBlock.
     */
    @Test
    public void testRotateBuildingRight() {
        System.out.println("rotateBuildingRight");
        LBlock instance = new LBlock();
        instance.rotateBuildingRight();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPreviewsShape method, of class LBlock.
     */
    @Test
    public void testGetPreviewsShape() {
        System.out.println("getPreviewsShape");
        Board board = null;
        int x = 0;
        int y = 0;
        LBlock instance = new LBlock();
        ArrayList<Cell> expResult = null;
        ArrayList<Cell> result = instance.getPreviewsShape(board, x, y);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
