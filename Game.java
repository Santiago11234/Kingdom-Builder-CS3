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
    //private int powerupPlaying;    //Richard: don't think this is necessary. Another state variable. 0 (not started), 1 (starting to play but not committed), 2 (committed to playing), 3 (finished)

    private HashSet<Tile> eligibleTiles;
    private int settlementCount;    //Stores number of settlements placed
    private int powerUpTurnCount;    //Stores number of clicks a power up has been used for
    private PowerUp powerupSelected;
    private Tile tileToRemove;
    private boolean gameOver;

    private Boolean obj1ButtonTF;
    private Boolean obj2ButtonTF;
    private Boolean obj3ButtonTF;

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
        obj1ButtonTF = false;
        obj2ButtonTF = false;
        obj3ButtonTF = false;
    }

    //return objtf
    public Boolean getObj1ButtonTF(){
        return obj1ButtonTF;
    }

    public Boolean getObj2ButtonTF(){
        return obj2ButtonTF;
    }

    public Boolean getObj3ButtonTF(){
        return obj3ButtonTF;
    }

    public void editTF(int x){
        if(x == 1){
            obj1ButtonTF = !obj1ButtonTF;
            System.out.println("BUTTON ONE SWITCHED TO: " + obj1ButtonTF);
        }else if(x == 2){
            obj2ButtonTF = !obj2ButtonTF;
            System.out.println("BUTTON TWO SWITCHED TO: " + obj2ButtonTF);
        }else if(x == 3){
            obj3ButtonTF = !obj3ButtonTF;
            System.out.println("BUTTON THREE SWITCHED TO: " + obj3ButtonTF);
        }
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
        eligibleTiles.clear();
        settlementCount = 0;
        powerUpTurnCount = 0;
        powerupSelected = null;
        tileToRemove = null;
        gameOver = false;
        //System.out.println(terrainDeck); //Richard: commented

        panel.setSettlementButton(true);
        panel.setSwitchTurnButton(false);

        for(Player p: players) {
            p.setCard(terrainDeck.remove(0));
        }

        board.createBoard();

        //Richard: testing
        for(Player p: players) {
            p.powerups.put(new PowerUp("farm"), 0);
            p.powerups.put(new PowerUp("oracle"), 0);
            p.powerups.put(new PowerUp("oasis"), 0);
            p.powerups.put(new PowerUp("tower"), 0);
            p.powerups.put(new PowerUp("tavern"), 0);
            p.powerups.put(new PowerUp("barn"), 0);
            p.powerups.put(new PowerUp("harbor"), 0);
            p.powerups.put(new PowerUp("paddock"), 0);

            p.updatePowerUpPositions();
        }
    }

    public int getFirstPlayer(){
        return firstPlayer;
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
        eligibleTiles = findEligibleTiles(players[turn].settlements, players[turn].getCard(), true);
        settlementPlaying = 1;
        panel.setSettlementButton(false);
    }

    public void mostMoves(int x, int y) {

        

        //Richard: assuming that clicking an unused power up results in nothing
        if((powerUpTurnCount == 0  || powerUpTurnCount == 1)&& (settlementPlaying != 1 || settlementPlaying == 1 && settlementCount == 0)) {
            for(PowerUp p: players[turn].powerups.keySet()) {
                if(p.clicked(x, y)) {
                    //Richard: clicking this just results in cancellation of settlement playing. Nothing else
                    cancelSettlementPlay();

                    //Richard: should work
                    PowerUp temp = powerupSelected;
                    if(cancelPowerUpPlay())
                        if(temp == p)
                            return;

                    temp = null;
                    powerupSelected = p;
                    if(players[turn].powerups.get(p) == 0) {
                        //Richard: if cancelPowerUpPlay is false, then no power ups are playing
                        powerUpMethod(null);
                        return;
                    }   
                }
            }
        }

        //Richard: clicking on an occupied tile doesn't do anything
        Tile t = board.tileClicked(x, y);   
        
        //Richard: taking back actions
        if(t == null) {
            if(cancelPowerUpPlay() || cancelSettlementPlay())
                 return;
        }
        
        //Richard: powerupCount = 0 already checked above
        if(powerUpTurnCount != 0) {
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
                eligibleTiles = findEligibleTiles(players[turn].settlements, players[turn].getCard(), true);
            }
        }
    }

    private boolean cancelPowerUpPlay() {
        if(powerUpTurnCount == 1) {
            powerUpTurnCount = 0;
            powerupSelected = null;
            eligibleTiles.clear();
            return true;
        }

        return false;
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

    private HashSet<Tile> findEligibleTiles(HashSet<Tile> settlements, String terrain, boolean adjacent) {
        HashSet<Tile> ret = new HashSet<Tile>();

        if(adjacent) {
            for(Tile t: settlements) {
                Tile[] neighbors = board.getNeighbors(t);

                for(Tile t2: neighbors) {
                    if(t2 != null && !t2.isOccupied() && t2.getType().equals(terrain)) {
                        ret.add(t2);
                    }
                }
            }
        }

        if(ret.isEmpty())
            ret.addAll(board.unoccupiedTileOfTerrain(terrain));
        
        return ret;
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
                panel.setSettlementButton(false);
            }

            return true;
        }
        
        return false;
    }

    private void powerUpMethod(Tile t) {
    	if(t == null && powerUpTurnCount != 0)
    		return;
    
        boolean end = false;

        switch(powerupSelected.getType()) {
            case "oracle":
                if(oracleFarmOasis(t, players[turn].getCard()))
                    end = true;
                break;

            case "farm":
                if(oracleFarmOasis(t, "grass"))
                    end = true;
                break;

            case "oasis":
                if(oracleFarmOasis(t, "desert"))
                    end = true;
                break;

            case "tower":
                switch(powerUpTurnCount) {
                    case 0:
                        eligibleTiles = eligibleBorderTiles();
                        powerUpTurnCount++;
                        break;

                    case 1:
                        if(addSettlement(t)) {
                            end = true;
                        }
                }
                break;

            case "tavern":
                switch(powerUpTurnCount) {
                    case 0:
                        eligibleTiles = findTilesTavern();
                        powerUpTurnCount++;
                        break;

                    case 1:
                        if(addSettlement(t)) {
                            end = true;
                        }
                }
                break;

            case "barn":
                if(barnHarbor(t, players[turn].getCard()))
                    end = true;
                break;

            case "harbor":
                if(barnHarbor(t, "water"))
                    end = true;
                break;

            case "paddock":
                switch(powerUpTurnCount) {
                    case 0: 
                        eligibleTiles = findTilesPaddock();
                        powerUpTurnCount++;
                        break;

                    case 1:
                        if(!eligibleTiles.contains(t))
                            return;

                        tileToRemove = t;
                        eligibleTiles = board.unoccupiedTwoTilesAway(t);
        
                        powerUpTurnCount++;
                        break;

                    case 2:
                        if(settlementMoving(t))
                            end = true;  
                }
        }

        if(powerUpTurnCount >= 2)
            panel.setSettlementButton(false);
            panel.setSwitchTurnButton(false);

        if(end)
            powerUpUsed();
    }

    private void powerUpUsed() {
        HashMap<PowerUp, Integer> temp = players[turn].powerups;
        temp.replace(powerupSelected, 2);
        powerupSelected = null;
        powerUpTurnCount = 0;
        tileToRemove = null;

        if(settlementPlaying != 2)
            panel.setSettlementButton(true);

        else
            panel.setSwitchTurnButton(true);
    }

    private boolean oracleFarmOasis(Tile t, String terrain) {
        switch(powerUpTurnCount) {
            case 0: 
                eligibleTiles = findEligibleTiles(players[turn].settlements, terrain, true);
                powerUpTurnCount++;
                break;

            case 1:
                if(addSettlement(t))
                    return true;
        }

        return false;
    }

    private boolean barnHarbor(Tile t, String terrain) {
        switch(powerUpTurnCount) {
            case 0:
                eligibleTiles = new HashSet<Tile>(players[turn].settlements);
                powerUpTurnCount++;
                break;

            case 1:
                if(!eligibleTiles.contains(t))
                    return false;

                tileToRemove = t;

                HashSet<Tile> temp = new HashSet<Tile>(players[turn].settlements);
                temp.remove(t);
                eligibleTiles = findEligibleTiles(temp, terrain, true);
                eligibleTiles.remove(t);

                powerUpTurnCount++;
                break;

            case 2:
                return settlementMoving(t);       
        }

        return false;
    }

    private boolean settlementMoving(Tile t) {
        if(eligibleTiles.contains(t)) {
            players[turn].settlements.remove(tileToRemove);
            tileToRemove.setOccupied(false);
            addOrRemovePowerUps(tileToRemove, false);
            addSettlement(t);
            tileToRemove = null;
            return true;
        }

        return false;
    }

    private HashSet<Tile> eligibleBorderTiles() {
        HashSet<Tile> ret = new HashSet<Tile>();
        HashSet<Tile> settlements = players[turn].settlements;
        HashSet<Tile> borderTiles = board.unoccupiedBorderTiles();
        
        for(Tile t: settlements) {
            Tile[] neighbors = board.getNeighbors(t);
            
            for(Tile t2: neighbors)
                if(t2 != null && borderTiles.contains(t2))
                    ret.add(t2);
        }

        if(ret.isEmpty())
            ret = borderTiles;

        return ret;
    }

    private HashSet<Tile> findTilesTavern() {
        HashSet<Tile> eligible = new HashSet<Tile>();
        HashSet<Tile> visited = new HashSet<Tile>();
        HashSet<Tile> settlements = players[turn].settlements;

        for(Tile t: settlements) {
            if(!visited.add(t)) {
                continue;
            }

            //Richard: 0 is up-left to down-right. 1 is up-right to down-left. 2 is left to right
            for(int i = 0; i < 3; i++) {
                tavernOneDirection(i, board.getNeighbors(t), eligible, visited, settlements);
            }
        }

        return eligible;
    }

    //Richard: probably not super efficient, but it works (I think)
    private void tavernOneDirection(int direction, Tile[] neighbors, HashSet<Tile> eligible, HashSet<Tile> visited, HashSet<Tile> settlements) {
        int lineLength = 1;
        int oppDirection = 5 - direction;

        Tile left = neighbors[direction];
        Tile right = neighbors[oppDirection];

        while(left != null && settlements.contains(left)) {
            lineLength++;
            visited.add(left);
            left = board.getNeighbors(left)[direction];
        }
        while(right != null && settlements.contains(right)) {
            lineLength++;
            visited.add(right);
            right = board.getNeighbors(right)[oppDirection];
        }

        if(lineLength < 3) 
            return;

        if(left != null && Tile.isEligible(left))
            eligible.add(left);

        if(right != null && Tile.isEligible(right))
            eligible.add(right);
    }

    private HashSet<Tile> findTilesPaddock() {
        HashSet<Tile> ret = new HashSet<>();
        
        for(Tile t: players[turn].settlements)
            if(!board.unoccupiedTwoTilesAway(t).isEmpty())    
                ret.add(t);

        return ret;
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
    
    //Richard: untested but should be finished. Also adds or removes special tiles
    private void addOrRemovePowerUps(Tile t, boolean added) {
        Tile[] neighbors = board.getNeighbors(t);
        for(Tile t2: neighbors) {
            if(t2 == null || !t2.isSpecialTile())
                continue;

            if(added)
                addPowerUp(t2);

            else
                removePowerUp(t2);

            //return; //Richard: try this out
        }
    }

    private void addPowerUp(Tile t) {
        Player p = players[turn];

        if(p.specialTiles.contains(t))
            return;

        p.specialTiles.add(t);

        if(t.isPowerupTile() && t.hasPowerUp()) {
            p.powerups.put(t.getPowerUp(), 2);
            p.updatePowerUpPositions();
        }
    }

    private void removePowerUp(Tile t) {
        Player p = players[turn];

        if(!p.specialTiles.contains(t))
            return;

        p.specialTiles.remove(t);

        if(!t.isPowerupTile())
            return;

        //Richard: assuming that removal of one tile will only affect one special tile
        boolean powerUpRemoved = false;

        for(PowerUp powerUp: p.powerups.keySet())
            if(powerUp.getType().equals(t.getType())) {
                p.powerups.remove(powerUp);
                powerUpRemoved = true;
                break;
            }
        
        if(powerUpRemoved) {
            p.updatePowerUpPositions();
        }
    }

    private void enableOrDisablePowerUps() {
        Player player = players[turn];
        boolean disable;

        for(PowerUp p: player.powerups.keySet()) {
            disable = false;

            switch(p.getType()) {
                case "oracle":
                    if(player.getSettlementsLeft() == 0 || board.unoccupiedTileOfTerrain(player.getCard()).isEmpty())
                        disable = true;

                    break;

                case "farm":
                    if(player.getSettlementsLeft() == 0 || board.unoccupiedTileOfTerrain("grass").isEmpty())
                        disable = true;

                    break;

                case "oasis":
                    if(player.getSettlementsLeft() == 0 || board.unoccupiedTileOfTerrain("desert").isEmpty())
                        disable = true;

                    break;

                case "tower":
                    if(player.getSettlementsLeft() == 0 || board.unoccupiedBorderTiles().isEmpty())
                        disable = true;

                    break;

                case "tavern":
                    if(player.getSettlementsLeft() == 0 || findTilesTavern().isEmpty())
                        disable = true;

                    break;

                case "barn":
                    if(findEligibleTiles(player.settlements, getPlayerCard(), true).isEmpty())
                        disable = true;

                    break;

                case "harbor":
                    if(findEligibleTiles(player.settlements, getPlayerCard(), true).isEmpty())
                        disable = true;

                    break;

                case "paddock":
                    if(findTilesPaddock().isEmpty())
                        disable = true;
            }

            if(disable && player.powerups.get(p) != 1)
                player.powerups.replace(p, 1);

            else if(!disable && player.powerups.get(p) == 1)
                player.powerups.replace(p, 0);
        }
    }

    public void switchTurn() {
        eligibleTiles.clear();
        settlementPlaying = 0;
        
        powerupSelected = null;

        discard.add(0, players[turn].getCard());
        if(terrainDeck.size()<1){
            for(int i = 0; i < discard.size();i++){
                terrainDeck.add(i, discard.get(i));
            }
            discard.clear();
            Collections.shuffle(terrainDeck);
        }
        players[turn].setCard(terrainDeck.remove(0));

        HashMap<PowerUp, Integer> powerups = players[turn].powerups;
        for(PowerUp p: powerups.keySet())
            if(powerups.get(p) == 2)
                powerups.replace(p, 0);

        turn = (turn + 1) % 4;
        enableOrDisablePowerUps();
        
        //System.out.println("Turn " + turn);
        panel.setSettlementButton(true);
        panel.setSwitchTurnButton(false);

        if(gameOver && turn == firstPlayer) {}
    }

    public void drawAll(Graphics2D g) {
        board.drawBoard(g);
        c.draw(g);

        for(int i = 0; i < 4; i++) {
            players[i].drawSettlements(i, g);
        }
        players[turn].drawPowerUps(g);

        for(Tile t: eligibleTiles) {
            t.bold(turn, g);
        }

        if(tileToRemove != null) {
            tileToRemove.bold(4, g);
        }
    }
}
