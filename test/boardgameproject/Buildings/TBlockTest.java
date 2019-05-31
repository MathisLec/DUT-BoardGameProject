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
public class TBlockTest {

    Player player;
    Board board;
    Round round;

    public TBlockTest() {
        player = new Player();
        round = new Round();
        board = new Board(round, player);
    }

    /**
     * Test of buildingShape method, of class TBlock.
     */
    @Test
    public void testBuildingShape() {
        System.out.println("buildingShape");
        int x = 0;
        int y = 0;
        TBlock instance = new TBlock();
        instance.buildingShape(x, y);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of drawBuilding method, of class TBlock.
     */
    @Test
    public void testDrawBuilding() {
        System.out.println("drawBuilding");
        Canvas c = null;
        TBlock instance = new TBlock();
        instance.drawBuilding(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buildingRole method, of class TBlock.
     */
    @Test
    public void testBuildingRole() {
        System.out.println("buildingRole");
        TBlock instance = new TBlock();
        player.getBuildings().add(instance);

        board.addBuilding(instance, 1, 1);
        board.addWorker(1, 1);
        board.endTurn();

        int nbEnergyAfter = 18; //16 + 2
        int nbMaterialAfter = 6; // 8 - 2
        int nbBuildingInHandAfter = 5; // The limit is 5
        assertEquals(nbEnergyAfter, player.getNbEnergy());
        assertEquals(nbBuildingInHandAfter, player.getBuildings().size());
        assertEquals(nbMaterialAfter, player.getNbMaterials());

        round.endTurn();
        board.endTurn();
        //odd turn, nothing move
        assertEquals(nbEnergyAfter, player.getNbEnergy());
        assertEquals(nbBuildingInHandAfter, player.getBuildings().size());
        assertEquals(nbMaterialAfter, player.getNbMaterials());

        round.endTurn();
        board.endTurn();
        nbEnergyAfter = 20;
        assertEquals(nbEnergyAfter, player.getNbEnergy());
    }

    /**
     * Test of rotateBuildingLeft method, of class TBlock.
     */
    @Test
    public void testRotateBuildingLeft() {
        System.out.println("rotateBuildingLeft");
        TBlock instance = new TBlock();
        instance.rotateBuildingLeft();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of rotateBuildingRight method, of class TBlock.
     */
    @Test
    public void testRotateBuildingRight() {
        System.out.println("rotateBuildingRight");
        TBlock instance = new TBlock();
        instance.rotateBuildingRight();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPreviewsShape method, of class TBlock.
     */
    @Test
    public void testGetPreviewsShape() {
        System.out.println("getPreviewsShape");
        Board board = null;
        int x = 0;
        int y = 0;
        TBlock instance = new TBlock();
        ArrayList<Cell> expResult = null;
        ArrayList<Cell> result = instance.getPreviewsShape(board, x, y);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
