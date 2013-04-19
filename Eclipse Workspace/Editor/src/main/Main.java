package main;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
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
	public static ScrollwheelHandler SWH = new ScrollwheelHandler();
	public static ArrayList<CellImage> cellImages = new ArrayList<CellImage>();
	public static ArrayList<Terrain> terrains = new ArrayList<Terrain>();
	public static int mousex = 0;
	public static int mousey = 0;
	public static ArrayList<Button> buttons = new ArrayList<Button>();
	public static ArrayList<ImageButton> imgButtons = new ArrayList<ImageButton>();
	public static Brush brush = new Brush(56, 0, "DEFAULT");
	public static BufferedImage tilemap = null;
	public static BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
	public static Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");
	
	public static void main(String[] args) {
		init();
		run();
	}
	
	public static void init(){
		loadTerrains();
		loadCellImages();
		frame.setResizable(true);
		frame.setVisible(true);
		frame.setSize(1280, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.add(scr);
		frame.addKeyListener(KH);
		frame.addMouseListener(MH);
		frame.addMouseWheelListener(SWH);
		frame.setTitle("Editor v0.2");
		updateButtons();
		createNewMap();
		frame.getContentPane().setCursor(blankCursor);
	}
	
	public static void run(){
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
				brush.update(mousex, mousey);
			}catch(Exception ex){
				ex.printStackTrace(System.out);
			}
		}
	}

    
    public static BufferedImage getCellImage(int terrain){
    	BufferedImage img = cellImages.get(0).IMG;
    	for(int i = 0; i < cellImages.size(); i++){
    		if(cellImages.get(i).TERRAIN == terrain){
    			img = cellImages.get(i).IMG;
    			break;
    		}
    	}
    	return img;
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
    
    public static void loadCellImages(){
    	try{
    		cellImages.clear();
    		tilemap = ImageIO.read(new File("data/images/tilemap.png"));
    		int length = tilemap.getWidth() / 64;
    		for(int i = 0; i < length; i++){
    			CellImage CI = new CellImage();
    			BufferedImage img = tilemap.getSubimage(64 * i, 0, 64, 64);
    			CI.set(img, i);
    			cellImages.add(CI);
    		}
    	}catch(Exception ex){}
    }
    
    public static void loadTerrains(){
    	try{
    		terrains.clear();
    		Scanner reader = new Scanner(new File("data/terrains.txt"));
    		while(reader.hasNextLine()){
    			reader.nextLine();
    			String terrain = reader.nextLine();
    			String climate = reader.nextLine();
    			int id = Integer.parseInt(reader.nextLine());
    			terrains.add(new Terrain(terrain, climate, id));
    		}
    		reader.close();
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
    	
    	buttons.add(new Button("Brush size: " + brush.SIZE, "resetBrushsize", 150, 0));
    	buttons.add(new Button("++++++", "+brushsize", 150, 16));
    	buttons.add(new Button("------", "-brushsize", 150, 32));
    	buttons.add(new Button("New Map", "clearMap", 150, 48));
    	
    	buttons.add(new Button("Title: " + map.title, "changeTitle", 300, 0));
    	buttons.add(new Button("ID: " + map.id, "changeId", 300, 16));
    	buttons.add(new Button("Save Map", "saveMap", 300, 32));
    	buttons.add(new Button("Load Map", "loadMap", 300, 48));
    	
    	buttons.add(new Button("Pos: " + map.x + ", " + map.y, "", 600, 0));
    	buttons.add(new Button("Scale: " + map.width + ", " + map.height, "", 600, 16));

    	buttons.add(new Button(brush.CLIMATE, "changeBrushClimate", 450, 0));
    	
    	imgButtons.clear();
    	imgButtons.add(new ImageButton(getCellImage(brush.TERRAIN), getTerrainTitle(brush.TERRAIN), "changeBrushTerrain", 450, 16));

		for(int i = 0; i < buttons.size(); i++){
			if(buttons.get(i).BOX.intersects(new Rectangle(mousex, mousey, 1, 1))){
				buttons.get(i).hover = true;
			}else{
				buttons.get(i).hover = false;
			}
		}
		for(int i = 0; i < imgButtons.size(); i++){
			if(imgButtons.get(i).BOX.intersects(new Rectangle(mousex, mousey, 1, 1))){
				imgButtons.get(i).hover = true;
			}else{
				imgButtons.get(i).hover = false;
			}
		}
    }
    
    public static void createNewMap(){
    	int responce = JOptionPane.showConfirmDialog(frame, "Create(y) or Load(n)?", "create or load", JOptionPane.YES_NO_CANCEL_OPTION);
		if(responce == JOptionPane.YES_OPTION){
			map.width = Integer.parseInt(JOptionPane.showInputDialog(frame, "Map Width", "12"));
			map.height = Integer.parseInt(JOptionPane.showInputDialog(frame, "Map Height", "12"));
			CmdHandler.changeBrushClimate();
			CmdHandler.changeBrushTerrain();
			map.createEmptyMap();
		}else if(responce == JOptionPane.NO_OPTION){
			CmdHandler.loadMap();
		}
    }
    
    public static void paint(){
    	if(map.loaded){
			for(int x = 0; x < map.cells.length; x++){
				for(int y = 0; y < map.cells[x].length; y++){
					if(map.cells[x][y].intersects(brush.BOX)){
						map.cells[x][y].mirror(brush);
					}
				}
			}
    	}
    }
    
    public static void exit(){
    	if(JOptionPane.showConfirmDialog(frame, "Do you want to exit?", "Exit?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
    		System.exit(0);
    	}
    }

    
    public static String getTerrainTitle(int terrain){
    	String title = "";
    	for(int i = 0; i < terrains.size(); i++){
    		if(terrains.get(i).ID == terrain){
    			title = terrains.get(i).TERRAIN;
    			break;
    		}
    	}
    	return title;
    }
    
    public static int getTerrainId(String terrain){
    	int id = 0;
    	for(int i = 0; i < terrains.size(); i++){
    		if(terrains.get(i).TERRAIN == terrain){
    			id = terrains.get(i).ID;
    			break;
    		}
    	}
    	return id;
    }

}
