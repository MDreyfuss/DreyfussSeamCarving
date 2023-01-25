package imageEnergiesAndSeams;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class ImageTest {
    public static final int color1 = new Color(1).getRGB();
    public static final int color2 = new Color(2).getRGB();
    public static final int color3 = new Color(3).getRGB();
    public static final int color4 = new Color(4).getRGB();
    public static final int color5 = new Color(5).getRGB();
    public static final int color6 = new Color(6).getRGB();
    public static final int color7 = new Color(7).getRGB();
    public static final int color8 = new Color(8).getRGB();
    public static final int color9 = new Color(9).getRGB();
    public static final int color10 = new Color(10).getRGB();
    public static final int color11 = new Color(11).getRGB();
    public static final int color12 = new Color(12).getRGB();
    BufferedImage testImage;

    @BeforeEach
    void setup(){
        testImage = new BufferedImage(3,4, BufferedImage.TYPE_INT_RGB);
        testImage.setRGB(0,0, color1);
        testImage.setRGB(1,0, color2);
        testImage.setRGB(2,0, color3);
        testImage.setRGB(0,1, color4);
        testImage.setRGB(1,1, color5);
        testImage.setRGB(2,1, color6);
        testImage.setRGB(0,2, color7);
        testImage.setRGB(1,2, color8);
        testImage.setRGB(2,2, color9);
        testImage.setRGB(0,3, color10);
        testImage.setRGB(1,3, color11);
        testImage.setRGB(2,3, color12);
    }

    @Test
    void getCurrentPic(){
        Image image = new Image(testImage);
        int[][] expectedPic = {
                {color1, color2, color3},
                {color4, color5, color6},
                {color7, color8, color9},
                {color10, color11, color12}
        };
        assertArrayEquals(expectedPic, image.getCurrentPic());
    }

    @Test
    void removeVertical() {
        Image image = new Image(testImage);

        int[] testRemove = {0,1,2,1};
        int[][] expected = {
                {color2, color3},
                {color4, color6},
                {color7, color8},
                {color10, color12}};

        assertArrayEquals(expected, image.removeVertical(testRemove));
    }

    @Test
    void removeHorizontal() {
        Image image = new Image(testImage);

        int[] testRemove = {0,1,2};

        int [][] expected = {
                {color4, color2, color3},
                {color7, color8, color6},
                {color10, color11, color12},
        };

        assertArrayEquals(expected, image.removeHorizontal(testRemove));
    }
}