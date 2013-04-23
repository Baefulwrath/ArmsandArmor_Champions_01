package main;


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
			case "+brushsize":if(Main.brush.SIZE < 500){
				Main.brush.SIZE += 5;
			};
			break;
			case "-brushsize":if(Main.brush.SIZE > 5){
				Main.brush.SIZE -= 5;
			}
			break;
			case "resetBrushsize":Main.brush.SIZE = 5;
			break;
			case "changeTitle":changeTitle();
			break;
			case "changeId":changeId();
			break;
			case "saveMap":Main.saveMap();
			break;
			case "loadMap":Main.loadMap();
			break;
			case "clearMap":Main.clearMap();
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
			case "+zoom":Main.zoomIn();
			break;
			case "-zoom":Main.zoomOut();
			break;
			case "resetmappos":Main.map.x = 100;Main.map.y = 150;
			break;
		}
	}
	
	public static void changeBrushClimate(){
		String choices[] = Main.getClimates();
		String input = (String) JOptionPane.showInputDialog(Main.frame, "Climate choice", "Climate Choice", JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
		if(input != null){
			Main.brush.CLIMATE = Main.getClimateId(input);
			Main.brush.TERRAIN = Main.getTerrainId(Main.getTerrainsByClimate(input)[0]);
		}
	}
	
	public static void changeBrushTerrain(){
		String choices[] = Main.getTerrainsByClimate(Main.getClimateName(Main.brush.CLIMATE));
		String input = (String) JOptionPane.showInputDialog(Main.frame, "Terrain choice", "Terrain Choice", JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
		if(input != null){
			Main.brush.TERRAIN = Main.getTerrainId(input);
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
	
}
