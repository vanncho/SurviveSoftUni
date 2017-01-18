package game.gui;

import game.staticData.Constants;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class HealthPoints extends Pane {
    private Text text;
    private String healthText;

    public HealthPoints(int health) {
        this.setText("Health: " + health);
    }

    public void changeHealthPoints(int newHealth) {
        this.healthText = String.format("HEALTH:  %d", newHealth);
        this.setText(this.healthText);
    }

    private void setText(String setHealth) {
        this.text = new Text(setHealth);
        this.text.setFont(Font.font(Constants.DEFAULT_BARS_FONT_STYLE, FontWeight.BOLD, Constants.GUI_DEFAULT_FONT_SIZE));
        this.text.setFill(Color.WHITE);
        this.text.setLayoutX(Constants.HEALTH_POINTS_LAYOUT_X);
        this.text.setLayoutY(Constants.HEALTH_POINTS_LAYOUT_Y);

        if (this.getChildren().size() > 0) {
            this.getChildren().remove(0, 1);
        }
        this.getChildren().add(this.text);
    }
}
