/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardgameproject.Buildings;

import boardgameproject.Cell;
import javafx.scene.canvas.Canvas;

/**
 *
 * @author mlecoeuvre
 */
public class OBlock extends Building {

    @Override
    public void buildingShape(int x, int y) {
        for (int i = 0; i < 4; i++) {
            Cell cell = new Cell(x + i, y + i);
        }
    }

    @Override
    protected void buildingRole() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void rotateBuildingLeft(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void rotateBuildingRight(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void drawBuilding(Canvas c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
