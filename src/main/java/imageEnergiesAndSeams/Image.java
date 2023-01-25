package imageEnergiesAndSeams;

import java.awt.image.BufferedImage;

public class Image {
    private BufferedImage bufferedImage;
    private int[][] originalPic;
    private int[][] currentPic;
    private BufferedImage currBufferedImage;
    private Energy energy;

    public Image(BufferedImage bufferedImage){
        this.bufferedImage = bufferedImage;
        this.currBufferedImage = bufferedImage;
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
        BufferedImage newBufferedImage = new BufferedImage(width,height - 1, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height-1; y++) {
                if (y < seamToRemove[x]){
                    newBufferedImage.setRGB(x, y, currBufferedImage.getRGB(x, y));
                    newImage[y][x] = currentPic[y][x];
                }
                else{
                    newImage[y][x] = currentPic[y+1][x];
                    newBufferedImage.setRGB(x, y, currBufferedImage.getRGB(x, y + 1));
                }
            }
        }
        currBufferedImage = newBufferedImage;
        return newImage;
    }

    public int[][] removeVertical(int[] seamToRemove) {
        int height = currentPic.length;
        int width = currentPic[0].length;
        int[][] newImage = new int[height][width - 1];
        BufferedImage newBufferedImage = new BufferedImage(width - 1,height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width-1; x++) {
            for (int y = 0; y < height; y++) {
                if (x < seamToRemove[y]){
                    newImage[y][x] = currentPic[y][x];
                    newBufferedImage.setRGB(x, y, currBufferedImage.getRGB(x, y));
                }
                else{
                    newImage[y][x] = currentPic[y][x+1];
                    newBufferedImage.setRGB(x, y, currBufferedImage.getRGB(x + 1, y));
                }
            }
        }
        currBufferedImage = newBufferedImage;
        return newImage;
    }

    public BufferedImage resizeImage(int newHeight, int newWidth)
    {
        horizontalSeamCarving(currentPic.length - newHeight);
        verticalSeamCarving(currentPic[0].length - newWidth);
        return currBufferedImage;
    }

    public int[][] getCurrentPic() {
        return currentPic;
    }

    public BufferedImage getCurrBufferedImage() {
        return currBufferedImage;
    }
}
