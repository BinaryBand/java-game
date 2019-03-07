import java.awt.*;

class Player extends Object {

    private Controls keys;

    Player(float x, float y, int width, int height, Camera cam, Controls keys) {
        super(x, y, width, height, cam);

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