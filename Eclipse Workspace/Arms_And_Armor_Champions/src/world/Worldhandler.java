package world;

import java.util.ArrayList;
import java.util.Scanner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Worldhandler {
    public static int activeMap = 0;
    public static ArrayList<Map> maps = new ArrayList<Map>();
    public static ArrayList<CellImage> cellImages = new ArrayList<CellImage>();
    public static ArrayList<Terrain> terrains = new ArrayList<Terrain>();
    public static Texture tilemap;
    
    public static Map getMap(){
        return maps.get(activeMap);
    }
    
    /*public int[] getActiveCells(){
        return getMap().getActiveCells();
    }*/
    
    public static void load(){
    	loadTerrains();
    	loadCellImages();
    	String index = Gdx.files.internal("data/content/maps/INDEX.txt").readString();
    	while(index.contains(":")){
    		String file = "data/content/maps/" + index.substring(1, index.indexOf(';'));
    		maps.add(new Map(file));
    		index = index.substring(index.indexOf(';') + 1);
    	}
    }
    
    public static void loadCellImages(){
    	try{
    		cellImages.clear();
    		tilemap = new Texture(Gdx.files.internal("data/images/tilemap.png"));
    		int length = tilemap.getWidth() / 64;
    		for(int i = 0; i < length; i++){
    			CellImage CI = new CellImage();
    			TextureRegion tex = new TextureRegion(tilemap, i * 64, 0, 64, 64);
    			CI.set(tex, i);
    			cellImages.add(CI);
    		}
    	}catch(Exception ex){}
    }
    
    public static void loadTerrains(){
    	try{
    		terrains.clear();
    		Scanner reader = new Scanner(Gdx.files.internal("data/content/terrains.txt").readString());
    		while(reader.hasNextLine()){
    			reader.nextLine();
    			String terrain = reader.nextLine();
    			String climate = reader.nextLine();
    			String title = reader.nextLine();
    			int id = Integer.parseInt(reader.nextLine());
    			terrains.add(new Terrain(terrain, climate, title, id));
    		}
    		reader.close();
    	}catch(Exception ex){}
    }
    
//Old method
    /*public void loadCellImages(){
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
    }*/
    
    public static TextureRegion getCellImage(int terrain){
    	TextureRegion tex = cellImages.get(0).TEXTURE;
    	for(int i = 0; i < cellImages.size(); i++){
    		if(cellImages.get(i).TERRAIN == terrain){
    			tex = cellImages.get(i).TEXTURE;
    			break;
    		}
    	}
    	return tex;
    }
    
    public static String[] getClimates(){
    	ArrayList<String> strings = new ArrayList<String>();
    	for(int i = 0; i < terrains.size(); i++){
    		if(!contains(strings, terrains.get(i).CLIMATE)){
    			strings.add(terrains.get(i).CLIMATE);
    		}
    	}
    	String[] res = new String[strings.size()];
    	for(int i = 0; i < strings.size(); i++){
    		res[i] = strings.get(i);
    	}
    	return res;
    }
    
    public static boolean contains(ArrayList<String> a, String e){
    	boolean temp = false;
	    if(a.size() > 0 && !e.isEmpty()){
	    	for(int i = 0; i < a.size(); i++){
	    		if(a.get(i) != null){
		    		if(a.get(i).equals(e)){
			   			temp = true;
			   			break;
			   		}
	    		}
		   	}
	   	}
    	return temp;
    }
    
    public static boolean contains(String[] a, String e){
    	boolean temp = false;
	    if(a.length > 0 && !e.isEmpty()){
	    	for(int i = 0; i < a.length; i++){
	    		if(a[i] != null){
		    		if(a[i].equals(e)){
			   			temp = true;
			   			break;
			   		}
	    		}
		   	}
	   	}
    	return temp;
    }
    
    public static String[] getTerrainsByClimate(String climate){
    	ArrayList<String> strings = new ArrayList<String>();
    	for(int i = 0; i < terrains.size(); i++){
    		if(terrains.get(i).CLIMATE.equals(climate)){
    			strings.add(terrains.get(i).TERRAIN);
    		}
    	}
    	String[] res = new String[strings.size()];
    	for(int i = 0; i < strings.size(); i++){
    		res[i] = strings.get(i);
    	}
    	return res;
    }
}
