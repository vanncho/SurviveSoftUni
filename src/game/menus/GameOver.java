package game.menus;

import game.staticData.Constants;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class GameOver extends StackPane {


    public GameOver(String name) {
        Rectangle rect = new Rectangle(Constants.GAME_OVER_RECTANGLE_WIDTH, Constants.GAME_OVER_RECTANGLE_HEIGHT);
        rect.setStroke(Color.WHITE);
        rect.setStrokeWidth(Constants.GAME_OVER_RECTANGLE_BORDER_WIDTH);
        rect.setFill(Color.BLACK);

        Text text = new Text(name);
        text.setFill(Color.WHITE);
        text.setFont(Font.font(Constants.GAME_OVER_TEXT_FONT_TYPE, FontWeight.EXTRA_BOLD, Constants.GAME_OVER_TEXT_FONT_SIZE));

        setAlignment(Pos.CENTER);
        getChildren().addAll(rect, text);
    }

    public static GameOver gameOverTitle(double offsetX,double offsetY){
        GameOver title = new GameOver("G A M E  O V E R");
        title.setTranslateX(200 - offsetX);
        title.setTranslateY(280 - offsetY);
        return title;
    }

}
