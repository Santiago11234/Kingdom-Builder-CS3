import java.awt.Graphics;

public class Board {
    private static String[][][] smallBoards;    //Collection of 10-by-10 Tile arrays. All inputted manually
    
    //Top left coordinate
    public static final int X = 20;
    public static final int Y = (KingdomBuilder.HEIGHT - 19 * Tile.PARTICULAR_POINT - Tile.HEIGHT) / 2;
    
    public Tile[][] board; //20-by-40 array of Tiles
    
    public Board() {
        board = new Tile[20][40];
        
        setTilePositions();
    }
    
    public void setTilePositions() {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j += 2) {
                if(i % 2 == 0) {
                    board[i][j] = new Tile(i, j);
                    board[i][j].setPosition(X + j / 2 * Tile.WIDTH, Y + i * Tile.PARTICULAR_POINT);
                }
                else {
                    board[i][j + 1] = new Tile(i, j + 1);
                    board[i][j + 1].setPosition(X + Tile.WIDTH / 2 + j / 2 * Tile.WIDTH, Y + i * Tile.PARTICULAR_POINT);
                }
            }
        }
    }
    
    public void createBoard() {
        for(int i = 0; i < board.length; i++) {
            for(int j = i % 2; j < board[i].length; j += 2) {
                //Richard: placeholder
                board[i][j].setType("forest");
            }
        }
    }
    
    //Richard: should work. Tested this once
    private Tile[][] rotated(Tile[][] arr) { 
        Tile[][] ret = new Tile[arr.length][arr[0].length];
        
        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr[0].length; j++) {
                ret[i][j] = arr[arr.length - i - 1][arr[0].length - j - 1];
            }
        }

        return ret;
    }

    public Tile tileClicked(int x, int y) { 
        for(int i = 0; i < board.length; i++) {
            for(int j = i % 2; j < board[i].length; j += 2) {
                if(board[i][j].clicked(x, y)) {
                    System.out.println(i + " " + j);
                    return board[i][j];
                }
            }
        }

        return null;
    }

    //Not finished
    public Tile[] getNeighbors(int i, int j) {
        if(board[i][j] == null) {
            System.out.println("Uh oh, Board thinks something is fuc-");
        }

        Tile[] ret = new Tile[6];

        return ret;
    }

    public void drawBoard(Graphics g) {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j += 2) {
                if(i % 2 == 0) {
                    board[i][j].draw(g);
                }
                else {
                    board[i][j + 1].draw(g);
                }
            }
        }
    }
}
