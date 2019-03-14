import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class Player extends Object {

    private double angle;
    private Controls keys;
    private PlayerSprites sprite;

    static private int[] run = new int[] {10, 11, 12, 13, 14, 15, 16};
    static private int[] jump = new int[] {21, 22, 23, 24};
    static private int[] still = new int[] {0, 1, 2, 3};
    private double currentImg = 0;
    private boolean left = false;

    Player(int x, int y, int width, int height, Camera cam, Controls keys) {
        super(x, y, width, height, cam);

        this.angle = 0;
        this.keys = keys;
        this.sprite = new PlayerSprites();
    }

    @Override
    void update() {

        if (keys.getLeft()) { setXSpeed(-5); }
        if (keys.getRight()) { setXSpeed(5); }
        if (keys.getLeft() == keys.getRight()) { setXSpeed(0); }

        if (keys.getUp() && getYSpeed() < 0) {

            setYSpeed(getYSpeed() + 0.75);
        }
        else {

            setYSpeed(getYSpeed() + 1);
        }

        boolean onGround = false;
        for (Object item : getCollisions()) {

            if (item instanceof Block) {

                if (getY() + getHeight() - item.getY() <= 1 + getYSpeed()) {

                    if (!onGround) {

                        if (keys.getLeft() != keys.getRight()) {

                            currentImg = ((currentImg - run[0] + 0.4) % run.length) + run[0];
                            if (currentImg < run[0]) { currentImg = run[0]; }
                            if (keys.getLeft()) { left = true; }
                            if (keys.getRight()) { left = false; }
                        } else {

                            currentImg = ((currentImg - still[0] + 0.25) % still.length) + still[0];
                        }
                    }

                    onGround = true;

                    if (15.0 <= getYSpeed()) {

                        createObject(new Dust(getX() + (getWidth() / 2),
                                getY() + getHeight(),
                                (int) getYSpeed(),
                                getCam(),
                                -1));

                        createObject(new Dust(getX() + (getWidth() / 2),
                                getY() + getHeight(),
                                (int) getYSpeed(),
                                getCam(),
                                0));

                        createObject(new Dust(getX() + (getWidth() / 2),
                                getY() + getHeight(),
                                (int) getYSpeed(),
                                getCam(),
                                1));
                    }

                    setYSpeed(0);
                    setY(item.getY() - getHeight() + 1);

                    if (keys.getUp()) {

                        setYSpeed(-10);
                    }
                }
            }
        }

        if (!onGround) {

            if (getYSpeed() <= 0) {

                currentImg = jump[0];
            }
            else if (getYSpeed() <= 15) {

                currentImg = jump[1];
            }
            else {

                if (currentImg != jump[1]) {

                    currentImg = jump[1];
                } else {

                    currentImg = jump[2];
                }
            }
        }

        setX((int) (getX() + getXSpeed()));
        setY((int) (getY() + getYSpeed()));

        angle = (angle + getXSpeed()) / 1.2;
        getCam().setAngle(-angle / 10);
    }

    @Override
    void draw(int tempX, int tempY, Graphics g) {

        sprite.draw(tempX, tempY, g, (int) currentImg, left);
    }

    class PlayerSprites {

        private BufferedImage image;

        PlayerSprites() {

            try {
                image = ImageIO.read(new File("src\\res\\lucas.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        void draw(int tempX, int tempY, Graphics g, int frame, boolean left) {

            int columns = 9;

            int width = 360 / 9;
            int height = 144 / 3;

            int frameX = (frame % columns) * width;
            int frameY = (frame / columns) * height;

            if (!left) {
                g.drawImage(image, tempX, tempY, tempX + width, tempY + height,
                        frameX, frameY, frameX + width, frameY + height, null);
            }
            else {
                g.drawImage(image, tempX, tempY, tempX + width, tempY + height,
                        frameX + width, frameY, frameX, frameY + height, null);
            }
        }
    }

}