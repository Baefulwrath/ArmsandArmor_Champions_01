package main;

import java.awt.image.BufferedImage;

public class CellImage {
	public BufferedImage IMG;
	public String TERRAIN;
	public void set(BufferedImage img, String terrain){
		IMG = img;
		TERRAIN = terrain;
	}
}
