package ru.jxhwy.corp.quaddro;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/** First screen of the application. Displayed after the application is created. */
public class FirstScreen implements Screen {

    LinkedList<Card> deckOfCards = new LinkedList<>();
    User user1 = new User();
    User user2 = new User();
    Stage stage;

    @Override
    public void show() {
        stage = new Stage();
        int[]scores = new int[]{1,2,3,4};
        Color[]colors = new Color[]{Color.RED, Color.GREEN, Color.BLUE, Color.ORANGE};
        String[]shapes = new String[]{"square", "triangle", "circle", "cross"};
        for (int number : scores) {
            for (Color color : colors) {
                for (String shape : shapes) {
                    Card card = new Card(number, color, shape);
                    deckOfCards.add(card);
//                    stage.addActor(card);
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
        System.out.println(user1.cardsList);
        System.out.println(user2.cardsList);

        int marginX = 100;
        for (int i = 0; i < user1.cardsList.size(); i++) {
            Card card = user1.cardsList.get(i);
            card.setPosition(i * marginX, 0);
            stage.addActor(card);
        }

    }
    @Override
    public void render(float delta) {
        stage.act();
        stage.draw();
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
