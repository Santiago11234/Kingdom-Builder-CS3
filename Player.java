import java.util.TreeSet;
import java.util.TreeMap;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Player {
    private static final BufferedImage[] images = new BufferedImage[4]; //Richard: image indices correspond to player turns

    public TreeSet<Tile> specialTiles;
    public TreeSet<Tile> settlements;
    public TreeMap<PowerUp, Boolean> powerups;

    private String card;
    private int lordPoints;
    private int personalPoints;

    public Player() {
        specialTiles = new TreeSet<Tile>();
        settlements = new TreeSet<Tile>();
        powerups = new TreeMap<PowerUp, Boolean>();
    }

    public static void setImages() {
        try {
            images[0] = ImageIO.read(Player.class.getResource("/Images/Purple Settlement.png"));
            images[1] = ImageIO.read(Player.class.getResource("/Images/Yellow Settlement.png"));
            images[2] = ImageIO.read(Player.class.getResource("/Images/Blue Settlement.png"));
            images[3] = ImageIO.read(Player.class.getResource("/Images/Red Settlement.png"));
        }
        catch(Exception e) {
            System.out.println("Player image failure");
        }
    }

    public int getTotalPoints() {
        return lordPoints + personalPoints;
    }

    public void setLordPoints(int num) {
        lordPoints = num;
    }

    public void setPersonalPoints(int num) {
        personalPoints = num;
    }

    public int getSettlementsLeft() {
        return 40 - settlements.size();
    }

    public String getCard() {
        return card;
    }

    public void clear() {
        specialTiles.clear();
        settlements.clear();
        powerups.clear();
        card = null;
        lordPoints = personalPoints = 0;
    }

    public void drawAll(Graphics g) {
        for(Tile t: settlements) {

        }

        for(PowerUp p: powerups.keySet()) {
            
        }
    }
}
