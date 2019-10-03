import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Random;

public class Block {
    private Graphics2D g2;
    private static final int X_SIZE = Constants.BLOCK_WIDTH;
    private static final int Y_SIZE = Constants.BLOCK_HEIGHT;
    private static int SPEED = Constants.BLOCK_SPEED;

    private JPanel panel;
    private Player player;
    private int x;
    private int y;

    public Block(JPanel p, Player py) {
        panel = p;
        player = py;
        Graphics g = panel.getGraphics();
        g2 = (Graphics2D) g;

        Dimension dimension = panel.getSize();
        x = ((dimension.width / 8) * ((new Random().nextInt(8)))) + (dimension.width / 100);
        y = (dimension.height / 12) * 11;

        SPEED = Constants.BLOCK_SPEED;
    }

    public void draw() {
        g2.setColor(Color.darkGray);
        g2.fill(new Rectangle2D.Double(x, y, X_SIZE, Y_SIZE));
    }

    private Rectangle2D.Double getBoundingRectangle() {
        return new Rectangle2D.Double(x, y, X_SIZE, Y_SIZE);
    }

    private boolean playerHitsBlock() {
        Rectangle2D.Double playerHitBox = player.getBoundingRectangle();
        Rectangle2D.Double blockHitBox = getBoundingRectangle();
        return (playerHitBox.intersects(blockHitBox));
    }

    public void move() {
        if (!panel.isVisible()) return;
        y = y - SPEED;

        if (playerHitsBlock()) {
            player.decrementLives();

            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}