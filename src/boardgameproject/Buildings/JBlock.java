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
public class JBlock extends Building {

    public JBlock() {
        super();
        super.role = 'J';
        super.origineX = 1;
        super.origineY = 0;
        buildingShape(origineX, origineY);
    }

    public JBlock(ArrayList<Cell> list) {
        super(list);
        super.role = 'J';
    }

    @Override
    public void buildingShape(int x, int y) {
        for (int i = 0; i < 3; i++) {
            Cell cell = new Cell(x, y + i);
            super.cells.add(cell);
        }
        Cell cell = new Cell(x - 1, y + 2);
        super.cells.add(cell);
    }

    @Override
    protected void buildingRole() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void rotateBuildingLeft() {
        cells.clear();
        Cell cell;
        switch (state) {
            case TOP:
                origineX = 0;
                origineY = 0;
                for (int i = 0; i < 3; i++) {
                    cell = new Cell(origineX + i, origineY);
                    super.cells.add(cell);
                }
                cell = new Cell(origineX + 2, origineY + 1);
                super.cells.add(cell);
                break;
            case BOTTOM:
                origineX = 0;
                origineY = 0;
                for (int i = 0; i < 3; i++) {
                    cell = new Cell(origineX + i, origineY + 1);
                    super.cells.add(cell);
                }
                cell = new Cell(origineX, origineY);
                super.cells.add(cell);
                break;
            case LEFT:
                origineX = 0;
                origineY = 0;
                for (int i = 0; i < 3; i++) {
                    cell = new Cell(origineX, origineY + i);
                    super.cells.add(cell);
                }
                cell = new Cell(origineX + 1, origineY);
                super.cells.add(cell);
                break;
            case RIGHT:
                origineX = 1;
                origineY = 0;
                for (int i = 0; i < 3; i++) {
                    cell = new Cell(origineX, origineY + i);
                    super.cells.add(cell);
                }
                cell = new Cell(origineX - 1, origineY + 2);
                super.cells.add(cell);
                break;
        }
        changeStateRotateLeft();
    }

    @Override
    public void rotateBuildingRight() {
        cells.clear();
        Cell cell;
        switch (state) {
            case TOP:
                origineX = 0;
                origineY = 0;
                for (int i = 0; i < 3; i++) {
                    cell = new Cell(origineX + i, origineY + 1);
                    super.cells.add(cell);
                }
                cell = new Cell(origineX, origineY);
                super.cells.add(cell);
                break;
            case BOTTOM:
                origineX = 0;
                origineY = 0;
                for (int i = 0; i < 3; i++) {
                    cell = new Cell(origineX + i, origineY);
                    super.cells.add(cell);
                }
                cell = new Cell(origineX + 2, origineY + 1);
                super.cells.add(cell);
                break;
            case LEFT:
                origineX = 1;
                origineY = 0;
                for (int i = 0; i < 3; i++) {
                    cell = new Cell(origineX, origineY + i);
                    super.cells.add(cell);
                }
                cell = new Cell(origineX - 1, origineY + 2);
                super.cells.add(cell);
                break;
            case RIGHT:
                origineX = 0;
                origineY = 0;
                for (int i = 0; i < 3; i++) {
                    cell = new Cell(origineX, origineY + i);
                    super.cells.add(cell);
                }
                cell = new Cell(origineX + 1, origineY);
                super.cells.add(cell);
                break;
        }
        changeStateRotateRight();
    }

}
