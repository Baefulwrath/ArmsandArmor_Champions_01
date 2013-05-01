package render;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rapplebob.ArmsAndArmorChampions.*;

public class Menu_Renderer extends Renderer {
    public Menu_Renderer(){
        ID = "DEFAULT";
        state = State.MENU;
    }

    
    @Override
    public void mobileRender(SpriteBatch batch) {
    	drawImage(batch, background, 0, 0, (int) AAA_C.getZoom(), (int) AAA_C.getZoom(), 0, true);
        if(AAA_C.MMH.menus.size() > 0){
            drawMenu(batch, AAA_C.MMH.getActiveMenu(), AAA_C.getActiveMenuhandler().X, AAA_C.getActiveMenuhandler().Y);
        }else{
            com64.draw(batch, "NO MENUS TO DRAW", getScreenX(), 0);
        }
    }

	@Override
	public void staticRender(SpriteBatch batch) {
		
	}

    @Override
    public void specificUpdate() {
    }

    @Override
    public void loadSpecificResources() throws Exception {
    }

    
}
