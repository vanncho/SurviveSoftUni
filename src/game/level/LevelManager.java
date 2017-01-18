package game.level;

import game.core.Content;
import game.level.interfaces.LevelManageable;
import javafx.stage.Stage;

public class LevelManager implements LevelManageable {

    private Content content;
    private Stage stage;

    @Override
    public void changeLevel() {

        this.clearLevelData();

        this.getContent().loadNextLevel();
        this.getContent().getRoot().setLayoutX(0);
        this.getContent().getRoot().setLayoutY(0);
        this.getStage().show();
    }

    @Override
    public void setConfigurables(Content content, Stage stage) {
        this.setContent(content);
        this.setStage(stage);
    }

    private Stage getStage() {
        return this.stage;
    }

    private void setStage(Stage stage) {
        this.stage = stage;
    }

    private Content getContent() {
        return this.content;
    }

    private void setContent(Content content) {
        this.content = content;
    }

    private void clearLevelData() {

        Level.impassableBlocks.clear();
        Level.impassableBlockBBoxes.clear();
        Level.passableBlocks.clear();
        Level.passableBlockBBoxes.clear();
        Level.destructibleBlocks.clear();
        Level.destructibleBlockBBoxes.clear();

        this.getContent().getRoot().getChildren().clear();
    }
}
