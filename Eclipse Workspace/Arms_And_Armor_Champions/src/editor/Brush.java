package editor;

import java.awt.Rectangle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import world.*;

public class Brush {
	public Rectangle BOX = new Rectangle(0, 0, 0, 0);
	public int size = 100;
	public Cell CELL = new Cell(Worldhandler.hexWidth, 1, 1);
	public Texture IMG;
	public boolean loaded = false;
	
	public Brush(){
		try{
			IMG = new Texture(Gdx.files.internal("data/images/brush.png"));
			loaded = true;
		}catch(Exception ex){
			ex.printStackTrace(System.out);
			loaded = false;
		}
	}
	
	public void mouseMoved(int diffX, int diffY){
		BOX = new Rectangle(BOX.x + diffX, BOX.y + diffY, size, size);
	}
	
	public void setPosition(int x, int y, boolean centered){
		if(centered){
			x -= size / 2;
			y -= size / 2;
		}
		BOX = new Rectangle(x, y, size, size);
	}
}
