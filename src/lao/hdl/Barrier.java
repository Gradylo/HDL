package lao.hdl;

import java.awt.Image;
import java.util.ArrayList;

public class Barrier {
	public int x;
	public int y;
	public int width;
	public int height;
	public Image image;
	public boolean onBarrier;
	
	public static Image barrier = Imag.barrier;
	
	public Barrier() {
		image = Imag.Grass;
		this.x = 107;
		this.y = 350;
		width = image.getWidth(null);
		height = image.getHeight(null);
	}

	public Barrier(int x, int y) {
		image = Imag.Grass;
		this.x = x;
		this.y = y;
		width = image.getWidth(null);
		height = image.getHeight(null);
	}
	
	public Barrier(int x, int y,Image image) {
		this.image=image;
		this.x = x;
		this.y = y;
		width = image.getWidth(null);
		height = image.getHeight(null);
	}
	
	

	public ArrayList<Barrier> LongBarrier(int x, int y, int num) {// 长的地板
		ArrayList<Barrier> barrier = new ArrayList<Barrier>();
		for (int i = 1; i <= num; i++) {
			Barrier b = new Barrier(x, y);
			x += b.width;
			barrier.add(b);
		}
		return barrier;
	}

	// 判断英雄是否在障碍物上
	public boolean onBarrier(Hero hero) {
		if (this.x + this.width < PlayGame.WIDTH && this.x + this.width >= 0) {
			if (hero.isbL() && !hero.isbR()) {// 英雄向左边走
				if (this.x < hero.x + hero.width && this.x + this.width > hero.x + hero.width / 2
								&& this.y + 15 < hero.y + hero.height
								&& hero.y + hero.height < this.y + this.height / 2) {
					
					return true;
				}
			}
			if (!hero.isbL() && hero.isbR()) {// 英雄向右边走
				if (this.x < hero.x + hero.width / 2 && this.x + this.width > hero.x
								&& this.y + 15 < hero.y + hero.height
								&& hero.y + hero.height < this.y + this.height / 2) {
					return true;
				}
			}
			
			if (!hero.isbL() && !hero.isbR()||hero.isbL() && hero.isbR()) {// 英雄没有移动
				if (this.x < hero.x + hero.width && this.x + this.width > hero.x + hero.width / 2
								&& this.y + 15 < hero.y + hero.height
								&& hero.y + hero.height < this.y + this.height / 2) {
					return true;
				}
				if (this.x < hero.x + hero.width / 2 && this.x + this.width > hero.x
								&& this.y + 15 < hero.y + hero.height
								&& hero.y + hero.height < this.y + this.height / 2) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean EnemyonBarrier(Enemy enemy){
			if (enemy.dir==0) {// 英雄向左边走
				if (this.x < enemy.x + enemy.width && this.x + this.width > enemy.x + enemy.width / 2
								&& this.y + 15 < enemy.y + enemy.height
								&& enemy.y + enemy.height < this.y + this.height / 2) {
					
					return true;
				}
			}
			if (enemy.dir==1||enemy.dir==4) {// 英雄没有移动
				if (this.x < enemy.x + enemy.width && this.x + this.width > enemy.x + enemy.width / 2
								&& this.y + 15 < enemy.y + enemy.height
								&& enemy.y + enemy.height < this.y + this.height / 2) {
					return true;
				}
				if (this.x < enemy.x + enemy.width / 2 && this.x + this.width > enemy.x
								&& this.y + 15 < enemy.y + enemy.height
								&& enemy.y + enemy.height < this.y + this.height / 2) {
					return true;
				}
			}
		return false;
	}
	
	public boolean onBarrier(Hero2 hero2) {
		if (this.x + this.width < PlayGame.WIDTH && this.x > 0) {
			if (hero2.isbL() && !hero2.isbR()) {// 英雄向左边走
				if (this.x < hero2.x + hero2.width && this.x + this.width > hero2.x + hero2.width / 2
								&& this.y + 15 < hero2.y + hero2.height
								&& hero2.y + hero2.height < this.y + this.height / 2) {
					
					return true;
				}
			}
			if (!hero2.isbL() && hero2.isbR()) {// 英雄向右边走
				if (this.x < hero2.x + hero2.width / 2 && this.x + this.width > hero2.x
								&& this.y + 15 < hero2.y + hero2.height
								&& hero2.y + hero2.height < this.y + this.height / 2) {
					return true;
				}
			}
			
			if (!hero2.isbL() && !hero2.isbR()||hero2.isbL() && hero2.isbR()) {// 英雄没有移动
				if (this.x < hero2.x + hero2.width && this.x + this.width > hero2.x + hero2.width / 2
								&& this.y + 15 < hero2.y + hero2.height
								&& hero2.y + hero2.height < this.y + this.height / 2) {
					return true;
				}
				if (this.x < hero2.x + hero2.width / 2 && this.x + this.width > hero2.x
								&& this.y + 15 < hero2.y + hero2.height
								&& hero2.y + hero2.height < this.y + this.height / 2) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static  void ResetBarrier(){//重设位置
		PlayGame.barrier.add(new Barrier(2522,350,Barrier.barrier));//石头
		PlayGame.barrier.add(new Barrier(2719,350,Barrier.barrier));
		PlayGame.barrier.add(new Barrier(3482,350,Barrier.barrier));
		PlayGame.barrier.add(new Barrier(3670,350,Barrier.barrier));
		PlayGame.barrier.add(new Barrier(2340,350));
		PlayGame.barrier.add(new Barrier(850,560));
		PlayGame.barrier.add(new Barrier(1163,565));
		PlayGame.barrier.add(new Barrier(6900, 508));
		PlayGame.barrier.add(new Barrier(7640, 666));
		PlayGame.barrier.add(new Barrier(8170, 666));
		PlayGame.barrier.add(new Barrier(8274, 507));
		PlayGame.barrier.add(new Barrier(10390, 454));
		PlayGame.barrier.add(new Barrier(10495, 562));
		PlayGame.barrier.addAll(new Barrier().LongBarrier(107, 350, 22));
		PlayGame.barrier.addAll(new Barrier().LongBarrier(957, 666, 2));
		PlayGame.barrier.addAll(new Barrier().LongBarrier(530, 456, 3));
		PlayGame.barrier.addAll(new Barrier().LongBarrier(1380, 453, 2));
		PlayGame.barrier.addAll(new Barrier().LongBarrier(1914, 666, 2));
		PlayGame.barrier.addAll(new Barrier().LongBarrier(2022, 505, 3));
		PlayGame.barrier.addAll(new Barrier().LongBarrier(2869, 350, 5));
		PlayGame.barrier.addAll(new Barrier().LongBarrier(3823, 350, 8));
		PlayGame.barrier.addAll(new Barrier().LongBarrier(4462, 241, 16));
		PlayGame.barrier.addAll(new Barrier().LongBarrier(4567, 666, 3));
		PlayGame.barrier.addAll(new Barrier().LongBarrier(4883, 510, 2));
		PlayGame.barrier.addAll(new Barrier().LongBarrier(5203, 455, 7));
		PlayGame.barrier.addAll(new Barrier().LongBarrier(5626, 666, 6));
		PlayGame.barrier.addAll(new Barrier().LongBarrier(6050, 350, 7));
		PlayGame.barrier.addAll(new Barrier().LongBarrier(6265, 563, 2));
		PlayGame.barrier.addAll(new Barrier().LongBarrier(6583, 563, 2));
		PlayGame.barrier.addAll(new Barrier().LongBarrier(6690, 240, 5));
		PlayGame.barrier.addAll(new Barrier().LongBarrier(7112, 452, 3));
		PlayGame.barrier.addAll(new Barrier().LongBarrier(7323, 350, 2));
		PlayGame.barrier.addAll(new Barrier().LongBarrier(7640, 452, 2));
		PlayGame.barrier.addAll(new Barrier().LongBarrier(7745, 560, 3));
		PlayGame.barrier.addAll(new Barrier().LongBarrier(8064, 350, 2));
		PlayGame.barrier.addAll(new Barrier().LongBarrier(8170, 243, 2));
		PlayGame.barrier.addAll(new Barrier().LongBarrier(8486, 350, 2));
		PlayGame.barrier.addAll(new Barrier().LongBarrier(8592, 452, 5));
		PlayGame.barrier.addAll(new Barrier().LongBarrier(8910, 666, 3));
		PlayGame.barrier.addAll(new Barrier().LongBarrier(9335, 560, 2));
		PlayGame.barrier.addAll(new Barrier().LongBarrier(9656, 457, 2));
		PlayGame.barrier.addAll(new Barrier().LongBarrier(9866, 350, 5));
		PlayGame.barrier.addAll(new Barrier().LongBarrier(9866, 666, 13));
		PlayGame.barrier.addAll(new Barrier().LongBarrier(9970, 508, 4));
	}
	
	
}
