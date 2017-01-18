package game.weapons.factory;

import game.models.interfaces.HumanObject;
import game.weapons.Bullet;
import game.weapons.WeaponType;

import java.util.ArrayList;

public interface BulletFactory {

    ArrayList<Bullet> createBullets(WeaponType weaponType, HumanObject player, double mousePosX, double mousePosY);
}
