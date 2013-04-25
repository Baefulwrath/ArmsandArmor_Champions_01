import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;


public class Screen extends JPanel{

	public void paint(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g2d.setPaint(Color.BLACK);
		g2d.fill(new Rectangle2D.Float(0, 0, getWidth(), getHeight()));
		
		g2d.setPaint(Color.GRAY);
		g2d.fill(new Rectangle2D.Float(10, 10, getWidth() - 20, 300));
		g2d.fill(new Rectangle2D.Float(10, getHeight() - 115, getWidth() - 20, 100));
		
		for(int i = 0; i < Main.buttons.size(); i++){
			Button B = Main.buttons.get(i);
			if(B.hover){
				g2d.setColor(Color.DARK_GRAY);
			}else{
				g2d.setColor(Color.LIGHT_GRAY);
			}
			g2d.fill(new Rectangle2D.Float(B.BOX.x, B.BOX.y, B.BOX.width, B.BOX.height));
			g2d.setColor(Color.BLACK);
			g2d.drawRect(B.BOX.x, B.BOX.y, B.BOX.width, B.BOX.height);
			if(B.hover){
				g2d.setColor(Color.WHITE);
			}else{
				g2d.setColor(Color.BLACK);
			}
			g2d.drawString(B.TITLE, B.BOX.x + 2, B.BOX.y + 14);
		}
		
		g2d.setColor(Color.RED);
		g2d.drawRect(Main.mouse.x, Main.mouse.y, 1, 1);
	}
}
