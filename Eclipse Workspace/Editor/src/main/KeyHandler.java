package main;

import static java.awt.event.KeyEvent.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

	@Override
	public void keyPressed(KeyEvent e) {
		int KC = e.getKeyCode();
		switch(KC){
			case VK_ESCAPE:System.exit(0);
				break;
			case VK_UP:if(Main.map.y < 100000){Main.map.y += Main.scrollingSpeed;};
				break;
			case VK_DOWN:if(Main.map.y > -100000){Main.map.y -= Main.scrollingSpeed;};
				break;
			case VK_LEFT:if(Main.map.x < 100000){Main.map.x += Main.scrollingSpeed;};
				break;
			case VK_RIGHT:if(Main.map.x > -100000){Main.map.x -= Main.scrollingSpeed;};
				break;
			case VK_PLUS:Main.scrollingSpeed++;
				break;
			case VK_MINUS:Main.scrollingSpeed--;
				break;
			case VK_ADD:Main.scrollingSpeed++;
				break;
			case VK_SUBTRACT:Main.scrollingSpeed--;
				break;
			case VK_SPACE:
				if(Main.showGrid){
					Main.showGrid = false;
				}else{
					Main.showGrid = true;
				};
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
}
