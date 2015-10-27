package com;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * Created by Dominic on 27-Oct-15.
 */
public class Soundboard {

    public void playSound(){
        try {
            AudioInputStream audioInputStream;
            audioInputStream = AudioSystem.getAudioInputStream(getClass().getClassLoader()
                    .getResourceAsStream("blip.wav"));
            final Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e){
            System.out.println(e);
        }
    }
}
