import java.util.ArrayList;
import java.util.TreeSet;
import java.util.HashMap;
import java.util.Arrays;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Graphics;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.Font;
import java.awt.Color;

import javax.imageio.*;

public class EndPanel extends JPanel implements ActionListener{
    private KingdomBuilder frame;
    private BufferedImage woodBG, leaderboard, player1, player2, player3, player4, viewBoard, newGame;
    private JButton viewBoardButton, newGameButton;

    private static final int LEADERBOARD_TOP_X = 206;
    private static final int LEADERBOARD_TOP_Y = 240;
    private static final int LEADERBOARD_INDENTED = LEADERBOARD_TOP_Y + 20;
    private static final int RANK_SPACING = 90;
    private static final int PLAYER_SPACING = 70;
    private static final Font bigFont = new Font("Purisa", Font.BOLD, 60);
    private static final Font smallFont = new Font("Purisa", Font.BOLD, 50);

    private String[] objectives;
    private HashMap<String, ArrayList<Integer>> scores;
    private ArrayList<TreeSet<Integer>> playerRanks;

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
        playerRanks = new ArrayList<TreeSet<Integer>>();

        ArrayList<Integer> temp;
        for(String objective: scores.keySet()) {
            temp = scores.get(objective);
            
            for(int i = 0; i < temp.size(); i++) {
                totalScores[i] += temp.get(i);
            }
        }

        rankPlayers();
    }

    //Richard: no point in over-optimizing. Besides, other solutions are very similar
    private void rankPlayers() {
        int[] sortedScores = new int[4];
        
        for(int i = 0; i < totalScores.length; i++) {
            sortedScores[i] = totalScores[i];
        }

        Arrays.sort(sortedScores);

        int currentScore;
        int previousScore = -1;
        for(int i = 3; i >= 0; i--) {
            currentScore = sortedScores[i];

            if(currentScore == previousScore)
                continue;

            playerRanks.add(new TreeSet<Integer>());
            addPlayersWithScore(currentScore);

            previousScore = currentScore;
        }
    }

    private void addPlayersWithScore(int score) {
        for(int i = 0; i < totalScores.length; i++)
            if(totalScores[i] == score)
                playerRanks.get(playerRanks.size() - 1).add(i);
    }

    public void paintComponent(Graphics g) {
        //g.clearRect(0, 0, getWidth(), getHeight());
        g.drawImage(woodBG, 0, 0, 1600, 960, null);
        g.drawImage(leaderboard, 206, 18, 1288, 178, null);
        g.drawImage(viewBoard, 1300, 760, 242, 93, null);
        g.drawImage(newGame, 1300, 860, 242, 93, null);

        Font old = g.getFont();
        g.setColor(new Color(188, 151, 107));

        int yAt = LEADERBOARD_TOP_Y;
        for(int i = 0; i < playerRanks.size(); i++) {
            String mess;
            if(i == 0)
                mess = "1st";
            else if(i == 1)
                mess = "2nd";
            else if(i == 2)
                mess = "3rd";
            else
                mess = "4th";

            g.setFont(bigFont);
            g.drawString(mess, LEADERBOARD_TOP_X, yAt);

            g.setFont(smallFont);
            for(int idk: playerRanks.get(i)) {
                yAt += PLAYER_SPACING;
                g.drawString("Player " + (idk + 1) + " ~ " + totalScores[idk], LEADERBOARD_INDENTED, yAt);
            }

            yAt += RANK_SPACING;
        }


        g.setFont(old);
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
