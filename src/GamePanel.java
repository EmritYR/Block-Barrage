import com.sun.deploy.util.UpdateCheck;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable, KeyListener {
    private Player player;
    private ArrayList<Block> blocks = new ArrayList<>();
    private int score = 0;
//    private Score score;

    private Thread gameThread;
    private boolean isRunning;

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
                Thread.sleep(300);
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
        for (Block block : blocks) {
            block.move();
        }

        if (score % 15 == 0)
            blocks.add(new Block(this, player));

        score++;

        if (Player.getLives() < 1)
            isRunning = false;
    }

    private void gameRender() {
        player.draw();
        for (Block block : blocks) {
            block.draw();
        }
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