package render;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collections;

import world.Map;
import world.Worldhandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.rapplebob.ArmsAndArmorChampions.*;

import containers.Activator;
import containers.ActivatorType;
import containers.Alignment;
import containers.ConHand;
import containers.Container;
import containers.ContainerType;
import containers.Content;
import static containers.ContentType.*;
import containers.Menu;

public abstract class Renderer {

    public Renderer() {
        loadFonts();
    }
    public String ID = "DEFAULT";
    public State state = null;
    public Sprite sprite = new Sprite();
    public Texture grid;
    public Texture background;
    public Texture conBack;
    public Texture conFill;
    public Texture conCon;
    public Texture actBack;
    
    public BitmapFont com64;
    public BitmapFont com32;
    public BitmapFont com16;
    public BitmapFont com10;
    public BitmapFont com32_BI;
    public BitmapFont com16_BI;
    public BitmapFont com10_BI;

    public abstract void mobileRender(SpriteBatch batch);
    
    public abstract void staticRender(SpriteBatch batch);

    public abstract void specificUpdate();

    public void generalUpdate() {
    	
    	if(active){
    		specificUpdate();
    	}
    }
    public boolean active = false;

    public abstract void loadSpecificResources() throws Exception;
    
    public void loadResources(){
    	grid = new Texture(Gdx.files.internal("data/images/grid.png"));
    	background = new Texture(Gdx.files.internal("data/images/defaultBackground.png"));
    	conBack = new Texture(Gdx.files.internal("data/images/conBack.png"));
    	conFill = new Texture(Gdx.files.internal("data/images/conFill.png"));
    	conCon = new Texture(Gdx.files.internal("data/images/conCon.png"));
    	actBack = new Texture(Gdx.files.internal("data/images/actBack.png"));
    }

    public void drawImage(SpriteBatch batch, Texture img, float x, float y, float scale, int rotation, boolean smooth, Color tint, float opacity, boolean centered) {
    	if(smooth){
        	img.setFilter(TextureFilter.Linear, TextureFilter.Linear);
    	}else{
        	img.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
    	}
    	if(!centered){
    		x += img.getWidth() / 2;
    		y += img.getHeight() / 2;
    	}
    	sprite = new Sprite(img);
    	sprite.setSize(sprite.getWidth(), sprite.getHeight());
    	sprite.setScale(scale);
    	sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
    	sprite.setPosition(x - (sprite.getWidth() / 2), y - (sprite.getHeight() / 2));
    	sprite.setRotation(rotation);
    	sprite.setColor(tint.r, tint.g, tint.b, opacity);
    	actualDrawImage(batch);
    }

    public void drawImage(SpriteBatch batch, Texture img, float x, float y, float width, float height, int rotation, boolean smooth, Color tint, float opacity, boolean centered) {
    	if(smooth){
        	img.setFilter(TextureFilter.Linear, TextureFilter.Linear);
    	}else{
        	img.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
    	}
    	if(!centered){
    		x += width / 2;
    		y += height / 2;
    	}
    	sprite = new Sprite(img);
    	sprite.setSize(1, 1);
    	sprite.setScale(width, height);
    	sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
    	sprite.setPosition(x - (sprite.getWidth() / 2), y - (sprite.getHeight() / 2));
    	sprite.setRotation(rotation);
    	sprite.setColor(tint.r, tint.g, tint.b, opacity);
    	actualDrawImage(batch);
    }

    public void drawImage(SpriteBatch batch, TextureRegion img, float x, float y, float scale, int rotation, boolean smooth, Color tint, float opacity, boolean centered) {
    	if(smooth){
        	img.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
    	}else{
        	img.getTexture().setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
    	}
    	if(!centered){
    		x += img.getRegionWidth() / 2;
    		y += img.getRegionHeight() / 2;
    	}
    	sprite = new Sprite(img);
    	sprite.setSize(sprite.getWidth(), sprite.getHeight());
    	sprite.setScale(scale);
    	sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
    	sprite.setPosition(x - (sprite.getWidth() / 2), y - (sprite.getHeight() / 2));
    	sprite.setRotation(rotation);
    	sprite.setColor(tint.r, tint.g, tint.b, opacity);
    	actualDrawImage(batch);
    }

    public void drawImage(SpriteBatch batch, TextureRegion img, float x, float y, float width, float height, int rotation, boolean smooth, Color tint, float opacity, boolean centered) {
    	if(smooth){
        	img.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
    	}else{
        	img.getTexture().setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
    	}
    	if(!centered){
    		x += width / 2;
    		y += height / 2;
    	}
    	sprite = new Sprite(img);
    	sprite.setSize(1, 1);
    	sprite.setScale(width, height);
    	sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
    	sprite.setPosition(x - (sprite.getWidth() / 2), y - (sprite.getHeight() / 2));
    	sprite.setRotation(rotation);
    	sprite.setColor(tint.r, tint.g, tint.b, opacity);
    	actualDrawImage(batch);
    }
    
    private void actualDrawImage(SpriteBatch batch){
    	//if(onScreen(sprite)){
    		sprite.draw(batch);
    	//}
    }
    
    public boolean onScreen(Sprite spr){
    	boolean temp = false;
    	if(AAA_C.screenRect.intersects(new Rectangle((int) spr.getX(), (int) spr.getY(), (int) spr.getWidth(), (int) spr.getHeight()))){
    		temp = true;
    	}
    	return temp;
    }

    public void loadFonts() {
        com64 = new BitmapFont(Gdx.files.internal("data/fonts/com64.fnt"), Gdx.files.internal("data/fonts/com64.png"), false, false);
        com32 = new BitmapFont(Gdx.files.internal("data/fonts/com32.fnt"), Gdx.files.internal("data/fonts/com32.png"), false, false);
        com16 = new BitmapFont(Gdx.files.internal("data/fonts/com16.fnt"), Gdx.files.internal("data/fonts/com16.png"), false, false);
        com10 = new BitmapFont(Gdx.files.internal("data/fonts/com10.fnt"), Gdx.files.internal("data/fonts/com10.png"), false, false);
        com32_BI = new BitmapFont(Gdx.files.internal("data/fonts/com32_BI.fnt"), Gdx.files.internal("data/fonts/com32_BI.png"), false, false);
        com16_BI = new BitmapFont(Gdx.files.internal("data/fonts/com16_BI.fnt"), Gdx.files.internal("data/fonts/com16_BI.png"), false, false);
        com10_BI = new BitmapFont(Gdx.files.internal("data/fonts/com10_BI.fnt"), Gdx.files.internal("data/fonts/com10_BI.png"), false, false);
    }


    public float getScreenX() {
        return -(AAA_C.w / 2);
    }

    public float getScreenY() {
        return -(AAA_C.h / 2);
    }

    public void drawString(SpriteBatch batch, String string, float x, float y, BitmapFont font, Color col, float opacity) {
    	y -= font.getCapHeight();
        LabelStyle style = new LabelStyle(font, col);
        Label lab = new Label(string, style);
        lab.setPosition(x, y);
        lab.draw(batch, opacity);
    }

    public void drawMenu(SpriteBatch batch, Content c, int x, int y, BitmapFont font, boolean useMarker, boolean title) {
    	Menu m = (Menu) c;
    	x = x + m.X;
    	y = y + m.Y;
    	if(title){
	        drawString(batch, m.menuTitle, x, y, font, Color.WHITE, 1.0f);
	        drawString(batch, "______________________", x, y - 8, font, Color.WHITE, 1.0f);
    	}
        if (m.acts.size() > 0) {
            for (int i = 0; i < m.acts.size(); i++) {
                Activator a = m.acts.get(i);
                Color col = getActColor(a.AT);
                String print = a.title;
                if(AAA_C.debug){
                    print += " - " + a.script + " - " + a.AT.toString();
                }
                //drawString(batch, print, x, y - 50 - (i * 40), com32, col, 1.0f);
                drawActivator(batch, a, x + a.BOX.x, y + a.BOX.y, false, font, col, Color.WHITE, 1.0f);
                if(useMarker){
	                if(i == m.activeAct){
	                    String marker;
	                    switch(a.AT){
	                        case BUTTON:marker = "*";
	                            break;
	                        case INPUT:marker = ">";
	                            break;
	                        case TEXT:marker = "-";
	                            break;
	                        default:marker = "";
	                    }
	                    drawString(batch, marker, x + a.BOX.x - 32, y + a.BOX.y + (font.getCapHeight()), font, Color.WHITE, 1.0f);
	                }
                }
            }
        } else {
            drawString(batch, "EMPTY MENU", x, y - 50, font, Color.WHITE, 1.0f);
        }
    }

    public Color getActColor(ActivatorType type) {
        Color col;
        switch (type) {
            case BUTTON:
                col = Color.WHITE;
                break;
            case INPUT:
                col = Color.YELLOW;
                break;
            case TEXT:
                col = Color.CYAN;
                break;
            default:
                col = Color.WHITE;
        }
        return col;
    }
    
    
    public void drawMap(SpriteBatch batch, Map map, int mx, int my, boolean central){
        mx += getCentralMapX(map);
        my += getCentralMapY(map);
        if(map.LOADED && map.CELLS.length > 0){
        	for(int x = 0; x < map.CELLS.length; x++){
        		for(int y = 0; y < map.CELLS[x].length; y++){
        			drawImage(batch, Worldhandler.getClimateImage(map.CELLS[x][y].CLIMATE), map.getCellX(x, y) + mx, map.getCellY(x, y) + my, 1.0f, 0, false, Color.WHITE, 1.0f, false);
        			drawImage(batch, Worldhandler.getTerrainImage(map.CELLS[x][y].TERRAIN), map.getCellX(x, y) + mx, map.getCellY(x, y) + my, 1.0f, 0, false, Color.WHITE, 1.0f, false);
        			if(AAA_C.showGrid){
        				drawImage(batch, grid, map.getCellX(x, y) + mx, map.getCellY(x, y) + my, 1.0f, 0, false, Color.WHITE, 1.0f, false);
        			}
        			if(AAA_C.debug){
        				drawString(batch, x + "," + y, map.getCellX(x, y) + mx, map.getCellY(x, y) + my, com10, Color.RED, 1.0f);
        				drawString(batch, map.CELLS[x][y].CLIMATE + "", map.getCellX(x, y) + mx, map.getCellY(x, y) + my - 12, com10, Color.RED, 1.0f);
        			}
        		}
        	}
        }else{
            drawString(batch, "Map empty or not yet loaded.", -200, 50, com32, Color.RED, 1.0f);
        }
        drawString(batch, mx + ", " + my, getScreenX(), getScreenY(), com10, Color.RED, 1.0f);
    }
    /*
    public void drawPolygon(ShapeRenderer triangleBatch, Polygon pol[], int x, int y, Color col){
        triangleBatch.setColor(col);
        for(int i = 0; i < pol.length; i++){
            triangleBatch.triangle(pol[i].getVertices()[0] + x, pol[i].getVertices()[1] + y, pol[i].getVertices()[2] + x, pol[i].getVertices()[3] + y, pol[i].getVertices()[4] + x, pol[i].getVertices()[5] + y);
        }
    }
    
    public void drawPolygon(ShapeRenderer triangleBatch, float[] verts, int x, int y, Color col){
        triangleBatch.setColor(col);
        triangleBatch.triangle(verts[0] + x, verts[1] + y, verts[2] + x, verts[3] + y, verts[4] + x, verts[5] + y);
    }*/
    
    public int getCentralMapX(Map map){
        float x = - map.getWidth() / 2;
        return (int) x;
    }
    
    public int getCentralMapY(Map map){
        float y = map.getHeight() / 2;
        return (int) y;
    }
    
    public void drawContainers(SpriteBatch batch, ContainerType type){
    	Container[] CA = ConHand.getCons(type);
    	for(int i = CA.length - 1; i >= 0; i--){
    		drawContainer(batch, CA[i]);
    	}
    }
    
    public void drawContainer(SpriteBatch batch, Container con){
    	int x = con.getBox().x;
    	int y = con.getBox().y;
    	if(con.ACTIVE){
			if(con.MOVING){
				drawImage(batch, conBack, x - 1, y - 1, con.getBox().width + 2, con.getBox().height + 2, 0, false, Color.WHITE, con.TRANSPARENCY, false);
			}
    		if(con.BACKGROUND){
    			drawImage(batch, conFill, x, y, con.getBox().width, con.getBox().height, 0, false, Color.WHITE, con.TRANSPARENCY, false);
    		}
    		for(int i = 0; i < con.CONTENT.size(); i++){
    			drawContent(batch, con.CONTENT.get(i), x, y);
    		}
    		if(con.DECORATED){
    			drawImage(batch, conCon, x + con.conSurf.x, y + con.conSurf.y, con.conSurf.width, con.conSurf.height, 0, true, Color.WHITE, con.TRANSPARENCY, false);
        		drawString(batch, con.TITLE, x + con.conSurf.x, y + con.conSurf.y + con.conSurf.height - 12, com10, Color.RED, con.TRANSPARENCY);
        		drawActivator(batch, con.EXIT, x + con.EXIT.BOX.x, y + con.EXIT.BOX.y, false, com10, Color.RED, Color.WHITE, con.TRANSPARENCY);
    		}
    	}
    }
    
    public void drawActivator(SpriteBatch batch, Activator AC, int x, int y, boolean centered, BitmapFont font, Color col, Color tint, float opacity){
    	//If not centered: draw from upper left corner.
    	if(!centered){
        	x += AC.BOX.width / 2;
        	y += AC.BOX.height / 2;
    	}
    	if(AC.textured){
    		drawImage(batch, AC.TEX, x, y, AC.BOX.width, AC.BOX.height, 0, false, tint, opacity, true);
    	}else{
        	drawImage(batch, actBack, x, y, AC.BOX.width, AC.BOX.height, 0, false, tint, opacity, true);
    	}
    	drawString(batch, AC.title, x - AC.BOX.width / 4, y + (AC.BOX.height / 4), font, col, opacity);
    }
    
    public void drawContent(SpriteBatch batch, Content content, int x, int y){
    	switch(content.TYPE){
    		case MENU: drawMenu(batch, content, x, y, com10, false, false);
    		break;
		case DEFAULT:
			break;
		case MENUHOLDER:
			break;
    	}
    }
}
