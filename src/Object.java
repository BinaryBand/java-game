import java.awt.*;
import java.util.ArrayList;

abstract class Object {

    private ArrayList<Object> collisions = new ArrayList<>();
    private double x, y, xSpeed, ySpeed;
    private int width, height;
    private boolean exists;

    Object(double x, double y, int width, int height) {

        this.x = x;
        this.y = y;

        this.width = width;
        this.height = height;

        this.xSpeed = 0;
        this.ySpeed = 0;

        this.exists = true;
    }

    double getX() { return this.x; }
    double getY() { return this.y; }

    void setX(double x) { this.x = x; }
    void setY(double y) { this.y = y; }

    int getWidth() { return this.width; }
    int getHeight() { return this.height; }

    void setWidth(int width) { this.width = width; }
    void setHeight(int height) { this.height = height; }

    double getXSpeed() { return this.xSpeed; }
    double getYSpeed() { return this.ySpeed; }

    void setXSpeed(double xSpeed) { this.xSpeed = xSpeed; }
    void setYSpeed(double ySpeed) { this.ySpeed = ySpeed; }

    void kill() { this.exists = false; }
    boolean getExists() { return this.exists; }

    void addCollision(Object obj) { this.collisions.add(obj); }
    ArrayList<Object> getCollisions() { return this.collisions; }
    void clearCollision() { this.collisions.clear(); }

    abstract void update();
    abstract void draw(Graphics g);

}