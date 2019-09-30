import javax.swing.*;
import java.awt.*;

public class Score extends JComponent {
    private static int score = 1;

    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        Score.score = score;
    }

    public void incrementScore(){
        score++;
    }
}
