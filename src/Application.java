import java.awt.EventQueue;
import javax.swing.JFrame;

public class Application extends JFrame {

    private Application() {

        initUI();
    }

    private void initUI() {

        // Here we put the Board to the center of the JFrame container.
        add(new Game());

        // The setResizable() sets whether the frame can be resized.
        setResizable(false);

        // This line sets the size of the window.
        setSize(720, 480);

        // Set window title.
        setTitle("Game Engine");

        // This will close the application when we click on the close button. It is not the default behaviour.
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
