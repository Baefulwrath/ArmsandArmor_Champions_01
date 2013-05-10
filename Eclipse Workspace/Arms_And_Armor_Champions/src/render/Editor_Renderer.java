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
		drawString(batch, "Map", 0.0f, 0.0f, com10, Color.RED, 1.0f);
        if(AAA_C.editorPaused){
            drawMenu(batch, ConHand.getActiveMenu(), ConHand.getActiveMenuholder().X, ConHand.getActiveMenuholder().Y);
        }
        drawString(batch, ConHand.getActiveContainer().EXIT.BOX.x + ", " + ConHand.getActiveContainer().EXIT.BOX.y, -100, 100, com10, Color.CYAN, 0.5f);
        drawString(batch, AAA_C.inputhandler.staticMouse.x + ", " + AAA_C.inputhandler.staticMouse.y, -100, 50, com10, Color.BLUE, 0.5f);
	}

	@Override
	public void staticRender(SpriteBatch batch) {
		drawString(batch, "HUD", getScreenX(), getScreenY(), com10, Color.BLUE, 1.0f);
		drawContainers(batch, ContainerType.EDITOR);
	}

	@Override
	public void specificUpdate() {
	}

	@Override
	public void loadSpecificResources() throws Exception {
	}

}
