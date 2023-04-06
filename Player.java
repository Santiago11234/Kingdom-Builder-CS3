import java.util.TreeSet;
import java.util.TreeMap;

import java.awt.Graphics;

public class Player {
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
