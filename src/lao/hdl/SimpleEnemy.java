package lao.hdl;

import java.awt.Image;

public class SimpleEnemy extends Enemy {

	private Ammo a;
	
	public Image[] EnemyL = new Image[] { Imag.EnemyL0, Imag.EnemyL1, Imag.EnemyL2 };
	public Image[] EnemyR = new Image[] { Imag.EnemyR0, Imag.EnemyR1, Imag.EnemyR2 };
	

	public SimpleEnemy(int x, int y) {
		image = Imag.EnemyL1;
		this.x = x;
		this.y = y;
		width = image.getWidth(null);
		height = image.getHeight(null);
	}

	int num = 0;
	public void ShootHero(Hero hero) {//射击英雄
		num++;
		int x = this.x;
		int y = this.y;
		if (hero.x < this.x + this.width / 2) {// 左边
			if (hero.y + hero.height < this.y) {
				dir = 0;// 向上
			}
			if (hero.y >= this.y && hero.y <= this.y + this.height) {
				dir = 1;// 向前
			}
			if (hero.y > this.y + this.height) {
				dir = 2;// 向下
			}
		}
		if (hero.x > this.x + this.width / 2) {// 右边
			if (hero.y + hero.height < this.y) {
				dir = 3;// 向上
			}
			if (hero.y > this.y && hero.y < this.y + this.height) {
				dir = 4;// 向前
			}
			if (hero.y > this.y + this.height) {
				dir = 5;// 向下
			}
		}
		switch (dir) {
		case 1:
			y = this.y + 26;
			break;
		case 2:
			y = this.y + 60;
			break;
		case 3:
			x = this.x + 61;
			break;
		case 4:
			x = this.x + 81;
			y = this.y + 25;
			break;
		case 5:
			x = this.x + 75;
			y = this.y + 60;
			break;
		}
		a = new Ammo(x, y, dir);
		a.GunShoot(1);
		if (num % 100 == 0 && Math.abs(hero.x - this.x) < PlayGame.WIDTH) {// 见到英雄才射击
			PlayGame.enemyammos.add(a);
			num = 0;
		}
	}

	public void step() {// 切换图片
		switch (dir) {
		case 0:
			image = EnemyL[2];
			break;
		case 1:
			image = EnemyL[1];
			break;
		case 2:
			image = EnemyL[0];
			break;
		case 3:
			image = EnemyR[2];
			break;
		case 4:
			image = EnemyR[1];
			break;
		case 5:
			image = EnemyR[0];
			break;
		}
	}

	public static void Reset(){
		PlayGame.enemy.add(new SimpleEnemy(2978,260));
		PlayGame.enemy.add(new SimpleEnemy(3190,260));
		PlayGame.enemy.add(new SimpleEnemy(3930,260));
		PlayGame.enemy.add(new SimpleEnemy(970,580));
		PlayGame.enemy.add(new SimpleEnemy(1700,270));
		PlayGame.enemy.add(new SimpleEnemy(1928,580));
		PlayGame.enemy.add(new SimpleEnemy(2980,260));
		PlayGame.enemy.add(new SimpleEnemy(4163,258));
		PlayGame.enemy.add(new SimpleEnemy(4980,418));
		PlayGame.enemy.add(new SimpleEnemy(5330,365));
		PlayGame.enemy.add(new SimpleEnemy(5836,152));
		PlayGame.enemy.add(new SimpleEnemy(6368,472));
		PlayGame.enemy.add(new SimpleEnemy(6687,472));
		PlayGame.enemy.add(new SimpleEnemy(7847,470));
		PlayGame.enemy.add(new SimpleEnemy(8703,370));
		PlayGame.enemy.add(new SimpleEnemy(9762,369));
		PlayGame.enemy.add(new SimpleEnemy(10293,260));
		PlayGame.enemy.add(new SimpleEnemy(10293,420));
		PlayGame.enemy.add(new SimpleEnemy(10293,580));
	}
	
	
	public boolean isLive(){
		return isLive;
	}
	
}
