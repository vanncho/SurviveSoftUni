package game.core;

import game.bonusItems.BonusImpl;
import game.staticData.Constants;
import game.gui.*;
import game.level.Level;
import game.level.LevelDataImpl;
import game.level.TerrainGenerator;
import game.level.interfaces.LevelData;
import game.menus.MainMenu;
import game.menus.MenuBox;
import game.menus.Title;
import game.models.DumbZombie;
import game.models.Player;
import game.models.SmartZombie;
import game.models.interfaces.RandomDirectionMovable;
import game.models.interfaces.SmartMovable;
import game.sprites.ImageLoader;
import javafx.animation.AnimationTimer;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.List;
import java.util.Random;
import java.util.Set;

public class Content {
    private Pane root;
    private Player player;
    private Set<SmartMovable> smartMovableEnemies;
    private Set<RandomDirectionMovable> randomDirectionMovableEnemies;
    private AnimationTimer timer;
    private ImageView menuView;
    private HealthBar healthbar;
    private CurrentWeaponDisplay currentWeaponDisplay;
    private HealthPoints healthPoints;
    private ScorePoints scorePoints;
    private WeaponTextDisplay weaponTextDisplay;
    private WeaponClipCapacityText weaponClipCapacityText;
    private List<BonusImpl> bonusItemList;
    private GUIDrawer guiDrawer;

    public Content(Pane root,
                   Player player,
                   Set<SmartMovable> smartMovableEnemies,
                   Set<RandomDirectionMovable> randomDirectionMovableEnemies,
                   AnimationTimer timer,
                   HealthBar healthbar,
                   CurrentWeaponDisplay currentWeaponDisplay,
                   HealthPoints healthPoints,
                   ScorePoints scorePoints,
                   WeaponTextDisplay weaponTextDisplay,
                   WeaponClipCapacityText weaponClipCapacityText,
                   List<BonusImpl> bonusItems,
                   GUIDrawer guiDrawer) {
        this.setRoot(root);
        this.setPlayer(player);
        this.setSmartMovableEnemies(smartMovableEnemies);
        this.setRandomDirectionMovableEnemies(randomDirectionMovableEnemies);
        this.setTimer(timer);
        this.setHealthbar(healthbar);
        this.setCurrentWeaponDisplay(currentWeaponDisplay);
        this.setHealthPoints(healthPoints);
        this.setScorePoints(scorePoints);
        this.setWeaponTextDisplay(weaponTextDisplay);
        this.setWeaponClipCapacityText(weaponClipCapacityText);
        this.setBonusItemList(bonusItems);
        this.setGuiDrawer(guiDrawer);
        this.setMenuView(new ImageView(ImageLoader.MAIN_MENU_IMAGE));
    }

    private void setWeaponClipCapacityText(WeaponClipCapacityText weaponClipCapacityText) {
        this.weaponClipCapacityText = weaponClipCapacityText;
    }

    private void setWeaponTextDisplay(WeaponTextDisplay weaponTextDisplay) {
        this.weaponTextDisplay = weaponTextDisplay;
    }

    private void setCurrentWeaponDisplay(CurrentWeaponDisplay currentWeaponDisplay) {
        this.currentWeaponDisplay = currentWeaponDisplay;
    }

    public void setScorePoints(ScorePoints scorePoints) {
        this.scorePoints = scorePoints;
    }

    public void setHealthPoints(HealthPoints healthPoints) {
        this.healthPoints = healthPoints;
    }

    public void setHealthbar(HealthBar healthbar) {
        this.healthbar = healthbar;
    }

    public void setBonusItemList(List<BonusImpl> bonusItemList) {
        this.bonusItemList = bonusItemList;
    }

    public Pane getRoot() {
        return this.root;
    }

    public void setRoot(Pane root) {
        this.root = root;
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Set<SmartMovable> getSmartMovableEnemies() {
        return this.smartMovableEnemies;
    }

    public void setSmartMovableEnemies(Set<SmartMovable> smartMovableEnemies) {
        this.smartMovableEnemies = smartMovableEnemies;
    }

    public Set<RandomDirectionMovable> getRandomDirectionMovableEnemies() {
        return this.randomDirectionMovableEnemies;
    }

    private void setRandomDirectionMovableEnemies(Set<RandomDirectionMovable> randomDirectionMovableEnemies) {
        this.randomDirectionMovableEnemies = randomDirectionMovableEnemies;
    }

    public AnimationTimer getTimer() {
        return this.timer;
    }

    public void setTimer(AnimationTimer timer) {
        this.timer = timer;
    }

    public GUIDrawer getGuiDrawer() {
        return this.guiDrawer;
    }

    public void setGuiDrawer(GUIDrawer guiDrawer) {
        this.guiDrawer = guiDrawer;
    }

    private void setMenuView(ImageView menuView) {
        this.menuView = menuView;
    }

    public Parent createContent() {

        this.menuView.setFitWidth(Constants.SCREEN_WIDTH);
        this.menuView.setFitHeight(Constants.SCREEN_HEIGHT);
        this.root.getChildren().add(this.menuView);

        Title title = new Title("S U R V I V Е   S O F T U N I");
        title.setTranslateX(Constants.TITLE_TRANSLATE_X);
        title.setTranslateY(Constants.TITLE_TRANSLATE_Y);

        MainMenu itemStart = new MainMenu("START GAME");
        MainMenu itemExit = new MainMenu("EXIT");

        itemStart.setOnMouseClicked(event -> {
            this.menuView.setVisible(false);
            itemStart.setVisible(false);
            itemExit.setVisible(false);
            title.setVisible(false);

            this.getRoot().setPrefSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
            LevelData leveldata = new LevelDataImpl();

            if (Constants.RANDOMISE_LEVELS) {
                leveldata = generateRandomLevel(leveldata);
            }

            Level.initLevel(leveldata);
            this.getRoot().getChildren().addAll(Level.impassableBlocks);
            this.getRoot().getChildren().addAll(Level.impassableBlockBBoxes);
            this.getRoot().getChildren().addAll(Level.passableBlocks);
            this.getRoot().getChildren().addAll(Level.passableBlockBBoxes);
            this.getRoot().getChildren().addAll(Level.destructibleBlocks);
            this.getRoot().getChildren().addAll(Level.destructibleBlockBBoxes);


            this.getRoot().getChildren().add(this.player);
            this.getRoot().getChildren().add(this.player.getBoundingBox());
            this.getRoot().getChildren().add(this.guiDrawer);

            this.getPlayer().translateXProperty().addListener((obs, old, newValue) -> {
                int offset = newValue.intValue();
                if (offset > Constants.SCREEN_CAMERA_OFFSET && offset < Level.getLevelWidth()) {
                    this.getRoot().setLayoutX(-(offset - Constants.SCREEN_CAMERA_OFFSET));
                }
            });
            this.getPlayer().translateYProperty().addListener((obs, old, newValue) -> {
                int offset = newValue.intValue();
                if (offset > Constants.SCREEN_CAMERA_OFFSET && offset < Level.getLevelHeight() - Constants.SCREEN_CAMERA_OFFSET) {
                    this.getRoot().setLayoutY(-(offset - Constants.SCREEN_CAMERA_OFFSET));
                }
            });

            this.spawnEnemies();

            this.guiDrawer.toFront();
            this.guiDrawer.drawHealthBar();
            this.guiDrawer.drawWeaponBar();
            this.guiDrawer.drawHealthPoints();
            this.guiDrawer.drawScorePoints();
            this.guiDrawer.drawCurrentWeapon();
            this.guiDrawer.drawWeaponText();
            this.guiDrawer.drawWeaponClipCapacity();

            this.getTimer().start();
        });

        itemExit.setOnMouseClicked(event -> System.exit(0));

        MenuBox menu = new MenuBox(
                itemStart,
                itemExit);
        menu.setTranslateX(Constants.MAIN_MENU_TRANSLATE_X);
        menu.setTranslateY(Constants.MAIN_MENU_TRANSLATE_Y);
        this.getRoot().getChildren().addAll(title, menu);

        return this.getRoot();
    }

    public Parent loadNextLevel() {
        this.getRoot().setPrefSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        LevelDataImpl levelData = new LevelDataImpl();

        TerrainGenerator.changeRandomLevelHeight(Constants.RANDOM_LEVEL_SIZE_INCREASE_AMOUNT);
        TerrainGenerator.changeRandomLevelWidth(Constants.RANDOM_LEVEL_SIZE_INCREASE_AMOUNT);
        if (Constants.RANDOMISE_LEVELS) {
            generateRandomLevel(levelData);
        }
        Level.initLevel(levelData);
        this.getRoot().getChildren().addAll(Level.impassableBlocks);
        this.getRoot().getChildren().addAll(Level.impassableBlockBBoxes);
        this.getRoot().getChildren().addAll(Level.passableBlocks);
        this.getRoot().getChildren().addAll(Level.passableBlockBBoxes);
        this.getRoot().getChildren().addAll(Level.destructibleBlocks);
        this.getRoot().getChildren().addAll(Level.destructibleBlockBBoxes);


        this.getRoot().getChildren().add(this.player);
        this.getRoot().getChildren().add(this.player.getBoundingBox());
        this.getRoot().getChildren().add(this.guiDrawer);

        this.getPlayer().translateXProperty().addListener((obs, old, newValue) -> {
            int offset = newValue.intValue();
            if (offset > Constants.SCREEN_CAMERA_OFFSET && offset < Level.getLevelWidth()) {
                this.getRoot().setLayoutX(-(offset - Constants.SCREEN_CAMERA_OFFSET));
            }
        });
        this.getPlayer().translateYProperty().addListener((obs, old, newValue) -> {
            int offset = newValue.intValue();
            if (offset > Constants.SCREEN_CAMERA_OFFSET && offset < Level.getLevelHeight() - Constants.SCREEN_CAMERA_OFFSET) {
                this.getRoot().setLayoutY(-(offset - Constants.SCREEN_CAMERA_OFFSET));
            }
        });

        this.spawnEnemies();

//        this.guiDrawer.drawHealthBar();
//        this.guiDrawer.drawWeaponBar();

        this.getTimer().start();

        return this.getRoot();
    }

    private void spawnEnemies() {
        TerrainGenerator.changeRandomLevelSmartZCount();
        for (int i = 0; i < TerrainGenerator.getRandomLevelSmartZCount(); i++) {
            int[] position = getRandomSpawnPosition();
            SmartZombie smartZombie = new SmartZombie(position[0] * Constants.BLOCK_SIZE, position[1] * Constants.BLOCK_SIZE);
            this.getRoot().getChildren().add(smartZombie);
            this.getSmartMovableEnemies().add(smartZombie);
        }

        TerrainGenerator.changeRandomLevelDumbZCount();
        for (int i = 0; i < TerrainGenerator.getRandomLevelDumbZCount(); i++) {
            int[] position = getRandomSpawnPosition();
            DumbZombie dumbZombie = new DumbZombie(position[0] * Constants.BLOCK_SIZE, position[1] * Constants.BLOCK_SIZE);
            this.getRoot().getChildren().add(dumbZombie);
            this.getRandomDirectionMovableEnemies().add(dumbZombie);
        }

        System.out.println("Dumb:"+ TerrainGenerator.getRandomLevelDumbZCount());
    }

    private int[] getRandomSpawnPosition() {
        Random rand = new Random();
        int[] position = new int[2];
        int x = rand.nextInt(Level.levelBlockWidth);
        int y = rand.nextInt(Level.levelBlockHeight);

        if (Constants.RANDOMISE_LEVELS) {
            while (!TerrainGenerator.getPassableArea().contains(new TerrainGenerator.Tuple<>(x, y))) {
                x = rand.nextInt(Level.levelBlockWidth);
                y = rand.nextInt(Level.levelBlockHeight);
            }
        } else {
            while (Level.levelBlockMatrix[x][y] != 0) {
                x = rand.nextInt(Level.levelBlockWidth);
                y = rand.nextInt(Level.levelBlockHeight);
            }
        }
        position[0] = x;
        position[1] = y;

        return position;
    }

    private LevelData generateRandomLevel(LevelData leveldata) {
        leveldata.clearLevels();
        leveldata.addLevel(TerrainGenerator.generateNewLevel());
        //TODO split this in two functions?
        this.player.setTranslateX(TerrainGenerator.getPlayerStartX() * Constants.BLOCK_SIZE + 1);
        this.player.setTranslateY(TerrainGenerator.getPlayerStartY() * Constants.BLOCK_SIZE + 1);
        this.player.changeBoundingBox(this.player.calcBoundingBox(Constants.PLAYER_SIZE));

        return leveldata;
    }
}