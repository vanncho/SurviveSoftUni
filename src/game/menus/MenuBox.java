package game.menus;

import game.staticData.Constants;
import javafx.scene.layout.VBox;

public class MenuBox extends VBox {

    public MenuBox(MainMenu... items) {
        setSpacing(Constants.SPACING_SIZE);

        for (MainMenu item : items) {
            getChildren().addAll(item);
        }
    }
}
