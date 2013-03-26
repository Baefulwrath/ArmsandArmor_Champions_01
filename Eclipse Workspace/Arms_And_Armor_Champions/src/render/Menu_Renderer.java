package render;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.rapplebob.ArmsAndArmorChampions.*;

public class Menu_Renderer extends Renderer {
    public Menu_Renderer(){
        ID = "DEFAULT";
        state = State.MENU;
    }

    
    @Override
    public void render(ShapeRenderer shapeBatch, SpriteBatch batch) {
        if(AAA_C.MMH.menus.size() > 0){
            drawMenu(batch, AAA_C.MMH.getActiveMenu(), AAA_C.getActiveMenuhandler().x, AAA_C.getActiveMenuhandler().y);
        }else{
            com64.draw(batch, "NO MENUS TO DRAW", getScreenX(), 0);
        }
    }

    @Override
    public void specificUpdate() {
    }

    @Override
    public void loadResources() throws Exception {
    }
    
}
