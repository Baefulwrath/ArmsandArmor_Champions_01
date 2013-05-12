package containers;

public enum Alignment {
	TOP_LEFT, TOP_CENTER, TOP_RIGHT, LEFT, CENTER, RIGHT, BOTTOM_LEFT, BOTTOM_CENTER, BOTTOM_RIGHT;
	
    @Override
    public String toString() {
        return super.toString();
    }
	
	public static Alignment parseAlignment(String alignment){
		Alignment A = CENTER;
		for(int i = 0; i < values().length; i++){
			if(values()[i].toString().equals(alignment)){
				A = values()[i];
				break;
			}
		}
		return A;
	}
	
	public static int getX(float width, Alignment A){
		int x = 0;
		int w = (int) width;
		switch(A){
			case TOP_LEFT:x = -(w / 2);break;
			case TOP_CENTER:x = 0;break;
			case TOP_RIGHT:x = (w / 2);break;
			case LEFT:x = -(w / 2);break;
			case CENTER:x = 0;break;
			case RIGHT:x = (w / 2);break;
			case BOTTOM_LEFT:x = -(w / 2);break;
			case BOTTOM_CENTER:x = 0;break;
			case BOTTOM_RIGHT:x = (w / 2);break;
		}
		return x;
	}
	
	public static int getY(float height, Alignment A){
		int y = 0;
		int h = (int) height;
		switch(A){
			case TOP_LEFT:y = (h / 2);break;
			case TOP_CENTER:y = (h / 2);break;
			case TOP_RIGHT:y = (h / 2);break;
			case LEFT:y = 0;break;
			case CENTER:y = 0;break;
			case RIGHT:y = 0;break;
			case BOTTOM_LEFT:y = -(h / 2);break;
			case BOTTOM_CENTER:y = -(h / 2);break;
			case BOTTOM_RIGHT:y = -(h / 2);break;
		}
		return y;
	}
	
}
