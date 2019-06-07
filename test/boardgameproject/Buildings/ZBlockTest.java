/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardgameproject.Buildings;

import boardgameproject.Board;
import boardgameproject.Cell;
import boardgameproject.Exceptions.InsufficientRessourcesException;
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
public class ZBlockTest {

    Player player;
    Board board;
    Round round;

    public ZBlockTest() {
        player = new Player();
        round = new Round();
        board = new Board(round, player);
    }

    /**
     * Test of buildingRole method, of class ZBlock with horizontal orientation.
     */
    @Test
    public void testBuildingRoleHori() throws InvalidLocationException,
            InsufficientRessourcesException {
        System.out.println("buildingRole");
        ZBlock instance = new ZBlock();
        player.getBuildings().add(instance);
        instance.rotateBuildingLeft();

        board.addBuilding(instance, 1, 1);
        board.addWorker(1, 1);
        board.endTurn();

        int nbEnergyAfter = 18; //16 + 2
        int nbMaterialAfter = 6; // 8 - 2
        int nbBuildingInHandAfter = 5; // The limit is 5
        assertEquals(nbEnergyAfter, player.getNbEnergy());
        assertEquals(nbBuildingInHandAfter, player.getBuildings().size());
        assertEquals(nbMaterialAfter, player.getNbMaterials());
    }

    /**
     * Test of buildingRole method, of class ZBlock with horizontal orientation.
     */
    @Test
    public void testBuildingRoleVert() throws InvalidLocationException,
            InsufficientRessourcesException {
        System.out.println("buildingRole");
        ZBlock instance = new ZBlock();
        player.getBuildings().add(instance);

        board.addBuilding(instance, 1, 1);
        board.addWorker(1, 1);
        board.endTurn();

        assertEquals(player.getNbMoney(), 4); // 5 - 1
        assertEquals(player.getNbTurnSpacePort(), 1);
    }
}
