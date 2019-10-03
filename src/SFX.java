import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.File;

public class SFX {
    private Clip clip = null;

    public void setFile(String track) {
        try {
            AudioInputStream sound = AudioSystem.getAudioInputStream(new File(track));
            clip = AudioSystem.getClip();
            clip.open(sound);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play(int i) {
        clip.setFramePosition(i);
        clip.start();
    }

    public void stop() {
        clip.stop();
        clip.close();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
}