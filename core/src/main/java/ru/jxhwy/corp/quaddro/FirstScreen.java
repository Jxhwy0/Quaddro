package ru.jxhwy.corp.quaddro;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Collections;
import java.util.LinkedList;

/** First screen of the application. Displayed after the application is created. */
public class FirstScreen implements Screen {

    LinkedList<Card> deckOfCards = new LinkedList<>();
    User user1 = new User();
    User user2 = new User();
    Stage stage;
    Card[][] cardArray = new Card[3][3];
    Actor warningActor;

    boolean needShowWarning = false;

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        int[] scores = new int[]{1, 2, 3, 4};
        Color[] colors = new Color[]{Color.RED, Color.GREEN, Color.BLUE, Color.ORANGE};
        ShapeEnum[] shapes = new ShapeEnum[]{ShapeEnum.CIRCLE, ShapeEnum.CROSS, ShapeEnum.SQUARE, ShapeEnum.TRIANGLE};
        for (int number : scores) {
            for (Color color : colors) {
                for (ShapeEnum shape : shapes) {
                    Card card = new Card(number, color, shape, FirstScreen.this);
                    deckOfCards.add(card);
                }
            }
        }

        Collections.shuffle(deckOfCards);

        for (int i = 0; i < 4; i++) {
            Card card = deckOfCards.pop();
            user1.cardsList.add(card);

            Card card2 = deckOfCards.pop();
            user2.cardsList.add(card2);
        }
        System.out.println(user1.cardsList);
        System.out.println(user2.cardsList);

        int marginX = 100;
        int startMargin = MainGame.GAME_WIDTH / 4;
        for (int i = 0; i < user1.cardsList.size(); i++) {
            Card card = user1.cardsList.get(i);
            Card card1 = user2.cardsList.get(i);
            card1.setPosition(startMargin + i * marginX, 1100);
            card.setPosition(startMargin + i * marginX, 0);
            stage.addActor(card);
            stage.addActor(card1);
        }

        Texture cellTexture = new Texture("border.png");

        Table mytable = new Table();
        for (int row = 0; row < MainGame.NUMBER_ROWS; row++) {
            for (int column = 0; column < MainGame.NUMBER_COLUMNS; column++) {
                CellActor ca = new CellActor(cellTexture, row, column, 0,0);
                mytable.add(ca);
            }
            mytable.row();
        }
        ScrollPane.ScrollPaneStyle scrollPaneStyle = new ScrollPane.ScrollPaneStyle();
        ScrollPane pane = new ScrollPane(mytable, scrollPaneStyle);
//        int cellX = 150 * column + 100;
//        int cellY = 600 - 150 * row;
        pane.setWidth(600);
        pane.setHeight(400);

        pane.setPosition(150, 300);

        stage.addActor(pane);
        System.out.println("mytable " + mytable.getWidth());
        System.out.println("mytable " + mytable.getHeight());

//        for (int row = 0; row < MainGame.NUMBER_ROWS; row++) {
//            for (int column = 0; column < MainGame.NUMBER_COLUMNS; column++) {
//                int cellX = 150 * column + 100;
//                int cellY = 600 - 150 * row;
//                CellActor ca = new CellActor(cellTexture, row, column, cellX, cellY);
//                stage.addActor(ca);
//            }
//        }

        warningActor = addText();
        warningActor.setVisible(false);
        stage.addActor(warningActor);
    }

    public Label addText () {
        Label.LabelStyle labelStyle1 = new Label.LabelStyle();
        BitmapFont font = Utils.createFont();
        labelStyle1.font = font;
        labelStyle1.fontColor = Color.RED;
        Label text = new Label("СЮДА НЕЛЬЗЯ!!", labelStyle1);
        text.setPosition(300,700 );
        return text;

    }

    float counter = 0;

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.WHITE);
        stage.act();
        stage.draw();

        if(needShowWarning){
            warningActor.setVisible(true);
            counter += delta;
        }else {
            warningActor.setVisible(false);
        }

        if (counter > 1F){
            needShowWarning = false;
            counter = 0;
        }
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
