import java.awt.event.*;
import java.awt.Color;
import javax.swing.*;

/**
 * GamePanel
 */
public class GamePanel extends JPanel implements Runnable, KeyListener {
    private Player player;

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
                Thread.sleep(1);
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
				player.moveLeft();
				break;
			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_D :
				player.moveRight();
				break;
			case KeyEvent.VK_UP:
			case KeyEvent.VK_W :
				player.moveUp();
				break;
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_S :
				player.moveDown();
				break;
		}
    }

    public void keyReleased(KeyEvent e) {

    }

    public void keyTyped(KeyEvent e) {

    }

    private void gameUpdate() {

    }

    private void gameRender() {
        player.draw();
    }

    public void startGame() {
        if (gameThread == null) {
            isRunning = true;
            player = new Player(this);
            // ball = new Ball (this, bat);
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