package main;

import java.awt.Polygon;
import java.awt.Rectangle;


public class Cell {
	public int TERRAIN = 0;
	public int CLIMATE = 0;
	
    public boolean ACTIVE = false;
    public int WIDTH = 0;
    public int DIAMETER = 0;
    public int RADIUS = 0;
    public int SIDE = 0;
    public int HALFSIDE = 0;
    public int imgWDif = 0;
    public Polygon localPolygon = new Polygon();
    public Polygon actualPolygon = new Polygon();
    
    public Cell(int width, int terrain, int climate){
        set(width);
        TERRAIN = terrain;
        CLIMATE = climate;
    }
    
    public void update(int x, int y, double zoom){
        int[] pointsx = {(int) (x * zoom), (int) ((x + RADIUS)* zoom), (int) ((x + RADIUS) * zoom), (int) (x * zoom), (int) ((x -RADIUS) * zoom), (int) ((x -RADIUS) * zoom)};
        int[] pointsy = {(int) ((y + SIDE) * zoom), (int) ((y + HALFSIDE) * zoom), (int) ((y -HALFSIDE) * zoom), (int) ((y -RADIUS) * zoom), (int) ((y -HALFSIDE) * zoom), (int) ((y + HALFSIDE) * zoom)};
        
        actualPolygon = new Polygon(pointsx, pointsy, 6);
    }
    
    public void set(int width){
        WIDTH = width;
        DIAMETER = (int) (WIDTH * 1.1547);
        RADIUS = WIDTH / 2;
        SIDE = DIAMETER / 2;
        HALFSIDE = SIDE / 2;
        imgWDif = (DIAMETER - WIDTH) / 2;
        
        int[] pointsx = {0, RADIUS, RADIUS, 0, -RADIUS, -RADIUS};
        int[] pointsy = {SIDE, HALFSIDE, -HALFSIDE, -RADIUS, -HALFSIDE, HALFSIDE};
        
        localPolygon = new Polygon(pointsx, pointsy, 6);
    }
    
    public boolean intersects(Rectangle r){
    	if(actualPolygon.intersects(r)){
    		return true;
    	}else{
    		return false;
    	}
    }
    
    public void mirror(Cell cell){
    	set(Main.getHexWidth());
    	TERRAIN = cell.TERRAIN;
    	CLIMATE = cell.CLIMATE;
    }
}
