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
     * Test of buildingRole method, of class SBlock with horizontal orientation.
     */
    @Test
    public void testBuildingRoleHori() throws InvalidLocationException,
            InsufficientRessourcesException {
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
    public void testBuildingRoleVert() throws InvalidLocationException,
            InsufficientRessourcesException {
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

}
