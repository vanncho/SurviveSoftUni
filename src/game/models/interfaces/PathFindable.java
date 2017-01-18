package game.models.interfaces;

import game.moveLogic.AStar;

import java.util.Queue;

public interface PathFindable extends Enemy{

    void updatePath(int levelWidth, int levelHeight, int playerX, int playerY, int zombieX, int zombieY, int[][] matrix);

    Queue<AStar.Cell> getPath();
}
