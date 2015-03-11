package zz.mu.weco.main;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Enity_Enemy_1{

	BufferedImage enemyImage;
	BufferedImage enemyImage1;
	BufferedImage enemyImage2;
	float x;
	float y;
	float defX;
	float defY;
	
	float f_dy;
	float f_gravity;
	
	String ai = "nul";
	
	public boolean isAlive = true;
	
	boolean shouldFinishJump = false;

	String soundClip;
	
	float time = 0.1F;
	
	public Enity_Enemy_1(float x, float y, BufferedImage img, String soundClip) {
		enemyImage = img;
		this.x = x;
		this.y = y;
		this.soundClip = soundClip;
		setup();
	}
	public Enity_Enemy_1(float x, float y, BufferedImage img, String ai, BufferedImage img2, String soundClip) {
		enemyImage1 = img;
		this.x = x;
		this.y = y;
		this.ai = ai;
		enemyImage2 = img2;
		this.soundClip = soundClip;
		setup();
	}
	
	void setup(){
		enemyImage = enemyImage1;
		f_dy = -15;
		defX = x;
		defY = y;
		f_gravity = Main.player.f_gravity;
	}
	public void kill(){
		Main.game.playSound(soundClip);
		isAlive = false;
	}
	//public void draw(Graphics g){
	//	g.drawImage(enemyImage, x, y, null);
	//}
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, enemyImage.getHeight(), enemyImage.getWidth());
	}
	
	public void move(float dx){
		if(ai.equals("nul")){
		x -= dx;
		if(x < 0){
			
			//TODO: Loose
			isAlive = false;
			
		}
	}else{
		
		boolean jump = false;
		boolean attack = false;
		boolean move = false;
		boolean moveToPlayer = false;
		
		if(ai.contains("_")){
			
			String ai_a = ai;
			String[] parts = ai_a.split("_");
			
			String ai1 = parts[0];
			String ai2 = parts[1];
			String ai3 = parts[2];


					/**if(ai1.equals("j")){
						jump = true;
					}**/
					if(ai2.equals("a")){
						attack = true;
					}
					if(ai3.equals("m")){
						move = true;
					}
					if(ai3.equals("m(p)")){
						moveToPlayer = true;
					}
			
		}
		
		if(move){
			x -= dx;
			if(x < 0){
				isAlive = false;
			}
		}
		
		if(moveToPlayer){
			if((int)x < (int)Main.player.ply_x){
				enemyImage = enemyImage2;
				x += dx;
			}else if((int)x == (int)Main.player.ply_x){
				
			}else{
				enemyImage = enemyImage1;
				x -= dx;
			}
		}
		
		
		//jump-ai
		/**if(jump){
				if(Main.player.b_Air){
					shouldFinishJump = true;
				}
				}
		if(shouldFinishJump){
			if(y == 450){
				f_dy = -15;
				shouldFinishJump = false;
			}
			f_dy += f_gravity;
			y += f_dy;

	}**/
		}
		
		
	}
	
}
