package game.bonusItems;

import game.bonusItems.enums.BonusType;
import game.sprites.ImageLoader;

public class WeaponPistolBonus extends BonusImpl {

    public WeaponPistolBonus(int posX, int posY) {
        super(posX, posY);
        this.setBonusType(BonusType.PISTOL);
        this.configureImageView(ImageLoader.PISTOL_IMAGE);
        this.configureBoundingBox();
        this.getChildren().addAll(this.getImageView(), this.getBoundingBox());
    }
}
