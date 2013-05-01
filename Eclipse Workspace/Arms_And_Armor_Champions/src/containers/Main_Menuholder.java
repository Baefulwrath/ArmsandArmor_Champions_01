package containers;

public class Main_Menuholder extends Menuholder {
    public Main_Menuholder(){
    	super(-300, 200);
        loadMenusFromFolder("data/content/menus/main/");
    }
}
