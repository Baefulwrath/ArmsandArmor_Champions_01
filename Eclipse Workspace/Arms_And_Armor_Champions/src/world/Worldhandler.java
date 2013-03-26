package world;

import java.util.ArrayList;

public class Worldhandler {
    public int activeMap = 0;
    public ArrayList<Map> maps = new ArrayList<Map>();
    
    public Map getMap(){
        return maps.get(activeMap);
    }
    
    public int[] getActiveCells(){
        return getMap().getActiveCells();
    }
    
    public void load(){
        test();
    }
    
    public void test(){
        maps.add(new Map());
        getMap().test();
    }
}
