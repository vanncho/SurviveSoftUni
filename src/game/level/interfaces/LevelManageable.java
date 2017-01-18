package game.level.interfaces;

import game.core.Content;
import javafx.stage.Stage;

public interface LevelManageable {

    void changeLevel();

    void setConfigurables(Content content, Stage stage);
}
