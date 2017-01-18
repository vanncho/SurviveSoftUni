package game.core;

import game.bonusItems.BonusImpl;
import game.bonusItems.enums.BonusType;
import game.interfaces.InputManager;
import game.models.interfaces.Enemy;
import game.staticData.Constants;
import game.gui.GUIDrawer;
import game.level.Level;
import game.level.enums.BlockType;
import game.level.interfaces.Block;
import game.level.interfaces.LevelManageable;
import game.models.interfaces.HumanObject;
import game.models.interfaces.RandomDirectionMovable;
import game.models.interfaces.SmartMovable;
import game.moveLogic.AStar;
import game.moveLogic.Axis;
import game.moveLogic.MoveEnemyManager;
import game.moveLogic.interfaces.Movable;
import game.weapons.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Controller {
    private static final double HEALTH_REDUCTION = 0.3;
    private static Random rand;
    private HumanObject player;
    private List<KeyCode> inputKeyCodes;
    private InputManager inputHandler;
    private Set<SmartMovable> smartMovableEnemies;
    private Set<RandomDirectionMovable> randomDirectionMovableEnemies;
    private Pane root;
    private List<Bullet> bulletList;
    private LevelManageable levelManager;

    // HEALTH POINTS TEST
    private List<BonusImpl> bonusItems;
    private GUIDrawer guiDrawer;

    public Controller(HumanObject player,
                      List<KeyCode> inputKeyCodes,
                      InputManager inputHandler,
                      Set<SmartMovable> smartMovableEnemies,
                      Set<RandomDirectionMovable> randomDirectionMovableEnemies,
                      Pane root,
                      List<Bullet> bulletList,
                      GUIDrawer guiDrawer,
                      List<BonusImpl> bonusItems,
                      LevelManageable levelManager) {
        this.setPlayer(player);
        this.setInputKeyCodes(inputKeyCodes);
        this.setInputHandler(inputHandler);
        this.setSmartMovableEnemies(smartMovableEnemies);
        this.setRandomDirectionMovableEnemies(randomDirectionMovableEnemies);
        this.setRoot(root);
        this.setBulletList(bulletList);
        this.setBonusItems(bonusItems);
        this.setGuiDrawer(guiDrawer);
        this.setLevelManager(levelManager);
        this.rand = new Random();
    }

    private LevelManageable getLevelManager() {
        return this.levelManager;
    }

    private void setLevelManager(LevelManageable levelManager) {
        this.levelManager = levelManager;
    }

    public GUIDrawer getGuiDrawer() {
        return this.guiDrawer;
    }

    public void setGuiDrawer(GUIDrawer guiDrawer) {
        this.guiDrawer = guiDrawer;
    }

    public List<BonusImpl> getBonusItems() {
        return this.bonusItems;
    }

    public void setBonusItems(List<BonusImpl> bonusItems) {
        this.bonusItems = bonusItems;
    }

    public HumanObject getPlayer() {
        return this.player;
    }

    public void setPlayer(HumanObject player) {
        this.player = player;
    }

    public List<KeyCode> getInputKeyCodes() {
        return this.inputKeyCodes;
    }

    public void setInputKeyCodes(List<KeyCode> inputKeyCodes) {
        this.inputKeyCodes = inputKeyCodes;
    }

    public InputManager getInputHandler() {
        return inputHandler;
    }

    private void setInputHandler(InputManager inputHandler) {
        this.inputHandler = inputHandler;
    }

    public Set<SmartMovable> getSmartMovableEnemies() {
        return this.smartMovableEnemies;
    }

    public void setSmartMovableEnemies(Set<SmartMovable> smartMovableEnemies) {
        this.smartMovableEnemies = smartMovableEnemies;
    }

    public Set<RandomDirectionMovable> getRandomDirectionMovableEnemies() {
        return randomDirectionMovableEnemies;
    }

    private void setRandomDirectionMovableEnemies(Set<RandomDirectionMovable> randomDirectionMovableEnemies) {
        this.randomDirectionMovableEnemies = randomDirectionMovableEnemies;
    }

    public Pane getRoot() {
        return this.root;
    }

    public void setRoot(Pane root) {
        this.root = root;
    }

    public List<Bullet> getBulletList() {
        return this.bulletList;
    }

    public void setBulletList(List<Bullet> bulletList) {
        this.bulletList = bulletList;
    }

    public void updatePlayer(Movable movePlayerManager) {
        this.getPlayer().changePosXGrid((int) this.getPlayer().localToParent(this.getPlayer().getBoundsInLocal()).getMinX() / Constants.BLOCK_SIZE);
        this.getPlayer().changePosYGrid((int) this.getPlayer().localToParent(this.getPlayer().getBoundsInLocal()).getMinY() / Constants.BLOCK_SIZE);

        for (KeyCode kc : this.getInputKeyCodes()) {
            this.getInputHandler().handleInput(kc);
        }

        for (BonusImpl bonusItem : bonusItems) {
            Shape intersect = Shape.intersect(this.player.getBoundingBox(), bonusItem.getBoundingBox());
            if (intersect.getBoundsInLocal().getWidth() != -1) {
                this.updateBonusItems(bonusItem);
                break;
            }
        }
    }

    public void updateSmartEnemies() {

        for (SmartMovable smartMovableEnemy : this.getSmartMovableEnemies()) {
            if (this.getPlayer().getBoundingBox().getBoundsInParent().intersects(smartMovableEnemy.getBoundingBox().getBoundsInParent())) {
                this.getPlayer().changeHealth(this.getPlayer().getHealth() - HEALTH_REDUCTION);
                break;
            }
        }

        ArrayList<SmartMovable> enemiesToRemove = new ArrayList<>();
        for (SmartMovable smartMovableEnemy : this.getSmartMovableEnemies()) {

            MoveEnemyManager moveZombieManager = new MoveEnemyManager(smartMovableEnemy);

            if (smartMovableEnemy.getHealth() <= 0) {
                enemiesToRemove.add(smartMovableEnemy);
                continue;
            }

            //updates zombie position
            smartMovableEnemy.changePosXPixel((int) smartMovableEnemy.localToParent(smartMovableEnemy.getBoundsInLocal()).getMinX());
            smartMovableEnemy.changePosXGrid(smartMovableEnemy.getPosXReal() / Constants.BLOCK_SIZE);
            smartMovableEnemy.changePosYPixel((int) smartMovableEnemy.localToParent(smartMovableEnemy.getBoundsInLocal()).getMinY());
            smartMovableEnemy.changePosYGrid(smartMovableEnemy.getPosYReal() / Constants.BLOCK_SIZE);

            //find shortest path to player
            smartMovableEnemy.updatePath(Level.levelBlockWidth,
                    Level.levelBlockHeight,
                    this.player.getPosX(),
                    this.player.getPosY(),
                    smartMovableEnemy.getPosX(),
                    smartMovableEnemy.getPosY(),
                    Level.levelBlockMatrix);

            if (smartMovableEnemy.getPath().isEmpty()) {
                this.moveInRandomDirection(smartMovableEnemy, moveZombieManager);
            } else {
                //first node is current position. If npc is to move it needs the next node.
                AStar.Cell nextNode = smartMovableEnemy.getPath().poll();


                if (!smartMovableEnemy.getAllowNextCellMove()) {
                    moveZombieManager.centerZombie();
                    continue;
                }

                if (!moveZombieManager.isInSameCell()) {
                    smartMovableEnemy.changeAllowNextCellMove(false);
                }

                if (smartMovableEnemy.getPath().isEmpty()) {
                    continue;
                }

                nextNode = smartMovableEnemy.getPath().poll();

                if (nextNode.getX() < smartMovableEnemy.getPosX()) {
                    moveZombieManager.move(-Constants.ZOMBIE_VELOCITY, Axis.X);
                    smartMovableEnemy.getAnimation().play();
                    smartMovableEnemy.getAnimation().setOffsetY(2 * 64);
                } else if (nextNode.getX() > smartMovableEnemy.getPosX()) {
                    moveZombieManager.move(Constants.ZOMBIE_VELOCITY, Axis.X);
                    smartMovableEnemy.getAnimation().play();
                    smartMovableEnemy.getAnimation().setOffsetY(64);
                } else if (nextNode.getY() < smartMovableEnemy.getPosY()) {
                    moveZombieManager.move(-Constants.ZOMBIE_VELOCITY, Axis.Y);
                    smartMovableEnemy.getAnimation().play();
                    smartMovableEnemy.getAnimation().setOffsetY(0);
                } else if (nextNode.getY() > smartMovableEnemy.getPosY()) {
                    moveZombieManager.move(Constants.ZOMBIE_VELOCITY, Axis.Y);
                    smartMovableEnemy.getAnimation().play();
                    smartMovableEnemy.getAnimation().setOffsetY(3 * 64);
                }
            }
        }

        for (SmartMovable smartMovableEnemy : enemiesToRemove) {
            //IB Threshold set to 0.8 for testing purpose only!
            if (Math.random() < Constants.RANDOM_DROP_THRESHOLD) {
                this.addBonusItem(smartMovableEnemy.getPosXReal(), smartMovableEnemy.getPosYReal());
            }

            this.smartMovableEnemies.remove(smartMovableEnemy);
            this.getRoot().getChildren().remove(smartMovableEnemy);

            this.player.changeScore(this.player.getScore() + 1);
        }
    }

    public void updateRandomMovableEnemies() {

        for (RandomDirectionMovable randomDirectionMovableEnemy : this.getRandomDirectionMovableEnemies()) {
            if (this.getPlayer().getBoundingBox().getBoundsInParent().intersects(randomDirectionMovableEnemy.getBoundingBox().getBoundsInParent())) {
                this.getPlayer().changeHealth(this.getPlayer().getHealth() - HEALTH_REDUCTION);
                break;
            }
        }

        ArrayList<RandomDirectionMovable> enemiesToRemove = new ArrayList<>();
        for (RandomDirectionMovable randomDirectionMovableEnemy : this.getRandomDirectionMovableEnemies()) {

            MoveEnemyManager moveZombieManager = new MoveEnemyManager(randomDirectionMovableEnemy);

            if (randomDirectionMovableEnemy.getHealth() <= 0) {
                enemiesToRemove.add(randomDirectionMovableEnemy);
                continue;
            }

            //updates zombie position
            randomDirectionMovableEnemy.changePosXPixel((int) randomDirectionMovableEnemy.localToParent(randomDirectionMovableEnemy.getBoundsInLocal()).getMinX());
            randomDirectionMovableEnemy.changePosXGrid(randomDirectionMovableEnemy.getPosXReal() / Constants.BLOCK_SIZE);
            randomDirectionMovableEnemy.changePosYPixel((int) randomDirectionMovableEnemy.localToParent(randomDirectionMovableEnemy.getBoundsInLocal()).getMinY());
            randomDirectionMovableEnemy.changePosYGrid(randomDirectionMovableEnemy.getPosYReal() / Constants.BLOCK_SIZE);

            this.moveInRandomDirection(randomDirectionMovableEnemy, moveZombieManager);
        }

        for (RandomDirectionMovable randomDirectionMovableEnemy : enemiesToRemove) {
            //IB Threshold set to 0.8 for testing purpose only!
            if (Math.random() < Constants.RANDOM_DROP_THRESHOLD) {
                this.addBonusItem(randomDirectionMovableEnemy.getPosXReal(), randomDirectionMovableEnemy.getPosYReal());
            }

            this.getRandomDirectionMovableEnemies().remove(randomDirectionMovableEnemy);
            this.getRoot().getChildren().remove(randomDirectionMovableEnemy);

            this.player.changeScore(this.player.getScore() + 1);
        }
    }

    public void updateBullets() {
        this.getPlayer().changeCanShootTimer(this.getPlayer().getCanShootTimer() + 1);
        if (this.getPlayer().getCanShootTimer() > this.player.getCurrentWeapon().getWeaponType().getShootDelayTime()) {
            this.getPlayer().changeCanShoot(true);
            this.getPlayer().changeCanShootTimer(0);
        }

        if (this.getPlayer().getIsShooting()) {
            this.getBulletList().forEach(Bullet::move);
        }

        List<Bullet> bulletsToRemove = new ArrayList<>();
        List<Block> wallsToRemove = new ArrayList<>();
        for (Bullet bullet : this.getBulletList()) {
            boolean bulletRemoved = false;
            for (SmartMovable smartMovableEnemy : this.getSmartMovableEnemies()) {
                if (tryHitEnemy(bullet, smartMovableEnemy, bulletsToRemove)) {
                    bulletRemoved = true;
                    break;
                }
            }
            if (bulletRemoved) {
                continue;
            }

            for (RandomDirectionMovable randomDirectionMovableEnemy : this.getRandomDirectionMovableEnemies()) {
                if (tryHitEnemy(bullet, randomDirectionMovableEnemy, bulletsToRemove)) {
                    bulletRemoved = true;
                    break;
                }
            }

            if (bulletRemoved) {
                continue;
            }

            for (Block wall : Level.impassableBlocks) {
                if (bullet.getBoundsInParent().intersects(wall.getBoundsInParent())) {
                    this.getRoot().getChildren().remove(bullet);
                    bulletsToRemove.add(bullet);
                    bulletRemoved = true;
                    break;
                }
            }

            if (bulletRemoved) {
                continue;
            }

            for (Block wall : Level.destructibleBlocks)
                if (bullet.getBoundsInParent().intersects(wall.getBoundsInParent())) {
                    this.getRoot().getChildren().remove(bullet);
                    bulletsToRemove.add(bullet);
                    bulletRemoved = true;

                    if (wall.getBlockType() == BlockType.BRICK &&
                            wall.getOpacity() - Constants.BLOCK_OPACITY_DAMAGE <= 0) {
                        this.getRoot().getChildren().remove(wall.getBlockBBox());
                        this.getRoot().getChildren().remove(wall);
                        wallsToRemove.add(wall);
                        Level.levelBlockMatrix[(int) wall.getTranslateX() / Constants.BLOCK_SIZE][(int) wall.getTranslateY() / Constants.BLOCK_SIZE] = 0;
                        break;
                    } else if (wall.getBlockType() == BlockType.BRICK) {
                        wall.setOpacity(wall.getOpacity() - Constants.BLOCK_OPACITY_DAMAGE);
                    }
                    break;
                }
        }

        for (Bullet bullet : bulletsToRemove) {
            this.bulletList.remove(bullet);
        }

        for (Block wall : wallsToRemove) {
            int index = Level.destructibleBlocks.indexOf(wall);
            Level.destructibleBlocks.remove(wall);
            if (index >= 0) {
                Level.destructibleBlockBBoxes.remove(index);
            }
        }
    }

    private boolean tryHitEnemy(Bullet bullet, Enemy enemy, List<Bullet> bulletsToRemove) {
        if (bullet.getBoundsInParent().intersects(enemy.getBoundsInParent()) &&
                enemy.getHealth() > 0) {
            this.getRoot().getChildren().remove(bullet);
            bulletsToRemove.add(bullet);

            int damage = bullet.calculateDamage();
            // System.out.println(damage);
            enemy.changeDealDamage(damage);
            return true;
        }
        return false;
    }


    public void updateHealthBar() {
        Rectangle imageCutter = new Rectangle((int) ((this.getPlayer().getHealth() /
                this.getGuiDrawer().getHealthBar().getInitialHealth()) * 190), 50);
        this.getGuiDrawer().getHealthBarImage().setClip(imageCutter);
        this.guiDrawer.setLayoutX(0 - this.root.getLayoutX());
        this.guiDrawer.setLayoutY(0 - this.root.getLayoutY());
    }

    public void updateHealthPoints() {
        this.getGuiDrawer().getHealthPoints().changeHealthPoints((int) this.player.getHealth());
    }

    public void updateScorePoints() {
        this.getGuiDrawer().getScorePoints().changeScorePoints(this.player.getScore());
    }

    public void updateWeaponDisplayText() {
        this.getGuiDrawer().getWeaponTextDisplay().changeWeaponDisplayText(this.player.getCurrentWeapon().getWeaponType().name());
    }

    public void updateWeaponClipCapacityDisplay() {
        this.guiDrawer.getWeaponClipCapacityText().changeClipCapacity(this.player.getCurrentWeapon().getBulletsInClip(), this.player.getCurrentWeapon().getTotalBullets());
    }

    private void addBonusItem(int posXReal, int posYReal) {
        BonusType bonusType = BonusType.values[rand.nextInt(BonusType.values.length)];
        BonusImpl bonusItem = null;
        try {
            Class bonusClass = bonusType.getBonusClass();
            Constructor bonusCtor = bonusClass.getConstructor(int.class, int.class);
            bonusItem = (BonusImpl) bonusCtor.newInstance(posXReal, posYReal);
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
        this.bonusItems.add(bonusItem);
        this.getRoot().getChildren().add(bonusItem);
    }

    private void updateBonusItems(BonusImpl bonusItem) {
        this.bonusItems.remove(bonusItem);
        this.getRoot().getChildren().remove(bonusItem);
        switch (bonusItem.getBonusType()) {
            case HEART:
                this.player.addBonusHealth(Constants.BONUS_HEART_HEAL_AMOUNT);
                break;
            case PISTOL:
                this.player.addWeapon(new Pistol());
                break;
            case MACHINE_GUN:
                this.player.addWeapon(new MachineGun());
                break;
            case SHOTGUN:
                this.player.addWeapon(new Shotgun());
                break;
        }
//        if (bonusItem.getBonusType() == BonusType.HEART) {
//            this.player.addBonusHealth();
//        }
    }

    private void moveInRandomDirection(RandomDirectionMovable enemy, MoveEnemyManager moveZombieManager) {
        if (enemy.getIsInCollision()) {
            int pos = rand.nextInt(Constants.ENEMY_DIRECTIONS.length);
            enemy.changeMoveDirection(Constants.ENEMY_DIRECTIONS[pos]);
        }
        if (rand.nextInt(1000) < 5) {
            int pos = rand.nextInt(Constants.ENEMY_DIRECTIONS.length);
            enemy.changeMoveDirection(Constants.ENEMY_DIRECTIONS[pos]);
        }
        switch (enemy.getMoveDirection()) {
            case 'U':
                moveZombieManager.move(-Constants.ZOMBIE_VELOCITY, Axis.Y);
                enemy.getAnimation().play();
                enemy.getAnimation().setOffsetY(0);
                break;
            case 'D':
                moveZombieManager.move(Constants.ZOMBIE_VELOCITY, Axis.Y);
                enemy.getAnimation().play();
                enemy.getAnimation().setOffsetY(3 * 64);
                break;
            case 'L':
                moveZombieManager.move(-Constants.ZOMBIE_VELOCITY, Axis.X);
                enemy.getAnimation().play();
                enemy.getAnimation().setOffsetY(2 * 64);
                break;
            case 'R':
                moveZombieManager.move(Constants.ZOMBIE_VELOCITY, Axis.X);
                enemy.getAnimation().play();
                enemy.getAnimation().setOffsetY(64);
                break;
        }
    }
}