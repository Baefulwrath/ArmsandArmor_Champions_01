package containers;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;

import com.rapplebob.ArmsAndArmorChampions.AAA_C;

public class Container {
	public Rectangle conSurf = new Rectangle(0, 0, 0, 0);
	public String TITLE = "";
	public String ID = "";
	public int conSize = 20;
	public boolean ACTIVE = false;
	public ContainerState STATE = ContainerState.STATIC;
	public ContainerType TYPE = ContainerType.DEFAULT;
	public HashMap<String, Content> CONTENT = new HashMap<String, Content>();
	public Activator EXIT = new Activator();
	private Rectangle BOX = new Rectangle(0, 0, 0, 0);
	public Alignment ALIGNMENT = Alignment.CENTER;
	public Fill FILL = Fill.NONE;
	public boolean DECORATED = true;
	public float TRANSPARENCY = 1.0f;
	public boolean BACKGROUND = true;
	public boolean MOVING = false;
	
	
	public void update(){
		conSurf = new Rectangle(0, BOX.height - conSize, BOX.width, conSize);
		EXIT.set(ActivatorType.BUTTON, "EXITACTFROMCONTAINER", "X", "", "switchCon_" + ID, new Rectangle(conSurf.width - conSize, conSurf.y, conSize, conSize));
		for(int i = 0; i < CONTENT.size(); i++){
			CONTENT.get(i).update();
		}
	}
	
	public Container(String title, String id, boolean active, int x, int y, int width, int height, int controllerSize, ContainerState state, ContainerType type, Alignment alig, Fill fill, boolean decorated, float trans, boolean background){
		ID = id;
		TITLE = title;
		ACTIVE = active;
		STATE = state;
		TYPE = type;
		conSize = controllerSize;
		if(width + Fill.getWidth(AAA_C.w, fill) < 50){
			width = 50;
		}
		BOX = new Rectangle(x, y, width + Fill.getWidth(AAA_C.w, fill), height + Fill.getHeight(AAA_C.h, fill));
		ALIGNMENT = alig;
		FILL = fill;
		DECORATED = decorated;
		TRANSPARENCY = trans;
		BACKGROUND = background;
		update();
	}
	
	public void add(Content C){
		C.Y += getBox().height;
		if(DECORATED){
			C.Y -= conSize;
		}
		CONTENT.put(C.ID, C);
	}
	
	public void switchState(){
		if(ACTIVE){
			ACTIVE = false;
		}else{
			ACTIVE = true;
		}
	}
	
	public boolean justMoved = false;
	
	public boolean move(int diffX, int diffY){
		boolean movetofront = false;
		if(ACTIVE && MOVING && STATE != ContainerState.STATIC){
			movetofront = true;
			justMoved = true;
			BOX.x += diffX;
			BOX.y += diffY;
			update();
		}
		return movetofront;
	}
	
	public void stop(){
		MOVING = false;
		justMoved = false;
	}
	
	public boolean collides(Rectangle r){
		if(ACTIVE){
			if(r.intersects(getBox())){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
	public Rectangle getBox(){
		return new Rectangle(BOX.x + Alignment.getX(AAA_C.w, ALIGNMENT), BOX.y + Alignment.getY(AAA_C.h, ALIGNMENT), BOX.width, BOX.height);
	}
	
	public Rectangle getConBox(){
		Rectangle getbox = getBox();
		return new Rectangle(getbox.x, getbox.y + getbox.height - conSize, getbox.width, conSize);
	}
    
    public void mouseMoved(Rectangle mouse){
    	for(int i = 0; i < CONTENT.size(); i++){
    		CONTENT.get(i).mouseMoved(mouse, getBox().x, getBox().y);
    	}
    }
    
    public boolean getActById(String id, Activator A){
    	boolean temp = false;
    	for(int i = 0; i < CONTENT.size(); i++){
    		Menu m = (Menu) CONTENT.get(i);
    		if(m.TYPE == ContentType.MENU){
    			if(m.getActById(id, A)){
    				temp = true;
    				break;
    			}
    		}
    	}
    	return temp;
    }
}
