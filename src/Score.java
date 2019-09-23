import javax.swing.*;
import java.awt.*;

public class Score extends JComponent {
    private static int score;

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        Font f = new Font("Times New Roman", Font.BOLD, 12);
        g2.setFont(f);
        g2.setColor(Color.WHITE);
        g2.drawString(Integer.toString(score), 5, 5);
    }

    public static int getScore() {
        System.out.println(score);
        return score;
    }

    public static void setScore(int score) {
        Score.score = score;
    }

    public void incrementScore(){
        score++;
    }
}
