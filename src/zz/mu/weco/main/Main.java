package zz.mu.weco.main;

import zz.mu.weco.physics.Collisions;


public class Main implements Runnable{
	public static Player player;
	public static Game game;
	public static Collisions collisions;
	public static Frame frm;
	public static Main instance;
	
	static boolean Runnining = false;

	static backround bg_1;
public static void main (String[] args){
	
	instance = new Main();
	game = new Game();
	
	collisions = new Collisions();
	player = new Player();
	bg_1 = new backround();
	
	frm = new Frame(player, bg_1);
	frm.setLayout(null);
	frm.setSize(800, 600);
	frm.setVisible(true);
	frm.setDefaultCloseOperation(Frame.EXIT_ON_CLOSE);
	frm.setResizable(false);
	
	instance.start();	
}

public synchronized void start() {
	Runnining = true;
	new Thread(this).start();
}

public synchronized void stop() {
	Runnining = false;
}


@Override
public void run() {
	long lastTime = System.nanoTime();
	double nsPerTick = 1000000000D/60D;
	int ticks = 0;
	int frames = 0;
	
	long lasTimer = System.currentTimeMillis();
	double delta = 0;

	boolean shouldRender = true;
			
	frm.init();
	while(Runnining){
					
		long now = System.nanoTime();
		delta += (now - lastTime) / nsPerTick;
		lastTime = now;
		
		while (delta >= 0){
		ticks++;
		delta -= 1;
		shouldRender = true;
		}
		
		try {
			Thread.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if(shouldRender){
		frames++;
		
		frm.repaintScreen();
		frm.update();
		player.update();
		bg_1.update();
		collisions.checkCollisions();

		}
		
		if(System.currentTimeMillis() - lasTimer > 1000){
			lasTimer += 1000;
			System.out.println(frames + " : " + ticks);
			frames = 0;
			ticks = 0;
		}
	}
}

}
