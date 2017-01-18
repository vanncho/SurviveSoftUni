package game.bonusItems.enums;

import game.bonusItems.HealthBonus;
import game.bonusItems.WeaponPistolBonus;
import game.bonusItems.WeaponShotgunBonus;
import game.bonusItems.WeaponUziBonus;

public enum BonusType {

    HEART(HealthBonus.class),
    PISTOL(WeaponPistolBonus.class),
    MACHINE_GUN(WeaponUziBonus.class),
    SHOTGUN(WeaponShotgunBonus.class);

    public static final BonusType values[] = values();

    private Class bonusClass;

    BonusType(Class bonusClass) {
        this.bonusClass = bonusClass;
    }

    public Class getBonusClass() {
        return this.bonusClass;
    }
}

