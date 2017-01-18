package game.staticData;

public class Constants {

    public static final int SPACING_SIZE = 5;
    public static final int SCREEN_CAMERA_OFFSET = 350;
    public static final int SCREEN_WIDTH = 1000;
    public static final int SCREEN_HEIGHT = 640;

    //region Title Screen Constants
    public static final String TITLE_TEXT_FONT_TYPE = "Tw Cen MT Condensed";
    public static final int TITLE_TEXT_FONT_SIZE = 50;
    public static final int TITLE_RECTANGLE_WIDTH = 640;
    public static final int TITLE_RECTANGLE_HEIGHT = 60;
    public static final int TITLE_RECTANGLE_BORDER_WIDTH = 2;
    public static final int TITLE_TRANSLATE_X = 25;
    public static final int TITLE_TRANSLATE_Y = 110;

    //endregion

    //region Main Menu Screen Constants
    public static final String MAIN_MENU_TEXT_FONT_TYPE = "Tw Cen MT Condensed";
    public static final int MAIN_MENU_TEXT_FONT_SIZE = 22;
    public static final int MAIN_MENU_RECTANGLE_WIDTH = 200;
    public static final int MAIN_MENU_RECTANGLE_HEIGHT = 30;
    public static final double MAIN_MENU_RECTANGLE_OPACITY = 0.4;
    public static final int MAIN_MENU_TRANSLATE_X = 25;
    public static final int MAIN_MENU_TRANSLATE_Y = 230;
    //endregion

    //region Game Over Screen Constants
    public static final String GAME_OVER_TEXT_FONT_TYPE = "Tw Cen MT Condensed";
    public static final int GAME_OVER_TEXT_FONT_SIZE = 50;
    public static final int GAME_OVER_RECTANGLE_WIDTH = 640;
    public static final int GAME_OVER_RECTANGLE_HEIGHT = 70;
    public static final int GAME_OVER_RECTANGLE_BORDER_WIDTH = 2;
    //endregion

    //region Player constants
    public static final double PLAYER_INITIAL_HEALTH = 100;
    public static final int PLAYER_VELOCITY = 4;
    public static final int PLAYER_SIZE = 40;
    //endregion

    //region EnemyImpl/NPC constants
    public static final int ZOMBIE_VELOCITY = 1;
    public static final int SMART_ZOMBIE_SPAWN_NUM = 40;
    public static final int DUMB_ZOMBIE_SPAWN_NUM = 20;
    public static final int ZOMBIE_SIZE = 35;
    public static final int ZOMBIE_HEALTH = 10;
    //endregion

    //region Level/Blocks constants
    public static final int BLOCK_SIZE = 45;
    public static final double BLOCK_OPACITY_DAMAGE = 0.3;
    //endregion

    //region Display constants
    public static final double DISPLAY_WIDTH = 1000;
    public static final double DISPLAY_HEIGHT = 640;
    //endregion

    //region Terrain Generation constants
    public static final boolean RANDOMISE_LEVELS = true;
    public static final int STARTING_LEVEL_WIDTH = 30;
    public static final int STARTING_LEVEL_HEIGHT = 30;
    public static final double ENEMY_SPAWN_INCREASE_FACTOR = 1.07;
    public static final int RANDOM_LEVEL_SIZE_INCREASE_AMOUNT = 2;

    //endregion

    //region Weapon Stat constants
    public static final int PISTOL_BULLET_SPEED = 12;
    public static final int PISTOL_MIN_DAMAGE = 3;
    public static final int PISTOL_MAX_DAMAGE = 5;
    public static final int PISTOL_SHOOT_DELAY_TIME = 35;
    public static final int PISTOL_RELOAD_DELAY_TIME = 50;
    public static final int PISTOL_CLIP_CAPACITY = 7;
    public static final int PISTOL_MAX_BULLET_CAPACITY = 28;

    public static final int MACHINE_GUN_BULLET_SPEED = 20;
    public static final int MACHINE_GUN_MIN_DAMAGE = 2;
    public static final int MACHINE_GUN_MAX_DAMAGE = 4;
    public static final int MACHINE_GUN_SHOOT_DELAY_TIME = 15;
    public static final int MACHINE_GUN_RELOAD_DELAY_TIME = 100;
    public static final int MACHINE_GUN_CLIP_CAPACITY = 30;
    public static final int MACHINE_GUN_MAX_BULLET_CAPACITY = 90;

    public static final int SHOTGUN_BULLET_SPEED = 12;
    public static final int SHOTGUN_MIN_DAMAGE = 2;
    public static final int SHOTGUN_MAX_DAMAGE = 3;
    public static final int SHOTGUN_SHOOT_DELAY_TIME = 30;
    public static final int SHOTGUN_RELOAD_DELAY_TIME = 100;
    public static final int SHOTGUN_CLIP_CAPACITY = 6;
    public static final int SHOTGUN_MAX_BULLET_CAPACITY = 30;
    public static final int SHOTGUN_BULLET_SPREAD_OFFSET = 15;
    public static final int SHOTGUN_PELLET_COUNT = 5;
    //endregion

    //region Pathfinding constants
    public static final int MAX_DEQUEUE_SIZE = 15; //for A*, Distance zombies start honing in on enemy
    public static final int V_H_COST = 10; //for a*, heuristic cost for neighbour cells
    public static final char[] ENEMY_DIRECTIONS = {'L', 'R', 'U', 'D'};
    //endregion

    //region Bonus Item constants
    public final static int BONUS_HEART_HEAL_AMOUNT = 20;
    public final static int BONUS_IMAGE_WIDTH = 15;
    public final static int BONUS_IMAGE_HEIGHT = 15;
    public final static double RANDOM_DROP_THRESHOLD = 0.9;
    //endregion

    //region Level constants
    public static final String BLOCK_IMAGE_PATH = "/game/resources/1.png";
    public static final int BLOCK_VIEWPORT_SIZE = 16;
    public static final int BRICK_BLOCK_OFFSET_X = 16;
    public static final int PLATFORM_BLOCK_OFFSET_X = 0;
    public static final int BRICK_BLOCK_OFFSET_Y = 0;
    public static final int PLATFORM_BLOCK_OFFSET_Y = 0;
    //endregion

    //region GUI constants
    public static final int GUI_DEFAULT_FONT_SIZE = 15;
    public static final String DEFAULT_BARS_FONT_STYLE = "Calibri";
    //endregion

    //region GUI health points constants
    public static final int HEALTH_POINTS_LAYOUT_X = 25;
    public static final int HEALTH_POINTS_LAYOUT_Y = 600;
    //endregion

    //region GUI score points constants
    public static final int SCORE_POINTS_LAYOUT_X = 815;
    public static final int SCORE_POINTS_LAYOUT_Y = 600;
    //endregion

    //region GUI Current Weapon Text Display constants
    public static final int WEAPON_TEXT_LAYOUT_X = 730;
    public static final int WEAPON_TEXT_LAYOUT_Y = 578;
    //endregion

    //region GUI Current Weapon Clip Capacity Text constants
    public static final int WEAPON_CLIP_TEXT_LAYOUT_X = 857;
    public static final int WEAPON_CLIP_TEXT_LAYOUT_Y = 578;
    //endregion
}
