import java.awt.event.ActionEvent;
import java.io.File;

import java.io.FileInputStream;
import java.io.InputStream;
import javafx.scene.media.MediaPlayer;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.swing.*;
import sun.audio.AudioPlayer;

public class Sound extends Main {

	public static void playMusic(String filepath){
        
        try{
             String f ="RunAmok.wav";
             File musicFile = new File(f);
             AudioInputStream aud=AudioSystem.getAudioInputStream(musicFile);
             Clip clip =AudioSystem.getClip();
             clip.open(aud);
             clip.start();
             

       } catch(Exception e1){
            e1.printStackTrace();
          }
   }
	


}
   