import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Sound {
    private Clip clip;
    public void setFile(int i){
        try {
            //AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            AudioInputStream ais;
            if(i == 0) {
                ais = AudioSystem.getAudioInputStream(new File("src/resources/sound/Game.wav").getAbsoluteFile());
            }
            else{
                ais = AudioSystem.getAudioInputStream(new File("src/resources/sound/Laser.wav").getAbsoluteFile());
            }
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e){
            System.out.println("Hiba a zene betoltesekor!");
        }
    }
    public void play(){
        clip.start();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }
}
