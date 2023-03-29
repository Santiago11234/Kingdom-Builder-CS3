import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.awt.Color;
import java.awt.color.*;
import javax.imageio.ImageIO;

public class ImageProcessing {
    public static void main(String[] args) throws IOException {
    
        final int[] mountainRGB = {121, 89, 71};
        final int[] desertRGB = {238, 232, 170};
        final int[] forestRGB = {0, 100, 0};
        final int[] grasslandRGB = {85, 107, 47};
        final double colorThreshold = 100.0;

        BufferedImage board = ImageIO.read(ImageProcessing.class.getResource("/Images/Copy of Board1.png"));
        int width = board.getWidth();
        int height = board.getHeight();


        

        for (int i = 0; i < width; i++) {
            int startY = i % 2 == 0 ? 0 : 55; 
            for (int j = startY; j < height; j += 110) {
                int rgb = board.getRGB(i, j);
                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = rgb & 0xFF;
                
                double distToMountain = Math.sqrt(Math.pow(red - mountainRGB[0], 2) + Math.pow(green - mountainRGB[1], 2) + Math.pow(blue - mountainRGB[2], 2));
                double distToDesert = Math.sqrt(Math.pow(red - desertRGB[0], 2) + Math.pow(green - desertRGB[1], 2) + Math.pow(blue - desertRGB[2], 2));
                double distToForest = Math.sqrt(Math.pow(red - forestRGB[0], 2) + Math.pow(green - forestRGB[1], 2) + Math.pow(blue - forestRGB[2], 2));
                double distToGrassland = Math.sqrt(Math.pow(red - grasslandRGB[0], 2) + Math.pow(green - grasslandRGB[1], 2) + Math.pow(blue - grasslandRGB[2], 2));
                
                if (distToMountain < colorThreshold) {
                } else if (distToDesert < colorThreshold) {
                } else if (distToForest < colorThreshold) {
                } else if (distToGrassland < colorThreshold) {
                }
            }
        }

     
    }
}
