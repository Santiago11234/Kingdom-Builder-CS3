import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.awt.Graphics;

import javax.imageio.ImageIO;

public class PowerUp {
    public static final int WIDTH = 56; //Richard: test this later
    public static final int HEIGHT = (int)Math.round(WIDTH * 2 / Math.sqrt(3));
    private static final int SECOND_HIGHEST_POINT_Y = (int)Math.round(14.0 / 55 * HEIGHT);

    private static final Polygon sharedHitbox = new Polygon(new int[] {0, WIDTH / 2, WIDTH, WIDTH, WIDTH / 2, 0}, new int[] {SECOND_HIGHEST_POINT_Y, 0, SECOND_HIGHEST_POINT_Y, HEIGHT - SECOND_HIGHEST_POINT_Y, HEIGHT, HEIGHT - SECOND_HIGHEST_POINT_Y}, 6); 
    private static final BufferedImage[] images = new BufferedImage[9]; 

    private String type;
    private int x;
    private int y;
    private Polygon hitbox;

    public static void setImages() {
        try {
            images[0] = ImageIO.read(PowerUp.class.getResource("/Images/Power Up Oracle.png"));
            images[1] = ImageIO.read(PowerUp.class.getResource("/Images/Power Up Farm.png"));
            images[2] = ImageIO.read(PowerUp.class.getResource("/Images/Power Up Oasis.png"));
            images[3] = ImageIO.read(PowerUp.class.getResource("/Images/Power Up Tower.png"));
            images[4] = ImageIO.read(PowerUp.class.getResource("/Images/Power Up Tavern.png"));
            images[5] = ImageIO.read(PowerUp.class.getResource("/Images/Power Up Barn.png"));
            images[6] = ImageIO.read(PowerUp.class.getResource("/Images/Power Up Harbor.png"));
            images[7] = ImageIO.read(PowerUp.class.getResource("/Images/Power Up Paddock.png"));
            images[8] = ImageIO.read(PowerUp.class.getResource("/Images/Power Up Oracle.png")); //Fix          
        }
        catch(Exception e) {
            System.out.println("Power up error");
        }
    }

    public PowerUp(String t) {
        type = t;
    }

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

    public boolean clicked(int x, int y) {
        return hitbox.contains(x, y);
    }

    //Richard: hitboxes now scuffed. If you want to, you can crop the images
    public void draw(boolean up, Graphics g) {
        if(!up) {
            g.drawImage(images[8], x, y, WIDTH, HEIGHT, null);
            return;
        }
        
        switch(type) {
            case "oracle":
                g.drawImage(images[0], x, y, WIDTH, HEIGHT, null);
                break;

            case "farm":
                g.drawImage(images[1], x, y, WIDTH, HEIGHT, null);
                break;

            case "oasis":
                g.drawImage(images[2], x, y, WIDTH, HEIGHT, null);
                break;

            case "tower":
                g.drawImage(images[3], x, y, WIDTH, HEIGHT, null);
                break;

            case "tavern":
                g.drawImage(images[4], x, y, WIDTH, HEIGHT, null);
                break;

            case "barn":
                g.drawImage(images[5], x, y, WIDTH, HEIGHT, null);
                break;

            case "harbor":
                g.drawImage(images[6], x, y, WIDTH, HEIGHT, null);
                break;

            case "paddock":
                g.drawImage(images[7], x, y, WIDTH, HEIGHT, null);
                break;

            default:
                System.out.println("Power up type invalid: " + type);
        }

        //g.drawPolygon(hitbox);
    }
}