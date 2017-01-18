package game.bonusItems;

import game.bonusItems.enums.BonusType;
import game.sprites.ImageLoader;

public class WeaponUziBonus extends BonusImpl {

    public WeaponUziBonus(int posX, int posY) {
        super(posX, posY);
        this.setBonusType(BonusType.MACHINE_GUN);
        this.configureImageView(ImageLoader.MACHINE_GUN_IMAGE);
        this.configureBoundingBox();
        this.getChildren().addAll(this.getImageView(), this.getBoundingBox());
    }
}
