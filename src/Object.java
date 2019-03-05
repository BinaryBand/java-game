import java.awt.*;
import java.util.ArrayList;

abstract class Object {

    private ArrayList<Object> collisions = new ArrayList<>();
    private float x, y, xSpeed, ySpeed;
    private int width, height;
    private boolean exists;

    Object(float x, float y, int width, int height) {

        this.x = x;
        this.y = y;

        this.width = width;
        this.height = height;

        this.xSpeed = 0;
        this.ySpeed = 0;

        this.exists = true;
    }

    float getX() { return this.x; }
    float getY() { return this.y; }

    void setX(float x) { this.x = x; }
    void setY(float y) { this.y = y; }

    int getWidth() { return this.width; }
    int getHeight() { return this.height; }

    void setWidth(int width) { this.width = width; }
    void setHeight(int height) { this.height = height; }

    float getXSpeed() { return this.xSpeed; }
    float getYSpeed() { return this.ySpeed; }

    void setXSpeed(float xSpeed) { this.xSpeed = xSpeed; }
    void setYSpeed(float ySpeed) { this.ySpeed = ySpeed; }

    void kill() { this.exists = false; }
    boolean getExists() { return this.exists; }

    void addCollision(Object obj) { this.collisions.add(obj); }
    ArrayList<Object> getCollisions() { return this.collisions; }
    void clearCollision() { this.collisions.clear(); }

    abstract void update();
    abstract void draw(Graphics g);

}
