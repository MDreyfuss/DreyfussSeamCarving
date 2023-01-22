package imageData;

import java.awt.image.BufferedImage;

public class Image {
    private BufferedImage bufferedImage;
    private int[][] originalPic;
    private int[][] currentPic;
    private Energy energy;

    public Image(BufferedImage bufferedImage){
        this.bufferedImage = bufferedImage;
        buildColorArray(this.bufferedImage);
        this.energy = new Energy(originalPic);
    }

    private void buildColorArray(BufferedImage original) {
        originalPic = new int[original.getHeight()][original.getWidth()];
        currentPic = new int[original.getHeight()][original.getWidth()];
        for (int x = 0; x < original.getWidth(); x++) {
            for (int y = 0; y < original.getHeight(); y++) {
                originalPic[y][x] = original.getRGB(x, y);
                currentPic[y][x] = original.getRGB(x, y);
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

    public int[][] removeHorizontal(int[] seamToRemove) {
        int height = currentPic.length;
        int width = currentPic[0].length;
        int[][] newImage = new int[height - 1][width];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height-1; y++) {
                if (y < seamToRemove[x]){
                    newImage[y][x] = currentPic[y][x];
                }
                else{
                    newImage[y][x] = currentPic[y+1][x];
                }
            }
        }
        return newImage;
    }

    public int[][] removeVertical(int[] seamToRemove) {
        int height = currentPic.length;
        int width = currentPic[0].length;
        int[][] newImage = new int[height][width - 1];
        for (int x = 0; x < width-1; x++) {
            for (int y = 0; y < height; y++) {
                if (x < seamToRemove[y]){
                    newImage[y][x] = currentPic[y][x];
                }
                else{
                    newImage[y][x] = currentPic[y][x+1];
                }
            }
        }
        return newImage;
    }

    public int[][] getCurrentPic() {
        return currentPic;
    }
}
