//package ru.sibakovskaya.jigsawpuzzle;
//
//import static ru.sibakovskaya.jigsawpuzzle.levels.PuzzleScreen.PUZZLE_ZONE_WIDTH;
//
//import com.badlogic.gdx.graphics.g2d.Batch;
//import com.badlogic.gdx.graphics.g2d.TextureRegion;
//import com.badlogic.gdx.math.Interpolation;
//import com.badlogic.gdx.math.Rectangle;
//import com.badlogic.gdx.math.Vector2;
//import com.badlogic.gdx.scenes.scene2d.Actor;
//import com.badlogic.gdx.scenes.scene2d.InputEvent;
//import com.badlogic.gdx.scenes.scene2d.InputListener;
//import com.badlogic.gdx.scenes.scene2d.Stage;
//import com.badlogic.gdx.scenes.scene2d.actions.Actions;
//import com.badlogic.gdx.utils.Array;
//
//public class PuzzlePiece extends Actor {
//    private final TextureRegion textureRegion;
//
//    private final int row;
//    private final int column;
//    private Rectangle boundary = new Rectangle();
//    private float grabOffsetX;
//    private float grabOffsetY;
//
//    private boolean isSelected;
//
//    public PuzzlePiece(
//            TextureRegion textureRegion,
//            int row,
//            int column,
//            float width,
//            float height,
//            OnAddItemToInventoryListener listener
//    ) {
//        this.textureRegion = textureRegion;
//        setWidth(width);
//        setHeight(height);
//        this.row = row;
//        this.column = column;
//
//        addListener(
//                new InputListener() {
//                    @Override
//                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//                        isSelected = true;
//                        toFront();
//                        addAction(Actions.scaleTo(1.1f, 1.1f, 0.25f));
//                        grabOffsetX = x;
//                        grabOffsetY = y;
//                        return true;
//                    }
//
//                    @Override
//                    public void touchDragged(InputEvent event, float x, float y, int pointer) {
//                        float deltaX = x - grabOffsetX;
//                        float deltaY = y - grabOffsetY;
//                        moveBy(deltaX, deltaY);
//                    }
//
//                    @Override
//                    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
//                        isSelected = false;
//                        addAction(Actions.scaleTo(1.00f, 1.00f, 0.25f));
//                        PuzzleArea nearestPuzzleArea = findNearestPuzzleArea();
//                        if (nearestPuzzleArea != null) {
//                            moveToActor(nearestPuzzleArea);
//                        }
//
//                        // если пазл над инвентарем то перемещаем его в конец элементов
//                        if(getX() > PUZZLE_ZONE_WIDTH){
//                            listener.onAddItemToInventory(PuzzlePiece.this);
//                        }
//                        super.touchUp(event, x, y, pointer, button);
//                    }
//                }
//        );
//    }
//
//    @Override
//    public void setPosition(float x, float y) {
//        super.setPosition(x, y);
//        boundary = new Rectangle(x, y, getWidth(), getHeight());
//    }
//
//    public Rectangle getBoundary() {
//        return boundary;
//    }
//
//    public boolean isSelected() {
//        return isSelected;
//    }
//
//    public int getRow() {
//        return row;
//    }
//
//    public int getColumn() {
//        return column;
//    }
//
//    @Override
//    public void act(float delta) {
//        super.act(delta);
//        boundary.setPosition(getX(), getY());
//    }
//
//    @Override
//    public void draw(Batch batch, float parentAlpha) {
//        batch.draw(
//                textureRegion,
//                getX(),
//                getY(),
//                getOriginX(),
//                getOriginY(),
//                getWidth(),
//                getHeight(),
//                getScaleX(),
//                getScaleY(),
//                getRotation()
//        );
//    }
//
//    private PuzzleArea findNearestPuzzleArea() {
//        Stage stage = getStage();
//        Array<Actor> actors = stage.getActors();
//        float closestDistance = Float.MAX_VALUE;
//        PuzzleArea nearestPuzzleArea = null;
//        for (Actor actor : actors) {
//            if (actor instanceof PuzzleArea) {
//                PuzzleArea puzzleArea = (PuzzleArea) actor;
//                if (!puzzleArea.hasPuzzlePiece() && boundary.overlaps(puzzleArea.getBoundary())) {
//                    float currentDistance =
//                            Vector2.dst(getX(), getY(), puzzleArea.getX(), puzzleArea.getY());
//
//                    if (currentDistance < closestDistance) {
//                        nearestPuzzleArea = puzzleArea;
//                        closestDistance = currentDistance;
//                    }
//                }
//            }
//        }
//        return nearestPuzzleArea;
//    }
//
//    public void moveToActor(Actor other) {
//        float x = other.getX() + (other.getWidth() - this.getWidth()) / 2;
//        float y = other.getY() + (other.getHeight() - this.getHeight()) / 2;
//        addAction(Actions.moveTo(x, y, 0.50f, Interpolation.pow3));
//    }
//}
