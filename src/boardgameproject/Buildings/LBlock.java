/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardgameproject.Buildings;

import boardgameproject.Cell;
import java.util.ArrayList;

/**
 *
 * @author mlecoeuvre
 */
public class LBlock extends Building {

    public LBlock() {
        super();
        super.role = 'L';
        super.origineX = 0;
        super.origineY = 0;
        buildingShape(origineX, origineY);
    }

    public LBlock(ArrayList<Cell> list) {
        super(list);
        super.role = 'L';
    }

    @Override
    public void buildingShape(int x, int y) {
        for (int i = 0; i < 3; i++) {
            cells.add(new Cell(x, y + i));
        }
        cells.add(new Cell(x + 1, y + 2));
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
                origineX = 2;
                origineY = 0;
                for (int i = 0; i < 3; i++) {
                    cells.add(new Cell(origineX - 2 + i, origineY + 1));
                }
                cells.add(new Cell(origineX, origineY));
                break;
            case BOTTOM:
                origineX = 0;
                origineY = 0;
                for (int i = 0; i < 3; i++) {
                    cells.add(new Cell(origineX + i, origineY));
                }
                cells.add(new Cell(origineX, origineY + 1));
                break;
            case LEFT:
                origineX = 0;
                origineY = 0;
                for (int i = 0; i < 3; i++) {
                    cells.add(new Cell(origineX + 1, origineY + i));
                }
                cells.add(new Cell(origineX, origineY));
                break;
            case RIGHT:
                origineX = 0;
                origineY = 0;
                for (int i = 0; i < 3; i++) {
                    cells.add(new Cell(origineX, origineY + i));
                }
                cells.add(new Cell(origineX + 1, origineY + 2));
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
                    cells.add(new Cell(origineX + i, origineY));
                }
                cells.add(new Cell(origineX, origineY + 1));
                break;
            case BOTTOM:
                origineX = 2;
                origineY = 0;
                for (int i = 0; i < 3; i++) {
                    cells.add(new Cell(origineX - 2 + i, origineY + 1));
                }
                cells.add(new Cell(origineX, origineY));
                break;
            case LEFT:
                origineX = 0;
                origineY = 0;
                for (int i = 0; i < 3; i++) {
                    cells.add(new Cell(origineX, origineY + i));
                }
                cells.add(new Cell(origineX + 1, origineY + 2));
                break;
            case RIGHT:
                origineX = 0;
                origineY = 0;
                for (int i = 0; i < 3; i++) {
                    cells.add(new Cell(origineX + 1, origineY + i));
                }
                cells.add(new Cell(origineX, origineY));
                break;
        }
        changeStateRotateRight();
    }

}
