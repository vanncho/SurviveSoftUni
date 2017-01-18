package game.models.interfaces;

import javafx.scene.image.ImageView;

public interface Enemy extends GameMovableObject {

    int getHealth();

    int getPosYReal();

    int getPosXReal();

    boolean getIsCentered();

    boolean getAllowNextCellMove();

    ImageView getEnemyImageView();

    int getCurrentCellRow();

    int getCurrentCellCol();

    void changeHealth(int health);

    void changeDealDamage(int damage);

    void changePosXPixel(int posXReal);

    void changePosYPixel(int posYReal);

    void changeCurrentCellRow(int currentCellRow);

    void changeCurrentCellCol(int currentCellCol);

    void isCentered(boolean centered);

    void changeAllowNextCellMove(boolean allowNextCellMove);

    void changeEnemyImageView(ImageView enemyImageView);

}
