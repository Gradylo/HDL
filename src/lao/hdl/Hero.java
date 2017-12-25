package lao.hdl;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Hero extends Player {
	public  int JUMP_HEIGHT = 0;
	public boolean walk;
	public boolean nojumping=true;
	public boolean jumping = false;//跳跃状态
	public boolean jumpFinished = true;//非跳跃状态
	public final int WALKSPEED = 2;
	public int YSPEED=3;
	public boolean crawing=false;
	public int heroDir;
	public boolean bL = false, bU = false, bR = false, bD = false, bSR = false, bSL = false, bS = false,
			LastDirL = false, LastDirR = false;
	public boolean down;
	public enum Dir {
		L, LU, U, RU, R, RD, D, LD, STOPR, STOPL,STOP
	};

	public Dir dir = Dir.STOPR;
	public Dir ptdir = Dir.L;//枪口方向
	public Image[] heroimagesU0 = new Image[] { Imag.hero0 };
	public Image[] heroimagesU1 = new Image[] { Imag.hero03 };
	public Image[] heroimagesL = new Image[] { Imag.herowalkL0, Imag.herowalkL1, Imag.herowalkL2, Imag.herowalkL3 };
	public Image[] heroimagesR = new Image[] { Imag.herowalkR0, Imag.herowalkR1, Imag.herowalkR2, Imag.herowalkR3 };
	public Image[] heroimagesRS = new Image[] { Imag.herowalkRS0, Imag.herowalkRS1, Imag.herowalkRS2,
			Imag.herowalkRS3 };
	public Image[] heroimagesLS = new Image[] { Imag.herowalkLS0, Imag.herowalkLS1, Imag.herowalkLS2,
			Imag.herowalkLS3 };
	public Image heroimagesC0 =  Imag.herocrawl0 ;
	public Image heroimagesC1 =  Imag.herocrawl1 ;
	public Image[] heroimagesRU = new Image[] { Imag.heroRUS0, Imag.heroRUS1, Imag.heroRUS2, Imag.heroRUS3 };
	public Image[] heroimagesRD = new Image[] { Imag.heroRDS0, Imag.heroRDS1, Imag.heroRDS2, Imag.heroRDS3 };
	public Image[] heroimagesLD = new Image[] { Imag.heroLDS0, Imag.heroLDS1, Imag.heroLDS2, Imag.heroLDS3 };
	public Image[] heroimagesLU = new Image[] { Imag.heroLUS0, Imag.heroLUS1, Imag.heroLUS2, Imag.heroLUS3 };
	public Image[] heroimagesJR = new Image[] { Imag.heroJR0, Imag.heroJR1, Imag.heroJR2, Imag.heroJR3, Imag.heroJR4,
			Imag.heroJR5, Imag.heroJR6 };
	public Image[] heroimagesJL = new Image[] { Imag.heroJR6, Imag.heroJR5, Imag.heroJR4, Imag.heroJR3, Imag.heroJR2,
					Imag.heroJR1, Imag.heroJR0 };
	public Image[] heroDeing=new Image[]{Imag.heroD0,Imag.heroD1};
	
	
	public Hero() {
		image = Imag.hero1;
		this.x = 200;
		this.y = 250;
		width = image.getWidth(null);
		height = image.getHeight(null);
	}

	public void checkOut() {//检查出界
		if(isLive){

				if(PlayGame.BACKGROUNDX<=-9890){
					if(this.x+this.width >= PlayGame.WIDTH-150)
						this.x = PlayGame.WIDTH-150-this.width;
				}else{
					if (this.x >= PlayGame.WIDTH - 300) 
						this.x = PlayGame.WIDTH - 300;
				}
			
		}
		if (this.x <= 0) {
			this.x = 0;
		}
		if(this.y+this.height>=PlayGame.HEIGHT-50&&!onGravity){
			isLive=false;
		}
	}

	public Ammo shoot() {//射击
		int x = this.x + width / 2 - Ammo.width / 2;
		int y = this.y + height / 2 - Ammo.height / 2 - 15;
		if (ptdir == Dir.L) {
			x = this.x - Ammo.width / 2;
		}
		if (ptdir == Dir.R) {
			x = this.x + width - Ammo.width / 2;
		}
		if (ptdir == Dir.RU) {
			x = this.x + width - Ammo.width / 2;
			y = this.y - Ammo.height / 2;
		}
		if (ptdir == Dir.U&&LastDirR&&bS) {
			x = this.x + width/2- Ammo.width / 2;
			y = this.y - Ammo.height / 2-20;
		}
		if (ptdir == Dir.U&&LastDirL&&bS) {
			x = this.x +5- Ammo.width / 2;
			y = this.y - Ammo.height / 2-20;
		}
		if (ptdir == Dir.RD) {
			x = this.x + width - Ammo.width / 2;
			y = this.y + this.height - Ammo.height;
		}
		if (ptdir == Dir.LU) {
			x = this.x - Ammo.width / 2;
			y = this.y - Ammo.height / 2;
		}
		if (ptdir == Dir.LD) {
			x = this.x - Ammo.height / 2;
			y = this.y + this.height / 2;
		}
		if (ptdir == Dir.D && LastDirR) {
			this.ptdir = Dir.R;
			x = this.x+this.width - Ammo.height / 2;
			y = this.y + this.height-35;
		}
		if (ptdir == Dir.D && LastDirL) {
			this.ptdir = Dir.L;
			x = this.x - Ammo.height / 2;
			y = this.y + this.height-35;
		}
		Ammo a = new Ammo(x, y, ptdir);
		a.GunShoot(3);
		return a;
	}

	public void keyPressed(KeyEvent e) {//按下键盘
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_ENTER:
			if(PlayGame.state==PlayGame.START||PlayGame.state==PlayGame.GAME_OVER||PlayGame.state==PlayGame.END){
				PlayGame.state=PlayGame.RUNNING;
			}
			break;
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

	public void keyReleased(KeyEvent e) {//离开键盘
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_K:
			if(this.isLive){
			down=false;
			}
			break;
		case KeyEvent.VK_J:
			if(this.isLive){
			PlayGame.ammos.add(shoot());
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

	public void move() {//移动
		switch (dir) {
		case L:
			x -= WALKSPEED;
			break;
		case R:
			x += WALKSPEED;
			break;
		case LU:
			x -= WALKSPEED;
			break;
		case RU:
			x += WALKSPEED;
			break;
		case LD:
			x -= WALKSPEED;
			break;
		case RD:
			x += WALKSPEED;
			break;
		}
		CrawingDown();
		//枪口方向
		if (this.dir != Dir.STOPL && this.dir != Dir.STOPR) {
			this.ptdir = this.dir;
		}
		if (this.dir == Dir.STOP&&LastDirL) {
			this.ptdir = Dir.L;
		}
		if (this.dir == Dir.STOP&&LastDirR) {
			this.ptdir = Dir.R;
		}
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
		image = Imag.hero1;
		this.x = 150;
		this.y = -this.height;
		width = image.getWidth(null);
		height = image.getHeight(null);
		dir = Dir.STOPR;
		ResetDir();
		life--;
		isLive=true;
		}
	}
	
	public void CrawingDown(){//趴下之后跳下去
		if(down&&onGravity&&dir==Dir.D){
			if(LastDirR&&heroDir==2){
				setimag();
				image = Imag.hero1;
				YSPEED=3;
				heroDir=4;
			}
			if(LastDirL&&heroDir==3){
				setimag();
				image = Imag.hero2;
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
			 image = Imag.hero1;
			 heroDir=4;
			 }
		else if(jumpFinished&&!jumping&&dir == Dir.STOPL||(dir==Dir.STOP&&LastDirL)) {
			setimag();
				image = Imag.hero2;
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
	
	public boolean hit(ArrayList<Ammo> Ammo){//击中英雄
		boolean flag=false;
	     Rectangle rhero =new Rectangle(this.x, this.y,dirWidth ,dirHeight );
		for(int i=0;i<Ammo.size();i++){
			Ammo ammo=Ammo.get(i);
			Rectangle rammo = new Rectangle(ammo.x,ammo.y,ammo.width,ammo.height);  
			if(this.heroDir==2||this.heroDir==3){//趴下
				rhero=new Rectangle(this.x,this.y+this.height-dirHeight,dirWidth,dirHeight);
			}
			if(this.heroDir==6||this.heroDir==8||this.heroDir==9||this.heroDir==4){//右边
				rhero =new Rectangle(this.x, this.y,dirWidth/2,dirHeight );
			}
			if(this.heroDir==7||this.heroDir==10||this.heroDir==11||this.heroDir==5){//左边
				rhero =new Rectangle(this.x+dirWidth/2, this.y,dirWidth/2,dirHeight );
			}
			if(this.heroDir==12){//跳跃
				rhero=new Rectangle(this.x+2,this.y,dirWidth,dirHeight);
			}
			if(this.heroDir==0||this.heroDir==1){//向上射击
				rhero=new Rectangle(this.x,this.y-36,dirWidth,dirHeight);
			}
			if(rhero.intersects(rammo)){
				isLive=false;
				ammo.flag=true;
				flag=true;
			}
		}
		return flag;
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
					image = Imag.hero1;
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
	
	int num=0;//设定英雄在草坪上的个数
	public void Gravity(ArrayList<Barrier> barrier) {//在草坪移动
		Barrier b=null;
		for(Barrier ba:barrier){
			if(ba.onBarrier(this)){
				b=ba;
				num++;
			}
		}
		if(num>=1){//在草坪上
			walk=true;
			nojumping=false;
			YSPEED=0;
			onGravity=true;
			if (crawing&&dir!=Dir.RD&&dir!=Dir.LD&&onGravity) {//判断是否趴下
				YSPEED=0;
				crawing(crawing);
			}
			if(jumpFinished){
				jumping=false;
			}

		}else{
			onGravity=false;
			nojumping=true;
			YSPEED=3;
		}
		num=0;
		}
		
	public void setimag(){
		dirWidth=image.getWidth(null);
		dirHeight=image.getHeight(null);
	}
	
	public void ResetDir(){
//		bL = false;
	    bU = false; 
//	    bR = false;d
	    bD = false;
//	    bSR = false;
//	    bSL = false;
	    bS = false;
		LastDirL = false;
		LastDirR = false;
		onGravity=false;
		down=false;
		nojumping=true;
		jumping = false;//跳跃状态
		jumpFinished = true;//非跳跃状态
		crawing=false;
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
