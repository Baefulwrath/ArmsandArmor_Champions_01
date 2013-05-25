package containers;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Scanner;

import scripting.Scripthandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.rapplebob.ArmsAndArmorChampions.AAA_C;

public class ConHand {

    public static Container[] cons = new Container[0];
    public static float windowTransparency = 1.0f;
    public static Menuholder[] MHs = new Menuholder[3];
    
    public static void load(){
    	MHs[0] = new Main_Menuholder();
    	MHs[1] = new Game_Menuholder();
    	MHs[2] = new Editor_Menuholder();
        loadContainers();
    }
    
    public static void update(){
    	updateMenuhandlers();
    	updateContainers();
    }
    
    public static Menuholder getMenuholderByName(String name){
    	Menuholder mh = MHs[0];
        for(int i = 0; i < MHs.length; i++){
        	if(MHs[i].NAME == name){
        		mh = MHs[i];
        		break;
        	}
        }
    	return mh;
    }
    
    public static Menuholder getActiveMenuholder(){
    	Menuholder mh = MHs[0];
        for(int i = 0; i < MHs.length; i++){
        	if(MHs[i].gameState == AAA_C.state){
        		mh = MHs[i];
        		break;
        	}
        }
        return mh;
    }
    
    public static Activator getActiveAct(){
        return getActiveMenu().getActiveActivator();
    }
    
    public static Menu getActiveMenu(){
        return getActiveMenuholder().getActiveMenu();
    }
    
    public static void resetMenuholders(){
        for(int i = 0; i < MHs.length; i++){
        	MHs[i].activeMenu = 0;
        }
    }
    
    public static void updateMenuhandlers(){
        for(int i = 0; i < MHs.length; i++){
        	MHs[i].update();
        }
    }
    
    public static void updateContainers(){
    	for(int i = 0; i < cons.length; i++){
    		cons[i].update();
    	}
    }
    
    public static void loadContainers(){
    	try{
    		String path = "data/content/windows/";
    		String index = Gdx.files.internal(path + "INDEX.txt").readString();
        	ArrayList<String> files = new ArrayList<String>();
        	while(index.contains(":")){
        		files.add(index.substring(1, index.indexOf(";")));
        		index = index.substring(index.indexOf(";") + 1);
        	}
        	cons = new Container[files.size()];
            Scanner reader;
            if(files.size() > 0){
	            for(int i = 0; i < files.size(); i++){
	                FileHandle file = Gdx.files.internal(path + files.get(i));
	                if(file.extension().equals("txt")){
	                	String text = file.readString();
	                	reader = new Scanner(text);
	                	
	                	String title = reader.nextLine().substring(5);
	                	String id = reader.nextLine().substring(5);
	                	boolean active = Boolean.parseBoolean(reader.nextLine().substring(5));
	                	int x = Integer.parseInt(reader.nextLine().substring(5));
	                	int y = Integer.parseInt(reader.nextLine().substring(5));
	                	int width = Integer.parseInt(reader.nextLine().substring(5));
	                	int height = Integer.parseInt(reader.nextLine().substring(5));
	                	int conSize = Integer.parseInt(reader.nextLine().substring(5));
	                	ContainerState state = ContainerState.parseState(reader.nextLine().substring(5));
	                	ContainerType type = ContainerType.parseState(reader.nextLine().substring(5));
	                	Alignment alig = Alignment.parseAlignment(reader.nextLine().substring(5));
	                	Fill fill = Fill.parseFill(reader.nextLine().substring(5));
	                	boolean decorated = Boolean.parseBoolean(reader.nextLine().substring(5));
	                	float transparency = Float.parseFloat(reader.nextLine().substring(5));
	                	boolean back = Boolean.parseBoolean(reader.nextLine().substring(5));
	                	reader.nextLine();
	                	Container CT = new Container(title, id, active, x, y, width, height, conSize, state, type, alig, fill, decorated, transparency, back);
	                	String statement = reader.nextLine();
	                	while(!statement.equals("-<ENDOFFILE>-")){
	                		String contentData = "";
	                		String line = statement + reader.nextLine();
	                		while(!line.equals(">")){
	                			contentData += line;
	                			line = reader.nextLine();
	                		}
	                		contentData += line;
	                		CT.add(createContentFromString(contentData));
	                		statement = reader.nextLine();
	                	}
	                	cons[i] = CT;
	                }
	            }
            }
    	}catch(Exception ex){
    		ex.printStackTrace(System.out);
    	}
    }
    
    public static Content createContentFromString(String s){
    	Content C = new nullContent();
    	ContentType CType = ContentType.parseType(s.substring(1, s.indexOf(")")));
    	switch(CType){
		case MENU: C = Menu.parseMenu(s.substring(s.indexOf("<") + 1, s.indexOf(">")));
			break;
		case MENUHOLDER:
			break;
		default:
			break;
    	}
    	return C;
    }
    
    public static boolean collides(Rectangle r, ContainerType type){
    	boolean temp = false;
    	for(int i = 0; i < cons.length; i++){
    		if(cons[i].TYPE == type && cons[i].collides(r)){
    			temp = true;
    			break;
    		}
    	}
    	return temp;
    }
    
    public static Container[] getCons(ContainerType type){
    	ArrayList<Container> C = new ArrayList<Container>();
    	for(int i = 0; i < cons.length; i++){
    		if(cons[i].TYPE == type){
    			C.add(cons[i]);
    		}
    	}
    	Container[] CA = new Container[C.size()];
    	for(int i = 0; i < C.size(); i++){
    		CA[i] = C.get(i);
    	}
    	return CA;
    }
    
    public static Container getActiveContainer(){
    	Container C = new Container("", "", false, 0, 0, 0, 0, 0, ContainerState.STATIC, ContainerType.DEFAULT, Alignment.CENTER, Fill.NONE, true, 1.0f, false);
    	for(int i = 0; i < cons.length; i++){
    		if(cons[i].getBox().intersects(AAA_C.inputhandler.staticMouse) && cons[i].ACTIVE){
    			C = cons[i];
    			break;
    		}
    	}
    	return C;
    }
    
    public static void leftClick(Rectangle r){
    	Container con = getActiveContainer();
    	if(con.ACTIVE){
    		boolean deco = false;
    		if(con.DECORATED){
    			deco = leftClick_Decoration(r);
    		}
    		if(!deco){
    			for(int i = 0; i < con.CONTENT.size(); i++){
    				switch(con.CONTENT.get(i).TYPE){
					case DEFAULT:
						break;
					case MENU: leftClick_Menu(r, con.CONTENT.get(i));
						break;
					case MENUHOLDER:
						break;
					default:
						break;
    					
    				}
    			}
    		}
    	}
    }
    
    public static boolean leftClick_Decoration(Rectangle r){
    	boolean clicked = false;
    	Container con = getActiveContainer();
    	int x = con.getBox().x;
    	int y = con.getBox().y;
    	if(r.intersects(con.getConBox())){
    		clicked = true;
	    	if(con.EXIT.intersects(r, x, y)){
	    		Scripthandler.handleScript(getActiveContainer().EXIT.script);
	    	}else{
	    		if(con.STATE != ContainerState.STATIC){
	    			con.MOVING = true;
	    		}
	    	}
    	}
    	return clicked;
    }
    
    public static void leftClick_Menu(Rectangle r, Content c){
    	Menu m = (Menu) c;
    	if(m.hover){
    		Scripthandler.handleScript(m.getActiveActivator().script);
    	}
    }
    
    public static Container getContainer(String ID){
    	Container C = new Container("", "", false, 0, 0, 0, 0, 0, ContainerState.STATIC, ContainerType.DEFAULT, Alignment.CENTER, Fill.NONE, true, 1.0f, false);
    	for(int i = 0; i < cons.length; i++){
    		if(cons[i].ID.equals(ID)){
    			C = cons[i];
    			break;
    		}
    	}
    	return C;
    }
    
    public static void clearMoving(){
    	for(int i = 0; i < cons.length; i++){
    		cons[i].stop();
    	}
    }
    
    public static void moveToFront(Container C){
    	Container[] temp = new Container[cons.length];
    	int index = 1;
    	for(int i = 0; i < cons.length; i++){
    		if(!C.ID.equals(cons[i].ID)){
    			temp[index] = cons[i];
        		index++;
    		}
    	}
    	temp[0] = C;
    	for(int i = 0; i < cons.length; i++){
    		cons[i] = temp[i];
    	}
    }
    
    public static void moveCons(int diffX, int diffY){
    	for(int i = 0; i < cons.length; i++){
    		if(cons[i].move(diffX, diffY)){
    			moveToFront(cons[i]);
    		}
    	}
    }
}
