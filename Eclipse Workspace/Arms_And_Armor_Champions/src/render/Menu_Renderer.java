package render;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rapplebob.ArmsAndArmorChampions.*;

import containers.ConHand;
import containers.ContainerType;

public class Menu_Renderer extends Renderer {
    public Menu_Renderer(){
        ID = "MENU";
        state = State.MENU;
    }

    
    @Override
    public void mobileRender(SpriteBatch batch) {
    	drawImage(batch, background, 0, 0, (int) AAA_C.getZoom(), (int) AAA_C.getZoom(), 0, true, Color.WHITE, 1.0f, true);
    }

	@Override
	public void staticRender(SpriteBatch batch) {
        if(ConHand.getActiveMenuholder().menus.size() > 0){
            drawMenu(batch, ConHand.getActiveMenu(), ConHand.getActiveMenuholder().X, ConHand.getActiveMenuholder().Y, com32);
        }else{
            com64.draw(batch, "NO MENUS TO DRAW", getScreenX(), 0);
        }
		drawContainers(batch, ContainerType.MAIN);
	}

    @Override
    public void specificUpdate() {
    }

    @Override
    public void loadSpecificResources() throws Exception {
    }

    
}
