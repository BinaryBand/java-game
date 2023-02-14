import java.awt.*;

class Enemy extends Object {

    Enemy(int x, int y, int width, int height, Camera cam) {
        super(x, y, width, height, cam);

        setXSpeed(-1);
    }

    @Override
    void update() {

        setYSpeed(getYSpeed() + 1.2);

        for (Object item : getCollisions()) {

            if (item instanceof Block) {

                if (getY() + getHeight() - item.getY() <= 1 + getYSpeed()) {


                    if (20.0 <= getYSpeed()) {

                        createDust();
                    }

                    setYSpeed(0);
                    setY(item.getY() - getHeight() + 1);
                }
            }
        }

        setX((int) (getX() + getXSpeed()));
        setY((int) (getY() + getYSpeed()));

        if (getY() >= 500) {

            setX(375);
            setY(-200);
        }
    }

    @Override
    void draw(int tempX, int tempY, Graphics g) {

        g.setColor(Color.red);

        g.drawRect(tempX,
                tempY,
                getWidth(),
                getHeight());
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
