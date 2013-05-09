package containers;

public class Editor_Menuholder extends Menuholder {
	public Editor_Menuholder(){
    	super(-300, 200);
        loadMenusFromFolder("data/content/menus/editor/");
	}
}
