package containers;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Scanner;

import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class Menu extends Content{
    public String ID = "";
    public String menuTitle = "";
    public ArrayList<Activator> acts = new ArrayList<Activator>();
    public int activeAct = 0;
    public boolean hover = false;
    
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
    	for(int i = 0; i < acts.size(); i++){
    		acts.get(i).update();
    	}
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
    
    public boolean testMouseHover(Rectangle mouse, Rectangle menu){
        Rectangle a;
        boolean temp = false;
        for(int i = 0; i < acts.size(); i++){
            //a = new Rectangle(menu.x, menu.y - 50 - (i * 40), 5000, (int) font.getBounds(acts.get(i).title).height);
        	a = new Rectangle(acts.get(i).BOX.x + menu.x, acts.get(i).BOX.y + menu.y, acts.get(i).BOX.width, acts.get(i).BOX.height);
            if(mouse.intersects(a)){
                activeAct = i;
                temp = true;
                break;
            }
        }
        return temp;
    }
    
    public static Menu parseMenu(String s){
    	Menu m = new Menu(0, 0);
    	Scanner reader = new Scanner(s);
    	try{
    		int mx = Integer.parseInt(reader.nextLine());
    		int my = Integer.parseInt(reader.nextLine());
    		String mid = reader.nextLine();
    		String mtitle = reader.nextLine();
    		reader.nextLine();
    		ArrayList<Activator> acs = new ArrayList<Activator>();
    		while(!reader.hasNext("}")){
    			String line = reader.nextLine();
	    		Activator act = new Activator();
	    		String aid = line.substring(0, line.indexOf("["));
	    		int ax = Integer.parseInt(line.substring(line.indexOf("[") + 1, line.indexOf(":")));
	    		line = line.substring(line.indexOf(":") + 1);
	    		int ay = Integer.parseInt(line.substring(0, line.indexOf(":")));
	    		line = line.substring(line.indexOf(":") + 1);
	    		int aw = Integer.parseInt(line.substring(0, line.indexOf(":")));
	    		line = line.substring(line.indexOf(":") + 1);
	    		int ah = Integer.parseInt(line.substring(0, line.indexOf("]")));
	    		line = line.substring(line.indexOf("]") + 1);
	    		String uScript = line.substring(line.indexOf('"') + 1, line.indexOf('"', line.indexOf('"') + 1));
	    		String atitle = line.substring(line.indexOf("(") + 1, line.indexOf(")"));
	    		String ascript = line.substring(line.indexOf(":") + 1, line.indexOf(";"));
	    		ActivatorType atype = ActivatorType.parseType(line.substring(line.indexOf("[") + 1, line.indexOf("]")));
	    		act.set(atype, aid, atitle, uScript, ascript, new Rectangle(ax, ay, aw, ah));
	    		acs.add(act);
    		}
    		m = new Menu(mx, my);
    		m.set(mid, mtitle, acs);
    	}catch(Exception ex){
        	reader.close();
    		System.out.println("/*" + s + "*/");
    		ex.printStackTrace(System.out);
    	}
    	reader.close();
    	return m;
    }
    
    @Override
    public void print(){
    	System.out.println("Menu");
    }
    
    public void sout(){
    	//In case of disaster: sout the menu to console.
    	System.out.println("_______________");
    	System.out.println(menuTitle + " - " + activeAct);
        for(int i = 0; i < acts.size(); i++){
        	System.out.println(acts.get(i).title);
        }
    	System.out.println("_______________");
    }

	@Override
	public void mouseMoved(Rectangle mouse, int cx, int cy) {
		hover = testMouseHover(mouse, new Rectangle(X + cx, Y + cy, 0, 0));
	}
	
	public boolean actExists(String id){
		boolean temp = false;
		for(int i = 0; i < acts.size(); i++){
			if(acts.get(i).ID.equals(id)){
				temp = true;
				break;
			}
		}
		return temp;
	}
	
	public Activator getActById(String id){
		Activator temp = new Activator();
		for(int i = 0; i < acts.size(); i++){
			if(acts.get(i).ID.equals(id)){
				temp = acts.get(i);
				break;
			}
		}
		return temp;
	}
}
