import java.awt.*;

interface Object {

    float getX();
    float getY();

    float getHeight();
    float getWidth();

    float getXSpeed();
    float getYSpeed();

    void setPlatform(Platform platform);

    boolean getExists();

    void update();
    void addCollision(Object object);
    void clearCollisions();
    void draw(Graphics g);

}
