/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardgameproject.Buildings;

import boardgameproject.Board;
import boardgameproject.Cell;
import boardgameproject.Exceptions.InvalidLocationException;
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
     * Test of buildingRole method, of class IBlock with horizontal orientation.
     */
    @Test
    public void testBuildingRoleHori() throws InvalidLocationException {
        System.out.println("buildingRoleHori");
        IBlock instance = new IBlock();
        instance.rotateBuildingLeft();
        board.addBuilding(instance, 0, 0);
        board.addWorker(0, 0);
        board.endTurn();
        int expectedResultMaterial = 6; // 8 - 4 + 2
        assertEquals(player.getNbMaterials(), expectedResultMaterial);
    }

    /**
     * Test of buildingRole method, of class IBlock with vertical orientation.
     */
    @Test
    public void testBuildingRoleVert() throws InvalidLocationException {
        System.out.println("buildingRoleVert");
        IBlock instance = new IBlock();
        board.addBuilding(instance, 0, 0);
        board.addWorker(0, 0);
        board.endTurn();
        int expectedResultMaterial = 6; // 8-4 +1+1
        int expectedResultEnergy = 15; // 16 - 1
        assertEquals(player.getNbMaterials(), expectedResultMaterial);
        assertEquals(player.getNbEnergy(), expectedResultEnergy);
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
