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
		int button = e.getButton();
		switch(button){
			case MouseEvent.BUTTON1:leftClick(mouse, hud);
			break;
			case MouseEvent.BUTTON2:middleClick(mouse, hud);
			break;
			case MouseEvent.BUTTON3:rightClick(mouse, hud);
			break;
		}
		
	}
	
	public void leftClick(Rectangle mouse, Rectangle hud){
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

	public void middleClick(Rectangle mouse, Rectangle hud){
		CmdHandler.activateCommand("resetBrushsize");
	}

	public void rightClick(Rectangle mouse, Rectangle hud){
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		Main.painting = false;
		
	}

}
