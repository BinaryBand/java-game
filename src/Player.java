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

                this.setX(this.getX() - 1);
                this.setY(this.getY() - 1);


                this.setWidth(this.getWidth() + 2);
                this.setHeight(this.getHeight() + 2);
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

        if (this.getX() + this.getWidth() <= 0) {
            this.setX(720);
        } else if (720 <= this.getX()) {
            this.setX(0 - this.getWidth());
        }

        if (this.getY() + this.getHeight() <= 0) {
            this.setY(480);
        } else if (480 <= this.getY()) {
            this.setY(0 - this.getHeight());
        }
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