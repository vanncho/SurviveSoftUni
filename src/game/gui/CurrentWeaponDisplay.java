package game.gui;

public class CurrentWeaponDisplay {

    private double offsetX;
    private double offsetY;
    private double currentWeaponDisplayWidth;
    private double currentWeaponDisplayHeight;

    public CurrentWeaponDisplay(double offsetX, double offsetY, int currentWeaponDisplayWidth, int currentWeaponDisplayHeight) {
        this.setOffsetX(offsetX);
        this.setOffsetY(offsetY);
        this.setCurrentWeaponDisplayWidth(currentWeaponDisplayWidth);
        this.setCurrentWeaponDisplayHeight(currentWeaponDisplayHeight);
    }

    public double getOffsetX() {
        return this.offsetX;
    }

    public double getOffsetY() {
        return this.offsetY;
    }

    public double getCurrentWeaponDisplayHeight() {
        return this.currentWeaponDisplayHeight;
    }

    public double getCurrentWeaponDisplayWidth() {
        return this.currentWeaponDisplayWidth;
    }

    private void setOffsetX(double offsetX) {
        this.offsetX = offsetX;
    }

    private void setOffsetY(double offsetY) {
        this.offsetY = offsetY;
    }

    private void setCurrentWeaponDisplayWidth(double currentWeaponDisplayWidth) {
        this.currentWeaponDisplayWidth = currentWeaponDisplayWidth;
    }

    private void setCurrentWeaponDisplayHeight(double currentWeaponDisplayHeight) {
        this.currentWeaponDisplayHeight = currentWeaponDisplayHeight;
    }
}
