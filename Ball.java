import javax.swing.*;

/**
 * Created by dcaoyz on 2016-01-27.
 */
public class Ball extends BreakoutObject {
    private int xdir;
    private int ydir;
    private int speed;

    public Ball(int fps, int speed) {

        xdir = (speed)* (60/fps);
        ydir = -(speed)* (60/fps);

        x = 204;
        y = 600;

        width = 25;
        height = 25;

        this.fps = fps;
        this.speed = speed;


        ImageIcon ii = new ImageIcon("./ball.png");
        image = ii.getImage();
    }

    public void move() {

        x += xdir;
        y += ydir;

        if (x <= 0) {
            setXDir((speed) * (60/fps));
        }

        if (x >= 508 - 25) {
            setXDir(-(speed) * (60/fps));
        }

        if (y <= 0) {
            setYDir((speed) * (60/fps));
        }
    }

    public void setXDir(int x) {
        xdir = x;
    }

    public void setYDir(int y) {
        ydir = y;
    }

    public int getYDir() {
        return ydir;
    }
}
