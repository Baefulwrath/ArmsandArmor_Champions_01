package editor;

import input.Inputhandler;

import com.rapplebob.ArmsAndArmorChampions.AAA_C;

import containers.ConHand;
import world.*;

public class Editorhandler {
	public static Brush brush = new Brush();
	public static boolean painting = false;
	private static boolean actuallyPainting = false;
	public static int mapWidth = 20;
	public static int mapHeight = 20;
	public static int mapSpeed = 32;
	public static Map map = new Map(mapWidth, mapHeight, Worldhandler.hexWidth);
	
	public static boolean moveUp = false;
	public static boolean moveDown = false;
	public static boolean moveLeft = false;
	public static boolean moveRight = false;
	private static long lastMovement = 0;
	private static long movementInterval = 50;
	
	
	public static void update(){
		paintCheck();
		if(actuallyPainting){
			paint();
		}
		if(readyToMove()){
			move();
		}
	}
	
	public static void paintCheck(){
		if(painting){
			if(AAA_C.editorPaused || ConHand.collides(Inputhandler.staticMouse, AAA_C.getCTypeFromState(AAA_C.state))){
				actuallyPainting = false;
			}else{
				actuallyPainting = true;
			}
		}
	}
	
	public static void paint(){
		for(int x = 0; x < map.CELLS.length; x++){
			for(int y = 0; y < map.CELLS[x].length; y++){
				if(map.CELLS[x][y].intersects(brush.BOX)){
					map.CELLS[x][y].mirror(brush.CELL);
				}
			}
		}
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
			map.Y += mapSpeed;
		}else if(moveDown){
			map.Y -= mapSpeed;
		}
		if(moveLeft){
			map.X -= mapSpeed;
		}else if(moveRight){
			map.X += mapSpeed;
		}
	}
}
