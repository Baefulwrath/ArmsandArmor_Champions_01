package com.rapplebob.ArmsAndArmorChampions;

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
		cfg.samples = 16;
		
		new LwjglApplication(new AAA_C(), cfg);
	}
}
