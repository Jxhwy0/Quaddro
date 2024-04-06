package ru.jxhwy.corp.quaddro;

import com.badlogic.gdx.Screen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/** First screen of the application. Displayed after the application is created. */
public class FirstScreen implements Screen {

    LinkedList<Card> deckOfCards = new LinkedList<>();
    User user1 = new User();
    User user2 = new User();

    @Override
    public void show() {
        int[]numbers = new int[]{1,2,3,4};
        String[]colours = new String[]{"red", "green", "blue", "yellow"};
        String[]shapes = new String[]{"square", "triangle", "circle", "cross"};
        for (int number : numbers) {
            for (String colour : colours) {
                for (String shape : shapes) {
                    Card card = new Card(number, colour, shape);
                    deckOfCards.add(card);
                }
            }
        }

        Collections.shuffle(deckOfCards);

        for(int i = 0; i<4; i++){
            Card card = deckOfCards.pop();
            user1.cardsList.add(card);

            Card card2 = deckOfCards.pop();
            user2.cardsList.add(card2);
        }

    }
    @Override
    public void render(float delta) {
        // Draw your screen here. "delta" is the time since last render in seconds.
    }
    @Override
    public void resize(int width, int height) {
        // Resize your screen here. The parameters represent the new window size.
    }
    @Override
    public void pause() {
        // Invoked when your application is paused.
    }
    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }
    @Override
    public void hide() {
        // This method is called when another screen replaces this one.
    }
    @Override
    public void dispose() {
        // Destroy screen's assets here.
    }
}
