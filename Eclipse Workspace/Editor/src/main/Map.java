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
	    	for(int cx = 0; cx < cells.length; cx++){
	        	for(int cy = 0; cy < cells[cx].length; cy++){
	        		cells[cx][cy].update(x + getCellX(cx, cy), y + getCellY(cx, cy), Main.zoom);
	        	}
	    	}
    	}
    }

    public void createEmptyMap(){
    	if(cells.length > 0){
    		title = "";
    		id = "";
    	}
    	Main.map.loaded = false;
    	cells = new Cell[width][height];
		for(int y = 0; y < cells.length; y++){
			for(int x = 0; x < cells[y].length; x++){
				cells[y][x] = new Cell(Main.getHexWidth(), Main.brush.TERRAIN, Main.brush.CLIMATE);
			}
		}
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
        temp = (int) (cells[cx][cy].SIDE * 1.5) * cy;
        return temp;
    }
    
    public int getWidth(){
    	if(Main.map.cells.length > 1){
    		return (int) ((getCellX(cells.length - 1, 0) + getCellX(1, 0)) * Main.zoom);
    	}else{
    		return 0;
    	}
    }
    
    public int getHeight(){
    	if(Main.map.cells[0].length > 1){
    		return (int) ((getCellY(0, cells[0].length - 1) + getCellY(0, 1)) * Main.zoom);
    	}else{
    		return 0;
    	}
    }
}
