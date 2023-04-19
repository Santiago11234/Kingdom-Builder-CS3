import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.awt.Color;
import java.awt.color.*;
import javax.imageio.ImageIO;

public class ImageProcessing {

   
    
    public static void main(String[] args) throws IOException {
        final int[] mountainRGB = {121,107,95};
        final int[] desertRGB = {252,212,53};
        final int[] forestRGB = {3,50,11};
        final int[] grasslandRGB = {105,152,60};
        final double colorThreshold = 150.0;

        BufferedImage boardImage = ImageIO.read(ImageProcessing.class.getResource("/Images/Board1.png"));
        int width = boardImage.getWidth();
        int height = boardImage.getHeight();
        Board board = new Board();
       



        for (int i = 30, x= 0; i < width; i+=width / 10.5, x++) {
            for (int j = 30, y =x%2; j < height; j += height / 10, y+=2) {
                
                int rgb = boardImage.getRGB(i, j);
                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = rgb & 0xFF;
                
                double distToMountain = Math.sqrt(Math.pow(red - mountainRGB[0], 2) + Math.pow(green - mountainRGB[1], 2) + Math.pow(blue - mountainRGB[2], 2));
                double distToDesert = Math.sqrt(Math.pow(red - desertRGB[0], 2) + Math.pow(green - desertRGB[1], 2) + Math.pow(blue - desertRGB[2], 2));
                double distToForest = Math.sqrt(Math.pow(red - forestRGB[0], 2) + Math.pow(green - forestRGB[1], 2) + Math.pow(blue - forestRGB[2], 2));
                double distToGrassland = Math.sqrt(Math.pow(red - grasslandRGB[0], 2) + Math.pow(green - grasslandRGB[1], 2) + Math.pow(blue - grasslandRGB[2], 2));
                
               

                if (distToMountain < colorThreshold) {
                    board.setTileType(x, y, "mountain");
                } else if (distToDesert < colorThreshold) {
                    board.setTileType(x, y, "desert");
                } else if (distToForest < colorThreshold) {
                    board.setTileType(x, y, "forest");
                } else if (distToGrassland < colorThreshold) {
                    board.setTileType(x, y, "grassland");
                }
                else {
                    board.setTileType(x, y, "water");
                }

                System.out.println(board.board[x][y].getType());

                System.out.println(x + " " + y);
                
               
            }
        }

        for(int i = 0; i < board.board.length; i++) {
            for(int j = i%2; j < board.board[i].length; j+=2) {
                System.out.print(board.board[i][j].getType() + " ");
            }
            System.out.println();
        }

     
    }
}
