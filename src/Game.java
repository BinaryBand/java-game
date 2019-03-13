import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class Game extends JPanel {

    private Camera cam;
    private Boolean running;
    private int width, height;
    private java.util.Timer timer;
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
        int delay = 1000 / fps;

        running = true;

        timer = new Timer();
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

        // Clear list of items to be removed
        ArrayList<Object> removedItems = new ArrayList<>();

        // Record items that no longer exist
        for (Object item : items) { if (!item.getExists()) { removedItems.add(item); } }

        // Remove items that no longer exist
        for (Object item : removedItems) { items.remove(item); }

        // Clear list of collisions for each item
        for (Object item : items) { item.clearCollision(); }

        new GameFunctions().handleCollisions(items);

        // Update each item on the map
        for (Object item : items) { item.update(); }

        cam.update();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw each item on the map
        for (Object item : items) { item.preDraw(g); }
    }

    private class GameLoop extends TimerTask {

        public void run() {

            loop();
            repaint();

            if (!running) {

                timer.cancel();
            }
        }
    }

}