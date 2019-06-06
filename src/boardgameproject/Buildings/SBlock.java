/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardgameproject.Buildings;

import boardgameproject.Board;
import boardgameproject.Cell;
import boardgameproject.Exceptions.InsufficientRessourcesException;
import boardgameproject.Player;
import boardgameproject.Round;
import java.util.ArrayList;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

/**
 *
 * @author mlecoeuvre
 */
public class SBlock extends Building {

    public SBlock() {
        super();
        super.role = 'S';
        super.origineX = 0;
        super.origineY = 0;
        super.materialCost = 2;
    }

    @Override
    public void buildingShape(int x, int y) {
        for (int i = 0; i < 2; i++) {
            Cell cell = new Cell(x, y + i);
            Cell cell1 = new Cell(x + 1, y + 1 + i);
            cells.add(cell);
            cells.add(cell1);
        }
    }

    @Override
    public void drawBuilding(Canvas c, int cellSize) {
        for (Cell s : cells) {
            s.drawCell(c, s.getX(), s.getY(), Color.GREEN, cellSize);
        }
    }

    @Override
    public void buildingRole(Player player, Board board, Round round) throws InsufficientRessourcesException {
        switch (state) {
            case TOP:
            case BOTTOM:
                int nbMaterialToAdd = player.getNbResearch();
                int nbEnergyToConsume = 2;
                int nbMoneyToConsume = 1;
                int nbStellariumToAdd = 1;
                player.consummeEnergy(nbEnergyToConsume);
                player.consummeMoney(nbMoneyToConsume);
                player.addMaterial(nbMaterialToAdd);
                if (player.getNbResearch() == player.getLimitNbResearch()) {
                    player.addStellarium(nbStellariumToAdd);
                }
                break;
            case LEFT:
            case RIGHT:
                nbMaterialToAdd = 1;
                player.addMaterial(nbMaterialToAdd);
                break;
        }
    }

    @Override
    public void rotateBuildingLeft() {
        cells.clear();
        switch (state) {
            case TOP:
            case BOTTOM:
                origineX = 0;
                origineY = 1;
                for (int i = 0; i < 2; i++) {
                    cells.add(new Cell(origineX + i, origineY));
                    cells.add(new Cell(origineX + 1 + i, origineY - 1));
                }
                break;
            case LEFT:
            case RIGHT:
                origineX = 0;
                origineY = 0;
                for (int i = 0; i < 2; i++) {
                    cells.add(new Cell(origineX, origineY + i));
                    cells.add(new Cell(origineX + 1, origineY + 1 + i));
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
                origineX = 0;
                origineY = 1;
                for (int i = 0; i < 2; i++) {
                    cells.add(new Cell(origineX + i, origineY));
                    cells.add(new Cell(origineX + 1 + i, origineY - 1));
                }
                break;
            case LEFT:
            case RIGHT:
                origineX = 0;
                origineY = 0;
                for (int i = 0; i < 2; i++) {
                    cells.add(new Cell(origineX, origineY + i));
                    cells.add(new Cell(origineX + 1, origineY + 1 + i));
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
                    for (int i = 0; i < 2; i++) {
                        shape.add(board.getBoard()[x + i][y]);
                        shape.add(board.getBoard()[x + 1 + i][y + 1]);
                    }
                    break;
                case LEFT:
                case RIGHT:
                    for (int i = 0; i < 2; i++) {
                        shape.add(board.getBoard()[x][y + i]);
                        shape.add(board.getBoard()[x + 1][y - i]);
                    }
                    break;
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            shape = new ArrayList<>();
        }
        return shape;
    }

}
