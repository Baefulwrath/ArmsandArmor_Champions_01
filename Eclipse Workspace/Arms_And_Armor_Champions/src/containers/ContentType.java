package containers;

public enum ContentType {
	DEFAULT, MENU, MENUHANDLER;

    @Override
    public String toString() {
        return super.toString();
    }
	
	public static ContentType parseState(String state){
		ContentType T = DEFAULT;
		for(int i = 0; i < values().length; i++){
			if(values()[i].toString().equals(state)){
				T = values()[i];
				break;
			}
		}
		return T;
	}
}
