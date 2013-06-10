package scripting;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import world.Climate;
import world.Worldhandler;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rapplebob.ArmsAndArmorChampions.AAA_C;
import com.rapplebob.ArmsAndArmorChampions.State;

import static com.rapplebob.ArmsAndArmorChampions.AAA_C.*;
import com.rapplebob.ArmsAndArmorChampions.*;

import containers.Activator;
import containers.ConHand;
import containers.Container;
import containers.Content;
import containers.ContentType;
import containers.Menu;
import editor.Editorhandler;

public class Scripthandler {

    //Spara pointers till alla variabler man kan ändra på i ett hashtable.
    //Låt en metod ställa in dessa variabler ifall variabeln finns i listan och se att värdet blir av korrekt typ.
    private static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    private static BufferedReader reader = new BufferedReader(inputStreamReader);
    private static boolean initialized = false;
    private static HashMap<String, Integer> genInts = new HashMap<String, Integer>();
    
    public static void initialize(){
    	loadGenericVariables();
    	initialized = true;
    }
    
    public static void update() {
        try {
            if (reader.ready()) {
                handleScript(reader.readLine());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void loadGenericVariables(){
    	genInts.put("BRUSHCLIMATE", Editorhandler.brush.CELL.CLIMATE);
    	genInts.put("BRUSHTERRAIN", Editorhandler.brush.CELL.TERRAIN);
    }

    public static void handleScript(String script) {
        // Ta bort kommentarer, mellanrum o.s.v
        script = cleanupScript(script);
        // Kolla efter metoder såsom GET_ och fyll i variabler.
        script = fillScript(script);
        // Loopa igenom olika kommandon och utför dem.
        while (script.contains("#")) {
            activateScript(script);
            script = script.substring(script.indexOf("#") + 1);
        }
        activateScript(script);

        //System.out.println(script);
    }

    public static void activateScript(String script) {
        if (script.length() > 0) {
            if (script.length() > 1) {
                if (script.contains("#")) {
                    readLine(script.substring(0, script.indexOf("#")));
                } else {
                    readLine(script);
                }
            } else {
                if (!script.contains("#")) {
                    readLine(script);
                }
            }
        }
    }

    public static String cleanupScript(String script) {
        while (script.contains(" ")) {
            script = script.substring(0, script.indexOf(" ")) + script.substring(script.indexOf(" ") + 1);
        }
        return script;
    }

    public static String fillScript(String script) {
        while (script.contains("GET_")) {
            script = script.substring(0, script.indexOf("GET_")) + getVar(script.substring(script.indexOf("GET_") + 4, script.indexOf("_TEG"))) + script.substring(script.indexOf("_TEG") + 4);
        }
        return script;
    }

    public static String getVar(String id) {
        //Skriv kodfanskapet.
        String value = "[GET-ERROR]";
        if(id.substring(0, 4).equals("loc_")){
            value += "[LOC]";
            id = id.substring(4);
            //Hämta en lokal variabel såsom titeln på den activator som kommandot skickades från.
            if(id.substring(0, 15).equals("activatorTitle_")){
                id = id.substring(15);
                value = getActivatorTitleById(id);
            }else if(id.substring(0, 12).equals("activatorId_")){
                id = id.substring(12);
                value = getActivatorIdByTitle(id);
            }
        }else{
    		if(genInts.containsKey(id)){
    			value = genInts.get(id).toString();
    		}
        }
        return value;
    }

    public static void readLine(String line) {
        String cmd = line.substring(0, line.indexOf("_") + 1);
        if (line.length() == 5) {
            if (cmd.equals("exit_")) {
                exit();
            }
        }
        if (line.length() == 5) {
            if (cmd.equals("test_")) {
                System.out.println("Test successful!");
            }
        }
        if (line.length() > 9) {
            if (cmd.equals("openMenu_")) {
                openMenu(line);
            }
        }
        if (line.length() > 6) {
            if (cmd.equals("setAA_")) {
            	ConHand.getActiveMenu().activeAct = Integer.parseInt(line.substring(6));
            }
        }
        if (line.length() == 9) {
            if (cmd.equals("activate_")) {
                handleScript(ConHand.getActiveAct().script);
            }
        }
        if (line.length() > 6) {
            if (cmd.equals("print_")) {
                System.out.println(line.substring(6));
            }
        }
        if (line.length() == 10) {
            if (cmd.equals("startgame_")) {
                setStage();
                newState = State.GAME;
            }
        }
        if (line.length() == 8) {
            if (cmd.equals("endgame_")) {
                save();
                newState = State.MENU;
            }
        }
        if (line.length() == 9) {
            if (cmd.equals("continue_")) {
                gamePaused = false;
            }
        }
        if (line.length() == 14) {
            if (cmd.equals("unpauseEditor_")) {
                editorPaused = false;
            }
        }
        if (line.length() > 9) {
            if (cmd.equals("setState_")) {
                setState(line.substring(9));
            }
        }
        if (line.length() > 10) {
            if (cmd.equals("switchCon_")) {
            	switchContainer(line.substring(10));
            }
        }
        if (line.length() > 12) {
            if (cmd.equals("setActImage_")) {
            	setActImage(line.substring(12));
            }
        }
        if (line.length() > 12) {
            if (cmd.equals("setActTitle_")) {
            	setActTitle(line.substring(12));
            }
        }
    }
    
    public static String getActivatorIdByTitle(String title){
        String id = "";
        String script = "";
        Menu m = ConHand.getActiveMenu();
        boolean found = false;
        for(int i = 0; i < m.acts.size(); i++){
        	if(m.acts.get(i).title.equals(title)){
        		id = m.acts.get(i).ID;
        		found = true;
        		break;
        	}
        }
        if(!found){
        	for(Map.Entry<String, Container> entry : ConHand.cons.entrySet()){
        		Container C = ConHand.cons.get(entry.getKey());
            	for(int i = 0; i < C.CONTENT.size(); i++){
            		Content CT = C.CONTENT.get(i);
            		if(CT.TYPE == ContentType.MENU){
            			m = (Menu) CT;
            			for(int mi = 0; mi < m.acts.size(); mi++){
            	        	if(m.acts.get(i).title.equals(title)){
            	        		id = m.acts.get(i).ID;
            	        		found = true;
            	        		break;
            	        	}
            			}
            			if(found){
            				break;
            			}
            		}
            	}
    			if(found){
    				break;
    			}
        	}
        }
        return title;
    }
    
    public static String getActivatorTitleById(String id){
        String title = "";
        String script = "";
        Menu m = ConHand.getActiveMenu();
        boolean found = false;
        for(int i = 0; i < m.acts.size(); i++){
        	if(m.acts.get(i).ID.equals(id)){
        		title = m.acts.get(i).title;
        		found = true;
        		break;
        	}
        }
        if(!found){
        	for(Map.Entry<String, Container> entry : ConHand.cons.entrySet()){
        		Container C = ConHand.cons.get(entry.getKey());
            	for(int i = 0; i < C.CONTENT.size(); i++){
            		Content CT = C.CONTENT.get(i);
            		if(CT.TYPE == ContentType.MENU){
            			m = (Menu) CT;
            			for(int mi = 0; mi < m.acts.size(); mi++){
            	        	if(m.acts.get(i).ID.equals(id)){
            	        		title = m.acts.get(i).title;
            	        		found = true;
            	        		break;
            	        	}
            			}
            			if(found){
            				break;
            			}
            		}
            	}
    			if(found){
    				break;
    			}
        	}
        }
        return title;
    }
    
    public static void setState(String state){
    	State temp = State.parseState(state);
    	AAA_C.newState = State.parseState(state);
    	System.out.println(state + " - " + temp.toString());
    }

    public static void openMenu(String cmd) {
        String id = cmd.substring(cmd.indexOf("_") + 1);
        ConHand.getActiveMenuholder().openMenuByID(id);
    }
    
    public static void switchContainer(String ID){
    	if(ConHand.getContainer(ID).ACTIVE){
    		ConHand.getContainer(ID).ACTIVE = false;
    	}else{
    		ConHand.getContainer(ID).ACTIVE = true;
    	}
    }

    public static void setActImage(String cmd){
    	Activator A = ConHand.getActById(cmd.substring(0, cmd.indexOf("_")));
    	cmd = cmd.substring(cmd.indexOf("_") + 1);
    	String type = cmd.substring(0, cmd.indexOf("_"));
    	cmd = cmd.substring(cmd.indexOf("_") + 1);
    	boolean useTex = false;
    	Texture tex = AAA_C.getActiveRenderer().actBack;
    	TextureRegion reg = new TextureRegion();
    	switch(type){
    			case "Climate":reg = Worldhandler.getClimateImage(Worldhandler.getClimateIdByString(cmd));
    			useTex = false;
    		break;
    			case "Terrain":reg = Worldhandler.getTerrainImage(Worldhandler.getTerrainIdByString(cmd));
    			useTex = false;
    		break;
    			case "Image":reg = ConHand.getSharedTexture(cmd);
    			useTex = false;
    		break;
    	}
    	if(useTex){
    		A.TEX = new TextureRegion(tex, tex.getWidth(), tex.getHeight());
    	}else{
    		A.TEX = reg;
    	}
    }
    
    //setActTitle_EDICON01_Climate_GET_BRUSHCLIMATE_TEG
    public static void setActTitle(String cmd){
    	String aid = cmd.substring(0, cmd.indexOf("_"));
    	cmd = cmd.substring(cmd.indexOf("_") + 1);
    	String type = cmd.substring(0, cmd.indexOf("_"));
    	cmd = cmd.substring(cmd.indexOf("_") + 1);
    	String title = cmd;
    	switch(type){
	    	case "Climate":
	    		title = Worldhandler.climates.get(Integer.parseInt(cmd)).CLIMATE;
	    		break;
	    	case "Terrain":
	    		title = Worldhandler.terrains.get(Integer.parseInt(cmd)).TERRAIN;
	    		break;
    	}
    	String cid = ConHand.getContainerByAct(aid).ID;
    	for(Map.Entry<String, Content> entry : ConHand.cons.get(cid).CONTENT.entrySet()){
    		Menu m = (Menu) ConHand.cons.get(cid).CONTENT.get(entry.getKey());
    		if(m.TYPE == ContentType.MENU){
    			if(m.acts.containsKey(aid)){
    				ConHand.cons.get(cid).CONTENT.get(entry.getKey()). = cmd;
    			}
    		}
    	}
    }
    
}
