package game.menus;

import game.staticData.Constants;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class MainMenu extends StackPane {

    public MainMenu(String name) {
        LinearGradient gradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, new Stop(0, Color.DARKVIOLET),
                new Stop(0.1, Color.BLACK),
                new Stop(0.9, Color.BLACK),
                new Stop(1, Color.DARKVIOLET));

        Rectangle rect = new Rectangle(Constants.MAIN_MENU_RECTANGLE_WIDTH, Constants.MAIN_MENU_RECTANGLE_HEIGHT);
        rect.setOpacity(Constants.MAIN_MENU_RECTANGLE_OPACITY);

        Text text = new Text(name);
        text.setFill(Color.DARKGRAY);
        text.setFont(Font.font(Constants.MAIN_MENU_TEXT_FONT_TYPE, FontWeight.SEMI_BOLD, Constants.MAIN_MENU_TEXT_FONT_SIZE));

        setAlignment(Pos.CENTER);
        getChildren().addAll(rect, text);

        setOnMouseEntered(event -> {
            rect.setFill(gradient);
            text.setFill(Color.WHITE);
        });

        setOnMouseExited(event -> {
            rect.setFill(Color.BLACK);
            text.setFill(Color.DARKGRAY);
        });

        setOnMousePressed(event -> rect.setFill(Color.DARKVIOLET));

        setOnMouseReleased(event -> rect.setFill(gradient));
    }
}
