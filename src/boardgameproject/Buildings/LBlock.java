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
    }

    public LBlock(ArrayList<Cell> list) {
        super(list);
        super.role = 'L';
    }
    @Override
    public void buildingShape(int x, int y) {
        for (int i = 0; i < 3; i++) {
            Cell cell = new Cell(x, y + i);
            super.cells.add(cell);
        }
        Cell cell = new Cell(x + 1, y + 2);
        super.cells.add(cell);
    }

    @Override
    protected void buildingRole() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void rotateBuildingLeft() {
        switch (state) {
            case TOP:
                for (int i = 0; i < 3; i++) {
                    Cell cell = cells.get(i);
                    int cache = cell.getX();
                    cell.setX(cell.getY());
                    cell.setY(cache+1);
                }
                Cell cell = super.cells.get(3);
                cell.setX(cell.getX() + 1);
                cell.setY(cell.getY() + 1);
                break;
            case BOTTOM:
                for (int i = 0; i < 3; i++) {
                    cell = cells.get(i);
                    int cache = cell.getX();
                    cell.setX(cell.getY());
                    cell.setY(cache);
                }
                cell = super.cells.get(3);
                cell.setY(cell.getY() - 2);
                break;
            case LEFT:
                for (int i = 0; i < 3; i++) {
                    cell = cells.get(i);
                    int cache = cell.getX();
                    cell.setX(cell.getY() - 1);
                    cell.setY(cache);
                }
                cell = super.cells.get(3);
                cell.setX(cell.getX() - 1);
                cell.setY(cell.getY() - 1);
                break;
            case RIGHT:
                for (int i = 0; i < 3; i++) {
                    cell = cells.get(i);
                    int cache = cell.getX();
                    cell.setX(cell.getY() + 1);
                    cell.setY(cache);
                }
                cell = super.cells.get(3);
                cell.setX(cell.getX() - 2);
                cell.setY(cell.getY() + 1);
                break;
        }
        changeStateRotateLeft();
    }

    @Override
    public void rotateBuildingRight() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
