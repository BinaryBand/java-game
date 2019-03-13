import java.awt.*;

class Block extends Object {

    Block(double x, double y, int width, int height, Camera cam) {
        super(x, y, width, height, cam);
    }

    @Override
    void update() {

    }

    @Override
    void draw(Graphics g) {

        int tempX = (int) (getX() - getCam().getX());
        int tempY = (int) (getY() - getCam().getY());

        g.setColor(Color.black);
        g.fillRect(tempX,
                tempY,
                getWidth(),
                getHeight());
    }

}
