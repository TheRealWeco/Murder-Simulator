package zz.mu.weco.physics;

import java.awt.Rectangle;
import java.util.ArrayList;

import zz.mu.weco.main.Main;
import zz.mu.weco.main.Shot;

public class Collisions {

	@SuppressWarnings({ "unused", "rawtypes" })
	public void checkCollisions(){
		Rectangle player = Main.player.getBounding();
		//Rectangle shoot = Main.player.shot.getBounds();	
		Rectangle enemy_walk = Main.frm.enemyWalk.getBounds();
		
		if(Main.frm.enemyWalk.isAlive){
		ArrayList bullets = Main.player.getShots();
		if(bullets != null && !(bullets.size() == 0)){
		for(int w = 0; w < bullets.size(); w++){
			Shot m = (Shot) bullets.get(w);
			Rectangle m1 = m.getBounds();
			
			if(enemy_walk.intersects(m1)){
				Main.frm.enemyWalk.kill();
			}
			
		}
		}
		}
		}
}
