package input;

import java.awt.Rectangle;

import scripting.Scripthandler;
import world.Worldhandler;

import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputProcessor;
import com.rapplebob.ArmsAndArmorChampions.*;

import containers.ConHand;
import containers.ContainerType;
import static com.badlogic.gdx.Input.Keys.*;


public class Inputhandler implements InputProcessor {

    public Rectangle mouse = new Rectangle(0, 0, 0, 0);
    public Rectangle staticMouse = new Rectangle(0, 0, 0, 0);

    @Override
    public boolean keyDown(int keycode) {
        if (AAA_C.state == State.DEFAULT) {
            switch (keycode) {
                case ESCAPE:
                	AAA_C.exit();
                    break;
                case F1:
                	AAA_C.newState = State.MENU;
                    break;
            }
        } else if (AAA_C.state == State.MENU) {
            if (ConHand.getActiveMenu().thereAreHighlitActs()) {
                /*switch(keycode){
                    case ENTER:
                        Scripthandler.handleScript(Main.getActiveAct().script);
                        break;
                    case BACKSPACE:Main.getActiveAct().rmChar();
                        break;
                    case default:Main.getActiveAct().addChar();
                        break;
                }*/
            } else {
            	if(ConHand.collides(mouse, ContainerType.MAIN)){
            		System.out.println("fisk");
            	}else{
	                switch (keycode) {
	                    case UP:
	                    	ConHand.getActiveMenu().up();
	                        break;
	                    case DOWN:
	                    	ConHand.getActiveMenu().down();
	                        break;
	                    case ENTER:
	                        Scripthandler.handleScript(ConHand.getActiveAct().script);
	                        break;
	                    case NUM_1:
	                    	ConHand.getActiveMenu().activeAct = 0;
	                        break;
	                    case NUM_2:
	                    	ConHand.getActiveMenu().activeAct = 1;
	                        break;
	                    case NUM_3:
	                    	ConHand.getActiveMenu().activeAct = 2;
	                        break;
	                    case NUM_4:
	                    	ConHand.getActiveMenu().activeAct = 3;
	                        break;
	                    case NUM_5:
	                    	ConHand.getActiveMenu().activeAct = 4;
	                        break;
	                    case NUM_6:
	                    	ConHand.getActiveMenu().activeAct = 5;
	                        break;
	                    case NUM_7:
	                    	ConHand.getActiveMenu().activeAct = 6;
	                        break;
	                    case NUM_8:
	                    	ConHand.getActiveMenu().activeAct = 7;
	                        break;
	                    case NUM_9:
	                    	ConHand.getActiveMenu().activeAct = 8;
	                        break;
	                }
            	}
            }
        } else if (AAA_C.state == State.GAME) {
        	if(ConHand.collides(mouse, ContainerType.GAME)){
        	}else{
	            switch (keycode) {
	                case ESCAPE:
	                    if (AAA_C.gamePaused) {
	                    	AAA_C.gamePaused = false;
	                    } else {
	                    	AAA_C.gamePaused = true;
	                    };
	                    break;
	            }
	            if (AAA_C.gamePaused) {
	                switch (keycode) {
	                    case UP:
	                    	ConHand.getActiveMenuhandler().getActiveMenu().up();
	                        break;
	                    case DOWN:
	                    	ConHand.getActiveMenuhandler().getActiveMenu().down();
	                        break;
	                    case ENTER:
	                        Scripthandler.handleScript(ConHand.getActiveAct().script);
	                        break;
	                }
	            } else {
	                switch (keycode) {
	                	case UP:Worldhandler.getMap().y -= Worldhandler.mapSpeed;
	                	break;
	                	case DOWN:Worldhandler.getMap().y += Worldhandler.mapSpeed;
	                	break;
	                	case LEFT:Worldhandler.getMap().x += Worldhandler.mapSpeed;
	                	break;
	                	case RIGHT:Worldhandler.getMap().x -= Worldhandler.mapSpeed;
	                	break;
	                	case SPACE:if(AAA_C.showGrid){AAA_C.showGrid = false;}else{AAA_C.showGrid = true;};
	                	break;
	                	case TAB:if(AAA_C.debug){AAA_C.debug = false;}else{AAA_C.debug = true;};
	                	break;
	                }
	            }
        	}
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        if (AAA_C.state == State.DEFAULT) {
        } else if (AAA_C.state == State.MENU) {
        } else if (AAA_C.state == State.GAME) {
        }
        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
    	updateMouse(screenX, screenY);
        if (AAA_C.state == State.DEFAULT) {
            switch (button) {
        		case Buttons.MIDDLE:AAA_C.resetZoom();
            }
        } else if (AAA_C.state == State.MENU) {
        	if(ConHand.collides(mouse, ContainerType.MAIN)){
        		
        	}else{
	            switch (button) {
	                case Buttons.LEFT:
	                    Scripthandler.handleScript(ConHand.getActiveAct().script);
	                    break;
	            }
        	}
        } else if (AAA_C.state == State.GAME) {
            
        }
        switch(button){
        	case Buttons.MIDDLE:AAA_C.resetZoom();
        	break;
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
    	updateMouse(screenX, screenY);
    	ConHand.getActiveMenuhandler().getActiveMenu().testMouseHover(mouse, new Rectangle(ConHand.getActiveMenuhandler().X, ConHand.getActiveMenuhandler().Y, 0, 0), AAA_C.getActiveRenderer().com32);
        return true;
    }

    @Override
    public boolean scrolled(int amount) {
    	if(amount > 0){
        	AAA_C.zoomOut();
        }else{
        	AAA_C.zoomIn();
        }
        return true;
    }
    
    public void updateMouse(int screenX, int screenY){
        mouse = new Rectangle((int) ((screenX + AAA_C.getActiveRenderer().getScreenX()) * AAA_C.getZoomScale()), (int) ((-screenY - AAA_C.getActiveRenderer().getScreenY()) * AAA_C.getZoomScale()), 1, 1);
        staticMouse = new Rectangle(screenX, screenY, 1, 1);
    }
}
