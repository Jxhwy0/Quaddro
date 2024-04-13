package ru.jxhwy.corp.quaddro;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class Card extends Group {
    int score;
    Color color;
    ShapeEnum shape;
    Texture texture;


    public Card(int score, Color color, ShapeEnum shape) {
        this.score = score;
        this.color = color;
        this.shape = shape;

        switch (shape) {
            case SQUARE ->
                texture = new Texture("krug.jpg");
            case CROSS ->
                texture = new Texture("234.png");
            case TRIANGLE ->
                texture = new Texture("09.jpg");
            case CIRCLE ->
                texture = new Texture("square337.png");
        }
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        BitmapFont font = Utils.createFont();
        labelStyle.font = font;
        labelStyle.fontColor = Color.GOLD;

        Label scores = new Label(String.valueOf(score), labelStyle);
        scores.setPosition(100, 100);
        addActor(scores);
    }

    @Override
    public String toString() {
        return "Card{" +
            "score=" + score +
            ", color='" + color + '\'' +
            ", shape='" + shape + '\'' +
            '}';
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        batch.setColor(color);
        batch.draw(texture, getX(),getY());
        super.draw(batch, parentAlpha);
    }
}
