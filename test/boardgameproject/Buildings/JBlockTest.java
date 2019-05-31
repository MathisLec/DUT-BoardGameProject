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
public class JBlockTest {

    Player player;
    Board board;
    Round round;

    public JBlockTest() {
        player = new Player();
        round = new Round();
        board = new Board(round, player);
    }

    /**
     * Test of buildingShape method, of class JBlock.
     */
    @Test
    public void testBuildingShape() {
        System.out.println("buildingShape");
        int x = 0;
        int y = 0;
        JBlock instance = new JBlock();
        instance.buildingShape(x, y);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of drawBuilding method, of class JBlock.
     */
    @Test
    public void testDrawBuilding() {
        System.out.println("drawBuilding");
        Canvas c = null;
        JBlock instance = new JBlock();
        instance.drawBuilding(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buildingRole method, of class JBlock.
     */
    @Test
    public void testBuildingRole() {
        System.out.println("buildingRole");
        JBlock instance = new JBlock();
        player.getBuildings().add(instance);

        board.addBuilding(instance, 1, 1);
        board.addWorker(1, 1);
        board.endTurn();
        int nbEnergyAfter = 12; //16 - 4
        int nbMaterialAfter = 0; // 8 - 8
        int nbBuildingInHandAfter = 5; // The limit is 5
        assertEquals(nbEnergyAfter, player.getNbEnergy());
        assertEquals(nbBuildingInHandAfter, player.getBuildings().size());
        assertEquals(nbMaterialAfter, player.getNbMaterials());
    }

    /**
     * Test of rotateBuildingLeft method, of class JBlock.
     */
    @Test
    public void testRotateBuildingLeft() {
        System.out.println("rotateBuildingLeft");
        JBlock instance = new JBlock();
        instance.rotateBuildingLeft();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of rotateBuildingRight method, of class JBlock.
     */
    @Test
    public void testRotateBuildingRight() {
        System.out.println("rotateBuildingRight");
        JBlock instance = new JBlock();
        instance.rotateBuildingRight();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPreviewsShape method, of class JBlock.
     */
    @Test
    public void testGetPreviewsShape() {
        System.out.println("getPreviewsShape");
        Board board = null;
        int x = 0;
        int y = 0;
        JBlock instance = new JBlock();
        ArrayList<Cell> expResult = null;
        ArrayList<Cell> result = instance.getPreviewsShape(board, x, y);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
