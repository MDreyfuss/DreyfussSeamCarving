import imageEnergiesAndSeams.Image;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SeamCarving extends JFrame {

    private final JLabel imageLabel;
    private Image myImg;

    public SeamCarving() {

        setLayout(new BorderLayout());

        // This is where the image will be stored.
        imageLabel = new JLabel();

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout());
        JButton resizeButton = new JButton("Resize");
        resizeButton.addActionListener(event -> {
            // This will set the image based on the current size of the label
            setSeamImageSize(imageLabel.getWidth(), imageLabel.getHeight());
        });
        northPanel.add(resizeButton);

        JButton loadButton = new JButton("Load");
        northPanel.add(loadButton);
        loadButton.addActionListener(event -> {
            chooseFile();
        });

        add(northPanel, BorderLayout.NORTH);
        add(imageLabel, BorderLayout.CENTER);

        BufferedImage image;
        try {
            image = ImageIO.read(SeamCarving.class.getResourceAsStream("/broadwayTower.jpg"));
            loadSeamImage(image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        setTitle("Seam Carving");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void chooseFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION)
        {
            File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
            try {
                loadSeamImage(ImageIO.read(file));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void loadSeamImage(BufferedImage image) {

        //load the image into seam carver code
        myImg = new Image(image);
        imageLabel.setIcon(new ImageIcon(myImg.getCurrBufferedImage()));

        setSize(image.getWidth(null), image.getHeight(null));
        pack();
    }

    private void setSeamImageSize(int width, int height) {
        // generate a newImage with the new width and height
        Icon newImage =  new ImageIcon (myImg.resizeImage(height, width));

        imageLabel.setIcon(newImage);
    }

    public static void main(String[] args) {
        new SeamCarving().setVisible(true);
    }

}
