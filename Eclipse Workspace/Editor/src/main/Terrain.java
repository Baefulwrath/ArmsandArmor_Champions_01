package main;

public class Terrain {
	public String TERRAIN = "DEFAULT";
	public String CLIMATE = "DEFAULT";
	public String TITLE = "VOID";
	public int ID = 0;
	public Terrain(String terrain, String climate, String title, int id){
		TERRAIN = terrain;
		CLIMATE = climate;
		TITLE = title;
		ID = id;
	}
}
