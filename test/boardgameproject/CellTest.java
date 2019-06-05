/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardgameproject;

import boardgameproject.Buildings.Building;
import boardgameproject.Buildings.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author TheThisma
 */
public class CellTest {

    public CellTest() {
    }

    /**
     * Test of changeWorkerStatus method, of class Cell.
     */
    @Test
    public void testChangeWorkerStatus() {
        System.out.println("changeWorkerStatus");
        Cell instance = new Cell(0, 0);
        instance.setHasWorkerStatus();
        assertEquals(instance.hasWorker(), true);

        instance.setHasNotWorkerStatus();
        assertEquals(instance.hasWorker(), false);
    }

    /**
     * Test of changeBuildingStatus method, of class Cell.
     */
    @Test
    public void testChangeBuildingStatus_Building() {
        System.out.println("changeBuildingStatus");
        Building building = new IBlock();
        Cell instance = new Cell();
        instance.changeBuildingStatus(building);
        assertEquals(instance.hasBuilding(), true);
        assertEquals(instance.getBuildingType(), building);
    }

    /**
     * Test of changeBuildingStatus method, of class Cell.
     */
    @Test
    public void testChangeBuildingStatus_0args() {
        System.out.println("changeBuildingStatus");
        Cell instance = new Cell();
        instance.changeBuildingStatus();
        assertEquals(instance.hasBuilding(), false);
        assertEquals(instance.getBuildingType(), null);
    }

    /**
     * Test of hasWorker method, of class Cell.
     */
    @Test
    public void testHasWorker() {
        System.out.println("hasWorker");
        Cell instance = new Cell();
        boolean expResult = false;
        boolean result = instance.hasWorker();
        assertEquals(expResult, result);

        instance.setHasWorkerStatus();
        expResult = true;
        assertEquals(instance.hasWorker(), expResult);
    }

    /**
     * Test of hasBuilding method, of class Cell.
     */
    @Test
    public void testHasBuilding() {
        System.out.println("hasBuilding");
        Cell instance = new Cell();
        boolean expResult = false;
        boolean result = instance.hasBuilding();
        assertEquals(expResult, result);

        Building building = new IBlock();
        instance.changeBuildingStatus(building);
        result = instance.hasBuilding();
        expResult = true;
        assertEquals(expResult, result);
    }

    /**
     * Test of getBuildingType method, of class Cell.
     */
    @Test
    public void testGetBuildingType() {
        System.out.println("getBuildingType");
        Cell instance = new Cell();
        Building expResult = null;
        char result = instance.getBuildingType();
        assertEquals(expResult, result);

        Building building = new IBlock();
        instance.changeBuildingStatus(building);
        expResult = building;
        result = instance.getBuildingType();
        assertEquals(expResult, result);
    }

    /**
     * Test of getX method, of class Cell.
     */
    @Test
    public void testGetX() {
        System.out.println("getX");
        Cell instance = new Cell(1, 2);
        int expResult = 1;
        int result = instance.getX();
        assertEquals(expResult, result);
    }

    /**
     * Test of getY method, of class Cell.
     */
    @Test
    public void testGetY() {
        System.out.println("getY");
        Cell instance = new Cell(5, 4);
        int expResult = 4;
        int result = instance.getY();
        assertEquals(expResult, result);
    }

}
