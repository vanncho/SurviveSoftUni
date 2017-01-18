package game.models.interfaces;

import game.interfaces.JavaFX;
import game.sprites.SpriteAnimation;
import javafx.scene.shape.Shape;

public interface GameMovableObject extends JavaFX{

    int getSpriteCount();

    int getSpriteColumns();

    int getSpriteOffsetX();

    int getSpriteOffsetY();

    int getSpriteWidth();

    int getSpriteHeight();

    Shape getBoundingBox();

    boolean getIsInCollision();

    int getPosX();

    int getPosY();

    SpriteAnimation getAnimation();

    int getObjectSize();

    Shape calcBoundingBox(int size);

    void changeSpriteCount(int spriteCount);

    void changeSpriteColumns(int spriteColumns);

    void changeSpriteOffsetX(int spriteOffsetX);

    void changeSpriteOffsetY(int spriteOffsetY);

    void changeSpriteWidth(int spriteSpriteWidth);

    void changeSpriteHeight(int spriteSpriteHeight);

    void changeBoundingBox(Shape boundingBox);

    void isInCollision(boolean inCollision);

    void changeId(int id);

    void changePosXGrid(int posX);

    void changePosYGrid(int posY);

    void changeAnimation(SpriteAnimation animation);

    void changeObjectSize(int objectSize);
}
