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

            setX(getX() - 1);
        }

        if (keys.getRight()) {

            setX(getX() + 1);
        }

        setYSpeed(getYSpeed() + 0.25);

        for (Object item : this.getCollisions()) {

            if (item instanceof Block) {

                if ((getY() + getHeight()) - item.getY() <= 1 + getYSpeed()) {

                    setYSpeed(0);
                    setY(item.getY() - getHeight() + 1);

                    if (keys.getUp()) {

                        setYSpeed(-5);
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