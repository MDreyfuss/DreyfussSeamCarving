import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Energy {
    private double[][] energyArray;
    BufferedImage energy;
    private int max;
    private int min;
    private String newFile;


    Energy(BufferedImage pic, String filePath)
    {
        this.energyArray = buildEnergyArray(pic);
        newFile = filePath.substring(0, filePath.lastIndexOf(".")) + "ENERGY.png";
        createEnergyPic(energyArray);
    }

    private File createEnergyPic(double[][] energyArray) {
        this.energy = new BufferedImage(energyArray.length, energyArray[0].length, BufferedImage.TYPE_INT_RGB);
        for (int row = 0; row < energyArray.length; row++) {
            for (int col = 0; col < energyArray[row].length; col++) {
                double value = energyArray[row][col] == 390150 ? max : energyArray[row][col];
                int valueb = (int)((value - min)*255.0) / (max - min); //max-min x work
                valueb = Math.min(valueb, 255);
                Color valuec =  new Color(valueb, valueb, valueb);
                int brightness = valuec.getRGB();
                energy.setRGB(row,col,brightness);
            }
        }
        File outputFile = new File(newFile);
        try {
            ImageIO.write(energy, "png", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputFile;
    }

    private double[][] buildEnergyArray(BufferedImage original) {
        this.max = 0;
        this.min = 390150;
        energyArray = new double[original.getWidth()][original.getHeight()];

        for (int row = 0; row < energyArray.length; row++) {
            for (int col = 0; col < energyArray[row].length; col++) {
                int energyPixel;
                if (row == 0 || row >= (original.getWidth()-1) || col == 0 || col >= (original.getHeight()-1)) {
                    energyPixel = 390150;
                } else {
                    Color left = new Color(original.getRGB(row, col - 1));
                    Color right = new Color(original.getRGB(row, col + 1));
                    Color up = new Color(original.getRGB(row - 1, col));
                    Color down = new Color(original.getRGB(row + 1, col));

                    energyPixel = (up.getRed() - down.getRed()) * (up.getRed() - down.getRed())
                            + (up.getGreen() - down.getGreen()) * (up.getGreen() - down.getGreen())
                            + (up.getBlue() - down.getBlue()) * (up.getBlue() - down.getBlue())
                            + (left.getRed() - right.getRed()) * (left.getRed() - right.getRed())
                            + (left.getGreen() - right.getGreen()) * (left.getGreen() - right.getGreen())
                            + (left.getBlue() - right.getBlue()) * (left.getBlue() - right.getBlue());

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

    public BufferedImage getEnergyPic() {
        return energy;
    }

    public double[][] getEnergyArray() {
        return energyArray;
    }
}
