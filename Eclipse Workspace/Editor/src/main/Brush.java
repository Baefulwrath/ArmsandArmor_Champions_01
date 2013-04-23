package main;

import java.awt.Rectangle;

public class Brush extends Cell{
	
	public int SIZE = 5;
	public Rectangle BOX = new Rectangle(0, 0, SIZE, SIZE);
	
	public void update(int mousex, int mousey){
		BOX = new Rectangle(mousex, mousey, SIZE, SIZE);
	}

	public Brush(int width, int terrain, int climate) {
		super(width, terrain, climate);
	}

}
