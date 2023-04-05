import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class KingdomBuilderPanel extends JPanel implements ActionListener, MouseListener {
    private KingdomBuilder frame;
    private BufferedImage blurBG,wood;

    public KingdomBuilderPanel(KingdomBuilder kb) {
       setSize(getPreferredSize());
       setLayout(null);
       frame = kb;
       try {
            blurBG = ImageIO.read(KingdomBuilderPanel.class.getResource("/Images/blurred BG.jpg"));
            wood = ImageIO.read(KingdomBuilderPanel.class.getResource("/Images/wood.png"));
       } catch (Exception e) {
        // TODO: handle exception
        System.out.println("Exception Error");
       }

       addMouseListener(this);
    }

    public void paintComponent(Graphics l) {
        Graphics2D g = (Graphics2D)l;
        
        g.clearRect(0, 0, getWidth(), getHeight());
        g.drawImage(blurBG, 0, 0, getWidth(), getHeight(), null);
        g.drawImage(wood, 1100, 60, 450, 840, null);
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
