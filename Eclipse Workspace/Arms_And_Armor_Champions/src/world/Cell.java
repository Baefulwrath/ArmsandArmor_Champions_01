package world;

import java.awt.Rectangle;

import com.badlogic.gdx.math.Polygon;

public class Cell {
	public int TERRAIN = 0;
    public boolean ACTIVE = false;
    private Polygon[] polygons = new Polygon[6];
    public float WIDTH = 0;
    public float DIAMETER = 0;
    public float RADIUS = 0;
    public float SIDE = 0;
    public float HALFSIDE = 0;
    public float imgWDif = 0;
    
    public Cell(int width, int terrain){
        set(width);
        TERRAIN = terrain;
    }
    public Polygon[] getPolygons(){
        return polygons;
    }
    public void set(int width){
        WIDTH = width;
        DIAMETER = (float) (WIDTH * 1.1547);
        RADIUS = WIDTH / 2;
        SIDE = DIAMETER / 2;
        HALFSIDE = SIDE / 2;
        imgWDif = (DIAMETER - WIDTH) / 2;
        float[] verts0 = {0, 0, 0, SIDE, RADIUS, HALFSIDE};
        float[] verts1 = {0, 0, RADIUS, HALFSIDE, RADIUS, -HALFSIDE};
        float[] verts2 = {0, 0, RADIUS, -HALFSIDE, 0, -SIDE};
        float[] verts3 = {0, 0, 0, -SIDE, -RADIUS, -HALFSIDE};
        float[] verts4 = {0, 0, -RADIUS, -HALFSIDE, -RADIUS, HALFSIDE};
        float[] verts5 = {0, 0, -RADIUS, HALFSIDE, 0, SIDE};
        polygons[0] = new Polygon(verts0);
        polygons[1] = new Polygon(verts1);
        polygons[2] = new Polygon(verts2);
        polygons[3] = new Polygon(verts3);
        polygons[4] = new Polygon(verts4);
        polygons[5] = new Polygon(verts5);
    }
    
    public boolean intersects(Rectangle r){
        return false;
    }

}
