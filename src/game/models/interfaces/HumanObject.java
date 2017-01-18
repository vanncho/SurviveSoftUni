package game.models.interfaces;

import game.weapons.Weapon;
import game.weapons.WeaponType;
import javafx.scene.image.ImageView;

public interface HumanObject extends GameMovableObject{

    int getLives();

    int getScore();

    double getHealth();

    boolean getIsShooting();

    boolean getCanShoot();

    int getCanShootTimer();

    ImageView getPlayerImageView();

    Weapon getCurrentWeapon();

    void addBonusHealth(int healAmount);

    void gainLife();

    void addWeapon(Weapon weapon);

    boolean changeWeapon(WeaponType weaponType);

    void changePlayerState(String stateName);

    void changeLives(int lives);

    void changeScore(int score);

    void changeHealth(double health);

    void isShooting(boolean shooting);

    void changeCanShoot(boolean canShoot);

    void changeCanShootTimer(int canShootTimer);

}
