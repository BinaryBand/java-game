import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controls implements KeyListener {

    private boolean left, right, up, down;

    Controls() {

        this.left = false;
        this.right = false;
        this.up = false;
        this.down = false;
    }

    boolean getLeft() { return this.left; }
    boolean getRight() { return this.right; }
    boolean getUp() { return this.up; }
    boolean getDown() { return this.down; }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) { this.left = true; }
        if (key == KeyEvent.VK_RIGHT) { this.right = true; }
        if (key == KeyEvent.VK_UP) { this.up = true; }
        if (key == KeyEvent.VK_DOWN) { this.down = true; }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) { this.left = false; }
        if (key == KeyEvent.VK_RIGHT) { this.right = false; }
        if (key == KeyEvent.VK_UP) { this.up = false; }
        if (key == KeyEvent.VK_DOWN) { this.down = false; }
    }

}
