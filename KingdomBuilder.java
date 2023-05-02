import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import java.awt.CardLayout;

public class KingdomBuilder extends JFrame {
    public static final int WIDTH = 1600;
    public static final int HEIGHT = 960;

    private KingdomBuilderPanel gamePanel;
    private EndPanel endPanel;
    private CardLayout cardLayout;
    
    public KingdomBuilder() {
        super("Kingdom Builder"); 
        cardLayout = new CardLayout();
        setLayout(cardLayout);

        add(new StartPanel(this));
        gamePanel = new KingdomBuilderPanel(this);
        add(gamePanel);

        endPanel = new EndPanel(this);
        add(endPanel);

        pack();

        cardLayout.first(getContentPane());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
    
    public void nextScreen() {
        cardLayout.next(getContentPane());
    }

    public void endGame(String[] obj, HashMap<String, ArrayList<Integer>> scores) {
        endPanel.setObjectivesAndScore(obj, scores);
        nextScreen();
    }

    public void previousScreen() {
        cardLayout.previous(getContentPane());   
    }
    
    public void showBoard() {

    }

    public void restart() {
        cardLayout.first(getContentPane());
        gamePanel.start();
    }

    public static void main(String[] args) {
        new KingdomBuilder();
    }   
}
