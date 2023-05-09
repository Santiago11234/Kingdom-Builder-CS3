import java.util.ArrayList;
import java.util.TreeSet;
import java.util.HashMap;
import java.util.Arrays;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.Font;
import java.awt.Color;

import javax.imageio.*;

public class EndPanel extends JPanel implements ActionListener{
    private KingdomBuilder frame;
    private BufferedImage woodBG, leaderboard, viewBoard, newGame;
    private JButton viewBoardButton, newGameButton;

    private static final int LEADERBOARD_TOP_X = 150;
    private static final int LEADERBOARD_TOP_Y = 250;
    private static final int LEADERBOARD_INDENTED = LEADERBOARD_TOP_Y + 20;
    private static final int RANK_SPACING = 90;
    private static final int PLAYER_SPACING = 70;
    private static final Font bigFont = new Font("Times New Roman", Font.BOLD, 60);
    private static final Font smallFont = new Font("Verdana", Font.PLAIN, 50);

    private static final int PLAYER_X = 700;
    private static final int PLAYER_Y_SPACING = 90;

    private static final int OBJECTIVE_X = PLAYER_X + 300;
    private static final int OBJECTIVE_X_SPACING = 150;
    private static final int OBJECTIVE_X_IMAGE_LEFT_OFFSET = 31;
    private static final int OBJECTIVE_Y = 210;

    private static final int PLAYER_Y = OBJECTIVE_Y + 200;

    private static final int OBJECTIVE_CARD_WIDTH = 97;
    private static final int OBJECTIVE_CARD_HEIGHT = 142;

    private static final int CASTLE_SIZE = 147;

    private static BufferedImage CASTLE_IMAGE;

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
        
        newGameButton = new JButton("");
        newGameButton.setOpaque(false);
        newGameButton.setContentAreaFilled(false);
        newGameButton.setBorderPainted(false);
        newGameButton.setSize(242, 93);
        newGameButton.setLocation(1300, 860);
        newGameButton.setFocusable(false);
        newGameButton.addActionListener(this);

        BufferedImage viewBoardBlackened = null;
        BufferedImage newGameBlackened = null;

        try {
            woodBG = ImageIO.read(EndPanel.class.getResource("/Images/straightWood.png"));
            leaderboard = ImageIO.read((EndPanel.class.getResource("/Images/LEADERBOARD.png")));
            viewBoard = ImageIO.read(EndPanel.class.getResource("/Images/End Panel Buttons/viewBoardButton.png"));
            viewBoardBlackened = ImageIO.read(EndPanel.class.getResource("/Images/End Panel Buttons/View Board Button Blackened.png"));
            newGame = ImageIO.read(EndPanel.class.getResource("/Images/End Panel Buttons/newGameButton.png"));
            newGameBlackened = ImageIO.read(EndPanel.class.getResource("/Images/End Panel Buttons/New Game Button Blackened.png"));
            CASTLE_IMAGE = ImageIO.read(EndPanel.class.getResource("/Images/castleTile.png"));
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("End panel error");
        }

        int width = 240;
        int height = 93;
        viewBoardButton.setIcon(new ImageIcon(viewBoard.getScaledInstance(width, height, Image.SCALE_SMOOTH)));
        viewBoardButton.setRolloverIcon(new ImageIcon(viewBoardBlackened.getScaledInstance(width, height, Image.SCALE_SMOOTH)));
        add(viewBoardButton);
        
        newGameButton.setIcon(new ImageIcon(newGame.getScaledInstance(width, height, Image.SCALE_SMOOTH)));
        newGameButton.setRolloverIcon(new ImageIcon(newGameBlackened.getScaledInstance(width, height, Image.SCALE_SMOOTH)));
        add(newGameButton);
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
        //g.drawImage(viewBoard, 1300, 760, 242, 93, null);
        //g.drawImage(newGame, 1300, 860, 242, 93, null);

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

        for(int i = 0; i < 3; i++) 
            g.drawImage(CardDrawer.getObjectiveCardImage(objectives[i]), OBJECTIVE_X + i * OBJECTIVE_X_SPACING - OBJECTIVE_X_IMAGE_LEFT_OFFSET, OBJECTIVE_Y, OBJECTIVE_CARD_WIDTH, OBJECTIVE_CARD_HEIGHT, null);

        g.drawImage(CASTLE_IMAGE, OBJECTIVE_X + 3 * OBJECTIVE_X_SPACING - OBJECTIVE_X_IMAGE_LEFT_OFFSET + (OBJECTIVE_CARD_WIDTH - CASTLE_SIZE) / 2 - 1, OBJECTIVE_Y, CASTLE_SIZE, CASTLE_SIZE, null);

        int y = PLAYER_Y;
        for(int i = 0; i < 4; i++) {
            g.drawString("Player " + (i + 1), PLAYER_X, y);

            int x = OBJECTIVE_X;
            for(String obj: objectives) {
                g.drawString("" + scores.get(obj).get(i), x, y);
                x += OBJECTIVE_X_SPACING;
            }
            g.drawString("" + scores.get("castle").get(i), x, y);

            y += PLAYER_Y_SPACING;
        }

        g.setFont(old);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==newGameButton){
            //frame.nextScreen();
            frame.restart();
        }
        if(e.getSource()==viewBoardButton){
            frame.viewBoard();
        }
    }
}
