import java.util.HashSet;
import java.util.HashMap;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Player {
    private static final BufferedImage[] images = new BufferedImage[4]; //Richard: image indices correspond to player turns
    private static final int SETTLEMENT_X_OFFSET = 7;
    private static final int SETTLEMENT_Y_OFFSET = 9;
    private static final int SETTLEMENT_WIDTH = Tile.WIDTH - 2 * SETTLEMENT_X_OFFSET;
    private static final int SETTLEMENT_HEIGHT = Tile.HEIGHT - 2 * SETTLEMENT_Y_OFFSET;
    private static final int POWERUP_X = 1126;
    private static final int POWERUP_Y = 652;
    private static final int POWER_UP_X_OFFSET = 10 + PowerUp.WIDTH;
    private static final int POWER_UP_Y_OFFSET = 10 + PowerUp.HEIGHT;

    public HashSet<Tile> specialTiles;
    public HashSet<Tile> settlements;
    public HashMap<PowerUp, Integer> powerups; //Possible states: 0: unused. 1: disabled. 2: used

    private String card;

    public Player() {
        specialTiles = new HashSet<Tile>();
        settlements = new HashSet<Tile>();
        powerups = new HashMap<PowerUp, Integer>();
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

    public int getSettlementsLeft() {
        return 40 - settlements.size();
    }

    public String getCard() {
        return card;
    }

    public void setCard(String c) {
        card = c;
    }

    public void clear() {
        specialTiles.clear();
        settlements.clear();
        powerups.clear();
        card = null;
    }

    public void updatePowerUpPositions() {
        int counter = 0;
        for(PowerUp p: powerups.keySet()) {
            p.setPosition(POWERUP_X + counter % 4 * POWER_UP_X_OFFSET, POWERUP_Y + POWER_UP_Y_OFFSET * (counter / 4));
            counter++;
        }
    }

    public void drawSettlements(int turn, Graphics g) {
        BufferedImage settlementImage = images[turn];

        for(Tile t: settlements) {
            g.drawImage(settlementImage, t.getX() + SETTLEMENT_X_OFFSET, t.getY() + SETTLEMENT_Y_OFFSET, SETTLEMENT_WIDTH, SETTLEMENT_HEIGHT, null);
        }
    }

    public void drawPowerUps(Graphics g) {
        for(PowerUp p: powerups.keySet()) {
            p.draw(powerups.get(p), g);
        }
    }
}
