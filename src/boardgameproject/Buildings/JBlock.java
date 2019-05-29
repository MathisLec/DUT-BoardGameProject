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
        super.materialCost = 8;
        super.energyConsume = 4;
    }

    public JBlock(ArrayList<Cell> list) {
        super(list);
        super.role = 'J';
        super.materialCost = 8;
        super.energyConsume = 4;
    }

    @Override
    public void buildingShape(int x, int y) {
        for (int i = 0; i < 3; i++) {
            cells.add(new Cell(x+1, y + i));
        }
        cells.add(new Cell(x, y + 2));
    }

    @Override
    public void drawBuilding(Canvas c) {
        for (Cell s : cells) {
            s.drawCell(c, s.getX(), s.getY(), Color.BLUE);
        }
    }

    @Override
    public void buildingRole(Player player, Board board) {
        player.drawBuilding();
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
    public ArrayList<Cell> getPreviewsShape(Board board, int x, int y) {
        ArrayList<Cell> shape = new ArrayList<>();
        try {
            switch (state) {
                case TOP:
                    for (int i = 0; i < 3; i++) {
                        shape.add(board.getBoard()[x + i][y]);
                    }
                    shape.add(board.getBoard()[x + 2][y - 1]);
                    break;
                case BOTTOM:
                    for (int i = 0; i < 3; i++) {
                        shape.add(board.getBoard()[x + i][y]);
                    }
                    shape.add(board.getBoard()[x][y + 1]);
                    break;
                case LEFT:
                    for (int i = 0; i < 3; i++) {
                        shape.add(board.getBoard()[x][y + i]);
                    }
                    shape.add(board.getBoard()[x + 1][y + 2]);
                    break;
                case RIGHT:
                    for (int i = 0; i < 3; i++) {
                        shape.add(board.getBoard()[x + 1][y + i]);
                    }
                    shape.add(board.getBoard()[x][y]);
                    break;
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            shape = new ArrayList<>();
            System.out.println("ça sort du cadre légal");
        }
        return shape;
    }

}
