package game.level.interfaces;

import game.interfaces.JavaFX;
import game.level.enums.BlockType;
import javafx.scene.shape.Shape;

public interface Block extends JavaFX {

    Shape getBlockBBox();

    BlockType getBlockType();
}
