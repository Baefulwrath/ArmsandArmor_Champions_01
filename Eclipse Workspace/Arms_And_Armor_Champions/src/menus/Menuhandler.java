package menus;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class Menuhandler {
    public ArrayList<Menu> menus = new ArrayList<Menu>();
    public int activeMenu = 0;
    public int x = -300;
    public int y = 250;
    
    public void openMenuByID(String ID){
        for(int i = 0; i < menus.size(); i++){
            if(menus.get(i).ID.equals(ID)){
                activeMenu = i;
                break;
            }
        }
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
            FileHandle handle = Gdx.files.external(path);
            //Loopa igenom filer
            Scanner reader;
            //Test Bajs
            for(int i = 0; i < folder.list().length; i++){
                File file = new File(folder.file().getAbsolutePath() + "/" + folder.list()[i]);
                if(file.getName().substring(file.getName().length() - 4).equals(".txt")){
                    Menu m = new Menu();
                    String ID = "";
                    String title = "";
                    ArrayList<Activator> acts = new ArrayList<Activator>();
                    reader = new Scanner(file);
                    ID = reader.nextLine();
                    title = reader.nextLine();
                    String actList = "";
                    reader.nextLine();
                    while(!reader.hasNext("}")){
                        String line = reader.nextLine();
                        Activator act = new Activator();
                        String atitle = line.substring(line.indexOf("(") + 1, line.indexOf(")"));
                        String script = line.substring(line.indexOf(":") + 1, line.indexOf(";"));
                        ActivatorType AT = ActivatorType.parseType(line.substring(line.indexOf("[") + 1, line.indexOf("]")));
                        act.set(AT, atitle, script);
                        acts.add(act);
                    }
                    m.set(ID, title, acts);
                    menus.add(m);
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public Menu getActiveMenu(){
        return menus.get(activeMenu);
    }
}
