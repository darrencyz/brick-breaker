import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;

/**
 * Created by dcaoyz on 2016-01-27.
 */
public class Paddle extends BreakoutObject {
    private int dx;

    public Paddle (int fps) {
        width = 100;
        height = 10;
        x = 204;
        y = 680;
        this.fps = fps;
        ImageIcon ii = new ImageIcon("./paddle.png");
        image = ii.getImage();
    }

    public void move() {
        x += dx;

        if (x <= 0) {
            x = 0;
        }

        if (x >= 408) {
            x = 408;
        }
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            dx = -5 * (60/fps);
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            dx = 5 * (60/fps);
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
    }
}