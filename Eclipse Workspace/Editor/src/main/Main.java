package main;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Main{

	/**
	 * @param args
	 */
	
	public static Map map = new Map();
	public static JFrame frame = new JFrame();
	public static Screen scr = new Screen();
	public static boolean running = true;
	public static boolean showGrid = true;
	public static int scrollingSpeed = 8;
	public static KeyHandler KH = new KeyHandler();
	public static MouseHandler MH = new MouseHandler();
	public static MouseMotionHandler MMH = new MouseMotionHandler();
	public static ArrayList<CellImage> cellImages = new ArrayList<CellImage>(); 
	public static int mousex = 0;
	public static int mousey = 0;
	
	public static void main(String[] args) {
		init();
		run();
	}
	
	public static void init(){
		//init code
		map.createEmptyMap();
		loadCellImages();
		frame.setResizable(true);
		frame.setVisible(true);
		frame.setSize(1280, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.add(scr);
		frame.addKeyListener(KH);
		frame.addMouseListener(MH);
		frame.addMouseMotionListener(MMH);
	}
	
	public static void run(){
		//program code
		while(running){
			try{
				mousex = frame.getMousePosition().x;
				mousey = frame.getMousePosition().y;
			}catch(Exception ex){
				mousex = 0;
				mousey = 0;
			}
			try{
				map.update();
				scr.repaint();
			}catch(Exception ex){
				ex.printStackTrace(System.out);
			}
		}
	}

    
    public static BufferedImage getCellImage(Terrain terrain){
    	BufferedImage img = cellImages.get(0).IMG;
    	for(int i = 0; i < cellImages.size(); i++){
    		if(cellImages.get(i).TERRAIN == terrain){
    			img = cellImages.get(i).IMG;
    			break;
    		}
    	}
    	return img;
    }
    
    public static void loadCellImages(){
    	try{
    		cellImages.clear();
    		Scanner reader = new Scanner(new File("data/images/tiles/INDEX.txt"));
    		String index = reader.nextLine();
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
	                	Terrain terrain = Terrain.parseTerrain(files.get(i).substring(files.get(i).indexOf('#') + 1));
	                	BufferedImage img = ImageIO.read(new File("data/images/tiles/" + file));
	                	CI.set(img, terrain);
	                	cellImages.add(CI);
	                }else{
	                	System.out.println("Non-png in index of tiles");
	                }
	            }
            }
    	}catch(Exception ex){}
    }

}
