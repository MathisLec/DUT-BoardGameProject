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
import javafx.scene.canvas.GraphicsContext;
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
       
    }

    public OBlock(ArrayList<Cell> list) {
        super(list);
        super.role = 'O';
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
            s.drawCell(c, s.getX(), s.getY(),Color.BLUE);
        }
    }

    @Override
    public void buildingRole(Player player, Board board) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public ArrayList<Cell> getPreviewsShape(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deplaceBuilding(double x, double y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
