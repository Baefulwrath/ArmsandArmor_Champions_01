package containers;

public enum ContainerType {
	MAIN, GAME;

    @Override
    public String toString() {
        return super.toString();
    }
	
	public ContainerType parseState(String state){
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
