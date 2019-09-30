import javax.swing.*;

public class ScorePanel extends JPanel implements Runnable {
    private Thread scoreThread;
    private JLabel scoreLabel = new JLabel("Score: " + Score.getScore());
    private boolean isRunning = false;

    public ScorePanel() {
        scoreLabel.setFocusable(true);
        scoreLabel.requestFocus();
        add(scoreLabel, "Center");
    }

    @Override
    public void run() {
        isRunning = true;
        while (isRunning) {
            scoreLabel.setText("Score: " + Score.getScore());

            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void startGame() {
        if (scoreThread == null) {
            isRunning = true;
            scoreThread = new Thread(this);
            scoreThread.start();
        }
    }

    public void endGame() {
        if (isRunning) {
            isRunning = false;
        }
    }
}

