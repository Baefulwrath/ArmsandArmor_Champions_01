package containers;

import java.awt.Rectangle;

public abstract class Content {
	
	public int X;
	public int Y;
	public ContentType TYPE = ContentType.DEFAULT;
	
	public Content(int x, int y, ContentType type){
		X = x;
		Y = y;
		TYPE = type;
	}
	
	public void print(){
		System.out.println("Content");
	}
	
	public abstract void update();

	public abstract void mouseMoved(Rectangle mouse, int cx, int cy);
}
