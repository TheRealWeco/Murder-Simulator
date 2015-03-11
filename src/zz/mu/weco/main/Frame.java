package zz.mu.weco.main;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Frame extends JFrame implements MouseListener{
	
	paint zeichnen;
	Player player;
	backround bg;
	public Enity_Enemy_1 enemyWalk;
	EnemySpawner spawn;
	
	ArrayList enemyWalking;
	public Frame(Player p, backround backround){
		super(Game.name + " V." + Game.version);
		
		enemyWalking = new ArrayList();
		
		player = p;
		bg = backround;
		zeichnen = new paint();
		zeichnen.setBounds(0, 0, 800, 600);
		addKeyListener(new keyCheck());
		add(zeichnen);
		
		enemyWalk = new Enity_Enemy_1(500, 450, Main.game.getSpriteSheet().getSubimage(250, 0, 50, 50), "j_a_m(p)", 
				Main.game.getSpriteSheet().getSubimage(250, 50, 50, 50), "pirates_death.wav");
		
		enemyWalking.add(enemyWalk);
		spawn = new EnemySpawner(enemyWalk, 200);//
	      /*addMouseListener(new MouseAdapter() { 
	          public void mousePressed(MouseEvent me) { 
	        	  player.fire();
	        	  
	  			ArrayList bullets = player.getShots();
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
	        }); */
		addMouseListener(this);

	}
	
	public void repaintScreen(){
		zeichnen.repaint();
	}
	
	public void update() {
		
		spawn.spawn();
		
		if(enemyWalking != null && !(enemyWalking.size() == 0)){
		for(int w = 0; w < enemyWalking.size(); w++){
			Enity_Enemy_1 m = (Enity_Enemy_1) enemyWalking.get(w);
			if(m.isAlive){
				m.move(0.4F);
			}else{
			}
		}
		}
	}

	@SuppressWarnings("serial")
	private class paint extends JLabel{
		@Override
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			
			g.drawImage(bg.getBgImage1(), bg.getBounding().x, bg.getBounding().y, null);
			//g.drawImage(player.getPlayerImage(), player.getBounding().x, player.getBounding().y, null);
			if(player.bigLeft){
				g.drawImage(player.getPlayerImage(), player.getBounding().x - 50, player.getBounding().y, null);
			}else{
			g.drawImage(player.getPlayerImage(), player.getBounding().x, player.getBounding().y, null);
			}
			//if(enemyWalk.isAlive){
			//g.drawImage(enemyWalk.enemyImage, enemyWalk.x, enemyWalk.y, null);
			//}
			//e.draw(g);
			
			@SuppressWarnings("rawtypes")
			ArrayList bullets = player.getShots();
			if(bullets != null && !(bullets.size() == 0)){
			for(int w = 0; w < bullets.size(); w++){
				Shot m = (Shot) bullets.get(w);
				g.drawImage(m.bulletImg, m.x, m.y, null);
			}
			}
			
			if(enemyWalking != null && !(enemyWalking.size() == 0)){
			for(int w = 0; w < enemyWalking.size(); w++){
				Enity_Enemy_1 m = (Enity_Enemy_1) enemyWalking.get(w);
				if(m.isAlive){
				g.drawImage(m.enemyImage, (int)m.x,(int) m.y, null);
				}else{
					enemyWalking.remove(w);
				}
			}
			}
			//g.setColor(Color.ORANGE);
			//g.fillRect(player.getBounding().x, player.getBounding().y, player.getBounding().width, player.getBounding().height);
			//g.drawRect(player.getBounding().x, player.getBounding().y, player.getBounding().width, player.getBounding().height);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
  	  player.fire();
	  
		ArrayList bullets = player.getShots();
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

	@Override
	public void mouseEntered(MouseEvent e) {		
	}

	@Override
	public void mouseExited(MouseEvent e) {		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		player.alwaysFire = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		player.alwaysFire = false;
		
	}

	public void init() {
		
	}
	
}

