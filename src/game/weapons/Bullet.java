package game.weapons;

import game.sprites.ImageLoader;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;

import java.util.Random;

public class Bullet extends Pane {
    public Point2D velocity = new Point2D(0, 0);
    private Random rand;
    private int minDamage;
    private int maxDamage;
    private int speed;
    private ImageView bulletImageView;

    public Bullet(int minDamage, int maxDamage, int speed) {
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.speed = speed;
        this.rand = new Random();
        this.setBulletImageView(new ImageView(ImageLoader.BULLET_IMAGE));
        this.getChildren().add(this.getBulletImageView());
    }

    public int getMinDamage() {
        return this.minDamage;
    }

    public int getMaxDamage() {
        return this.maxDamage;
    }

    public int getSpeed() {
        return this.speed;
    }

    public ImageView getBulletImageView() {
        return this.bulletImageView;
    }

    private void setBulletImageView(ImageView bulletImageView) {
        this.bulletImageView = bulletImageView;
    }

    public void setTarget(double posX, double posY) {
        double angle = calcShootAngle(posX, posY);

        this.getTransforms().clear();
        this.getTransforms().add(new Rotate(angle, 0, 0));
    }

    private Point2D calcVelocity(double posX, double posY) {
        return new Point2D(posX, posY).subtract(this.getTranslateX(), this.getTranslateY()).normalize().multiply(this.getSpeed());
    }

    private double calcAngle(double vecX, double vecY) {
        double angle = new Point2D(vecX, vecY).angle(1, 0);
        return vecY > 0 ? angle : -angle;
    }

    public double calcShootAngle(double mousePosX, double mousePosY) {
        this.velocity = this.calcVelocity(mousePosX, mousePosY);
        double angle = calcAngle(this.velocity.getX(), this.velocity.getY());

        return angle;
    }

    public int calculateDamage() {
        return (this.rand.nextInt((this.getMaxDamage() - this.getMinDamage()) + 1) + this.getMinDamage());
    }

    public void move() {
        this.setTranslateX(getTranslateX() + this.velocity.getX());
        this.setTranslateY(getTranslateY() + this.velocity.getY());
    }
}
