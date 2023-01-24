package imageEnergiesAndSeams;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Energy {
    private double[][] energyArray;
    private final int MAX_ENERGY = 390150;
    BufferedImage energyImage;
    private int max;
    private int min;

    public Energy(int[][] pic)
    {
        this.energyArray = buildEnergyArray(pic);
    }

    public File createEnergyPic(String newFilePath) {
        this.energyImage = new BufferedImage(energyArray.length, energyArray[0].length, BufferedImage.TYPE_INT_RGB);
        for (int row = 0; row < energyArray.length; row++) {
            for (int col = 0; col < energyArray[row].length; col++) {
                double value = energyArray[row][col] == MAX_ENERGY ? max : energyArray[row][col];
                int valueb = (int)((value - min)*255.0) / (max - min);
                valueb = Math.min(valueb, 255);
                Color valuec =  new Color(valueb, valueb, valueb);
                int brightness = valuec.getRGB();
                energyImage.setRGB(row,col,brightness);
            }
        }
        File outputFile = new File(newFilePath);
        try {
            ImageIO.write(energyImage, "png", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputFile;
    }

    private double[][] buildEnergyArray(int[][] original) {
        this.max = 0; //these values are useful for creating an energy image
        this.min = MAX_ENERGY;
        int width = original[0].length;
        int height = original.length;
        energyArray = new double[height][width];

        for (int row = 0; row < energyArray.length; row++) {
            for (int col = 0; col < energyArray[row].length; col++) {
                int energyPixel;
                if (row == 0 || row >= (height-1) || col == 0 || col >= (width-1)) {
                    energyPixel = MAX_ENERGY;
                } else {
                    Color left = new Color(original[row][col - 1]);
                    Color right = new Color(original[row][col + 1]);
                    Color up = new Color(original[row - 1][col]);
                    Color down = new Color(original[row + 1][col]);

                    energyPixel = getEnergyPixelg(left, right, up, down);

                    if (energyPixel > max)
                    {
                        max = energyPixel;
                    }

                    if (energyPixel < min)
                    {
                        min = energyPixel;
                    }
                }
                energyArray[row][col] = energyPixel;
            }
        }
        return energyArray;
    }

    private int getEnergyPixel(Color left, Color right, Color up, Color down) {
        return (up.getRed() - down.getRed()) * (up.getRed() - down.getRed())
                + (up.getGreen() - down.getGreen()) * (up.getGreen() - down.getGreen())
                + (up.getBlue() - down.getBlue()) * (up.getBlue() - down.getBlue())
                + (left.getRed() - right.getRed()) * (left.getRed() - right.getRed())
                + (left.getGreen() - right.getGreen()) * (left.getGreen() - right.getGreen())
                + (left.getBlue() - right.getBlue()) * (left.getBlue() - right.getBlue());
    }

    public double[][] getEnergyArray() {
        return energyArray;
    }
}
