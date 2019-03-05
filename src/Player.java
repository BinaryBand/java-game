import java.awt.*;

class Player extends Object {

    private Controls keys;

    Player(float x, float y, int width, int height, Controls keys) {
        super(x, y, width, height);

        this.setX(x);
        this.setY(y);

        this.setWidth(width);
        this.setHeight(height);

        this.setXSpeed(0);
        this.setYSpeed(0);

        this.keys = keys;
    }

    @Override
    void update() {

        for (Object item : this.getCollisions()) {

            if (item instanceof Particle) {

                this.setWidth(this.getWidth() + 1);
                this.setHeight(this.getHeight() + 1);
            }
        }

        if (this.keys.getRight()) {
            this.setXSpeed(this.getXSpeed() + 0.1);
        }
        if (this.keys.getLeft()) {
            this.setXSpeed(this.getXSpeed() - 0.1);
        }
        if (this.keys.getUp()) {
            this.setYSpeed(this.getYSpeed() - 0.1);
        }
        if (this.keys.getDown()) {
            this.setYSpeed(this.getYSpeed() + 0.1);
        }

        this.setX(this.getX() + this.getXSpeed());
        this.setY(this.getY() + this.getYSpeed());
    }

    @Override
    void draw(Graphics g) {

        g.setColor(Color.blue);

        g.fillOval((int)this.getX(),
                (int)this.getY(),
                this.getWidth(),
                this.getHeight());
    }

}