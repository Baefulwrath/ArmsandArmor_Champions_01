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
	public Activator EXIT = new Activator();
	
	public void update(){
		controlsurface = new Rectangle(X, Y + (HEIGHT / 2) - (conSize / 2), WIDTH, conSize);
		EXIT.set(ActivatorType.BUTTON, "X", "switchCon_" + ID, new Rectangle(WIDTH - conSize, HEIGHT / 2, conSize, conSize));
	}
	
	public Container(String title, String id, boolean active, int x, int y, int width, int height, int controllerSize, ContainerState state, ContainerType type){
		ID = id;
		TITLE = title;
		ACTIVE = active;
		X = x;
		Y = y;
		WIDTH = width;
		HEIGHT = height;
		STATE = state;
		TYPE = type;
		conSize = controllerSize;
		if(WIDTH < 50){
			WIDTH = 50;
		}
		update();
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
			update();
		}
	}
}
