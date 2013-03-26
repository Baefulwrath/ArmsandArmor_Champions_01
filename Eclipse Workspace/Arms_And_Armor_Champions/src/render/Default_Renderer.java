package render;

import world.Cell;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;
import com.rapplebob.ArmsAndArmorChampions.*;

public class Default_Renderer extends Renderer {
    
    public Texture testImg;
    
    public Default_Renderer(){
        ID = "DEFAULT";
        state = State.DEFAULT;
        polTestArray[0] = polTest;
        float[] verts2 = {100, 200, 100, 300, 0, 300};
        polTest = new Polygon(verts2);
        polTestArray[1] = polTest;
    }
    
    private int rot = 0;
    private float[] verts = {100, 100, 0, 100, 100, 200};
    private Polygon polTest = new Polygon(verts);
    private Polygon[] polTestArray = new Polygon[2];
    private Cell testCell = new Cell(64);
    
    public void render(ShapeRenderer shapeBatch, SpriteBatch batch) {
        if(rot < 360){
            rot++;
        }else{
            rot = 0;
        }
        batch.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        drawImage(batch, testImg, 0, 0, 20, rot);
        com10.draw(batch,  AAA_C.inputhandler.mouse.x + ", " + AAA_C.inputhandler.mouse.y, 0, -getScreenY() - 10);
        com64.draw(batch, "com64", 0, 0);
        com32.draw(batch, "com32", 0, 32);
        com16.draw(batch, "com16", 0, 64);
        com10.draw(batch, "com10", 0, 86);
        com32_BI.draw(batch, "com32_BI", -256, 32);
        com16_BI.draw(batch, "com16_BI", -256, 64);
        com10_BI.draw(batch, "com10_BI", -256, 86);
        drawString(batch, "ESC to quit, F1 to return to menus.", getScreenX(), -getScreenY() - 10, com10, Color.RED);
        drawString(batch, "DEFAULT RENDERER", getScreenX(), getScreenY(), com16, Color.RED);
        drawString(batch, "+", (int) AAA_C.inputhandler.mouse.x, (int) AAA_C.inputhandler.mouse.y, com16, Color.CYAN);
        drawPolygon(shapeBatch, polTestArray, 400, 0, Color.YELLOW);
        drawPolygon(shapeBatch, testCell.getPolygons(), -500, 200, Color.YELLOW);
    }

    @Override
    public void specificUpdate() {
    }

    @Override
    public void loadResources() throws Exception {
        testImg = new Texture(Gdx.files.internal("testImg.png"));
    }
}
