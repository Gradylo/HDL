package lao.hdl;

import java.awt.Image;
import java.util.Random;


public class Ammo extends FlyObject implements SubmachineGun,Runnable{
	public static  int XSPEED=5;
	public static  int YSPEED=5;
	public Image image;
	public Image[] images;
	boolean flag=false;
	private int Dir;
	private int state;
	private int index=0;
	public Image[] ammo2 = new Image[] { Imag.ammo2,Imag.ammo2_1,Imag.ammo2_2,Imag.ammo2_3};
	public Image[] ammo2_boom = new Image[] {Imag.ammo2_boom0,Imag.ammo2_boom1,Imag.ammo2_boom2,Imag.ammo2_boom3};
	
	
	Hero.Dir dir;//英雄方向
	public Ammo(int x,int y,Hero.Dir dir){
		image=Imag.ammo1;
		this.x=x;
		this.y=y;
		width=image.getWidth(null);
		height=image.getHeight(null);
		this.dir=dir;
	}
	
	public Ammo(int x,int y,Image image,int dir){
		this.x = x;
		this.y = y;
		this.image = image;
		this.Dir = dir;
	}
	
	public Ammo(int x,int y,int dir){
		this.x=x;
		this.y=y;
		this.Dir=dir;
		
	}

	public  void BoomStep(){
		if(flag){
			int num=new Random().nextInt(4);
			image=ammo2_boom[num];
		}
	}
	
	public void move() {//子弹移动,根据英雄的枪口位置
		switch(dir){
		case L:
			x -= XSPEED;
			break;
		case LU:
			x -= XSPEED;
			y -= YSPEED;
			break;
		case U:
			y -= YSPEED;
			break;
		case RU:
			x += XSPEED;
			y -= YSPEED;
			break;
		case R:
			x += XSPEED;
			break;
		case RD:
			x += XSPEED;
			y += YSPEED;
			break;
		case D:
			y += YSPEED;
			break;
		case LD:
			x -= XSPEED;
			y += YSPEED;
			break;
		}
		
	}
	
	public void shootHero(){//射击英雄
		switch (Dir) {
		case 0:
			x -= XSPEED;
			y -= YSPEED;
			break;
		case 1:
			x -= XSPEED;
			break;
		case 2:
			x -= XSPEED;
			y += YSPEED;
			break;
		case 3:
			x += XSPEED;
			y -= YSPEED;
			break;
		case 4:
			x += XSPEED;
			break;
		case 5:
			x += XSPEED;
			y += YSPEED;
			break;
		}
	}
	
	public void GunShoot(int Gun) {//射击效果
		switch(Gun){
		case 1://默认小米加
			image=Imag.ammo0;
			width=image.getWidth(null);
			height=image.getHeight(null);
			break;
		case 2://大炮
			image=Imag.ammo1;		
			width=image.getWidth(null);
			height=image.getHeight(null);
			break;
		case 3://会员专属
			image=ammo2[0];
			width=image.getWidth(null);
			height=image.getHeight(null);
			state=3;
			break;
		}
	}
	
	int t=0;
	public void AmmoStep(){
		t++;
		if (t >= 40) {
			t = 0;
		}
		if(state==3){
			if(t%6==0){
				index++;
				image=ammo2[index%ammo2.length];
			}			
		}
	}
	
	public void bossAmmoMove() {//boss子弹移动
		switch (Dir) {
		case 2:
			x -= XSPEED;
			y += YSPEED;
			break;
		case 5:
			x += XSPEED;
			y += YSPEED;
			break;
		case 6:
			y += YSPEED;
			break;
		}
	}

	public void run() {
		
		
	}

	
	
}
