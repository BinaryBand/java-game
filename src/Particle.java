import java.awt.*;
import java.util.Random;

class Particle extends Object {

    Particle(float x, float y, int width, int height, Random rand) {
        super(x, y, width, height);

        this.setX(x);
        this.setY(y);

        this.setWidth(width);
        this.setHeight(height);

        this.setXSpeed(rand.nextInt() % 2);
        this.setYSpeed(rand.nextInt() % 2);
    }

    @Override
    void update() {

        for (Object item : this.getCollisions()) {

            if (item instanceof Player) {

                this.kill();
            }
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

        g.setColor(Color.red);

        g.fillOval(Math.round(this.getX()),
                Math.round(this.getY()),
                this.getWidth(),
                this.getHeight());
    }

}
