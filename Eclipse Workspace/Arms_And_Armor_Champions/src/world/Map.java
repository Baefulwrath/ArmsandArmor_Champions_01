package world;

import java.util.Scanner;
import com.badlogic.gdx.Gdx;
import com.rapplebob.ArmsAndArmorChampions.AAA_C;


public class Map {
    public Cell[][] CELLS = new Cell[0][0];
    public String TITLE = "";
    public String ID = "";
    public int WIDTH = 0;
    public int HEIGHT = 0;
    public int X = 0;
    public int Y = 0;
    public boolean LOADED = false;
    
    public Map(String file){
    	LOADED = false;
    	//Load from file
    	Scanner reader = new Scanner(Gdx.files.internal(file).readString());
    	TITLE = reader.nextLine();
    	ID = reader.nextLine();
    	WIDTH = Integer.parseInt(reader.nextLine());
    	HEIGHT = Integer.parseInt(reader.nextLine());
    	CELLS = new Cell[WIDTH][HEIGHT];
    	while(reader.hasNextLine()){
			int cx = Integer.parseInt(reader.nextLine());
			int cy = Integer.parseInt(reader.nextLine());
			int width = Integer.parseInt(reader.nextLine());
			int ter = Integer.parseInt(reader.nextLine());
			int cli = Worldhandler.getClimateIdByTerrain(ter);
			CELLS[cx][cy] = new Cell(width, ter, cli);
    	}
    	X = AAA_C.getActiveRenderer().getCentralMapX(this);
    	Y = AAA_C.getActiveRenderer().getCentralMapY(this);
        LOADED = true;
    }
    
    public Map(int w, int h, int cw){
    	LOADED = false;
    	TITLE = "UNTITLED";
    	ID = "NO_ID";
    	WIDTH = w;
    	HEIGHT = h;
    	CELLS = new Cell[WIDTH][HEIGHT];
    	for(int x = 0; x < WIDTH; x++){
        	for(int y = 0; y < HEIGHT; y++){
        		CELLS[x][y] = new Cell(cw, 0, 0);
        	}
    	}
    	X = AAA_C.getActiveRenderer().getCentralMapX(this);
    	Y = AAA_C.getActiveRenderer().getCentralMapY(this);
    	LOADED = true;
    }
    
    public void update(){
    	for(int x = 0; x < CELLS.length; x++){
        	for(int y = 0; y < CELLS[x].length; y++){
        		CELLS[x][y].update((int) (getCellX(x, y)), (int) (getCellY(x, y)), X, Y);
        	}
    	}
    }

    private float getCellX(int cx, int cy){
    	float temp = 0;
        temp = cx * CELLS[cx][cy].WIDTH;
        if(cy % 2 == 0){
        	temp += CELLS[cx][cy].RADIUS;
        }
        return temp;
    }
    
    private float getCellY(int cx, int cy){
    	float temp = 0;
        temp = - (CELLS[cx][cy].SIDE * 1.5f) * cy;
        return temp;
    }
    
    public int getHeight(){
    	float h = (CELLS[0][0].SIDE * 1.5f) * HEIGHT;
    	return (int) h;
    }
    
    public int getWidth(){
    	float w = (CELLS[0][0].RADIUS) * WIDTH * 2;
    	return (int) w;
    }

}
