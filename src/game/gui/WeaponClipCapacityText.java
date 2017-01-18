package game.gui;

import game.staticData.Constants;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class WeaponClipCapacityText extends Pane {

    private Text text;
    private String clipCapacityText;

    public WeaponClipCapacityText(int bulletsLeft) {
        this.setText("Capacity: " + bulletsLeft);
    }

    public void changeClipCapacity(int currentCapacity, int totalCapacity) {
        this.clipCapacityText = String.format("%d/%d", currentCapacity, totalCapacity);
        this.setText(this.clipCapacityText);
    }

    private void setText(String setCapacity) {
        this.text = new Text(setCapacity);
        this.text.setFont(Font.font(Constants.DEFAULT_BARS_FONT_STYLE, FontWeight.BOLD, Constants.GUI_DEFAULT_FONT_SIZE));
        this.text.setFill(Color.WHITE);
        this.text.setLayoutX(Constants.WEAPON_CLIP_TEXT_LAYOUT_X);
        this.text.setLayoutY(Constants.WEAPON_CLIP_TEXT_LAYOUT_Y);

        if (this.getChildren().size() > 0) {
            this.getChildren().remove(0, 1);
        }
        this.getChildren().add(this.text);
    }
}
