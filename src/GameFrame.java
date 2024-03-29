import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameFrame extends JFrame implements ActionListener {
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
}