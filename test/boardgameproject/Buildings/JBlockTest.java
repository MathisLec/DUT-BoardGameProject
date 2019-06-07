/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardgameproject.Buildings;

import boardgameproject.Board;
import boardgameproject.Exceptions.InsufficientRessourcesException;
import boardgameproject.Exceptions.InvalidLocationException;
import boardgameproject.Player;
import boardgameproject.Round;
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
     * Test of buildingRole method, of class JBlock with top orientation.
     */
    @Test
    public void testBuildingRoleTop() throws InvalidLocationException,
            InsufficientRessourcesException {
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
    public void testBuildingRoleLeft() throws InvalidLocationException,
            InsufficientRessourcesException {
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
    public void testBuildingRoleBottom() throws InvalidLocationException,
            InsufficientRessourcesException {
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
    public void testBuildingRoleRight() throws InvalidLocationException,
            InsufficientRessourcesException {
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
        assertEquals(nbWorkerOnBoardAfter, instance.getNbWorker());
        assertEquals(nbWorkerInHandAfter, player.getNbWorkers());
    }

}
