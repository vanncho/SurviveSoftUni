package game.menus;

import game.staticData.Constants;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Title extends StackPane {

    public Title(String name) {
        Rectangle rect = new Rectangle(Constants.TITLE_RECTANGLE_WIDTH, Constants.TITLE_RECTANGLE_HEIGHT);
        rect.setStroke(Color.WHITE);
        rect.setStrokeWidth(Constants.TITLE_RECTANGLE_BORDER_WIDTH);
        rect.setFill(null);

        Text text = new Text(name);
        text.setFill(Color.WHITE);
        text.setFont(Font.font(Constants.TITLE_TEXT_FONT_TYPE, FontWeight.EXTRA_BOLD, Constants.TITLE_TEXT_FONT_SIZE));

        setAlignment(Pos.CENTER);
        getChildren().addAll(rect, text);
    }
}
