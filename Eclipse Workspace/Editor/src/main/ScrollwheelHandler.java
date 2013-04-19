package main;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class ScrollwheelHandler implements MouseWheelListener{

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if(e.getWheelRotation() > 0){
			//down
			CmdHandler.activateCommand("-brushsize");
		}else{
			//up
			CmdHandler.activateCommand("+brushsize");
		}
	}

}
