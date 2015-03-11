package zz.mu.weco.main;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class Player{
	
	Rectangle bounding;
	
	float ply_x;
	float ply_y;
	
	float f_dx;
	float f_dy;
	float f_gravity;
	
	float f_counter;
	float neededTime;
	
	boolean b_Air = false;
	boolean stand = true;
	
	BufferedImage playerImage;
	BufferedImage[] lauf;
	//BufferedImage shotImg;
	BufferedImage[] shotImgs;
	boolean d = true;
	boolean bigLeft;

	@SuppressWarnings("rawtypes")
	ArrayList Shots;
	boolean alwaysFire = false;
	int shotCounter;
	boolean shoot = false;
	int s_counter;
	int s_neededTime;
	boolean shoot2 = false;
	public Shot shot;

	public float dex = 0.1F;
	
	public Player(){
		
		Shots = new ArrayList<>();
		
		lauf = new BufferedImage[8];
		shotImgs = new BufferedImage[2];
				
		ply_x = 200;
		ply_y = 450;
		
		f_dx = 0.4F;
		f_dy = -100;
		f_gravity = 0.5F;
		
		f_counter = 0;
		neededTime = 150;
		
		shotCounter = 75;
		s_counter = 0;
		s_neededTime = 20;

		
		try {
			playerImage = ImageIO.read(getClass().getClassLoader().getResourceAsStream("pic\\SpriteSheet.png")).getSubimage(0, 0, 50, 50);
			
			lauf[0] = ImageIO.read(getClass().getClassLoader().getResourceAsStream("pic\\SpriteSheet.png")).getSubimage(0, 0, 50, 50);
			lauf[1] = ImageIO.read(getClass().getClassLoader().getResourceAsStream("pic\\SpriteSheet.png")).getSubimage(50, 0, 50, 50);
			lauf[2] = Main.game.getSpriteSheet().getSubimage(0, 50, 50, 50);
			lauf[3] = Main.game.getSpriteSheet().getSubimage(50, 50, 50, 50);
			lauf[4] = Main.game.getSpriteSheet().getSubimage(100, 0, 50, 50);
			lauf[5] = Main.game.getSpriteSheet().getSubimage(100, 50, 50, 50);
			lauf[6] = Main.game.getSpriteSheet().getSubimage(150, 0, 100, 50);
			lauf[7] = Main.game.getSpriteSheet().getSubimage(150, 50, 100, 50);

			shotImgs[0] = Main.game.getBulletSheet().getSubimage(0, 0,  75, 13);
			shotImgs[1] = Main.game.getBulletSheet().getSubimage(0, 13,  75, 13);

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		bounding = new Rectangle((int)ply_x, (int)ply_y, playerImage.getWidth(), playerImage.getHeight());
		
	}
	
	public void update(){
		

		
		shotCounter--;
		if(Shots.size() > 0){
  			ArrayList bullets = getShots();
			if(bullets != null || bullets.size() == 0){
			for(int w = 0; w < bullets.size(); w++){
				Shot m = (Shot) bullets.get(w);
				if(m.visible){
				m.move();
				}else{
					bullets.remove(w);
				}
			}
			}
		}
		if(alwaysFire){
			fire();
			}
		if(!shoot){
		if(stand){
			if(d){
				bigLeft = false;
			playerImage = lauf[0];
			}else{
				bigLeft = false;
				playerImage = lauf[2];
			}
		}else{
			if(d){
		if(f_counter >= neededTime * 1.5F){
			f_counter = 0;
		}
		if(f_counter == 0){
			bigLeft = false;
			playerImage = lauf[0];
			f_counter++;
		}else if(f_counter == 75){
			bigLeft = false;
			playerImage = lauf[1];
			f_counter++;
		}else{
			f_counter++;
		}
			}else{
				if(f_counter >= neededTime * 1.5F){
					f_counter = 0;
				}
				if(f_counter == 0){
					bigLeft = false;
					playerImage = lauf[2];
					f_counter++;
				}else if(f_counter == 75){
					bigLeft = false;
					playerImage = lauf[3];
					f_counter++;
				}else{
					f_counter++;
				}
			}
		}
		}else{
			if(s_counter == 1){
				bigLeft = false;
				if(d){
				playerImage = lauf[4];
				}else{
					playerImage = lauf[5];
				}
			}
			if(s_counter == 15){
				if(d){
					bigLeft = false;
					playerImage = lauf[6];
					}else{
						bigLeft = true;
						playerImage = lauf[7];
					}
				if(d){
				shot = new Shot(ply_x + 10, ply_y + 20, true, shotImgs[0], 10, shotImgs[1]);
				}else{
				shot = new Shot(ply_x + 10, ply_y + 20, false, shotImgs[0], 10, shotImgs[1]);
				}
				Main.game.playSound("magicShoot.wav");
				Shots.add(shot);
				shotCounter = 100;
			}
			if(s_counter >= 30){
				bigLeft = false;
				s_counter = 0;
				shoot = false;
			}
			/*if(f_counter == 50){
				if(d){
				playerImage = lauf[6];
				}else{
					playerImage = lauf[7];
				}
				shoot = false;
			}*/
			s_counter++;
		}
		
		
		
		if(getBounding().x >= 740){
			
			ply_x = 739;
		}
		if(getBounding().x <= 0){
			ply_x = 0;
		}
				
		if(keyCheck.keysCheck(KeyEvent.VK_A)){
			ply_x -= f_dx;
			
			/*try {
				//playerImage = ImageIO.read(getClass().getClassLoader().getResourceAsStream("pic\\SpriteSheet.png")).getSubimage(50, 50, 50, 50);
			} catch (IOException e) {
				e.printStackTrace();
			}*/
			
		}
		if(keyCheck.keysCheck(KeyEvent.VK_D)){
			ply_x += f_dx;
			
			/*try {
				playerImage = ImageIO.read(getClass().getClassLoader().getResourceAsStream("pic\\SpriteSheet.png")).getSubimage(50, 0, 50, 50);
			} catch (IOException e) {
				e.printStackTrace();
			}*/

		}
		if(keyCheck.keysCheck(KeyEvent.VK_SPACE)){
			b_Air = true;
			
			//if(ply_y == 450){
			//	f_dy = -9;
			//	b_Air = false;
			//}
			//f_dy += f_gravity;
			//ply_y += f_dy;
		}
		
		
		float time = 0.01F;
		if(b_Air == true){
			
			if(ply_y == 450){
				f_dy = -100;
				b_Air = false;
			}
			f_dy += f_gravity;
			ply_y += f_dy * time;
		}
		
		bounding.x = (int)ply_x;
		bounding.y = (int)ply_y;
		
	}
	public void setPlayerImage(BufferedImage newImage){
		playerImage = newImage;
	}
	public Rectangle getBounding(){
		return bounding;
	}
	public BufferedImage getPlayerImage(){
		return playerImage;	
	}
	public void stand(boolean stay){
		stand = stay;
	}

	public void fire() {
		if(shotCounter - dex < -2){
			
			if(s_counter >= 30){
			s_counter = 0;
			}
			shoot = true;
		/*	
		Shot shot;
		if(d){
		shot = new Shot(ply_x + 10, ply_y + 20, true, shotImgs[0], 10, shotImgs[1]);
		}else{
		shot = new Shot(ply_x + 10, ply_y + 20, false, shotImgs[0], 10, shotImgs[1]);
		}
		Shots.add(shot);
		shotCounter = 100;
		}
		*/
		}
	}
	@SuppressWarnings("rawtypes")
	public ArrayList getShots(){
		return Shots;
	}
}
