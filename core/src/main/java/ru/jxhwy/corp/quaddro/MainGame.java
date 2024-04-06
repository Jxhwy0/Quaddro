package ru.jxhwy.corp.quaddro;

import com.badlogic.gdx.Game;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class MainGame extends Game {

    public static final int GAME_WIDTH = 1280;
    public static final int GAME_HEIGHT = 720;

    @Override
    public void create() {
        setScreen(new FirstScreen());
    }
}
