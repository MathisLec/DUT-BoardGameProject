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
public class IBlock extends Building {

    public IBlock() {
        super();
        super.role = 'I';
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
    public void rotateBuildingLeft() {
        switch (state) {
            case TOP:
            case BOTTOM:
            case LEFT:
            case RIGHT:
                for (Cell c : cells) {
                    int cache = c.getX();
                    c.setX(c.getY());
                    c.setY(cache);
                }
                break;
        }
        changeStateRotateLeft();
    }

    @Override
    public void rotateBuildingRight() {
        switch (state) {
            case TOP:
            case BOTTOM:
            case LEFT:
            case RIGHT:
                for (Cell c : cells) {
                    int cache = c.getX();
                    c.setX(c.getY());
                    c.setY(cache);
                }
                break;
        }
        changeStateRotateRight();
    }

}
