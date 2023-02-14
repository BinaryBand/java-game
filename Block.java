import java.awt.*;

class Block extends Object {

    Block(int x, int y, int width, int height, Camera cam) {
        super(x, y, width, height, cam);
    }

    @Override
    void update() {

        setX(getX() + (int) (getXSpeed()));
    }

    @Override
    void draw(int tempX, int tempY, Graphics g) {

        g.setColor(Color.darkGray);

        g.drawRect(tempX,
                tempY,
                getWidth(),
                getHeight());
    }

}