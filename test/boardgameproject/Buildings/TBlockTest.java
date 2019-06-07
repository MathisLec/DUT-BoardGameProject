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
     * Test of buildingRole method, of class TBlock with top orientation
     */
    @Test
    public void testBuildingRoleTop() throws InvalidLocationException,
            InsufficientRessourcesException {
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
     * Test of buildingRole method, of class TBlock with left orientation
     */
    @Test
    public void testBuildingRoleLeft() throws InvalidLocationException,
            InsufficientRessourcesException {
        System.out.println("buildingRole");
        TBlock instance = new TBlock();
        player.getBuildings().add(instance);
        instance.rotateBuildingLeft();

        board.addBuilding(instance, 1, 1);
        board.addWorker(1, 1);
        board.endTurn();

        assertEquals(player.getNbEnergy(), 18); // 16 + 2
        assertEquals(player.getNbMaterials(), 5); // 8 - 2 - 1
    }

    /**
     * Test of buildingRole method, of class TBlock with bottom orientation
     */
    @Test
    public void testBuildingRoleBottom() throws InvalidLocationException,
            InsufficientRessourcesException {
        System.out.println("buildingRole");
        TBlock instance = new TBlock();
        player.getBuildings().add(instance);
        instance.rotateBuildingLeft();
        instance.rotateBuildingLeft();

        board.addBuilding(instance, 1, 1);
        board.addWorker(1, 1);
        board.endTurn();
        // even turn, nothing move
        assertEquals(player.getNbEnergy(), 16);
        round.endTurn();

        board.endTurn();
        assertEquals(player.getNbEnergy(), 18);
        round.endTurn();
        // even turn, nothing move
        board.endTurn();
        assertEquals(player.getNbEnergy(), 18);
    }

    /**
     * Test of buildingRole method, of class TBlock with right orientation
     */
    @Test
    public void testBuildingRoleRight() throws InvalidLocationException,
            InsufficientRessourcesException {
        System.out.println("buildingRole");
        TBlock instance = new TBlock();
        player.getBuildings().add(instance);
        instance.rotateBuildingRight();

        board.addBuilding(instance, 1, 1);
        board.addWorker(1, 1);
        board.endTurn();

        assertEquals(player.getNbEnergy(), 17); // 16 + 1
    }

}
