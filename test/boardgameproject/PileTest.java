/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardgameproject;

import boardgameproject.Buildings.Building;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author TheThisma
 */
public class PileTest {
    
    public PileTest() {
    }

    /**
     * Test of drawBuilding method, of class Pile.
     */
    @Test
    public void testDrawBuilding() {
        int firstIndex = 0;
        Pile pile = new Pile();
        Building b1 = pile.getPile().get(firstIndex);
        Building b2 = pile.drawBuilding();
        assertEquals(b1,b2);
        assertTrue(b1 != null);
    }
    
}
