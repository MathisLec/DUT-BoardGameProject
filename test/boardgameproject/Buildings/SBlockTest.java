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
public class SBlockTest {

    Player player;
    Board board;
    Round round;

    public SBlockTest() {
        player = new Player();
        round = new Round();
        board = new Board(round, player);
    }

    /**
     * Test of buildingShape method, of class SBlock.
     */
    @Test
    public void testBuildingShape() {
        System.out.println("buildingShape");
        int x = 0;
        int y = 0;
        SBlock instance = new SBlock();
        instance.buildingShape(x, y);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of drawBuilding method, of class SBlock.
     */
    @Test
    public void testDrawBuilding() {
        System.out.println("drawBuilding");
        Canvas c = null;
        SBlock instance = new SBlock();
        instance.drawBuilding(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buildingRole method, of class SBlock with horizontal orientation.
     */
    @Test
    public void testBuildingRoleHori() throws InvalidLocationException {
        System.out.println("buildingRole");
        SBlock instance = new SBlock();
        player.getBuildings().add(instance);
        instance.rotateBuildingLeft();

        board.addBuilding(instance, 1, 1);
        board.addWorker(1, 1);
        board.endTurn();

        int nbMaterialAfter = 7; // 8 - 2 + 1
        assertEquals(nbMaterialAfter, player.getNbMaterials());
    }

    /**
     * Test of buildingRole method, of class SBlock with vertical orientation.
     */
    @Test
    public void testBuildingRoleVert() throws InvalidLocationException {
        // this test has been done with a research points fixed to 1
        System.out.println("buildingRole");
        SBlock instance = new SBlock();
        player.getBuildings().add(instance);

        board.addBuilding(instance, 1, 1);
        board.addWorker(1, 1);
        board.endTurn();

        assertEquals(player.getNbEnergy(), 14); // 16 - 2
        assertEquals(player.getNbMoney(), 4); // 5 - 1
        assertEquals(player.getNbMaterials(), 7); // 8 - 2 + 1
    }

    /**
     * Test of rotateBuildingLeft method, of class SBlock.
     */
    @Test
    public void testRotateBuildingLeft() {
        System.out.println("rotateBuildingLeft");
        SBlock instance = new SBlock();
        instance.rotateBuildingLeft();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of rotateBuildingRight method, of class SBlock.
     */
    @Test
    public void testRotateBuildingRight() {
        System.out.println("rotateBuildingRight");
        SBlock instance = new SBlock();
        instance.rotateBuildingRight();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPreviewsShape method, of class SBlock.
     */
    @Test
    public void testGetPreviewsShape() {
        System.out.println("getPreviewsShape");
        Board board = null;
        int x = 0;
        int y = 0;
        SBlock instance = new SBlock();
        ArrayList<Cell> expResult = null;
        ArrayList<Cell> result = instance.getPreviewsShape(board, x, y);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
