/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardgameproject.Buildings;

import boardgameproject.Board;
import boardgameproject.Cell;
import boardgameproject.Player;
import boardgameproject.Round;
import java.util.ArrayList;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

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
        super.materialCost = 8;
        super.energyConsume = 0;

    }

    public OBlock(ArrayList<Cell> list) {
        super(list);
        super.role = 'O';
        super.materialCost = 8;
        super.energyConsume = 0;
    }

    @Override
    public void buildingShape(int x, int y) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                cells.add(new Cell(x + i, y + j));
            }
        }
    }

    @Override
    public void drawBuilding(Canvas c) {
        for (Cell s : cells) {
            s.drawCell(c, s.getX(), s.getY(), Color.YELLOW);
        }
    }

    @Override
    public void buildingRole(Player player, Board board, Round round) {
        //Re-initialize the number of placable workers
        player.setNbWorkerToPlaceByDefault();
        //TODO permit the player to move a worker from a building to another building
        for (int i = 0; i < getNbWorker(); i++) {
            player.increaseNbWorkerToPlace();
        }
    }

    @Override
    public void rotateBuildingLeft() {
        //do nothing
    }

    @Override
    public void rotateBuildingRight() {
        //do nothing
    }

    @Override
    public ArrayList<Cell> getPreviewsShape(Board board, int x, int y) {
        ArrayList<Cell> shape = new ArrayList<>();
        try {
            switch (state) {
                case TOP:
                case BOTTOM:
                case LEFT:
                case RIGHT:
                    for (int i = 0; i < 2; i++) {
                        for (int j = 0; j < 2; j++) {
                            shape.add(board.getBoard()[x + j][y + i]);
                        }
                    }
                    break;
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            shape = new ArrayList<>();
            System.out.println("ça sort du cadre légal");
        }
        return shape;
    }

}
