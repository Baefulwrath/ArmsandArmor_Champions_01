
package com.rapplebob.ArmsAndArmorChampions;

import java.io.File;
import java.util.Scanner;

import javax.swing.JOptionPane;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Arms and Armor: Champions";
		cfg.useGL20 = false;
		cfg.width = 1280;
		cfg.height = 800;
		cfg.resizable = false;
		cfg.fullscreen = false;

		loadStartupSettings(cfg);
		
		new LwjglApplication(new AAA_C(), cfg);
	}
	
	public static boolean fullscreen = false;
	
	public static void loadStartupSettings(LwjglApplicationConfiguration cfg){
		try{
			Scanner reader = new Scanner(new File("STARTUPSETTINGS.txt"));
			cfg.title = reader.nextLine().substring(6);
			cfg.useGL20 = Boolean.parseBoolean(reader.nextLine().substring(8));
			cfg.width = Integer.parseInt(reader.nextLine().substring(6));
			cfg.height = Integer.parseInt(reader.nextLine().substring(7));
			cfg.resizable = Boolean.parseBoolean(reader.nextLine().substring(10));
			cfg.fullscreen = Boolean.parseBoolean(reader.nextLine().substring(11));
			reader.close();
		}catch(Exception ex){
			ex.printStackTrace(System.out);
			JOptionPane.showMessageDialog(null, "There seems to be some kind of disturbence at startup,\nMy guess is this has to do with the STARTUPSETTINGS.txt -file.\nFix that shit.", "ERROR", JOptionPane.WARNING_MESSAGE);
			System.exit(0);
		}
	}
}
