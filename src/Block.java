import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Random;

public class Block {
    private static final int X_SIZE = 40;
    private static final int Y_SIZE = 10;
    private static int SPEED = Constants.BLOCK_SPEED;

    private JPanel panel;
    private Player player;
    private Dimension dimension;
    private int x;
    private int y;
    private int invunurable = 1;

    private Graphics2D g2;
    private Color backgroundColor;

    public Block(JPanel p, Player py) {
        panel = p;
        player = py;
        Graphics g = panel.getGraphics();
        g2 = (Graphics2D) g;
        backgroundColor = panel.getBackground();

        dimension = panel.getSize();
        x = ((dimension.width / 8) * ((new Random().nextInt(8)))) + (dimension.width / 100);
        y = (dimension.height / 12) * 11;

        SPEED = Constants.BLOCK_SPEED;
    }

    public void draw() {
        g2.setColor(Color.darkGray);
        g2.fill(new Rectangle2D.Double(x, y, X_SIZE, Y_SIZE));
    }

    private void erase() {
        g2.setColor(backgroundColor);
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
        erase();

        y = y - SPEED;

        if (playerHitsBlock()) {

            if (invunurable == 0) {
                player.decrementLives();
                invunurable = 1;
            } else {
                invunurable--;
            }
            System.out.println(invunurable);

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}