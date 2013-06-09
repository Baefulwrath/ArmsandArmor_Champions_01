package world;

import java.util.ArrayList;
import java.util.Scanner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Worldhandler {
    public static int activeMap = 0;
    public static int mapSpeed = 32;
    public static ArrayList<Map> maps = new ArrayList<Map>();
	public static ArrayList<TerrainImage> terrainImages = new ArrayList<TerrainImage>();
	public static ArrayList<ClimateImage> climateImages = new ArrayList<ClimateImage>();
    public static ArrayList<Terrain> terrains = new ArrayList<Terrain>();
	public static ArrayList<Climate> climates = new ArrayList<Climate>();
	public static Texture terrainmap;
	public static Texture climatemap;
    public static int hexDiameter = 128;
    public static int hexWidth = 110;

	public static boolean moveUp = false;
	public static boolean moveDown = false;
	public static boolean moveLeft = false;
	public static boolean moveRight = false;
	private static long lastMovement = 0;
	private static long movementInterval = 50;
    
	public static void update(){
		if(readyToMove()){
			move();
		}
		getMap().update();
	}
	
    public static Map getMap(){
        return maps.get(activeMap);
    }
    
    
    /*public int[] getActiveCells(){
        return getMap().getActiveCells();
    }*/
    
    public static void load(){
    	loadTerrains();
    	loadClimates();
    	loadTerrainImages();
    	loadClimateImages();
    	loadMaps();
    }
    
    public static void loadMaps(){
    	maps.clear();
    	String index = Gdx.files.internal("data/content/maps/INDEX.txt").readString();
    	while(index.contains(":")){
    		String file = "data/content/maps/" + index.substring(1, index.indexOf(';'));
    		maps.add(new Map(file));
    		index = index.substring(index.indexOf(';') + 1);
    	}
    }
    
    public static void loadTerrainImages(){
    	try{
    		terrainImages.clear();
    		terrainmap = new Texture(Gdx.files.internal("data/images/terrainmap.png"));
    		int length = terrainmap.getWidth() / hexDiameter;
    		for(int i = 0; i < length; i++){
    			TerrainImage TI = new TerrainImage();
    			TextureRegion img = new TextureRegion(terrainmap, hexDiameter * i, 0, hexDiameter, hexDiameter);
    			TI.set(img, i);
    			terrainImages.add(TI);
    		}
    	}catch(Exception ex){}
    }
    
    public static void loadClimateImages(){
    	try{
    		climateImages.clear();
    		climatemap = new Texture(Gdx.files.internal("data/images/climatemap.png"));
    		int length = climatemap.getWidth() / hexDiameter;
    		for(int i = 0; i < length; i++){
    			ClimateImage CI = new ClimateImage();
    			TextureRegion img = new TextureRegion(climatemap, hexDiameter * i, 0, hexDiameter, hexDiameter);
    			CI.set(img, i);
    			climateImages.add(CI);
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
    	}catch(Exception ex){ex.printStackTrace();}
    }
    
    public static void loadClimates(){
    	try{
    		climates.clear();
    		Scanner reader = new Scanner(Gdx.files.internal("data/content/climates.txt").readString());
    		while(reader.hasNextLine()){
    			reader.nextLine();
    			String climate = reader.nextLine();
    			String title = reader.nextLine();
    			int id = Integer.parseInt(reader.nextLine());
    			climates.add(new Climate(climate, title, id));
    		}
    		reader.close();
    	}catch(Exception ex){ex.printStackTrace();}
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
    
    public static TextureRegion getTerrainImage(int terrain){
    	TextureRegion tex = terrainImages.get(0).TEXTURE;
    	for(int i = 0; i < terrainImages.size(); i++){
    		if(terrainImages.get(i).TERRAIN == terrain){
    			tex = terrainImages.get(i).TEXTURE;
    			break;
    		}
    	}
    	return tex;
    }
    public static TextureRegion getClimateImage(int climate){
    	TextureRegion tex = climateImages.get(0).TEXTURE;
    	for(int i = 0; i < climateImages.size(); i++){
    		if(climateImages.get(i).CLIMATE == climate){
    			tex = climateImages.get(i).TEXTURE;
    			break;
    		}
    	}
    	return tex;
    }
    
    public static int getClimateIdByTerrain(int terrain){
    	int temp = 0;
    	for(int i = 0; i < climates.size(); i++){
    		if(terrains.get(terrain).CLIMATE.equals(climates.get(i).CLIMATE)){
    			temp = i;
    			break;
    		}
    	}
    	return temp;
    }
    
    public static int getClimateIdByString(String s){
    	Climate C = climates.get(0);
    	for(int i = 0; i < climates.size(); i++){
    		if(climates.get(i).CLIMATE.equals(s)){
    			C = climates.get(i);
    			break;
    		}
    	}
    	return C.ID;
    }
    
    public static int getTerrainIdByString(String s){
    	Terrain T = terrains.get(0);
    	for(int i = 0; i < terrains.size(); i++){
    		if(terrains.get(i).TERRAIN.equals(s)){
    			T = terrains.get(i);
    			break;
    		}
    	}
    	return T.ID;
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
    
	public static boolean readyToMove(){
		boolean temp = false;
		if(lastMovement + movementInterval <= System.currentTimeMillis()){
			temp = true;
			lastMovement = System.currentTimeMillis();
		}
		return temp;
	}
	
	public static void move(){
		if(moveUp){
			getMap().Y += mapSpeed;
		}else if(moveDown){
			getMap().Y -= mapSpeed;
		}
		if(moveLeft){
			getMap().X -= mapSpeed;
		}else if(moveRight){
			getMap().X += mapSpeed;
		}
	}
}
