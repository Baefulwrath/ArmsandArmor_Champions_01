package main;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main{

	/**
	 * @param args
	 */
	
	public static Map map = new Map();
	public static JFrame frame = new JFrame();
	public static Screen scr = new Screen();
	public static boolean running = true;
	public static boolean showGrid = false;
	public static boolean debug = false;
	public static boolean painting = false;
	public static int scrollingSpeed = 8;
	public static KeyHandler KH = new KeyHandler();
	public static MouseHandler MH = new MouseHandler();
	public static MouseMotionHandler MMH = new MouseMotionHandler();
	public static ArrayList<CellImage> cellImages = new ArrayList<CellImage>();
	public static ArrayList<Terrain> terrains = new ArrayList<Terrain>();
	public static int mousex = 0;
	public static int mousey = 0;
	public static ArrayList<Button> buttons = new ArrayList<Button>();
	public static ArrayList<ImageButton> imgButtons = new ArrayList<ImageButton>();
	public static Cell editorTile = new Cell(56, "DEFAULT", "DEFAULT");
	public static int brushSize = 1;
	public static Rectangle brush = new Rectangle (0, 0, brushSize, brushSize);
	public static double zoom = 1.5;
	
	
	public static void main(String[] args) {
		init();
		run();
	}
	
	public static void init(){
		//init code
		loadCellImages();
		loadTerrains();
		frame.setResizable(true);
		frame.setVisible(true);
		frame.setSize(1280, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.add(scr);
		frame.addKeyListener(KH);
		frame.addMouseListener(MH);
		frame.addMouseMotionListener(MMH);
		frame.setTitle("Editor v0.1");
		updateButtons();
		createNewMap();
	}
	
	public static void run(){
		//program code
		while(running){
			try{
				mousex = frame.getMousePosition().x;
				mousey = frame.getMousePosition().y - 25;
			}catch(Exception ex){
				mousex = 0;
				mousey = 0;
			}
			try{
				Thread.sleep(5);
				map.update();
				scr.repaint();
				if(readyToUpdateButtons()){
					updateButtons();
				}
				if(painting){
					paint();
				}
				brush = new Rectangle (mousex - (brushSize / 2), mousey - (brushSize / 2), brushSize, brushSize);
			}catch(Exception ex){
				ex.printStackTrace(System.out);
			}
		}
	}

    
    public static BufferedImage getCellImage(String terrain){
    	BufferedImage img = cellImages.get(0).IMG;
    	for(int i = 0; i < cellImages.size(); i++){
    		if(cellImages.get(i).TERRAIN.equals(terrain)){
    			img = cellImages.get(i).IMG;
    			break;
    		}
    	}
    	return img;
    }
    
    public static String[] getClimates(){
    	ArrayList<String> strings = new ArrayList<String>();
    	for(int i = 0; i < terrains.size(); i++){
    		strings.add(terrains.get(i).CLIMATE);
    	}
    	String[] res = new String[strings.size()];
    	for(int i = 0; i < strings.size(); i++){
    		res[i] = strings.get(i);
    	}
    	return res;
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
    
    public static void loadCellImages(){
    	try{
    		cellImages.clear();
    		Scanner reader = new Scanner(new File("data/images/terrain/INDEX.txt"));
    		String index = reader.nextLine();
    		reader.close();
        	ArrayList<String> files = new ArrayList<String>();
        	while(index.contains(":")){
        		files.add(index.substring(1, index.indexOf(";")));
        		index = index.substring(index.indexOf(";") + 1);
        	}
            if(files.size() > 0){
	            for(int i = 0; i < files.size(); i++){
	            	String file = files.get(i).substring(0, files.get(i).indexOf('#'));
	                if(file.substring(file.length() - 3).equals("png")){
	                	CellImage CI = new CellImage();
	                	String terrain = files.get(i).substring(files.get(i).indexOf('#') + 1);
	                	BufferedImage img = ImageIO.read(new File("data/images/terrain/" + file));
	                	CI.set(img, terrain);
	                	cellImages.add(CI);
	                }else{
	                	System.out.println("Non-png in index of cellimages");
	                }
	            }
            }
    	}catch(Exception ex){}
    }
    
    public static void loadTerrains(){
    	try{
    		terrains.clear();
    		Scanner reader = new Scanner(new File("data/terrain/INDEX.txt"));
    		String index = reader.nextLine();
    		reader.close();
        	ArrayList<String> files = new ArrayList<String>();
        	while(index.contains("#")){
        		files.add(index.substring(0, index.indexOf('#')));
        		index = index.substring(index.indexOf('#') + 1);
        	}
        	if(files.size() > 0){
        		for(int i = 0; i < files.size(); i++){
        			if(files.get(i).substring(files.get(i).length() - 3).equals("txt")){
        				Scanner file = new Scanner(new File("data/terrain/" + files.get(i)));
        				String t = file.nextLine();
        				String c = file.nextLine();
        				file.close();
        				terrains.add(new Terrain(c, t));
        			}
        		}
        	}
    	}catch(Exception ex){ex.printStackTrace();}
    }
    
    public static long lastButtonUpdate = 0;
    
    public static boolean readyToUpdateButtons(){
    	if(lastButtonUpdate + 50 <= System.currentTimeMillis()){
    		lastButtonUpdate = System.currentTimeMillis();
    		return true;
    	}else{
    		return false;
    	}
    }
    
    public static void updateButtons(){
    	buttons.clear();
    	buttons.add(new Button("scroll speed (+/-): " + scrollingSpeed, "", 10, 0));
    	buttons.add(new Button("Show grid: " + showGrid, "switchshowgrid", 10, 16));
    	buttons.add(new Button("Mouse: " + mousex + ", " + mousey, "", 10, 32));
    	buttons.add(new Button("Debug: " + debug, "switchdebug", 10, 48));
    	
    	buttons.add(new Button("Brush size: " + brushSize, "", 150, 0));
    	buttons.add(new Button("++++++", "+brushsize", 150, 16));
    	buttons.add(new Button("------", "-brushsize", 150, 32));
    	buttons.add(new Button("New Map", "clearMap", 150, 48));
    	
    	buttons.add(new Button("Title: " + map.title, "changeTitle", 300, 0));
    	buttons.add(new Button("ID: " + map.id, "changeId", 300, 16));
    	buttons.add(new Button("Save Map", "saveMap", 300, 32));
    	buttons.add(new Button("Load Map", "loadMap", 300, 48));
    	
    	buttons.add(new Button("Pos: " + map.x + ", " + map.y, "", 600, 0));

    	buttons.add(new Button(editorTile.CLIMATE, "changeBrushClimate", 450, 0));
    	
    	imgButtons.clear();
    	imgButtons.add(new ImageButton(getCellImage(editorTile.TERRAIN), editorTile.TERRAIN, "changeBrushTerrain", 450, 16));
    }
    
    public static void createNewMap(){
    	int responce = JOptionPane.showConfirmDialog(frame, "Create(y) or Load(n)?", "create or load", JOptionPane.YES_NO_CANCEL_OPTION);
		if(responce == JOptionPane.YES_OPTION){
			map.width = Integer.parseInt(JOptionPane.showInputDialog(frame, "Map Width", "30"));
			map.height = Integer.parseInt(JOptionPane.showInputDialog(frame, "Map Height", "6"));
			CmdHandler.changeBrushClimate();
			CmdHandler.changeBrushTerrain();
			map.createEmptyMap();
		}else if(responce == JOptionPane.NO_OPTION){
			CmdHandler.loadMap();
		}
    }
    
    public static void paint(){
		for(int x = 0; x < map.cells.length; x++){
			for(int y = 0; y < map.cells[x].length; y++){
				if(map.cells[x][y].intersects(brush)){
					map.cells[x][y].mirror(editorTile);
				}
			}
		}
    }
    
    public static void exit(){
    	if(JOptionPane.showConfirmDialog(frame, "Do you want to exit?", "Exit?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
    		System.exit(0);
    	}
    }

}
