package lao.hdl;

import java.awt.Image;

public class FlyObject {
	public  int XSPEED;
	public  int YSPEED;
	public static int width;
	public static int height;
	public int x;
	public int y;
	public Image image;
	public boolean Live=true;
	public boolean outOfBounds(){
		boolean flage=false;
		if((this.x>PlayGame.WIDTH||x<=0-width)||(this.y<=0-height||this.y>=PlayGame.HEIGHT)){
			flage=true;
		}
		return flage;
	}
}
