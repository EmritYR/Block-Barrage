import javax.swing.*;
import java.awt.event.*;

public class GameFrame extends JFrame implements ActionListener, KeyListener {
    private GamePanel gamePanel;
    private ScorePanel scorePanel;

    public GameFrame() {
        setSize(400, 600);
        setTitle("Block Barrage");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gamePanel = new GamePanel();
        add(gamePanel, "Center");

        scorePanel = new ScorePanel();
        add(scorePanel, "North");

        JPanel buttonPanel = new JPanel();

        JButton startB = new JButton("Start Game");
        startB.addActionListener(this);
        buttonPanel.add(startB);

        JButton stopB = new JButton("Stop Game");
        stopB.addActionListener(this);
        buttonPanel.add(stopB);

        JButton closeB = new JButton("Close");
        closeB.addActionListener(this);
        buttonPanel.add(closeB);

        add(buttonPanel, "South");
    }


    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "Start Game":
                gamePanel.requestFocus();
                gamePanel.startGame();
                scorePanel.startGame();
                break;
            case "Stop Game":
                gamePanel.endGame();
                scorePanel.endGame();
                break;
            case "Close":
                setVisible(false);
                System.exit(0);
        }
    }

    @Override
    public void keyTyped(KeyEvent event) {
        int keyCode = event.getKeyCode();

        switch (keyCode){
            case KeyEvent.VK_ENTER:
                gamePanel.requestFocus();
                gamePanel.startGame();
                scorePanel.startGame();
                break;
            case KeyEvent.VK_ESCAPE:
                gamePanel.endGame();
                scorePanel.endGame();
                setVisible(false);
                System.exit(0);
                break;
        }
    }

    @Override
    public void keyPressed(KeyEvent event) {

    }

    @Override
    public void keyReleased(KeyEvent event) {

    }
}