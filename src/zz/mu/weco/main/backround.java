package zz.mu.weco.main;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class backround {
	
	BufferedImage bgImage_1;
	Rectangle bounding;
	Player player = Main.player;
	
	float bg_x;
	float bg_y;
	float f_dx;
	
	public backround(){
		
		bg_x = 0;
		bg_y = 0;
		f_dx = 5;
		
		try {
			bgImage_1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("pic\\backround_sand.png")).getSubimage(0, 0, 992, 600);
			bounding = new Rectangle((int)bg_x, (int)bg_y, bgImage_1.getWidth(), bgImage_1.getHeight());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update(){
		//bild - frame --> dann minus davor
		/*if(bg_x <= -180){
			bg_x = 0;
		}
		if(player.getBounding().x >= 740){
			player.ply_x += 100;
			bg_x -= f_dx;
		}
		bounding.x = (int)bg_x;
		bounding.y = (int)bg_y;*/
	}
	
	public BufferedImage getBgImage1(){
		return bgImage_1;
	}
	public Rectangle getBounding(){
		return bounding;
	}
}
