import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class MouseHandler implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}
	
	public static boolean justClicked = false;

	@Override
	public void mousePressed(MouseEvent arg0) {
		Main.click();
		justClicked = true;
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		justClicked = false;
	}

}
