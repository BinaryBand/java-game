import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class Player implements Object {

    private Controls keys;
    private boolean exists;
    private Image spriteSheet;
    private Platform platform;
    private float x, y, height, width, xSpeed, ySpeed;
    private ArrayList<Object> collisions = new ArrayList<>();

    Player(float x, float y, float height, float width, Controls keys) {

        this.keys = keys;

        ImageIcon rawImage = new ImageIcon("src/res/mario.png");
        this.spriteSheet = rawImage.getImage();

        this.x = x;
        this.y = y;

        this.height = height;
        this.width = width;

        this.xSpeed = 0;
        this.ySpeed = 0;

        this.exists = true;
    }

    @Override
    public float getX() { return this.x; }

    @Override
    public float getY() { return this.y; }

    @Override
    public float getHeight() { return this.height; }

    @Override
    public float getWidth() { return this.width; }

    @Override
    public float getXSpeed() { return this.xSpeed; }

    @Override
    public float getYSpeed() { return this.ySpeed; }

    @Override
    public void setPlatform(Platform platform) { this.platform = platform; }

    @Override
    public boolean getExists() { return this.exists; }

    @Override
    public void addCollision(Object object) { this.collisions.add(object); }

    @Override
    public void clearCollisions() { this.collisions.clear(); this.platform = null; }

    public void update() {

        // this.ySpeed += 1;

        if (this.platform != null) {

            this.ySpeed = 0;

            float o = platform.getY2() - platform.getY1();
            float a = platform.getX2() - platform.getX1();
            float a2 = platform.getX2() - (this.x + (this.width / 2));

            this.y = (float) (a2 / (Math.cos(Math.atan(o / a)))) - this.height;
        }

        for (Object obj : this.collisions) {

            if (obj instanceof Enemy) {

                this.xSpeed = -10;
            }
        }

        if (this.keys.getLeft()) this.xSpeed -= 1;
        if (this.keys.getRight()) this.xSpeed += 1;

        if (this.keys.getUp()) this.ySpeed -= 1;
        if (this.keys.getDown()) this.ySpeed += 1;

        this.x += this.xSpeed;
        this.y += this.ySpeed;
    }

    public void draw(Graphics g) {

        g.drawImage(this.spriteSheet,
                Math.round(this.x),
                Math.round(this.y),
                Math.round(this.width),
                Math.round(this.height),
                null);
    }

}
