package displays;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Assets {
    public static BufferedImage[] redTrain;
    public static BufferedImage[] yellowTrain;
    public static BufferedImage[] greenTrain;
    public static BufferedImage[] blueTrain;
    public static BufferedImage[] purpleTrain;
    public static BufferedImage[] whiteTrain;
    public static BufferedImage[] blackGreenTrain;
    public static BufferedImage[] blackTrain;
    public static BufferedImage upArrow;
    public static BufferedImage downArrow;
    public static BufferedImage rightArrow;
    public static BufferedImage leftArrow;

    public static void init() {
        BufferedImage sheet = load("/images/TrainsModels.png");


        redTrain = new BufferedImage[]{sheet.getSubimage(36, 10, 40, 95), sheet.getSubimage(12, 136, 90, 60)
                , sheet.getSubimage(12, 232, 90, 60), sheet.getSubimage(35, 300, 40, 90)};
        yellowTrain = new BufferedImage[]{sheet.getSubimage(131, 9, 40, 95), sheet.getSubimage(107, 139, 90, 60)
                , sheet.getSubimage(100, 233, 90, 60), sheet.getSubimage(131, 298, 40, 90)};
        greenTrain = new BufferedImage[]{sheet.getSubimage(225, 9, 40, 95), sheet.getSubimage(207, 138, 90, 60),
                sheet.getSubimage(197, 232, 90, 60), sheet.getSubimage(225, 300, 40, 90)};
        blueTrain = new BufferedImage[]{sheet.getSubimage(323, 10, 40, 90), sheet.getSubimage(300, 135, 90, 60),
                sheet.getSubimage(293, 232, 90, 60), sheet.getSubimage(320, 300, 40, 90)};
        purpleTrain = new BufferedImage[]{sheet.getSubimage(420, 10, 40, 90), sheet.getSubimage(400, 138, 90, 60)
                , sheet.getSubimage(390, 230, 90, 60), sheet.getSubimage(420, 300, 40, 90)};
        whiteTrain = new BufferedImage[]{sheet.getSubimage(515, 10, 40, 90), sheet.getSubimage(493, 137, 90, 60)
                , sheet.getSubimage(485, 233, 90, 60), sheet.getSubimage(515, 300, 40, 90)};
        blackGreenTrain = new BufferedImage[]{sheet.getSubimage(610, 10, 40, 90), sheet.getSubimage(590, 137, 90, 60)
                , sheet.getSubimage(580, 233, 90, 60), sheet.getSubimage(610, 297, 40, 90)};
        blackTrain = new BufferedImage[]{sheet.getSubimage(707, 10, 40, 90), sheet.getSubimage(684, 139, 90, 60)
                , sheet.getSubimage(675, 232, 90, 60), sheet.getSubimage(707, 297, 40, 90)};

        upArrow = load("/images/upArrow.png");
        downArrow = load("/images/downArrow.png");
        rightArrow = load("/images/rightArrow.png");
        leftArrow = load("/images/leftArrow.png");

    }

    public static BufferedImage load(String path) {
        try {
            return ImageIO.read(Assets.class.getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
