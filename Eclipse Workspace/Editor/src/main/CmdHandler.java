package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class CmdHandler {
	public static void activateCommand(String cmd){
		if(Main.debug){
			System.out.println(cmd);
		}
		switch(cmd){
			case "switchshowgrid":
				if(Main.showGrid){
					Main.showGrid = false;
				}else{
					Main.showGrid = true;
				}
				;
			break;
			case "switchdebug":
				if(Main.debug){
					Main.debug = false;
				}else{
					Main.debug = true;
				}
				;
			break;
			case "changeBrushClimate":changeBrushClimate();
			break;
			case "changeBrushTerrain":changeBrushTerrain();
			break;
			case "+brushsize":if(Main.brushSize < 500){
				Main.brushSize += 5;
			};
			break;
			case "-brushsize":if(Main.brushSize > 5){
				Main.brushSize -= 5;
			}else{
				Main.brushSize = 1;
			};
			break;
			case "changeTitle":changeTitle();
			break;
			case "changeId":changeId();
			break;
			case "saveMap":saveMap();
			break;
			case "loadMap":loadMap();
			break;
			case "clearMap":clearMap();
			break;
			case "+mapwidth":if(Main.map.width < 500){
				Main.map.width += 1;
			};
			break;
			case "-mapwidth":if(Main.map.width > 1){
				Main.map.width -= 1;
			}else{
				Main.map.width = 1;
			};
			case "+mapheight":if(Main.map.height < 500){
				Main.map.height += 1;
			};
			break;
			case "-mapheight":if(Main.map.height > 1){
				Main.map.height -= 1;
				Main.map.height = 1;
			};
		}
	}
	
	public static void changeBrushClimate(){
		String choices[] = Main.getClimates();
		String input = (String) JOptionPane.showInputDialog(Main.frame, "Climate choice", "Climate Choice", JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
		if(input != null){
			Main.editorTile.CLIMATE = input;
		}
	}
	
	public static void changeBrushTerrain(){
		String choices[] = Main.getTerrainsByClimate(Main.editorTile.CLIMATE);
		String input = (String) JOptionPane.showInputDialog(Main.frame, "Terrain choice", "Terrain Choice", JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
		if(input != null){
			Main.editorTile.TERRAIN = input;
		}
	}
	
	public static void changeTitle(){
		String input = JOptionPane.showInputDialog(Main.frame, "Input title", "Input title");
		if(input != null){
			Main.map.title = input;
		}
	}
	
	public static void changeId(){
		String input = JOptionPane.showInputDialog(Main.frame, "Input id", "Input id");
		if(input != null){
			Main.map.id = input;
		}
	}
	
	public static String lastDirectory = "data/maps";
	
	public static void saveMap(){
		JFileChooser fc = new JFileChooser(lastDirectory);
		int returnVal = fc.showSaveDialog(Main.frame);
		lastDirectory = fc.getCurrentDirectory().getPath();
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			if(file.exists()){
				if(file.isFile() && file.getName().substring(file.getName().length() - 3).equals("txt")){
					save(file);
				}else{
					JOptionPane.showMessageDialog(Main.frame, "Bad file");
				}
			}else{
				if(file.getName().substring(file.getName().length() - 3).equals("txt")){
					save(file);
				}else{
					JOptionPane.showMessageDialog(Main.frame, "Bad file, remember to include .txt");
				}
			}
        }
	}
	
	public static void save(File file){
		try{
			BufferedWriter pen = new BufferedWriter(new FileWriter(file));
			pen.write(Main.map.title + System.getProperty("line.separator"));
			pen.write(Main.map.id + System.getProperty("line.separator"));
			pen.write(Main.map.width + System.getProperty("line.separator"));
			pen.write(Main.map.height + System.getProperty("line.separator"));
			for(int x = 0; x < Main.map.cells.length; x++){
				for(int y = 0; y < Main.map.cells[x].length; y++){
					pen.write(x + System.getProperty("line.separator"));
					pen.write(y + System.getProperty("line.separator"));
					pen.write(Main.map.cells[x][y].WIDTH + System.getProperty("line.separator"));
					pen.write(Main.map.cells[x][y].CLIMATE.toString() + System.getProperty("line.separator"));
					pen.write(Main.map.cells[x][y].TERRAIN.toString() + System.getProperty("line.separator"));
				}
			}
			pen.close();
		}catch(Exception ex){
			JOptionPane.showMessageDialog(Main.frame, "Error saving file");
		}
	}
	
	public static void loadMap(){
		JFileChooser fc = new JFileChooser(lastDirectory);
		int returnVal = fc.showOpenDialog(Main.frame);
		lastDirectory = fc.getCurrentDirectory().getPath();
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
    		if(file.isFile() && file.getName().substring(file.getName().length() - 3).equals("txt")){
    			load(file);
    		}else{
    			JOptionPane.showMessageDialog(Main.frame, "Bad file");
    		}
        }
	}
	
	public static void load(File file){
		try{
			Main.map.loaded = false;
			Scanner reader = new Scanner(file);
			Main.map.title = reader.nextLine();
			Main.map.id = reader.nextLine();
			Main.map.width = Integer.parseInt(reader.nextLine());
			Main.map.height = Integer.parseInt(reader.nextLine());
			while(reader.hasNextLine()){
				Main.map.cells[Integer.parseInt(reader.nextLine())][Integer.parseInt(reader.nextLine())] = new Cell(Integer.parseInt(reader.nextLine()), reader.nextLine(), reader.nextLine());
			}
			Main.map.load(0, 0);
		}catch(Exception ex){
			JOptionPane.showMessageDialog(Main.frame, "Error loading file");
		}
	}
	
	public static void clearMap(){
		if(JOptionPane.showConfirmDialog(Main.frame, "Do you really want to clear the map?", "Rasputinbullar?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
			Main.createNewMap();
		}
	}
	
}
