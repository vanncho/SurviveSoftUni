package game.gui;

import game.sprites.ImageLoader;
import game.weapons.WeaponType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class GUIDrawer extends Pane {
    private ImageView healthBarImage;
    private ImageView healthBarBackgroundImage;
    private HealthBar healthBar;
    private ImageView weaponBarImage;
    private WeaponBar weaponBar;
    private HealthPoints healthPoints;
    private ScorePoints scorePoints;
    private CurrentWeaponDisplay currentWeaponDisplay;
    private ImageView currentWeaponDisplayImage;
    private WeaponTextDisplay weaponTextDisplay;
    private WeaponClipCapacityText weaponClipCapacityText;

    public GUIDrawer(HealthBar healthBar,
                     WeaponBar weaponBar,
                     HealthPoints healthPoints,
                     ScorePoints scorePoints,
                     CurrentWeaponDisplay currentWeaponDisplay,
                     WeaponTextDisplay weaponTextDisplay,
                     WeaponClipCapacityText weaponClipCapacityText) {

        this.setHealthBarImage(new ImageView(ImageLoader.HEALTH_BAR));
        this.setHealthBarBackgroundImage(new ImageView(ImageLoader.HEALTH_BAR_BACKGROUND));
        this.setHealthBar(healthBar);

        this.setWeaponBarImage(new ImageView(ImageLoader.WEAPON_BAR_BACKGROUND));
        this.setWeaponBar(weaponBar);
        this.setHealthPoints(healthPoints);
        this.setScorePoints(scorePoints);
        this.setCurrentWeaponDisplay(currentWeaponDisplay);
        this.setCurrentWeaponDisplayImage(new ImageView(ImageLoader.PISTOL_IMAGE));
        this.setWeaponTextDisplay(weaponTextDisplay);
        this.setWeaponClipCapacityText(weaponClipCapacityText);
    }

    public WeaponClipCapacityText getWeaponClipCapacityText() {
        return weaponClipCapacityText;
    }

    public WeaponTextDisplay getWeaponTextDisplay() {
        return this.weaponTextDisplay;
    }

    public ScorePoints getScorePoints() {
        return this.scorePoints;
    }

    public HealthPoints getHealthPoints() {
        return this.healthPoints;
    }

    public HealthBar getHealthBar() {
        return this.healthBar;
    }

    public ImageView getHealthBarImage() {
        return this.healthBarImage;
    }

    public void drawHealthBar() {
        this.healthBarImage.setLayoutX(this.getHealthBar().getOffsetX());
        this.healthBarImage.setLayoutY(this.getHealthBar().getOffsetY());
        this.healthBarImage.setFitHeight(this.getHealthBar().getHealthBarHeight());
        this.healthBarImage.setFitWidth(this.getHealthBar().getHealthBarWidth());

        this.healthBarBackgroundImage.setLayoutX(this.getHealthBar().getOffsetX());
        this.healthBarBackgroundImage.setLayoutY(this.getHealthBar().getOffsetY());
        this.healthBarBackgroundImage.setFitHeight(this.getHealthBar().getHealthBarHeight());
        this.healthBarBackgroundImage.setFitWidth(this.getHealthBar().getHealthBarWidth());

        this.getChildren().add(this.healthBarBackgroundImage);
        this.getChildren().add(this.healthBarImage);
    }

    public void drawWeaponBar() {
        this.weaponBarImage.setLayoutX(this.getWeaponBar().getOffsetX());
        this.weaponBarImage.setLayoutY(this.getWeaponBar().getOffsetY());
        this.weaponBarImage.setFitWidth(this.getWeaponBar().getWeaponBarWidth());
        this.weaponBarImage.setFitHeight(this.getWeaponBar().getWeaponBarHeight());

        this.getChildren().add(this.weaponBarImage);
    }

    public void drawCurrentWeapon() {
        this.currentWeaponDisplayImage.setLayoutX(this.getCurrentWeaponDisplay().getOffsetX());
        this.currentWeaponDisplayImage.setLayoutY(this.getCurrentWeaponDisplay().getOffsetY());
        this.currentWeaponDisplayImage.setFitWidth(this.getCurrentWeaponDisplay().getCurrentWeaponDisplayWidth());
        this.currentWeaponDisplayImage.setFitHeight(this.getCurrentWeaponDisplay().getCurrentWeaponDisplayHeight());

        this.getChildren().add(this.currentWeaponDisplayImage);
    }

    public void changeWeaponImage(WeaponType weaponType) {

        this.getChildren().remove(this.getCurrentWeaponDisplayImage());
        this.setCurrentWeaponDisplayImage(new ImageView(weaponType.getWeaponImage()));

//        switch (weaponType) {
//            case PISTOL:
//                this.setCurrentWeaponDisplayImage(new ImageView(weaponType.getWeaponImage()));
//                break;
//            case MACHINE_GUN:
//                this.setCurrentWeaponDisplayImage(new ImageView(weaponType.getWeaponImage()));
//                break;
//            case SHOTGUN:
//                this.setCurrentWeaponDisplayImage(new ImageView(weaponType.getWeaponImage()));
//                break;
//        }

        this.drawCurrentWeapon();
    }

    public void drawHealthPoints() {
        this.getChildren().add(this.healthPoints);
    }

    public void drawScorePoints() {
        this.getChildren().add(this.scorePoints);
    }

    public void drawWeaponText() {
        this.getChildren().add(this.weaponTextDisplay);
    }

    public void drawWeaponClipCapacity() {
        this.getChildren().add(this.weaponClipCapacityText);
    }

    public CurrentWeaponDisplay getCurrentWeaponDisplay() {
        return this.currentWeaponDisplay;
    }

    public ImageView getCurrentWeaponDisplayImage() {
        return this.currentWeaponDisplayImage;
    }

    public WeaponBar getWeaponBar() {
        return this.weaponBar;
    }

    private void setWeaponClipCapacityText(WeaponClipCapacityText weaponClipCapacityText) {
        this.weaponClipCapacityText = weaponClipCapacityText;
    }

    private void setWeaponTextDisplay(WeaponTextDisplay weaponTextDisplay) {
        this.weaponTextDisplay = weaponTextDisplay;
    }

    private void setCurrentWeaponDisplay(CurrentWeaponDisplay currentWeaponDisplay) {
        this.currentWeaponDisplay = currentWeaponDisplay;
    }

    private void setCurrentWeaponDisplayImage(ImageView currentWeaponDisplayImage) {
        this.currentWeaponDisplayImage = currentWeaponDisplayImage;
    }

    private void setHealthBar(HealthBar healthBar) {
        this.healthBar = healthBar;
    }

    private void setHealthBarImage(ImageView healthBarImage) {
        this.healthBarImage = healthBarImage;
    }

    private void setHealthBarBackgroundImage(ImageView healthBarBackgroundImage) {
        this.healthBarBackgroundImage = healthBarBackgroundImage;
    }

    private void setWeaponBar(WeaponBar weaponBar) {
        this.weaponBar = weaponBar;
    }

    private void setWeaponBarImage(ImageView weaponBarImage) {
        this.weaponBarImage = weaponBarImage;
    }

    private void setHealthPoints(HealthPoints healthPoints) {
        this.healthPoints = healthPoints;
    }

    private void setScorePoints(ScorePoints scorePoints) {
        this.scorePoints = scorePoints;
    }
}