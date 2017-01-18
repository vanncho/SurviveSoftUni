package game.models;

import game.staticData.Constants;
import game.models.interfaces.RandomDirectionMovable;
import game.sprites.ImageLoader;
import game.sprites.SpriteAnimation;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class DumbZombie extends EnemyImpl implements RandomDirectionMovable {
    private final int SPRITE_COUNT = 4;
    private final int SPRITE_COLUMNS = 4;
    private final int SPRITE_OFFSET_X = 0;
    private final int SPRITE_OFFSET_Y = 0;
    private final int SPRITE_WIDTH = 64;
    private final int SPRITE_HEIGHT = 64;

    private char moveDirection; // For use of randomised movement

    public DumbZombie(int setTranslateX, int setTranslateY) {
        super(setTranslateX, setTranslateY);

        //Image info
        this.setSpriteCount(SPRITE_COUNT);
        this.setSpriteColumns(SPRITE_COLUMNS);
        this.setSpriteOffsetX(SPRITE_OFFSET_X);
        this.setSpriteOffsetY(SPRITE_OFFSET_Y);
        this.setSpriteWidth(SPRITE_WIDTH);
        this.setSpriteHeight(SPRITE_HEIGHT);

        this.setObjectSize(Constants.ZOMBIE_SIZE);

        this.setEnemyImageView(new ImageView(ImageLoader.DUMB_ZOMBIE_IMAGE));
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
    }

    @Override
    public char getMoveDirection() {
        return this.moveDirection;
    }

    private void setMoveDirection(char moveDirection) {
        this.moveDirection = moveDirection;
    }

    @Override
    public void changeMoveDirection(char moveDirection) {
        this.setMoveDirection(moveDirection);
    }
}
