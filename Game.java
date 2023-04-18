import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.Graphics2D;

public class Game {
    private KingdomBuilderPanel panel;
    private CardDrawer cardDrawer;

    public Player[] players;
    public Board board;
    private ArrayList<String> terrainDeck;
    private ArrayList<String> discard;
    public String[] objectiveDeck;
    public CardDrawer c;

    private int firstPlayer;
    private int turn;
    private int settlementPlaying;    //A state variable. 0 (not started), 1 (playing), 2 (finished)
    private int powerupPlaying;    //Another state variable. 0 (not started), 1 (starting to play but not committed), 2 (committed to playing), 3 (finished)
    private TreeSet<Tile> eligibleTiles;
    private int settlementCount;    //Stores number of settlements placed
    private int powerupCount;    //Stores number of clicks a power up has been used for
    private PowerUp powerupSelected;
    private Tile tileToRemove;
    private boolean gameOver;

    public Game(KingdomBuilderPanel p){
        c = new CardDrawer();
        panel = p;
        board = new Board();
        players = new Player[4];
        for(int i = 0; i < players.length; i++){
            players[i] = new Player();
        }
        terrainDeck = new ArrayList<>();
        discard = new ArrayList<>();
        objectiveDeck = new String[3];
        eligibleTiles = new TreeSet<>();
        init();
    }

    public void init() {
        for(int i = 0; i < players.length; i++){
            players[i].clear();
        }
        for(int i =0; i < 25; i++){
            if(i%5==0){
                terrainDeck.add("canyon");
            }else if(i%5 == 1){
                terrainDeck.add("desert");
            }else if(i%5 == 2){
                terrainDeck.add("flowers");
            }else if(i%5== 3){
                terrainDeck.add("forest");
            }else if(i%5==4){
                terrainDeck.add("grass");
            }
        }
        Collections.shuffle(terrainDeck);
        int temp1 = (int)(Math.random()*10);
        int temp2 = temp1;
        int temp3 = temp2;
        while(temp2 == temp1){
            temp2 = (int)(Math.random()*10);
        }
        while(temp3 == temp2 || temp3 == temp1){
            temp3 = (int)(Math.random()*10);
        }
        objectiveDeck[0]= c.objectiveCards[temp1];
        objectiveDeck[1] = c.objectiveCards[temp2];
        objectiveDeck[2] = c.objectiveCards[temp3];
        firstPlayer = (int)(Math.random()*4);
        turn = firstPlayer;
        settlementPlaying = 0;
        powerupPlaying= 0;
        eligibleTiles.clear();
        settlementCount = 0;
        powerupCount = 0;
        powerupSelected = null;
        tileToRemove = null;
        gameOver = false;
        System.out.println(terrainDeck);
        board.createBoard();
    }

    public boolean firstPlayerPlaying() {
        if(turn == firstPlayer){
            return true;
        }
        return false;
    }

    private boolean objectivesContain(String s) {
        for(String card: objectiveDeck) {
           if(s.equals(card)) {
               return true;
           }
        }

        return false;
    }

    public void startSettlementPlay() {

    }

    public void mostMoves(int x, int y) {

    }

    private void findEligibleTiles(String terrain, boolean adjacent) {

    }

    private boolean addSettlement(Tile t) {
        if(eligibleTiles.contains(t)) {
            players[turn].settlements.add(t);
            t.setOccupied(true);
            score();
            addOrRemovePowerUps(t, true);
            enableOrDisablePowerUps();

            if(players[turn].getSettlementsLeft() == 0) {
                settlementPlaying = 2;
                gameOver = true;
            }
        }
        
        return false;
    }

    private void powerUpMethod(Tile t) {

    }

    private void powerUpUsed() {

    }

    private TreeSet<Tile> findTilesTavern() {
        return eligibleTiles;
    }

    private TreeSet<Tile> findTilesPaddock() {
        return eligibleTiles;
    }

    private TreeSet<Tile> twoTilesAway(Tile t) { 
        return eligibleTiles;
    }
    
    private void score() {
        int score = 0;

        if(objectivesContain("fishermen")) {
            for(Tile t: players[turn].settlements) {
                if(neighborsInclude(t, "water")) {
                    score++;
                }
            }
        }
        if(objectivesContain("miners")) {
            for(Tile t: players[turn].settlements){
                if(neighborsInclude(t, "mountain")){
                    score++;
                }
            }
        }
        if(objectivesContain("workers")) {
            for(Tile t: players[turn].settlements){
                if(neighborsInclude(t, "castle")||neighborsInclude(t, "oracle")||neighborsInclude(t, "farm")||neighborsInclude(t, "oasis")||neighborsInclude(t, "tower")||neighborsInclude(t, "tavern")||neighborsInclude(t, "barn")||neighborsInclude(t, "harbor")||neighborsInclude(t, "paddock")){
                    score++;
                }
            }
        }
    }

    private boolean neighborsInclude(Tile t, String terrain) {
        Tile[] arr = board.getNeighbors(t);
        
        for(Tile ti: arr) {
            if(ti != null && t.getType().equals(terrain)) {
                return true;
            }
        }

        return false;
    }

    private void scoreConnectedSpecialTiles() {

    }
    
    private ArrayList<Integer> settlementAreas() {
        return null;
    }
    
    private void addOrRemovePowerUps(Tile t, boolean added) {
    
    }

    private void enableOrDisablePowerUps() {

    }

    public void switchTurn() {
        turn = (turn + 1) % 4;
    }

    public void drawAll(Graphics2D g) {

    }
}
