package main;

public enum Terrain {
	DEFAULT, SNOW, ARCTICHILL, GLACIER, FROZENLAKE, STEPPE, CONIFER, SUBARCTICHILL, HILLSANDCONIFER, SUBARCTICMOUNTAIN, SUBARCTICLAKE, GRASSLAND, DECIDUOUS, TEMPERATEHILL, HILLSANDDECIDUOUS,
	TEMPERATELAKE, TEMPERATEMOUNTAIN, SAVANNA, JUNGLE, TROPICALHILL, HILLSANDJUNGLE, TROPICALMOUNTAIN, TROPICALLAKE, DESERT, ARIDHILL, ARIDMOUNTAIN, OASIS, COASTALSEA, OCEAN, REEF;
	
	public Climate getClimate(){
		switch(this){
			case SNOW:return Climate.ARCTIC;
			case ARCTICHILL:return Climate.ARCTIC;
			case GLACIER:return Climate.ARCTIC;
			case FROZENLAKE:return Climate.ARCTIC;
			case STEPPE:return Climate.SUBARCTIC;
			case CONIFER:return Climate.SUBARCTIC;
			case SUBARCTICHILL:return Climate.SUBARCTIC;
			case HILLSANDCONIFER:return Climate.SUBARCTIC;
			case SUBARCTICMOUNTAIN:return Climate.SUBARCTIC;
			case SUBARCTICLAKE:return Climate.SUBARCTIC;
			case GRASSLAND:return Climate.TEMPERATE;
			case DECIDUOUS:return Climate.TEMPERATE;
			case TEMPERATEHILL:return Climate.TEMPERATE;
			case HILLSANDDECIDUOUS:return Climate.TEMPERATE;
			case TEMPERATELAKE:return Climate.TEMPERATE;
			case TEMPERATEMOUNTAIN:return Climate.TEMPERATE;
			case SAVANNA:return Climate.TROPICAL;
			case JUNGLE:return Climate.TROPICAL;
			case TROPICALHILL:return Climate.TROPICAL;
			case HILLSANDJUNGLE:return Climate.TROPICAL;
			case TROPICALMOUNTAIN:return Climate.TROPICAL;
			case TROPICALLAKE:return Climate.TROPICAL;
			case DESERT:return Climate.ARID;
			case ARIDHILL:return Climate.ARID;
			case ARIDMOUNTAIN:return Climate.ARID;
			case OASIS:return Climate.ARID;
			case COASTALSEA:return Climate.OCEAN;
			case OCEAN:return Climate.OCEAN;
			case REEF:return Climate.OCEAN;
			default:return Climate.DEFAULT;
		}
	}

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
    	return temp;
    }
}
