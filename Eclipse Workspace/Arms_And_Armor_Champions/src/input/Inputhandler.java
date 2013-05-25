package input;

import java.awt.Rectangle;

import scripting.Scripthandler;
import world.Worldhandler;

import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputProcessor;
import com.rapplebob.ArmsAndArmorChampions.*;

import containers.ConHand;
import containers.ContainerType;
import editor.Editorhandler;
import static com.badlogic.gdx.Input.Keys.*;


public class Inputhandler implements InputProcessor {

    public static Rectangle mouse = new Rectangle(0, 0, 0, 0);
    public static Rectangle staticMouse = new Rectangle(0, 0, 0, 0);
    public int lastMouseX = 0;
    public int lastMouseY = 0;
    
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
        } else if (AAA_C.state == State.GAME) {
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
	                    	ConHand.getActiveMenuholder().getActiveMenu().up();
	                        break;
	                    case DOWN:
	                    	ConHand.getActiveMenuholder().getActiveMenu().down();
	                        break;
	                    case ENTER:
	                        Scripthandler.handleScript(ConHand.getActiveAct().script);
	                        break;
	                }
	            } else {
	                switch (keycode) {
	            		case UP:Worldhandler.moveDown = true;
	            		break;
	            		case DOWN:Worldhandler.moveUp = true;
	            		break;
	            		case LEFT:Worldhandler.moveRight = true;
	            		break;
	            		case RIGHT:Worldhandler.moveLeft = true;
	            		break;
	                	case SPACE:if(AAA_C.showGrid){AAA_C.showGrid = false;}else{AAA_C.showGrid = true;};
	                	break;
	                	case TAB:if(AAA_C.debug){AAA_C.debug = false;}else{AAA_C.debug = true;};
	                	break;
	                }
	            }
        } else if (AAA_C.state == State.EDITOR) {
            switch (keycode) {
            case ESCAPE:
                if (AAA_C.editorPaused) {
                	AAA_C.editorPaused = false;
                } else {
                	AAA_C.editorPaused = true;
                };
                break;
            }
            if (AAA_C.editorPaused) {
                switch (keycode) {
                    case UP:
                    	ConHand.getActiveMenuholder().getActiveMenu().up();
                        break;
                    case DOWN:
                    	ConHand.getActiveMenuholder().getActiveMenu().down();
                        break;
                    case ENTER:
                        Scripthandler.handleScript(ConHand.getActiveAct().script);
                        break;
                }
            } else {
                switch (keycode) {
            		case UP:Editorhandler.moveDown = true;
            		break;
            		case DOWN:Editorhandler.moveUp = true;
            		break;
            		case LEFT:Editorhandler.moveRight = true;
            		break;
            		case RIGHT:Editorhandler.moveLeft = true;
            		break;
            		case SPACE:if(AAA_C.showGrid){AAA_C.showGrid = false;}else{AAA_C.showGrid = true;};
            		break;
            		case TAB:if(AAA_C.debug){AAA_C.debug = false;}else{AAA_C.debug = true;};
            		break;
                }
            }
        }
        switch (keycode) {
        	case Z:AAA_C.resetActualZoom();
        	break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
    	switch(AAA_C.state){
    		case EDITOR:
                switch (keycode) {
            		case UP:Editorhandler.moveDown = false;
            		break;
            		case DOWN:Editorhandler.moveUp = false;
            		break;
            		case LEFT:Editorhandler.moveRight = false;
            		break;
            		case RIGHT:Editorhandler.moveLeft = false;
            		break;
                }
    		break;
    		case GAME:
                switch (keycode) {
            		case UP:Worldhandler.moveDown = false;
            		break;
            		case DOWN:Worldhandler.moveUp = false;
            		break;
            		case LEFT:Worldhandler.moveRight = false;
            		break;
            		case RIGHT:Worldhandler.moveLeft = false;
            		break;
                }
    		break;
    	}
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
    	if(ConHand.collides(staticMouse, AAA_C.getCTypeFromState(AAA_C.state))){
    		touchDown_Container(button);
        }else{
	        if (AAA_C.state == State.DEFAULT) {
	            switch (button) {
	        		case Buttons.MIDDLE:AAA_C.resetZoom();
	            }
	        } else if (AAA_C.state == State.MENU) {
	        	switch (button) {
		            case Buttons.LEFT:activateMenu();
		                break;
		        }
	        } else if (AAA_C.state == State.GAME) {
	        	if(AAA_C.gamePaused){
			        switch (button) {
		                case Buttons.LEFT:activateMenu();
		                	break;
			        }
	        	}
	        } else if (AAA_C.state == State.EDITOR) {
	        	if(AAA_C.editorPaused){
			        switch (button) {
		                case Buttons.LEFT:activateMenu();
		                	break;
			        }
	        	}else{
	        		Editorhandler.painting = true;
	        	}
	        }
	        switch(button){
	        	case Buttons.MIDDLE:AAA_C.resetZoom();
	        	break;
	        }
        }
        return true;
    }
    
    public void activateMenu(){
    	if(ConHand.getActiveMenu().hover){
    		Scripthandler.handleScript(ConHand.getActiveAct().script);
    	}
    }
    
    public void touchDown_Container(int button){
    	switch(button){
	    	case Buttons.LEFT:ConHand.leftClick(staticMouse);
	    	break;
    	}
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
    	ConHand.clearMoving();
    	Editorhandler.painting = false;
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
    	updateMouse(screenX, screenY);
    	ConHand.moveCons(staticMouse.x - lastMouseX, staticMouse.y - lastMouseY);
    	switch(AAA_C.state){
    		case EDITOR:Editorhandler.brush.setPosition(mouse.x, mouse.y, true);
    		break;
    	}
    	updateLastMouse(screenX, screenY);
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
    	updateMouse(screenX, screenY);
    	if(ConHand.collides(staticMouse, AAA_C.getCTypeFromState(AAA_C.state))){
    		ConHand.getActiveContainer().mouseMoved(staticMouse);
    	}else{
    		ConHand.getActiveMenu().hover = ConHand.getActiveMenu().testMouseHover(staticMouse, new Rectangle(ConHand.getActiveMenu().X, ConHand.getActiveMenu().Y, 0, 0));
    	}
    	switch(AAA_C.state){
    		case EDITOR:Editorhandler.brush.setPosition(mouse.x, mouse.y, true);
    		break;
    	}
    	updateLastMouse(screenX, screenY);
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
        staticMouse = new Rectangle((int) (screenX + AAA_C.getActiveRenderer().getScreenX()), (int) (-screenY - AAA_C.getActiveRenderer().getScreenY()), 1, 1);
    }
    
    public void updateLastMouse(int screenX, int screenY){
    	lastMouseX = (int) (screenX + AAA_C.getActiveRenderer().getScreenX());
    	lastMouseY = (int) (-screenY - AAA_C.getActiveRenderer().getScreenY());
    }
}
