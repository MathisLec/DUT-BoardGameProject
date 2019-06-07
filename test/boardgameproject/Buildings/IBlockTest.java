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
     * Test of buildingRole method, of class IBlock with horizontal orientation.
     */
    @Test
    public void testBuildingRoleHori() throws InvalidLocationException,
            InsufficientRessourcesException {
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
    public void testBuildingRoleVert() throws InvalidLocationException,
            InsufficientRessourcesException {
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
}
