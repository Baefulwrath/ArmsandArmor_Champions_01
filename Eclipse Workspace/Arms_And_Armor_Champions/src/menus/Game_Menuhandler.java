package menus;

public class Game_Menuhandler extends Menuhandler {
    public Game_Menuhandler(){
    	super(-300, 200);
        loadMenusFromFolder("data/content/menus/game/");
    }
}
