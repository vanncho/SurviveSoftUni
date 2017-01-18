package game.models;

import game.staticData.Constants;
import game.models.interfaces.HumanObject;
import game.sprites.ImageLoader;
import game.sprites.SpriteAnimation;
import game.weapons.Pistol;
import game.weapons.Weapon;
import game.weapons.WeaponType;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.HashMap;

public class Player extends GameMovableObjectImpl implements HumanObject {

    private final int SPRITE_COUNT = 20;
    private final int SPRITE_COLUMNS = 20;
    private final int SPRITE_OFFSET_X = 0;
    private final int SPRITE_OFFSET_Y = 0;
    private final int SPRITE_WIDTH_PISTOL = 263;
    private final int SPRITE_WIDTH_MACHINE_GUN = 323;
    private final int SPRITE_WIDTH_SHOTGUN = 323;
    private final int SPRITE_HEIGHT = 220;
    private final int SPRITE_DURATION = 1000;
    private final int PLAYER_INITIAL_SCORE = 0;
    private final int PLAYER_INITIAL_LIVES = 3;

    private Weapon currentWeapon;
    private HashMap<WeaponType, Weapon> weapons = new HashMap<>();
    private boolean isShooting = false;
    private boolean canShoot = false;
    private int canShootTimer = 0;
    private ImageView playerImageView;

    //IB
    private double health;
    private int score;
    private int lives;

    public Player(int setTranslateX, int setTranslateY) {
        super(setTranslateX, setTranslateY);
        this.setSpriteCount(SPRITE_COUNT);
        this.setSpriteColumns(SPRITE_COLUMNS);
        this.setSpriteOffsetX(SPRITE_OFFSET_X);
        this.setSpriteOffsetY(SPRITE_OFFSET_Y);
        this.setSpriteWidth(SPRITE_WIDTH_PISTOL);
        this.setSpriteHeight(SPRITE_HEIGHT);

        this.setPlayerImageView(new ImageView(ImageLoader.PLAYER_IMAGE_PISTOL));

        this.setObjectSize(Constants.PLAYER_SIZE);

        this.getPlayerImageView().setFitHeight(Constants.PLAYER_SIZE);
        this.getPlayerImageView().setFitWidth(Constants.PLAYER_SIZE);

        this.getPlayerImageView().setViewport(new Rectangle2D(this.getSpriteOffsetX(), this.getSpriteOffsetY(), this.getSpriteWidth(), this.getSpriteHeight()));
        this.setAnimation(new SpriteAnimation(this.getPlayerImageView(),
                Duration.millis(SPRITE_DURATION),
                this.getSpriteCount(),
                this.getSpriteColumns(),
                this.getSpriteOffsetX(),
                this.getSpriteOffsetY(),
                this.getSpriteWidth(),
                this.getSpriteHeight()));
        this.getChildren().addAll(this.getPlayerImageView());

        this.setBoundingBox(calcBoundingBox(Constants.PLAYER_SIZE));

        this.currentWeapon = new Pistol();
        addWeapon(new Pistol());

        //IB testing
        this.setHealth(Constants.PLAYER_INITIAL_HEALTH);
        this.setScore(PLAYER_INITIAL_SCORE);
        this.setLives(PLAYER_INITIAL_LIVES);
    }

    public int getLives() {
        return this.lives;
    }

    private void setLives(int lives) {
        this.lives = lives;
    }

    public int getScore() {
        return this.score;
    }

    private void setScore(int score) {
        this.score = score;
    }

    public double getHealth() {
        return this.health;
    }

    private void setHealth(double health) {
        this.health = health;
    }

    public boolean getIsShooting() {
        return this.isShooting;
    }

    private void setIsShooting(boolean shooting) {
        this.isShooting = shooting;
    }

    public boolean getCanShoot() {
        return this.canShoot;
    }

    private void setCanShoot(boolean canShoot) {
        this.canShoot = canShoot;
    }

    public int getCanShootTimer() {
        return this.canShootTimer;
    }

    private void setCanShootTimer(int canShootTimer) {
        this.canShootTimer = canShootTimer;
    }

    public ImageView getPlayerImageView() {
        return this.playerImageView;
    }

    private void setPlayerImageView(ImageView playerImageView) {
        this.playerImageView = playerImageView;
    }

    public Weapon getCurrentWeapon() {
        return this.currentWeapon;
    }

    private void setCurrentWeapon(Weapon currentWeapon) {
        this.currentWeapon = currentWeapon;
    }

    public void addBonusHealth(int healAmount) {
        if (this.getHealth() + healAmount <= Constants.PLAYER_INITIAL_HEALTH) {
            this.setHealth(this.getHealth() + healAmount);
        } else {
            this.setHealth(Constants.PLAYER_INITIAL_HEALTH);
        }
    }

    public void gainLife() {
        this.setLives(this.getLives() + 1);
    }

    public void addWeapon(Weapon weapon) {
        if (!this.weapons.containsKey(weapon.getWeaponType())) {
            this.weapons.put(weapon.getWeaponType(), weapon);
        } else {
            this.weapons.get(weapon.getWeaponType()).addClip();
        }
    }

    public boolean changeWeapon(WeaponType weaponType) {
        if (this.weapons.containsKey(weaponType)) {
            setCurrentWeapon(this.weapons.get(weaponType));
            return true;
        }
        return false;
    }

    public void changePlayerState(String stateName) {
        this.getChildren().remove(getPlayerImageView());
        changePlayerWeaponImage(stateName);
        this.getChildren().addAll(this.getPlayerImageView());
    }

    public void changeLives(int lives) {
        this.setLives(lives);
    }

    public void changeScore(int score) {
        this.setScore(score);
    }

    public void changeHealth(double health) {
        this.setHealth(health);
    }

    public void isShooting(boolean shooting) {
        this.setIsShooting(shooting);
    }

    public void changeCanShoot(boolean canShoot) {
        this.setCanShoot(canShoot);
    }

    public void changeCanShootTimer(int canShootTimer) {
        this.setCanShootTimer(canShootTimer);
    }

    private void changePlayerWeaponImage(String stateName) {

        int spriteWidth = 0;

        switch (stateName) {
            case "PistolState":
                spriteWidth = SPRITE_WIDTH_PISTOL;
                break;
            case "MachineGunState":
                spriteWidth = SPRITE_WIDTH_MACHINE_GUN;
                break;
            case "ShotgunState":
                spriteWidth = SPRITE_WIDTH_SHOTGUN;
        }

        this.setSpriteCount(SPRITE_COUNT);
        this.setSpriteColumns(SPRITE_COLUMNS);
        this.setSpriteOffsetX(0);
        this.setSpriteOffsetY(0);
        this.setSpriteWidth(spriteWidth);
        this.setSpriteHeight(SPRITE_HEIGHT);

        switch (stateName) {
            case "PistolState":
                this.setPlayerImageView(new ImageView(ImageLoader.PLAYER_IMAGE_PISTOL));
                break;
            case "MachineGunState":
                this.setPlayerImageView(new ImageView(ImageLoader.PLAYER_IMAGE_MACHINE_GUN));
                break;
            case "ShotgunState":
                this.setPlayerImageView(new ImageView(ImageLoader.PLAYER_IMAGE_SHOTGUN));
                break;
        }


        this.setObjectSize(Constants.PLAYER_SIZE);

        this.getPlayerImageView().setFitHeight(Constants.PLAYER_SIZE);
        this.getPlayerImageView().setFitWidth(Constants.PLAYER_SIZE);

        this.getPlayerImageView().setViewport(new Rectangle2D(this.getSpriteOffsetX(), this.getSpriteOffsetY(), this.getSpriteWidth(), this.getSpriteHeight()));
        this.setAnimation(new SpriteAnimation(this.getPlayerImageView(),
                Duration.millis(200),
                this.getSpriteCount(),
                this.getSpriteColumns(),
                this.getSpriteOffsetX(),
                this.getSpriteOffsetY(),
                this.getSpriteWidth(),
                this.getSpriteHeight()));
    }

    public boolean playerHasWeapon(WeaponType weaponType) {
        return this.weapons.containsKey(weaponType);
    }
}