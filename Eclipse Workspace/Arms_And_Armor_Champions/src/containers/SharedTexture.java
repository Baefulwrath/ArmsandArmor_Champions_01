package containers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SharedTexture {
	private Texture TEX;
	private TextureRegion REG;
	public String ID;
	private boolean asRegion = false;
	
	public SharedTexture(){
		
	}
	public SharedTexture(Texture t, String id){
		TEX = t;
		ID = id;
		asRegion = false;
	}
	public SharedTexture(TextureRegion r, String id){
		REG = r;
		ID = id;
		asRegion = true;
	}
	public TextureRegion getTex(){
		if(asRegion){
			return REG;
		}else{
			return new TextureRegion(TEX, TEX.getWidth(), TEX.getHeight());
		}
	}
}
