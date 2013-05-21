package render;

import world.Worldhandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.rapplebob.ArmsAndArmorChampions.*;

import containers.ConHand;
import containers.ContainerType;

public class Game_Renderer extends Renderer {
    public Game_Renderer(){
        ID = "GAME";
        state = State.GAME;
    }

    @Override
    public void mobileRender(SpriteBatch batch) {
    	drawImage(batch, background, 0, 0, AAA_C.getZoom(), AAA_C.getZoom(), 0, true, Color.WHITE, 1.0f, true);
        drawMap(batch, Worldhandler.getMap(), Worldhandler.getMap().x, Worldhandler.getMap().y, true);
    }

	@Override
	public void staticRender(SpriteBatch batch) {
		drawContainers(batch, ContainerType.GAME);
        drawString(batch, AAA_C.inputhandler.mouse.x + ", " + AAA_C.inputhandler.mouse.y, 0, -getScreenY() - 16, com16, Color.RED, 1.0f);
        drawString(batch, "size: " + Worldhandler.getMap().cells.length + ", " + Worldhandler.getMap().cells[0].length, -200, -350, com32, Color.RED, 1.0f);
        if(AAA_C.gamePaused){
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
