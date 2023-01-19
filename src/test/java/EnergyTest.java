import ImageData.Energy;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class EnergyTest {

    @Test
    void testBuildEnergyArray() {
        BufferedImage testImage = new BufferedImage(4,4, BufferedImage.TYPE_INT_RGB);
        testImage.setRGB(0,0, new Color(30, 30, 30).getRGB());
        testImage.setRGB(0,1, new Color(193, 78, 78).getRGB());
        testImage.setRGB(0,2, new Color(76, 117, 70).getRGB());
        testImage.setRGB(0,3, new Color(22, 23, 89).getRGB());
        testImage.setRGB(1,0, new Color(183, 161, 239).getRGB());
        testImage.setRGB(1,1, new Color(255, 138, 4).getRGB());
        testImage.setRGB(1,2, new Color(211, 184, 47).getRGB());
        testImage.setRGB(1,3, new Color(10, 187, 160).getRGB());
        testImage.setRGB(2,0, new Color(84, 176, 250).getRGB());
        testImage.setRGB(2,1, new Color(196, 6, 50).getRGB());
        testImage.setRGB(2,2, new Color(115, 30, 30).getRGB());
        testImage.setRGB(2,3, new Color(124, 82, 133).getRGB());
        testImage.setRGB(3,0, new Color(206, 75, 255).getRGB());
        testImage.setRGB(3,1, new Color(178, 201, 138).getRGB());
        testImage.setRGB(3,2, new Color(68, 31, 231).getRGB());
        testImage.setRGB(3,3, new Color(29, 98, 227).getRGB());

        double[][] expectedArray = {
                {390150.0,390150.0,390150.0,390150.0},
                {390150.0,44154.0,97452.0,390150.0},
                {390150.0,98531.0,95563.0,390150.0},
                {390150.0,390150.0,390150.0,390150.0}
        };

        Energy testEnergy = new Energy(testImage);

        assertArrayEquals(expectedArray, testEnergy.getEnergyArray());
    }
}