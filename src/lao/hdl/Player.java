package lao.hdl;

import java.awt.Graphics;
import java.awt.Image;


public abstract class Player {
	public int x;
	public int y;
	public int width;
	public int height;
	public Image image;
	public Image[] images;
	public int direction;//
	public int G=2;//
	public Weapon weapon;
	public boolean onGravity=false;
	public int life=5;
	public boolean isLive=true;
	public int index = 0;
	public int dirWidth;
	public int dirHeight;
	public int dirx;
	public int diry;
	
	
	public void paintlife(Graphics g){
		int x=50;
		for(int i=0;i<this.life;i++){
			g.drawImage(Imag.hero1life, x, 30, null);
			x+=30;
		}
	}
	
	public void Gravity(Barrier b) {// 在草坪上走
		if (this.x + this.width >= b.x && this.x <= b.x + b.width / 2 && this.y + this.height >= b.y + b.height / 2) {
			this.y = b.y - this.height + b.height / 2;
		}
	}
	
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	
}
