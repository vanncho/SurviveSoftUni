package game.bonusItems.interfaces;

import game.bonusItems.enums.BonusType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public interface Bonus {

    Rectangle getBoundingBox();

    ImageView getImageView();

    int getPosX();

    int getPosY();

    Image getImage();

    BonusType getBonusType();
}
