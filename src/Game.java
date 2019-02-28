import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Game extends JPanel implements ActionListener {

    private ArrayList<Object> items = new ArrayList<>();

    Game() {

        createGame();
    }

    private void createGame() {

        // Create player object
        items.add(new Player(100, 100, 100, 100, setControls()));
        items.add(new Enemy(300, 200, 50, 50));

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

        for (Object item : items) {
            item.update();
        }

        repaint();
    }

}