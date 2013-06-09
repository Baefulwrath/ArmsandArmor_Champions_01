package containers;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Scanner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.rapplebob.ArmsAndArmorChampions.State;

public class Menuholder extends Content{
    public Menuholder(String name, int x, int y) {
		super(x, y, ContentType.MENUHOLDER);
		NAME = name;
	}

	public ArrayList<Menu> menus = new ArrayList<Menu>();
    public int activeMenu = 0;
    public State gameState = State.DEFAULT;
    public String NAME = "";
    
    public void openMenuByID(String ID){
        for(int i = 0; i < menus.size(); i++){
            if(menus.get(i).ID.equals(ID)){
                activeMenu = i;
                break;
            }
        }
        //soutMenu(getActiveMenu());
    }
    
    public void soutMenu(Menu m){
    	//In case of disaster: sout the menu to console.
    	m.sout();
    }
    
    public void update(){
        for(int i = 0; i < menus.size(); i++){
            menus.get(i).update();
        }
    }
    
    public void loadMenusFromFolder(String path){
        try{
            //Rensa menyer
            menus.clear();
            //Öppna mappen
            	String index = Gdx.files.internal(path + "INDEX.txt").readString();
            	ArrayList<String> files = new ArrayList<String>();
            	while(index.contains(":")){
            		files.add(index.substring(1, index.indexOf(";")));
            		index = index.substring(index.indexOf(";") + 1);
            	}
	            //Loopa igenom filer
	            Scanner reader;
	            if(files.size() > 0){
		            for(int i = 0; i < files.size(); i++){
		                FileHandle file = Gdx.files.internal(path + files.get(i));
		                if(file.extension().equals("txt")){
		                	String s = file.readString();
		                    Menu m = Menu.parseMenu(s);
		                    menus.add(m);
		                }else{
		                	System.out.println("Non-txt in index of menus");
		                }
		            }
	            }else{
	            	System.out.println("NO MENUS TO LOAD");
	            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public Menu getActiveMenu(){
        return menus.get(activeMenu);
    }

	@Override
	public void mouseMoved(Rectangle mouse, int cx, int cy) {
	}
}
