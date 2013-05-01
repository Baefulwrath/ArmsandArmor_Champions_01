package containers;

import java.awt.Rectangle;
import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class Menu extends Content{
    public String ID = "";
    public String menuTitle = "";
    public ArrayList<Activator> acts = new ArrayList<Activator>();
    public int activeAct = 0;
    public Menu(int x, int y){
    	super(x, y, ContentType.MENU);
    }
    
    public void set(String id, String t, ArrayList<Activator> a){
        ID = id;
        menuTitle = t;
        acts = a;
    }
    public void up(){
        activeAct--;
        testIndex();
    }
    public void down(){
        activeAct++;
        testIndex();
    }
    public void testIndex(){
        if(activeAct >= acts.size()){
            activeAct = 0;
        }else if(activeAct < 0){
            activeAct = acts.size() - 1;
        }
    }
    public void update(){
        testIndex();
    }
    public Activator getActiveActivator(){
        return acts.get(activeAct);
    }
    
    public boolean thereAreHighlitActs(){
        boolean temp = false;
        for(int i = 0; i < acts.size(); i++){
            if(acts.get(i).highlit){
                temp = true;
                break;
            }
        }
        return temp;
    }
    
    public void clearHighlights(){
        for(int i = 0; i < acts.size(); i++){
            acts.get(i).highlit = false;
        }
    }
    
    public boolean testMouseHover(Rectangle mouse, Rectangle menu, BitmapFont font){
        Rectangle a;
        boolean temp = false;
        for(int i = 0; i < acts.size(); i++){
            a = new Rectangle(menu.x, menu.y - 50 - (i * 40), 5000, (int) font.getBounds(acts.get(i).title).height);
            if(mouse.intersects(a)){
                activeAct = i;
                temp = true;
                break;
            }
        }
        return temp;
    }
}
