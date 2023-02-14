import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class Sprite {

    private BufferedImage image;
    private int columns, width, height;

    Sprite(String imageLoc, int columns, int width, int height) {

        try {

            image = ImageIO.read(new File(imageLoc));
        } catch (IOException e) {

            e.printStackTrace();
        }

        this.columns = columns;
        this.width = width;
        this.height = height;
    }

    void draw(int tempX, int tempY, Graphics g, int frame, boolean flipped) {

        int frameX = (frame % columns) * width;
        int frameY = (frame / columns) * height;

        if (!flipped) {

            g.drawImage(image, tempX, tempY, tempX + width, tempY + height,
                    frameX, frameY, frameX + width, frameY + height, null);
        }
        else {

            g.drawImage(image, tempX, tempY, tempX + width, tempY + height,
                    frameX + width, frameY, frameX, frameY + height, null);
        }
    }
}