import java.util.HashSet;

import java.awt.Graphics;

public class Board {
    private static String[][][] smallBoards;    //Collection of 10-by-10 Tile arrays. All inputted manually
    
    //Top left coordinate
    public static final int X = 90;
    public static final int Y = 112;
    
    public Tile[][] board; //20-by-40 array of Tiles
    
    private Tile[] arr;

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
                int boing = (int)(Math.random() * 7);

                switch(boing) {
                    case 0:
                        board[i][j].setType("canyon");
                        break;

                    case 1:
                        board[i][j].setType("desert");
                        break;

                    case 2:
                        board[i][j].setType("flowers");
                        break;

                    case 3:
                        board[i][j].setType("forest");
                        break;

                    case 4:
                        board[i][j].setType("grass");
                        break;

                    case 5:
                        board[i][j].setType("mountain");
                        break;

                    case 6:
                        board[i][j].setType("water");
                        break;
                }
            }
        }
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