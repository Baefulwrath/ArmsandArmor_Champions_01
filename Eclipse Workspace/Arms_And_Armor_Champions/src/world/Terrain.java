package world;

public enum Terrain {
	DEFAULT, SANDPLAINS;

    
    @Override
    public String toString() {
        return super.toString();
    }
    
    public static Terrain parseTerrain(String value){
    	Terrain temp = DEFAULT;
    	for(int i = 0; i < values().length; i++){
    		if(value.equals(values()[i].toString())){
    			temp = values()[i];
    		}
    	}
    	System.out.println(value);
    	return temp;
    }
}
