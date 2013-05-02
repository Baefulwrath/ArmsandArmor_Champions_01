package containers;

import java.awt.Rectangle;
import java.util.ArrayList;

public class Container {
	public Rectangle controlsurface = new Rectangle(0, 0, 0, 0);
	public String TITLE = "";
	public String ID = "";
	public int X = 0;
	public int Y = 0;
	public int WIDTH = 0;
	public int HEIGHT = 0;
	public int conSize = 20;
	public boolean ACTIVE = false;
	public ContainerState STATE = ContainerState.STATIC;
	public ContainerType TYPE = ContainerType.DEFAULT;
	public ArrayList<Content> CONTENT = new ArrayList<Content>();
	
	public void update(){
		
	}
	
	public Container(String title, String id, boolean active, int x, int y, int width, int height, int controllerSize, ContainerState state, ContainerType type){
		ID = id;
		TITLE = title;
		ACTIVE = active;
		X = x;
		Y = y;
		WIDTH = width;
		HEIGHT = height;
		controlsurface = new Rectangle(X, Y + (HEIGHT / 2) - (conSize / 2), WIDTH, conSize);
		STATE = state;
		TYPE = type;
		conSize = controllerSize;
		if(WIDTH < 50){
			WIDTH = 50;
		}
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
