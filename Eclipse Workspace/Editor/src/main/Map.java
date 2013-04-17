package main;

import java.util.ArrayList;

public class Map {
    public String title = "";
    public String id = "";
    public ArrayList<Cell> cells = new ArrayList<Cell>();
    public int width = 30;
    public int height = 6;
    public int x = 100;
    public int y = 150;
    public boolean loaded = false;
    
    public void update(){
    	for(int i = 0; i < cells.size(); i++){
    		cells.get(i).update(x + getCellX(i), y + getCellY(i));
    	}
    }

    public void createEmptyMap(){
    	cells.clear();
        for(int i = 0; i < (width * height) ; i++){
            cells.add(new Cell(56, Main.editorTile.CLIMATE, Main.editorTile.TERRAIN));
        }
        load(0, 0);
    }
    
    public void load(int width, int height){
        loaded = true;
    }

    public int getCellX(int index){
        int temp = 0;
        int row = 0;
        int x = index;
        while(x >= width){
            x -= width;
            row++;
        }
        temp = x * (cells.get(0).RADIUS);
        return temp;
    }
    
    public int getCellY(int index){
        int temp = 0;
        int row = 0;
        int x = index;
        while(x >= width){
            x -= width;
            row++;
        }
        temp += (cells.get(0).SIDE * 3) * row;
        if(x % 2 == 0){
            temp += cells.get(0).HALFSIDE * 3;
        }
        return temp;
    }
    
    public int getHeight(){
        int temp = 1;
        int tempi = cells.size();
        while(tempi > width){
            temp++;
            tempi -= width;
        }
        return temp;
    }
}
