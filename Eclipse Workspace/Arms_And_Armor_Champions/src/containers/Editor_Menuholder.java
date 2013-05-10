package containers;

import com.rapplebob.ArmsAndArmorChampions.State;

public class Editor_Menuholder extends Menuholder {
	public Editor_Menuholder(){
    	super("editor", -300, 200);
        loadMenusFromFolder("data/content/menus/editor/");
        gameState = State.EDITOR;
	}
}
