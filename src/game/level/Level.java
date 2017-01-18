package game.level;

import game.staticData.Constants;
import game.level.enums.BlockType;
import game.level.enums.DestructionType;
import game.level.interfaces.LevelData;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.List;

public class Level extends Pane {

    private static int levelNumber = 0;
    private static boolean shouldChangeLevel = false;

    private static int levelWidth;
    private static int levelHeight;
    public static int levelBlockWidth;
    public static int levelBlockHeight;
    public static int[][] levelBlockMatrix;
    public static List<BlockImpl> impassableBlocks = new ArrayList<>();
    public static List<Shape> impassableBlockBBoxes = new ArrayList<>();
    public static List<BlockImpl> destructibleBlocks = new ArrayList<>();
    public static List<Shape> destructibleBlockBBoxes = new ArrayList<>();
    public static List<BlockImpl> passableBlocks = new ArrayList<>();
    public static List<Shape> passableBlockBBoxes = new ArrayList<>();

    public static int getLevelWidth() {
        return levelWidth;
    }

    public static int getLevelHeight() {
        return levelHeight;
    }

    public static boolean getShouldChangeLevel() {
        return shouldChangeLevel;
    }

    public static void setShouldChangeLevel(boolean shouldChangeLevel) {
        Level.shouldChangeLevel = shouldChangeLevel;
    }

    public static void initLevel(LevelData newLevelData) {
        LevelData levelData = newLevelData;

        levelWidth = levelData.getLevels().get(levelNumber)[0].split("\\s+").length * Constants.BLOCK_SIZE;
        levelHeight = levelData.getLevels().get(levelNumber).length * Constants.BLOCK_SIZE;
        levelBlockWidth = levelData.getLevels().get(levelNumber)[0].split("\\s+").length;
        levelBlockHeight = levelData.getLevels().get(levelNumber).length;

        System.out.println(levelBlockHeight + " " + levelBlockWidth);
        System.out.println(levelHeight + " " + levelWidth);
        levelBlockMatrix = new int[levelBlockWidth][levelBlockHeight];


        for (int i = 0; i < levelData.getLevels().get(levelNumber).length; i++) {
            String line[] = levelData.getLevels().get(levelNumber)[i].split("\\s+");
            for (int j = 0; j < line.length; j++) {
                levelBlockMatrix[j][i] = Integer.parseInt(line[j]);

                int id = Integer.valueOf(line[j]);
                BlockType newBlockType = null;

                if (id == 1 || id == 0) {
                    continue;
                }
                //TODO possible minor bottleneck? Slower than a switch
                for (BlockType blockType : BlockType.values()) {
                    if (blockType.getId() == id) {
                        newBlockType = blockType;
                        break;
                    }
                }

                BlockImpl block = new BlockImpl(newBlockType, j * Constants.BLOCK_SIZE, i * Constants.BLOCK_SIZE);
                if (block.getBlockType().getDestructionType() == DestructionType.IMPASSABLE) {
                    impassableBlocks.add(block);
                    impassableBlockBBoxes.add(block.getBlockBBox());
                } else if (block.getBlockType().getDestructionType() == DestructionType.PASSABLE) {
                    passableBlocks.add(block);
                    passableBlockBBoxes.add(block.getBlockBBox());
                } else {
                    destructibleBlocks.add(block);
                    destructibleBlockBBoxes.add(block.getBlockBBox());
                }
            }
        }
    }
}

