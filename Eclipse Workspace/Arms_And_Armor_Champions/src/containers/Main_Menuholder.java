package containers;

import com.rapplebob.ArmsAndArmorChampions.State;

public class Main_Menuholder extends Menuholder {
    public Main_Menuholder(){
    	super("main", -300, 200);
        loadMenusFromFolder("data/content/menus/main/");
        gameState = State.MENU;
    }
}
