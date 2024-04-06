package ru.jxhwy.corp.quaddro;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Card extends Actor{
    int score;
    Color color;
    String shape;
    Texture texture;

    public Card(int score, Color color, String shape) {
        texture = new Texture("square337.png");
        this.score = score;
        this.color = color;
        this.shape = shape;
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
    }
}
