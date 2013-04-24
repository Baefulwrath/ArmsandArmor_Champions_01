package com.rapplebob.ArmsAndArmorChampions;

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
		
		int input = JOptionPane.showConfirmDialog(null, "Go fullscreen?", "Fullscreen?", JOptionPane.YES_NO_CANCEL_OPTION);
		if(input == JOptionPane.YES_OPTION){
			cfg.fullscreen = true;
		}else{
			cfg.fullscreen = false;
		}
		
		new LwjglApplication(new AAA_C(), cfg);
	}
}
