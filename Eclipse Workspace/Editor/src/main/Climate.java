package main;


public enum Climate {
	DEFAULT, TEMPERATE, SUBARCTIC, ARCTIC, TROPICAL, ARID, OCEAN;

	public Terrain[] getTerrains(){
		Terrain[] T;
 		switch(this){
 			case DEFAULT:T = Terrain.values();
		break;
			case TEMPERATE:Terrain[] temp = {Terrain.DEFAULT, Terrain.GRASSLAND, Terrain.DECIDUOUS, Terrain.TEMPERATEHILL, Terrain.HILLSANDDECIDUOUS, Terrain.TEMPERATELAKE, Terrain.TEMPERATEMOUNTAIN};
			T = temp;
		break;
			case SUBARCTIC:Terrain[] sub = {Terrain.DEFAULT, Terrain.STEPPE, Terrain.CONIFER, Terrain.SUBARCTICHILL, Terrain.HILLSANDCONIFER, Terrain.SUBARCTICMOUNTAIN, Terrain.SUBARCTICLAKE};
			T = sub;
		break;
			case ARCTIC:Terrain[] arc = {Terrain.DEFAULT, Terrain.SNOW, Terrain.ARCTICHILL, Terrain.GLACIER, Terrain.FROZENLAKE};
			T = arc;
		break;
			case TROPICAL:Terrain[] trop = {Terrain.DEFAULT, Terrain.SAVANNA, Terrain.JUNGLE, Terrain.TROPICALHILL, Terrain.HILLSANDJUNGLE, Terrain.TROPICALMOUNTAIN, Terrain.TROPICALLAKE};
			T = trop;
		break;
			case ARID:Terrain[] arid = {Terrain.DEFAULT, Terrain.DESERT, Terrain.ARIDHILL, Terrain.ARIDMOUNTAIN, Terrain.OASIS};
			T = arid;
		break;
			case OCEAN:Terrain[] ocean = {Terrain.DEFAULT, Terrain.COASTALSEA, Terrain.OCEAN, Terrain.REEF};
			T = ocean;
		break;
			default:T = Terrain.values();
		}
 		return T;
	}
	
	public String[] getTerrainsString(){
		Terrain[] T = getTerrains();
		String[] S = new String[T.length];
		for(int i = 0; i < T.length; i++){
			S[i] = T[i].toString();
		}
		return S;
	}
	
    @Override
    public String toString() {
        return super.toString();
    }
    
    public static Climate parseClimate(String value){
    	Climate temp = DEFAULT;
    	for(int i = 0; i < values().length; i++){
    		if(value.equals(values()[i].toString())){
    			temp = values()[i];
    		}
    	}
    	return temp;
    }
}
