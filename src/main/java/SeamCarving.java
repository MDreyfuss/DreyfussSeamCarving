import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static java.lang.Math.max;

public class SeamCarving {

    public static void main(String[] args) {
        try {

            BufferedImage original = ImageIO.read(new File("C:\\Users\\mirdr\\IdeaProjects\\DreyfussSeamCarving\\src\\main\\resources\\Broadway_tower_edit.jpg"));

            Energy energy = new Energy(original);

        } catch (IOException e) {
            System.out.println("Error reading file");
            e.printStackTrace();
        }
    }

}
