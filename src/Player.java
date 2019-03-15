import java.awt.*;

class Player extends Object {

    private double angle;
    private Controls keys;
    private Sprite sprite;

    static private int[] run = new int[] {10, 11, 12, 13, 14, 15, 16};
    static private int[] jump = new int[] {21, 22, 23, 24};
    static private int[] still = new int[] {0, 1, 2, 3};
    private double currentImg = 0;
    private boolean flipped = false;

    Player(int x, int y, int width, int height, Camera cam, Controls keys) {
        super(x, y, width, height, cam);

        this.angle = 0;
        this.keys = keys;
        this.sprite = new Sprite("src/res/lucas.png", 9, 40, 48);
    }

    @Override
    void update() {

        if (keys.getLeft()) { setXSpeed(-6); }
        if (keys.getRight()) { setXSpeed(6); }
        if (keys.getLeft() == keys.getRight()) { setXSpeed(0); }

        updateGravity();

        boolean onGround = false;
        for (Object item : getCollisions()) {

            if (item instanceof Block) {

                if (getY() + getHeight() - item.getY() <= 1 + getYSpeed()) {

                    if (!onGround) {

                        handleGroundedAnimation();
                        onGround = true;
                    }

                    if (20.0 <= getYSpeed()) {

                        createDust();
                    }

                    setYSpeed(0);
                    setY(item.getY() - getHeight() + 1);

                    if (keys.getUp()) {

                        setYSpeed(-10);
                    }

                    if (keys.getDown() && item.getHeight() < 25) {

                        setY(getY() + 2);
                    }
                }
            }
        }

        if (!onGround) {

            if (getYSpeed() <= 0) {

                currentImg = jump[0];
            }
            else if (getYSpeed() <= 20) {

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

        if (getY() >= 500) {

            setX(0);
            setY(-100);
        }

        angle = (angle + getXSpeed()) / 1.5;
        getCam().setAngle(-angle / 15);
    }

    @Override
    void draw(int tempX, int tempY, Graphics g) {

        sprite.draw(tempX, tempY, g, (int) currentImg, flipped);
    }

    private void updateGravity() {

        if (keys.getUp() && getYSpeed() < 0) {

            setYSpeed(getYSpeed() + 0.6);
        }
        else {

            setYSpeed(getYSpeed() + 1.2);
        }
    }

    private void handleGroundedAnimation() {

        if (keys.getLeft() != keys.getRight()) {

            currentImg = ((currentImg - run[0] + 0.4) % run.length) + run[0];
            if (currentImg < run[0]) { currentImg = run[0]; }
            if (keys.getLeft()) { flipped = true; }
            if (keys.getRight()) { flipped = false; }
        } else {

            currentImg = ((currentImg - still[0] + 0.25) % still.length) + still[0];
        }
    }

    private void createDust() {

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
    }

}