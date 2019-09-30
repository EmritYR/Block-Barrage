import javax.swing.*;

public class Score extends JComponent {
    private static int score = 1;
    private static int level = 1;

    public static int getScore() {
        return score;
    }

    public void incrementScore() {
        score++;
    }

    public void incrementLevel() {
        level++;
    }

    public static int getLevel() {
        return level;
    }
}
