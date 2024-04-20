package ru.jxhwy.corp.quaddro;

import com.badlogic.gdx.Game;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class MainGame extends Game {

    public static final int GAME_WIDTH = 720;
    public static final int GAME_HEIGHT = 1280;
    public static final int  NUMBER_COLUMNS = 3;
    public static final int  NUMBER_ROWS = 3;
    public static final int CARD_HEIGHT = 125;
    public static final int CARD_WIDTH = 125;


    @Override
    public void create() {
        setScreen(new FirstScreen());
    }
}
