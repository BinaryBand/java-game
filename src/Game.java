import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Game extends JPanel {

    private Camera cam;
    private Boolean running;
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
        Player player = new Player(0, 0, 37, 47, cam, setControls());

        // Add player to objects list and set it as the subject for the camera
        items.add(player);
        cam.setSubject(player);

        // Create blocks
        items.add(new Block(-50, 125, 100, 25, cam));
        items.add(new Block(125, 75, 100, 25, cam));
        items.add(new Block(300, 25, 100, 25, cam));
        items.add(new Block(-50, 200, 450, 50, cam));

        // Set background color
        setBackground(Color.WHITE);

        // Frame rate
        int fps = 30;
        int delay = 1000 / fps;

        running = true;

        Timer timer = new Timer();
        timer.schedule(new GameLoop(), 0, delay);
    }

    private Controls setControls() {

        // Create and set up controls
        Controls keys = new Controls();
        this.setFocusable(true);
        this.requestFocus();
        this.addKeyListener(keys);

        return keys;
    }

    private void loop() {

        // Clear list of items to be removed and added
        ArrayList<Object> removedItems = new ArrayList<>();
        ArrayList<Object> addedObjects = new ArrayList<>();

        // Record items that no longer exist or are being created
        for (Object item : items) {

            addedObjects.addAll(item.getAddedObjects());

            if (!item.getExists()) {
                removedItems.add(item);
            }
        }

        // Remove items that no longer exist
        for (Object item : removedItems) { items.remove(item); }

        // Add items than must be created
        items.addAll(addedObjects);

        // Clear list of collisions and added objects for each item
        for (Object item : items) {

            item.clearCollision();
            item.clearAddedObjects();
        }

        new GameFunctions().handleCollisions(items);

        // Update each item on the map
        for (Object item : items) { item.update(); }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        cam.update();

        Graphics2D g2 = (Graphics2D) g;

//        AffineTransform at = new AffineTransform();
//        at.scale(1.5, 1.5);
//        g2.transform(at);

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        AffineTransform old = g2.getTransform();
        g2.rotate(Math.toRadians(cam.getAngle()), width / 2.0, height / 2.0);

        // Draw each item on the map
        for (Object item : items) {

            item.preDraw(g2);
        }

        g2.setTransform(old);
        g2.dispose();
    }

    private class GameLoop extends TimerTask {

        @Override
        public void run() {

            long FPS = 30;

            long timer = System.currentTimeMillis();
            final double timeF = 1000000000.0 / FPS;
            long initialTime = System.nanoTime();
            double deltaF = 0;

            while (running) {

                long currentTime = System.nanoTime();
                deltaF += (currentTime - initialTime) / timeF;
                initialTime = currentTime;

                if (deltaF >= 1) {

                    loop();
                    repaint();
                    deltaF--;
                }

                if (System.currentTimeMillis() - timer > 1000) {

                    timer += 1000;
                }
            }
        }
    }

}