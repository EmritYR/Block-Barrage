import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Player {
    private static final int SIDE_SIZE = 15;
    private static final int SPEED = 5;

    private JPanel panel;
    private Dimension dimension;
    private int x;
    private int y;
    private static int lives = 3;
    private int invunurable = 0;

    private Graphics2D g2;
    private Color backgroundColor;

    public Player(JPanel p) {
        panel = p;
        Graphics g = panel.getGraphics();
        g2 = (Graphics2D) g;
        backgroundColor = panel.getBackground();

        dimension = panel.getSize();
        x = (dimension.width / 2);
        y = (dimension.height / 8);
    }

    public void draw() {
        switch (lives) {
            case 1:
                g2.setColor(Color.RED);
                break;
            case 2:
                g2.setColor(Color.ORANGE);
                break;
            case 3:
                g2.setColor(Color.GREEN);
                break;
            case 4:
                g2.setColor(Color.BLUE);
                break;
            case 5:
                g2.setColor(Color.MAGENTA);
                break;
            default:
                g2.setColor(Color.CYAN);
                break;
        }

        for (int i = 1; i <= lives; i++) {
            g2.fill(new Rectangle2D.Double(x, y - (SIDE_SIZE * i) - (SIDE_SIZE * i / 3.0), SIDE_SIZE, SIDE_SIZE));
        }

//         Show Collision Boundary
//         g2.setColor(Color.BLACK);
//         g2.fill(new Rectangle2D.Double(x, y - (SIDE_SIZE * lives) - (SIDE_SIZE * lives / 3.5), SIDE_SIZE, (SIDE_SIZE * lives) + (lives * 2)));

    }

    private void erase() {
        g2.setColor(backgroundColor);
        for (int i = 1; i <= lives + 1; i++) {
            g2.fill(new Rectangle2D.Double(x, y - (SIDE_SIZE * i) - (SIDE_SIZE * i / 3.0), SIDE_SIZE, SIDE_SIZE));
        }
    }

    // Collision Box Slightly Smaller for Game Fairness Design
    public Rectangle2D.Double getBoundingRectangle() {
        return new Rectangle2D.Double(x, y - (SIDE_SIZE * lives) - (SIDE_SIZE * lives / 3.5), SIDE_SIZE, (SIDE_SIZE * lives) + (lives * 2));
    }

    public void decrementLives() {
        if (invunurable == 0) {
            lives--;
            invunurable = 1;
        } else {
            invunurable--;
        }
    }

    public void incrementLives() {
        lives++;
    }

    public void move(String direction) {
        if (!panel.isVisible()) return;

        switch (direction) {
            case "UP":
                y = y - SPEED;
                if (y < 0)
                    y = 0;
                break;
            case "LEFT":
                x = x - SPEED;
                if (x < 0)
                    x = 0;
                break;
            case "DOWN":
                y = y + SPEED;
                if (y + SPEED >= dimension.height)
                    y = dimension.height - SPEED;
                break;
            case "RIGHT":
                x = x + SPEED;
                if (x + SPEED >= dimension.width)
                    x = dimension.width - SPEED;
                break;
        }
    }

    public static int getLives() {
        return lives;
    }
}