import javax.swing.ImageIcon;

/**
 * Created by dcaoyz on 2016-01-27.
 */
public class Brick extends BreakoutObject {
    private boolean destroyed;
    private boolean power;

    public Brick (int x, int y, int width, int height, int color) {
        super(x, y, width, height);
        ImageIcon ii = new ImageIcon("./brick" + color + ".png");
        image = ii.getImage();
        destroyed = false;
        power = false;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void setDestroyed(boolean value) {
        destroyed = value;
    }

    public boolean isPowerup() {
        return power;
    }

    public void setPower(boolean value) {
        power = value;
    }
}
