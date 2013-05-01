package containers;

import java.awt.Rectangle;
import java.util.ArrayList;

public class Container {
	public Rectangle controlsurface = new Rectangle(0, 0, 0, 0);
	public String TITLE = "";
	public int X = 0;
	public int Y = 0;
	public int WIDTH = 0;
	public int HEIGHT = 0;
	public int conSize = 20;
	public boolean ACTIVE = false;
	public ContainerState STATE = ContainerState.STATIC;
	public ArrayList<Content> CONTENT = new ArrayList<Content>();
	
	public Container(int x, int y, int width, int height, String title, ContainerState state, int controllerSize){
		X = x;
		Y = y;
		WIDTH = width;
		HEIGHT = height;
		TITLE = title;
		controlsurface = new Rectangle(X, Y - conSize, WIDTH, conSize);
		STATE = state;
		conSize = controllerSize;
	}
	
	public void add(Content C){
		CONTENT.add(C);
	}
	
	public void switchState(){
		if(ACTIVE){
			ACTIVE = false;
		}else{
			ACTIVE = true;
		}
	}
	
	public void move(int mx, int my){
		if(ACTIVE){
			X += mx;
			Y += my;
			controlsurface = new Rectangle(X, Y - conSize, WIDTH, conSize);
		}
	}
}
