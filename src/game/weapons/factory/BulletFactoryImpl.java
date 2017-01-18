package game.weapons.factory;

import game.models.interfaces.HumanObject;
import game.staticData.Constants;
import game.weapons.Bullet;
import game.weapons.WeaponType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BulletFactoryImpl implements BulletFactory {

    private ArrayList<Bullet> newBullets;

    public BulletFactoryImpl() {
        this.newBullets = new ArrayList<>();
    }


    @Override
    public ArrayList<Bullet> createBullets(WeaponType weaponType, HumanObject player, double mousePosX, double mousePosY) {
        this.newBullets.clear();
        Bullet bullet;


        switch (weaponType) {
            case PISTOL:
                bullet = new Bullet(weaponType.getMinDamage(),
                        weaponType.getMaxDamage(),
                        weaponType.getBulletSpeed());

                bullet.setTranslateX(player.getTranslateX() + Constants.PLAYER_SIZE / 2);
                bullet.setTranslateY(player.getTranslateY() + Constants.PLAYER_SIZE / 2);
                bullet.setTarget(mousePosX, mousePosY);

                this.newBullets.add(bullet);
                break;
            case MACHINE_GUN:
                bullet = new Bullet(weaponType.getMinDamage(),
                        weaponType.getMaxDamage(),
                        weaponType.getBulletSpeed());

                bullet.setTranslateX(player.getTranslateX() + Constants.PLAYER_SIZE / 2);
                bullet.setTranslateY(player.getTranslateY() + Constants.PLAYER_SIZE / 2);
                bullet.setTarget(mousePosX, mousePosY);

                this.newBullets.add(bullet);
                break;
            case SHOTGUN:
                for (int i = 0; i < Constants.SHOTGUN_PELLET_COUNT; i++) {
                    bullet = new Bullet(weaponType.getMinDamage(),
                            weaponType.getMaxDamage(),
                            weaponType.getBulletSpeed());
                    bullet.setTranslateX(player.getTranslateX() + Constants.PLAYER_SIZE / 2);
                    bullet.setTranslateY(player.getTranslateY() + Constants.PLAYER_SIZE / 2);

                    int[] spreadOffset = getRandomBulletSpread();
                    bullet.setTarget(mousePosX + spreadOffset[0], mousePosY + spreadOffset[1]);

                    this.newBullets.add(bullet);
                }
                break;
        }

        return newBullets;
    }

    private int[] getRandomBulletSpread() {
        Random rand = new Random();
        int[] position = new int[2];

        int x = rand.nextInt(2 * Constants.SHOTGUN_BULLET_SPREAD_OFFSET) - Constants.SHOTGUN_BULLET_SPREAD_OFFSET;
        int y = rand.nextInt(2 * Constants.SHOTGUN_BULLET_SPREAD_OFFSET) - Constants.SHOTGUN_BULLET_SPREAD_OFFSET;

        position[0] = x;
        position[1] = y;

        return position;
    }
}
