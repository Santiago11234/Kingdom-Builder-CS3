import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.util.ArrayList;
import java.util.TreeSet;

public class KingdomBuilderPanel extends JPanel implements ActionListener, MouseListener {
    private KingdomBuilder frame;
    private Game game;

    private JButton startButton, returnButton;
    private JButton settlementButton;
    private JButton endTurnButtonButton;

    private JButton obj1Button;
    private JButton obj2Button;
    private JButton obj3Button;

    private JButton pwr1;
    private JButton pwr2;
    private JButton pwr3;
    private JButton pwr4;
    private JButton pwr5;
    private JButton pwr6;
    private JButton pwr7;
    private JButton pwr8;

    //make an array of the pwr buttons

    private Boolean obj1ButtonTF = false;
    private Boolean obj2ButtonTF = false;
    private Boolean obj3ButtonTF = false;

    private ArrayList<Tile> arrayOfSpecials = new ArrayList<Tile>();

    private BufferedImage blurBG, playerWood,mapWood, settlementWood, player1NameBlock, player2NameBlock, player3NameBlock, player4NameBlock, addSettlementButton, settlementButtonBlackened,
            endTurnButton, endTurnBlackened, settlementCountBlock, deckTextBlock, discardTextBlock,purpleSettlement,yellowSettlement,blueSettlement,redSettlement,player1Small,player2Small,player3Small,player4Small,firstPlayer; 

    public KingdomBuilderPanel(KingdomBuilder kb) {
        setSize(getPreferredSize());
        setLayout(null);
        frame = kb;
        
        obj1Button = new JButton(""); 
        obj1Button.setBounds(1117, 168, 129, 189);
        obj1Button.setOpaque(false);
        obj1Button.setContentAreaFilled(false);
        obj1Button.setBorderPainted(false);
        obj1Button.setFocusable(false);

        obj2Button = new JButton("");
        obj2Button.setBounds(1270, 168, 129, 189);
        obj2Button.setOpaque(false);
        obj2Button.setContentAreaFilled(false);
        obj2Button.setBorderPainted(false);
        obj2Button.setFocusable(false);

        obj3Button = new JButton("");
        obj3Button.setBounds(1423, 168, 129, 189);
        obj3Button.setOpaque(false);
        obj3Button.setContentAreaFilled(false);
        obj3Button.setBorderPainted(false);
        obj3Button.setFocusable(false);

        add(obj1Button);
        add(obj2Button);
        add(obj3Button);

        pwr1 = new JButton("HI");
        pwr2 = new JButton("HI");
        pwr3 = new JButton("HI");
        pwr4 = new JButton("HI");
        pwr5 = new JButton("HI");
        pwr6 = new JButton("HI");
        pwr7 = new JButton("HI");
        pwr8 = new JButton("HI");

        add(pwr1);
        add(pwr2);
        add(pwr3);
        add(pwr4);
        add(pwr5);
        add(pwr6);
        add(pwr7);
        add(pwr8);

        JButton[] pwrButtons = {pwr1,pwr2,pwr3,pwr4,pwr5,pwr6,pwr7,pwr8};


        // startButton = new JButton("skip to end button");
        // startButton.setOpaque(false);
        // startButton.setContentAreaFilled(true);
        // startButton.setBorderPainted(false);
        // startButton.setSize(200, 30);
        // startButton.setLocation(1237, 0);
        // startButton.setFocusable(false);
        // startButton.addActionListener(this);
        // add(startButton);

        settlementButton = new JButton();
        settlementButton.setBounds(1409, 652, 114, 110);
        settlementButton.setBorder(null);
        settlementButton.setContentAreaFilled(false);
        settlementButton.setFocusable(false);
        settlementButton.addActionListener(this);

        endTurnButtonButton = new JButton();
        endTurnButtonButton.setBounds(1144, 840, 381, 49);
        endTurnButtonButton.setBorder(null);
        endTurnButtonButton.setContentAreaFilled(false);
        endTurnButtonButton.setFocusable(false);
        endTurnButtonButton.addActionListener(this);

        addMouseListener(this);

        try {
            blurBG = ImageIO.read(KingdomBuilderPanel.class.getResource("/Images/blurred BG.jpg"));
            playerWood = ImageIO.read(KingdomBuilderPanel.class.getResource("/Images/playerWood.png"));
            mapWood = ImageIO.read(KingdomBuilderPanel.class.getResource("/Images/mapWood.png"));
            settlementWood = ImageIO.read(KingdomBuilderPanel.class.getResource("/Images/settlementWood.png"));
            player1NameBlock = ImageIO.read(KingdomBuilderPanel.class.getResource("/Images/player1NameBlock.png"));
            player2NameBlock = ImageIO.read(KingdomBuilderPanel.class.getResource("/Images/player2NameBlock.png"));
            player3NameBlock = ImageIO.read(KingdomBuilderPanel.class.getResource("/Images/player3NameBlock.png"));
            player4NameBlock = ImageIO.read(KingdomBuilderPanel.class.getResource("/Images/player4NameBlock.png"));
            addSettlementButton = ImageIO.read(KingdomBuilderPanel.class.getResource("/Images/addSettlementButton.png"));
            endTurnButton = ImageIO.read(KingdomBuilderPanel.class.getResource("/Images/endTurnButton.png"));
            settlementCountBlock = ImageIO.read(KingdomBuilderPanel.class.getResource("/Images/settlementCountBlock.png"));
            deckTextBlock = ImageIO.read(KingdomBuilderPanel.class.getResource("/Images/deckTextBlock.png"));
            discardTextBlock = ImageIO.read(KingdomBuilderPanel.class.getResource("/Images/discardTextBlock.png"));
            purpleSettlement = ImageIO.read(KingdomBuilderPanel.class.getResource("/Images/Purple Settlement.png"));
            yellowSettlement = ImageIO.read(KingdomBuilderPanel.class.getResource("/Images/Yellow Settlement.png"));
            blueSettlement = ImageIO.read(KingdomBuilderPanel.class.getResource("Images/Blue Settlement.png"));
            redSettlement =ImageIO.read(KingdomBuilderPanel.class.getResource("/Images/Red Settlement.png"));
            player1Small = ImageIO.read(KingdomBuilderPanel.class.getResource("/Images/player1NameBlockSmall.png"));
            player2Small = ImageIO.read(KingdomBuilderPanel.class.getResource("/Images/player2NameBlockSmall.png"));
            player3Small = ImageIO.read(KingdomBuilderPanel.class.getResource("/Images/player3NameBlockSmall.png"));
            player4Small = ImageIO.read(KingdomBuilderPanel.class.getResource("/Images/player4NameBlockSmall.png"));
            firstPlayer = ImageIO.read(KingdomBuilderPanel.class.getResource("/Images/FirstPlayerToken.PNG"));

            settlementButtonBlackened = ImageIO.read(KingdomBuilderPanel.class.getResource("Images/Add Settlement Button Blackened.png"));
            endTurnBlackened = ImageIO.read(KingdomBuilderPanel.class.getResource("Images/End Turn Button Blackened.png"));


            obj1Button.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    game.editTF(1);
                    System.out.println("Mouse Entered");
                    System.out.println(" Obj 1 Made Smaller");
                    obj1Button.setBounds(1015, 15, 235, 345);
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    game.editTF(1);
                    System.out.println("Mouse EXITED");
                    System.out.println("Obj 1 Made Bigger");
                    obj1Button.setBounds(1117, 168, 129, 189);
                }
            });
            obj2Button.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    game.editTF(2);
                    System.out.println("Mouse Entered");
                    System.out.println(" Obj 2 Made Smaller");
                    obj2Button.setBounds(1168, 15, 235, 345);
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    game.editTF(2);
                    System.out.println("Mouse EXITED");
                    System.out.println("Obj 2 Made Bigger");
                    obj2Button.setBounds(1270, 168, 129, 189);
                }
            });
            obj3Button.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    game.editTF(3);
                    System.out.println("Mouse Entered");
                    System.out.println(" Obj 3 Made Smaller");
                    obj3Button.setBounds(1321, 15, 235, 345);
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    game.editTF(3);
                    System.out.println("Mouse EXITED");
                    System.out.println("Obj 3 Made Bigger");
                    obj3Button.setBounds(1423, 168, 129, 189);
                }
            });

            //replicate the code for button 3 for button 1 and 2

            

        } catch (Exception e) {
            System.out.println("Kingdom Builder panel error");
        }

        //?????? Not doing this results in Java cropping it slightly.
        int width = 114;
        int height = 110;
        settlementButton.setIcon(new ImageIcon(addSettlementButton.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH)));
        settlementButton.setRolloverIcon(new ImageIcon(settlementButtonBlackened.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH)));
        settlementButton.setDisabledIcon(settlementButton.getRolloverIcon());
        add(settlementButton);

        endTurnButtonButton.setIcon(new ImageIcon(endTurnButton));
        endTurnButtonButton.setRolloverIcon(new ImageIcon(endTurnBlackened));
        endTurnButtonButton.setDisabledIcon(endTurnButtonButton.getRolloverIcon());
        add(endTurnButtonButton);

        returnButton = new JButton("Return To Leaderboard");
        returnButton.setOpaque(false);
        returnButton.setContentAreaFilled(true);
        returnButton.setBorderPainted(false);
        returnButton.setSize(200, 30);
        returnButton.setLocation(1237, 45);
        returnButton.setFocusable(false);
        returnButton.addActionListener(this);
        returnButton.setVisible(false);
        add(returnButton);

        game = new Game(this);
        start();
        arrayOfSpecials = game.getGameBoard().getArrayOfSpecials();
        //iterate through pwrButtons in a for loop and use the x and y properties of the array of specials to set the bounds of the buttons
        System.out.println(arrayOfSpecials.size());
        for (int i = 0; i < arrayOfSpecials.size()-1; i++) {
            System.out.println("HELLOWORLD");
            pwrButtons[i].setBounds(arrayOfSpecials.get(i).getX(), arrayOfSpecials.get(i).getY(), 50, 50);
            System.out.println(arrayOfSpecials.get(i).getX());
            pwrButtons[i].setContentAreaFilled(true);
        }

    }

    public void start() {
        game.init();
    }

    public void restart() {
        returnButton.setVisible(false);
        start();
    }

    public void setSettlementButton(boolean b) {
    	//canPlaceSettlement = b;
        settlementButton.setEnabled(b);
    }

    public void setSwitchTurnButton(boolean b) {
        endTurnButtonButton.setEnabled(b);
    }

    public void enableReturnButton() {
        returnButton.setVisible(true);
    }

    public void paintComponent(Graphics l) {
        Graphics2D g = (Graphics2D)l;
        g.setFont(new Font("Roboto", Font.BOLD, 12));
        g.setColor(Color.black);
        
        g.clearRect(0, 0, getWidth(), getHeight());

        g.drawImage(blurBG, 0, 0, getWidth(), getHeight(), null);
        g.drawImage(playerWood, 1091, 42, 487, 882, null);
        g.drawImage(mapWood, 70, 90, 990, 858, null);
        g.drawImage(settlementWood, 70, 18, 990, 62, null);
 
        //TextBlocks on player section
        g.drawImage(settlementCountBlock, 1418, 772, 102, 32, null);
        g.drawImage(deckTextBlock, 1143, 581, 81, 32, null);
        g.drawString("Cards: " + game.getTerrainDeck().size(), 1157, 602);
        g.drawImage(discardTextBlock, 1270, 581, 102, 32, null);
        g.drawString("Discarded: " + game.getDiscard().size(), 1284, 602);

        //buttons on player section
        //g.drawImage(addSettlementButton, 1409, 652, 114, 110, null);
        //g.drawImage(endTurnButton, 1144, 840, 381, 49, null);

        //Player not main people info
        int temp = game.turn();
        int height = 50;
        int width = 60;
        if(temp == 0){
            //settlements images that will go by player's name
            g.drawImage(yellowSettlement, 350, 23, width, height, null);
            g.drawImage(player2Small,125,31,200,33,null);
            g.drawString("" +game.players[1].getSettlementsLeft(), 363, height+5);
            if(game.getFirstPlayer()==1){
                g.drawImage(firstPlayer, 300, 42, 20, 15, null);
            }

            g.drawImage(blueSettlement, 660, 23, width, height, null);
            g.drawImage(player3Small,435,31,200,33,null);
            g.drawString("" +game.players[2].getSettlementsLeft(), 676, height+5);
            if(game.getFirstPlayer()==2){
                g.drawImage(firstPlayer, 605, 42, 20, 15, null);
            }

            g.drawImage(redSettlement, 970, 23, width, height, null);
            g.drawImage(player4Small,745,31,200,33,null);
            g.drawString("" +game.players[3].getSettlementsLeft(), 983, height+5);

            if(game.getFirstPlayer()==3){
                g.drawImage(firstPlayer, 915, 42, 20, 15, null);
            }


            //player's name on player board and the settlement ontop of the add settlement button
            g.drawImage(player1NameBlock, 1117, 71, 436, 75, null);
            g.drawString("Settlements: " + game.players[temp].getSettlementsLeft(), 1421, 793);
            g.drawImage(purpleSettlement, 1130, 95, 48, 40, null);
            //idea to put settlement house ontop of addSettlement button
            //g.drawImage(purpleSettlement, 1436, 682, width, height, null);
            //first player token
            if(temp==game.getFirstPlayer()){
                g.drawImage(firstPlayer, 1500, 95, 45, 39, null);
            }
        }
        if(temp == 1){
            //player3
            g.drawImage(blueSettlement, 350, 23, width, height, null);
            g.drawImage(player3Small,125,31,200,33,null);
            g.drawString("" +game.players[2].getSettlementsLeft(), 363, height+5);
            if(game.getFirstPlayer()==2){
                g.drawImage(firstPlayer, 300, 42, 20, 15, null);
            }
            //player4
            g.drawImage(redSettlement, 660, 23, width, height, null);
            g.drawImage(player4Small,435,31,200,33,null);
            g.drawString("" +game.players[3].getSettlementsLeft(), 673, height+5);

            if(game.getFirstPlayer()==3){
                g.drawImage(firstPlayer, 605, 42, 20, 15, null);
            }


            //player1 stuff
            g.drawImage(player1Small,745,31,200,33,null);
            g.drawImage(purpleSettlement, 970, 23, width, height, null);
            g.drawString("" +game.players[0].getSettlementsLeft(), 983, height+5);

            if(game.getFirstPlayer()==0){
                g.drawImage(firstPlayer, 915, 42, 20, 15, null);
            }


            g.drawImage(player2NameBlock, 1117, 71, 436, 75, null);
            g.drawString("Settlements: " + game.players[temp].getSettlementsLeft(), 1421, 793);
            g.drawImage(yellowSettlement, 1130, 95, 48, 40, null);

            if(temp==game.getFirstPlayer()){
                g.drawImage(firstPlayer, 1500, 95, 45, 39, null);
            }
        }
        if(temp == 2){
            g.drawImage(redSettlement, 350, 23, width, height, null);
            g.drawImage(player4Small,125,31,200,33,null);
            g.drawString("" +game.players[3].getSettlementsLeft(), 363, height+5);
            if(game.getFirstPlayer()==3){
                g.drawImage(firstPlayer, 300, 42, 20, 15, null);
            }


            g.drawImage(purpleSettlement, 660, 23, width, height, null);
            g.drawImage(player1Small,435,31,200,33,null);
            g.drawString("" +game.players[0].getSettlementsLeft(), 673, height+5);
            if(game.getFirstPlayer()==0){
                g.drawImage(firstPlayer, 605, 42, 20, 15, null);
            }


            g.drawImage(yellowSettlement, 970, 23, width, height, null);
            g.drawImage(player2Small,745,31,200,33,null);
            g.drawString("" +game.players[1].getSettlementsLeft(), 983, height+5);

            if(game.getFirstPlayer()==1){
                g.drawImage(firstPlayer, 915, 42, 20, 15, null);
            }


            g.drawImage(player3NameBlock, 1117, 71, 436, 75, null);
            g.drawString("Settlements: " + game.players[temp].getSettlementsLeft(), 1421, 793);
            g.drawImage(blueSettlement, 1130, 95, 48, 40, null);

            if(temp==game.getFirstPlayer()){
                g.drawImage(firstPlayer, 1500, 95, 45, 39, null);
            }
        }
        if(temp == 3){
            g.drawImage(purpleSettlement, 350, 23, width, height, null);
            g.drawImage(player1Small,125,31,200,33,null);
            g.drawString("" +game.players[0].getSettlementsLeft(), 363, height+5);
            if(game.getFirstPlayer()==0){
                g.drawImage(firstPlayer, 300, 42, 20, 15, null);
            }

            g.drawImage(yellowSettlement, 660, 23, width, height, null);
            g.drawImage(player2Small,435,31,200,33,null);
            g.drawString("" +game.players[1].getSettlementsLeft(), 673, height+5);

            if(game.getFirstPlayer()==1){
                g.drawImage(firstPlayer, 605, 42, 20, 15, null);
            }


            g.drawImage(blueSettlement, 970, 23, width, height, null);
            g.drawImage(player3Small,745,31,200,33,null);
            g.drawString("" +game.players[2].getSettlementsLeft(), 983, height+5);

            if(game.getFirstPlayer()==2){
                g.drawImage(firstPlayer, 915, 42, 20, 15, null);
            }


            g.drawImage(player4NameBlock, 1117, 71, 436, 75, null);
            g.drawString("Settlements: " + game.players[temp].getSettlementsLeft(), 1421, 793);
            g.drawImage(redSettlement, 1130, 95, 48, 40, null);

            if(temp==game.getFirstPlayer()){
                g.drawImage(firstPlayer, 1500, 95, 45, 39, null);
            }
        }
        //Main player name ------- blocks moved above
        //g.drawImage(player1NameBlock, 1117, 71, 436, 75, null);
        //g.drawImage(player2NameBlock, 1117, 71, 436, 75, null);
        //g.drawImage(player3NameBlock, 1117, 71, 436, 75, null);
        //g.drawImage(player4NameBlock, 1117, 71, 436, 75, null);
        //Terrain Cards
        
        //g.drawImage(terrainCardDesert, 1392, 379, 161, 248, null);
        //g.drawImage(terrainCardFlowers, 1392, 379, 161, 248, null);
        //g.drawImage(terrainCardForest, 1392, 379, 161, 248, null);
        //g.drawImage(terrainCardGrass, 1392, 379, 161, 248, null);
        //g.drawImage(terrainCardBack, 1392, 379, 161, 248, null);
        
        game.drawAll(g);
    }


    public void endGame() {
        frame.endGame(game.getObjectives(), game.score());
    }

    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {
        //Richard: wtf?
        /*int x = e.getX();
        int y = e.getY();
        board.tileClicked(x,y).setType("red");*/
    }
    public void mouseExited(MouseEvent e) {}

    public void mouseClicked(MouseEvent e) {
        //Check this...
        if(!game.getNoMorePlease()){
        int x = e.getX();
        int y = e.getY();
        game.mostMoves(x, y);
        repaint();

        //if(x > 1409 && x < 1409 + 114 && y > 625 && y < 625+110 && canPlaceSettlement)
        //	game.startSettlementPlay();
        //if(x> 1144 && x < 1144+381 && y > 840 && y < 840+49) 
        //    game.switchTurn();
        }
    }

    public void actionPerformed(ActionEvent e) {
        if(!game.getNoMorePlease()){
            if(e.getSource() == settlementButton) {
                game.startSettlementPlay();
                repaint();
            }
            else if(e.getSource() == endTurnButtonButton) {
                game.switchTurn();
                repaint();
            }

            else{
                endGame();
                game.setNoMorePlease(true);
            }
        }else{
            frame.nextScreen();
        }
    }
}
