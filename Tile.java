import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Tile {

private static String[] terrainTypes;   
public static int SIZE;
private static Polygon sharedHitbox = new Polygon();   

private static BufferedImage[] images;

private String type;
private boolean occupied;
private Queue<PowerUp> powerups;   
private int x;
private int y;
private Polygon hitbox;

//Row and column of Tile in board
private int row;
private int column;

public static void setImages(){}

public Tile(int i, int j){} 


public int getRow(){return row;}

public int getColumn(){return column;}

public Polygon getPolygon(){return hitbox;}

public void setPosition(int x, int y){}


public String getType(){return "yes";}

public void setType(String t){}


public boolean isOccupied(){return true;}
	
public void setOccupied(boolean b){}

public boolean isSpecialTile(){return true;}


public boolean isPowerupTile(){return true;}

public boolean clicked(int x, int y){ return true;}


public void draw(Graphics g){}

public void bold(Graphics2D g) {}


}