import java.awt.*;

class Dust extends Object {

    private boolean alternate;

    Dust(int x, int y, int size, Camera cam, int xSpeed) {
        super(x, y, size, size, cam);

        setX(getX() - (getWidth() / 2));
        setXSpeed(xSpeed);

        alternate = false;
    }

    @Override
    void update() {

        if (alternate) {

            setX((int) (getX() + getXSpeed() + 1));

            setWidth(getWidth() - 2);
            setHeight(getHeight() - 2);

            if (getWidth() < 1) {

                destroy();
            }
        }


        alternate = !alternate;
    }

    @Override
    void draw(int tempX, int tempY, Graphics g) {

        g.setColor(Color.white);

        g.fillRect(tempX,
                tempY,
                getWidth(),
                getHeight());

        g.setColor(Color.black);

        g.drawRect(tempX,
                tempY,
                getWidth(),
                getHeight());
    }
}
