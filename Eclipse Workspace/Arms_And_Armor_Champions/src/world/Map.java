package world;

import java.util.Scanner;

import com.badlogic.gdx.Gdx;


public class Map {
    public Cell[][] cells = new Cell[0][0];
    public String title = "";
    public String id = "";
    public int width = 0;
    public int height = 0;
    public boolean loaded = false;
    
    public Map(String file){
    	loaded = false;
    	//Load from file
    	Scanner reader = new Scanner(Gdx.files.internal(file).readString());
    	title = reader.nextLine();
    	id = reader.nextLine();
    	width = Integer.parseInt(reader.nextLine());
    	height = Integer.parseInt(reader.nextLine());
    	cells = new Cell[width][height];
    	while(reader.hasNextLine()){
    		cells[Integer.parseInt(reader.nextLine())][Integer.parseInt(reader.nextLine())] = new Cell(Integer.parseInt(reader.nextLine()), Integer.parseInt(reader.nextLine()));
    	}
        loaded = true;
    }


    public float getCellX(int cx, int cy){
    	float temp = 0;
        temp = cx * cells[cx][cy].WIDTH;
        if(cy % 2 == 0){
        	temp += cells[cx][cy].RADIUS;
        }
        return temp;
    }
    
    public float getCellY(int cx, int cy){
    	float temp = 0;
        temp = - (cells[cx][cy].SIDE * 1.5f) * cy;
        return temp;
    }
    
    public int getHeight(){
    	float h = (cells[0][0].SIDE * 1.5f) * height;
    	return (int) h;
    }
    
    public int getWidth(){
    	float w = (cells[0][0].RADIUS) * width * 2;
    	return (int) w;
    }

}
