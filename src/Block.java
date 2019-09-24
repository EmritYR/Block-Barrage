import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Random;

/**
 * Block
 */
public class Block {
    private static final int X_SIZE = 35;
    private static final int Y_SIZE = 10;
    private static final int SPEED = 3;

    private JPanel panel;
    private Player player;
    private Dimension dimension;
    private int x;
    private int y;

    Graphics2D g2;
    private Color backgroundColor;

    public Block(JPanel p, Player py) {
        panel = p;
        player = py;
        Graphics g = panel.getGraphics();
        g2 = (Graphics2D) g;
        backgroundColor = panel.getBackground();

        dimension = panel.getSize();
        x = (dimension.width / 8) * ((new Random().nextInt(7)) + 1);
        y = (dimension.height / 12) * 11;
    }

    public void draw() {
        g2.setColor(Color.darkGray);
        g2.fill(new Rectangle2D.Double(x, y, X_SIZE, Y_SIZE));
    }

    public void erase() {
        g2.setColor(backgroundColor);
        g2.fill(new Rectangle2D.Double(x, y, X_SIZE, Y_SIZE));
    }

    public Rectangle2D.Double getBoundingRectangle() {
        return new Rectangle2D.Double(x, y, X_SIZE, Y_SIZE);
    }

    public boolean playerHitsBlock() {
        Rectangle2D.Double playerHitBox = player.getBoundingRectangle();
        Rectangle2D.Double blockHitBox = getBoundingRectangle();
        return  (playerHitBox.intersects(blockHitBox));
    }

    public void move() {
        if (!panel.isVisible()) return;
        erase();
        y = y - SPEED;

        if (playerHitsBlock()) {
            player.decrementLives();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}