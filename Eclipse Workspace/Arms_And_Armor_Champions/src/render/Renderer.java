package render;

import java.awt.Rectangle;
import java.util.ArrayList;

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
import containers.ConHand;
import containers.Container;
import containers.ContainerType;
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

    public void drawImage(SpriteBatch batch, Texture img, float x, float y, float scale, int rotation, boolean smooth, Color tint, float opacity) {
    	if(smooth){
        	img.setFilter(TextureFilter.Linear, TextureFilter.Linear);
    	}else{
        	img.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
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

    public void drawImage(SpriteBatch batch, Texture img, float x, float y, float width, float height, int rotation, boolean smooth, Color tint, float opacity) {
    	if(smooth){
        	img.setFilter(TextureFilter.Linear, TextureFilter.Linear);
    	}else{
        	img.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
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

    public void drawImage(SpriteBatch batch, TextureRegion img, float x, float y, float scale, int rotation, boolean smooth, Color tint, float opacity) {
    	if(smooth){
        	img.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
    	}else{
        	img.getTexture().setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
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

    public void drawImage(SpriteBatch batch, TextureRegion img, float x, float y, float width, float height, int rotation, boolean smooth, Color tint, float opacity) {
    	if(smooth){
        	img.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
    	}else{
        	img.getTexture().setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
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
    	if(onScreen(sprite)){
    		sprite.draw(batch);
    	}
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
        LabelStyle style = new LabelStyle(font, col);
        Label lab = new Label(string, style);
        lab.setPosition(x, y);
        lab.draw(batch, opacity);
    }

    public void drawMenu(SpriteBatch batch, Menu m, int x, int y) {
        drawString(batch, m.menuTitle, x - 24, y, com32_BI, Color.WHITE, 1.0f);
        drawString(batch, "______________________", x - 24, y - 8, com32, Color.WHITE, 1.0f);
        if (m.acts.size() > 0) {
            for (int i = 0; i < m.acts.size(); i++) {
                Activator a = m.acts.get(i);
                Color col = getActColor(a.AT);
                String print = a.title;
                if(AAA_C.debug){
                    print += " - " + a.script + " - " + a.AT.toString();
                }
                drawString(batch, print, x, y - 50 - (i * 40), com32, col, 1.0f);
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
                    drawString(batch, marker, x - 32, y - 50 - (i * 40), com32, Color.WHITE, 1.0f);
                }
            }
        } else {
            drawString(batch, "EMPTY MENU", x, y - 50, com32_BI, Color.WHITE, 1.0f);
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
        if(map.loaded && map.cells.length > 0){
        	for(int x = 0; x < map.cells.length; x++){
        		for(int y = 0; y < map.cells[x].length; y++){
        			drawImage(batch, Worldhandler.getClimateImage(map.cells[x][y].CLIMATE), map.getCellX(x, y) + mx, map.getCellY(x, y) + my, 1.0f, 0, false, Color.WHITE, 1.0f);
        			drawImage(batch, Worldhandler.getTerrainImage(map.cells[x][y].TERRAIN), map.getCellX(x, y) + mx, map.getCellY(x, y) + my, 1.0f, 0, false, Color.WHITE, 1.0f);
        			if(AAA_C.showGrid){
        				drawImage(batch, grid, map.getCellX(x, y) + mx, map.getCellY(x, y) + my, 1.0f, 0, false, Color.WHITE, 1.0f);
        			}
        			if(AAA_C.debug){
        				drawString(batch, x + "," + y, map.getCellX(x, y) + mx, map.getCellY(x, y) + my, com10, Color.RED, 1.0f);
        				drawString(batch, map.cells[x][y].CLIMATE + "", map.getCellX(x, y) + mx, map.getCellY(x, y) + my - 12, com10, Color.RED, 1.0f);
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
    	for(int i = 0; i < CA.length; i++){
    		drawContainer(batch, CA[i]);
    	}
    }
    
    public void drawContainer(SpriteBatch batch, Container con){
    	int botX = con.BOX.x;
    	int botY = con.BOX.y;
    	int topY = con.BOX.y + con.BOX.height;
    	int cenX = botX + (con.BOX.width / 2);
    	int cenY = botY + (con.BOX.height / 2);
    	int conX = cenX;
    	int conY = topY - (con.controlsurface.height / 2);
    	if(con.ACTIVE){
    		drawImage(batch, conBack, cenX, cenY, con.BOX.width + 2, con.BOX.height + 2, 0, false, Color.WHITE, ConHand.windowTransparency);
    		drawImage(batch, conFill, cenX, cenY, con.BOX.width, con.BOX.height, 0, false, Color.WHITE, ConHand.windowTransparency);
    		drawImage(batch, conCon, conX, conY, con.controlsurface.width, con.controlsurface.height, 0, true, Color.WHITE, ConHand.windowTransparency);
    		drawString(batch, con.TITLE, botX, topY - 12, com10, Color.RED, ConHand.windowTransparency);
    		drawActivator(batch, con.EXIT, botX + con.EXIT.BOX.x, topY, false, Color.RED, Color.WHITE, ConHand.windowTransparency);
    	}
    }
    
    public void drawActivator(SpriteBatch batch, Activator AC, int x, int y, boolean centered, Color col, Color tint, float opacity){
    	//If not centered: draw from upper left corner.
    	if(!centered){
        	x += AC.BOX.width / 2;
        	y -= AC.BOX.height / 2;
    	}
    	drawImage(batch, actBack, x, y, AC.BOX.width, AC.BOX.height, 0, false, tint, opacity);
    	drawString(batch, AC.title, x - AC.BOX.width / 4, y - AC.BOX.height / 4, com10, col, opacity);
    }
}
