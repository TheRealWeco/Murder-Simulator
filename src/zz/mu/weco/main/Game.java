package zz.mu.weco.main;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Game {
public static String version = "1.0";
public static String name = "game";
public BufferedImage getSpriteSheet(){
	try {
		return ImageIO.read(getClass().getClassLoader().getResourceAsStream("pic\\SpriteSheet.png"));
	} catch (IOException e) {
		e.printStackTrace();
	}
	return null;
}
public BufferedImage getBulletSheet(){
	try {
		return ImageIO.read(getClass().getClassLoader().getResourceAsStream("pic\\BulletSheet.png"));
	} catch (IOException e) {
		e.printStackTrace();
	}
	return null;
}
public static synchronized void playSound(final String titel){
	new Thread(new Runnable() {
		
		@Override
		public void run() {
			try {
				Clip clip = AudioSystem.getClip();
				AudioInputStream inputStream = AudioSystem.getAudioInputStream(getClass().getClassLoader().getResourceAsStream("sfx\\" + titel));
				clip.open(inputStream);
				clip.start();
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			} catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}).start();
}
}
