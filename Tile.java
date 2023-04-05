import java.util.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Tile {
    private static String[] terrainTypes = {"canyon", "desert", "flowers", "forest", "grass", "mountain", "water"};   
    private static String[] specialTypes = {"castle", "oracle", "farm", "oasis", "tower", "tavern", "barn", "harbor", "paddock"};
    public static final int WIDTH = 48;
    public static final int HEIGHT = (int)Math.round(WIDTH * 2 / Math.sqrt(3));
    public static final int PARTICULAR_POINT = (int)Math.round(41.0 / 55 * HEIGHT);
    private static final Polygon sharedHitbox = new Polygon(new int[] {0, WIDTH / 2, WIDTH, WIDTH, WIDTH / 2, 0}, new int[] {(int)Math.round(14.0 / 55 * HEIGHT), 0, (int)Math.round(14.0 / 55 * HEIGHT), PARTICULAR_POINT, HEIGHT, PARTICULAR_POINT}, 6); //Richard: this is probably easier to hardcode, but the big boi here provides... some... adaptability
    private static final BufferedImage[] images = new BufferedImage[terrainTypes.length + specialTypes.length];

    private String type;
    private boolean occupied;
    private Queue<PowerUp> powerups;   
    private int x;
    private int y;
    private Polygon hitbox;

    //Row and column of Tile in board
    private int row;
    private int column;

    public static void setImages() {
        try {
            //Richard: will this work with the school computers? For some reason, the school computers didn't work with getResource
            images[0] = ImageIO.read(Tile.class.getResourceAsStream("/Images/Canyon Tile.png"));
            images[1] = ImageIO.read(Tile.class.getResourceAsStream("/Images/Desert Tile.png"));
            images[2] = ImageIO.read(Tile.class.getResourceAsStream("/Images/Flower Tile.png"));
            images[3] = ImageIO.read(Tile.class.getResourceAsStream("/Images/Forest Tile.png"));
            images[4] = ImageIO.read(Tile.class.getResourceAsStream("/Images/Grassland Tile.png"));
            images[5] = ImageIO.read(Tile.class.getResourceAsStream("/Images/Mountain Tile.png"));
            images[6] = ImageIO.read(Tile.class.getResourceAsStream("/Images/Water Tile.png"));

        }
        catch(Exception e) {
            System.out.println("Tile has an image error");
        }
    }

    public Tile(int i, int j) {
        row = i;
        column = j;
    } 


    public int getRow() {return row;}

    public int getColumn() {return column;}

    public Polygon getPolygon() {return hitbox;}

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;

        int[] xArray = new int[6];
        int[] yArray = new int[6];

        for(int i = 0; i < xArray.length; i++) {
            xArray[i] = sharedHitbox.xpoints[i] + x;
            yArray[i] = sharedHitbox.ypoints[i] + y;
        }

        hitbox = new Polygon(xArray, yArray, 6);
    }

    public String getType() {return "yes";}

    public void setType(String t) {}


    public boolean isOccupied() {return occupied;}
        
    public void setOccupied(boolean b) {}

    public boolean isSpecialTile() {return true;}


    public boolean isPowerupTile() {return true;}

    public boolean clicked(int x, int y) {return hitbox.contains(x, y);}


    public void draw(Graphics g) {
        //switch(type) {
        //
        //}
    }

    public void bold(Graphics2D g) {}
}
