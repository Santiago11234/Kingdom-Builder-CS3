import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class StartPanel extends JPanel implements ActionListener {
    private KingdomBuilder frame;

    private JButton startButton;
    private BufferedImage startImage;

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
            startImage = ImageIO.read(StartPanel.class.getResource("/Images/start.png"));
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Exception Error");
        }

    }
    
    public void paintComponent(Graphics g) {
        g.drawImage(startImage, KingdomBuilder.WIDTH, KingdomBuilder.HEIGHT, null);
    }

    public void actionPerformed(ActionEvent e) {
        frame.nextScreen();
    }
}
