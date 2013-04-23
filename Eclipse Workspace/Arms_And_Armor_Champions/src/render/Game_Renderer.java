package render;

import world.Worldhandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.rapplebob.ArmsAndArmorChampions.*;

public class Game_Renderer extends Renderer {
    public Game_Renderer(){
        ID = "DEFAULT";
        state = State.GAME;
    }

    @Override
    public void render(SpriteBatch batch) {
    	drawImage(batch, background, 0, 0, (int) AAA_C.getZoom(), (int) AAA_C.getZoom(), 0, true);
        drawString(batch, AAA_C.inputhandler.mouse.x + ", " + AAA_C.inputhandler.mouse.y, 0, (int) -getScreenY() - 16, com16, Color.RED, false);
        drawMap(batch, Worldhandler.getMap(), Worldhandler.getMap().x, Worldhandler.getMap().y, true, true);
        if(AAA_C.gamePaused){
//Rita ut bakgrund till menyn för att släppa fokus på spelet.
//Se till att musen fungera in-game för menyer också
            drawMenu(batch, AAA_C.getActiveMenu(), AAA_C.getActiveMenuhandler().x, AAA_C.getActiveMenuhandler().y, false);
        }
        drawString(batch, "size: " + Worldhandler.getMap().cells.length + ", " + Worldhandler.getMap().cells[0].length, -200, -350, com32, Color.RED, false);
    }

    @Override
    public void specificUpdate() {
    }

    
    @Override
    public void loadSpecificResources() throws Exception {
    }
    
}
