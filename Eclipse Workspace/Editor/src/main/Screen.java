package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Screen extends JPanel{
	
	private BufferedImage grid;
	
	public Screen(){
		load();
		
	}
	
	public void load(){
		try{
			grid = ImageIO.read(new File("data/images/grid.png"));
		}catch(Exception ex){
			ex.printStackTrace(System.out);
		}
	}

	@Override
	public void paint(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g2d.setPaint(Color.BLACK);
		g2d.fill(new Rectangle2D.Float(0, 0, getWidth(), getHeight()));
		g2d.setPaint(Color.RED);
		g2d.drawString("Show grid: " + Main.showGrid, 10, 24);
		g2d.drawString("Mouse: " + Main.mousex + ", " + Main.mousey, 10, 36);
		if(Main.map.loaded){
			for(int i = 0; i < Main.map.cells.size(); i++){
				//Draw image
				g2d.drawImage(Main.getCellImage(Main.map.cells.get(i).TERRAIN), Main.map.getCellX(i) + Main.map.x - Main.map.cells.get(i).SIDE, Main.map.getCellY(i) + Main.map.y - Main.map.cells.get(i).SIDE, null);
				if(Main.showGrid){
					g2d.drawImage(grid, Main.map.getCellX(i) + Main.map.x - Main.map.cells.get(i).SIDE, Main.map.getCellY(i) + Main.map.y - Main.map.cells.get(i).SIDE, null);
					//Draw icons
				}
				//Draw marker
				//g2d.drawPolygon(Main.map.cells.get(i).actualPolygon);
				if(Main.debug){
					g2d.drawString("" + i, Main.map.getCellX(i) + Main.map.x, Main.map.getCellY(i) + Main.map.y);
				}
			}
		}else{
			g2d.drawString("Map not loaded", 200, 200);
		}
		
		
		g2d.setPaint(Color.BLACK);
		g2d.fill(new Rectangle2D.Float(0, 0, getWidth(), 100));
		if(Main.buttons.size() > 0){
			for(int i = 0; i < Main.buttons.size(); i++){
				try{
					g2d.setPaint(Color.RED);
					g2d.drawString(Main.buttons.get(i).TITLE, Main.buttons.get(i).BOX.x + 2, Main.buttons.get(i).BOX.y + 12);
					g2d.drawRect(Main.buttons.get(i).BOX.x, Main.buttons.get(i).BOX.y, Main.buttons.get(i).BOX.width, Main.buttons.get(i).BOX.height);
				}catch(Exception ex){}
			}
		}
		if(Main.imgButtons.size() > 0){
			for(int i = 0; i < Main.imgButtons.size(); i++){
				g2d.drawImage(Main.imgButtons.get(i).image, Main.imgButtons.get(i).BOX.x, Main.imgButtons.get(i).BOX.y, null);
				g2d.setPaint(Color.RED);
				g2d.drawString(Main.imgButtons.get(i).TITLE, Main.imgButtons.get(i).BOX.x + 2, Main.imgButtons.get(i).BOX.y + Main.imgButtons.get(i).BOX.height + 12);
				g2d.drawRect(Main.imgButtons.get(i).BOX.x, Main.imgButtons.get(i).BOX.y, Main.imgButtons.get(i).BOX.width, Main.imgButtons.get(i).BOX.height);
			}
		}
		
		g2d.setColor(Color.BLUE);
		g2d.drawRect(Main.brush.x, Main.brush.y, Main.brush.width, Main.brush.height);
		
		g2d.setPaint(Color.RED);
		if(Main.debug){
			g2d.drawRect(Main.mousex, Main.mousey, 1, 1);
		}

		g2d.setPaint(Color.RED);
		if(Main.map.x < 0){
			g2d.fill(new Rectangle2D.Float(0, (getHeight() / 4), 10, (getHeight() / 2)));
		}else if(Main.map.x > getWidth()){
			g2d.fill(new Rectangle2D.Float((getWidth() - 10), (getHeight() / 4), 10, (getHeight() / 2)));
		}
		
	}
	
	public void drawMap(Graphics2D g2d, Map map, int x, int y){
	}
}
