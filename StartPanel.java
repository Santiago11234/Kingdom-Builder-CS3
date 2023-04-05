import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Graphics;
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
        startButton.setOpaque(false);
        startButton.setContentAreaFilled(false);
        startButton.setBorderPainted(false);
        startButton.setSize(300, 120);
        startButton.setLocation(650, 515);
        startButton.setFocusable(false);
        startButton.addActionListener(this);
        add(startButton);

        try {
            //All image stuff should occur here (tiles and power ups too)
            Tile.setImages();
            startImage = ImageIO.read(StartPanel.class.getResource("/Images/start.png"));
        } catch (Exception e) {
            System.out.println("Start panel error");
        }
    }
    
    public void paintComponent(Graphics g) {
        g.drawImage(startImage, 0, 0, KingdomBuilder.WIDTH, KingdomBuilder.HEIGHT, null);
    }

    public void actionPerformed(ActionEvent e) {
        frame.nextScreen();
    }
}
