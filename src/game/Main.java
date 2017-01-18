package game;

import game.bonusItems.BonusImpl;
import game.core.Content;
import game.core.Controller;
import game.core.InputHandler;
import game.gui.*;
import game.interfaces.InputManager;
import game.level.Level;
import game.level.LevelManager;
import game.level.interfaces.LevelManageable;
import game.menus.GameOver;
import game.models.Player;
import game.models.interfaces.RandomDirectionMovable;
import game.models.interfaces.SmartMovable;
import game.moveLogic.interfaces.Movable;
import game.moveLogic.MovePlayerManager;
import game.staticData.Constants;
import game.weapons.Bullet;
import game.weapons.WeaponType;
import game.weapons.factory.BulletFactory;
import game.weapons.factory.BulletFactoryImpl;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.*;

public class Main extends Application {

    public Pane root = new Pane();
    public static Set<SmartMovable> smartMovableEnemies = new LinkedHashSet<>();
    public static Set<RandomDirectionMovable> randomDirectionMovableEnemies = new LinkedHashSet<>();
    public Player player = new Player(270, 270);
    Movable movePlayerManager = new MovePlayerManager(player);
    public List<KeyCode> inputKeyCodes = new ArrayList<>();
    public List<Bullet> bulletList = new ArrayList<>();

    public HealthBar healthbar = new HealthBar(this.player.getHealth(), 20, Constants.DISPLAY_HEIGHT - 80, 190, 50);
    public WeaponBar weaponBar = new WeaponBar(this.player.getScore(), Constants.DISPLAY_WIDTH - 290, Constants.DISPLAY_HEIGHT - 80, 190, 50);
    public CurrentWeaponDisplay currentWeaponDisplay = new CurrentWeaponDisplay(Constants.DISPLAY_WIDTH - 100, Constants.DISPLAY_HEIGHT - 80, 100, 51);
    public HealthPoints healthPoints = new HealthPoints((int) this.player.getHealth());
    public ScorePoints scorePoints = new ScorePoints(this.player.getScore());
    public WeaponTextDisplay weaponTextDisplay = new WeaponTextDisplay(this.player.getCurrentWeapon().getWeaponType().getWeaponName());
    public WeaponClipCapacityText weaponClipCapacityText = new WeaponClipCapacityText(this.player.getCurrentWeapon().getBulletsInClip());

    public List<BonusImpl> bonusItems = new ArrayList<>();
    public GUIDrawer guiDrawer = new GUIDrawer(healthbar, weaponBar, healthPoints, scorePoints, currentWeaponDisplay, weaponTextDisplay, weaponClipCapacityText);
    public LevelManageable levelManager = new LevelManager();
    InputManager inputManager = new InputHandler(player, movePlayerManager, guiDrawer);

    public Controller controller = new Controller(
            player,
            inputKeyCodes,
            inputManager,
            smartMovableEnemies,
            randomDirectionMovableEnemies,
            root,
            bulletList,
            guiDrawer,
            bonusItems,
            levelManager);

    public AnimationTimer timer = new AnimationTimer() {
        Movable movePlayerManager = new MovePlayerManager(player);

        @Override
        public void handle(long now) {
            doHandle();
        }

        private void doHandle() {

            if (player.getHealth() <= 0) {
                stop();
                gameOver();
            }
            if (Level.getShouldChangeLevel()) {
                levelManager.changeLevel();
                Level.setShouldChangeLevel(false);
            }
            controller.updateBullets();
            controller.updatePlayer(this.movePlayerManager);
            controller.updateSmartEnemies();
            controller.updateRandomMovableEnemies();
            controller.updateHealthBar();
            controller.updateHealthPoints();
            controller.updateScorePoints();
            controller.updateWeaponDisplayText();
            controller.updateWeaponClipCapacityDisplay();
        }
    };

    public Content content = new Content(root, player, smartMovableEnemies, randomDirectionMovableEnemies, timer, healthbar, currentWeaponDisplay, healthPoints, scorePoints, weaponTextDisplay, weaponClipCapacityText, bonusItems, guiDrawer);

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(this.content.createContent()));

        this.levelManager.setConfigurables(this.content, stage);


        stage.getScene().setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();
            if (!this.inputKeyCodes.contains(keyCode)) {
                this.inputKeyCodes.add(keyCode);
            }
        });

        stage.getScene().setOnKeyReleased(event -> {
            KeyCode keyCode = event.getCode();
            this.inputKeyCodes.remove(keyCode);
        });

        stage.getScene().setCursor(Cursor.CROSSHAIR);
        //TODO enable when collisions are fully fixed.
//        stage.getScene().setOnMouseMoved(e -> {
//            double mouseX = e.getSceneX();
//            double mouseY = e.getSceneY();
//            double xDistance = mouseX - (player.getTranslateX() + 60);
//            double yDistance = mouseY - (player.getTranslateY() + 60);
//            double rotationAngle = Math.toDegrees(Math.atan2(yDistance, xDistance));
//            player.setRotate(rotationAngle);
//        });

        stage.getScene().setOnMouseClicked(event -> {
            MouseButton clickedButton = event.getButton();
            if (!clickedButton.equals(MouseButton.PRIMARY)) {
                return;
            }

            BulletFactory bulletFactory = new BulletFactoryImpl();
            double mousePosX = event.getX() - this.root.getLayoutX();
            double mousePosY = event.getY() - this.root.getLayoutY();

            if (this.player.getCanShoot()) {
                this.player.changeCanShoot(false);
               // System.out.println(String.format("%d/%d", this.player.getCurrentWeapon().getBulletsInClip(), this.player.getCurrentWeapon().getTotalBullets()));

                WeaponType playerCurrentWeapon = this.player.getCurrentWeapon().getWeaponType();
                if (!this.player.getCurrentWeapon().shoot()) {
                    return;
                }

                ArrayList<Bullet> newBullets = bulletFactory.createBullets(this.player.getCurrentWeapon().getWeaponType(),
                        this.player,
                        mousePosX,
                        mousePosY);

                for (Bullet newBullet : newBullets) {
                    this.root.getChildren().add(newBullet);
                    this.bulletList.add(newBullet);
                }
                this.player.isShooting(true);
            }
        });

        stage.show();
    }

    private void gameOver() {
        root.getChildren().addAll(GameOver.gameOverTitle(this.root.getLayoutX(), this.root.getLayoutY()));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
