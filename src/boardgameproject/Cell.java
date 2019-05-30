/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardgameproject;

import boardgameproject.Buildings.Building;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author mlecoeuvre
 */
public class Cell {

    private boolean hasWorker;
    private boolean hasBuilding;
    private char buildingType;
    private boolean selectedCell;
    private int x, y;
    private final int cellShape = 30;

    public Cell() {
        this.hasWorker = false;
        this.hasBuilding = false;
        this.buildingType = 'B';
        this.selectedCell = false;
        this.x = 0;
        this.y = 0;
    }

    public Cell(int x, int y) {
        this.hasWorker = false;
        this.hasBuilding = false;
        this.buildingType = 'B';
        this.selectedCell = false;
        this.x = x;
        this.y = y;
    }

    public void setSelectedCell(boolean selectedCell) {
        this.selectedCell = selectedCell;
    }

    public boolean isSelectedCell() {
        return selectedCell;
    }
    
    public void deplaceCell(double x, double y){
        this.x = (int)x;
        this.y = (int)y;
    }

    public int getCellShape() {
        return cellShape;
    }
    
    public void drawCell(Canvas c,int x, int y, Color col){
        GraphicsContext gc = c.getGraphicsContext2D();
        gc.setFill(col);
        gc.fillRect(x*cellShape, y*cellShape, cellShape, cellShape);
        gc.setFill(Color.BLACK);
        gc.strokeRect(x*cellShape, y*cellShape, cellShape, cellShape);
    }

    public void changeWorkerStatus() {
        hasWorker = !hasWorker;
    }

    public void changeBuildingStatus(Building building) {
        hasBuilding = !hasBuilding;
        buildingType = building.getRole();
    }
    
    public void changeBuildingStatus(char role) {
        hasBuilding = !hasBuilding;
        buildingType = role;
    }

    public void changeBuildingStatus() {
        hasBuilding = false;
        buildingType = 'B';
    }

    public boolean hasWorker() {
        return hasWorker;
    }

    public boolean hasBuilding() {
        return hasBuilding;
    }

    public char getBuildingType() {
        return buildingType;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

}
