package lao.hdl;

import java.awt.Image;
import java.util.ArrayList;

import lao.hdl.Hero.Dir;


public class Enemy implements Runnable{
	public int x;
	public int y;
	public int width;
	public int height;
	public Image image;
	public Image[] images;
	public int life;
	public boolean isLive = true;
	public int index = 0;
	boolean flag = false;
	boolean boom;
	public int dir;// 方向
	public int G=2;
	public boolean onGravity;
	
	public Image[] EnemyBoomL = new Image[] { Imag.EnemyDL, Imag.EnemyBoom1, Imag.EnemyBoom2, Imag.EnemyBoom3 };
	public Image[] EnemyBoomR = new Image[] { Imag.EnemyDR, Imag.EnemyBoom1, Imag.EnemyBoom2, Imag.EnemyBoom3 };

	public boolean hit(ArrayList<Ammo> Ammo) {//受到伤害
		try{
		for(int i=0;i<Ammo.size();i++){
			Ammo ammo=Ammo.get(i);
			int x1 = ammo.x - this.width;
			int x2 = ammo.x + ammo.width;
			int y1 = ammo.y - this.height;
			int y2 = ammo.y + ammo.height;
			int x = this.x;
			int y = this.y;
			if (x >= x1 && x <= x2 && y >= y1 && y <= y2) {
				ammo.flag = true;
				flag = true;
			}
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}

	public void Hitboom() {
				try {
					if (dir < 3) {
						for (int i = 0; i < EnemyBoomL.length; i++) {
							image = EnemyBoomL[i];
							Thread.sleep(50);
						}
					} else {
						for (int i = 0; i < EnemyBoomR.length; i++) {
							image = EnemyBoomR[i];
							Thread.sleep(50);
						}
					}
					Thread.sleep(50);
					isLive = false;
				} catch (Exception e) {
					e.printStackTrace();
				}
	}
	
	public void outOfBounds(){
		if(this.x<0||this.y>PlayGame.HEIGHT){
			isLive=false;
		}
	}

	public void run() {
		Hitboom();
	}
	
	
	
}
