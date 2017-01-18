package game.bonusItems;

import game.bonusItems.enums.BonusType;
import game.sprites.ImageLoader;

public class HealthBonus extends BonusImpl {

    public HealthBonus(int posX, int posY) {
        super(posX, posY);
        this.setBonusType(BonusType.HEART);
        this.configureImageView(ImageLoader.HEART);
        this.configureBoundingBox();
        this.getChildren().addAll(this.getImageView(), this.getBoundingBox());
    }
}
