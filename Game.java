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

        init();
    }

    public void init() {
        board.createBoard();
    }
}
