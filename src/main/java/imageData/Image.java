package imageData;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Image {
    String filePath;
    BufferedImage original;
    BufferedImage current;
    Energy energy;

    public Image(String filePath){
        this.filePath = filePath;
        try {
            this.original = ImageIO.read(new File(filePath));
            this.current = original;
        } catch (IOException e) {
            System.out.println("Error reading file");
            e.printStackTrace();
        }
        this.energy = new Energy(original);
    }
/*
    public File horizontalSeamCarving(int numToRemove){
        for (int i = 0; i < numToRemove; i++) {
            EnergyAndSeams.Energy energy = new EnergyAndSeams.Energy(current, filePath);
            EnergyAndSeams.Seams seams = new EnergyAndSeams.Seams(energy, current);
            int[] toremove = seams.calcRemoveSeam(seams.horizontalSeamArray);
            current = removeHorizontal(toremove);
        }
        String newFile = filePath.substring(0, filePath.lastIndexOf(".")) + "NEW.png";
        File outputFile = new File(newFile);
        try {
            ImageIO.write(current, "png", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputFile;
    }

    public File verticalSeamCarving(int numToRemove){
        for (int i = 0; i < numToRemove; i++) {
            EnergyAndSeams.Energy energy = new EnergyAndSeams.Energy(current, filePath);
            EnergyAndSeams.Seams seams = new EnergyAndSeams.Seams(energy, current);
            int[] toremove = seams.calcRemoveSeam(seams.verticalSeamArray);
            current = removeVertical(toremove);
        }
        String newFile = filePath.substring(0, filePath.lastIndexOf(".")) + "NEW.png";
        File outputFile = new File(newFile);
        try {
            ImageIO.write(current, "png", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputFile;
    }

    private BufferedImage removeVertical(int[] toremove) {
        BufferedImage newImage = new BufferedImage(current.getWidth()-1, current.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < current.getWidth()-1; i++) {
            for (int j = 0; j < current.getHeight(); j++) {
                if (i < toremove[j]){
                    newImage.setRGB(i,j, current.getRGB(i,j));
                }
                else{
                    newImage.setRGB(i,j,current.getRGB(i+1,j));
                }
            }
        }
        return newImage;
    }

    private BufferedImage removeHorizontal(int[] toremove) {
        BufferedImage newImage = new BufferedImage(current.getWidth(), current.getHeight()-1, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < current.getHeight()-1; i++) {
            for (int j = 0; j < current.getWidth(); j++) {
                if (i < toremove[j]){
                    newImage.setRGB(j,i, current.getRGB(j,i));
                }
                else{
                    newImage.setRGB(j,i,current.getRGB(j,i+1));
                }
            }
        }
        return newImage;
    }
    */
}
