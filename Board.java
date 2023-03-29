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
    
    //Board Class:
    private static String[][][] smallBoards;    //Collection of 10-by-10 Tile arrays. All inputted manually
    
    //Top left coordinate
    public static int x;
    public static int y;
    
    public Tile[][] board; //20-by-20 array of Tiles
    
    public Board(){}
    
    public void setTilePositions(){}
    
    public void createBoard(){}

    
    private Tile[][] rotated(Tile[][] arr){ return arr;}

    public Tile tileClicked(int x, int y){ return new Tile(x,y);}

    public void drawBoard(Graphics g){}
}
