/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardgameproject.Buildings;

import boardgameproject.Board;
import boardgameproject.Player;
import boardgameproject.Round;
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

}
