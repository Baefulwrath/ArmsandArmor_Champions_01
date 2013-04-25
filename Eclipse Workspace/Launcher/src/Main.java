import java.awt.Desktop;
import java.awt.Rectangle;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Main {
	
	public static JFrame frame = new JFrame();
	public static Screen scr = new Screen();
	
	public static Rectangle mouse = new Rectangle(0, 0, 0, 0);
	public static MouseHandler MH = new MouseHandler();
	
	public static ArrayList<Button> buttons = new ArrayList<Button>();
	
	public static boolean admin = false;
	
	public static void main(String[] args) {
		init();
		run();
	}
	
	public static void init(){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(640, 480);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Arms and Armor Launcher");
		frame.setVisible(true);
		frame.setResizable(false);
		frame.add(scr);
		frame.addMouseListener(MH);
		loadStartupFile();
	}
	
	public static void updateButtons(){
		buttons.clear();
		buttons.add(new Button("Title: " + title, "title", 150, 20, 30));
		buttons.add(new Button("Use GL20: " + useGL20, "useGL20", 150, 20, 50));
		buttons.add(new Button("Screen Width: " + width, "width", 150, 20, 70));
		
		buttons.add(new Button("Screen Height: " + height, "height", 150, scr.getWidth() - 170, 30));
		buttons.add(new Button("Screen Resizable: " + resizable, "resizable", 150, scr.getWidth() - 170, 50));
		buttons.add(new Button("Screen Fullscreen: " + fullscreen, "fullscreen", 150, scr.getWidth() - 170, 70));
		
		buttons.add(new Button("Save", "save", 150, (scr.getWidth() / 2) - (150 / 2), 250));
		buttons.add(new Button("Load", "load", 150, (scr.getWidth() / 2) - (150 / 2), 270));
		
		buttons.add(new Button("Launch Game!", "launch", 150, (scr.getWidth() / 2) - (150 / 2), scr.getHeight() - 90));
		buttons.add(new Button("Admin: " + admin, "testAdmin", 150, (scr.getWidth() / 2) - (150 / 2), scr.getHeight() - 50));
	}
	
	public static void run(){
		while(true){
			try{
				Thread.sleep(5);
				update();
			}catch(Exception ex){
				ex.printStackTrace(System.out);
			}
		}
	}
	
	public static void update() throws Exception{
		try{
			updateButtons();
			mouse = new Rectangle(scr.getMousePosition().x, scr.getMousePosition().y, 1, 1);
		}catch(Exception ex){
			Rectangle mouse = new Rectangle(0, 0, 0, 0);
		}
		for(int i = 0; i < buttons.size(); i++){
			if(mouse.intersects(buttons.get(i).BOX)){
				buttons.get(i).hover = true;
			}else{
				buttons.get(i).hover = false;
			}
		}
		scr.repaint();
	}
	
    public static String title = "";
    public static int width = 0;
    public static int height = 0;
    public static boolean useGL20 = false;
    public static boolean resizable = false;
    public static boolean fullscreen = false;
	
    public static void loadStartupFile() {
        try {
            Scanner reader = new Scanner(new File("STARTUPSETTINGS.txt"));
            title = reader.nextLine().substring(6);
            useGL20 = Boolean.parseBoolean(reader.nextLine().substring(8));
            width = Integer.parseInt(reader.nextLine().substring(6));
            height = Integer.parseInt(reader.nextLine().substring(7));
            resizable = Boolean.parseBoolean(reader.nextLine().substring(10));
            fullscreen = Boolean.parseBoolean(reader.nextLine().substring(11));
            reader.close();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
            JOptionPane.showMessageDialog(frame, "Shit fucked up, \nMy guess is this has to do with the STARTUPSETTINGS.txt -file.\nFix that shit.", "ERROR", JOptionPane.WARNING_MESSAGE);
            System.exit(0);
        }
    }
    
    public static void saveStartupFile(){
        try {
                FileWriter fstream = new FileWriter("STARTUPSETTINGS.txt");
                BufferedWriter out = new BufferedWriter(fstream);
                out.write("title " + title + System.getProperty("line.separator"));
                out.write("useGL20 " + useGL20 + System.getProperty("line.separator"));
                out.write("width " + width + System.getProperty("line.separator"));
                out.write("height " + height + System.getProperty("line.separator"));
                out.write("resizable " + resizable + System.getProperty("line.separator"));
                out.write("fullscreen " + fullscreen);
                out.close();
                JOptionPane.showMessageDialog(frame, "Settings saved!", "Saved", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
            JOptionPane.showMessageDialog(frame, "ERROR", "ACTUALLY IT'S EXCEPTION IN JAVA... (jus' sayin')", JOptionPane.WARNING_MESSAGE);
            System.exit(0);
        }
    }
    
    public static Button getActiveButton(){
    	Button B = new Button("", "", 0, -100, -100);
    	for(int i = 0; i < buttons.size(); i++){
    		if(buttons.get(i).hover){
    			B = buttons.get(i);
    			break;
    		}
    	}
    	return B;
    }
    
    public static void click(){
    	if(!MH.justClicked){
    		CmdHandler.activate(getActiveButton().CMD);
    	}
    }

    public static boolean switchBoolean(boolean b, boolean safe) {
        if (safe) {
    		if(admin){
	            if (JOptionPane.showConfirmDialog(frame, "This is a safe value, are you sure you want to switch it?", "Safe value!", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
	                if (b) {
	                    b = false;
	                } else {
	                    b = true;
	                }
	            }
	        }else{
	        	JOptionPane.showMessageDialog(frame, "Admins only.", "Admin", JOptionPane.ERROR_MESSAGE);
	        }
        } else {
            if (b) {
                b = false;
            } else {
                b = true;
            }
        }
        return b;
    }

    public static String switchString(String old, boolean safe) {
    	String n;
    	if(safe){
    		if(admin){
	            if (JOptionPane.showConfirmDialog(frame, "This is a safe value, are you sure you want to switch it?", "Safe value!", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
	            	n = JOptionPane.showInputDialog(frame, "Switch String", "Switch String");
	            }else{
	            	n = old;
	            }
    		}else{
        		n = old;
	        	JOptionPane.showMessageDialog(frame, "Admins only.", "Admin", JOptionPane.ERROR_MESSAGE);
        	}
    	}else{
    		n = old;
    	}
        return n;
    }

    public static int switchInt(int old, boolean safe) {
    	int n;
    	if(safe){
    		if(admin){
	            if (JOptionPane.showConfirmDialog(frame, "This is a safe value, are you sure you want to switch it?", "Safe value!", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
	            	n = Integer.parseInt(JOptionPane.showInputDialog(frame, "Switch integer", "Switch integer"));
	            }else{
	            	n = old;
	            }
    		}else{
        		n = old;
	        	JOptionPane.showMessageDialog(frame, "Admins only.", "Admin", JOptionPane.ERROR_MESSAGE);
        	}
    	}else{
    		n = old;
    	}
        return n;
    }
    
    public static void testAdmin(){
    	if(!admin){
	    	if("filur".equals(JOptionPane.showInputDialog(frame, "Password...", "Password"))){
	    		admin = true;
	    		JOptionPane.showMessageDialog(frame, "Admin mode activated", "Godmode activated.", JOptionPane.INFORMATION_MESSAGE);
	    	}
    	}
    }

    public static void launch(){
    	try{
    		Desktop D = Desktop.getDesktop();
	    	File file = new File("Engine.jar");
	    	if(!file.exists()){
	    		file = new File("Engine.exe");
	    	}
	    	if(file.exists()){
	        	D.open(file);
	    	}
    	}catch(Exception ex){
    		ex.printStackTrace(System.out);
    	}
    	System.exit(0);
    }
    
    public static void exit(){
    	System.exit(0);
    }
}
