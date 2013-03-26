package com.rapplebob.ArmsAndArmorChampions;

public enum State {
    DEFAULT, MENU, GAME;
    
    
    @Override
    public String toString() {
        //only capitalize the first letter
        String s = super.toString();
        return s.substring(0, 1) + s.substring(1).toLowerCase();
    }
}
