package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Screen extends JPanel{
	
	private BufferedImage grid;
	private BufferedImage back;
	
	public Screen(){
		load();
		
	}
	
	public void load(){
		try{
			grid = ImageIO.read(new File("data/images/grid.png"));
			back = ImageIO.read(new File("data/images/background.png"));
		}catch(Exception ex){
			ex.printStackTrace(System.out);
		}
	}

	@Override
	public void paint(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g2d.setPaint(Color.BLACK);
		g2d.fill(new Rectangle2D.Float(0, 0, getWidth(), getHeight()));
		g2d.drawImage(back, 0, 0, getWidth(), getHeight(), null);
		g2d.setPaint(Color.RED);
		g2d.drawString("Show grid: " + Main.showGrid, 10, 24);
		g2d.drawString("Mouse: " + Main.mousex + ", " + Main.mousey, 10, 36);
		
		if(Main.map.loaded){
			for(int x = 0; x < Main.map.cells.length; x++){
				for(int y = 0; y < Main.map.cells[x].length; y++){
					if(visible(new Rectangle( (int) ((Main.map.getCellX(x, y) + Main.map.x - Main.map.cells[x][y].SIDE) * Main.zoom) + 1, (int) ((Main.map.getCellY(x, y) + Main.map.y - Main.map.cells[x][y].SIDE) * Main.zoom), (int) (Main.map.cells[x][y].DIAMETER * Main.zoom), (int) (Main.map.cells[x][y].DIAMETER * Main.zoom)))){
						g2d.drawImage(Main.getClimateImageByClimate(Main.map.cells[x][y].CLIMATE), (int) ((Main.map.getCellX(x, y) + Main.map.x - Main.map.cells[x][y].SIDE) * Main.zoom) + 1, (int) ((Main.map.getCellY(x, y) + Main.map.y - Main.map.cells[x][y].SIDE) * Main.zoom), (int) (Main.map.cells[x][y].DIAMETER * Main.zoom), (int) (Main.map.cells[x][y].DIAMETER * Main.zoom), null);
						g2d.drawImage(Main.getTerrainImage(Main.map.cells[x][y].TERRAIN), (int) ((Main.map.getCellX(x, y) + Main.map.x - Main.map.cells[x][y].SIDE) * Main.zoom) + 1, (int) ((Main.map.getCellY(x, y) + Main.map.y - Main.map.cells[x][y].SIDE) * Main.zoom), (int) (Main.map.cells[x][y].DIAMETER * Main.zoom), (int) (Main.map.cells[x][y].DIAMETER * Main.zoom), null);
						if(Main.showGrid){
							g2d.drawImage(grid, (int) ((Main.map.getCellX(x, y) + Main.map.x - Main.map.cells[x][y].SIDE) * Main.zoom) + 1, (int) ((Main.map.getCellY(x, y) + Main.map.y - Main.map.cells[x][y].SIDE) * Main.zoom), (int) (Main.map.cells[x][y].DIAMETER * Main.zoom), (int) (Main.map.cells[x][y].DIAMETER * Main.zoom), null);
						}
						if(Main.debug){
							g2d.setPaint(Color.RED);
							g2d.drawString(x + ", " + y,  (int) ((Main.map.getCellX(x, y) + Main.map.x) * Main.zoom), (int) ((Main.map.getCellY(x, y) + Main.map.y) * Main.zoom));
							g2d.drawString(Main.getTerrainName(Main.map.cells[x][y].TERRAIN),  (int) ((Main.map.getCellX(x, y) + Main.map.x) * Main.zoom), (int) ((Main.map.getCellY(x, y) + Main.map.y) * Main.zoom) + 12);
							g2d.drawString(Main.getClimateName(Main.map.cells[x][y].CLIMATE),  (int) ((Main.map.getCellX(x, y) + Main.map.x) * Main.zoom), (int) ((Main.map.getCellY(x, y) + Main.map.y) * Main.zoom) + 24);
							g2d.drawPolygon(Main.map.cells[x][y].actualPolygon);
						}
					}
				}
			}
		}
		if(Main.debug && Main.map.loaded){
			//g2d.drawRect(Main.map.x - Main.map.cells[0][0].SIDE, Main.map.y - Main.map.cells[0][0].SIDE, Main.map.getWidth(), Main.map.getHeight());
		}
		
		g2d.setPaint(Color.DARK_GRAY);
		g2d.fill(new Rectangle2D.Float(0, 0, getWidth(), 100));
		g2d.setPaint(Color.GRAY);
		g2d.fill(new Rectangle2D.Float(0, 95, getWidth(), 5));
		if(Main.buttons.size() > 0){
			for(int i = 0; i < Main.buttons.size(); i++){
				try{
					if(Main.buttons.get(i).hover){
						g2d.setColor(Color.WHITE);
					}else{
						g2d.setPaint(Color.LIGHT_GRAY);
					}
					g2d.fill(new Rectangle2D.Float(Main.buttons.get(i).BOX.x, Main.buttons.get(i).BOX.y, Main.buttons.get(i).BOX.width, Main.buttons.get(i).BOX.height));
					g2d.setColor(Color.BLACK);
					g2d.drawString(Main.buttons.get(i).TITLE, Main.buttons.get(i).BOX.x + 2, Main.buttons.get(i).BOX.y + 12);
					g2d.setColor(Color.BLACK);
					g2d.drawRect(Main.buttons.get(i).BOX.x, Main.buttons.get(i).BOX.y, Main.buttons.get(i).BOX.width, Main.buttons.get(i).BOX.height);
				}catch(Exception ex){}
			}
		}
		if(Main.imgButtons.size() > 0){
			for(int i = 0; i < Main.imgButtons.size(); i++){
				try{
					if(Main.imgButtons.get(i).hover){
						g2d.setColor(Color.WHITE);
					}else{
						g2d.setPaint(Color.LIGHT_GRAY);
					}
					g2d.fill(new Rectangle2D.Float(Main.imgButtons.get(i).BOX.x, Main.imgButtons.get(i).BOX.y, Main.imgButtons.get(i).BOX.width, Main.imgButtons.get(i).BOX.height));
					g2d.drawImage(Main.imgButtons.get(i).image, Main.imgButtons.get(i).BOX.x, Main.imgButtons.get(i).BOX.y, Main.imgButtons.get(i).BOX.width, Main.imgButtons.get(i).BOX.height, null);
					g2d.setColor(Color.WHITE);
					g2d.drawString(Main.imgButtons.get(i).TITLE, Main.imgButtons.get(i).BOX.x + 2, Main.imgButtons.get(i).BOX.y + Main.imgButtons.get(i).BOX.height + 12);
					g2d.setColor(Color.BLACK);
					g2d.drawRect(Main.imgButtons.get(i).BOX.x, Main.imgButtons.get(i).BOX.y, Main.imgButtons.get(i).BOX.width, Main.imgButtons.get(i).BOX.height);
				}catch(Exception ex){}
			}
		}
		
		g2d.setColor(Color.BLUE);
		g2d.drawRect(Main.brush.BOX.x, Main.brush.BOX.y, Main.brush.BOX.width, Main.brush.BOX.height);
		
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
		
		
		g2d.drawImage(back, 0, 0, getWidth(), getHeight(), null);
	}
	
	public boolean visible(Rectangle r){
		boolean temp = false;
		if(r.intersects(new Rectangle(0, 0, getWidth(), getHeight()))){
			temp = true;
		}
		return temp;
	}
	
}
