import java.util.ArrayList;
import java.util.TreeSet;
import java.util.HashMap;

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

    private String[] objectives;
    private HashMap<String, ArrayList<Integer>> scores;
    private TreeSet<Integer>[] playerRanks;

    private int[] totalScores;

    public EndPanel(KingdomBuilder kb) {
        setSize(getPreferredSize());
        setLayout(null);
        frame = kb;
        viewBoardButton = new JButton("");
        viewBoardButton.setOpaque(false);
        viewBoardButton.setContentAreaFilled(false);
        viewBoardButton.setBorderPainted(false);
        viewBoardButton.setSize(242, 93);
        viewBoardButton.setLocation(1300, 760);
        viewBoardButton.setFocusable(false);
        viewBoardButton.addActionListener(this);
        add(viewBoardButton);
        newGameButton = new JButton("");
        newGameButton.setOpaque(false);
        newGameButton.setContentAreaFilled(false);
        newGameButton.setBorderPainted(false);
        newGameButton.setSize(242, 93);
        newGameButton.setLocation(1300, 860);
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

    public void setObjectivesAndScore(String[] obj, HashMap<String, ArrayList<Integer>> scores) {
        objectives = obj;
        this.scores = scores;

        totalScores = new int[4];

        ArrayList<Integer> temp;
        for(String objective: scores.keySet()) {
            temp = scores.get(objective);
            
            for(int i = 0; i < temp.size(); i++) {
                totalScores[i] += temp.get(i);
            }
        }

        rankPlayers();
    }

    private void rankPlayers() {
        int numPlayersRanked = 0;
        int rankAt = 0;

        while(numPlayersRanked < 4) {

        }
        playerRanks[rankAt]= new TreeSet<Integer>();
        int maxScoreIndex = 0;
        for(int i = 1; i < totalScores.length; i++) {
            if(totalScores[i] > totalScores[maxScoreIndex]) {
                maxScoreIndex = i;
            }
        }

        for(int i = 0; i < totalScores.length; i++) {
            if(totalScores[i] == totalScores[maxScoreIndex]) {
                playerRanks[rankAt].add(i);
            }
        }
    }

    public void paintComponent(Graphics g) {
        //g.clearRect(0, 0, getWidth(), getHeight());
        g.drawImage(woodBG, 0, 0, 1600, 960, null);
        g.drawImage(leaderboard, 206, 18, 1288, 178, null);
        g.drawImage(viewBoard, 1300, 760, 242, 93, null);
        g.drawImage(newGame, 1300, 860, 242, 93, null);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==newGameButton){
            //frame.nextScreen();
            frame.restart();
        }
        if(e.getSource()==viewBoardButton){
            frame.previousScreen();
        }
    }
}
