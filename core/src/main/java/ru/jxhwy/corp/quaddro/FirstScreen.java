package ru.jxhwy.corp.quaddro;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.SnapshotArray;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/** First screen of the application. Displayed after the application is created. */
public class FirstScreen implements Screen {

    private ShapeRenderer shapeRenderer;

    List<Rectangle> rects = new ArrayList<>();

    LinkedList<Card> deckOfCards = new LinkedList<>();
    User user1 = new User();
    User user2 = new User();
    Stage stage;
    Card[][] cardArray = new Card[3][3];
    Actor warningActor;

    FitViewport viewport;

    boolean needShowWarning = false;

    @Override
    public void show() {
        shapeRenderer = new ShapeRenderer();
        viewport = new FitViewport(MainGame.GAME_WIDTH, MainGame.GAME_HEIGHT);
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);

        Texture cellTexture = new Texture("border.png");
        Table mytable = new Table();
        for (int row = 0; row < MainGame.NUMBER_ROWS; row++) {
            for (int column = 0; column < MainGame.NUMBER_COLUMNS; column++) {
                CellActor ca = new CellActor(cellTexture, row, column);
                mytable.add(ca).pad(10);
            }
            mytable.row();
        }
//        mytable.layout();
//
//        Array<Cell> children =  mytable.getCells();
//        for (int j = 0; j < children.size; j++) {
//            CellActor cellActor = (CellActor)children.get(j).getActor();
//            Rectangle rectangle = new Rectangle(children.get(j).getActorX(), children.get(j).getActorY(), children.get(j).getActorWidth(), children.get(j).getActorHeight());
//            cellActor.setBoundary(rectangle);
//            rects.add(rectangle);
//        }

        ScrollPane.ScrollPaneStyle scrollPaneStyle = new ScrollPane.ScrollPaneStyle();
        ScrollPane pane = new ScrollPane(mytable, scrollPaneStyle);
//        int cellX = 150 * column + 100;
//        int cellY = 600 - 150 * row;
        pane.setWidth(MainGame.GAME_WIDTH);
        pane.setHeight(MainGame.GAME_HEIGHT / 2);
        pane.setPosition(0, MainGame.GAME_HEIGHT/4);
        stage.addActor(pane);

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
 //       System.out.println(rects);
        warningActor = addText();
        warningActor.setVisible(false);
        stage.addActor(warningActor);
    }

    public Label addText () {
        Label.LabelStyle labelStyle1 = new Label.LabelStyle();
        BitmapFont font = Utils.createFont(50);
        labelStyle1.font = font;
        labelStyle1.fontColor = Color.RED;
        Label text = new Label("СЮДА НЕЛЬЗЯ!", labelStyle1);
        text.setPosition((float) (MainGame.GAME_WIDTH/4),MainGame.GAME_HEIGHT/6);
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
//
//        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
//        shapeRenderer.setColor(0, 1, 0, 1);
//        Array<Actor> actors = stage.getActors();
//        float closestDistance = Float.MAX_VALUE;
//        Cell nearestCellActor = null;
//        for (Actor actor : actors) {
//            if (actor instanceof ScrollPane) {
//                ScrollPane pane = (ScrollPane) actor;
//                Actor tableActor = pane.getActor();
//                if (tableActor instanceof Table) {
//                    Table table = (Table) tableActor;
//                    Array<Cell> cells = table.getCells();
//                    for (Cell cell : cells) {
//                        //Rectangle hitbox = new Rectangle((pane.getX() + cell.getActorX()) - pane.getScrollX(), (pane.getY()  + cell.getActorY()) - pane.getScrollY(), cell.getActorWidth(),  cell.getActorHeight());
//                        Rectangle hitbox = new Rectangle((pane.getX() + pane.getWidth() + cell.getActorX()) , (pane.getY()  + cell.getActorY() + pane.getHeight()), cell.getActorWidth(),  cell.getActorHeight());
//                        shapeRenderer.rect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
//                        System.out.println(hitbox);
//                    }
//                    System.out.println("pane " + pane.getScrollX() + "  " + pane.getScrollY());
//                }
//            }
//        }
//
//        shapeRenderer.end();
    }
    @Override
    public void resize(int width, int height) {
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
