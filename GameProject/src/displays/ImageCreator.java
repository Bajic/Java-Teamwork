package displays;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageCreator {
    public static BufferedImage trainLeft;
    public static BufferedImage trainRight;
    public static BufferedImage trainUp;
    public static BufferedImage trainDown;
    public static BufferedImage upArrow;
    public static BufferedImage downArrow;
    public static BufferedImage rightArrow;
    public static BufferedImage leftArrow;

    public static void init() {
        BufferedImage sheet = load("/images/TrainModel.png");

        trainLeft = sheet.getSubimage(197, 127, 90, 60);  //TODO: remove magic numbers (make const)
        trainDown = sheet.getSubimage(222, 0, 40, 95);
        trainRight = sheet.getSubimage(0, 225, 90, 60);
        trainUp = sheet.getSubimage(28, 287, 40, 95);

        upArrow = load("/images/upArrow.png");
        downArrow = load("/images/downArrow.png");
        rightArrow = load("/images/rightArrow.png");
        leftArrow = load("/images/leftArrow.png");

    }

    public static BufferedImage load(String path) {
        try {
            return ImageIO.read(ImageCreator.class.getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
