package scripting;

import java.io.BufferedReader;
import java.io.InputStreamReader;


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

public class Scripthandler {

    //Spara pointers till alla variabler man kan �ndra p� i ett hashtable.
    //L�t en metod st�lla in dessa variabler ifall variabeln finns i listan och se att v�rdet blir av korrekt typ.
    private static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    private static BufferedReader reader = new BufferedReader(inputStreamReader);

    public static void update() {
        try {
            if (reader.ready()) {
                handleScript(reader.readLine());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void handleScript(String script) {
        // Ta bort kommentarer, mellanrum o.s.v
        script = cleanupScript(script);
        // Kolla efter metoder s�som GET_ och fyll i variabler.
        script = fillScript(script);
        // Loopa igenom olika kommandon och utf�r dem.
        while (script.contains("#")) {
            activateScript(script);
            script = script.substring(script.indexOf("#") + 1);
        }
        activateScript(script);

        System.out.println(script);
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
            //H�mta en lokal variabel s�som titeln p� den activator som kommandot skickades fr�n.
            if(id.substring(0, 15).equals("activatorTitle_")){
                id = id.substring(15);
                value = getActivatorTitleById(id);
            }
        }else{
            value += "[GEN]";
            //H�mta en generell variabel som finns i hashtablet.
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
    }
    
    public static String getActivatorTitleById(String id){
        String title = "";
        String script = "";
        Menu m = ConHand.getActiveMenu();
        boolean found = false;
        for(int i = 0; i < m.acts.size(); i++){
            if(m.acts.get(i).script.length() > 3){
                //id:t m�ste vara det f�rsta kommandot som anges av activatorn.
                if(m.acts.get(i).script.contains("#")){
                    script = m.acts.get(i).script.substring(0, m.acts.get(i).script.indexOf("#"));
                }else{
                    script = m.acts.get(i).script;
                }
                if(script.substring(0, 3).equals("id_")){
                    title = m.acts.get(i).title;
                    found = true;
                    break;
                }
            }
        }
        if(!found){
        	for(int ci = 0; ci < ConHand.cons.length; ci++){
        		Container C = ConHand.cons[ci];
            	for(int i = 0; i < C.CONTENT.size(); i++){
            		Content CT = C.CONTENT.get(i);
            		if(CT.TYPE == ContentType.MENU){
            			///////////////////////////////////////////////////////////////////////////////////////////////////////////
            		}
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
    	Activator A = ConHand.getActiveContainer().getActById(cmd);
    	A.TEX = ;
    }
}
