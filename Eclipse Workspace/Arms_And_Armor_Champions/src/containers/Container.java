package containers;

import java.awt.Rectangle;
import java.util.ArrayList;

public class Container {
	public Rectangle controlsurface = new Rectangle(0, 0, 0, 0);
	public String TITLE = "";
	public String ID = "";
	public int conSize = 20;
	public boolean ACTIVE = false;
	public ContainerState STATE = ContainerState.STATIC;
	public ContainerType TYPE = ContainerType.DEFAULT;
	public ArrayList<Content> CONTENT = new ArrayList<Content>();
	public Activator EXIT = new Activator();
	public Rectangle BOX = new Rectangle(0, 0, 0, 0);
	public Alignment ALIGNMENT = Alignment.CENTER;
	public boolean DECORATED = true;
	public float TRANSPARENCY = 1.0f;
	public boolean BACKGROUND = true;
	
	public void update(){
		controlsurface = new Rectangle(BOX.x, BOX.y + (BOX.height / 2) - (conSize / 2), BOX.width, conSize);
		EXIT.set(ActivatorType.BUTTON, "X", "switchCon_" + ID, new Rectangle(BOX.width - conSize, BOX.height / 2, conSize, conSize));
	}
	
	public Container(String title, String id, boolean active, int x, int y, int width, int height, int controllerSize, ContainerState state, ContainerType type, Alignment alig, boolean decorated, float trans, boolean background){
		ID = id;
		TITLE = title;
		ACTIVE = active;
		STATE = state;
		TYPE = type;
		conSize = controllerSize;
		if(width < 50){
			width = 50;
		}
		BOX = new Rectangle(x, y, width, height);
		ALIGNMENT = alig;
		DECORATED = decorated;
		TRANSPARENCY = trans;
		BACKGROUND = background;
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
	
	public void move(int diffX, int diffY){
		if(ACTIVE){
			BOX.x += diffX;
			BOX.y += diffY;
			update();
		}
	}
	
	public boolean collides(Rectangle r){
		if(r.intersects(BOX)){
			return true;
		}else{
			return false;
		}
	}
}
