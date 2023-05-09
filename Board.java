import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import javax.imageio.ImageIO;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
//import java.lang.reflect.Array;

public class Board {
    private static SmallBoards sm;   //Collection of 10-by-10 Tile arrays. All inputted manually
    public BufferedImage board1, board2, board3, board4, board5, board6, board7;
    public BufferedImage[] boards;
    public Integer[] intArray ;
    public List<Integer> l;
    public static final int TILEWIDTH = 46;
    public static final int TILEHEIGHT = (int)Math.round(TILEWIDTH * 2 / Math.sqrt(3));
    //Top left coordinate
    public static final int X = 90;
    public static final int Y = 112;
    //make arraylist called arrayofspecials
    public ArrayList<Tile> arrayOfSpecials = new ArrayList<Tile>();
    
    public Tile[][] board; //20-by-40 array of Tiles
    
    private Tile[] arr;

    public Board() {
        board = new Tile[20][40];
        sm = new SmallBoards();
        intArray = new Integer[] {0, 1, 2, 3,4,5,6};
        l = Arrays.asList(intArray);
        boards = new BufferedImage[7];
        try {
            boards[0] = ImageIO.read(Board.class.getResource("/Images/Board1.png"));
            boards[1] = ImageIO.read(Board.class.getResource("/Images/Board2.png"));
            boards[2] = ImageIO.read(Board.class.getResource("/Images/Board3.png"));
            boards[3] = ImageIO.read(Board.class.getResource("/Images/Board4.png"));
            boards[4] = ImageIO.read(Board.class.getResource("/Images/Board5.png"));
            boards[5] = ImageIO.read(Board.class.getResource("/Images/Board6.png"));
            boards[6] = ImageIO.read(Board.class.getResource("/Images/Board7.png"));


        } catch (Exception e) {
            System.out.println("Kingdom Builder board error");
        }
        
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
        
		shuffleBoards();
        
        for(int i = 0; i < 10; i++) {
            for(int j = i % 2; j < 20; j += 2) {
                board[i][j].setType(sm.getBoard(getBoards().get(0))[i][j]);
                board[i][j].setOccupied(false);

                board[i+10][j].setType(sm.getBoard(getBoards().get(1))[i][j]);
                board[i + 10][j].setOccupied(false);

                board[i][j+20].setType(sm.getBoard(getBoards().get(2))[i][j]);
                board[i][j + 20].setOccupied(false);

                board[i+10][j+20].setType(sm.getBoard(getBoards().get(3))[i][j]);
                board[i + 10][j + 20].setOccupied(false);
            }
        }
    }

    public void shuffleBoards() {
        Collections.shuffle(l);
    }

    public List<Integer> getBoards() {
        return l;
    }

    public void setTileType(int i, int j, String type) {
        board[i][j].setType(type);
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
                    return board[i][j];
                }
            }
        }

        return null;
    }

    public Tile[] getNeighbors(Tile t) {
        int i = t.getRow();
        int j = t.getColumn();

        //Richard: shouldn't happen
        if(i > board.length || i < 0 && j > board[0].length || j < 0) {
            System.out.println("Out of bounds. i: " + i + ". j: " + j);
        }

        //Richard: also should happen
        if(board[i][j] == null) {
            System.out.println("How did this happen?");
        }

        Tile[] ret = new Tile[6];

        //Richard: no other checks should be necessary
        //  1  2
        //3      4
        //  5  6
        if(i - 1 >= 0) {
            if(j - 1 >= 0) {
                ret[0] = board[i - 1][j - 1];
            }
            if(j + 1 < board[0].length) {
                ret[1] = board[i - 1][j + 1];
            }
        }
        if(j - 2 >= 0) {
            ret[2] = board[i][j - 2];
        }
        if(j + 2 < board[0].length) {
            ret[3] = board[i][j + 2];
        }
        if(i + 1 < board.length) {
            if(j - 1 >= 0) {
                ret[4] = board[i + 1][j - 1];
            }
            if(j + 1 < board[0].length) {
                ret[5] = board[i + 1][j + 1];
            }
        }

        return ret;
    }

    public HashSet<Tile> unoccupiedTileOfTerrain(String terrain) {
        HashSet<Tile> ret = new HashSet<Tile>();

        for(int i = 0; i < board.length; i++) {
            for(int j = i % 2; j < board[i].length; j += 2) {
                if(board[i][j].getType().equals(terrain) && !board[i][j].isOccupied()) {
                    ret.add(board[i][j]);
                } 
            }
        }

        return ret;
    }
    
    //Richard: assumes that no border tiles are special
    public HashSet<Tile> unoccupiedBorderTiles() {
        HashSet<Tile> ret = new HashSet<Tile>();
        Tile temp;

        for(int j = 0; j < board[0].length; j += 2) {
            temp = board[0][j];
            
            if(Tile.isEligible(temp))
                ret.add(temp);
            
            temp = board[board.length - 1][j + 1];

            if(Tile.isEligible(temp)) {
                ret.add(temp);
            }
        }

        for(int i = 0; i < board.length; i++) {
            temp = board[i][i % 2];

            if(Tile.isEligible(temp))
                ret.add(temp);

            temp = board[i][board[0].length - (i + 1) % 2 - 1];

            if(Tile.isEligible(temp))
                ret.add(temp);
        }

        return ret;
    }

    //Richard: ultra-scuffed
    public HashSet<Tile> unoccupiedTwoTilesAway(Tile t) {
        HashSet<Tile> ret = new HashSet<Tile>();

        int i = t.getRow();
        int j = t.getColumn();

        i -= 2;
        addTile(ret, i, j);
        j -= 2;
        addTile(ret, i, j);
        j += 4;
        addTile(ret, i, j);

        i += 4;
        j -= 2;
        
        addTile(ret, i, j);
        j -= 2;
        addTile(ret, i, j);
        j += 4;
        addTile(ret, i, j);
        
        i -= 2;
        j -= 6;
        addTile(ret, i, j);
        j += 8;
        addTile(ret, i, j);

        i -= 1;
        j -= 7;
        addTile(ret, i, j);
        j += 6;
        addTile(ret, i, j);

        i += 2;
        j -= 6;
        addTile(ret, i, j);
        j += 6;
        addTile(ret, i, j);

        return ret;
    }

    private void addTile(HashSet<Tile> set, int i, int j) {
        if(i >= 0 && i < board.length && j >= 0 && j < board[0].length && j % 2 == i % 2) {
            if(Tile.isEligible(board[i][j]))
                set.add(board[i][j]);
        }
    }

    public void drawBoard(Graphics g) {
        g.drawImage(boards[getBoards().get(0)], 90,112, 482, 413, null);
        g.drawImage(boards[getBoards().get(1)], 90,98+414, 482, 413, null);

        //Richard: moved them right. Looks better imo
        g.drawImage(boards[getBoards().get(2)], 68+481 + 2,112, 482, 413, null);
        g.drawImage(boards[getBoards().get(3)], 68+481 + 2, 98+414, 482, 413, null);

        for(int i = 0; i < board.length; i++) {
            for(int j = i % 2; j < board[i].length; j += 2) {
                board[i][j].drawIfSpecial(g);
                //arrayOfSpecials.add(board[i][j]);
                //System.out.println(arrayOfSpecials.size());
                //System.out.println("YO WE STARTED");
            }
        }
    }
    //return array of specials
    public ArrayList<Tile> getArrayOfSpecials(){
        return arrayOfSpecials;
    }
}
