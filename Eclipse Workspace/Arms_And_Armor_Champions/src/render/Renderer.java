package render;

import world.Map;
import world.Worldhandler;
import menus.Activator;
import menus.ActivatorType;
import menus.Menu;

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

public abstract class Renderer {

    public Renderer() {
        loadFonts();
    }
    public String ID = "DEFAULT";
    public State state = null;
    public Sprite sprite = new Sprite();
    public Texture idleCell;
    public Texture markedCell;
    public Texture background;

    public abstract void render(SpriteBatch batch);

    public abstract void specificUpdate();

    public void generalUpdate() {
    	
    	
    	
    	if(active){
    		specificUpdate();
    	}
    }
    public boolean active = false;

    public abstract void loadSpecificResources() throws Exception;
    
    public void loadResources(){
    	idleCell = new Texture(Gdx.files.internal("data/images/idleCell.png"));
    	markedCell = new Texture(Gdx.files.internal("data/images/markedCell.png"));
    	background = new Texture(Gdx.files.internal("data/images/defaultBackground.png"));
    }

    public void drawImage(SpriteBatch batch, Texture img, int x, int y, float scale, int rotation, boolean smooth) {
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
    	sprite.draw(batch);
    }

    public void drawImage(SpriteBatch batch, Texture img, int x, int y, int width, int height, int rotation, boolean smooth) {
    	if(smooth){
        	img.setFilter(TextureFilter.Linear, TextureFilter.Linear);
    	}else{
        	img.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
    	}
    	img.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
    	sprite = new Sprite(img);
    	sprite.setSize(1, 1);
    	sprite.setScale(width, height);
    	sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
    	sprite.setPosition(x - (sprite.getWidth() / 2), y - (sprite.getHeight() / 2));
    	sprite.setRotation(rotation);
    	sprite.draw(batch);
    }

    public void drawImage(SpriteBatch batch, TextureRegion img, int x, int y, float scale, int rotation, boolean smooth) {
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
    	sprite.draw(batch);
    }

    public void drawImage(SpriteBatch batch, TextureRegion img, int x, int y, int width, int height, int rotation, boolean smooth) {
    	if(smooth){
        	img.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
    	}else{
        	img.getTexture().setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
    	}
    	img.getTexture().setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
    	sprite = new Sprite(img);
    	sprite.setSize(1, 1);
    	sprite.setScale(width, height);
    	sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
    	sprite.setPosition(x - (sprite.getWidth() / 2), y - (sprite.getHeight() / 2));
    	sprite.setRotation(rotation);
    	sprite.draw(batch);
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
    public BitmapFont com64;
    public BitmapFont com32;
    public BitmapFont com16;
    public BitmapFont com10;
    public BitmapFont com32_BI;
    public BitmapFont com16_BI;
    public BitmapFont com10_BI;

    public float getScreenX() {
        return -(AAA_C.w / 2);
    }

    public float getScreenY() {
        return -(AAA_C.h / 2);
    }

    public void drawString(SpriteBatch batch, String string, int x, int y, BitmapFont font, Color col) {
        LabelStyle style = new LabelStyle(font, col);
        Label lab = new Label(string, style);
        lab.setPosition(x, y);
        lab.draw(batch, 1);
    }

    public void drawMenu(SpriteBatch batch, Menu m, int x, int y) {
        drawString(batch, m.title, x - 24, y, com32_BI, Color.WHITE);
        drawString(batch, "______________________", x - 24, y - 8, com32, Color.WHITE);
        if (m.acts.size() > 0) {
            for (int i = 0; i < m.acts.size(); i++) {
                Activator a = m.acts.get(i);
                Color col = getActColor(a.AT);
                String print = a.title;
                if(AAA_C.debug){
                    print += " - " + a.script + " - " + a.AT.toString();
                }
                drawString(batch, print, x, y - 50 - (i * 40), com32, col);
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
                    drawString(batch, marker, x - 32, y - 50 - (i * 40), com32, Color.WHITE);
                }
            }
        } else {
            drawString(batch, "EMPTY MENU", x, y - 50, com32_BI, Color.WHITE);
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
        			drawImage(batch, Worldhandler.getCellImage(map.cells[x][y].TERRAIN), (int) map.getCellX(x, y), (int) map.getCellY(x, y), 1.0f, 0, false);
        		}
        	}
        }else{
            drawString(batch, "Map empty or not yet loaded.", -200, 50, com32, Color.RED);
        }
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
        float x = -(map.cells[0][0].SIDE * (map.width / 2));
        x += map.cells[0][0].RADIUS;
        return (int) x;
    }
    
    public int getCentralMapY(Map map){
        float y = ((map.height / 2) + 1) * map.cells[0][0].DIAMETER;
        return (int) y;
    }
}
