/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardgameproject;

import boardgameproject.Buildings.Building;
import java.io.Serializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author mlecoeuvre
 */
public class Cell implements Serializable {

    private boolean hasWorker;
    private boolean hasBuilding;
    private char buildingType;
    private int x, y;

    public Cell() {
        this.hasWorker = false;
        this.hasBuilding = false;
        this.buildingType = 'B';
        this.x = 0;
        this.y = 0;
    }

    public Cell(int x, int y) {
        this.hasWorker = false;
        this.hasBuilding = false;
        this.buildingType = 'B';
        this.x = x;
        this.y = y;
    }

    /**
     *
     * @param x
     * @param y
     */
    public void deplaceCell(double x, double y) {
        this.x = (int) x;
        this.y = (int) y;
    }

    /**
     *
     * @param c
     * @param x
     * @param y
     * @param col
     * @param cellSize
     */
    public void drawCell(Canvas c, int x, int y, Color col, int cellSize) {
        GraphicsContext gc = c.getGraphicsContext2D();
        gc.setFill(col);
        gc.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
        gc.setStroke(Color.BLACK);
        gc.strokeRect(x * cellSize, y * cellSize, cellSize, cellSize);
    }

    /**
     *
     */
    public void setHasWorkerStatus() {
        hasWorker = true;
    }

    /**
     *
     */
    public void setHasNotWorkerStatus() {
        hasWorker = false;
    }

    /**
     *
     * @param building
     */
    public void changeBuildingStatus(Building building) {
        hasBuilding = !hasBuilding;
        buildingType = building.getRole();
    }

    /**
     *
     * @param role
     */
    public void changeBuildingStatus(char role) {
        hasBuilding = !hasBuilding;
        buildingType = role;
    }

    /**
     *
     */
    public void changeBuildingStatus() {
        hasBuilding = false;
        buildingType = 'B';
    }

    /**
     *
     * @return
     */
    public boolean hasWorker() {
        return hasWorker;
    }

    /**
     *
     * @return
     */
    public boolean hasBuilding() {
        return hasBuilding;
    }

    /**
     *
     * @return
     */
    public char getBuildingType() {
        return buildingType;
    }

    /**
     *
     * @return
     */
    public int getX() {
        return x;
    }

    /**
     *
     * @return
     */
    public int getY() {
        return y;
    }

    /**
     *
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     *
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }
}
