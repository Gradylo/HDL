package lao.hdl;

import java.io.FileInputStream;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class BossBgm implements Runnable{
		public AudioStream as;
	    public boolean playSound = true;
	    
	   public void run() {
	     playSound();
	   }
	 
	   private void playSound() {
	     while (playSound)
	       try {
	         this.as = new AudioStream(new FileInputStream("audios/Bossbg.mid"));
	         AudioPlayer.player.start(this.as);
	         Thread.sleep(152000L);
	       } catch (Exception e) {
	         e.printStackTrace();
	       }
	   }
	
	
}
