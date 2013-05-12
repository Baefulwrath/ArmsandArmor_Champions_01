package containers;

public enum Fill {
	NONE, WIDTH, HEIGHT, FULL;

    @Override
    public String toString() {
        return super.toString();
    }
	
	public static Fill parseFill(String fill){
		Fill F = NONE;
		for(int i = 0; i < values().length; i++){
			if(values()[i].toString().equals(fill)){
				F = values()[i];
				break;
			}
		}
		return F;
	}
	
	public static int getWidth(float width, Fill F){
		int screenW = (int) width;
		int w = 0;
		switch(F){
			case NONE:w = 0;
			break;
			case WIDTH:w = screenW;
			break;
			case HEIGHT:w = 0;
			break;
			case FULL:w = screenW;
			break;
		}
		return w;
	}
	
	public static int getHeight(float height, Fill F){
		int screenH = (int) height;
		int h = 0;
		switch(F){
			case NONE:h = 0;
			break;
			case WIDTH:h = 0;
			break;
			case HEIGHT:h = screenH;
			break;
			case FULL:h = screenH;
			break;
		}
		return h;
	}
}
