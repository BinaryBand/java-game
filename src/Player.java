import java.awt.*;

class Player extends Object {

    private Controls keys;

    Player(float x, float y, int width, int height, Camera cam, Controls keys) {
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

                setYSpeed(0);
                setY(item.getY() - getHeight() + 1);
                setYSpeed(0);

                if (keys.getUp()) {

                    setYSpeed(-5);
                }
            }
        }

        setX(getX() + getXSpeed());
        setY(getY() + getYSpeed());
    }

    @Override
    void draw(Graphics g) {

        g.setColor(Color.blue);

        int tempX = (int) (getX() - getCam().getX());
        int tempY = (int) (getY() - getCam().getY());

        g.fillRect(tempX,
                tempY,
                getWidth(),
                getHeight());
    }

}