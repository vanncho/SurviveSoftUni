package game.gui;

public class WeaponBar {

    private int playerInitialScore;
    private double offsetX;
    private double offsetY;
    private double weaponBarWidth;
    private double weaponBarHeight;

    public WeaponBar(int playerInitialScore, double offsetX, double offsetY, int weaponBarWidth, int weaponBarHeight) {
        this.setPlayerInitialScore(playerInitialScore);
        this.setOffsetX(offsetX);
        this.setOffsetY(offsetY);
        this.setWeaponBarWidth(weaponBarWidth);
        this.setWeaponBarHeight(weaponBarHeight);
    }

    public double getOffsetX() {
        return this.offsetX;
    }

    public double getOffsetY() {
        return this.offsetY;
    }

    public double getWeaponBarWidth() {
        return this.weaponBarWidth;
    }

    public double getWeaponBarHeight() {
        return this.weaponBarHeight;
    }

    private void setWeaponBarWidth(double weaponBarWidth) {
        this.weaponBarWidth = weaponBarWidth;
    }

    private void setOffsetX(double offsetX) {
        this.offsetX = offsetX;
    }

    private void setOffsetY(double offsetY) {
        this.offsetY = offsetY;
    }

    private void setWeaponBarHeight(double weaponBarHeight) {
        this.weaponBarHeight = weaponBarHeight;
    }

    private void setPlayerInitialScore(int playerInitialScore) {
        this.playerInitialScore = playerInitialScore;
    }
}
