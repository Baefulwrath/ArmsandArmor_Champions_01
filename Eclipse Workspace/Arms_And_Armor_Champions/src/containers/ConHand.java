package containers;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import scripting.Scripthandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rapplebob.ArmsAndArmorChampions.AAA_C;

public class ConHand {

    public static HashMap<String, Container> cons = new HashMap<String, Container>();
    public static float windowTransparency = 1.0f;
    public static Menuholder[] MHs = new Menuholder[3];
    public static SharedTexture[] sharedTextures = new SharedTexture[0];
    
    public static void load(){
    	MHs[0] = new Main_Menuholder();
    	MHs[1] = new Game_Menuholder();
    	MHs[2] = new Editor_Menuholder();
        loadContainers();
        loadSharedTextures();
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
    	for(Map.Entry<String, Container> entry : cons.entrySet()){
    		cons.get(entry.getKey()).update();
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
        	cons = new HashMap<String, Container>();
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
	                	String contentArea = text.substring(text.indexOf("#") + 2);
	                	while(!statement.equals("-<ENDOFFILE>-")){
	                		String contentData = "";
	                		ContentType contentType = ContentType.parseType(statement.substring(statement.indexOf("(") + 1, statement.indexOf(")")));
	                		contentData = contentArea.substring(contentArea.indexOf("<") + 3, contentArea.indexOf(">") - 2);
	                		CT.add(createContentFromString(contentType, contentData));
	                		contentArea = contentArea.substring(contentArea.indexOf(">") + 2);
	                		
	                		//Flytta fram reader till rätt ställe...
	                		while(!reader.hasNext(">")){
	                			reader.nextLine();
	                		}
                			reader.nextLine();
	                		statement = reader.nextLine();
	                	}
	                	cons.put(id, CT);
	                }
	            }
            }
    	}catch(Exception ex){
    		ex.printStackTrace(System.out);
    	}
    }
    
    public static Content createContentFromString(ContentType CType, String s){
    	Content C = new nullContent();
    	if(!s.isEmpty()){
	    	switch(CType){
			case MENU: C = Menu.parseMenu(s);
				break;
			case MENUHOLDER:
				break;
			default:
				break;
	    	}
    	}
    	return C;
    }
    
    public static boolean collides(Rectangle r, ContainerType type){
    	boolean temp = false;
    	for(Map.Entry<String, Container> entry : cons.entrySet()){
    		if(cons.get(entry.getKey()).TYPE == type && cons.get(entry.getKey()).collides(r)){
    			temp = true;
    			break;
    		}
    	}
    	return temp;
    }
    
    public static Container[] getCons(ContainerType type){
    	ArrayList<Container> C = new ArrayList<Container>();
    	for(Map.Entry<String, Container> entry : cons.entrySet()){
    		if(cons.get(entry.getKey()).TYPE == type){
    			C.add(cons.get(entry.getKey()));
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
    	for(Map.Entry<String, Container> entry : cons.entrySet()){
    		if(cons.get(entry.getKey()).getBox().intersects(AAA_C.inputhandler.staticMouse) && cons.get(entry.getKey()).ACTIVE){
    			C = cons.get(entry.getKey());
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
    
    public static Container getContainer(String Id){
    	Container C = new Container("", "", false, 0, 0, 0, 0, 0, ContainerState.STATIC, ContainerType.DEFAULT, Alignment.CENTER, Fill.NONE, true, 1.0f, false);
    	if(cons.containsKey(Id)){
    		C = cons.get(Id);
    	}
    	return C;
    }
    
    public static Container getContainerByAct(String actId){
    	Container C = new Container("", "", false, 0, 0, 0, 0, 0, ContainerState.STATIC, ContainerType.DEFAULT, Alignment.CENTER, Fill.NONE, true, 1.0f, false);
    	for(Map.Entry<String, Container> entry : cons.entrySet()){
        	for(Map.Entry<String, Content> entry2 : cons.get(entry.getKey()).CONTENT.entrySet()){
    			if(){
    				
    			}
    		}
    	}
    	return C;
    }
    
    public static void clearMoving(){
    	for(Map.Entry<String, Container> entry : cons.entrySet()){
    		cons.get(entry.getKey()).stop();
    	}
    }
    
    public static void moveToFront(Container C){
    	Container[] temp = new Container[cons.size()];
    	int index = 1;
    	for(Map.Entry<String, Container> entry : cons.entrySet()){
    		if(!C.ID.equals(entry.getKey())){
    			temp[index] = cons.get(entry.getKey());
        		index++;
    		}
    	}
    	temp[0] = C;
    	int i = 0;
    	for(Map.Entry<String, Container> entry : cons.entrySet()){
    		cons.put(entry.getKey(), temp[i]);
    		i++;
    	}
    }
    
    public static void moveCons(int diffX, int diffY){
    	for(Map.Entry<String, Container> entry : cons.entrySet()){
    		if(cons.get(entry.getKey()).move(diffX, diffY)){
    			moveToFront(cons.get(entry.getKey()));
    		}
    	}
    }
    
    public static void loadSharedTextures(){
    	try{
    		String path = "data/images/containerImages/";
    		String index = Gdx.files.internal(path + "INDEX.txt").readString();
        	ArrayList<String> texFiles = new ArrayList<String>();
        	ArrayList<String> regFiles = new ArrayList<String>();
        	ArrayList<SharedTexture> STs = new ArrayList<SharedTexture>();
        	while(index.contains(":")){
        		if(index.substring(4).equals("TEX_")){
            		texFiles.add(index.substring(1, index.indexOf(";")));
        		}else if(index.substring(4).equals("REG_")){
            		regFiles.add(index.substring(1, index.indexOf(";")));
        		}
        		index = index.substring(index.indexOf(";") + 1);
        	}
            if(texFiles.size() > 0){
	            for(int i = 0; i < texFiles.size(); i++){
	            	STs.add(new SharedTexture(new Texture(Gdx.files.internal(path + texFiles.get(i))), texFiles.get(i).substring(5, texFiles.get(i).indexOf("."))));
	            }
            }
            if(regFiles.size() > 0){
	            for(int i = 0; i < regFiles.size(); i++){
	            	int width = Integer.parseInt(regFiles.get(i).substring(5, regFiles.get(i).indexOf('_', 5)));
	            	Texture img = new Texture(Gdx.files.internal(path + regFiles.get(i).substring(regFiles.get(i).indexOf('_', 5) + 1, regFiles.get(i).indexOf('#'))));
	                String text = Gdx.files.internal(path + regFiles.get(i).substring(regFiles.get(i).indexOf('#') + 1)).readString();
	                Scanner reader = new Scanner(text);
	                while(reader.hasNext("*")){
	                	String id = reader.nextLine();
	                	int pos = Integer.parseInt(reader.nextLine());
	                	STs.add(new SharedTexture(new TextureRegion(img, width * pos, 0, width, width), id));
	                }
	            }
            }
            sharedTextures = new SharedTexture[STs.size()];
            for(int i = 0; i < STs.size(); i++){
            	sharedTextures[i] = STs.get(i);
            }
    	}catch(Exception ex){}
    }
    
    public static TextureRegion getSharedTexture(String id){
    	TextureRegion temp = new TextureRegion();
    	for(int i = 0; i < sharedTextures.length; i++){
    		if(sharedTextures[i].ID.equals(id)){
    			temp = sharedTextures[i].getTex();
    			break;
    		}
    	}
    	return temp;
    }
    
    public static Activator getActById(String id){
    	Activator A = new Activator();
    	for(Map.Entry<String, Container> entry : cons.entrySet()){
    		if(cons.get(entry.getKey()).getActById(id, A)){
    			break;
    		}
    	}
    	return A;
    }
}
