import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Player
 */
public class Player {
    private static final int SIDE_SIZE = 15;
    private static final int SPEED = 5;

    private JPanel panel;
    private Dimension dimension;
    private int x;
    private int y;

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
        g2.setColor(Color.BLUE);
        g2.fill(new Rectangle2D.Double(x, y, SIDE_SIZE, SIDE_SIZE));
    }

    private void erase() {
        g2.setColor(backgroundColor);
        g2.fill(new Rectangle2D.Double(x, y, SIDE_SIZE, SIDE_SIZE));
    }

    public Rectangle2D.Double getBoundingRectangle() {
        return new Rectangle2D.Double(x, y, SIDE_SIZE, SIDE_SIZE);
    }

    public void move(String direction) {
        if (!panel.isVisible()) return;
        erase();

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
}