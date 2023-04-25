import java.util.HashSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.awt.Graphics2D;

public class Game {
    private KingdomBuilderPanel panel;
    
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
    private HashSet<Tile> eligibleTiles;
    private int settlementCount;    //Stores number of settlements placed
    private int powerUpTurnCount;    //Stores number of clicks a power up has been used for
    private PowerUp powerupSelected;
    private Tile tileToRemove;
    private boolean gameOver;

    public Game(KingdomBuilderPanel p){
        panel = p;
        board = new Board();
        players = new Player[4];
        for(int i = 0; i < players.length; i++){
            players[i] = new Player();
        }
        terrainDeck = new ArrayList<>();
        discard = new ArrayList<>();
        objectiveDeck = new String[3];
        eligibleTiles = new HashSet<>();

        c = new CardDrawer(this);
    }

    public void init() {
        for(int i = 0; i < players.length; i++){
            players[i].clear();
        }

        terrainDeck.clear();
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

        discard.clear();

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
        powerupPlaying = 0;
        eligibleTiles.clear();
        settlementCount = 0;
        powerUpTurnCount = 0;
        powerupSelected = null;
        tileToRemove = null;
        gameOver = false;
        //System.out.println(terrainDeck); //Richard: commented

        for(Player p: players) {
            p.setCard(terrainDeck.remove(0));
        }

        board.createBoard();

        //Richard: testing
        for(Player p: players) {
            for(int i = 0; i < 8; i++)
                p.powerups.put(new PowerUp("farm"), true);
            p.updatePowerUpPositions();
        }
    }
    
    //return terrain deck
    public ArrayList<String> getTerrainDeck(){
        return terrainDeck;
    }

    public ArrayList<String> getDiscard() {
        return discard;
    }

    public String[] getObjectives() {
        return objectiveDeck;
    }

    public String getPlayerCard() {
        return players[turn].getCard();
    }

    public int turn(){
        return turn;
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
        findEligibleTiles(players[turn].getCard(), true);
        settlementPlaying = 1;
        panel.setSettlementButton(false);
    }

    public void mostMoves(int x, int y) {
        //Richard: assuming that clicking an unused power up results in nothing
        if(powerupPlaying == 0 || (powerupPlaying == 1 || powerupPlaying == 2) && powerUpTurnCount == 0) {
            for(PowerUp p: players[turn].powerups.keySet()) {
                if(p.clicked(x, y)) {
                    //Richard: clicking this just results in cancellation of settlement playing. Nothing else
                    if(cancelSettlementPlay())
                        return;

                    powerupSelected = p;

                    if(players[turn].powerups.get(p)) {
                        if(powerupPlaying == 0)   //Richard: doing this in the name of "performance"
                            powerupPlaying = 1;
                        
                        powerUpMethod(null);
                    }

                    return;
                }
            }
        }

        //Richard: clicking on an occupied tile doesn't do anything
        Tile t = board.tileClicked(x, y);   
        
        //Richard: taking back actions
        if(t == null) {
            if(powerUpTurnCount == 0) {
                eligibleTiles.clear();
                powerupSelected = null;
                return;
            }

            if(cancelSettlementPlay())
                 return;
        }
        
        //Richard: powerupCount = 0 already checked above
        if(powerupPlaying == 1 || powerupPlaying == 2) {
            powerUpMethod(t);
        }

        else if(settlementPlaying == 1) {
            if(!addSettlement(t))
                return;

            settlementCount++;
            if(settlementCount == 3) {
                settlementPlaying = 2;
                settlementCount = 0;
                panel.setSwitchTurnButton(true);
            }
            else {
                findEligibleTiles(players[turn].getCard(), true);
            }
        }
    }

    private boolean cancelSettlementPlay() {
        if(settlementPlaying == 1 && settlementCount == 0) {
            settlementPlaying = 0;
            panel.setSettlementButton(true);
            eligibleTiles.clear();
            return true;
        }

        return false;
    }

    private void findEligibleTiles(String terrain, boolean adjacent) {
        if(adjacent) {
            for(Tile t: players[turn].settlements) {
                Tile[] neighbors = board.getNeighbors(t);

                for(Tile t2: neighbors) {
                    if(t2 != null && !t2.isOccupied() && t2.getType().equals(terrain)) {
                        eligibleTiles.add(t2);
                    }
                }
            }
            
            if(!eligibleTiles.isEmpty()) {
                return;
            }
        }

        eligibleTiles.addAll(board.unoccupiedTileOfTerrain(terrain));
    }

    private boolean addSettlement(Tile t) {
        if(eligibleTiles.contains(t)) {
            players[turn].settlements.add(t);
            t.setOccupied(true);
            score();
            addOrRemovePowerUps(t, true);
            enableOrDisablePowerUps();
            eligibleTiles.clear();

            if(players[turn].getSettlementsLeft() == 0) {
                settlementPlaying = 2;
                gameOver = true;
            }

            return true;
        }
        
        return false;
    }

    private void powerUpMethod(Tile t) {
        boolean increment = false; //Richard: if true, increments powerupPlaying to 2
        boolean end = false;

        switch(powerupSelected.getType()) {
            case "oracle":
                switch(powerUpTurnCount) {
                    case 0:
                        findEligibleTiles(players[turn].getCard(), true);
                
                    case 1:
                        if(addSettlement(t)) {
                            powerUpUsed();
                            increment = true;
                            end = true;
                        }
                }

                break;

            case "farm":
                switch(powerUpTurnCount) {
                    case 0:
                        findEligibleTiles("grass", true);
                
                    case 1:
                        if(addSettlement(t)) {
                            powerUpUsed();
                            increment = true;
                            end = true;
                        }
                }

                break;

            case "oasis":
            case "tower":
            case "tavern":
            case "barn":
            case "harbor":
            case "paddock":
        }
        
        if(increment)
            powerupPlaying = 2;

        if(end)
            powerUpTurnCount = 0;
    }

    private void powerUpUsed() {
        HashMap<PowerUp, Boolean> temp = players[turn].powerups;
        temp.replace(powerupSelected, false);
        powerupSelected = null;
        powerUpTurnCount = 0;
        tileToRemove = null;

        boolean none = true;
        for(PowerUp p: temp.keySet()) {
            if(temp.get(p))
                none = false;
                break;        
        }

        if(none) {
            powerupPlaying = 3;
        }
    }

    private HashSet<Tile> findTilesTavern() {
        return eligibleTiles;
    }

    private HashSet<Tile> findTilesPaddock() {
        return eligibleTiles;
    }

    private HashSet<Tile> twoTilesAway(Tile t) { 
        return eligibleTiles;
    }
    
    private void score() {
        int score = 0;

        if(objectivesContain("fishermen")) {
            for(Tile t: players[turn].settlements) {
                //Richard: pretty sure things on water don't count
                if(!t.getType().equals("water") && neighborsInclude(t, "water")) {
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

        players[turn].setPersonalPoints(score);
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
    
    //Richard: untested
    private void addOrRemovePowerUps(Tile t, boolean added) {
        if(added) {
            Tile[] neighbors = board.getNeighbors(t);
            for(Tile t2: neighbors) {
                if(t2 == null || !t2.isSpecialTile())
                    return;

                if(t2.isPowerupTile() && !players[turn].specialTiles.contains(t2))
                    players[turn].powerups.put(t.getPowerUp(), true);

                players[turn].specialTiles.add(t2);
            }
        }
    }

    private void enableOrDisablePowerUps() {
        HashMap<PowerUp, Boolean> powerups = players[turn].powerups;

        for(PowerUp p: powerups.keySet()) {
            switch(p.getType()) {
                case "oracle":
                    powerups.replace(p, !board.unoccupiedTileOfTerrain(players[turn].getCard()).isEmpty());

                case "farm":


                case "oasis":
                case "tower":
                case "tavern":
                case "barn":
                case "harbor":
                case "paddock":
            }
        }
    }

    public void switchTurn() {
        eligibleTiles.clear();
        settlementPlaying = powerupPlaying = 0;
        
        powerupSelected = null;
        tileToRemove = null;

        //Richard: switch turns here
        discard.add(0, players[turn].getCard());
        players[turn].setCard(terrainDeck.remove(0));

        turn = (turn + 1) % 4;

        panel.setSettlementButton(true);
        panel.setSwitchTurnButton(false);
    }

    public void drawAll(Graphics2D g) {
        board.drawBoard(g);
        c.draw(g);

        players[turn].drawAll(turn, g);

        for(Tile t: eligibleTiles) {
            t.bold(turn, g);
        }
    }
}