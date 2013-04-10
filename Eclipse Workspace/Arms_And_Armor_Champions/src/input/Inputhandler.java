package input;

import java.awt.Rectangle;

import scripting.Scripthandler;

import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputProcessor;
import com.rapplebob.ArmsAndArmorChampions.*;
import static com.badlogic.gdx.Input.Keys.*;


public class Inputhandler implements InputProcessor {

    public Rectangle mouse = new Rectangle(0, 0, 0, 0);

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
            if (AAA_C.getActiveMenu().thereAreHighlitActs()) {
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
                    	AAA_C.getActiveMenu().up();
                        break;
                    case DOWN:
                    	AAA_C.getActiveMenu().down();
                        break;
                    case ENTER:
                        Scripthandler.handleScript(AAA_C.getActiveAct().script);
                        break;
                    case NUM_1:
                    	AAA_C.getActiveMenu().activeAct = 0;
                        break;
                    case NUM_2:
                    	AAA_C.getActiveMenu().activeAct = 1;
                        break;
                    case NUM_3:
                    	AAA_C.getActiveMenu().activeAct = 2;
                        break;
                    case NUM_4:
                    	AAA_C.getActiveMenu().activeAct = 3;
                        break;
                    case NUM_5:
                    	AAA_C.getActiveMenu().activeAct = 4;
                        break;
                    case NUM_6:
                    	AAA_C.getActiveMenu().activeAct = 5;
                        break;
                    case NUM_7:
                    	AAA_C.getActiveMenu().activeAct = 6;
                        break;
                    case NUM_8:
                    	AAA_C.getActiveMenu().activeAct = 7;
                        break;
                    case NUM_9:
                    	AAA_C.getActiveMenu().activeAct = 8;
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
                    	AAA_C.getActiveMenuhandler().getActiveMenu().up();
                        break;
                    case DOWN:
                    	AAA_C.getActiveMenuhandler().getActiveMenu().down();
                        break;
                    case ENTER:
                        Scripthandler.handleScript(AAA_C.getActiveAct().script);
                        break;
                }
            } else {
                switch (keycode) {

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
        if (AAA_C.state == State.DEFAULT) {
        } else if (AAA_C.state == State.MENU) {
            switch (button) {
                case Buttons.LEFT:
                    Scripthandler.handleScript(AAA_C.getActiveAct().script);
                    break;
            }
        } else if (AAA_C.state == State.GAME) {
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
        mouse = new Rectangle(screenX + (int) AAA_C.getActiveRenderer().getScreenX(), -screenY - (int) AAA_C.getActiveRenderer().getScreenY(), 1, 1);
        AAA_C.getActiveMenuhandler().getActiveMenu().testMouseHover(mouse, new Rectangle(AAA_C.getActiveMenuhandler().x, AAA_C.getActiveMenuhandler().y, 0, 0), AAA_C.getActiveRenderer().com32);
        return true;
    }

    @Override
    public boolean scrolled(int amount) {
        return true;
    }
}
