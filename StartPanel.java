import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class StartPanel extends JPanel implements ActionListener {
    private KingdomBuilder frame;

    private JButton startButton;
    private BufferedImage startImage;
 
    public StartPanel(KingdomBuilder kb) {
        setLayout(null);
        setPreferredSize(new Dimension(KingdomBuilder.WIDTH, KingdomBuilder.HEIGHT));
        frame = kb;   

        startButton = new JButton("");
        startButton.setContentAreaFilled(false);
        startButton.setBorder(null);
        startButton.setSize(342, 131);
        startButton.setLocation(629, 510);
        startButton.setFocusable(false);
        startButton.addActionListener(this);
        
        BufferedImage startButtonImage = null;
        BufferedImage startButtonBlackened = null;

        try {
            //All image stuff should occur here (tiles and power ups too)
            Tile.setImages();
            Player.setImages();
            PowerUp.setImages();
            CardDrawer.setImages();
            startImage = ImageIO.read(StartPanel.class.getResource("/Images/start.png"));
            startButtonImage = ImageIO.read(StartPanel.class.getResource("/Images/Start Button.png"));
            startButtonBlackened = ImageIO.read(StartPanel.class.getResource("/Images/Start Button Blackened.png"));
        } catch (Exception e) {
            System.out.println("Start panel error");
        }

        int width = 342;
        int height = 131;
        startButton.setIcon(new ImageIcon(startButtonImage.getScaledInstance(width, height, Image.SCALE_SMOOTH)));
        startButton.setRolloverIcon(new ImageIcon(startButtonBlackened.getScaledInstance(width, height, Image.SCALE_SMOOTH)));
        startButton.setDisabledIcon(startButton.getRolloverIcon());
        add(startButton);
    }
    
    
    public void paintComponent(Graphics g) {
        g.drawImage(startImage, 0, 0, KingdomBuilder.WIDTH, KingdomBuilder.HEIGHT, null);
    }

    public void actionPerformed(ActionEvent e) {
        frame.nextScreen();
    }
}
