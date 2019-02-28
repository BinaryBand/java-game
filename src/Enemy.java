import java.awt.*;

public class Enemy implements Object {

    private float x, y, height, width, xSpeed, ySpeed;

    Enemy(float x, float y, float height, float width) {

        this.x = x;
        this.y = y;

        this.height = height;
        this.width = width;

        this.xSpeed = 0;
        this.ySpeed = 0;
    }

    @Override
    public void update() {

        this.x += this.xSpeed;
        this.y += this.ySpeed;
    }

    @Override
    public void draw(Graphics g) {

        g.fillRect(Math.round(this.x), Math.round(this.y), Math.round(this.width), Math.round(this.height));
    }

}
