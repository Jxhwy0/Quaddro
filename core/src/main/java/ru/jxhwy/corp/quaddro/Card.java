package ru.jxhwy.corp.quaddro;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;

public class Card extends Group {
    int score;
    Color color;
    ShapeEnum shape;
    TextureRegion textureRegion;
    float grabOffsetX;
    float grabOffsetY;
    Texture texture1;
    Texture texture2;
    Texture texture3;
    Texture texture4;
    private Rectangle boundary = new Rectangle();
    float startX;
    float startY;
    InputListener listener;

    public Card(int score, Color color, ShapeEnum shape, FirstScreen firstScreen) {
        this.score = score;
        this.color = color;
        this.shape = shape;
        texture1 = new Texture("krug.jpg");
        texture2 = new Texture("234.png");
        texture3 = new Texture("09.jpg");
        texture4 = new Texture("square337.png");

        switch (shape) {
            case SQUARE -> textureRegion = new TextureRegion(texture1);
            case CROSS -> textureRegion = new TextureRegion(texture2);
            case TRIANGLE -> textureRegion = new TextureRegion(texture3);
            case CIRCLE -> textureRegion = new TextureRegion(texture4);
        }
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        BitmapFont font = Utils.createFont(20);
        labelStyle.font = font;
        labelStyle.fontColor = Color.GOLD;
        Label scores = new Label(String.valueOf(score), labelStyle);
        scores.setPosition(75, 100);
        scores.setRotation(60);
        addActor(scores);


        setWidth(MainGame.CARD_WIDTH);
        setHeight(MainGame.CARD_HEIGHT);
        setOriginX(getWidth() / 2);
        setOriginY(getHeight() / 2);

        listener = new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                toFront();
                addAction(Actions.scaleTo(1.1f, 1.1f, 0.25f));
                grabOffsetX = x;
                grabOffsetY = y;
                return true;
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                float deltaX = x - grabOffsetX;
                float deltaY = y - grabOffsetY;
                moveBy(deltaX, deltaY);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                addAction(Actions.scaleTo(1.00f, 1.00f, 0.1f));
                Cell nearCell = findNearestCellActor();

                if (nearCell != null && isAllRight()) {
                    nearCell.setActor(Card.this);
                    removeListener(listener);
                    // moveToActor(findNearestCellActor());
                } else {
                    addAction(Actions.moveTo(startX, startY, 0.50f, Interpolation.pow3));
                    firstScreen.needShowWarning = true;
                }
            }
        };

        addListener(listener);

    }

    private boolean isAllRight() {
        // int[][]
        return true;
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        boundary = new Rectangle(x, y, getWidth(), getHeight());
        startX = x;
        startY = y;
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
    public void act(float delta) {
        super.act(delta);
        boundary.setPosition(getX(), getY());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.setColor(color);
        batch.draw(textureRegion,
            getX(),
            getY(),
            getOriginX(),
            getOriginY(),
            getWidth(),
            getHeight(),
            getScaleX(),
            getScaleY(),
            getRotation());
        super.draw(batch, parentAlpha);
    }

//    private CellActor findNearestCellActor() {
//        Stage stage = getStage();
//        Array<Actor> actors = stage.getActors();
//        float closestDistance = Float.MAX_VALUE;
//        CellActor nearestCellActor = null;
//        for (Actor actor : actors) {
//            if (actor instanceof CellActor) {
//                CellActor cellActor = (CellActor) actor;
//                if (boundary.overlaps(cellActor.getBoundary())) {
//                    float currentDistance =
//                        Vector2.dst(getX(), getY(), cellActor.getX(), cellActor.getY());
//
//                    if (currentDistance < closestDistance) {
//                        nearestCellActor = cellActor;
//                        closestDistance = currentDistance;
//                    }
//                }
//            }
//        }
//        return nearestCellActor;
//    }
//
    private Cell findNearestCellActor() {
        Stage stage = getStage();
        Array<Actor> actors = stage.getActors();
        float closestDistance = Float.MAX_VALUE;
        Cell nearestCellActor = null;
        for (Actor actor : actors) {
            if (actor instanceof ScrollPane) {
                ScrollPane pane = (ScrollPane) actor;
                Actor tableActor = pane.getActor();
                if (tableActor instanceof Table) {
                    Table table = (Table) tableActor;
                    Array<Cell> cells = table.getCells();
                    for (Cell cell : cells) {
                        //Rectangle hitbox = new Rectangle((pane.getX() + cell.getActorX()) - pane.getScrollX(), (pane.getY()  + cell.getActorY()) - pane.getScrollY(), cell.getActorWidth(),  cell.getActorHeight());
                       //Rectangle hitbox = new Rectangle((pane.getX() + cell.getActorX()) , (pane.getY()  + cell.getActorY() ), cell.getActorWidth(),  cell.getActorHeight());
                       //Rectangle hitbox = new Rectangle( cell.getActorX() , cell.getActorY() , cell.getActorWidth(),  cell.getActorHeight());
                       Rectangle hitbox = new Rectangle(pane.getX() +  cell.getActorX() , pane.getY()  +  cell.getActorY() , cell.getActorWidth(),  cell.getActorHeight());
                        System.out.println(cell.getRow() + " " + cell.getColumn() + " " + hitbox);
                        if (boundary.overlaps(hitbox)) {
                            float currentDistance =
                                Vector2.dst( getX() - pane.getX() + pane.getScrollX(), getY() - pane.getY() + pane.getScrollY(), cell.getActorX(), cell.getActorY());

                            if (currentDistance < closestDistance) {
                                nearestCellActor = cell;
                                closestDistance = currentDistance;
                            }
                        }

                    }
                    System.out.println("boundary" + boundary);
                    System.out.println("pane " + pane.getScrollX() + "  " + pane.getScrollY() + " " + (pane.getY() + pane.getHeight()));
                }
            }
        }
        return nearestCellActor;
    }

//    private Cell findNearestCellActor() {
//        Stage stage = getStage();
//        Array<Actor> actors = stage.getActors();
//        float closestDistance = Float.MAX_VALUE;
//        Cell nearestCellActor = null;
//        for (Actor actor : actors) {
//            if (actor instanceof ScrollPane) {
//                ScrollPane pane = (ScrollPane) actor;
//                System.out.println("pane " + (pane.getX() + pane.getWidth()) + "   " + (pane.getY() + pane.getHeight()));
//                Rectangle hitbox = new Rectangle(pane.getX() + 10, pane.getY() + pane.getHeight() - 125, 125, 125);
//                if (boundary.overlaps(hitbox)) {
//
//                }
//            }
//        }
//        return null;
//    }


    public void moveToActor(Actor other) {
        float x = other.getX() + (other.getWidth() - this.getWidth()) / 2;
        float y = other.getY() + (other.getHeight() - this.getHeight()) / 2;
        addAction(Actions.moveTo(x, y, 0.50f, Interpolation.pow3));
    }
}
