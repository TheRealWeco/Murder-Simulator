package zz.mu.weco.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.imageio.ImageIO;

public class keyCheck implements KeyListener{

	private static boolean keys[] = new boolean[100];
	
	public static boolean keysCheck(int keyCode){
		if((keyCode >= 0) && (keyCode < keys.length)){
			return keys[keyCode];
			
		}else{
			return false;
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		
		if((keyCode >= 0) && (keyCode < keys.length)){
			keys[keyCode] = true;
		}
		
		if(keyCode == e.VK_D || keyCode == e.VK_A){
			Main.player.stand = false;
		}
		
		if(keyCode == e.VK_D){
			Main.player.d = true;
		}
		if(keyCode == e.VK_A){
			Main.player.d = false;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if((keyCode >= 0) && (keyCode < keys.length)){
			keys[keyCode] = false;
		}
		
		if(keyCode == e.VK_D || keyCode == e.VK_A){
			Main.player.stand = true;
		}
		
		/*if(keyCode == e.VK_D){
			Main.player.d = false;
		}
		if(keyCode == e.VK_A){
			Main.player.d = true;
		}*/

	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
