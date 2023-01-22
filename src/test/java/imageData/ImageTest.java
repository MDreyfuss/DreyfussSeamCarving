package imageData;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class ImageTest {
    BufferedImage testImage;

    @BeforeEach
    void setup(){
        testImage = new BufferedImage(3,4, BufferedImage.TYPE_INT_RGB);
        testImage.setRGB(0,0, new Color(1).getRGB());
        testImage.setRGB(1,0, new Color(2).getRGB());
        testImage.setRGB(2,0, new Color(3).getRGB());
        testImage.setRGB(0,1, new Color(4).getRGB());
        testImage.setRGB(1,1, new Color(5).getRGB());
        testImage.setRGB(2,1, new Color(6).getRGB());
        testImage.setRGB(0,2, new Color(7).getRGB());
        testImage.setRGB(1,2, new Color(8).getRGB());
        testImage.setRGB(2,2, new Color(9).getRGB());
        testImage.setRGB(0,3, new Color(10).getRGB());
        testImage.setRGB(1,3, new Color(11).getRGB());
        testImage.setRGB(2,3, new Color(12).getRGB());
    }

    @Test
    void getCurrentPic(){
        Image image = new Image(testImage);
        int[][] expectedPic = {
                {new Color(1).getRGB(), new Color(2).getRGB(), new Color(3).getRGB()},
                {new Color(4).getRGB(), new Color(5).getRGB(), new Color(6).getRGB()},
                {new Color(7).getRGB(), new Color(8).getRGB(), new Color(9).getRGB()},
                {new Color(10).getRGB(), new Color(11).getRGB(), new Color(12).getRGB()}
        };
        assertArrayEquals(expectedPic, image.getCurrentPic());
    }

    @Test
    void removeVertical() {
        Image image = new Image(testImage);

        int[] testRemove = {0,1,2,1};
        int[][] expected = {
                {new Color(2).getRGB(), new Color(3).getRGB()},
                {new Color(4).getRGB(), new Color(6).getRGB()},
                {new Color(7).getRGB(), new Color(8).getRGB()},
                {new Color(10).getRGB(), new Color(12).getRGB()}};

        assertArrayEquals(expected, image.removeVertical(testRemove));
    }

    @Test
    void removeHorizontal() {
        Image image = new Image(testImage);

        int[] testRemove = {0,1,2};

        int [][] expected = {
                {new Color(4).getRGB(), new Color(2).getRGB(), new Color(3).getRGB()},
                {new Color(7).getRGB(), new Color(8).getRGB(), new Color(6).getRGB()},
                {new Color(10).getRGB(), new Color(11).getRGB(), new Color(12).getRGB()},
        };

        assertArrayEquals(expected, image.removeHorizontal(testRemove));
    }
}