import java.awt.*;

class Player extends Object {

    private Controls keys;
    private int points;

    Player(float x, float y, int width, int height, Controls keys) {
        super(x, y, width, height);

        this.setX(x);
        this.setY(y);

        this.setWidth(width);
        this.setHeight(height);

        this.points = 0;

        this.keys = keys;
    }

    @Override
    void update() {

        for (Object item : this.getCollisions()) {

            if (item instanceof Particle) {

                this.points += 1;

                if (this.points % 50 == 0) {

                    this.setX(this.getX() - 1);
                    this.setY(this.getY() - 1);

                    this.setWidth(this.getWidth() + 2);
                    this.setHeight(this.getHeight() + 2);
                }
            }
        }

        if (this.keys.getRight()) { this.setX(this.getX() + 2); }
        if (this.keys.getLeft()) { this.setX(this.getX() - 2); }
        if (this.keys.getUp()) { this.setY(this.getY() - 2); }
        if (this.keys.getDown()) { this.setY(this.getY() + 2); }

        this.setX(this.getX() + this.getXSpeed());
        this.setY(this.getY() + this.getYSpeed());
    }

    @Override
    void draw(Graphics g) {

        g.setColor(Color.blue);

        g.fillOval(Math.round(this.getX()),
                Math.round(this.getY()),
                this.getWidth(),
                this.getHeight());
    }

}