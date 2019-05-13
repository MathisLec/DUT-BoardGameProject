/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardgameproject.Buildings;

import boardgameproject.Cell;
import java.util.ArrayList;
import javafx.scene.canvas.Canvas;

/**
 *
 * @author mlecoeuvre
 */
public class IBlock extends Building {

    public IBlock() {
        super();
        super.role = 'I';
        buildingShape(1,0);
    }

    public IBlock(ArrayList<Cell> list) {
        super(list);
        super.role = 'I';  
    }

    @Override
    public void buildingShape(int x, int y) {
        for (int i = 0; i < 4; i++) {
            Cell cell = new Cell(x, y + i);
            super.cells.add(cell);
        }
    }

    @Override
    protected void buildingRole() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void rotateBuildingLeft(int x, int y) {
        cells.clear();
        switch (state) {
            case TOP:
            case BOTTOM:
                for (int i = 0; i < 4; i++) {
                    Cell cell = new Cell(x + i, y);
                    super.cells.add(cell);
                }
                break;
            case LEFT:
            case RIGHT:
                for (int i = 0; i < 4; i++) {
                    Cell cell = new Cell(x, y + i);
                    super.cells.add(cell);
                }
                break;
        }
        changeStateRotateLeft();
    }

    @Override
    public void rotateBuildingRight(int x, int y) {
        rotateBuildingLeft(x, y);
        changeStateRotateRight();
    }

    @Override
    public void drawBuilding(Canvas c) {
        for(Cell s : cells){
            s.drawCell(c, s.getX(), s.getY());
        }
    }

}
