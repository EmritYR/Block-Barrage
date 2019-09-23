import java.awt.event.*;
import java.awt.Color;
import javax.swing.*;

/**
 * GamePanel
 */
public class GamePanel extends JPanel implements Runnable, KeyListener {
    private Player player;
    private  Block block;

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
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
        }
    }

    public void keyPressed(KeyEvent e) {
        if (player == null)
            return;

        int keyCode = e.getKeyCode();

        switch (keyCode){
			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_A :
				player.move("LEFT");
				break;
			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_D :
				player.move("RIGHT");
				break;
			case KeyEvent.VK_UP:
			case KeyEvent.VK_W :
				player.move("UP");
				break;
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_S :
				player.move("DOWN");
				break;
		}
    }

    public void keyReleased(KeyEvent e) {

    }

    public void keyTyped(KeyEvent e) {

    }

    private void gameUpdate() {
        block.move();
    }

    private void gameRender() {
        player.draw();
        block.draw();
    }

    public void startGame() {
        if (gameThread == null) {
            isRunning = true;
            player = new Player(this);
            block = new Block(this);
            gameThread = new Thread(this);
            gameThread.start();
        }
    }

    public void endGame() {
        if (isRunning) {
            isRunning = false;
        }
    }
}