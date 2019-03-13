import java.awt.*;
import java.util.ArrayList;

abstract class Object {

    private ArrayList<Object> collisions = new ArrayList<>();
    private int width, height;
    private double x, y, xSpeed, ySpeed;
    private boolean exists;
    private Camera cam;

    Object(double x, double y, int width, int height, Camera cam) {

        this.x = x;
        this.y = y;

        this.width = width;
        this.height = height;

        this.xSpeed = 0;
        this.ySpeed = 0;

        this.cam = cam;

        this.exists = true;
    }

    double getX() { return x; }
    double getY() { return y; }

    void setX(double x) { this.x = x; }
    void setY(double y) { this.y = y; }

    int getWidth() { return width; }
    int getHeight() { return height; }

    void setWidth(int width) { this.width = width; }
    void setHeight(int height) { this.height = height; }

    double getXSpeed() { return xSpeed; }
    double getYSpeed() { return ySpeed; }

    void setXSpeed(double xSpeed) { this.xSpeed = xSpeed; }
    void setYSpeed(double ySpeed) { this.ySpeed = ySpeed; }

    Camera getCam() { return this.cam; }

    void kill() { this.exists = false; }
    boolean getExists() { return exists; }

    void addCollision(Object obj) { this.collisions.add(obj); }
    ArrayList<Object> getCollisions() { return collisions; }
    void clearCollision() { this.collisions.clear(); }

    abstract void update();
    abstract void draw(Graphics g);

    void preDraw(Graphics g) {

        int tempX = (int) (x - cam.getX());
        int tempY = (int) (y - cam.getY());

        if (tempX + width > 0 && tempX < cam.getWidth()) {

            if (tempY + height > 0 && tempY < cam.getHeight()) {

                draw(g);
            }
        }
    }
}