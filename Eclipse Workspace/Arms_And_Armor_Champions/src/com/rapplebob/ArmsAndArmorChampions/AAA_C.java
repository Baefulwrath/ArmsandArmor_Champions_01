package com.rapplebob.ArmsAndArmorChampions;

import java.awt.Rectangle;
import java.util.Scanner;

import menus.*;
import render.*;
import input.*;
import world.*;
import scripting.*;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;

import static com.badlogic.gdx.Gdx.*;

import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AAA_C implements ApplicationListener {
	private static OrthographicCamera camera;
	private static SpriteBatch batch;
    public static Renderer[] renderers = new Renderer[3];
    public static int activeRenderer = 0;
    public static int fps = 120;
    public static long lastRender = 0;
    public static boolean allowedToRender = true;
    public static State state = State.DEFAULT;
    public static State newState = State.DEFAULT;
    public static boolean debug = true;
    public static boolean gamePaused = false;
    public static float w = 1280;
    public static float h = 800;
    public static boolean showGrid = true;
    public static String settings = "";
    public static Rectangle screenRect = new Rectangle(0, 0, (int) w, (int) h);

    public static Main_Menuhandler MMH;
    public static Game_Menuhandler GMH;
    
    public static Inputhandler inputhandler;
    
    private Texture texture;
	private Sprite sprite;
    
	@Override
	public void create() {
		w = graphics.getWidth();
		h = graphics.getHeight();
		camera = new OrthographicCamera(1, h/w);
		camera.zoom = w;
		batch = new SpriteBatch();
		texture = new Texture(Gdx.files.internal("data/libgdx.png"));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		TextureRegion region = new TextureRegion(texture, 0, 0, 512, 275);
		
		sprite = new Sprite(region);
		sprite.setSize(sprite.getWidth(), sprite.getHeight());
		sprite.setScale(1);
		sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
		sprite.setPosition(0, 0);
		
        loadRenderers();
        setRendererByState(state);
        Worldhandler.load();
        inputhandler = new Inputhandler();
        input.setInputProcessor(inputhandler);
        MMH = new Main_Menuhandler();
        GMH = new Game_Menuhandler();
        getActiveMenuhandler().openMenuByID("main");
        
        loadSettings();
        setLoadedSettings();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

	@Override
	public void render() {
        updateRenderers();
        Scripthandler.update();
        updateMenuhandlers();
        screenRect = new Rectangle((int) (getActiveRenderer().getScreenX() * getZoomScale()), (int) (getActiveRenderer().getScreenY() * getZoomScale()), (int) (w * getZoom()), (int) (h * getZoom()));
        if(newState != state){
            changeState(newState);
        }
        if (readyToRender()) {
            doRender();
        }
	}

    public boolean readyToRender() {
        boolean ready = false;
        if (lastRender + getMilliFromFps(fps) <= System.currentTimeMillis() && allowedToRender) {
            ready = true;
            lastRender = System.currentTimeMillis();
        }
        return ready;
    }

    public int getMilliFromFps(int ifps) {
        int temp = 1000 / fps;
        return temp;
    }

    public void doRender() {
//Prepare screen
        graphics.getGL10().glClearColor(0, 0.05f, 0.05f, 1.0f);
        graphics.getGL10().glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_STENCIL_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        graphics.getGL10().glDisable(GL10.GL_CULL_FACE);
//Prepare rendering tools
        camera.position.set(camera.viewportWidth * .5f, camera.viewportHeight * .5f, 0f);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.setColor(1.0f, 1.0f, 1.0f, 1.0f);
//Begin work
        batch.begin();
//Get rendering orders
        //sprite.draw(batch);
        renderers[activeRenderer].render(batch);
//End cycle
        batch.end();
    }
    
	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

    public static void switchRendererByID(String ID){
        for(int i = 0; i < renderers.length; i++){
            if(renderers[i].ID.equals(ID)){
                setRendererByIndex(i);
                break;
            }
        }
        setRendererByIndex(0);
    }
    
    
    public static void updateRenderers(){
        for(int i = 0; i < renderers.length; i++){
            if(i == activeRenderer){
                renderers[i].active = true;
            }else{
                renderers[i].active = false;
            }
            renderers[i].generalUpdate();
        }
    }

    public static void loadRenderers() {
        try{
            renderers[0] = new Default_Renderer();
            renderers[1] = new Menu_Renderer();
            renderers[2] = new Game_Renderer();
            for(int i = 0; i < renderers.length; i++){
                renderers[i].loadResources();
                renderers[i].loadSpecificResources();
            }
        }catch(Exception ex){
            ex.printStackTrace(System.out);
            System.exit(0);
        }
    }
    
    public static int getRendererIDByState(State state){
        int r = 0;
        for(int i = 0; i < renderers.length; i++){
            if(state == renderers[i].state){
                r = i;
                break;
            }
        }
        return r;
    }
    
    public static void setRendererByIndex(int index){
        activeRenderer = index;
    }
    
    public static void setRendererByState(State state){
        setRendererByIndex(getRendererIDByState(state));
    }
    
    public static Menuhandler getActiveMenuhandler(){
        if(state == State.GAME){
            return GMH;
        }else{
            return MMH;
        }
    }
    
    public static Activator getActiveAct(){
        return getActiveMenuhandler().getActiveMenu().getActiveActivator();
    }
    
    public static Menu getActiveMenu(){
        return getActiveMenuhandler().getActiveMenu();
    }
    
    public static void updateMenuhandlers(){
        MMH.update();
        GMH.update();
    }
    
    public static void exit(){
        System.exit(0);
    }
    
    public static void changeState(State s){
        setRendererByState(s);
        state = newState;
        resetZoom();
    }
    
    public static Renderer getActiveRenderer(){
        return renderers[activeRenderer];
    }
    
    public static void setStage(){
        //Förbered inför ett spel med vald data.
    }
    
    public static void save(){
        //Spara data efter ett spel.
    }
    
    public static void loadSettings(){
    	try{
    		String temp = Gdx.files.internal("data/SETTINGS.txt").readString();
    		settings = temp;
    	}catch(Exception ex){
            ex.printStackTrace(System.out);
    	}
    }
    
    public static void setLoadedSettings(){
		try{
			Scanner reader = new Scanner(settings);
	    	newState = State.parseState(reader.nextLine().substring(9));
	    	debug = Boolean.parseBoolean(reader.nextLine().substring(6));
	    	showGrid = Boolean.parseBoolean(reader.nextLine().substring(9));
	    	reader.close();
		}catch(Exception ex){
			ex.printStackTrace(System.out);
	    }
    }
    
    public static void resetZoom(){
    	camera.zoom = w;
    }
    
    public static int zoomLimit = 0;
    public static int zoomSpeed = 50;
    
    public static void setZoomLimit(){
    	if(state == State.DEFAULT){
    		zoomLimit = (int) (w * 5);
    	}else if(state == State.MENU){
    		zoomLimit = (int) (w * 1.1);
    	}else if(state == State.GAME){
    		zoomLimit = (int) (w * 2);
    	}else{
    		zoomLimit = 0;
    	}
    }
    
    public static void zoomIn(){
    	if(camera.zoom - zoomSpeed > 500){
    		camera.zoom -= zoomSpeed;
    	}
    }
    
    public static void zoomOut(){
    	setZoomLimit();
    	if(camera.zoom + zoomSpeed < zoomLimit){
    		camera.zoom += zoomSpeed;
    	}
    }
    
    public static float getZoom(){
    	return camera.zoom;
    }
    
    public static float getZoomScale(){
    	return camera.zoom / w;
    }
}
