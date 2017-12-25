package lao.hdl;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Hero2 extends Hero {
	public Image[] heroimagesU0 = new Image[] { Imag.hero2_0 };
	public Image[] heroimagesU1 = new Image[] { Imag.hero02_3 };
	public Image[] heroimagesL = new Image[] { Imag.hero2walkL0, Imag.hero2walkL1, Imag.hero2walkL2, Imag.hero2walkL3 };
	public Image[] heroimagesR = new Image[] { Imag.hero2walkR0, Imag.hero2walkR1, Imag.hero2walkR2, Imag.hero2walkR3 };
	public Image[] heroimagesRS = new Image[] { Imag.hero2walkRS0, Imag.hero2walkRS1, Imag.hero2walkRS2,
			Imag.hero2walkRS3 };
	public Image[] heroimagesLS = new Image[] { Imag.hero2walkLS0, Imag.hero2walkLS1, Imag.hero2walkLS2,
			Imag.hero2walkLS3 };
	public Image heroimagesC0 =  Imag.hero2crawl0 ;
	public Image heroimagesC1 =  Imag.hero2crawl1 ;
	public Image[] heroimagesRU = new Image[] { Imag.hero2RUS0, Imag.hero2RUS1, Imag.hero2RUS2, Imag.hero2RUS3 };
	public Image[] heroimagesRD = new Image[] { Imag.hero2RDS0, Imag.hero2RDS1, Imag.hero2RDS2, Imag.hero2RDS3 };
	public Image[] heroimagesLD = new Image[] { Imag.hero2LDS0, Imag.hero2LDS1, Imag.hero2LDS2, Imag.hero2LDS3 };
	public Image[] heroimagesLU = new Image[] { Imag.hero2LUS0, Imag.hero2LUS1, Imag.hero2LUS2, Imag.hero2LUS3 };
	public Image[] heroimagesJR = new Image[] { Imag.hero2JR0, Imag.hero2JR1, Imag.hero2JR2, Imag.hero2JR3, Imag.hero2JR4,
			Imag.hero2JR5, Imag.hero2JR6 };
	public Image[] heroimagesJL = new Image[] { Imag.hero2JR6, Imag.hero2JR5, Imag.hero2JR4, Imag.hero2JR3, Imag.hero2JR2,
					Imag.hero2JR1, Imag.hero2JR0 };
	public Image[] heroDeing=new Image[]{Imag.hero2D0,Imag.hero2D1};
	
	
	
	public Hero2() {
		image = Imag.hero2_1;
		this.x = 200;
		this.y = 250;
		width = image.getWidth(null);
		height = image.getHeight(null);
	}

	public void keyPressedToMove(int key) {//按下键盘
		switch (key) {
		case KeyEvent.VK_K:
			if(this.isLive){
			down=true;
			if(onGravity&&!bD){
				jumpFinished=false;
				jumping=true;				
			}
			}
			break;
		case KeyEvent.VK_D:
			if(this.isLive){
			bR = true;
			bSR = false;
			bSL = false;
			LastDirR = false;
			LastDirL = false;
			}
			break;
		case KeyEvent.VK_A:
			if(this.isLive){
			bL = true;
			bSR = false;
			bSL = false;
			LastDirR = false;
			LastDirL = false;
			}
			break;
		case KeyEvent.VK_W:
			if(this.isLive){
			bU = true;
			bSR = false;
			bSL = false;
			}
			break;
		case KeyEvent.VK_S:
			if(this.isLive){
			crawing = true;
			bD = true;
			bSR = false;
			bSL = false;
			}
			break;
		}
		locateDir();
	}

	public void keyReleasedToMove(int key) {//离开键盘
		switch (key) {
		case KeyEvent.VK_K:
			if(this.isLive){
			down=false;
			}
			break;
		case KeyEvent.VK_J:
			if(this.isLive){
			PlayGame.hero2ammos.add(shoot());
			bS = true;
			}
			break;
		case KeyEvent.VK_D:
			if(this.isLive){
			bR = false;
			bSR = true;
			LastDirR = true;
			}
			break;
		case KeyEvent.VK_A:
			if(this.isLive){
			bL = false;
			bSL = true;
			LastDirL = true;
			}
			break;
		case KeyEvent.VK_W:
			if(this.isLive){
			bU = false;
			}
			break;
		case KeyEvent.VK_S:
			if(this.isLive){
			crawing = false;
			bD = false;
			}
			break;
		}
		locateDir();
	}

	
	
	public void locateDir() {//方向设定
		if (bL && !bU && !bR && !bD)
			dir = Dir.L;
		else if (!bL && !bU && !bR && bD)
			dir = Dir.D;
		else if (!bL && !bU && bR && !bD)
			dir = Dir.R;
		else if (bL && bU && !bR && !bD)
			dir = Dir.LU;
		else if (!bL && bU && bR && !bD)
			dir = Dir.RU;
		else if (!bL && bU && !bR && !bD)
			dir = Dir.U;
		else if (bL && !bU && !bR && bD)
			dir = Dir.LD;
		else if (!bL && !bU && bR && bD)
			dir = Dir.RD;
		else if (!bL && !bU && !bR && !bD && bSR)
			dir = Dir.STOPR;
		else if (!bL && !bU && !bR && !bD && bSL)
			dir = Dir.STOPL;
		else if (!bL && !bU && !bR && !bD)
			dir=Dir.STOP;
	}
	
	public void BirthHero(){//重生的英雄
		if(this.life>0&&!isLive&&heroDir==13){
		image = Imag.hero2_1;
		this.x = 150;
		this.y = -this.height;
		width = image.getWidth(null);
		height = image.getHeight(null);
		life--;
		isLive=true;
		}
	}
	
	public void paintlife(Graphics g){
		int x=50;
		for(int i=0;i<this.life;i++){
			g.drawImage(Imag.hero2life, x, 70, null);
			x+=30;
		}
	}
	
	
	public void dieing(){//击中英雄
		if(!isLive){
			new Thread(){
				public void run() {
					try {
//					for(int i=0;i<heroDeing.length;i++){
//						image=heroDeing[i];
//							Thread.sleep(200);
//					}
					image=heroDeing[1];
					Thread.sleep(200);
					heroDir=13;
					image = Imag.hero2_1;
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				};
			}.start();
//			if (t % 5 == 0) {
//				index++;
//				image = heroDeing[index % heroDeing.length];
//				heroDir=13;
//			}
		}
	}
	
	
	public void CrawingDown(){//趴下之后跳下去
		if(down&&onGravity&&dir==Dir.D){
			if(LastDirR&&heroDir==2){
				setimag();
				image = Imag.hero2_1;
				YSPEED=3;
				heroDir=4;
			}
			if(LastDirL&&heroDir==3){
				setimag();
				image = Imag.hero2_2;
				heroDir=5;
				YSPEED=3;
			}
		}
	}
	
	public void UpShoot(){//向上射击
		if (jumpFinished&&!jumping&&LastDirR&&jumpFinished&&dir==Dir.U) {//右
			setimag();
			image = heroimagesU0[0];
			heroDir=0;
		}else if( jumpFinished&&!jumping&&LastDirL&&jumpFinished&&dir==Dir.U) {//左
			setimag();
			image = heroimagesU1[0];
			heroDir=1;
		}	
	}
	
	public void crawing(boolean crawing){//趴下
		if ( LastDirR&&jumpFinished) {
			setimag();
			image = heroimagesC0;
			heroDir=2;
		}else if(LastDirL&&jumpFinished) {
			setimag();
			image = heroimagesC1;
			heroDir=3;
		}
	}
	
	public void stop(){//停止的图片
		if (jumpFinished&&!jumping&&dir == Dir.STOPR||(dir==Dir.STOP&&LastDirR)) {
			setimag();
			 image = Imag.hero2_1;
			 heroDir=4;
			 }
		else if(jumpFinished&&!jumping&&dir == Dir.STOPL||(dir==Dir.STOP&&LastDirL)) {
			setimag();
				image = Imag.hero2_2;
				heroDir=5;
			}
	}
	
	public void jump(){//英雄跳跃
			if ((JUMP_HEIGHT < 65) && (!this.jumpFinished)) {
				YSPEED=3;
				JUMP_HEIGHT += 1;
				this.y -= YSPEED;
			}else{
				jumpFinished=true;
				JUMP_HEIGHT=0;
				this.y+=YSPEED;
			}
	}
	
	public void walk(){//英雄走路
		if (dir == Dir.R&&jumpFinished&&!jumping) {//向右走
			setimag();
			if (t % 10 == 0) {
				index++;
				image = heroimagesRS[index % heroimagesRS.length];
				heroDir=6;
			}
		}
		if (dir == Dir.L&&jumpFinished) {//左走
			setimag();
			if (t % 10 == 0) {
				index++;
				image = heroimagesLS[index % heroimagesLS.length];
				heroDir=7;
			}
		}
		if (dir == Dir.RU&&jumpFinished) {//右上
			setimag();
			if (t % 10 == 0) {
				index++;
				image = heroimagesRU[index % heroimagesRU.length];
				heroDir=8;
				
			}
		}
		if (dir == Dir.RD&&jumpFinished) {//右下
			setimag();
			if (t % 10 == 0) {
				index++;
				image = heroimagesRD[index % heroimagesRD.length];
				heroDir=9;
				
			}
		}
		if (dir == Dir.LD&&jumpFinished) {//左下
			setimag();
			if (t % 10 == 0) {
				index++;
				image = heroimagesLD[index % heroimagesLD.length];
				heroDir=10;
				
			}
		}
		if (dir == Dir.LU&&jumpFinished) {//左上
			setimag();
			if (t % 10 == 0) {
				index++;
				image = heroimagesLU[index % heroimagesLU.length];
				heroDir=11;
				
			}
		}
	}
	
	
	int t = 0;
	public void HeroStep() {// 切换图片
		t++;
		diry=this.y;
		if (t >= 30) {
			t = 0;
		}
		if((dir!=Dir.STOPL||dir!=Dir.STOPR)&&walk&&jumpFinished&&!jumping){
			walk();			
		}
		if (jumping) {//跳跃
			setimag();
			if (t % 6 == 0&&bR) {
				index++;
				image = heroimagesJR[index % heroimagesJR.length];
				heroDir=12;
			}
			if (t % 6 == 0&&bL) {
				index++;
				image = heroimagesJL[index % heroimagesJL.length];
				heroDir=12;
			}else if(t % 6 == 0){
				index++;
				image = heroimagesJR[index % heroimagesJR.length];
				heroDir=12;
			}
		}
		stop();
		UpShoot();
	}
	
	
	
	public boolean isbR() {
		return bR;
	}

	public boolean isbL() {
		return bL;
	}

	public boolean isLastDirL() {
		return LastDirL;
	}

	public boolean isLastDirR() {
		return LastDirR;
	}

	public boolean isCrawing() {
		return crawing;
	}

	public boolean isbU() {
		return bU;
	}

	public boolean isbD() {
		return bD;
	}

	public boolean isbSR() {
		return bSR;
	}

	public boolean isbSL() {
		return bSL;
	}

	public boolean isbS() {
		return bS;
	}

}
