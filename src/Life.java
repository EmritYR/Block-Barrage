import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Random;

public class Life {
    private static final int SPEED = Constants.LIFE_SPEED;
    private static final int SIDE_SIZE = Constants.LIFE_SIZE;

    private JPanel panel;
    private Player player;
    private int x;
    private int y;
    private boolean used = false;

    private Graphics2D g2;

    public Life(JPanel p, Player py) {
        panel = p;
        player = py;
        g2 = (Graphics2D) panel.getGraphics();

        Dimension dimension = panel.getSize();
        x = ((dimension.width / 8) * ((new Random().nextInt(8)))) + (dimension.width / 100);
        y = (dimension.height / 12) * 11;
    }

    public void draw() {
        if (used) return;
        g2.setColor(Color.RED);
        g2.fill(new Rectangle2D.Double(x, y, SIDE_SIZE, SIDE_SIZE));
    }

    private Rectangle2D.Double getBoundingRectangle() {
        return new Rectangle2D.Double(x, y, SIDE_SIZE, SIDE_SIZE);
    }

    private boolean playerHitsLife() {
        Rectangle2D.Double playerHitBox = player.getBoundingRectangle();
        Rectangle2D.Double lifeHitBox = getBoundingRectangle();
        return playerHitBox.intersects(lifeHitBox);
    }

    public void move() {
        if (!panel.isVisible()) return;
        y = y - SPEED;

        if (playerHitsLife() && !used) {
            player.incrementLives();
            used = true;

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isUsed() {
        return used;
    }
}
