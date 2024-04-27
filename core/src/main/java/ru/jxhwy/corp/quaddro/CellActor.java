package ru.jxhwy.corp.quaddro;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;


public class CellActor extends Actor {
    private final Texture texture;
    private final int row;
    private final int column;
    private Rectangle boundary;

    public CellActor(Texture texture, int row, int column, int cellX, int cellY) {
        this.texture = texture;
        this.row = row;
        this.column = column;
       // setPosition(cellX,cellY);
        setWidth(MainGame.CARD_WIDTH);
        setHeight(MainGame.CARD_HEIGHT);
        boundary = new Rectangle(cellX,cellY,MainGame.CARD_WIDTH,MainGame.CARD_HEIGHT);

    }

    public Rectangle getBoundary() {
        return boundary;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture,getX(),getY(),getWidth(),getHeight());
    }

    @Override
    public String toString() {
        return "CellActor{" +
            "texture=" + texture +
            ", row=" + row +
            ", column=" + column +
            ", boundary=" + boundary +
            '}';
    }
}
