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
 * @author mlecoeuvre
 */
public class PlayerTest {

    public PlayerTest() {
    }

    /**
     * Test of placeBuilding method, of class Player.
     */
    @Test
    public void testPlaceBuilding() {
        System.out.println("placeBuilding");
        Player instance = new Player();
        Building building = instance.getBuildings().get(0);
        instance.placeBuilding(building);
        assertEquals(instance.getBuildings().size(), 4);
    }

    /**
     * Test of addWorkerInHand method, of class Player.
     */
    @Test
    public void testAddWorkerInHand() {
        System.out.println("addWorkerInHand");
        int nb = 1;
        Player instance = new Player();
        instance.addWorkerInHand(nb);
        assertEquals(instance.getNbWorkers(), 9);
    }

    /**
     * Test of placeWorker method, of class Player.
     */
    @Test
    public void testPlaceWorker() {
        System.out.println("placeWorker");
        Player instance = new Player();
        instance.placeWorker();
        assertEquals(instance.getNbWorkers(), 7);
    }

    /**
     * Test of consumeEnergy method, of class Player.
     */
    public void testConsumeEnergy() throws Exception {
        System.out.println("consumeEnergy");
        int nb = 1;
        Player instance = new Player();
        instance.consumeEnergy(nb);
        assertEquals(instance.getNbEnergy(), 15);
    }

    /**
     * Test of consumeMaterial method, of class Player.
     */
    @Test
    public void testConsumeMaterial() throws Exception {
        System.out.println("consumeMaterial");
        int nb = 1;
        Player instance = new Player();
        instance.consumeMaterial(nb);
        assertEquals(instance.getNbMaterials(), 7);
    }

    /**
     * Test of consumeMoney method, of class Player.
     */
    @Test
    public void testConsumeMoney() throws Exception {
        System.out.println("consumeMoney");
        int nb = 1;
        Player instance = new Player();
        instance.consumeMoney(nb);
        assertEquals(instance.getNbMoney(), 4);
    }

    /**
     * Test of consumeResearch method, of class Player.
     */
    @Test
    public void testConsumeResearch() throws Exception {
        System.out.println("consumeResearch");
        int nb = 1;
        Player instance = new Player();
        try {
            instance.consumeResearch(nb);
        } catch (Exception ex) {
            System.out.println("Test ConsumeResearch passed");
        }
    }

    /**
     * Test of consumeStellarium method, of class Player.
     */
    @Test
    public void testConsumeStellarium() throws Exception {
        System.out.println("consumeStellarium");
        int nb = 1;
        Player instance = new Player();
        try {
            instance.consumeStellarium(nb);
        } catch (Exception ex) {
            System.out.println("Test ConsumeStellarium passed");
        }
    }

    /**
     * Test of addEnergy method, of class Player.
     */
    @Test
    public void testAddEnergy() {
        System.out.println("addEnergy");
        int nb = 1;
        Player instance = new Player();
        instance.addEnergy(nb);
        assertEquals(instance.getNbEnergy(), 17);
    }

    /**
     * Test of addMaterial method, of class Player.
     */
    @Test
    public void testAddMaterial() {
        System.out.println("addMaterial");
        int nb = 1;
        Player instance = new Player();
        instance.addMaterial(nb);
        assertEquals(instance.getNbMaterials(), 9);
    }

    /**
     * Test of addMoney method, of class Player.
     */
    @Test
    public void testAddMoney() {
        System.out.println("addMoney");
        int nb = 1;
        Player instance = new Player();
        instance.addMoney(nb);
        assertEquals(instance.getNbMoney(), 6);
    }

    /**
     * Test of addResearch method, of class Player.
     */
    @Test
    public void testAddResearch() {
        System.out.println("addResearch");
        int nb = 1;
        Player instance = new Player();
        instance.addResearch(nb);
        assertEquals(instance.getNbResearch(), 1);
    }

    /**
     * Test of addStellarium method, of class Player.
     */
    @Test
    public void testAddStellarium() {
        System.out.println("addStellarium");
        int nb = 1;
        Player instance = new Player();
        instance.addStellarium(nb);
        assertEquals(instance.getNbStellarium(), 1);
    }

    /**
     * Test of addTurnSpacePort method, of class Player.
     */
    @Test
    public void testAddTurnSpacePort() {
        System.out.println("addTurnSpacePort");
        Player instance = new Player();
        instance.addTurnSpacePort();
        assertEquals(instance.getNbTurnSpacePort(), 1);
    }

    /**
     * Test of increaseNbWorkerToPlace method, of class Player.
     */
    @Test
    public void testIncreaseNbWorkerToPlace() {
        System.out.println("increaseNbWorkerToPlace");
        Player instance = new Player();
        instance.increaseNbWorkerToPlace();
        assertEquals(instance.getNbWorkerToPlace(), 2);
    }

    /**
     * Test of decreaseNbWorkerToPlace method, of class Player.
     */
    @Test
    public void testDecreaseNbWorkerToPlace() {
        System.out.println("decreaseNbWorkerToPlace");
        Player instance = new Player();
        instance.decreaseNbWorkerToPlace();
        assertEquals(instance.getNbWorkerToPlace(), 0);
    }

    /**
     * Test of setNbWorkerToPlaceByDefault method, of class Player.
     */
    @Test
    public void testSetNbWorkerToPlaceByDefault() {
        System.out.println("setNbWorkerToPlaceByDefault");
        Player instance = new Player();
        instance.setNbWorkerToPlaceByDefault();
        assertEquals(instance.getNbWorkerToPlace(), 1);
    }

    /**
     * Test of drawBuilding method, of class Player.
     */
    @Test
    public void testDrawBuilding() {
        System.out.println("drawBuilding");
        Player instance = new Player();
        instance.drawBuilding();
        assertEquals(instance.getBuildings().size(), 5);
    }

    /**
     * Test of putBuildingFromHandToPile method, of class Player.
     */
    @Test
    public void testPutBuildingFromHandToPile() {
        System.out.println("putBuildingFromHandToPile");
        Player instance = new Player();
        Building b = instance.getBuildings().get(0);
        instance.putBuildingFromHandToPile(b);
        assertFalse(instance.getBuildings().contains(b));
    }

    /**
     * Test of increaseNbBuildingToReturn method, of class Player.
     */
    @Test
    public void testIncreaseNbBuildingToReturn() {
        System.out.println("increaseNbBuildingToReturn");
        Player instance = new Player();
        instance.increaseNbBuildingToReturn();
        assertEquals(instance.getNbBuildingToReturn(), 1);
    }

    /**
     * Test of decreaseNbBuildingToReturn method, of class Player.
     */
    @Test
    public void testDecreaseNbBuildingToReturn() {
        System.out.println("decreaseNbBuildingToReturn");
        Player instance = new Player();
        instance.decreaseNbBuildingToReturn();
        assertEquals(instance.getNbBuildingToReturn(), 0);
    }

    /**
     * Test of setNbBuildingToReturnByDefault method, of class Player.
     */
    @Test
    public void testSetNbBuildingToReturnByDefault() {
        System.out.println("setNbBuildingToReturnByDefault");
        Player instance = new Player();
        instance.setNbBuildingToReturnByDefault();
        assertEquals(instance.getNbBuildingToReturn(), 0);
    }

    /**
     * Test of allowToPlaceWorker method, of class Player.
     */
    @Test
    public void testAllowToPlaceWorker() {
        System.out.println("allowToPlaceWorker");
        Player instance = new Player();
        instance.allowToPlaceWorker();
        assertTrue(instance.isAllowToPlaceWorker());
    }

    /**
     * Test of disallowToPlaceWorker method, of class Player.
     */
    @Test
    public void testDisallowToPlaceWorker() {
        System.out.println("disallowToPlaceWorker");
        Player instance = new Player();
        instance.disallowToPlaceWorker();
        assertFalse(instance.isAllowToPlaceWorker());
    }

    /**
     * Test of getPileSize method, of class Player
     */
    @Test
    public void testGetPileSize() {
        System.out.println("getPileSize");
        Player instance = new Player();
        assertEquals(46, instance.getPileSize());
    }

}
