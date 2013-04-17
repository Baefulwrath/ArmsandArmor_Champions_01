package main;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Rectangle mouse = new Rectangle(Main.mousex, Main.mousey, 1, 1);
		Rectangle hud = new Rectangle(0, 0, Main.scr.getWidth(), 100);
		if(mouse.intersects(hud)){
			for(int i = 0; i < Main.buttons.size(); i++){
				if(mouse.intersects(Main.buttons.get(i).BOX)){
					CmdHandler.activateCommand(Main.buttons.get(i).CMD);
					break;
				}
			}
			for(int i = 0; i < Main.imgButtons.size(); i++){
				if(mouse.intersects(Main.imgButtons.get(i).BOX)){
					CmdHandler.activateCommand(Main.imgButtons.get(i).CMD);
					break;
				}
			}
		}else{
			Main.painting = true;
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		Main.painting = false;
		
	}

}
