import java.awt.*;
import java.util.ArrayList;

abstract class Object {

    private ArrayList<Object> addedObjects = new ArrayList<>();
    private ArrayList<Object> collisions = new ArrayList<>();
    private int x, y, width, height;
    private double xSpeed, ySpeed;
    private boolean exists;
    private Camera cam;

    Object(int x, int y, int width, int height, Camera cam) {

        this.x = x;
        this.y = y;

        this.width = width;
        this.height = height;

        this.xSpeed = 0;
        this.ySpeed = 0;

        this.cam = cam;

        this.exists = true;
    }

    int getX() { return x; }
    int getY() { return y; }

    void setX(int x) { this.x = x; }
    void setY(int y) { this.y = y; }

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

    void createObject(Object obj) { addedObjects.add(obj); }
    ArrayList<Object> getAddedObjects() { return addedObjects; }
    void clearAddedObjects() { addedObjects.clear(); }

    void addCollision(Object obj) { collisions.add(obj); }
    ArrayList<Object> getCollisions() { return collisions; }
    void clearCollision() { collisions.clear(); }

    abstract void update();
    abstract void draw(int tempX, int tempY, Graphics g);

    void preDraw(Graphics g) {

        int tempX = (x - cam.getX());
        int tempY = (y - cam.getY());

        if (tempX + width > 0 && tempX < cam.getWidth()) {

            if (tempY + height > 0 && tempY < cam.getHeight()) {

                draw(tempX, tempY, g);
            }
        }
    }
}