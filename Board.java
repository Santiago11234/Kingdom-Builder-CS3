import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import java.util.*;
import java.awt.*;
public class Board {
    private static String[][][] smallBoards;    //Collection of 10-by-10 Tile arrays. All inputted manually
    
    //Top left coordinate
    public static int X = 20;
    public static int Y = 20;
    
    public Tile[][] board; //20-by-40 array of Tiles
    
    public Board() {
        board = new Tile[20][40];
        
        setTilePositions();
    }
    
    public void setTilePositions() {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j += 2) {
                if(i % 2 == 0) {
                    board[i][j] = new Tile(i, j);
                    board[i][j].setPosition(X + j / 2 * Tile.WIDTH, Y + i * Tile.PARTICULAR_POINT);
                }
                else {
                    board[i][j + 1] = new Tile(i, j + 1);
                    board[i][j + 1].setPosition(X + Tile.WIDTH / 2 + j / 2 * Tile.WIDTH, Y + i * Tile.PARTICULAR_POINT);
                }
            }
        }
    }
    
    public void createBoard(){}
    
    private Tile[][] rotated(Tile[][] arr){ return arr;}

    public Tile tileClicked(int x, int y) { 
        for(int i = 0; i < board.length; i++) {
            for(int j = i % 2; j < board[i].length; j += 2) {
                if(board[i][j].clicked(x, y)) {
                    System.out.println(i + " " + j);
                    return board[i][j];
                }
            }
        }

        return null;
    }

    public void drawBoard(Graphics g) {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j += 2) {
                if(i % 2 == 0) {
                    board[i][j].draw(g);
                }
                else {
                    board[i][j + 1].draw(g);
                }
            }
        }
    }
}
