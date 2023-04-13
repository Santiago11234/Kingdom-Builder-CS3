import javax.swing.JPanel;
import javax.swing.JButton;

import javax.imageio.*;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class EndPanel extends JPanel implements ActionListener{
    private KingdomBuilder frame;
    private BufferedImage woodBG, leaderboard, player1, player2, player3, player4, viewBoard, newGame;
    private JButton viewBoardButton, newGameButton;

    public EndPanel(KingdomBuilder kb) {
        setSize(getPreferredSize());
        setLayout(null);
        frame = kb;
        viewBoardButton = new JButton("");
        viewBoardButton.setOpaque(false);
        viewBoardButton.setContentAreaFilled(false);
        viewBoardButton.setBorderPainted(false);
        viewBoardButton.setSize(150, 50);
        viewBoardButton.setLocation(1400, 800);
        viewBoardButton.setFocusable(false);
        viewBoardButton.addActionListener(this);
        add(viewBoardButton);
        newGameButton = new JButton("");
        newGameButton.setOpaque(false);
        newGameButton.setContentAreaFilled(false);
        newGameButton.setBorderPainted(false);
        newGameButton.setSize(150, 50);
        newGameButton.setLocation(1400, 870);
        newGameButton.setFocusable(false);
        newGameButton.addActionListener(this);
        add(newGameButton);
        try {
            woodBG = ImageIO.read(EndPanel.class.getResource("/Images/straightWood.png"));
            leaderboard = ImageIO.read((EndPanel.class.getResource("/Images/LEADERBOARD.png")));
            viewBoard = ImageIO.read(EndPanel.class.getResource("/Images/viewBoardButton.png"));
            newGame = ImageIO.read(EndPanel.class.getResource("/Images/newGameButton.png"));
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("End panel error");
        }
    }

    public void paintComponent(Graphics g) {
        g.clearRect(0, 0, getWidth(), getHeight());
        g.drawImage(woodBG, 0, 0, 1600, 960, null);
        g.drawImage(leaderboard, 250, 50, 1100, 130, null);
        g.drawImage(viewBoard, 1400, 800, 150, 50, null);
        g.drawImage(newGame, 1400, 870, 150, 50, null);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==newGameButton){
            frame.restart();
        }
        if(e.getSource()==viewBoardButton){
            
        }
    }
}
