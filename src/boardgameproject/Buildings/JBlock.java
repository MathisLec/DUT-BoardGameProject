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
        buildingShape(1, 0);
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
    public void rotateBuildingLeft(int x, int y) {
        cells.clear();
        Cell cell;
        switch (state) {
            case TOP:
                for (int i = 0; i < 3; i++) {
                    cell = new Cell(x + i, y);
                    super.cells.add(cell);
                }
                cell = new Cell(x + 2, y + 1);
                super.cells.add(cell);
                break;
            case BOTTOM:
                for (int i = 0; i < 3; i++) {
                    cell = new Cell(x, y + i);
                    super.cells.add(cell);
                }
                cell = new Cell(x + 1, y);
                super.cells.add(cell);
                break;
            case LEFT:
                for (int i = 0; i < 3; i++) {
                    cell = new Cell(x + i, y);
                    super.cells.add(cell);
                }
                cell = new Cell(x, y);
                super.cells.add(cell);
                break;
            case RIGHT:
                for (int i = 0; i < 3; i++) {
                    cell = new Cell(x, y + i);
                    super.cells.add(cell);
                }
                cell = new Cell(x, y + 2);
                super.cells.add(cell);
                break;
        }
        changeStateRotateLeft();
    }

    @Override
    public void rotateBuildingRight(int x, int y) {
        cells.clear();
        Cell cell;
        switch (state) {
            case TOP:
                for (int i = 0; i < 3; i++) {
                    cell = new Cell(x, y + i);
                    super.cells.add(cell);
                }
                cell = new Cell(x + 1, y);
                super.cells.add(cell);
                break;
            case BOTTOM:
                for (int i = 0; i < 3; i++) {
                    cell = new Cell(x + i, y);
                    super.cells.add(cell);
                }
                cell = new Cell(x + 2, y + 1);
                super.cells.add(cell);
                break;
            case LEFT:
                for (int i = 0; i < 3; i++) {
                    cell = new Cell(x, y + i);
                    super.cells.add(cell);
                }
                cell = new Cell(x, y + 2);
                super.cells.add(cell);
                break;
            case RIGHT:
                for (int i = 0; i < 3; i++) {
                    cell = new Cell(x + i, y);
                    super.cells.add(cell);
                }
                cell = new Cell(x, y);
                super.cells.add(cell);
                break;
        }
        changeStateRotateRight();
    }

    @Override
    public void drawBuilding(Canvas c) {
        for (Cell s : cells) {
            s.drawCell(c, s.getX(), s.getY());
        }
    }

}
