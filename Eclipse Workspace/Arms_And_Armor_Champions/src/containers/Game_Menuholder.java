package containers;

public class Game_Menuholder extends Menuholder {
    public Game_Menuholder(){
    	super(-300, 200);
        loadMenusFromFolder("data/content/menus/game/");
    }
}
