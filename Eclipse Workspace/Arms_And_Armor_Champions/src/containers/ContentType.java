package containers;

public enum ContentType {
	DEFAULT, MENU, MENUHOLDER;

    @Override
    public String toString() {
        return super.toString();
    }
	
	public static ContentType parseType(String type){
		ContentType T = DEFAULT;
		for(int i = 0; i < values().length; i++){
			if(values()[i].toString().equals(type)){
				T = values()[i];
				break;
			}
		}
		return T;
	}
}
