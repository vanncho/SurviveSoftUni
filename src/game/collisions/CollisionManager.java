package game.collisions;

import game.staticData.Constants;
import game.models.interfaces.GameMovableObject;
import game.moveLogic.Axis;
import javafx.scene.shape.Shape;

public class CollisionManager {

    public static boolean checkWallCollision(GameMovableObject gameMovableObject,
                                             boolean isPositiveDirection,
                                             Shape platform,
                                             Axis axis,
                                             boolean activeCollision) {
        if (axis == Axis.X) {
            if (gameMovableObject.getBoundsInParent().intersects(platform.getBoundsInParent())) {
                if (!activeCollision){
                    return true;
                }
                if (isPositiveDirection) {
                    if (gameMovableObject.getTranslateX() + gameMovableObject.getObjectSize() == platform.getTranslateX()) {
                        gameMovableObject.setTranslateX(gameMovableObject.getTranslateX() - 1);
                        gameMovableObject.getBoundingBox().setTranslateX(gameMovableObject.getBoundingBox().getTranslateX() - 1);
                        return true;
                    }
                } else {
                    if (gameMovableObject.getTranslateX() == platform.getTranslateX() + Constants.BLOCK_SIZE) {
                        gameMovableObject.setTranslateX(gameMovableObject.getTranslateX() + 1);
                        gameMovableObject.getBoundingBox().setTranslateX(gameMovableObject.getBoundingBox().getTranslateX() + 1);
                        return true;
                    }
                }
            }
        } else {
            if (gameMovableObject.getBoundsInParent().intersects(platform.getBoundsInParent())) {
                if (!activeCollision){
                    return true;
                }
                if (isPositiveDirection) {
                    if (gameMovableObject.getTranslateY() + gameMovableObject.getObjectSize() == platform.getTranslateY()) {
                        gameMovableObject.setTranslateY(gameMovableObject.getTranslateY() - 1);
                        gameMovableObject.getBoundingBox().setTranslateY(gameMovableObject.getBoundingBox().getTranslateY() - 1);

                        return true;
                    }
                } else {
                    if (gameMovableObject.getTranslateY() == platform.getTranslateY() + Constants.BLOCK_SIZE) {
                        gameMovableObject.setTranslateY(gameMovableObject.getTranslateY() + 1);
                        gameMovableObject.getBoundingBox().setTranslateY(gameMovableObject.getBoundingBox().getTranslateY() + 1);
                        return true;

                    }
                }
            }
        }
        return false;
    }
}
