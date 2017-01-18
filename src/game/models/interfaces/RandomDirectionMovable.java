package game.models.interfaces;

public interface RandomDirectionMovable extends Enemy{

    void changeMoveDirection(char moveDirection);

    char getMoveDirection();
}
