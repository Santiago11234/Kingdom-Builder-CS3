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
    private BufferedImage test;

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
            test = ImageIO.read(StartPanel.class.getResource("/Images/Canyon Tile.png"));
        }
        catch(Exception e) {
            System.out.println("Tile error");
        }
    }
    
    public void paintComponent(Graphics g) {
        g.fillRect(0, 0, getWidth(), getHeight());
        g.drawImage(test, 0, 0, 50, 58, null);
    }

    public void actionPerformed(ActionEvent e) {
        frame.nextScreen();
    }
}
