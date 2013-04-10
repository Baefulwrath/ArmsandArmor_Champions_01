package com.rapplebob.ArmsAndArmorChampions;

public enum State {
    DEFAULT, MENU, GAME;
    
    
    @Override
    public String toString() {
        return super.toString();
    }
    
    public static State getStateByString(String state){
    	State temp = DEFAULT;
    	for(int i = 0; i < values().length; i++){
    		if(state.equals(values()[i].toString())){
    			temp = values()[i];
    		}
    	}
    	return temp;
    }
}
