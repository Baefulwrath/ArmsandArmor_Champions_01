package render;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rapplebob.ArmsAndArmorChampions.AAA_C;
import com.rapplebob.ArmsAndArmorChampions.State;

import containers.ConHand;
import containers.ContainerType;

public class Editor_Renderer extends Renderer {
    public Editor_Renderer(){
        ID = "DEFAULT";
        state = State.EDITOR;
    }

	@Override
	public void mobileRender(SpriteBatch batch) {
    	drawImage(batch, background, 0, 0, AAA_C.getZoom(), AAA_C.getZoom(), 0, true, Color.WHITE, 1.0f, true);
		drawString(batch, "Map", 0.0f, 0.0f, com10, Color.RED, 1.0f);
        drawString(batch, ConHand.getActiveContainer().EXIT.BOX.x + ", " + ConHand.getActiveContainer().EXIT.BOX.y, -100, 100, com10, Color.CYAN, 0.5f);
        drawString(batch, ConHand.getActiveContainer().getBox().x + ", " + ConHand.getActiveContainer().getBox().y, 0, 100, com10, Color.CYAN, 0.5f);
        drawString(batch, AAA_C.inputhandler.staticMouse.x + ", " + AAA_C.inputhandler.staticMouse.y, -100, 50, com10, Color.BLUE, 0.5f);
	}

	@Override
	public void staticRender(SpriteBatch batch) {
		drawString(batch, "HUD", getScreenX(), getScreenY(), com10, Color.BLUE, 1.0f);
		drawContainers(batch, ContainerType.EDITOR);
        if(AAA_C.editorPaused){
            drawMenu(batch, ConHand.getActiveMenu(), ConHand.getActiveMenuholder().X, ConHand.getActiveMenuholder().Y);
        }
	}

	@Override
	public void specificUpdate() {
	}

	@Override
	public void loadSpecificResources() throws Exception {
	}

}
