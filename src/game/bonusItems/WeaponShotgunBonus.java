package game.bonusItems;

import game.bonusItems.enums.BonusType;
import game.sprites.ImageLoader;

public class WeaponShotgunBonus extends BonusImpl {

    public WeaponShotgunBonus(int posX, int posY) {
        super(posX, posY);
        this.setBonusType(BonusType.SHOTGUN);
        this.configureImageView(ImageLoader.SHOTGUN_IMAGE);
        this.configureBoundingBox();
        this.getChildren().addAll(this.getImageView(), this.getBoundingBox());
    }
}
