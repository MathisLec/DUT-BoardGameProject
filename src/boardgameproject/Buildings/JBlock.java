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
public class JBlock extends Building {

    public JBlock() {
        super();
        super.role = 'J';
        super.origineX = 1;
        super.origineY = 0;
        super.materialCost = 8;
    }

    @Override
    public void buildingShape(int x, int y) {
        for (int i = 0; i < 3; i++) {
            cells.add(new Cell(x + 1, y + i));
        }
        cells.add(new Cell(x, y + 2));
    }

    @Override
    public void drawBuilding(Canvas c, int cellSize) {
        for (Cell s : cells) {
            s.drawCell(c, s.getX(), s.getY(), Color.BLUE, cellSize);
        }
    }

    @Override
    public void buildingRole(Player player, Board board, Round round) throws InsufficientRessourcesException {
        switch (state) {
            case TOP:
                int nbEnergyToConsume = 4;
                player.consummeEnergy(nbEnergyToConsume);
                player.drawBuilding();
                break;
            case BOTTOM:
                nbEnergyToConsume = player.getNbResearch();
                int nbMoneyToConsume = player.getNbResearch();
                int nbWorkerToAdd = (int) player.getNbResearch() / 2;
                player.consummeEnergy(nbEnergyToConsume);
                player.consummeMoney(nbMoneyToConsume);
                player.addWorkerInHand(nbWorkerToAdd);
                break;
            case LEFT:
                nbEnergyToConsume = 2;
                nbMoneyToConsume = 2;
                int nbResearchToAdd = 1;
                player.consummeEnergy(nbEnergyToConsume);
                player.consummeMoney(nbMoneyToConsume);
                player.addResearch(nbResearchToAdd);
                break;
            case RIGHT:
                int nbEnergyToAdd = 4;
                int nbMoneyToAdd = 4;
                this.clearWorkers();
                player.addEnergy(nbEnergyToAdd);
                player.addMoney(nbMoneyToAdd);
                break;
        }
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
        }
        return shape;
    }

}
