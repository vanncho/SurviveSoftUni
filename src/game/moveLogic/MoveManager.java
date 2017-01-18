package game.moveLogic;

import game.collisions.CollisionManager;
import game.level.Level;
import game.models.Player;
import game.models.interfaces.GameMovableObject;
import game.moveLogic.interfaces.Movable;
import javafx.scene.shape.Shape;

import java.util.List;

public abstract class MoveManager implements Movable {
    protected GameMovableObject gameMovableObject;

    public MoveManager(GameMovableObject gameMovableObject) {
        this.gameMovableObject = gameMovableObject;
    }

    public GameMovableObject getGameMovableObject() {
        return this.gameMovableObject;
    }

    public void setGameMovableObject(GameMovableObject gameMovableObject) {
        this.gameMovableObject = gameMovableObject;
    }

    @Override
    public void move(int value, Axis axis) {
        boolean isPositiveDirection = value > 0;
        for (int i = 0; i < Math.abs(value); i++) {

            if (checkPlatformsForCollisions(axis, isPositiveDirection, Level.impassableBlockBBoxes)) {
                return;
            }
            if (checkPlatformsForCollisions(axis, isPositiveDirection, Level.destructibleBlockBBoxes)) {
                return;
            }
            for (Shape platform : Level.passableBlockBBoxes) {
                if (CollisionManager.checkWallCollision(this.gameMovableObject, isPositiveDirection, platform, axis, false)) {
                    if (this.gameMovableObject instanceof Player) {
                        //Level.levelNumber++;
                        Level.setShouldChangeLevel(true);
                        break;
                    }
                }
            }
            if (Level.getShouldChangeLevel()) {
                //return;
            }

            this.gameMovableObject.isInCollision(false);
            if (axis == Axis.X) {
                this.gameMovableObject.setTranslateX(this.gameMovableObject.getTranslateX() + (isPositiveDirection ? 1 : -1));
                this.gameMovableObject.getBoundingBox()
                        .setTranslateX(this.gameMovableObject.getBoundingBox().getTranslateX() + (isPositiveDirection ? 1 : -1));
            } else {
                this.gameMovableObject.setTranslateY(this.gameMovableObject.getTranslateY() + (isPositiveDirection ? 1 : -1));
                this.gameMovableObject.getBoundingBox()
                        .setTranslateY(this.gameMovableObject.getBoundingBox().getTranslateY() + (isPositiveDirection ? 1 : -1));
            }
        }
    }

    private boolean checkPlatformsForCollisions(Axis axis, boolean isPositiveDirection, List<Shape> blockBBoxes) {
        for (Shape platform : blockBBoxes) {
            if (CollisionManager.checkWallCollision(this.gameMovableObject, isPositiveDirection, platform, axis, true)) {
                this.gameMovableObject.isInCollision(true);
                if (this.gameMovableObject instanceof Player) {

                }
                return true;
            }
        }
        return false;
    }
}
