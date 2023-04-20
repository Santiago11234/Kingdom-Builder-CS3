import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Graphics2D;
//ima push so hard itll make your head spin

public class KingdomBuilderPanel extends JPanel implements ActionListener, MouseListener {
    private KingdomBuilder frame;
    private Game game;
    private JButton startButton;

    private BufferedImage blurBG, playerWood,mapWood, settlementWood, player1NameBlock, player2NameBlock, player3NameBlock, player4NameBlock, addSettlementButton,
            endTurnButton, terrainCardCanyon, terrainCardDesert, terrainCardFlowers, terrainCardForest, terrainCardGrass, terrainCardBack,
            settlementCountBlock, deckTextBlock, discardTextBlock,purpleSettlement,yellowSettlement,blueSettlement,redSettlement; 

    public KingdomBuilderPanel(KingdomBuilder kb) {
       setSize(getPreferredSize());
       setLayout(null);
       frame = kb;
       startButton = new JButton("skip to end button");
        startButton.setOpaque(false);
        startButton.setContentAreaFilled(true);
        startButton.setBorderPainted(false);
        startButton.setSize(200, 50);
        startButton.setLocation(1117, 0);
        startButton.setFocusable(false);
        startButton.addActionListener(this);
        add(startButton);

       try {
        blurBG = ImageIO.read(KingdomBuilderPanel.class.getResource("/Images/blurred BG.jpg"));
        playerWood = ImageIO.read(KingdomBuilderPanel.class.getResource("/Images/playerWood.png"));
        mapWood = ImageIO.read(KingdomBuilderPanel.class.getResource("/Images/mapWood.png"));
        settlementWood = ImageIO.read(KingdomBuilderPanel.class.getResource("/Images/settlementWood.png"));
        player1NameBlock = ImageIO.read(KingdomBuilderPanel.class.getResource("/Images/player1NameBlock.png"));
        player2NameBlock = ImageIO.read(KingdomBuilderPanel.class.getResource("/Images/player2NameBlock.png"));
        player3NameBlock = ImageIO.read(KingdomBuilderPanel.class.getResource("/Images/player3NameBlock.png"));
        player4NameBlock = ImageIO.read(KingdomBuilderPanel.class.getResource("/Images/player4NameBlock.png"));
        addSettlementButton = ImageIO.read(KingdomBuilderPanel.class.getResource("/Images/addSettlementButton.png"));
        endTurnButton = ImageIO.read(KingdomBuilderPanel.class.getResource("/Images/endTurnButton.png"));
        terrainCardCanyon = ImageIO.read(KingdomBuilderPanel.class.getResource("/Images/Terrain Card Canyon.png"));
        terrainCardDesert = ImageIO.read(KingdomBuilderPanel.class.getResource("/Images/Terrain Card Desert.png"));
        terrainCardFlowers = ImageIO.read(KingdomBuilderPanel.class.getResource("/Images/Terrain Card Flowers.png"));
        terrainCardForest = ImageIO.read(KingdomBuilderPanel.class.getResource("/Images/Terrain Card Forest.png"));
        terrainCardGrass = ImageIO.read(KingdomBuilderPanel.class.getResource("/Images/Terrain Card Grass.png"));
        terrainCardBack = ImageIO.read(KingdomBuilderPanel.class.getResource("/Images/Terrain Card Back.png"));
        settlementCountBlock = ImageIO.read(KingdomBuilderPanel.class.getResource("/Images/settlementCountBlock.png"));
        deckTextBlock = ImageIO.read(KingdomBuilderPanel.class.getResource("/Images/deckTextBlock.png"));
        discardTextBlock = ImageIO.read(KingdomBuilderPanel.class.getResource("/Images/discardTextBlock.png"));
        purpleSettlement = ImageIO.read(KingdomBuilderPanel.class.getResource("/Images/Purple Settlement.png"));
        yellowSettlement = ImageIO.read(KingdomBuilderPanel.class.getResource("/Images/Yellow Settlement.png"));
        blueSettlement = ImageIO.read(KingdomBuilderPanel.class.getResource("Images/Blue Settlement.png"));
        redSettlement =ImageIO.read(KingdomBuilderPanel.class.getResource("/Images/Red Settlement.png"));

        } catch (Exception e) {
            System.out.println("Kingdom Builder panel error");
       }

       addMouseListener(this);

       game = new Game(this);
    }

    public void setSettlementButton(boolean b) {

    }

    public void paintComponent(Graphics l) {
        Graphics2D g = (Graphics2D)l;
        
        g.clearRect(0, 0, getWidth(), getHeight());

        g.drawImage(blurBG, 0, 0, getWidth(), getHeight(), null);
        g.drawImage(playerWood, 1091, 42, 487, 882, null);
        g.drawImage(mapWood, 70, 90, 990, 858, null);
        g.drawImage(settlementWood, 70, 18, 990, 62, null);

        //TextBlocks on player section
        g.drawImage(settlementCountBlock, 1418, 772, 102, 32, null);
        g.drawImage(deckTextBlock, 1143, 581, 81, 32, null);
        g.drawImage(discardTextBlock, 1270, 581, 102, 32, null);
        //buttons on player section
        g.drawImage(addSettlementButton, 1409, 652, 114, 110, null);
        g.drawImage(endTurnButton, 1144, 840, 381, 49, null);

        //Player 2, 3, 4 info
        int temp = game.turn();
        int height = 50;
        int width = 60;
        if(temp == 0){
            //settlements images that will go by player's name
            g.drawImage(yellowSettlement, 350, 23, width, height, null);
            g.drawImage(blueSettlement, 660, 23, width, height, null);
            g.drawImage(redSettlement, 970, 23, width, height, null);

            //player's name on player board and the settlement ontop of the add settlement button
            g.drawImage(player1NameBlock, 1117, 71, 436, 75, null);
            //idea to put settlement house ontop of addSettlement button
            //g.drawImage(purpleSettlement, 1436, 682, width, height, null);
        }
        if(temp == 1){
            g.drawImage(blueSettlement, 350, 23, width, height, null);
            g.drawImage(redSettlement, 660, 23, width, height, null);
            g.drawImage(purpleSettlement, 970, 23, width, height, null);

            g.drawImage(player2NameBlock, 1117, 71, 436, 75, null);
        }
        if(temp == 2){
            g.drawImage(redSettlement, 350, 23, width, height, null);
            g.drawImage(purpleSettlement, 660, 23, width, height, null);
            g.drawImage(yellowSettlement, 970, 23, width, height, null);

            g.drawImage(player3NameBlock, 1117, 71, 436, 75, null);
        }
        if(temp == 3){
            g.drawImage(purpleSettlement, 350, 23, width, height, null);
            g.drawImage(yellowSettlement, 660, 23, width, height, null);
            g.drawImage(blueSettlement, 970, 23, width, height, null);

            g.drawImage(player4NameBlock, 1117, 71, 436, 75, null);
        }

        //Main player name ------- blocks moved above
        //g.drawImage(player1NameBlock, 1117, 71, 436, 75, null);
        //g.drawImage(player2NameBlock, 1117, 71, 436, 75, null);
        //g.drawImage(player3NameBlock, 1117, 71, 436, 75, null);
        //g.drawImage(player4NameBlock, 1117, 71, 436, 75, null);
        //Terrain Cards
        g.drawImage(terrainCardCanyon, 1392, 379, 161, 248, null);
        //g.drawImage(terrainCardDesert, 1392, 379, 161, 248, null);
        //g.drawImage(terrainCardFlowers, 1392, 379, 161, 248, null);
        //g.drawImage(terrainCardForest, 1392, 379, 161, 248, null);
        //g.drawImage(terrainCardGrass, 1392, 379, 161, 248, null);
        //g.drawImage(terrainCardBack, 1392, 379, 161, 248, null);
        //Discard Pile
        g.drawImage(terrainCardCanyon, 1267, 416, 106, 154, null);
        //Deck Pile
        g.drawImage(terrainCardBack, 1130, 416, 106, 154, null);
        
        //Objective Cards
        game.drawAll(g);
    }

    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}

    public void mouseClicked(MouseEvent e) {
        
    }

    public void actionPerformed(ActionEvent e) {
        frame.nextScreen();
    }
}
