import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Block
 */
public class Block {
    private static final int X_SIZE = 20;
    private static final int Y_SIZE = 10;
    private static final int SPEED = 10;

    private JPanel panel;
    private Dimension dimension;
    private int x;
    private int y;

    Graphics2D g2;
    private Color backgroundColor;

    public Block(JPanel p) {
        panel = p;
        Graphics g = panel.getGraphics();
        g2 = (Graphics2D) g;
        backgroundColor = panel.getBackground();

        dimension = panel.getSize();
        x = (dimension.width / 2);
        y = (dimension.height / 4) * 3;
    }

    public void draw() {
        g2.setColor(Color.BLUE);
        g2.fill(new Rectangle2D.Double(x, y, X_SIZE, Y_SIZE));
    }

    public void erase() {
        g2.setColor(backgroundColor);
        g2.fill(new Rectangle2D.Double(x, y, X_SIZE, Y_SIZE));
    }

    public Rectangle2D.Double getBoundingRectangle() {
        return new Rectangle2D.Double(x, y, X_SIZE, Y_SIZE);
    }

    public void move (){
        if (!panel.isVisible ()) return;
        erase();
        y = y + SPEED;
    }
}