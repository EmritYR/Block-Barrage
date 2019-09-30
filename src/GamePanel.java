import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable, KeyListener {
    private Graphics2D g2;
    private Dimension dimension;

    private Player player;
    private ArrayList<Block> blocks = new ArrayList<>();
    private Life life = null;
    private Trophy trophy = null;
    private Score score = new Score();

    private final int UPDATE_TIME = Constants.UPDATE_TIME;
    private final int SPAWN_X_BLOCKS = Constants.SPAWN_X_BLOCKS;
    private Thread gameThread;
    private boolean isRunning;

//    private int score = 1;
    private int updater = 0;
    private int level = 1;

    private int lifeSpawnRate = Constants.LIFE_SPAWN_RATE;
    private int trophySpawnRate = Constants.TROPHY_SPAWN_RATE;
    private int blockSpawnRate = Constants.BLOCK_SPAWN_RATE;

    public GamePanel() {
        setBackground(Color.gray);
        addKeyListener(this);
        setFocusable(true);
        requestFocus();
        gameThread = null;
        isRunning = false;
    }

    public void run() {
        try {
            isRunning = true;
            while (isRunning) {
                gameUpdate();
                gameRender();
                Thread.sleep(5);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void keyPressed(KeyEvent e) {
        if (player == null)
            return;
        int keyCode = e.getKeyCode();

        switch (keyCode) {
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                player.move("LEFT");
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                player.move("RIGHT");
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                player.move("UP");
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                player.move("DOWN");
                break;
        }
    }

    public void keyReleased(KeyEvent e) {

    }

    public void keyTyped(KeyEvent e) {

    }

    private void gameUpdate() {
        if (updater == UPDATE_TIME) {
            // Block Updating Controller
            blockController();

            // Extra Life Power UP Controller
            lifeController();

            // Trophy Controller
            trophyController();

            score.incrementScore();
            updater = 0;
        } else {
            updater++;
        }

        if (Player.getLives() < 1)
            isRunning = false;
    }

    private void blockController() {
        // Adds New Block to Screen
        if (Score.getScore() % blockSpawnRate == 0) {
            addBlocks();
        }

        // Move All Blocks
        for (Block block : blocks) {
            block.move();
        }
    }

    private void lifeController() {
        if (Score.getScore() % lifeSpawnRate == 0 && life == null)
            life = new Life(this, player);
        if (life != null) {
            life.move();
            if (life.isUsed())
                life = null;
        }
    }

    private void trophyController() {
        if (Score.getScore() % trophySpawnRate == 0 && trophy == null)
            trophy = new Trophy(this, player);
        if (trophy != null) {
            trophy.move();
            if (trophy.isUsed()) {
                trophy = null;
                levelUp();
            }
        }
    }

    private void addBlocks() {
        for (int i = 0; i < SPAWN_X_BLOCKS; i++) {
            blocks.add(new Block(this, player));
        }
    }

    public void levelUp() {
        level++;
        Constants.SPAWN_X_BLOCKS += 1;
        Constants.BLOCK_SPEED += 3;
        System.out.println(level);
    }

    private void gameRender() {
        clearPanel();

        player.draw();

        for (Block block : blocks) {
            block.draw();
        }

        if (life != null)
            life.draw();

        if (trophy != null)
            trophy.draw();
    }

    private void clearPanel() {
        Graphics g = this.getGraphics();
        g2 = (Graphics2D) g;
        g2.setColor(this.getBackground());
        dimension = this.getSize();
        g2.fill(new Rectangle2D.Double(0, 0, dimension.width, dimension.height));
    }


    public void startGame() {
        if (gameThread == null) {
            isRunning = true;
            player = new Player(this);
            addBlocks();
            gameThread = new Thread(this);
            gameThread.start();
        }
    }

    public void endGame() {
        if (isRunning) {
            isRunning = false;
        }
    }

//    public void paint(Graphics g) {
//        Graphics2D g3 = (Graphics2D) g;
//
//        Font f = new Font("Times New Roman", Font.BOLD, 12);
//        g3.setFont(f);
//        g3.setColor(Color.WHITE);
//        g3.drawString(Integer.toString(score), 5, 5);
//    }
}