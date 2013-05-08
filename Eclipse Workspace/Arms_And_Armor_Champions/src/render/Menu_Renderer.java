package render;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rapplebob.ArmsAndArmorChampions.*;

import containers.ConHand;
import containers.ContainerType;

public class Menu_Renderer extends Renderer {
    public Menu_Renderer(){
        ID = "DEFAULT";
        state = State.MENU;
    }

    
    @Override
    public void mobileRender(SpriteBatch batch) {
    	drawImage(batch, background, 0, 0, (int) AAA_C.getZoom(), (int) AAA_C.getZoom(), 0, true, Color.WHITE, 1.0f);
        if(ConHand.MMH.menus.size() > 0){
            drawMenu(batch, ConHand.MMH.getActiveMenu(), ConHand.getActiveMenuhandler().X, ConHand.getActiveMenuhandler().Y);
        }else{
            com64.draw(batch, "NO MENUS TO DRAW", getScreenX(), 0);
        }
        drawString(batch, "+", AAA_C.inputhandler.mouse.x, AAA_C.inputhandler.mouse.y, com16, Color.RED, 1.0f);
    }

	@Override
	public void staticRender(SpriteBatch batch) {
		drawContainers(batch, ContainerType.MAIN);
        drawString(batch, "+", AAA_C.inputhandler.staticMouse.x, AAA_C.inputhandler.mouse.y, com16, Color.CYAN, 1.0f);
        drawString(batch, AAA_C.inputhandler.staticMouse.x + ", " + AAA_C.inputhandler.staticMouse.y, 200, 200, com16, Color.BLUE, 0.5f);
	}

    @Override
    public void specificUpdate() {
    }

    @Override
    public void loadSpecificResources() throws Exception {
    }

    
}
