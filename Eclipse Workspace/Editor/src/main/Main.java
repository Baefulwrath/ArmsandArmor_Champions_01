package main;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
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
	public static ArrayList<TerrainImage> terrainImages = new ArrayList<TerrainImage>();
	public static ArrayList<ClimateImage> climateImages = new ArrayList<ClimateImage>();
	public static ArrayList<Terrain> terrains = new ArrayList<Terrain>();
	public static ArrayList<Climate> climates = new ArrayList<Climate>();
	public static BufferedImage terrainmap = null;
	public static BufferedImage climatemap = null;
	public static int mousex = 0;
	public static int mousey = 0;
	public static ArrayList<Button> buttons = new ArrayList<Button>();
	public static ArrayList<ImageButton> imgButtons = new ArrayList<ImageButton>();
	public static Brush brush = new Brush(getHexWidth(), 0, 0);
	public static BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
	public static Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");
	public static double zoom = 1.0;
	public static int hexDiameter = 128;
	public static int hexWidth = 110;
	
	public static void main(String[] args) {
		init();
		run();
	}
	
	public static void init(){
		loadClimates();
		loadClimateImages();
		loadTerrains();
		loadTerrainImages();
		frame.setResizable(true);
		frame.setVisible(true);
		frame.setSize(1280, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.add(scr);
		frame.addKeyListener(KH);
		frame.addMouseListener(MH);
		frame.addMouseWheelListener(SWH);
		frame.setTitle("Editor v0.3");
		updateButtons();
		createNewMap();
		frame.getContentPane().setCursor(cursor);
		loadSettings();
	}
	
	public static void run(){
		while(running){
			try{
				mousex = scr.getMousePosition().x;
				mousey = scr.getMousePosition().y;
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
    
    public static BufferedImage getTerrainImage(int terrain){
    	BufferedImage img = terrainImages.get(0).IMG;
    	for(int i = 0; i < terrainImages.size(); i++){
    		if(terrainImages.get(i).TERRAIN == terrain){
    			img = terrainImages.get(i).IMG;
    			break;
    		}
    	}
    	return img;
    }
    
    public static BufferedImage getClimateImageByTerrain(int terrain){
    	int climate = getClimateId(terrain);
    	BufferedImage img = climateImages.get(0).IMG;
    	for(int i = 0; i < climateImages.size(); i++){
    		if(climateImages.get(i).CLIMATE == climate){
    			img = climateImages.get(i).IMG;
    			break;
    		}
    	}
    	return img;
    }
    
    public static BufferedImage getClimateImageByClimate(int climate){
    	BufferedImage img = climateImages.get(0).IMG;
    	for(int i = 0; i < climateImages.size(); i++){
    		if(climateImages.get(i).CLIMATE == climate){
    			img = climateImages.get(i).IMG;
    			break;
    		}
    	}
    	return img;
    }
    
    public static BufferedImage getClimateImageByClimate(String climate){
    	BufferedImage img = climateImages.get(0).IMG;
    	for(int i = 0; i < climateImages.size(); i++){
    		if(getClimateName(climateImages.get(i).CLIMATE).equals(climate)){
    			img = climateImages.get(i).IMG;
    			break;
    		}
    	}
    	return img;
    }
    
    public static int getClimateId(int terrain){
    	int climate = getClimateId(terrains.get(terrain).CLIMATE);
    	return climate;
    }
    
    public static int getClimateId(String climate){
    	int id = 0;
    	for(int i = 0; i < climates.size(); i++){
    		if(climates.get(i).CLIMATE.equals(climate)){
    			id = i;
    			break;
    		}
    	}
    	return id;
    }
    
    public static String getClimateName(int climate){
    	return climates.get(climate).CLIMATE;
    }
    
    public static String[] getClimates(){
    	ArrayList<String> strings = new ArrayList<String>();
    	for(int i = 0; i < climates.size(); i++){
    		strings.add(climates.get(i).CLIMATE);
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
    
    public static void loadTerrainImages(){
    	try{
    		terrainImages.clear();
    		terrainmap = ImageIO.read(new File("data/images/terrainmap.png"));
    		int length = terrainmap.getWidth() / hexDiameter;
    		for(int i = 0; i < length; i++){
    			TerrainImage TI = new TerrainImage();
    			BufferedImage img = terrainmap.getSubimage(hexDiameter * i, 0, hexDiameter, hexDiameter);
    			TI.set(img, i);
    			terrainImages.add(TI);
    		}
    	}catch(Exception ex){}
    }
    
    public static void loadClimateImages(){
    	try{
    		climateImages.clear();
    		climatemap = ImageIO.read(new File("data/images/climatemap.png"));
    		int length = climatemap.getWidth() / hexDiameter;
    		for(int i = 0; i < length; i++){
    			ClimateImage CI = new ClimateImage();
    			BufferedImage img = climatemap.getSubimage(hexDiameter * i, 0, hexDiameter, hexDiameter);
    			CI.set(img, i);
    			climateImages.add(CI);
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
    		Scanner reader = new Scanner(new File("data/climates.txt"));
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
    	buttons.add(new Button("Show grid (space): " + showGrid, "switchshowgrid", 10, 16));
    	buttons.add(new Button("Mouse: " + mousex + ", " + mousey, "", 10, 32));
    	buttons.add(new Button("Debug (d): " + debug, "switchdebug", 10, 48));
    	
    	buttons.add(new Button("Brush size: " + brush.SIZE, "resetBrushsize", 150, 0));
    	buttons.add(new Button("++++++ (scroll up)", "+brushsize", 150, 16));
    	buttons.add(new Button("------ (scroll down)", "-brushsize", 150, 32));
    	buttons.add(new Button("New Map", "clearMap", 150, 48));
    	
    	buttons.add(new Button("Title: " + map.title, "changeTitle", 300, 0));
    	buttons.add(new Button("ID: " + map.id, "changeId", 300, 16));
    	buttons.add(new Button("Save Map", "saveMap", 300, 32));
    	buttons.add(new Button("Load Map", "loadMap", 300, 48));
    	
    	buttons.add(new Button("Pos: " + map.x + ", " + map.y + " (r)", "resetmappos", 750, 0));
    	buttons.add(new Button("Scale: " + map.width + ", " + map.height, "", 750, 16));
    	buttons.add(new Button("zoom: " + zoom, "", 750, 32));
    	buttons.add(new Button("++++++ (page up)", "+zoom", 750, 48));
    	buttons.add(new Button("------ (page down)", "-zoom", 750, 64));

    	
    	imgButtons.clear();
    	imgButtons.add(new ImageButton(getClimateImageByClimate(brush.CLIMATE), getClimateName(brush.CLIMATE), "changeBrushClimate", 450, 0, 64, 64));
    	imgButtons.add(new ImageButton(getTerrainImage(brush.TERRAIN), getTerrainName(brush.TERRAIN), "changeBrushTerrain", 600, 0, 64, 64));

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
			map.x = 100;
			map.y = 150;
			zoom = 1.0;
			map.createEmptyMap();
		}else if(responce == JOptionPane.NO_OPTION){
			Main.loadMap();
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
    			title = terrains.get(i).TITLE;
    			break;
    		}
    	}
    	return title;
    }

    
    public static String getTerrainName(int terrain){
    	String name = "";
    	for(int i = 0; i < terrains.size(); i++){
    		if(terrains.get(i).ID == terrain){
    			name = terrains.get(i).TERRAIN;
    			break;
    		}
    	}
    	return name;
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

	public static void saveMap(){
		JFileChooser fc = new JFileChooser(CmdHandler.lastDirectory);
		int returnVal = fc.showSaveDialog(frame);
		CmdHandler.lastDirectory = fc.getCurrentDirectory().getPath();
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			if(file.exists()){
				if(file.isFile() && file.getName().substring(file.getName().length() - 3).equals("txt")){
					Main.save(file);
				}else{
					JOptionPane.showMessageDialog(frame, "Bad file");
				}
			}else{
				if(file.getName().substring(file.getName().length() - 3).equals("txt")){
					Main.save(file);
				}else{
					JOptionPane.showMessageDialog(frame, "Bad file, remember to include .txt");
				}
			}
	    }
	}

	public static void save(File file){
		try{
			BufferedWriter pen = new BufferedWriter(new FileWriter(file));
			pen.write(map.title + System.getProperty("line.separator"));
			pen.write(map.id + System.getProperty("line.separator"));
			pen.write(map.width + System.getProperty("line.separator"));
			pen.write(map.height + System.getProperty("line.separator"));
			for(int x = 0; x < map.cells.length; x++){
				for(int y = 0; y < map.cells[x].length; y++){
					pen.write(x + System.getProperty("line.separator"));
					pen.write(y + System.getProperty("line.separator"));
					pen.write(map.cells[x][y].WIDTH + System.getProperty("line.separator"));
					pen.write(map.cells[x][y].TERRAIN + System.getProperty("line.separator"));
				}
			}
			pen.close();
		}catch(Exception ex){
			JOptionPane.showMessageDialog(frame, "Error saving file");
		}
	}

	public static void loadMap(){
		JFileChooser fc = new JFileChooser(CmdHandler.lastDirectory);
		int returnVal = fc.showOpenDialog(frame);
		CmdHandler.lastDirectory = fc.getCurrentDirectory().getPath();
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			if(file.isFile() && file.getName().substring(file.getName().length() - 3).equals("txt")){
				Main.load(file);
			}else{
				JOptionPane.showMessageDialog(frame, "Bad file");
			}
	    }
		map.x = 100;
		map.y = 150;
	}

	public static void load(File file){
		try{
			map.loaded = false;
			Scanner reader = new Scanner(file);
			map.title = reader.nextLine();
			map.id = reader.nextLine();
			map.width = Integer.parseInt(reader.nextLine());
			map.height = Integer.parseInt(reader.nextLine());
			map.cells = new Cell[map.width][map.height];
			while(reader.hasNextLine()){
				int cx = Integer.parseInt(reader.nextLine());
				int cy = Integer.parseInt(reader.nextLine());
				int width = Integer.parseInt(reader.nextLine());
				int ter = Integer.parseInt(reader.nextLine());
				int cli = getClimateId(ter);
				map.cells[cx][cy] = new Cell(width, ter, cli);
			}
			reader.close();
			map.loaded = true;
		}catch(Exception ex){
			JOptionPane.showMessageDialog(frame, "Error loading file");
		}
	}

	public static void clearMap(){
		if(JOptionPane.showConfirmDialog(frame, "Do you really want to clear the map?", "Rasputinbullar?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
			createNewMap();
		}
	}
	
	public static void zoomOut(){
		if(zoom > 1.0 && map.loaded){
			//Main.map.x += (Main.map.getWidth() - (Main.map.getWidth() * 0.9)) / 2;
			//Main.map.y += (Main.map.getHeight() - (Main.map.getHeight() * 0.9)) / 2;
			zoom -= 0.1;
		}
	}
	
	public static void zoomIn(){
		if(zoom < 5.0 && map.loaded){
			//Main.map.x += (Main.map.getWidth() - (Main.map.getWidth() * 1.1)) / 2;
			//Main.map.y += (Main.map.getHeight() - (Main.map.getHeight() * 1.1)) / 2;
			zoom += 0.1;
		}
	}
	
	public static int getHexWidth(){
		//return (int) (Main.hexDiameter / 1.547);
		return hexWidth;
	}
	
	public static void loadSettings(){
		try{
			Scanner reader = new Scanner(new File("data/settings.txt"));
			hexDiameter = Integer.parseInt(reader.nextLine().substring(9));
			hexWidth = Integer.parseInt(reader.nextLine().substring(6));
			reader.close();
		}catch(Exception ex){}
	}

}
