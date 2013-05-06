package main;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class ImageButton extends Button{
	
	public BufferedImage image;

	public ImageButton(BufferedImage img, String title, String cmd, int x, int y, int width, int height) {
		super(title, cmd, x, y);
		// TODO Auto-generated constructor stub
		image = img;
		BOX = new Rectangle(x, y, width, height);
	}

}
