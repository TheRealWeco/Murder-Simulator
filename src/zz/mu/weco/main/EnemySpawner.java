package zz.mu.weco.main;

public class EnemySpawner {

	public boolean shouldSpawn = true;
	
	int x;
	int y;
	
	int time;
	
	int i = 0;
	
	Enity_Enemy_1 enemy;
	
	public EnemySpawner (int x, int y, Enity_Enemy_1 enemy, int time){
		this.x = x;
		this.y = y;
		this.enemy = enemy;
		this.time = time;
	}
	public EnemySpawner (Enity_Enemy_1 enemy, int time){
		this.enemy = enemy;
		this.time = time;
	}
	public void spawn(){
		if(i >= time){
		Main.frm.enemyWalking.add(enemy);
		i = 0;
		}else{
			i++;
		}
	}
	
}
