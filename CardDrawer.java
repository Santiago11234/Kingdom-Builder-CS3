import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.util.ArrayList;

public class CardDrawer {
    public static final String[] objectiveCards = {"fishermen", "miners", "workers", "discoverers", "knights", "lords", "farmers", "merchants", "hermits", "citizens"};
    private static final BufferedImage[] terrainCardImages = new BufferedImage[10];
    private static final BufferedImage[] objectiveCardImages = new BufferedImage[10];

    private static final int TERRAIN_CARD_WIDTH = 106;
    private static final int TERRAIN_CARD_HEIGHT = 154;

    private static final int DECK_LOWER_Y = 416;
    private static final int DECK_STAGGER_OFFSET_X = 2;
    private static final int DECK_STAGGER_OFFSET_Y = 2;
    private static final int MAX_CARDS_DRAWN = 7;
    
    private static final int OBJECTIVE_CARD_WIDTH = 129;
    private static final int OBJECTIVE_CARD_HEIGHT = 189;
    private static final int[] OBJECTIVE_CARD_XS = {1117, 1270, 1423};
    private static final int OBJECTIVE_CARD_Y = 168;

    private static final int OBJECTIVE_CARD_BIG_WIDTH = 235;
    private static final int OBJECTIVE_CARD_BIG_HEIGHT = 345;

    private ArrayList<String> terrainDeck;
    private ArrayList<String> discard;
    private String[] objectiveDeck;

    private Game game;

    public CardDrawer(Game g) {
        game = g;
        terrainDeck = g.getTerrainDeck();
        discard = g.getDiscard();
        objectiveDeck = g.getObjectives();
    }

    public static void setImages(){

        try {
            terrainCardImages[0] = ImageIO.read(CardDrawer.class.getResource("/Images/Terrain Card Canyon.png"));
            terrainCardImages[1] = ImageIO.read(CardDrawer.class.getResource("/Images/Terrain Card Desert.png"));
            terrainCardImages[2] = ImageIO.read(CardDrawer.class.getResource("/Images/Terrain Card Flowers.png"));
            terrainCardImages[3] = ImageIO.read(CardDrawer.class.getResource("/Images/Terrain Card Forest.png"));
            terrainCardImages[4] = ImageIO.read(CardDrawer.class.getResource("/Images/Terrain Card Grass.png"));
            terrainCardImages[5] = ImageIO.read(CardDrawer.class.getResource("/Images/Terrain Card Back.png"));

            objectiveCardImages[0] = ImageIO.read(CardDrawer.class.getResource("/Images/FishermenObjective.png"));
            objectiveCardImages[1] = ImageIO.read(CardDrawer.class.getResource("/Images/MinersObjective.png"));
            objectiveCardImages[2] = ImageIO.read(CardDrawer.class.getResource("/Images/WorkersObjective.png"));
            objectiveCardImages[3] = ImageIO.read(CardDrawer.class.getResource("/Images/DiscoverersObjective.png"));
            objectiveCardImages[4] = ImageIO.read(CardDrawer.class.getResource("/Images/KnightsObjective.png"));
            objectiveCardImages[5] = ImageIO.read(CardDrawer.class.getResource("/Images/LordsObjective.png"));
            objectiveCardImages[6] = ImageIO.read(CardDrawer.class.getResource("/Images/FarmersObjective.png"));
            objectiveCardImages[7] = ImageIO.read(CardDrawer.class.getResource("/Images/MerchantsObjective.png"));
            objectiveCardImages[8] = ImageIO.read(CardDrawer.class.getResource("/Images/HermitsObjective.png"));
            objectiveCardImages[9] = ImageIO.read(CardDrawer.class.getResource("/Images/CitizensObjective.png"));

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("ur mom");
        }
    }

    private BufferedImage getTerrainCardImage(String card) {
        switch(card) {
            case "canyon":
                return terrainCardImages[0];
            
            case "desert":
                return terrainCardImages[1];

            case "flowers":
                return terrainCardImages[2];

            case "forest":
                return terrainCardImages[3];

            case "grass":
                return terrainCardImages[4];

            default:
                System.out.println("Faulty terrain card: "+ card);
                return null;
        }
    }

    private BufferedImage getObjectiveCardImage(String card) {
        for(int i = 0; i < objectiveCards.length; i++) {
            if(objectiveCards[i].equals(card)) {
                return objectiveCardImages[i];
            }
        } 

        System.out.println("Faulty objective card: " + card);
        return null;
    }

    public void draw(Graphics g) {
        if(game.getPlayerCard() != null)
            g.drawImage(getTerrainCardImage(game.getPlayerCard()), 1393, 379, 161, 248, null);

        //Richard: staggered decks
        //Discard Pile
        if(!discard.isEmpty()) {
            for(int i = Math.min(discard.size() - 1, MAX_CARDS_DRAWN); i >= 0; i--) {
                g.drawImage(getTerrainCardImage(discard.get(i)), 1267 - DECK_STAGGER_OFFSET_X * i, DECK_LOWER_Y - i * DECK_STAGGER_OFFSET_Y, TERRAIN_CARD_WIDTH, TERRAIN_CARD_HEIGHT, null);
            }
        }
        
        //Deck Pile
        if(!terrainDeck.isEmpty()) {
            for(int i = Math.min(terrainDeck.size() - 1, MAX_CARDS_DRAWN); i >= 0; i--) {
                g.drawImage(terrainCardImages[5], 1130 - DECK_STAGGER_OFFSET_X * i, DECK_LOWER_Y - i * DECK_STAGGER_OFFSET_Y, TERRAIN_CARD_WIDTH, TERRAIN_CARD_HEIGHT, null);
            }
        }

        //Richard: objective cards
        for(int i = 0; i < objectiveDeck.length; i++) {
            if(objectiveCards[i] != null) {
                g.drawImage(getObjectiveCardImage(objectiveDeck[i]), OBJECTIVE_CARD_XS[i], OBJECTIVE_CARD_Y, OBJECTIVE_CARD_WIDTH, OBJECTIVE_CARD_HEIGHT, null);
            }
        }
    }

    public boolean clickLogic(int x, int y) {
        return false;
    }
}
