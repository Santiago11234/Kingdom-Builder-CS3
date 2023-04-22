import java.util.Queue;
import java.util.LinkedList;

import java.awt.Polygon;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.BasicStroke;
import java.awt.Color;

import javax.imageio.ImageIO;
//import javax.swing.text.html.HTMLDocument.HTMLReader.SpecialAction;

public class Tile {
    private static final String[] terrainTypes = {"canyon", "desert", "flowers", "forest", "grass", "mountain", "water"};   
    private static final String[] specialTypes = {"castle", "oracle", "farm", "oasis", "tower", "tavern", "barn", "harbor", "paddock"};
    private static final Color[] playerColors = new Color[4];
    
    public static final int WIDTH = 46;
    public static final int HEIGHT = (int)Math.round(WIDTH * 2 / Math.sqrt(3));
    public static final int PARTICULAR_POINT = (int)Math.round(41.0 / 55 * HEIGHT);
    
    //Richard: this is probably easier to hardcode, but the big boi here provides... some... adaptability
    private static final Polygon sharedHitbox = new Polygon(new int[] {0, WIDTH / 2, WIDTH, WIDTH, WIDTH / 2, 0}, new int[] {(int)Math.round(14.0 / 55 * HEIGHT), 0, (int)Math.round(14.0 / 55 * HEIGHT), PARTICULAR_POINT, HEIGHT, PARTICULAR_POINT}, 6); 
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
        playerColors[0] = new Color(135, 57, 230); //Purple
        playerColors[1] = new Color(237, 223, 19); //Yellow
        playerColors[2] = new Color(0, 11, 227); //Blue
        playerColors[3] = new Color(232, 32, 9); //Red

        try {
            //Richard: will this work with the school computers? For some reason, the school computers didn't work with getResource
            images[0] = ImageIO.read(Tile.class.getResource("/Images/Canyon Tile.png"));
            images[1] = ImageIO.read(Tile.class.getResource("/Images/Desert Tile.png"));
            images[2] = ImageIO.read(Tile.class.getResource("/Images/Flower Tile.png"));
            images[3] = ImageIO.read(Tile.class.getResource("/Images/Forest Tile.png"));
            images[4] = ImageIO.read(Tile.class.getResource("/Images/Grassland Tile.png"));
            images[5] = ImageIO.read(Tile.class.getResource("/Images/Mountain Tile.png"));
            images[6] = ImageIO.read(Tile.class.getResource("/Images/Water Tile.png"));

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

    public String getType() {
        return type;
    }

    public void setType(String t) {
        type = t;

        //Richard: awaiting Power Ups
        if(isPowerupTile()) {
            powerups = new LinkedList<PowerUp>();
            for(int i = 0; i < 2; i++) {

            }
        }
        else {
            powerups = null;
        }
    }


    public boolean isOccupied() {return occupied;}
        
    public void setOccupied(boolean b) {occupied = b;}

    public boolean isSpecialTile() {
        for(String s: specialTypes) {
            if(s.equals(type)) {
                return true;
            }
        }

        return false;
    }

    public boolean isPowerupTile() {
        return isSpecialTile() && !type.equals("castle");
    }

    public PowerUp getPowerUp() {
        return powerups.poll();
    }

    public boolean clicked(int x, int y) {return hitbox.contains(x, y);}

    public void draw(Graphics g) {
        switch(type) {
            case "red":
                g.setColor(playerColors[3]);
                g.fillPolygon(hitbox);
                break;
                
            case "canyon":
                g.drawImage(images[0], x, y, WIDTH, HEIGHT, null);
                break;

            case "desert":
                g.drawImage(images[1], x, y, WIDTH, HEIGHT, null);
                break;

            case "flowers":
                g.drawImage(images[2], x, y, WIDTH, HEIGHT, null);
                break;

            case "forest":
                g.drawImage(images[3], x, y, WIDTH, HEIGHT, null);
                break;

            case "grass":
                g.drawImage(images[4], x, y, WIDTH, HEIGHT, null);
                break;

            case "mountain":
                g.drawImage(images[5], x, y, WIDTH, HEIGHT, null);
                break;

            case "water":
                g.drawImage(images[6], x, y, WIDTH, HEIGHT, null);
                break;

            default:
                System.out.println("Tile draw has a problem");
        }

        if(powerups != null && !powerups.isEmpty()) {
            powerups.peek().draw(true, g);
        }
    }

    public void bold(int playerTurn, Graphics2D g) {
        g.setStroke(new BasicStroke(5));

        //Richard: really scuffed infill way. Perhaps a bad place to put this
        int[] goddamnitX = new int[6];
        int[] goddamnitY = new int[6];

        goddamnitX[0] = hitbox.xpoints[0] + 3;
        goddamnitX[1] = hitbox.xpoints[1];
        goddamnitX[2] = hitbox.xpoints[2] - 3;
        goddamnitX[3] = hitbox.xpoints[3] - 3;
        goddamnitX[4] = hitbox.xpoints[4];
        goddamnitX[5] = hitbox.xpoints[5] + 3;

        goddamnitY[0] = hitbox.ypoints[0] + 1;
        goddamnitY[1] = hitbox.ypoints[1] + 3;
        goddamnitY[2] = hitbox.ypoints[2] + 1;
        goddamnitY[3] = hitbox.ypoints[3] - 1;
        goddamnitY[4] = hitbox.ypoints[4] - 3;
        goddamnitY[5] = hitbox.ypoints[5] - 1;


        Polygon idfk = new Polygon(goddamnitX, goddamnitY, 6);

        //Outline
        g.setColor(playerColors[playerTurn]);
        g.drawPolygon(idfk);

        //Fill
        g.setColor(new Color(playerColors[playerTurn].getRed(), playerColors[playerTurn].getGreen(), playerColors[playerTurn].getBlue(), 90));
        g.fillPolygon(idfk);

        //Outline?
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(1.3f));
        g.drawPolygon(hitbox);
    }

    public String toString() {
        return row + ", " + column + ". " + type;
    }
}
