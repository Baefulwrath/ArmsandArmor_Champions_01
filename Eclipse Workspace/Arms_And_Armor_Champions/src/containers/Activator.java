package containers;

import java.awt.Rectangle;

import scripting.Scripthandler;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Activator {
    public ActivatorType AT = ActivatorType.BUTTON;
    public String title = "";
    public String ID = "";
    public String script = "";
    public String updateScript = "";
    public boolean highlit = false;
    public Rectangle BOX = new Rectangle(0, 0, 0, 0);
    public boolean textured = false;
    public TextureRegion TEX;
    
    public void update(){
    	Scripthandler.handleScript(updateScript);
    }
    
    public void set(ActivatorType type, String id, String t, String uScript, String s, Rectangle r){
        AT = type;
        ID = id;
        title = t;
        updateScript = uScript;
        script = s;
        BOX = r;
    }
    
    public void set(ActivatorType type, String id, String t, String uScript, String s, Rectangle r, TextureRegion tex){
        AT = type;
        ID = id;
        title = t;
        updateScript = uScript;
        script = s;
        BOX = r;
        TEX = tex;
        textured = true;
    }
    
    public boolean intersects(Rectangle r, int x, int y){
    	boolean temp = false;
    	Rectangle box = new Rectangle(BOX.x + x, BOX.y + y, BOX.width, BOX.height);
    	if(r.intersects(box)){
    		temp = true;
    	}
    	return temp;
    }
    
    public boolean intersects(Rectangle r){
    	boolean temp = false;
    	if(r.intersects(BOX)){
    		temp = true;
    	}
    	return temp;
    }
}
