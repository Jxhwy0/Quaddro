package ru.jxhwy.corp.quaddro;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

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
            case SQUARE -> texture = new Texture("krug.jpg");
            case CROSS -> texture = new Texture("234.png");
            case TRIANGLE -> texture = new Texture("09.jpg");
            case CIRCLE -> texture = new Texture("square337.png");
        }
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        BitmapFont font = Utils.createFont();
        labelStyle.font = font;
        labelStyle.fontColor = Color.GOLD;
        Label scores = new Label(String.valueOf(score), labelStyle);
        scores.setPosition(75, 100);
        scores.setRotation(60);
        addActor(scores);

        setWidth(85);
        setHeight(125);
        setOriginX(getWidth() / 2);
        setOriginY(getHeight()/2);
        addListener(new DragListener(){
            @Override
            public void drag(InputEvent event, float x, float y, int pointer) {
                moveBy(x - getWidth()/2,y-getHeight()/2);
            }
        });
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
        batch.draw(texture, getX(),getY(), getWidth(), getHeight());
        super.draw(batch, parentAlpha);
    }
}
