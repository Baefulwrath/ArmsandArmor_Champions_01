package render;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.rapplebob.ArmsAndArmorChampions.*;

public class Game_Renderer extends Renderer {
    public Game_Renderer(){
        ID = "DEFAULT";
        state = State.GAME;
    }

    @Override
    public void render(ShapeRenderer triangleBatch, SpriteBatch batch) {
        drawString(batch, AAA_C.inputhandler.mouse.x + ", " + AAA_C.inputhandler.mouse.y, 0, -getScreenY() - 16, com16, Color.RED);
        drawMap(triangleBatch, batch, AAA_C.worldhandler.getMap(), 0, 0, true);
        if(AAA_C.gamePaused){
//Rita ut bakgrund till menyn för att släppa fokus på spelet.
//Se till att musen fungera in-game för menyer också
            drawMenu(batch, AAA_C.getActiveMenu(), AAA_C.getActiveMenuhandler().x, AAA_C.getActiveMenuhandler().y);
        }
        drawString(batch, "size: " + AAA_C.worldhandler.getMap().cells.size(), -200, -200, com64, Color.RED);
    }

    @Override
    public void specificUpdate() {
    }

    @Override
    public void loadResources() throws Exception {
    }
    
}
