package containers;

import com.rapplebob.ArmsAndArmorChampions.State;

public class Game_Menuholder extends Menuholder {
    public Game_Menuholder(){
    	super("game", -300, 200);
        loadMenusFromFolder("data/content/menus/game/");
        gameState = State.GAME;
    }
}
