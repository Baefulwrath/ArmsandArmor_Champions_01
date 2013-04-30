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
    	drawImage(batch, background, 0, 0, AAA_C.getZoom(), AAA_C.getZoom(), 0, true);
        drawMap(batch, Worldhandler.getMap(), Worldhandler.getMap().x, Worldhandler.getMap().y, true, true);
        if(AAA_C.gamePaused){
            drawMenu(batch, AAA_C.getActiveMenu(), AAA_C.getActiveMenuhandler().X, AAA_C.getActiveMenuhandler().X, false);
        }
        drawString(batch, AAA_C.inputhandler.mouse.x + ", " + AAA_C.inputhandler.mouse.y, 0, -getScreenY() - 16, com16, Color.RED, 1.0f, false);
        drawString(batch, "size: " + Worldhandler.getMap().cells.length + ", " + Worldhandler.getMap().cells[0].length, -200, -350, com32, Color.RED, 1.0f, false);
    }

    @Override
    public void specificUpdate() {
    }

    
    @Override
    public void loadSpecificResources() throws Exception {
    }
    
}
