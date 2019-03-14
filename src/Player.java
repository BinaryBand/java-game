import java.awt.*;

class Player extends Object {

    private Controls keys;

    Player(int x, int y, int width, int height, Camera cam, Controls keys) {
        super(x, y, width, height, cam);

        this.keys = keys;
    }

    @Override
    void update() {

        if (keys.getLeft()) {

            setXSpeed(getXSpeed() - 0.25);
        }

        if (keys.getRight()) {

            setXSpeed(getXSpeed() + 0.25);
        }

        getCam().setAngle(getXSpeed());

        setYSpeed(getYSpeed() + 1);

        for (Object item : this.getCollisions()) {

            if (item instanceof Block) {

                if (getY() + getHeight() - item.getY() <= 1 + getYSpeed()) {

                    setYSpeed(0);
                    setY(item.getY() - getHeight() + 1);

                    if (keys.getUp()) {

                        setYSpeed(-10);
                    }
                }
            }
        }

        setX((int) (getX() + getXSpeed()));
        setY((int) (getY() + getYSpeed()));
    }

    @Override
    void draw(int tempX, int tempY, Graphics g) {

        g.setColor(Color.blue);

        g.fillRect(tempX,
                tempY,
                getWidth(),
                getHeight());
    }

}