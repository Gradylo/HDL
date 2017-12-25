package lao.hdl;
 
 import java.io.FileInputStream;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
 
 public class BGM implements Runnable
 {
   public AudioStream as;
   public boolean playSound = true;
 
   public void run() {
	 if(PlayGame.state==PlayGame.RUNNING)
     playBackgroundSound();
   }
 
   private void playBackgroundSound() {
     while (true)
       try {
         this.as = new AudioStream(new FileInputStream("audios/bgsound.mid"));//bgsound.mid
         AudioPlayer.player.start(this.as);
         Thread.sleep(152000L);
       } catch (Exception e) {
         e.printStackTrace();
       }
   }
 }

