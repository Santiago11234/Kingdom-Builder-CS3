public class Game {
    private KingdomBuilderPanel panel;
    private CardDrawer cardDrawer;

    public Board board;
    public Player[] players;

    public Game(KingdomBuilderPanel p) {
        panel = p;
        board = new Board();

        players = new Player[4];
        System.out.println(players[0]);
        for(Player player: players) {
            player = new Player();
        }

        init();
    }

    public void init() {
        board.createBoard();
    }
}
