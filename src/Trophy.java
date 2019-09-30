import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.PrimitiveIterator;

public class Trophy {
    private static final int SIZE = Constants.TROPHY_SIZE;

    private JPanel panel;
    private Player player;
    private Dimension dimension;
    private Graphics2D g2;
    private Color backgroundColor;
    private int y;
    private int x;
    private boolean used;

    public Trophy(JPanel p, Player py) {
        panel = p;
        player = py;
        g2 = (Graphics2D) panel.getGraphics();
        backgroundColor = panel.getBackground();
        dimension = panel.getSize();

        x = ((dimension.width / 2) - (SIZE / 2));
        y = (dimension.height / 12) * 11;
    }

    public void draw() {
        g2.setColor(Color.YELLOW);
        g2.fill(new Rectangle2D.Double(x, y, SIZE, SIZE * 2));
    }

    private Rectangle2D.Double getBoundingRectangle() {
        return new Rectangle2D.Double(x, y, SIZE, SIZE * 2);
    }

    private boolean playerHitsTrophy(){
        Rectangle2D.Double playerHitBox = player.getBoundingRectangle();
        Rectangle2D.Double trophyHitBox = getBoundingRectangle();
        return playerHitBox.intersects(trophyHitBox);
    }

    public void move(){
        if(!panel.isVisible()) return;
        if (playerHitsTrophy() && !used) {
            used = true;
        }
    }

    public boolean isUsed() {
        return used;
    }
}
