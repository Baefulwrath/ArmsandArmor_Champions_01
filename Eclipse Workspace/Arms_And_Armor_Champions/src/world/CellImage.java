package world;

import com.badlogic.gdx.graphics.Texture;

public class CellImage {
	public Texture TEXTURE;
	public Terrain TERRAIN;
	public void set(Texture texture, Terrain terrain){
		TEXTURE = texture;
		TERRAIN = terrain;
	}
}
