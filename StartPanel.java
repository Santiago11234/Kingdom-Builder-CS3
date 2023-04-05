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

    public StartPanel(KingdomBuilder kb) {
        setLayout(null);
        setPreferredSize(new Dimension(KingdomBuilder.WIDTH, KingdomBuilder.HEIGHT));
        frame = kb;   

        startButton = new JButton("Start");
        startButton.setSize(100, 100);
        startButton.setLocation(100, 100);
        startButton.setFocusable(false);
        startButton.addActionListener(this);
        add(startButton);

        try {
            //All image stuff should occur here (tiles and power ups too)
            Tile.setImages();
        }
        catch(Exception e) {
            System.out.println("Start panel error");
        }
    }
    
    public void paintComponent(Graphics g) {
        g.clearRect(0, 0, getWidth(), getHeight());
    }

    public void actionPerformed(ActionEvent e) {
        frame.nextScreen();
    }
}
