package lao.hdl;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Boss extends Enemy{
	private int speed = 3;//移动速度
	public int dir;// 方向
	public int x;
	public int y;
	public int width;
	public int height;
	public boolean into;
	Hero hero = new Hero();
	private int life=1000;
	public Image[] bos = new Image[] {Imag.boss,Imag.boss1,Imag.boss2};
	public Image[] bossboom=new Image[]{Imag.bossboom_0,Imag.bossboom_1,
					Imag.bossboom_2,Imag.bossboom_3,Imag.bossboom_4
			,Imag.bossboom_5,Imag.bossboom_6,Imag.bossboom_7,Imag.bossboom_8,
			Imag.bossboom_9,Imag.bossboom_10,Imag.bossboom_11};
	
	
	
	public Boss(){
		image = Imag.boss;//获取boss的图片
		width = image.getWidth(null);//获取boss的宽度
		height = image.getHeight(null);//获取boss的高度
		this.x = 11500;//boss的起始坐标
		this.y = 0;//boss的高度		
	}
	
	
	public void move() {//boss移动
		//boss向左移动
		x -= speed;
		//boss向左移动到一定位置时向右移动
		if(this.x<=0){
			speed=-3;
		}
		//boss向右移动到一定位置时向左移动
		if(this.x>=PlayGame.WIDTH-this.width){
			speed=3;
		}
	}
	
	
	public void bossdeing(){
		new Thread(){
			public void run() {
				try {
					flag=true;
					image=bossboom[0];
					Thread.sleep(350);
					setX(getX()+100);
					setY(getY()+getHeight()/2);
				for(int i=1;i<bossboom.length;i++){
					image=bossboom[i];
//					System.out.println(i);
					Thread.sleep(95);
				}
				Thread.sleep(95);
				isLive = false;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			};
		}.start();
		
	}

	int index = 0;
	public void ShotHero(Hero hero) {//添加boss子弹
		index++;
		ArrayList<Ammo> ammo=new ArrayList<Ammo>();
		int x = this.x+10;
		int y = this.y+140;
		if(hero.x+hero.width<this.x){
			dir = 2;
		}
		if(hero.x>this.x&&hero.x+hero.width<this.x+this.width){
			dir = 6;
		}
		if(hero.x+hero.width>this.x+this.width){
			dir = 5;
		}
		switch (dir) {
		case 2:
			ammo.add(new Ammo(x,y,Imag.ammo1,dir));
			ammo.add (new Ammo(x+80,y,Imag.ammo1,dir));
			ammo.add ( new Ammo(x+220,y,Imag.ammo1,dir));
			ammo.add ( new Ammo(x+290,y,Imag.ammo1,dir));
			break;
		case 6:
			ammo.add ( new Ammo(x,y,Imag.ammo1,dir));
			ammo.add ( new Ammo(x+80,y,Imag.ammo1,dir));
			ammo.add ( new Ammo(x+220,y,Imag.ammo1,dir));
			ammo.add ( new Ammo(x+290,y,Imag.ammo1,dir));
			break;
		case 5:
			ammo.add ( new Ammo(x,y,Imag.ammo1,dir));
			ammo.add ( new Ammo(x+80,y,Imag.ammo1,dir));
			ammo.add ( new Ammo(x+220,y,Imag.ammo1,dir));
			ammo.add ( new Ammo(x+290,y,Imag.ammo1,dir));
			break;
		default:
			break;
		}
		
		if (index%300==0) {
			PlayGame.bossammo.addAll(ammo);//将子弹数组添进去
		}
	}
	
	
	public void step() {//切换图片
		switch (dir) {
		case 2:
			image = bos[2];
			break;
		case 6:
			image = bos[0];
			break;
		case 5:
			image = bos[1];
			break;

		default:
			break;
		}
	}

	
	public void Bosshit(ArrayList<Ammo> Ammo) {//击中英雄
		boolean flag=false;
	     Rectangle rboss =new Rectangle(this.x, this.y,this.width,this.height/2+30);
	     try{
		for(Ammo ammo:Ammo){
			Rectangle rammo = new Rectangle(ammo.x,ammo.y,ammo.width,ammo.height);  	
			if(rboss.intersects(rammo)){
				life-=5;
				ammo.flag=true;
				flag=true;
			}
		}}
	     catch(Exception e){
	    	 e.printStackTrace();
	     }
	}
	
	public int getLife() {
		return life;
	}


	public void setX(int x) {
		this.x = x;
	}


	public void setY(int y) {
		this.y = y;
	}


	public int getX() {
		return x;
	}


	public int getY() {
		return y;
	}


	public int getWidth() {
		return width;
	}


	public void setWidth(int width) {
		this.width = width;
	}


	public int getHeight() {
		return height;
	}


	public void setHeight(int height) {
		this.height = height;
	}
	
	
}

