package containers;

public abstract class Content {
	
	public int X;
	public int Y;
	public ContentType TYPE = ContentType.DEFAULT;
	
	public Content(int x, int y, ContentType type){
		X = x;
		Y = y;
		TYPE = type;
	}
}
