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
public class TBlock extends Building {

    public TBlock() {
        super();
        super.role = 'T';
        super.origineX = 1;
        super.origineY = 0;
    }

    public TBlock(ArrayList<Cell> list) {
        super(list);
        super.role = 'T';
    }

    @Override
    public void buildingShape(Canvas c,int x, int y) {
        for (int i = 0; i < 3; i++) {
            cells.add(new Cell(x + i - 1, y + 1));
        }
        cells.add(new Cell(x, y));
    }
    
    @Override
     public void drawBuilding(Canvas c) {
        for (Cell s : cells) {
            s.drawCell(c, s.getX(), s.getY(),Color.BROWN);
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
                origineX = 1;
                origineY = 0;
                for (int i = 0; i < 3; i++) {
                    cells.add(new Cell(origineX, origineY + i));
                }
                cells.add(new Cell(origineX - 1, origineY + 1));
                break;
            case BOTTOM:
                origineX = 0;
                origineY = 0;
                for (int i = 0; i < 3; i++) {
                    cells.add(new Cell(origineX, origineY + i));
                }
                cells.add(new Cell(origineX + 1, origineY + 1));
                break;
            case LEFT:
                origineX = 0;
                origineY = 0;
                for (int i = 0; i < 3; i++) {
                    cells.add(new Cell(origineX + i, origineY));
                }
                cells.add(new Cell(origineX + 1, origineY + 1));
                break;
            case RIGHT:
                origineX = 1;
                origineY = 0;
                for (int i = 0; i < 3; i++) {
                    cells.add(new Cell(origineX - 1 + i, origineY + 1));
                }
                cells.add(new Cell(origineX, origineY));
                break;
        }
        changeStateRotateLeft();
    }

    @Override
    public void rotateBuildingRight() {
        cells.clear();
        switch (state) {
            case TOP:
                origineX = 0;
                origineY = 0;
                for (int i = 0; i < 3; i++) {
                    cells.add(new Cell(origineX, origineY + i));
                }
                cells.add(new Cell(origineX + 1, origineY + 1));
                break;
            case BOTTOM:
                origineX = 1;
                origineY = 0;
                for (int i = 0; i < 3; i++) {
                    cells.add(new Cell(origineX, origineY + i));
                }
                cells.add(new Cell(origineX - 1, origineY + 1));
                break;
            case LEFT:
                origineX = 1;
                origineY = 0;
                for (int i = 0; i < 3; i++) {
                    cells.add(new Cell(origineX - 1 + i, origineY + 1));
                }
                cells.add(new Cell(origineX, origineY));
                break;
            case RIGHT:
                origineX = 0;
                origineY = 0;
                for (int i = 0; i < 3; i++) {
                    cells.add(new Cell(origineX + i, origineY));
                }
                cells.add(new Cell(origineX + 1, origineY + 1));
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
