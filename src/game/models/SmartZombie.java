package game.models;

import game.models.interfaces.SmartMovable;
import game.moveLogic.AStar;
import game.staticData.Constants;
import game.sprites.ImageLoader;
import game.sprites.SpriteAnimation;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

public class SmartZombie extends EnemyImpl implements SmartMovable{
    private final int SPRITE_COUNT = 4;
    private final int SPRITE_COLUMNS = 4;
    private final int SPRITE_OFFSET_X = 0;
    private final int SPRITE_OFFSET_Y = 0;
    private final int SPRITE_WIDTH = 64;
    private final int SPRITE_HEIGHT = 64;

    private Queue<AStar.Cell> path;
    private char moveDirection; // For use of randomised movement

    public SmartZombie(int setTranslateX, int setTranslateY) {
        super(setTranslateX, setTranslateY);

        //Image info
        this.setSpriteCount(SPRITE_COUNT);
        this.setSpriteColumns(SPRITE_COLUMNS);
        this.setSpriteOffsetX(SPRITE_OFFSET_X);
        this.setSpriteOffsetY(SPRITE_OFFSET_Y);
        this.setSpriteWidth(SPRITE_WIDTH);
        this.setSpriteHeight(SPRITE_HEIGHT);

        this.setObjectSize(Constants.ZOMBIE_SIZE);

        this.setEnemyImageView(new ImageView(ImageLoader.ZOMBIE_IMAGE));
        this.getEnemyImageView().setFitHeight(Constants.ZOMBIE_SIZE);
        this.getEnemyImageView().setFitWidth(Constants.ZOMBIE_SIZE);

        this.getEnemyImageView().setViewport(new Rectangle2D(this.getSpriteOffsetX(), this.getSpriteOffsetY(), this.getSpriteWidth(), this.getSpriteHeight()));
        this.setAnimation(new SpriteAnimation(this.getEnemyImageView(),
                Duration.millis(1000),
                this.getSpriteCount(),
                this.getSpriteColumns(),
                this.getSpriteOffsetX(),
                this.getSpriteOffsetY(),
                this.getSpriteWidth(),
                this.getSpriteHeight()));
        getChildren().addAll(this.getEnemyImageView());

        this.setBoundingBox(calcBoundingBox(Constants.ZOMBIE_SIZE));

        this.setHealth(Constants.ZOMBIE_HEALTH);

        //Regular cell to cell movement
        this.setIsCentered(false);
        this.setAllowNextCellMove(false);

        //Random movement
        this.setMoveDirection('U');

        //A*
        this.setPath(new LinkedBlockingDeque<>());
    }

    public void updatePath(int levelWidth, int levelHeight, int playerX, int playerY, int zombieX, int zombieY, int[][] matrix) {
        this.path = AStar.findPath(levelWidth, levelHeight, playerX, playerY, zombieX, zombieY, matrix);
    }

    public Queue<AStar.Cell> getPath() {
        return this.path;
    }

    private void setPath(Queue<AStar.Cell> path) {
        this.path = path;
    }

    public char getMoveDirection() {
        return this.moveDirection;
    }

    private void setMoveDirection(char moveDirection) {
        this.moveDirection = moveDirection;
    }

    public void changePath(Queue<AStar.Cell> path){
        this.setPath(path);
    }

    public void changeMoveDirection(char moveDirection){
        this.setMoveDirection(moveDirection);
    }

}
