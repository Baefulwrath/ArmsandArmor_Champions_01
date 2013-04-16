package main;

import java.awt.Polygon;
import java.awt.Rectangle;


public class Cell {
	public Terrain TERRAIN = Terrain.DEFAULT;
    public boolean ACTIVE = false;
    public int WIDTH = 0;
    public int DIAMETER = 0;
    public int RADIUS = 0;
    public int SIDE = 0;
    public int HALFSIDE = 0;
    public int imgWDif = 0;
    public int imgHDif = 0;
    public Polygon localPolygon = new Polygon();
    public Polygon actualPolygon = new Polygon();
    
    public Cell(int width, Terrain terrain){
        set(width);
        TERRAIN = terrain;
    }
    
    public void update(int x, int y){
        int[] pointsx = {x, x + RADIUS, x + RADIUS, x, x -RADIUS, x -RADIUS};
        int[] pointsy = {y + SIDE, y + HALFSIDE, y -HALFSIDE, y -RADIUS, y -HALFSIDE, y + HALFSIDE};
        actualPolygon = new Polygon(pointsx, pointsy, 6);
    }
    
    public void set(int width){
        WIDTH = width;
        DIAMETER = (int) (WIDTH * 1.1547);
        RADIUS = WIDTH / 2;
        SIDE = DIAMETER / 2;
        HALFSIDE = SIDE / 2;
        imgWDif = (DIAMETER - WIDTH) / 2;
        imgHDif = SIDE;
        
        int[] pointsx = {0, RADIUS, RADIUS, 0, -RADIUS, -RADIUS};
        int[] pointsy = {SIDE, HALFSIDE, -HALFSIDE, -RADIUS, -HALFSIDE, HALFSIDE};
        
        localPolygon = new Polygon(pointsx, pointsy, 6);
    }
    
    public boolean intersects(Rectangle r){
        return false;
    }
}
