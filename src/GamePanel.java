import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable, KeyListener {
    private Player player;
    private ArrayList<Block> blocks = new ArrayList<>();
    private Life life = null;

    private final int UPDATE_TIME = 50;
    private final int SPAWN_X_BLOCKS = 3;
    private Thread gameThread;
    private boolean isRunning;
    private  Graphics2D g2;
    private Dimension dimension;

    private int score = 0;
    private int updater = 0;

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
            // Move All Blocks
            for (Block block : blocks) {
                System.out.println(blocks.size());
                block.move();
            }

            // Adds New Block to Screen
            if (score % 15 == 0){
                for (int i = 0; i < SPAWN_X_BLOCKS ; i++) {
                    blocks.add(new Block(this, player));
                }
            }

            // Extra Life Power UP Controller
            if (score % 100 == 0 && life != null)
                life =  new Life(this, player);
            if (life != null){
                life.move();
                if (life.isUsed())
                    life = null;
            }

//            System.out.println(score);
            score++;
            updater = 0;
        } else {
            updater++;
        }

        if (Player.getLives() < 1)
            isRunning = false;
    }

    private void gameRender() {
        clearPanel();
        player.draw();
        for (Block block : blocks) {
            block.draw();
        }

        if(life != null)
            life.draw();
    }
    private void clearPanel(){
        Graphics g = this.getGraphics();
        g2 = (Graphics2D) g;
        g2.setColor(this.getBackground());
        dimension = this.getSize();
        g2.fill(new Rectangle2D.Double(0, 0, dimension.width, dimension.height ));
    }


    public void startGame() {
        if (gameThread == null) {
            isRunning = true;
            player = new Player(this);
            blocks.add(new Block(this, player));
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