package containers;

import java.awt.Rectangle;

public class Activator {
    public ActivatorType AT = ActivatorType.BUTTON;
    public String title = "";
    public String script = "";
    public boolean highlit = false;
    public Rectangle BOX = new Rectangle(0, 0, 0, 0);
    
    public void set(ActivatorType type, String t, String s, Rectangle r){
        AT = type;
        title = t;
        script = s;
        BOX = r;
    }
}
