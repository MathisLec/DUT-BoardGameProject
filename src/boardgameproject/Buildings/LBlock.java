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
public class LBlock extends Building {

    public LBlock() {
        super();
        super.role = 'L';
        super.origineX = 0;
        super.origineY = 0;
        super.materialCost = 8;

    }

    @Override
    public void buildingShape(int x, int y) {
        for (int i = 0; i < 3; i++) {
            cells.add(new Cell(x, y + i));
        }
        cells.add(new Cell(x + 1, y + 2));
    }

    @Override
    public void drawBuilding(Canvas c) {
        for (Cell s : cells) {
            s.drawCell(c, s.getX(), s.getY(), Color.ORANGE);
        }
    }

    @Override
    public void buildingRole(Player player, Board board, Round round) throws InsufficientRessourcesException {
        switch (state) {
            case TOP:
                int nbWorkerToAdd = 3;
                int nbEnergyToConsume = 1;
                if (getNbWorker() == 2) {
                    player.consummeEnergy(nbEnergyToConsume);
                    player.addWorkerInHand(nbWorkerToAdd);
                    clearWorkers();
                }
                break;
            case BOTTOM:
                int nbMoneyToConsumme = 4;
                nbWorkerToAdd = 1;
                player.consummeMoney(nbMoneyToConsumme);
                player.addWorkerInHand(nbWorkerToAdd);
                break;
            case LEFT:
                int nbMoneyToAdd = 2;
                player.addMoney(nbMoneyToAdd);
                break;
            case RIGHT:
                nbEnergyToConsume = 1;
                nbMoneyToAdd = 4;
                player.consummeEnergy(nbEnergyToConsume);
                player.addMoney(nbMoneyToAdd);
                break;
        }

    }

    @Override
    public void rotateBuildingLeft() {
        cells.clear();
        Cell cell;
        switch (state) {
            case TOP:
                origineX = 2;
                origineY = 0;
                for (int i = 0; i < 3; i++) {
                    cells.add(new Cell(origineX - 2 + i, origineY + 1));
                }
                cells.add(new Cell(origineX, origineY));
                break;
            case BOTTOM:
                origineX = 0;
                origineY = 0;
                for (int i = 0; i < 3; i++) {
                    cells.add(new Cell(origineX + i, origineY));
                }
                cells.add(new Cell(origineX, origineY + 1));
                break;
            case LEFT:
                origineX = 0;
                origineY = 0;
                for (int i = 0; i < 3; i++) {
                    cells.add(new Cell(origineX + 1, origineY + i));
                }
                cells.add(new Cell(origineX, origineY));
                break;
            case RIGHT:
                origineX = 0;
                origineY = 0;
                for (int i = 0; i < 3; i++) {
                    cells.add(new Cell(origineX, origineY + i));
                }
                cells.add(new Cell(origineX + 1, origineY + 2));
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
                    cells.add(new Cell(origineX + i, origineY));
                }
                cells.add(new Cell(origineX, origineY + 1));
                break;
            case BOTTOM:
                origineX = 2;
                origineY = 0;
                for (int i = 0; i < 3; i++) {
                    cells.add(new Cell(origineX - 2 + i, origineY + 1));
                }
                cells.add(new Cell(origineX, origineY));
                break;
            case LEFT:
                origineX = 0;
                origineY = 0;
                for (int i = 0; i < 3; i++) {
                    cells.add(new Cell(origineX, origineY + i));
                }
                cells.add(new Cell(origineX + 1, origineY + 2));
                break;
            case RIGHT:
                origineX = 0;
                origineY = 0;
                for (int i = 0; i < 3; i++) {
                    cells.add(new Cell(origineX + 1, origineY + i));
                }
                cells.add(new Cell(origineX, origineY));
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
                    shape.add(board.getBoard()[x + 2][y + 1]);
                    break;
                case BOTTOM:
                    for (int i = 0; i < 3; i++) {
                        shape.add(board.getBoard()[x + i][y + 1]);
                    }
                    shape.add(board.getBoard()[x][y]);
                    break;
                case LEFT:
                    for (int i = 0; i < 3; i++) {
                        shape.add(board.getBoard()[x + 1][y - i]);
                    }
                    shape.add(board.getBoard()[x][y]);
                    break;
                case RIGHT:
                    for (int i = 0; i < 3; i++) {
                        shape.add(board.getBoard()[x][y + i]);
                    }
                    shape.add(board.getBoard()[x + 1][y]);
                    break;
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            shape = new ArrayList<>();
            System.out.println("ça sort du cadre légal");
        }
        return shape;
    }

}
