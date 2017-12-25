package lao.hdl;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * 
 * @author McGrady
 *
 */
public class PlayGame extends JPanel {
	public static int WIDTH = 1000;
	public static int HEIGHT = 743;
	public static int BACKGROUNDSPEED=2;
	public static int BARRIERSPEED=2;
	public static int BACKGROUNDX=0;
	
	Hero hero = new Hero();
	Hero2 hero2=new Hero2();
	Boss boss=new Boss();
	SendKey sk=new SendKey();
	static ArrayList<Ammo> ammos=new ArrayList<Ammo>();
	static ArrayList<Ammo> hero2ammos=new ArrayList<Ammo>();
	static ArrayList<Ammo> enemyammos=new ArrayList<Ammo>();
	static ArrayList<Ammo> jumpenemyammos=new ArrayList<Ammo>();
	static ArrayList<Ammo> bossammo=new ArrayList<Ammo>();//boss子弹
	static ArrayList<Barrier> barrier=new ArrayList<Barrier>();
	static ArrayList<SimpleEnemy> enemy=new ArrayList<SimpleEnemy>();
	static ArrayList<JumpEnemy> jumpenemy=new ArrayList<JumpEnemy>();
	ExecutorService pool=Executors.newFixedThreadPool(3);//添加线程池对爆炸进行处理
	ExecutorService musicpool=Executors.newFixedThreadPool(2);
	public static final int START = 0;
	public static final int RUNNING = 1;
	public static final int END = 2;
	public static final int GAME_OVER = 3;
	public static int state=START;
	
	static{//场景加载
		enemy.add(new SimpleEnemy(2978,260));
		enemy.add(new SimpleEnemy(3190,260));
		enemy.add(new SimpleEnemy(3930,260));
		enemy.add(new SimpleEnemy(970,580));
		enemy.add(new SimpleEnemy(1700,270));
		enemy.add(new SimpleEnemy(1928,580));
		enemy.add(new SimpleEnemy(2980,260));
		enemy.add(new SimpleEnemy(4163,258));
		enemy.add(new SimpleEnemy(4980,418));
		enemy.add(new SimpleEnemy(5330,365));
		enemy.add(new SimpleEnemy(5836,152));
		enemy.add(new SimpleEnemy(6368,472));
		enemy.add(new SimpleEnemy(6687,472));
		enemy.add(new SimpleEnemy(7847,470));
		enemy.add(new SimpleEnemy(8703,370));
		enemy.add(new SimpleEnemy(9762,369));
		enemy.add(new SimpleEnemy(10293,260));
		enemy.add(new SimpleEnemy(10293,420));
		enemy.add(new SimpleEnemy(10293,580));
				barrier.add(new Barrier(2522,350,Barrier.barrier));//石头
				barrier.add(new Barrier(2719,350,Barrier.barrier));
				barrier.add(new Barrier(3482,350,Barrier.barrier));
				barrier.add(new Barrier(3670,350,Barrier.barrier));
				barrier.add(new Barrier(2340,350));
				barrier.add(new Barrier(850,560));
				barrier.add(new Barrier(1163,565));
				barrier.add(new Barrier(6900, 508));
				barrier.add(new Barrier(7640, 666));
				barrier.add(new Barrier(8170, 666));
				barrier.add(new Barrier(8274, 507));
				barrier.add(new Barrier(10390, 454));
				barrier.add(new Barrier(10495, 562));
				barrier.addAll(new Barrier().LongBarrier(107, 350, 22));
				barrier.addAll(new Barrier().LongBarrier(957, 666, 2));
				barrier.addAll(new Barrier().LongBarrier(530, 456, 3));
				barrier.addAll(new Barrier().LongBarrier(1380, 453, 2));
				barrier.addAll(new Barrier().LongBarrier(1914, 666, 2));
				barrier.addAll(new Barrier().LongBarrier(2022, 505, 3));
				barrier.addAll(new Barrier().LongBarrier(2869, 350, 5));
				barrier.addAll(new Barrier().LongBarrier(3823, 350, 8));
				barrier.addAll(new Barrier().LongBarrier(4462, 241, 16));
				barrier.addAll(new Barrier().LongBarrier(4567, 666, 3));
				barrier.addAll(new Barrier().LongBarrier(4883, 510, 2));
				barrier.addAll(new Barrier().LongBarrier(5203, 455, 7));
				barrier.addAll(new Barrier().LongBarrier(5626, 666, 6));
				barrier.addAll(new Barrier().LongBarrier(6050, 350, 7));
				barrier.addAll(new Barrier().LongBarrier(6265, 563, 2));
				barrier.addAll(new Barrier().LongBarrier(6583, 563, 2));
				barrier.addAll(new Barrier().LongBarrier(6690, 240, 5));
				barrier.addAll(new Barrier().LongBarrier(7112, 452, 3));
				barrier.addAll(new Barrier().LongBarrier(7323, 350, 2));
				barrier.addAll(new Barrier().LongBarrier(7640, 452, 2));
				barrier.addAll(new Barrier().LongBarrier(7745, 560, 3));
				barrier.addAll(new Barrier().LongBarrier(8064, 350, 2));
				barrier.addAll(new Barrier().LongBarrier(8170, 243, 2));
				barrier.addAll(new Barrier().LongBarrier(8486, 350, 2));
				barrier.addAll(new Barrier().LongBarrier(8592, 452, 5));
				barrier.addAll(new Barrier().LongBarrier(8910, 666, 3));
				barrier.addAll(new Barrier().LongBarrier(9335, 560, 2));
				barrier.addAll(new Barrier().LongBarrier(9656, 457, 2));
				barrier.addAll(new Barrier().LongBarrier(9866, 350, 5));
				barrier.addAll(new Barrier().LongBarrier(9866, 666, 13));
				barrier.addAll(new Barrier().LongBarrier(9970, 508, 4));
	}
	
	public void paint(Graphics g) {//主画面
		paintBarrier(g);			
		g.drawImage(Imag.background, BACKGROUNDX, 0, null);
		paintHeroLife(g);
		paintAmmo(g);
		paintEnemy(g);
		paintBoss(g);
		paintHero(g);
		paintStep();
		paintString(g);
		paintState(g);
	}
	
	public void paintHeroLife(Graphics g){
		hero.paintlife(g);
		hero2.paintlife(g);
	}
	
	public void paintState(Graphics g){//开始画面
		switch(state){
		case START:
			g.drawImage(Imag.startbackground, BACKGROUNDX, 0, null);
			break;
		case GAME_OVER:
			g.drawImage(Imag.gameover, BACKGROUNDX, 0, null);
			break;
		case END:
			g.drawImage(Imag.end, BACKGROUNDX, 0, null);
			break;
		}

		
	}
	
	public void paintBoss(Graphics g){//画boss
		if(boss!=null)
		g.drawImage(boss.image,boss.x,boss.y,null);
	}
	
	public void paintEnemy(Graphics g){//画敌人
		synchronized (enemy) {
			for(Enemy e:enemy){
				g.drawImage(e.image, e.x, e.y, null);			
			}
		}
		for(Enemy e:jumpenemy){
			g.drawImage(e.image, e.x, e.y, null);
		}
	}
	
	public void paintString(Graphics g){//显示状态
		g.setColor(new Color(0xFF0000));
		g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,20));
		if(boss.into)
		g.drawString("Boss Life:"+boss.getLife(),20, 20);
	}
	
	public void paintHero(Graphics g) {//画英雄
		if(hero.isLive){
		if(hero.heroDir>3){
			g.drawImage(hero.image,hero.x,hero.y,null);
		}
		
		if(hero.heroDir==2||hero.heroDir==3){//趴下
			g.drawImage(hero.image,hero.x,hero.y+40,null);
		}
		if(hero.heroDir==0||hero.heroDir==1){//向上射击
			g.drawImage(hero.image,hero.x,hero.y-38,null);
		}
		}else{
			g.drawImage(hero.image,hero.x,hero.y+50,null);
		}
		if(hero2.isLive){
			if(hero2.heroDir>3){
				g.drawImage(hero2.image,hero2.x,hero2.y,null);
			}
			
			if(hero2.heroDir==2||hero2.heroDir==3){//趴下
				g.drawImage(hero2.image,hero2.x,hero2.y+40,null);
			}
			if(hero2.heroDir==0||hero2.heroDir==1){//向上射击
				g.drawImage(hero2.image,hero2.x,hero2.y-38,null);
			}
			}else{
				g.drawImage(hero2.image,hero2.x,hero.y+50,null);
			}
	}
	
	public void paintStep(){//英雄图片
		hero.HeroStep();
		hero2.HeroStep();
	}
	
	public void paintBarrier(Graphics g){//障碍物
		synchronized (barrier) {
		for(Barrier b:barrier){
				g.drawImage(b.image,b.x ,b.y,null);					
			}
		}
	}
	
	public void paintAmmo(Graphics g){//画子弹
		for(Ammo a:hero2ammos){
			g.drawImage(a.image,a.x,a.y,null);
		}
		for(Ammo a:ammos){
			g.drawImage(a.image,a.x,a.y,null);
		}
		for(Ammo a:enemyammos){
			g.drawImage(a.image,a.x,a.y,null);
		}
		for(Ammo a:jumpenemyammos){
			g.drawImage(a.image,a.x,a.y,null);
		}
		for (Ammo am : bossammo) {//画出boss子弹
			g.drawImage(am.image, am.x, am.y,null);
		}
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		PlayGame game = new PlayGame();
		frame.add(game);
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setAlwaysOnTop(true);
		frame.setResizable(false);
		frame.setVisible(true);
		

		game.Receive();
		game.action();
	}
	
	public void Receive(){
		new Thread(){
			public void run() {
				DatagramSocket ds;
				try {
				ds = new DatagramSocket(10086);
				byte[] bye=new byte[100];
				DatagramPacket dp=new DatagramPacket(bye,bye.length);
				byte[] bye2=dp.getData();
				int len=dp.getLength();
				String str=null;
				String s=null;
				while(true){
					ds.receive(dp);
					str=new String(bye2,0,len);
					if(str.charAt(0)=='r'){
						s=str.substring(1);
						hero2.keyPressedToMove(Integer.parseInt(s.trim()));
					}else if(str.charAt(0)=='p'){
						s=str.substring(1);
						hero2.keyReleasedToMove(Integer.parseInt(s.trim()));
					}
				}
				}catch (IOException e) {
					e.printStackTrace();
				}
			};
		}.start();
	}
	
	public boolean checkHeroDeing(Hero hero){
		hero.dieing();
		hero.BirthHero();
		return hero.isLive;
	}
	
	public boolean checkHero2Deing(Hero2 hero2){
		hero2.dieing();
		hero2.BirthHero();
		return hero2.isLive;
	}
	
	public void hithero(){//伤害英雄
		hero.hit(enemyammos);
		hero.hit(jumpenemyammos);
		hero.hit(bossammo);
		
	}
	
	public void hithero2(){
		hero2.hit(enemyammos);
		hero2.hit(jumpenemyammos);
		hero2.hit(bossammo);
	}
	
	public void hitEnemy(){//击中敌人
		Iterator<SimpleEnemy> it=enemy.iterator();
		Iterator<JumpEnemy> jit=jumpenemy.iterator();
		while(it.hasNext()){
			SimpleEnemy e=it.next();
			if(!e.isLive){				
				it.remove();
			}
		}
		while(jit.hasNext()){
			JumpEnemy e=jit.next();
			if(!e.isLive){				
				jit.remove();
			}
		}
	}
	
	public void EnemyStep(){//敌人图片切换
		for(int i=0;i<enemy.size();i++){
			SimpleEnemy e=enemy.get(i);
			if(!e.flag){
				if((e.hit(ammos)||e.hit(hero2ammos))&&!e.boom){
					pool.submit(e);
//					e.Hitboom();
					e.boom=true;
				}
				e.outOfBounds();
				e.ShootHero(hero);
//				e.ShootHero(hero2);
				e.step();				
			}
		}
		for(int i=0;i<jumpenemy.size();i++){
			JumpEnemy e=jumpenemy.get(i);
			if(!e.flag){
				if((e.hit(ammos)||e.hit(hero2ammos))&&!e.boom){
					pool.submit(e);
//					e.Hitboom();
					e.boom=true;
				}
				e.ShootHero(hero);
//				e.ShootHero(hero2);
				e.step();
				e.jump();
				e.outOfBounds();
				e.Gravity(barrier);
			}
		}
	}
	
	public void PlayerMove() {//英雄移动
		for(Barrier b:barrier){
			b.onBarrier(hero);
		}
		hero.move();
		hero.jump();
		hero.Gravity(barrier);
	}
	
	public void Player2Move(){
		for(Barrier b:barrier){
			b.onBarrier(hero2);
		}
		hero2.move();
		hero2.jump();
		hero2.Gravity(barrier);
	}
	
	public void AmmoStep(){//子弹移动
		for(int i=0;i<hero2ammos.size();i++){
			Ammo a=hero2ammos.get(i);
			a.move();
			a.AmmoStep();
//			a.BoomStep();
		}
		for(int i=0;i<ammos.size();i++){
			Ammo a=ammos.get(i);
			a.move();
			a.AmmoStep();
//			a.BoomStep();
		}
		for(int i=0;i<enemyammos.size();i++){
			Ammo a=enemyammos.get(i);
			a.shootHero();
		}
		for(int i=0;i<jumpenemyammos.size();i++){
			Ammo a=jumpenemyammos.get(i);
			a.shootHero();
		}
		for(int i=0;i<bossammo.size();i++){
			Ammo a=bossammo.get(i);
			a.bossAmmoMove();
		}
	}
	
	public void BossDeing(){//Boss死亡
		if(boss.getLife()<=0&&!boss.boom){
			boss.bossdeing();
			boss.boom=true;
		}
		if(!boss.isLive){
			boss.y=-boss.height;
		}
	}
	
	public void Bossinto(){//Boss进场
//		BossBgm bossBgm=new BossBgm();
		if(hero.x>boss.x-1000-boss.width&&!boss.flag){//判断英雄靠近boss
			boss.move();//boss走起来
			boss.Bosshit(ammos);
			boss.Bosshit(hero2ammos);
			boss.ShotHero(hero);
			boss.step();
			boss.into=true;
//			bossBgm.playSound=true;
//			musicpool.submit(bossBgm);
		}else{
//			bossBgm.playSound=false;
		}
	}
	
	public void outOfBounds(){//检查越界,以及子弹碰撞
		for(int i=0;i<bossammo.size();i++){//遍历每一个boss子弹数组
			Ammo a=bossammo.get(i);
			if(a.outOfBounds()||a.flag){
				bossammo.remove(a);
			}
		}
		for(int i=0;i<ammos.size();i++){
			Ammo a=ammos.get(i);
			if(a.outOfBounds()||a.flag){
				ammos.remove(a);
			}
		}
		for(int i=0;i<hero2ammos.size();i++){
			Ammo a=hero2ammos.get(i);
			if(a.outOfBounds()||a.flag){
				hero2ammos.remove(a);
			}
		}
		for(int i=0;i<enemyammos.size();i++){
			Ammo a=enemyammos.get(i);
			if(a.outOfBounds()||a.flag){
				enemyammos.remove(a);
			}
		}
		for(int i=0;i<jumpenemyammos.size();i++){
			Ammo a=jumpenemyammos.get(i);
			if(a.outOfBounds()||a.flag){
				jumpenemyammos.remove(a);
			}
		}
	}
	
	public void BackgroundStep(){//背景移动
		hero.checkOut();
		hero2.checkOut();
		if((hero.x>=WIDTH-300&&hero.isbR()&&hero.isLive)&&
		(hero2.x>=WIDTH-300&&hero2.isbR()&&hero2.isLive)&&BACKGROUNDX>=-9890){
			BACKGROUNDX-=BACKGROUNDSPEED;
//			System.out.println(BACKGROUNDX);
			for(Barrier b:barrier){
				b.x-=BARRIERSPEED;
			}
			for(Enemy e:enemy){
				e.x-=BACKGROUNDSPEED;				
			}
			for(JumpEnemy e:jumpenemy){
				e.x-=BACKGROUNDSPEED;	
			}
			boss.x-=BACKGROUNDSPEED;
		}
	}
	
	public void checkgameover(){//检查游戏是否结束
		if((hero.life==0||hero2.life==0)){
			state=GAME_OVER;
		}
		if(!boss.isLive){
			state=END;
		}
		if(state==GAME_OVER||state==END){
			BACKGROUNDX=0;
			hero=new Hero();
			hero2=new Hero2();
			boss=new Boss();
			boss.into=false;
			ammos.clear();
			hero2ammos.clear();
			enemyammos.clear();
			jumpenemyammos.clear();
			bossammo.clear();
			jumpenemy.clear();
			synchronized (barrier) {
				barrier.clear();
				Barrier.ResetBarrier();
			}
			synchronized (enemy) {
				enemy.clear();
				SimpleEnemy.Reset();
			}
		}
	}
	
	public void playmusic(){//背景音乐
		BGM bgm = new BGM();
		musicpool.submit(bgm);
		if(state==RUNNING&&!boss.into){
			bgm.playSound=true;
		}else{
			bgm.playSound=false;
		}
	}
	
	public void action() {//开始执行
			KeyAdapter key = new KeyAdapter() {
				public void keyPressed(KeyEvent e) {
					hero.keyPressed(e);
					sk.start("r",e.getKeyCode());//按下键盘
				}
				
				public void keyReleased(KeyEvent e) {
					hero.keyReleased(e);
					sk.start("p",e.getKeyCode());//离开键盘
				}
			};
			this.setFocusable(true);
			this.addKeyListener(key);
		
		new Thread(){
			public void run() {
				while(true){
					if(state==RUNNING){
						playmusic();
					checkgameover();
					if(checkHeroDeing(hero)){
					hithero();
					PlayerMove();
					hitEnemy();
					}
					if(checkHero2Deing(hero2)){
						hithero2();
						Player2Move();
						hitEnemy();
						}
					JumpEnemy.CreateJumpEnemy();
					outOfBounds();
					AmmoStep();
					EnemyStep();
					BossDeing();
					Bossinto();
					BackgroundStep();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					}
						repaint();						
				}
			};
		}.start();
	}
	
}
