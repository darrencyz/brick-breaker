import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

/**
 * Created by dcaoyz on 2016-01-27.
 */

public class Breakout extends JComponent {
    private Timer t;
    private Brick[] bricks;
    private Paddle paddle;
    private Ball ball;
    private int fps;
    private int speed;
    private int score = 0;
    private boolean gameLost = false;
    private boolean gameWon = false;

    public Breakout(int fps, int speed) {
        super();
        this.fps = fps;
        this.speed = speed;
        newGame();
        ActionListener repainter = new ActionListener() {
            public void actionPerformed(ActionEvent e){
                ball.move();
                paddle.move();
                checkCollision();
                repaint();
            }
        };
        t = new Timer(1000/fps, repainter);
        t.start();
    }

    private void newGame() {
        addKeyListener(new PaddleListener());
        setFocusable(true);
        ball = new Ball(fps, speed);
        paddle = new Paddle(fps);
        bricks = new Brick[40];

        Random ran = new Random();
        int power = 0 + ran.nextInt(40 - 0 + 1);

        int k = 0;
        while (k < 40) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 8; j++) {
                    if (k == power) {
                        bricks[k] = new Brick(j * 51 + 50, i * 26 + 100, 50, 25, 5);
                        bricks[k].setPower(true);
                    }
                    else {
                        bricks[k] = new Brick(j * 51 + 50, i * 26 + 100, 50, 25, i);
                    }
                    k++;
                }
            }
        }
    }

    private void drawComponents(Graphics2D g2d) {
        g2d.drawString("Score: "+ String.valueOf(score), 30, 30);
        g2d.drawString("FPS: "+ String.valueOf(fps), 30, 50);
        g2d.drawString("Speed: "+ String.valueOf(speed), 30, 70);
        g2d.drawImage(paddle.getImage(), paddle.getX(), paddle.getY(), paddle.getWidth(), paddle.getHeight(), this);
        for (int i = 0; i < 40; i++) {
            if (!bricks[i].isDestroyed()) {
                g2d.drawImage(bricks[i].getImage(), bricks[i].getX(), bricks[i].getY(), bricks[i].getWidth(), bricks[i].getHeight(), this);
            }
        }
        g2d.drawImage(ball.getImage(), ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight(), this);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        if (gameLost) {
            gameOver(g2d);
        }
        else if (gameWon) {
            gameWon(g2d);
        }
        else {
            drawComponents(g2d);
        }
    }

    private class PaddleListener extends KeyAdapter {
        public void keyReleased(KeyEvent e) {
            paddle.keyReleased(e);
        }

        public void keyPressed(KeyEvent e) {
            paddle.keyPressed(e);
        }
    }

    private void gameOver(Graphics2D g2d) {
        Font font = new Font("Verdana", Font.BOLD, 18);

        g2d.setColor(Color.BLACK);
        g2d.setFont(font);
        g2d.drawString("Game Over! Score: " + String.valueOf(score), 135, 200);
    }

    private void gameWon(Graphics2D g2d) {
        Font font = new Font("Verdana", Font.BOLD, 18);

        g2d.setColor(Color.BLACK);
        g2d.setFont(font);
        g2d.drawString("Game Won! Score: " + String.valueOf(score), 135, 200);
    }

    private void checkCollision() {

        if (ball.getRect().getMaxY() > 750 && !gameWon) {
            gameLost = true;
        }

        for (int i = 0, j = 0; i < 40; i++) {

            if (bricks[i].isDestroyed()) {
                j++;
            }

            if (j == 40) {
                gameWon = true;
            }
        }

        if ((ball.getRect()).intersects(paddle.getRect())) {

            int paddleLPos = (int) paddle.getRect().getMinX();
            int ballLPos = (int) ball.getRect().getMinX();

            int first = paddleLPos + 8;
            int second = paddleLPos + 16;
            int third = paddleLPos + 24;
            int fourth = paddleLPos + 32;

            if (ballLPos < first) {
                ball.setXDir(-(speed) * (60/fps));
                ball.setYDir(-(speed)* (60/fps));
            }

            if (ballLPos >= first && ballLPos < second) {
                ball.setXDir(-(speed)* (60/fps));
                ball.setYDir(-1 * ball.getYDir());
            }

            if (ballLPos >= second && ballLPos < third) {
                ball.setXDir(0);
                ball.setYDir(-(speed)* (60/fps));
            }

            if (ballLPos >= third && ballLPos < fourth) {
                ball.setXDir((speed));
                ball.setYDir(-1 * ball.getYDir());
            }

            if (ballLPos > fourth) {
                ball.setXDir((speed)* (60/fps));
                ball.setYDir(-(speed)* (60/fps));
            }
        }

        for (int i = 0; i < 40; i++) {

            if ((ball.getRect()).intersects(bricks[i].getRect())) {

                int ballLeft = (int) ball.getRect().getMinX();
                int ballHeight = (int) ball.getRect().getHeight();
                int ballWidth = (int) ball.getRect().getWidth();
                int ballTop = (int) ball.getRect().getMinY();

                Point pointRight = new Point(ballLeft + ballWidth + 1, ballTop);
                Point pointLeft = new Point(ballLeft - 1, ballTop);
                Point pointTop = new Point(ballLeft, ballTop - 1);
                Point pointBottom = new Point(ballLeft, ballTop + ballHeight + 1);

                if (!bricks[i].isDestroyed()) {
                    if (bricks[i].getRect().contains(pointRight)) {
                        ball.setXDir(-(speed)* (60/fps));
                    } else if (bricks[i].getRect().contains(pointLeft)) {
                        ball.setXDir((speed)* (60/fps));
                    }

                    if (bricks[i].getRect().contains(pointTop)) {
                        ball.setYDir((speed)* (60/fps));
                    } else if (bricks[i].getRect().contains(pointBottom)) {
                        ball.setYDir(-(speed)* (60/fps));
                    }


                    if (bricks[i].isPowerup()) {
                        int column = i % 8;
                        for (int j = 0; j < 5; j++) {
                            if (!bricks[j*8 + column].isDestroyed()) {
                                bricks[j*8 + column].setDestroyed(true);
                                score += 20;
                            }
                        }
                    }
                    else {
                        bricks[i].setDestroyed(true);
                        score += 10;
                    }
                }
            }
        }
    }

    public static void main(String args[]) {
        int fps = Integer.parseInt(args[0]);
        int speed = Integer.parseInt(args[1]);
        JFrame frame = new JFrame("Breakout");
        frame.setSize(508, 750);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JFrame splash = new JFrame("Splash");
        splash.setSize(508, 750);
        splash.setUndecorated(true);
        splash.setLayout(new GridBagLayout());
        JPanel panel = new JPanel();
        JLabel jlabel = new JLabel("<html>Breakout<br><br>Use the left and right arrow keys to move<br>the paddle left and right.<br>Watch out for the special power-up block!<br><br> Click anywhere to start game. </html>");
        jlabel.setFont(new Font("Verdana",1,20));
        panel.add(jlabel);
        splash.add(panel, new GridBagConstraints());
        splash.setLocationRelativeTo(null);
        splash.setVisible(true);

        splash.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                splash.setVisible(false);
                frame.setContentPane(new Breakout(fps, speed));
                frame.setVisible(true);
            }
        });
    }
}
