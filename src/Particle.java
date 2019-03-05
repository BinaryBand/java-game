import java.awt.*;
import java.util.Random;

class Particle extends Object {

    Particle(double x, double y, int width, int height, Random rand) {
        super(x, y, width, height);

        this.setX(x);
        this.setY(y);

        this.setWidth(width);
        this.setHeight(height);

        this.setXSpeed((rand.nextDouble() * 6) - 3);
    }

    @Override
    void update() {

        for (Object item : this.getCollisions()) {

            if (item instanceof Particle) {

                double deltaX = (this.getX() + (this.getWidth() / 2)) - (item.getX() + (item.getWidth() / 2));
                double deltaY = (this.getY() + (this.getHeight() / 2)) - (item.getY() + (item.getHeight() / 2));
                double angle = Math.atan2(deltaY, deltaX);

                double speed = Math.sqrt(Math.pow(getXSpeed(), 2) + Math.pow(getYSpeed(), 2));

                this.setXSpeed( (speed * Math.cos(angle)) - (item.getXSpeed() / 2));
                this.setYSpeed( (speed * Math.sin(angle)) - (item.getYSpeed() / 2));
            }
            else if (item instanceof Player) {

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

        g.fillOval((int)this.getX(),
                (int)this.getY(),
                this.getWidth(),
                this.getHeight());
    }

}
