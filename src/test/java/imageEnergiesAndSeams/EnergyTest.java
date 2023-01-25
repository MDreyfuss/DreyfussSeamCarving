package imageEnergiesAndSeams;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class EnergyTest {

    @Test
    void buildEnergyArray() {
        //given
        int [][] testImage = new int[3][4];
        testImage[0][0] = new Color(30, 30, 30).getRGB();
        testImage[0][1] = new Color(193, 78, 78).getRGB();
        testImage[0][2] = new Color(76, 117, 70).getRGB();
        testImage[0][3] = new Color(22, 23, 89).getRGB();
        testImage[1][0] = new Color(183, 161, 239).getRGB();
        testImage[1][1] = new Color(255, 138, 4).getRGB();
        testImage[1][2] = new Color(211, 184, 47).getRGB();
        testImage[1][3] = new Color(10, 187, 160).getRGB();
        testImage[2][0] = new Color(84, 176, 250).getRGB();
        testImage[2][1] = new Color(196, 6, 50).getRGB();
        testImage[2][2] = new Color(115, 30, 30).getRGB();
        testImage[2][3] = new Color(124, 82, 133).getRGB();

        //when
        Energy testEnergy = new Energy(testImage);

        double[][] expectedArray = {
                {390150.0,390150.0,390150.0,390150.0},
                {390150.0,44154.0,97452.0,390150.0},
                {390150.0,390150.0,390150.0,390150.0},
        };

        //then
        assertArrayEquals(expectedArray, testEnergy.getEnergyArray());
    }
}