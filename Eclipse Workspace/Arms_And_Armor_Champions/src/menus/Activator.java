package menus;

public class Activator {
    public ActivatorType AT = ActivatorType.BUTTON;
    public String title = "";
    public String script = "";
    public boolean highlit = false;
    
    public void set(ActivatorType type, String t, String s){
        AT = type;
        title = t;
        script = s;
    }
}
