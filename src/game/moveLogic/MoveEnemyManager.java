package game.moveLogic;

import game.staticData.Constants;
import game.models.interfaces.Enemy;

public class MoveEnemyManager extends MoveManager {
    private Enemy enemy;

    public MoveEnemyManager(Enemy enemy) {
        super(enemy);
        this.enemy = enemy;
    }

    public boolean isInSameCell() {
        boolean isInSameCell = (this.enemy.getPosX() == this.enemy.getCurrentCellRow() &&
                this.enemy.getPosY() == this.enemy.getCurrentCellCol());
        this.enemy.changeCurrentCellRow(this.enemy.getPosX());
        this.enemy.changeCurrentCellCol(this.enemy.getPosY());

        return isInSameCell;
    }

    public void centerZombie() {
        if (this.enemy.getPosXReal() <= (this.enemy.getPosX() * Constants.BLOCK_SIZE + 1)) {

            move(Constants.ZOMBIE_VELOCITY,Axis.X);
            this.enemy.isCentered(false);
        } else if (this.enemy.getPosXReal() >= (this.enemy.getPosX() * Constants.BLOCK_SIZE + 7)) {
            move(-Constants.ZOMBIE_VELOCITY,Axis.X);
            this.enemy.isCentered(false);
        } else if (this.enemy.getPosYReal() <= (this.enemy.getPosY() * Constants.BLOCK_SIZE + 1)) {
            move(Constants.ZOMBIE_VELOCITY,Axis.Y);
            this.enemy.isCentered(false);
        } else if (this.enemy.getPosYReal() >= (this.enemy.getPosY() * Constants.BLOCK_SIZE + 7)) {
            move(-Constants.ZOMBIE_VELOCITY,Axis.Y);
            this.enemy.isCentered(false);

        } else {
            this.enemy.isCentered(true);
            this.enemy.changeAllowNextCellMove(true);
        }
    }
}
