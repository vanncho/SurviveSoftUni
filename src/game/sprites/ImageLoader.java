package game.sprites;

import javafx.scene.image.Image;

public class ImageLoader {

    //player
    public static Image PLAYER_IMAGE_PISTOL = new Image(ImageLoader.class.getResourceAsStream("/game/resources/models/player/survivor-move_handgun.png"));
    public static Image PLAYER_IMAGE_MACHINE_GUN = new Image(ImageLoader.class.getResourceAsStream("/game/resources/models/player/survivor-move_machinegun.png"));
    public static Image PLAYER_IMAGE_SHOTGUN = new Image(ImageLoader.class.getResourceAsStream("/game/resources/models/player/survivor-move_shotgun.png"));

    //Enemies
    public static Image ZOMBIE_IMAGE = new Image(ImageLoader.class.getResourceAsStream("/game/resources/models/zombie/zombie.png"));
    public static Image DUMB_ZOMBIE_IMAGE = new Image(ImageLoader.class.getResourceAsStream("/game/resources/models/zombie/dumbZombie.png"));

    //Weapons
    public static Image BULLET_IMAGE = new Image(ImageLoader.class.getResourceAsStream("/game/resources/weapons/Bullet/bullet.png"));
    public static Image MACHINE_GUN_IMAGE = new Image(ImageLoader.class.getResourceAsStream("/game/resources/weapons/machineGun/uzi.png"));
    public static Image PISTOL_IMAGE = new Image(ImageLoader.class.getResourceAsStream("/game/resources/weapons/pistol/pistol.png"));
    public static Image SHOTGUN_IMAGE = new Image(ImageLoader.class.getResourceAsStream("/game/resources/weapons/Shotgun/shotgun.png"));

    //Gui and menus
    public static Image HEALTH_BAR = new Image(ImageLoader.class.getResourceAsStream("/game/resources/gui/healthBar.png"));
    public static Image WEAPON_BAR_BACKGROUND = new Image(ImageLoader.class.getResourceAsStream("/game/resources/gui/weaponBar_Background.png"));
    public static Image HEALTH_BAR_BACKGROUND = new Image(ImageLoader.class.getResourceAsStream("/game/resources/gui/healthBar_Background.png"));
    public static Image MAIN_MENU_IMAGE = new Image(ImageLoader.class.getResourceAsStream("/game/resources/menu/menu_1000_640.jpg"));

    //Levels/blocks
    public static Image EXIT_SIGN = new Image(ImageLoader.class.getResourceAsStream("/game/resources/level/exitSign.png"));

    public static Image BLOCKS_IMG = new Image(ImageLoader.class.getResourceAsStream("/game/resources/level/blocks.png"));
    public static Image DESKS = new Image(ImageLoader.class.getResourceAsStream("/game/resources/level/desk.png"));
    public static Image BILLIARD = new Image(ImageLoader.class.getResourceAsStream("/game/resources/level/billiard.png"));
    public static Image WINDOWS = new Image(ImageLoader.class.getResourceAsStream("/game/resources/level/windows.png"));
    public static Image SINK = new Image(ImageLoader.class.getResourceAsStream("/game/resources/level/sink.png"));
    public static Image HOUSE_STUFF = new Image(ImageLoader.class.getResourceAsStream("/game/resources/level/toilet_table.png"));
    public static Image BLOOD_TABLE = new Image(ImageLoader.class.getResourceAsStream("/game/resources/level/blood_table.png"));
    public static Image TABLE = new Image(ImageLoader.class.getResourceAsStream("/game/resources/level/table.png"));
    public static Image COUCH = new Image(ImageLoader.class.getResourceAsStream("/game/resources/level/couch.png"));

    //Bonus
    public static Image HEART = new Image(ImageLoader.class.getResourceAsStream("/game/resources/bonus/heart.png"));
}
