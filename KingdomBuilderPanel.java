import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class KingdomBuilderPanel extends JPanel implements ActionListener, MouseListener {
    private KingdomBuilder frame;
    private Game game;

    private BufferedImage blurBG, playerWood,mapWood, settlementWood, player1NameBlock, player2NameBlock, player3NameBlock, player4NameBlock, addSettlementButton,
            endTurnButton, terrainCardCanyon, terrainCardDesert, terrainCardFlowers, terrainCardForest, terrainCardGrass, terrainCardBack; 

    public KingdomBuilderPanel(KingdomBuilder kb) {
       setSize(getPreferredSize());
       setLayout(null);
       frame = kb;

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
        g.drawImage(mapWood, 70, 90, 985, 858, null);
        g.drawImage(settlementWood, 69, 18, 988, 62, null);
        g.drawImage(player1NameBlock, 1117, 71, 436, 75, null);
        //g.drawImage(player2NameBlock, 1117, 71, 436, 75, null);
        //g.drawImage(player3NameBlock, 1117, 71, 436, 75, null);
        //g.drawImage(player4NameBlock, 1117, 71, 436, 75, null);
        g.drawImage(addSettlementButton, 1409, 652, 114, 110, null);
        g.drawImage(endTurnButton, 1144, 840, 381, 49, null);
        game.board.drawBoard(g);
    }

    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}

    public void mouseClicked(MouseEvent e) {
    
    }

    public void actionPerformed(ActionEvent e) {

    }
}
