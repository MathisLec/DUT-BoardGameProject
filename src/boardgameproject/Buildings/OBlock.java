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
public class OBlock extends Building {

    public OBlock() {
        super();
        super.role = 'O';
        super.origineX = 0;
        super.origineY = 0;
        buildingShape(origineX, origineY);
    }

    public OBlock(ArrayList<Cell> list) {
        super(list);
        super.role = 'O';
    }

    @Override
    public void buildingShape(int x, int y) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                Cell cell = new Cell(x + i, y + j);
                cells.add(cell);
            }
        }
    }

    @Override
    protected void buildingRole() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void rotateBuildingLeft() {
    }

    @Override
    public void rotateBuildingRight() {
    }

}
