package game.gui;

import game.staticData.Constants;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class WeaponTextDisplay extends Pane {

    private Text text;
    private String weaponText;

    public WeaponTextDisplay(String weaponName) {
        this.setText("Weapon loaded: " + weaponName);
    }

    public void changeWeaponDisplayText(String weaponName) {
        this.weaponText = String.format("%s", weaponName);
        this.setText(this.weaponText);
    }

    private void setText(String setWeaponText) {
        this.text = new Text(setWeaponText);
        this.text.setFont(Font.font(Constants.DEFAULT_BARS_FONT_STYLE, FontWeight.BOLD, Constants.GUI_DEFAULT_FONT_SIZE));
        this.text.setFill(Color.WHITE);
        this.text.setLayoutX(Constants.WEAPON_TEXT_LAYOUT_X);
        this.text.setLayoutY(Constants.WEAPON_TEXT_LAYOUT_Y);

        if (this.getChildren().size() > 0) {
            this.getChildren().remove(0, 1);
        }
        this.getChildren().add(this.text);
    }
}
