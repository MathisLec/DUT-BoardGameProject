/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardgameproject.Buildings;

import boardgameproject.Board;
import boardgameproject.Cell;
import boardgameproject.Player;
import java.util.ArrayList;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

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
    }

    public JBlock(ArrayList<Cell> list) {
        super(list);
        super.role = 'J';
    }

    @Override
    public void buildingShape(int x, int y) {
        for (int i = 0; i < 3; i++) {
            cells.add(new Cell(x, y + i));
        }
        cells.add(new Cell(x - 1, y + 2));
    }
    
    @Override
     public void drawBuilding(Canvas c) {
        for (Cell s : cells) {
            s.drawCell(c, s.getX(), s.getY(),Color.GREEN);
        }
    }

    @Override
    public void buildingRole(Player player, Board board) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void rotateBuildingLeft() {
        cells.clear();
        switch (state) {
            case TOP:
                origineX = 0;
                origineY = 0;
                for (int i = 0; i < 3; i++) {
                    cells.add(new Cell(origineX + i, origineY));
                }
                cells.add(new Cell(origineX + 2, origineY + 1));
                break;
            case BOTTOM:
                origineX = 0;
                origineY = 0;
                for (int i = 0; i < 3; i++) {
                    cells.add(new Cell(origineX + i, origineY + 1));
                }
                cells.add(new Cell(origineX, origineY));
                break;
            case LEFT:
                origineX = 0;
                origineY = 0;
                for (int i = 0; i < 3; i++) {
                    cells.add(new Cell(origineX, origineY + i));
                }
                cells.add(new Cell(origineX + 1, origineY));
                break;
            case RIGHT:
                origineX = 1;
                origineY = 0;
                for (int i = 0; i < 3; i++) {
                    cells.add(new Cell(origineX, origineY + i));
                }
                cells.add(new Cell(origineX - 1, origineY + 2));
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
                    cells.add(new Cell(origineX + i, origineY + 1));
                }
                cells.add(new Cell(origineX, origineY));
                break;
            case BOTTOM:
                origineX = 0;
                origineY = 0;
                for (int i = 0; i < 3; i++) {
                    cells.add(new Cell(origineX + i, origineY));
                }
                cells.add(new Cell(origineX + 2, origineY + 1));
                break;
            case LEFT:
                origineX = 1;
                origineY = 0;
                for (int i = 0; i < 3; i++) {
                    cells.add(new Cell(origineX, origineY + i));
                }
                cells.add(new Cell(origineX - 1, origineY + 2));
                break;
            case RIGHT:
                origineX = 0;
                origineY = 0;
                for (int i = 0; i < 3; i++) {
                    cells.add(new Cell(origineX, origineY + i));
                }
                cells.add(new Cell(origineX + 1, origineY));
                break;
        }
        changeStateRotateRight();
    }

    @Override
    public ArrayList<Cell> getPreviewsShape(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deplaceBuilding(double x, double y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
