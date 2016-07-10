import java.awt.Image;
import java.awt.Rectangle;

/**
 * Created by dcaoyz on 2016-01-27.
 */
public class BreakoutObject {
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected Image image;
    protected int fps;

    public BreakoutObject (int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public BreakoutObject (int x, int y, int width, int height, int fps) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.fps = fps;
    }

    public BreakoutObject () {}

    public int getX() {
        return x;
    }

    public void setX(int value) {
        x = value;
    }

    public int getY() {
        return y;
    }

    public void setY(int value) {
        y = value;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int value) {
        width = value;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int value) {
        height = value;
    }

    public Image getImage() {
        return image;
    }

    public int getFps() {
        return fps;
    }

    Rectangle getRect() {
        return new Rectangle(x, y, image.getWidth(null), image.getHeight(null));
    }
}
