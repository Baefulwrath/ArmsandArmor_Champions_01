package containers;

public enum ContainerState {
	MOBILE, STATIC;

    @Override
    public String toString() {
        return super.toString();
    }
	
	public static ContainerState parseState(String state){
		ContainerState T = STATIC;
		for(int i = 0; i < values().length; i++){
			if(values()[i].toString().equals(state)){
				T = values()[i];
				break;
			}
		}
		return T;
	}
}
