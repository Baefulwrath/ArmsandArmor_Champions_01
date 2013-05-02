package containers;

public enum ContainerType {
	DEFAULT, MAIN, GAME;

    @Override
    public String toString() {
        return super.toString();
    }
	
	public static ContainerType parseState(String state){
		ContainerType T = MAIN;
		for(int i = 0; i < values().length; i++){
			if(values()[i].toString().equals(state)){
				T = values()[i];
				break;
			}
		}
		return T;
	}
}
