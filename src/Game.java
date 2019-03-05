import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class Game extends JPanel implements ActionListener {

    private int width, height;
    private ArrayList<Object> items = new ArrayList<>();
    private ArrayList<Object> removedItems = new ArrayList<>();

    Game(int width, int height) {

        this.width = width;
        this.height = height;

        createGame();
    }

    private void createGame() {

        // Create player object
        this.items.add(new Player(100, 100, 10, 10, setControls()));

        // Create a bunch of random objects
        Random rand = new Random();
        for (int i = 0; i < 1000; i++) {

            this.items.add(new Particle(Math.abs(rand.nextInt() % 720), Math.abs(rand.nextInt() % 480), 10, 10, rand));
        }

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
        for (Object item : this.items) { item.draw(g); }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Clear list of items to be removed
        this.removedItems.clear();

        // Record items that no longer exist
        for (Object item : this.items) { if (!item.getExists()) { this.removedItems.add(item); } }

        // Remove items that no longer exist
        for (Object item : this.removedItems) { this.items.remove(item); }

        // Update each item on the map
        for (Object item : this.items) { item.update(); }

        // Clear list of collisions for each item
        for (Object item : this.items) { item.clearCollision(); }

        // Check for collisions
        quadTree(0, 0, this.width, this.height, this.items);

        repaint();
    }

    private void quadTree(int x1, int y1, int x3, int y3, ArrayList<Object> objects) {

        if (4 <= objects.size() && 12 <= x3 - x1) {

            ArrayList<Object> q1 = new ArrayList<>();
            ArrayList<Object> q2 = new ArrayList<>();
            ArrayList<Object> q3 = new ArrayList<>();
            ArrayList<Object> q4 = new ArrayList<>();

            int x2 = ((x3 - x1) / 2) + x1;
            int y2 = ((y3 - y1) / 2) + y1;

            for (Object item : objects) {

                if ((x2 > item.getX()) &&
                    (y2 > item.getY()) &&
                    (item.getX() + item.getWidth() > x1) &&
                    (item.getY() + item.getHeight() > y1)) {

                    q1.add(item);
                }

                if ((x3 > item.getX()) &&
                    (y2 > item.getY()) &&
                    (item.getX() + item.getWidth() > x2) &&
                    (item.getY() + item.getHeight() > y1)) {

                    q2.add(item);
                }

                if ((x2 > item.getX()) &&
                    (y3 > item.getY()) &&
                    (item.getX() + item.getWidth() > x1) &&
                    (item.getY() + item.getHeight() > y2)) {

                    q3.add(item);
                }

                if ((x3 > item.getX()) &&
                    (y3 > item.getY()) &&
                    (item.getX() + item.getWidth() > x2) &&
                    (item.getY() + item.getHeight() > y2)) {

                    q4.add(item);
                }
            }

            quadTree(x1, y1, x2, y2, q1);
            quadTree(x2, y1, x3, y2, q2);
            quadTree(x1, y2, x2, y3, q3);
            quadTree(x2, y2, x3, y3, q4);
        }
        else
        {

            for (int i = 0; i < objects.size(); i++) {

                Object first = objects.get(i);
                for (int j = i + 1; j < objects.size(); j++) {

                    Object second = objects.get(j);

                    if ((second.getX() + second.getWidth() > first.getX()) &&
                        (second.getY() + second.getHeight() > first.getY()) &&
                        (first.getX() + first.getWidth() > second.getX()) &&
                        (first.getY() + first.getHeight() > second.getY())) {

                        first.addCollision(second);
                        second.addCollision(first);
                    }
                }
            }
        }
    }

}