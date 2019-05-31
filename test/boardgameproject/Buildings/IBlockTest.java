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
public class IBlockTest {

    Player player;
    Round round;
    Board board;

    public IBlockTest() {
        player = new Player();
        round = new Round();
        board = new Board(round, player);
    }

    /**
     * Test of buildingShape method, of class IBlock.
     */
    @Test
    public void testBuildingShape() {
        System.out.println("buildingShape");
        int x = 0;
        int y = 0;
        IBlock instance = new IBlock();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buildingRole method, of class IBlock.
     */
    @Test
    public void testBuildingRole() {
        System.out.println("buildingRole");
        IBlock instance = new IBlock();
        board.addBuilding(instance, 0, 0);
        board.addWorker(0, 0);
        board.endTurn();
        int expectedResultEnergy = 10; // 8 + 2
        assertEquals(player.getNbMaterials(), expectedResultEnergy);
    }

    /**
     * Test of rotateBuildingLeft method, of class IBlock.
     */
    @Test
    public void testRotateBuildingLeft() {
        System.out.println("rotateBuildingLeft");
        IBlock instance = new IBlock();
        instance.rotateBuildingLeft();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of rotateBuildingRight method, of class IBlock.
     */
    @Test
    public void testRotateBuildingRight() {
        System.out.println("rotateBuildingRight");
        IBlock instance = new IBlock();
        instance.rotateBuildingRight();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of drawBuilding method, of class IBlock.
     */
    @Test
    public void testDrawBuilding() {
        System.out.println("drawBuilding");
        Canvas c = null;
        IBlock instance = new IBlock();
        instance.drawBuilding(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPreviewsShape method, of class IBlock.
     */
    @Test
    public void testGetPreviewsShape() {
        System.out.println("getPreviewsShape");
        Board board = null;
        int x = 0;
        int y = 0;
        IBlock instance = new IBlock();
        ArrayList<Cell> expResult = null;
        ArrayList<Cell> result = instance.getPreviewsShape(board, x, y);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
