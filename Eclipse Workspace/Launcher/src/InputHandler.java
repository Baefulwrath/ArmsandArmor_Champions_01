import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class InputHandler implements MouseListener, KeyListener{

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
	
	public static boolean justClicked = false;

	@Override
	public void mousePressed(MouseEvent e) {
		Main.click();
		justClicked = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		justClicked = false;
		Main.move = false;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int KC = e.getKeyCode();
		switch(KC){
			case VK_ESCAPE:Main.exit();
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
