package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {
    Clip clip;
    URL soundURL[]=new URL[30];
    public Sound()
    {
        soundURL[0]=getClass().getResource("/sound/NOVA.wav");
        soundURL[1]=getClass().getResource("/sound/coin.wav");
        soundURL[2]=getClass().getResource("/sound/powerup.wav");
        soundURL[3]=getClass().getResource("/sound/blocked.wav");
        soundURL[4]=getClass().getResource("/sound/FinalBattle.wav");
        soundURL[5]=getClass().getResource("/sound/retroMain.wav");
        soundURL[6]=getClass().getResource("/sound/sweetPause.wav");
        soundURL[7]=getClass().getResource("/sound/Title.wav");

    }
    public void setFIle(int i)
    {
        try {
            AudioInputStream ais= AudioSystem.getAudioInputStream(soundURL[i]);
            clip=AudioSystem.getClip();
            clip.open(ais);
        }catch (Exception e)
        {
        }
    }
    public void play()
    {
        clip.start();
    }
    public void loop()
    {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop()
    {
        clip.stop();
    }
}
