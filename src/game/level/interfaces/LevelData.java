package game.level.interfaces;

import java.util.List;

public interface LevelData {

    List<String[]> getLevels();

    void clearLevels();

    void addLevel(String[] level);

}
