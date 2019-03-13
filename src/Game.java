import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Game extends JPanel implements ActionListener {

    private Camera cam;
    private int width, height;
    private ArrayList<Object> items = new ArrayList<>();

    Game(int width, int height) {

        this.width = width;
        this.height = height;

        createGame();
    }

    private void createGame() {

        // Create a camera
        cam = new Camera(width, height);

        // Create a player object
        Player player = new Player(0, 0, 50, 50, cam, setControls());

        // Add player to objects list and set it as the subject for the camera
        items.add(player);
        cam.setSubject(player);

        // Create a bunch of random objects
        items.add(new Block(-50, 100, 100, 100, cam));

        // Set background color
        setBackground(Color.WHITE);

        // Frame rate
        int fps = 60;
        int DELAY = 1000 / fps;

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

        // Draw each item on the map
        for (Object item : items) { item.preDraw(g); }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Clear list of items to be removed
        ArrayList<Object> removedItems = new ArrayList<>();

        // Record items that no longer exist
        for (Object item : items) { if (!item.getExists()) { removedItems.add(item); } }

        // Remove items that no longer exist
        for (Object item : removedItems) { items.remove(item); }

        // Clear list of collisions for each item
        for (Object item : items) { item.clearCollision(); }

        handleCollisions();

        // Update each item on the map
        for (Object item : items) { item.update(); }

        cam.update();

        repaint();
    }

    private void handleCollisions() {

        int x1 = 0;
        for (Object item : items) {

            if (item.getX() < x1) {

                x1 = (int) (item.getX());
            }
        }

        int y1 = 0;
        for (Object item : items) {

            if (item.getY() < y1) {

                y1 = (int) (item.getY());
            }
        }

        int x2 = 0;
        for (Object item : items) {

            if (item.getX() + item.getWidth() > x2) {

                x2 = (int) (item.getX() + item.getWidth());
            }
        }

        int y2 = 0;
        for (Object item : items) {

            if (item.getY() + item.getHeight() > y2) {

                y2 = (int) (item.getY() + item.getHeight());
            }
        }

        // Check for collisions
        quadTree(x1, y1, x2, y2, items);
    }

    private void quadTree(int x1, int y1, int x3, int y3, ArrayList<Object> objects) {

        if (4 <= objects.size() && 8 <= x3 - x1) {

            ArrayList<Object> q1 = new ArrayList<>();
            ArrayList<Object> q2 = new ArrayList<>();
            ArrayList<Object> q3 = new ArrayList<>();
            ArrayList<Object> q4 = new ArrayList<>();

            int x2 = ((x3 - x1) / 2) + x1;
            int y2 = ((y3 - y1) / 2) + y1;

            for (Object item : objects) {

                if ((x2 > item.getX() + item.getXSpeed()) &&
                        (y2 > item.getY() + item.getYSpeed()) &&
                        (item.getX() + item.getWidth() + item.getXSpeed() > x1) &&
                        (item.getY() + item.getHeight() + item.getYSpeed() > y1)) {

                    q1.add(item);
                }

                if ((x3 > item.getX() + item.getXSpeed()) &&
                        (y2 > item.getY() + item.getYSpeed()) &&
                        (item.getX() + item.getWidth() + item.getXSpeed()> x2) &&
                        (item.getY() + item.getHeight() + item.getYSpeed() > y1)) {

                    q2.add(item);
                }

                if ((x2 > item.getX() + item.getXSpeed()) &&
                        (y3 > item.getY() + item.getYSpeed()) &&
                        (item.getX() + item.getWidth() + item.getXSpeed()> x1) &&
                        (item.getY() + item.getHeight() + item.getYSpeed() > y2)) {

                    q3.add(item);
                }

                if ((x3 > item.getX() + item.getXSpeed()) &&
                        (y3 > item.getY() + item.getYSpeed()) &&
                        (item.getX() + item.getWidth() + item.getXSpeed()> x2) &&
                        (item.getY() + item.getHeight() + item.getYSpeed() > y2)) {

                    q4.add(item);
                }
            }

            quadTree(x1, y1, x2, y2, q1);
            quadTree(x2, y1, x3, y2, q2);
            quadTree(x1, y2, x2, y3, q3);
            quadTree(x2, y2, x3, y3, q4);
        }
        else {

            for (int i = 0; i < objects.size(); i++) {

                Object first = objects.get(i);
                for (int j = i + 1; j < objects.size(); j++) {

                    Object second = objects.get(j);

                    if ((second.getX() + second.getWidth() + second.getXSpeed() > first.getX() + first.getXSpeed()) &&
                            (second.getY() + second.getHeight() + second.getYSpeed() > first.getY() + first.getYSpeed()) &&
                            (first.getX() + first.getWidth() + first.getXSpeed() > second.getX() + second.getXSpeed()) &&
                            (first.getY() + first.getHeight() + first.getYSpeed() > second.getY() + second.getYSpeed())) {

                        first.addCollision(second);
                        second.addCollision(first);
                    }
                }
            }
        }
    }

}