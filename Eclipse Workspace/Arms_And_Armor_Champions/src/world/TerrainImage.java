package world;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TerrainImage {
	public TextureRegion TEXTURE;
	public int TERRAIN;
	public void set(TextureRegion texture, int terrain){
		TEXTURE = texture;
		TERRAIN = terrain;
	}
}
