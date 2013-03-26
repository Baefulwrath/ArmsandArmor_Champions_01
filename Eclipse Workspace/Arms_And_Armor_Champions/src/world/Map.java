package world;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;

public class Map {
    public ArrayList<Cell> cells = new ArrayList<Cell>();
    public int width = 0;
    public boolean loaded = false;
    public Texture background;
    public Texture background2;
    public Texture foreground;
    public Texture gridpiece;
    
    public int[] getActiveCells(){
        ArrayList<Integer> cellList = new ArrayList<Integer>();
        for(int i = 0; i < cells.size(); i++){
            if(cells.get(i).ACTIVE){
                cellList.add(i);
            }
        }
        int[] cells = new int[cellList.size()];
        for(int i = 0; i < cellList.size(); i++){
            cells[i] = cellList.get(i);
        }
        return cells;
    }
    
    public void load(int width, int height){
        //Create tiles
        //Load images
        loaded = true;
    }
    
    public void test(){
        width = 24;
        for(int i = 0; i < 96; i++){
            cells.add(new Cell(80));
        }
        load(0, 0);
    }
    
    public float getCellX(int index){
        float temp = 0;
        int row = 0;
        int x = index;
        while(x >= width){
            x -= width;
            row++;
        }
        temp = x * (cells.get(0).RADIUS);
        return temp;
    }
    
    public float getCellY(int index){
        float temp = 0;
        int row = 0;
        int x = index;
        while(x >= width){
            x -= width;
            row++;
        }
        temp -= (cells.get(0).SIDE * 3) * row;
        if(x % 2 == 0){
            temp -= cells.get(0).HALFSIDE * 3;
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
