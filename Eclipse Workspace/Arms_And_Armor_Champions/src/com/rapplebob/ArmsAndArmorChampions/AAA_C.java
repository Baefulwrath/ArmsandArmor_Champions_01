package com.rapplebob.ArmsAndArmorChampions;

import java.io.File;
import java.util.Scanner;

import menus.*;
import render.*;
import input.*;
import world.*;
import scripting.*;

import com.badlogic.gdx.ApplicationListener;
import static com.badlogic.gdx.Gdx.*;
import static com.badlogic.gdx.graphics.GL10.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class AAA_C implements ApplicationListener {
	private OrthographicCamera camera;
	private SpriteBatch batch;
    public static ShapeRenderer shapeBatch;
    public static Renderer[] renderers = new Renderer[3];
    public static int activeRenderer = 0;
    public static int fps = 120;
    public static long lastRender = 0;
    public static boolean allowedToRender = true;
    public static State state = State.DEFAULT;
    public static State newState = State.DEFAULT;
    public static boolean debug = false;
    public static boolean gamePaused = false;
    public static int screenWidth = 1280;
    public static int screenHeight = 800;

    public static Main_Menuhandler MMH = new Main_Menuhandler();
    public static Game_Menuhandler GMH = new Game_Menuhandler();
    
    public static Inputhandler inputhandler;
    public static Worldhandler worldhandler;
    
	@Override
	public void create() {		
		screenWidth = graphics.getWidth();
		screenHeight = graphics.getHeight();
		camera = new OrthographicCamera(1, screenHeight/screenWidth);
		batch = new SpriteBatch();
        loadRenderers();
        //loadShaders();
        setRendererByState(state);
        worldhandler = new Worldhandler();
        worldhandler.load();
        inputhandler = new Inputhandler();
        input.setInputProcessor(inputhandler);
        getActiveMenuhandler().openMenuByID("main");
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
            doRender();
            lastRender = System.currentTimeMillis();
        }
        return ready;
    }

    public int getMilliFromFps(int ifps) {
        int temp = 1000 / fps;
        return temp;
    }

    public void doRender() {
        gl.glClearColor(0, 0, 0.1f, 1);
        gl.glClear(GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        shapeBatch.setProjectionMatrix(camera.combined);
        batch.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        shapeBatch.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        batch.begin();
        shapeBatch.begin(ShapeType.Triangle);
        renderers[activeRenderer].render(shapeBatch, batch);
        shapeBatch.end();
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
    
    public static String mainVertexShader = "";
    public static String mainFragmentShader = "";
    
    public static void loadShaders(){
        try{
            File file = new File("data/bin/main_vertex_shader.txt");
            Scanner reader = new Scanner(file);
            while(reader.hasNextLine()){
                mainVertexShader += reader.nextLine();
            }
            file = new File("data/bin/main_fragment_shader.txt");
            reader = new Scanner(file);
            while(reader.hasNextLine()){
                mainFragmentShader += reader.nextLine();
            }
        }catch(Exception ex){
            ex.printStackTrace(System.out);
        }
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
}
