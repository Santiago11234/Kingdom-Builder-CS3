import javax.swing.JPanel;

import javax.imageio.*;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class EndPanel extends JPanel implements ActionListener{
    private KingdomBuilder frame;
    private BufferedImage woodBG, leaderboard, player1, player2, player3, player4, viewBoard, newGame;

    public EndPanel(KingdomBuilder kb) {
        setSize(getPreferredSize());
        setLayout(null);
        frame = kb;
        try {
            woodBG = ImageIO.read(EndPanel.class.getResource("/Images/straightWood.png"));
            leaderboard = ImageIO.read((EndPanel.class.getResource("/Images/LEADERBOARD.png")));
            viewBoard = ImageIO.read(EndPanel.class.getResource("/Images/viewBoardButton"));
            newGame = ImageIO.read(EndPanel.class.getResource("/Images/newGameButton"));
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("End panel error");
        }
    }

    public void paintComponent(Graphics g) {
        g.clearRect(0, 0, getWidth(), getHeight());
        g.drawImage(woodBG, 0, 0, 1600, 960, null);
        g.drawImage(leaderboard, 250, 50, 1100, 130, null);
    }

    public void actionPerformed(ActionEvent e) {

    }
}
