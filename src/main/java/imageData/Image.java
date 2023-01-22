package imageData;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Image {
    String filePath;
    BufferedImage bufferedImage;
    int[][] originalPic;
    int[][] currentPic;
    Energy energy;

    public Image(String filePath){
        this.filePath = filePath;
        try {
            this.bufferedImage = ImageIO.read(new File(filePath));
        } catch (IOException e) {
            System.out.println("Error reading file");
            e.printStackTrace();
        }
        buildColorArray(this.bufferedImage);
        this.energy = new Energy(originalPic);
    }

    private void buildColorArray(BufferedImage original) {
        originalPic = new int[original.getHeight()][original.getWidth()];
        currentPic = new int[original.getHeight()][original.getWidth()];
        for (int row = 0; row < original.getHeight(); row++) {
            for (int col = 0; col < original.getWidth(); col++) {
                originalPic[row][col] = original.getRGB(row, col);
                currentPic[row][col] = original.getRGB(row, col);
            }
        }
    }

    public int[][] horizontalSeamCarving(int numToRemove){
        for (int i = 0; i < numToRemove; i++) {
            Energy energy = new Energy(currentPic);
            Seams seams = new Seams(energy);
            int[] toremove = seams.calcRemoveSeam(seams.getHorizontalEnergyArray());
            currentPic = removeHorizontal(toremove);
        }
        return currentPic;
    }

    public int[][] verticalSeamCarving(int numToRemove){
        for (int i = 0; i < numToRemove; i++) {
            Energy energy = new Energy(currentPic);
            Seams seams = new Seams(energy);
            int[] toremove = seams.calcRemoveSeam(seams.getVerticalEnergyArray());
            currentPic = removeVertical(toremove);
        }
        return currentPic;
    }

    private int[][] removeVertical(int[] seamToRemove) {
        int width = currentPic[0].length;
        int height = currentPic.length;
        int[][] newImage = new int[width][height];
        for (int i = 0; i < width-1; i++) {
            for (int j = 0; j < height; j++) {
                if (i < seamToRemove[j]){
                    newImage[i][j] = currentPic[i][j];
                }
                else{
                    newImage[i][j] = currentPic[i+1][j];
                }
            }
        }
        return newImage;
    }

    private int[][] removeHorizontal(int[] seamToRemove) {
        int width = currentPic[0].length;
        int height = currentPic.length;
        int[][] newImage = new int[width][height-1];
        for (int i = 0; i < height-1; i++) {
            for (int j = 0; j < width; j++) {
                if (i < seamToRemove[j]){
                    newImage[j][i] = currentPic[j][i];
                }
                else{
                    newImage[j][i] = currentPic[j][i+1];
                }
            }
        }
        return newImage;
    }

}
