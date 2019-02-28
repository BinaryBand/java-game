import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Game extends JPanel implements ActionListener {

    private ArrayList<Object> items = new ArrayList<>();
    private ArrayList<Object> tempList = new ArrayList<>();

    Game() {

        createGame();
    }

    private void createGame() {

        // Create player object
        items.add(new Player(100, 100, 100, 100, setControls()));
        items.add(new Enemy(300, 200, 50, 50));
        items.add(new Enemy(350, 300, 50, 50));

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

        for (Object item : items) {
            item.draw(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        this.tempList.clear();

        for (Object item : this.items) {

            if (!item.getExists()) {

                tempList.add(item);
            }
        }

        for (Object item : tempList) {

            this.items.remove(item);
        }

        for (Object item1 : this.items) {

            for (Object item2 : this.items) {

                if (checkCollision(item1, item2)) {

                    item1.addCollision(item2);
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

    private boolean checkCollision(Object object1, Object object2) {

        return
            object2.getX() + object2.getWidth() + object2.getXSpeed() > object1.getX() + object1.getXSpeed() &&
            object2.getY() + object2.getHeight() + object2.getYSpeed() > object1.getY() + object1.getYSpeed() &&
            object1.getX() + object1.getWidth() + object1.getXSpeed() > object2.getX() + object2.getXSpeed()&&
            object1.getY() + object1.getHeight() + object1.getYSpeed() > object2.getY() + object2.getYSpeed();
    }

}