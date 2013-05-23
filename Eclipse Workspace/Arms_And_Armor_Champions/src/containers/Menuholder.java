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
    	System.out.println("_______________");
    	System.out.println(m.menuTitle + " - " + getActiveMenu().activeAct);
        for(int i = 0; i < m.acts.size(); i++){
        	System.out.println(m.acts.get(i).title);
        }
    	System.out.println("_______________");
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
		                    Menu m = new Menu(0, 0);
		                    String ID = "";
		                    String title = "";
		                    ArrayList<Activator> acts = new ArrayList<Activator>();
		                    String text = file.readString();
		                    
		                    reader = new Scanner(text);
		                    ID = reader.nextLine();
		                    title = reader.nextLine();
		                    reader.nextLine();
		                    while(!reader.hasNext("}")){
		                        String line = reader.nextLine();
		                        String aBox = line.substring(line.indexOf("[") + 1, line.indexOf("]"));
		                        line = line.substring(line.indexOf("]") + 1);
		                        int ax = Integer.parseInt(aBox.substring(0, aBox.indexOf(":")));
		                        aBox = aBox.substring(aBox.indexOf(":") + 1);
		                        int ay = Integer.parseInt(aBox.substring(0, aBox.indexOf(":")));
		                        aBox = aBox.substring(aBox.indexOf(":") + 1);
		                        int aw = Integer.parseInt(aBox.substring(0, aBox.indexOf(":")));
		                        aBox = aBox.substring(aBox.indexOf(":") + 1);
		                        int ah = Integer.parseInt(aBox);
		                        String atitle = line.substring(line.indexOf("(") + 1, line.indexOf(")"));
		                        String script = line.substring(line.indexOf(":") + 1, line.indexOf(";"));
		                        ActivatorType AT = ActivatorType.parseType(line.substring(line.indexOf("[") + 1, line.indexOf("]")));
		                        Activator act = new Activator();
		                        act.set(AT, atitle, script, new Rectangle(ax, ay, aw, ah));
		                        acts.add(act);
		                    }
		                    m.set(ID, title, acts);
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
}
