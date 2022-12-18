import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Image {
    String filePath;
    BufferedImage original;
    BufferedImage current;

    Image(String filePath){
        this.filePath = filePath;
        try {
            this.original = ImageIO.read(new File(filePath));
            Energy energy = new Energy(original);
            Seams seams = new Seams(energy, original);
        } catch (IOException e) {
            System.out.println("Error reading file");
            e.printStackTrace();
        }
    }
}
