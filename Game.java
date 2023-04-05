public class Game {
    public Board board;

    public Game() {
        board = new Board();

        init();
    }

    public void init() {
        board.createBoard();
    }
}
