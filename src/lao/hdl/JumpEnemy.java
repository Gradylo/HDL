package lao.hdl;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

public class JumpEnemy extends Enemy {
	private Ammo a;
	private  int WALKSPEED = 2;
	public Image[] JumpEnemyL = new Image[] { Imag.JumpEnemyL0, Imag.JumpEnemyL1, Imag.JumpEnemyL2, Imag.JumpEnemyL3 };
	public Image[] JumpEnemyR = new Image[] { Imag.JumpEnemyR0, Imag.JumpEnemyR1, Imag.JumpEnemyR2, Imag.JumpEnemyR3 };
	public Image[] JumpEnemyBoomL = new Image[] { Imag.EnemyDL, Imag.EnemyBoom1, Imag.EnemyBoom2, Imag.EnemyBoom3 };
	public Image[] JumpEnemyBoomR = new Image[] { Imag.EnemyDR, Imag.EnemyBoom1, Imag.EnemyBoom2, Imag.EnemyBoom3 };
	public Image JumpEnemyLS = Imag.JumpEnemyLS;
	public Image JumpEnemyRS = Imag.JumpEnemyRS;
	private static int JUMP_HEIGHT = 0;
	public boolean jumping = false;//跳跃状态
	public boolean jumpFinished = true;//非跳跃状态
	
	
	
	public JumpEnemy(int x, int y) {
		image = Imag.JumpEnemyL0;
		this.x = x;
		this.y = y;
		width = image.getWidth(null);
		height = image.getHeight(null);
	}


	int num = 0;
	//当英雄出现在视野的时候前进，当英雄在射程范围内则射击
	public void ShootHero(Hero hero) {
		num++;
		int x = this.x;
		int y = this.y;
		if (hero.x <= this.x + this.width / 2&&
						hero.y >= this.y && hero.y <= this.y + this.height) {// 左边
				WALKSPEED=0;
				dir = 1;// 向左射击
		}	
			
		else if (hero.x-hero.width > this.x + this.width / 2&&
						hero.y > this.y && hero.y <= this.y + this.height) {// 右边
				WALKSPEED=0;
				dir = 4;// 向右射击
		}else{
			WALKSPEED=2;
			dir=0;//向左跑
			this.x-=WALKSPEED;			
		}
		if(dir==1||dir==4){
		switch (dir) {
		case 1:
			y = this.y + 18;
			break;
		case 4:
			x=this.x+this.width;
			y = this.y + 18;
			break;
		}
		a = new Ammo(x, y, dir);
		a.GunShoot(1);
		if (num % 100 == 0 && Math.abs(hero.x - this.x) < 600&&this.x+this.width<PlayGame.WIDTH) {// 见到英雄才射击
			PlayGame.jumpenemyammos.add(a);
			num = 0;
		}
		}
	}
	
	
	static int time=0;
	public static void CreateJumpEnemy(){//创建敌人
		 time++;
		if(time%50==0){
			int n=new Random().nextInt(10);
			time=0;
			if(n<3&&(PlayGame.jumpenemy.size()<5||PlayGame.jumpenemy.size()==0)){
				PlayGame.jumpenemy.add(new JumpEnemy(PlayGame.WIDTH+100,150)) ;				
			}
		}
	}
	
	public void step(){
		if(dir==0){//向左跑
			if (num % 10 == 0) {
				index++;
				image = JumpEnemyL[index % JumpEnemyL.length];
			}
		}
//		if(dir==3){//向右跑
//			if (num % 10 == 0) {
//				index++;
//				image = JumpEnemyR[index % JumpEnemyR.length];
//			}
//		}
		if(dir==1){
			image=JumpEnemyLS;
		}
		if(dir==4){
			image=JumpEnemyRS;
		}
	}
	
	
//	public void StepJump(){
//		new Thread(){
//			public void run() {
//				while(true){
//					try {
//						Thread.sleep(1000);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//					jumpFinished=false;
//					jumping=true;
//				}
//			};
//		}.start();
//	}

	public void jump(){

		if ((JUMP_HEIGHT < 30) && (!this.jumpFinished)) {
			G=2;
			JUMP_HEIGHT += 1;
			this.y -= G;
		}else{
			jumpFinished=true;
			JUMP_HEIGHT=0;
			this.y+=G;
		}
	}
	
	int n=0;//设定英雄在草坪上的个数
	public void Gravity(ArrayList<Barrier> barrier) {//在草坪移动
		Barrier b=null;
		for(Barrier ba:barrier){
			if(ba.EnemyonBarrier(this)){
				b=ba;
				n++;
			}
		}
		if(n>=1){//在草坪上
			G=0;
			onGravity=true;
			
			if(jumpFinished){
				jumping=false;
			}
			
		}else{
			onGravity=false;
			G=2;
		}
		n=0;
		}
	
	
}
