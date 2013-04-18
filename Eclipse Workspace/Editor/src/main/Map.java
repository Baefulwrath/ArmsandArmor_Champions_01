package main;

import java.util.ArrayList;

public class Map {
    public String title = "";
    public String id = "";
    public Cell[][] cells = new Cell[30][12];
    public int width = 30;
    public int height = 6;
    public int x = 100;
    public int y = 150;
    public boolean loaded = false;
    
    public void update(){
    	if(loaded){
	    	for(int x = 0; x < cells.length; x++){
	        	for(int y = 0; y < cells[x].length; y++){
	        		cells[x][y].update(x + getCellX(x, y), y + getCellY(x, y));
	        	}
	    	}
    	}
    }

    public void createEmptyMap(){
    	Main.map.loaded = false;
		for(int y = 0; y < cells.length; y++){
			for(int x = 0; x < cells[y].length; x++){
				cells[y][x] = new Cell(56, Main.editorTile.CLIMATE, Main.editorTile.TERRAIN);
			}
		}
        load(0, 0);
    }
    
    public void load(int width, int height){
        loaded = true;
    }

    public int getCellX(int cx, int cy){
        int temp = 0;
        temp = cx * cells[cx][cy].WIDTH;
        if(cy % 2 == 0){
        	temp += cells[cx][cy].RADIUS;
        }
        return temp;
    }
    
    public int getCellY(int cx, int cy){
        int temp = 0;
        temp = cells[cx][cy].SIDE * cy;
        return temp;
    }
}
