package containers;

import java.util.ArrayList;
import java.util.Scanner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.rapplebob.ArmsAndArmorChampions.AAA_C;
import com.rapplebob.ArmsAndArmorChampions.State;

public class ConHand {

    public static Main_Menuholder MMH;
    public static Game_Menuholder GMH;
    public static Container[] cons = new Container[0];
    
    public static void load(){
        MMH = new Main_Menuholder();
        GMH = new Game_Menuholder();
        loadContainers();
    }
    
    public static void update(){
    	updateMenuhandlers();
    	updateContainers();
    }
    
    public static Menuholder getActiveMenuhandler(){
        if(AAA_C.state == State.GAME){
            return GMH;
        }else{
            return MMH;
        }
    }
    
    public static Activator getActiveAct(){
        return getActiveMenuhandler().getActiveMenu().getActiveActivator();
    }
    
    public static Menu getActiveMenu(){
        return getActiveMenuhandler().getActiveMenu();
    }
    
    public static void updateMenuhandlers(){
        MMH.update();
        GMH.update();
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
	                	
	                	String title = reader.nextLine();
	                	String id = reader.nextLine();
	                	boolean active = Boolean.parseBoolean(reader.nextLine());
	                	int x = Integer.parseInt(reader.nextLine());
	                	int y = Integer.parseInt(reader.nextLine());
	                	int width = Integer.parseInt(reader.nextLine());
	                	int height = Integer.parseInt(reader.nextLine());
	                	int conSize = Integer.parseInt(reader.nextLine());
	                	ContainerState state = ContainerState.parseState(reader.nextLine());
	                	ContainerType type = ContainerType.parseState(reader.nextLine());
	                	Container CT = new Container(title, id, active, x, y, width, height, conSize, state, type);
	                	cons[i] = CT;
	                }
	            }
            }
    	}catch(Exception ex){
    		ex.printStackTrace(System.out);
    	}
    }
}