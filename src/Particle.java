import java.awt.*;
import java.util.Random;

class Particle extends Object {

    Particle(double x, double y, int width, int height, Camera cam) {
        super(x, y, width, height, cam);

        this.setX(x);
        this.setY(y);

        this.setWidth(width);
        this.setHeight(height);

        Random rand = new Random();
        this.setXSpeed((rand.nextDouble() * 6) - 3);
        this.setYSpeed((rand.nextDouble() * 6) - 3);
    }

    @Override
    void update() {

        for (Object item : this.getCollisions()) {

            if (item instanceof Particle) {

                double deltaX = (this.getX() + (this.getWidth() / 2.0)) - (item.getX() + (item.getWidth() / 2.0));
                double deltaY = (this.getY() + (this.getHeight() / 2.0)) - (item.getY() + (item.getHeight() / 2.0));
                double angle = Math.atan2(deltaY, deltaX);

                double speed = Math.sqrt(Math.pow(getXSpeed(), 2) + Math.pow(getYSpeed(), 2));

                this.setXSpeed(speed * Math.cos(angle));
                this.setYSpeed(speed * Math.sin(angle));
            }
            else if (item instanceof Player) {

                this.kill();
            }
        }

        this.setX(this.getX() + this.getXSpeed());
        this.setY(this.getY() + this.getYSpeed());
    }

    @Override
    void draw(Graphics g) {

        int tempX = (int) (this.getX() - this.getCam().getX());
        int tempY = (int) (this.getY() - this.getCam().getY());

        g.setColor(Color.red);

        g.fillOval(tempX,
                tempY,
                this.getWidth(),
                this.getHeight());

        Graphics2D g2d = (Graphics2D)g.create();
        RenderingHints hints = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON
        );
        g2d.setRenderingHints(hints);

        g2d.fillOval((int) (this.getX() - this.getCam().getX()),
                (int) (this.getY() - this.getCam().getY()),
                this.getWidth(),
                this.getHeight());

        g2d.dispose();
    }

}
