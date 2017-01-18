package game.weapons;

import game.sprites.ImageLoader;
import game.staticData.Constants;
import javafx.scene.image.Image;

public enum WeaponType {

    PISTOL("Pistol",
            ImageLoader.PISTOL_IMAGE,
            Constants.PISTOL_BULLET_SPEED,
            Constants.PISTOL_MIN_DAMAGE,
            Constants.PISTOL_MAX_DAMAGE,
            Constants.PISTOL_SHOOT_DELAY_TIME,
            Constants.PISTOL_RELOAD_DELAY_TIME,
            Constants.PISTOL_CLIP_CAPACITY,
            Constants.PISTOL_MAX_BULLET_CAPACITY),
    MACHINE_GUN("Machine Gun",
            ImageLoader.MACHINE_GUN_IMAGE,
            Constants.MACHINE_GUN_BULLET_SPEED,
            Constants.MACHINE_GUN_MIN_DAMAGE,
            Constants.MACHINE_GUN_MAX_DAMAGE,
            Constants.MACHINE_GUN_SHOOT_DELAY_TIME,
            Constants.MACHINE_GUN_RELOAD_DELAY_TIME,
            Constants.MACHINE_GUN_CLIP_CAPACITY,
            Constants.MACHINE_GUN_MAX_BULLET_CAPACITY),
    SHOTGUN("Shotgun", ImageLoader.SHOTGUN_IMAGE,
            Constants.SHOTGUN_BULLET_SPEED,
            Constants.SHOTGUN_MIN_DAMAGE,
            Constants.SHOTGUN_MAX_DAMAGE,
            Constants.SHOTGUN_SHOOT_DELAY_TIME,
            Constants.SHOTGUN_RELOAD_DELAY_TIME,
            Constants.SHOTGUN_CLIP_CAPACITY,
            Constants.SHOTGUN_MAX_BULLET_CAPACITY);


    private String weaponName;
    private Image weaponImage;
    private int bulletSpeed;
    private int minDamage;
    private int maxDamage;
    private int shootDelayTime;
    private int reloadDelayTime;
    private int clipCapacity;
    private int maxBulletsCapacity;

    WeaponType(String weaponName,
               Image weaponImage,
               int bulletSpeed,
               int minDamage,
               int maxDamage,
               int shootDelayTime,
               int reloadDelayTime,
               int clipCapacity,
               int maxBulletsCapacity) {
        this.weaponName = weaponName;
        this.weaponImage = weaponImage;
        this.bulletSpeed = bulletSpeed;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.shootDelayTime = shootDelayTime;
        this.clipCapacity = clipCapacity;
        this.maxBulletsCapacity = maxBulletsCapacity;
    }

    public String getWeaponName() {
        return this.weaponName;
    }

    public Image getWeaponImage() {
        return this.weaponImage;
    }

    public int getBulletSpeed() {
        return this.bulletSpeed;
    }

    public int getMinDamage() {
        return this.minDamage;
    }

    public int getMaxDamage() {
        return this.maxDamage;
    }

    public int getShootDelayTime() {
        return this.shootDelayTime;
    }

    public int getReloadDelayTime() {
        return this.reloadDelayTime;
    }

    public int getClipCapacity() {
        return this.clipCapacity;
    }

    public int getMaxBulletsCapacity() {
        return this.maxBulletsCapacity;
    }
}
