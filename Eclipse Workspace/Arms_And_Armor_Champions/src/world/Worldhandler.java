package world;

import java.util.ArrayList;
import java.util.Scanner;

import menus.Activator;
import menus.ActivatorType;
import menus.Menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;

public class Worldhandler {
    public int activeMap = 0;
    public ArrayList<Map> maps = new ArrayList<Map>();
    public ArrayList<CellImage> cellImages = new ArrayList<CellImage>();
    
    public Map getMap(){
        return maps.get(activeMap);
    }
    
    public int[] getActiveCells(){
        return getMap().getActiveCells();
    }
    
    public void load(){
    	loadCellImages();
        test();
    }
    
    public void test(){
        maps.add(new Map());
        getMap().test();
    }
    
    public void loadCellImages(){
    	try{
    		cellImages.clear();
    		String index = Gdx.files.internal("data/images/tiles/INDEX.txt").readString();
        	ArrayList<String> files = new ArrayList<String>();
        	while(index.contains(":")){
        		files.add(index.substring(1, index.indexOf(";")));
        		index = index.substring(index.indexOf(";") + 1);
        	}

            Scanner reader;
            if(files.size() > 0){
	            for(int i = 0; i < files.size(); i++){
	                FileHandle file = Gdx.files.internal("data/images/tiles/" + files.get(i).substring(0, files.get(i).indexOf('#')));
	                if(file.extension().equals("png")){
	                	CellImage CI = new CellImage();
	                	Terrain terrain = Terrain.parseTerrain(files.get(i).substring(files.get(i).indexOf('#') + 1));
	                	Texture texture = new Texture(Gdx.files.internal("data/images/tiles/" + files.get(i).substring(0, files.get(i).indexOf('#'))));
	                	CI.set(texture, terrain);
	                	cellImages.add(CI);
	                }else{
	                	System.out.println("Non-png in index of tiles");
	                }
	            }
            }
    	}catch(Exception ex){}
    }
    
    public Texture getCellImage(Terrain terrain){
    	Texture t = cellImages.get(0).TEXTURE;
    	for(int i = 0; i < cellImages.size(); i++){
    		if(cellImages.get(i).TERRAIN == terrain){
    			t = cellImages.get(i).TEXTURE;
    			break;
    		}
    	}
    	return t;
    }
}
