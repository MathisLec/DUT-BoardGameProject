/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardgameproject;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author TheThisma
 */
public class RoundTest {

    public RoundTest() {
    }

    /**
     * Test of setPutBuilding method, of class Round.
     */
    @Test
    public void testSetPutBuilding() {
        System.out.println("setPutBuilding");
        boolean b = true;
        Round instance = new Round();
        instance.setHasPlayerPlaceBuilding(b);
        assertTrue(instance.hasPlayerPlaceBuilding());
    }

    /**
     * Test of endTurn method, of class Round.
     */
    @Test
    public void testEndTurn() {
        System.out.println("endTurn");
        Round instance = new Round();
        instance.endTurn();
        assertEquals(instance.getNbTurn(), 1);
    }

}
