package main;

import java.awt.image.BufferedImage;

public class CellImage {
	public BufferedImage IMG;
	public int TERRAIN;
	public void set(BufferedImage img, int terrain){
		IMG = img;
		TERRAIN = terrain;
	}
}
