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
     * Test of buildingRole method, of class JBlock with top orientation.
     */
    @Test
    public void testBuildingRoleTop() throws InvalidLocationException {
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
     * Test of buildingRole method, of class JBlock with left orientation.
     */
    @Test
    public void testBuildingRoleLeft() throws InvalidLocationException {
        // this test has been done with a research points fixed to 1
        System.out.println("buildingRole");
        JBlock instance = new JBlock();

        player.getBuildings().add(instance);
        instance.rotateBuildingLeft();

        board.addBuilding(instance, 1, 1);
        board.addWorker(1, 1);
        board.endTurn();
        int nbMaterialAfter = 0; // 8 - 8
        int nbMoneyAfter = 3; // 5 - 2
        int nbEnergyAfter = 14; // 16 - 2
        int nbResearchAfter = 2; // 1 + 1
        assertEquals(nbEnergyAfter, player.getNbEnergy());
        assertEquals(nbMaterialAfter, player.getNbMaterials());
        assertEquals(nbMoneyAfter, player.getNbMoney());
        assertEquals(nbResearchAfter, player.getNbResearch());
    }

    /**
     * Test of buildingRole method, of class JBlock with bottom orientation.
     */
    @Test
    public void testBuildingRoleBottom() throws InvalidLocationException {
        // this test has been done with a research points fixed to 1
        System.out.println("buildingRole");
        JBlock instance = new JBlock();
        player.getBuildings().add(instance);
        instance.rotateBuildingLeft();
        instance.rotateBuildingLeft();

        board.addBuilding(instance, 1, 1);
        board.addWorker(1, 1);
        board.endTurn();
        int nbEnergyAfter = 15; //16 - 1
        int nbMaterialAfter = 0; // 8 - 8
        int nbMoneyAfter = 4; // 5 - 1
        int nbWorkerAfter = 7; // 8-1 + 0 (Rounded down)
        assertEquals(nbEnergyAfter, player.getNbEnergy());
        assertEquals(nbMoneyAfter, player.getNbMoney());
        assertEquals(nbMaterialAfter, player.getNbMaterials());
        assertEquals(nbWorkerAfter, player.getNbWorkers());
    }

    /**
     * Test of buildingRole method, of class JBlock with right orientation.
     */
    @Test
    public void testBuildingRoleRight() throws InvalidLocationException {
        System.out.println("buildingRole");
        JBlock instance = new JBlock();
        player.getBuildings().add(instance);
        instance.rotateBuildingRight();

        board.addBuilding(instance, 1, 2);
        board.addWorker(1, 2);
        board.endTurn();
        int nbEnergyAfter = 20; //16 + 4
        int nbMaterialAfter = 0; // 8 - 8
        int nbMoneyAfter = 9; // 5 + 4
        int nbWorkerOnBoardAfter = 0;
        int nbWorkerInHandAfter = 7;
        assertEquals(nbEnergyAfter, player.getNbEnergy());
        assertEquals(nbMoneyAfter, player.getNbMoney());
        assertEquals(nbMaterialAfter, player.getNbMaterials());
        assertEquals(nbWorkerOnBoardAfter,instance.getNbWorker());
        assertEquals(nbWorkerInHandAfter,player.getNbWorkers());
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
