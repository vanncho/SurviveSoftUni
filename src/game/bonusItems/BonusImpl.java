package game.bonusItems;

import game.staticData.Constants;
import game.bonusItems.enums.BonusType;
import game.bonusItems.interfaces.Bonus;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class BonusImpl extends Pane implements Bonus {

    private Image image;
    private ImageView imageView;
    private int posX;
    private int posY;
    private Rectangle boundingBox;
    private BonusType bonusType;

    public BonusImpl(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    @Override
    public Image getImage() {
        return this.image;
    }

    private void setImage(Image image) {
        this.image = image;
    }

    @Override
    public ImageView getImageView() {
        return this.imageView;
    }

    protected void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    public int getPosX() {
        return this.posX;
    }

    private void setPosX(int posX) {
        this.posX = posX;
    }

    @Override
    public int getPosY() {
        return this.posY;
    }

    private void setPosY(int posY) {
        this.posY = posY;
    }

    @Override
    public Rectangle getBoundingBox() {
        return this.boundingBox;
    }

    protected void setBoundingBox(Rectangle boundingBox) {
        this.boundingBox = boundingBox;
    }

    public BonusType getBonusType() {
        return this.bonusType;
    }

    protected void setBonusType(BonusType bonusType) {
        this.bonusType = bonusType;
    }

    protected void configureImageView(Image imageView) {
        this.setImageView(new ImageView(imageView));
        this.getImageView().setFitHeight(Constants.BONUS_IMAGE_HEIGHT);
        this.getImageView().setFitWidth(Constants.BONUS_IMAGE_WIDTH);
        this.getImageView().setTranslateX(this.getPosX());
        this.getImageView().setTranslateY(this.getPosY());
    }

    protected void configureBoundingBox() {
        this.setBoundingBox(new Rectangle(this.getImageView().getFitWidth(), this.getImageView().getFitHeight()));
        this.getBoundingBox().setTranslateX(this.getPosX());
        this.getBoundingBox().setTranslateY(this.getPosY());
        this.getBoundingBox().setFill(Color.BLACK);
        this.getBoundingBox().setOpacity(0);

    }
}
