import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.util.ArrayList;

public class CardDrawer {
    public static final String[] objectiveCards = {"fishermen", "miners", "workers", "discoverers", "knights", "lords", "farmers", "merchants", "hermits", "citizens"};
    private BufferedImage[] terrainCardImages = new BufferedImage[10];
    private BufferedImage[] objectiveCardImages = new BufferedImage[10];
    private ArrayList<String> terrainDeck ;
    private String[] objectiveDeck;;
    private ArrayList<String> discard;

    public CardDrawer() {
            }

    public void setImages(){

        try {
            terrainCardImages[0] = ImageIO.read(KingdomBuilderPanel.class.getResource("/Images/Terrain Card Canyon.png"));
            terrainCardImages[1] = ImageIO.read(KingdomBuilderPanel.class.getResource("/Images/Terrain Card Desert.png"));
            terrainCardImages[2] = ImageIO.read(KingdomBuilderPanel.class.getResource("/Images/Terrain Card Flowers.png"));
            terrainCardImages[3] = ImageIO.read(KingdomBuilderPanel.class.getResource("/Images/Terrain Card Forest.png"));
            terrainCardImages[4] = ImageIO.read(KingdomBuilderPanel.class.getResource("/Images/Terrain Card Grass.png"));
            terrainCardImages[5] = ImageIO.read(KingdomBuilderPanel.class.getResource("/Images/Terrain Card Back.png"));
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("ur mom");
        }
    }
    //public void BufferedImage getCurrentTerrainCard(){    }
    public void draw(Graphics g) {

    }
}
