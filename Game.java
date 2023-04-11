public class Game {
    private KingdomBuilderPanel panel;
    private CardDrawer cardDrawer;

    public Board board;
    public Player[] players;

    public Game(KingdomBuilderPanel p) {
        panel = p;
        board = new Board();

        players = new Player[4];
        for(int i = 0; i < players.length; i++) {
            players[i] = new Player();
        }
        System.out.println(players[0]);

        init();
    }

    private boolean objectivesContain(String s) {
        for(String card: objectiveCards) {
            if(s.equals(card)) {
                return true;
            }
        }

        return false;
    }

    public void init() {
        board.createBoard();
    }

    public void score() {
        if(objectivesContain("fishermen")) {

        }
        if(objectivesContain("miners")) {

        }
        if(objectivesContain("workers")) {
            
        }
    }
}
