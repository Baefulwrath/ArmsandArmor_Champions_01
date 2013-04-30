package menus;

public abstract class Content {
	public Content(int x, int y, ContentType type){
		X = x;
		Y = y;
		TYPE = type;
	}
	
	public int X;
	public int Y;
	public ContentType TYPE = ContentType.DEFAULT;
}
