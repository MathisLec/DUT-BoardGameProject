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

    /**
     * Constructor
     */
    public Cell(int x, int y) {
        this.hasWorker = false;
        this.hasBuilding = false;
        this.buildingType = 'B';
        this.x = x;
        this.y = y;
    }

    /**
     * Draw the Cells of the Board or of the Buildings
     * @param c the Canvas where the Cells will be drawn
     * @param x posX of the Cell
     * @param y posY of the Cell
     * @param col the Color of the Cell
     * @param cellSize the Size of The Cells drawn
     */
    public void drawCell(Canvas c, int x, int y, Color col, int cellSize) {
        GraphicsContext gc = c.getGraphicsContext2D();
        gc.setFill(col);
        gc.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
        gc.setStroke(Color.BLACK);
        gc.strokeRect(x * cellSize, y * cellSize, cellSize, cellSize);
    }

    /**
     * Set the WorkerStatus of the Cell to true
     */
    public void setHasWorkerStatus() {
        hasWorker = true;
    }

    /**
     * Set the WorkerStatus of the Cell to true
     */
    public void setHasNotWorkerStatus() {
        hasWorker = false;
    }

    /**
     * This method change the Type of the Cell to make it a part of the 
     * building which is built on it
     * @param building
     */
    public void changeBuildingStatus(Building building) {
        hasBuilding = !hasBuilding;
        buildingType = building.getRole();
    }

    /**
     * This method change the Type of the Cell to make it a part of the 
     * building which is built on it
     * @param role
     */
    public void changeBuildingStatus(char role) {
        hasBuilding = !hasBuilding;
        buildingType = role;
    }

    /**
     *
     * @return true if the Cell has a Worker on it
     */
    public boolean hasWorker() {
        return hasWorker;
    }

    /**
     *
     * @return true if the Cell has a Building on it
     */
    public boolean hasBuilding() {
        return hasBuilding;
    }

    /**
     *
     * @return The Type of the Building
     */
    public char getBuildingType() {
        return buildingType;
    }

    /**
     *
     * @return X
     */
    public int getX() {
        return x;
    }

    /**
     *
     * @return Y
     */
    public int getY() {
        return y;
    }

    /**
     * Set the value of X
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Set the value of Y
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }
}
