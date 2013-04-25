import java.awt.Rectangle;


public class Button {

	public String TITLE = "";
	public String CMD = "";
	public Rectangle BOX = new Rectangle();
	public boolean hover = false;
	
	public Button(String title, String cmd, int width, int x, int y){
		TITLE = title;
		CMD = cmd;
		BOX = new Rectangle(x, y, width, 16);
	}
}
