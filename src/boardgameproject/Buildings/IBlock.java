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
public class IBlock extends Building {

    public IBlock() {
        super();
        super.role = 'I';
        super.origineX = 0;
        super.origineY = 0;
        super.materialCost = 4;
        super.energyConsume = 1;
    }

    public IBlock(Board board, int x, int y, Canvas c) {
        super.role = 'I';
        super.origineX = x;
        super.origineY = y;
        super.materialCost = 4;
        super.energyConsume = 1;
        buildingShape(origineX, origineY);
    }

    public IBlock(ArrayList<Cell> list) {
        super(list);
        super.role = 'I';
    }

    @Override
    public void buildingShape(int x, int y) {
        for (int i = 0; i < 4; i++) {
            cells.add(new Cell(x, y + i));
        }
    }

    @Override
    public void drawBuilding(Canvas c) {
        for (Cell s : cells) {
            s.drawCell(c, s.getX(), s.getY(), Color.RED);
        }
    }

    @Override
    public void buildingRole(Player player, Board board) {
        int energyToAdd = 2;
        player.addEnergy(energyToAdd);
    }

    @Override
    public void rotateBuildingLeft() {
        cells.clear();
        switch (state) {
            case TOP:
            case BOTTOM:
                for (int i = 0; i < 4; i++) {
                    cells.add(new Cell(origineX + i, origineY));
                }
                break;
            case LEFT:
            case RIGHT:
                for (int i = 0; i < 4; i++) {
                    cells.add(new Cell(origineX, origineY + i));
                }
                break;
        }
        changeStateRotateLeft();
    }

    @Override
    public void rotateBuildingRight() {
        cells.clear();
        switch (state) {
            case TOP:
            case BOTTOM:
                for (int i = 0; i < 4; i++) {
                    cells.add(new Cell(origineX + i, origineY));
                }
                break;
            case LEFT:
            case RIGHT:
                for (int i = 0; i < 4; i++) {
                    cells.add(new Cell(origineX, origineY + i));
                }
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
                case BOTTOM:
                    for (int i = 0; i < 4; i++) {
                        shape.add(board.getBoard()[x + i][y]);
                    }
                    break;
                case LEFT:
                case RIGHT:
                    for (int i = 0; i < 4; i++) {
                        shape.add(board.getBoard()[x][y + i]);
                    }
                    break;
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            shape = new ArrayList<>();
            System.out.println("ça sort du cadre légal");
        }
        cells.addAll(shape);
        return shape;
    }

}
