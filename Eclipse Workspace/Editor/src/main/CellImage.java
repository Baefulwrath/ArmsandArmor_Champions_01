package main;

import java.awt.image.BufferedImage;

public class CellImage {
	public BufferedImage IMG;
	public Terrain TERRAIN;
	public void set(BufferedImage img, Terrain terrain){
		IMG = img;
		TERRAIN = terrain;
	}
}
