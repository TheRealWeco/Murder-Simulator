package zz.mu.weco.main;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Shot {
	
	int x;
	int y;
	public BufferedImage bulletImg;
	BufferedImage bulletImg2;
	int moveSpeed;
	
	boolean visible = true;
	
	boolean right;
	
	public Shot(float f, float g, boolean right, BufferedImage img, int speed, BufferedImage img2){
		this.x = (int) f;
		this.y = (int) g;
		bulletImg = img;
		moveSpeed = speed;
		this.right = right;
		//this.bulletImg2 = img2;
		if(right){
			
		}else{
			bulletImg = img2;
		}
	}
	public Rectangle getBounds(){
		return new Rectangle(x, y, bulletImg.getHeight(), bulletImg.getWidth());
	}
	public void move(){
		if(right){
		x = x + moveSpeed;
		}else{
			x = x - moveSpeed;
		}
		
		if(x > 800){
			visible = false;
		}
		if(x < 0){
			visible = false;
		}
	}
}
