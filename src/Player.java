import javax.swing.*;
import java.awt.*;

class Player implements Object {

    private Image spriteSheet;
    private Controls keys;
    private float x, y, height, width, xSpeed, ySpeed;

    Player(float x, float y, float height, float width, Controls keys) {

        ImageIcon rawImage = new ImageIcon("src/res/mario.png");
        this.spriteSheet = rawImage.getImage();
        this.keys = keys;

        this.x = x;
        this.y = y;

        this.height = height;
        this.width = width;

        this.xSpeed = 0;
        this.ySpeed = 0;
    }

    public void update() {

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
