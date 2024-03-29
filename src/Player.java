import javax.swing.JPanel;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;


public class Player {
    private JPanel panel;
    private Dimension dimension;
    private Graphics2D g2;
    private BufferedImage image;

    private static final int SIDE_SIZE = Constants.PLAYER_SIZE;
    private static final int SPEED = Constants.PLAYER_SPEED;
    private static int lives = 3;
    private int invulnerable = 0;
    private int invulnerableCooldown = Constants.PLAYER_HIT_COOLDOWN;
    private SFX sfx = new SFX();

    private int x;
    private int y;


    public Player(JPanel p) {
        panel = p;
        g2 = (Graphics2D) panel.getGraphics();

        image = ImageLoader.loadImage("assets/images/player_head.png");

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
//            g2.drawImage(image, x, y, SIDE_SIZE, SIDE_SIZE, null);
        }

//         Show Collision Boundary
        if (Options.SHOW_PLAYER_HITBOX) {
            g2.setColor(Color.BLACK);
            g2.fill(new Rectangle2D.Double(x, y - (SIDE_SIZE * lives) - (SIDE_SIZE * lives / 3.5), SIDE_SIZE, (SIDE_SIZE * lives) + (lives * 2)));
        }
    }

    // Collision Box Slightly Smaller for Game Fairness Design
    public Rectangle2D.Double getBoundingRectangle() {
        return new Rectangle2D.Double(x, y - (SIDE_SIZE * lives) - (SIDE_SIZE * lives / 3.5), SIDE_SIZE, (SIDE_SIZE * lives) + (lives * 2));
    }

    public void decrementLives() {
        if (invulnerable == 0) {
            lives--;
            invulnerable = invulnerableCooldown;
            sfx.setFile("assets/sounds/block_bump.wav");
            sfx.play(0);
        } else {
            invulnerable--;
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
                if (y < (SIDE_SIZE* lives + (SIDE_SIZE*lives)/3))
                    y = (SIDE_SIZE* lives + (SIDE_SIZE*lives)/3);
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
                System.out.println(x);
                x = x + SPEED;
                if (x + SPEED >= dimension.width- SIDE_SIZE)
                    x = dimension.width - SPEED - SIDE_SIZE;
                break;
        }
    }

    public static int getLives() {
        return lives;
    }

    public void setCoordinates(int x, int y){
        this.x = x;
        this.y = y;
    }
}