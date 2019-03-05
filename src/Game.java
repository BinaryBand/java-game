import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Game extends JPanel implements ActionListener {

    private ArrayList<Object> items = new ArrayList<>();
    private ArrayList<Platform> platforms = new ArrayList<>();
    private ArrayList<Object> collisionList = new ArrayList<>();

    Game() {

        createGame();
    }

    private void createGame() {

        // Create room
        items.add(new Player(100, 100, 100, 100, setControls()));

        // Create platforms
        platforms.add(new Platform(0, 200, 300, 250));
        platforms.add(new Platform(300, 250, 500, 270));

        // Set background color
        setBackground(Color.WHITE);

        // Frame rate
        int DELAY = 1000 / 30;

        Timer timer = new Timer(DELAY, this);
        timer.start();
    }

    private Controls setControls() {

        // Create and set up controls
        Controls keys = new Controls();
        this.setFocusable(true);
        this.requestFocus();
        this.addKeyListener(keys);

        return keys;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Object item : this.items) {

            item.draw(g);
        }

        for (Platform platform : this.platforms) {

            platform.draw(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        this.collisionList.clear();

        for (Object item : this.items) {

            if (!item.getExists()) {

                this.collisionList.add(item);
            }
        }

        for (Object item : this.collisionList) {

            this.items.remove(item);
        }

        for (Object item1 : this.items) {

            for (Object item2 : this.items) {

                if (itemCollision(item1, item2)) {

                    item1.addCollision(item2);
                }
            }
        }

        for (Object item : this.items) {

            for (Platform platform : this.platforms) {

                if (platformCollision(item, platform)) {

                    item.setPlatform(platform);
                }
            }
        }

        for (Object item : this.items) {

            item.update();
        }

        for (Object item : this.items) {

            item.clearCollisions();
        }

        repaint();
    }

    private boolean itemCollision(Object object1, Object object2) {

        return
            object2.getX() + object2.getWidth() + object2.getXSpeed() > object1.getX() + object1.getXSpeed() &&
            object2.getY() + object2.getHeight() + object2.getYSpeed() > object1.getY() + object1.getYSpeed() &&
            object1.getX() + object1.getWidth() + object1.getXSpeed() > object2.getX() + object2.getXSpeed()&&
            object1.getY() + object1.getHeight() + object1.getYSpeed() > object2.getY() + object2.getYSpeed();
    }

    private boolean platformCollision(Object object, Platform platform) {

        if (object.getYSpeed() < 0) {
            return false;
        }

        float x = object.getX() + (object.getWidth() / 2);
        float y = object.getY() + object.getHeight();

        float x1 = platform.getX1();
        float y1 = platform.getY1();

        float x2 = platform.getX2();
        float y2 = platform.getY2();

        if (!((x1 <= x && x <= x2) && (y1 <= y && y <= y2))) {

            return false;
        }

        float A = x - x1;   // position of point rel one end of line
        float B = y - y1;
        float C = x2 - x1;  // vector along line
        float D = y2 - y1;

        float dot = A * -D + B * C;
        float len_sq = -D * -D + C * C;

        float distance = (float) (Math.abs(dot) / Math.sqrt(len_sq));

        return distance <= Math.sqrt((object.getXSpeed() * object.getXSpeed()) + (object.getYSpeed() * object.getYSpeed())) + 1;
    }

}