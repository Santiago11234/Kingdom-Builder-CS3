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

    private BufferedImage blurBG, playerWood; 

    public KingdomBuilderPanel(KingdomBuilder kb) {
       setSize(getPreferredSize());
       setLayout(null);
       frame = kb;

       try {
            blurBG = ImageIO.read(KingdomBuilderPanel.class.getResource("/Images/blurred BG.jpg"));
            playerWood = ImageIO.read(KingdomBuilderPanel.class.getResource("/Images/playerWood.png"));
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
        g.drawImage(playerWood, 1100, 60, 450, 840, null);
        
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
