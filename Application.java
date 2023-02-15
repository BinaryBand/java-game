import java.awt.EventQueue;
import javax.swing.JFrame;

public class Application extends JFrame {

    private int width, height;

    private Application() {
        this.width = 720;
        this.height = 480;
        initUI();
    }

    private void initUI() {
        // Here we put the Board to the center of the JFrame container.
        add(new Game(this.width, this.height));

        // The setResizable() sets whether the frame can be resized.
        setResizable(false);

        // This line sets the size of the window.
        setSize(this.width, this.height);

        // Set window title.
        setTitle("Game Engine");

        // This will close the application when we click on the close button. It is not the default behavior.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Passing null to the setLocationRelativeTo() method centers the window on the screen.
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        // We create an instance of our code example and make it visible on the screen.
        EventQueue.invokeLater(() -> {
            Application ex = new Application();
            ex.setVisible(true);
        });
    }

}
