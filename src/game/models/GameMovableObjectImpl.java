package game.models;

import game.models.interfaces.GameMovableObject;
import game.sprites.SpriteAnimation;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public abstract class GameMovableObjectImpl extends Pane implements GameMovableObject {
    private int spriteCount;
    private int spriteColumns;
    private int spriteOffsetX;
    private int spriteOffsetY;
    private int spriteWidth;
    private int spriteHeight;

    private Shape boundingBox;

    private int objectSize;

    // for object ID
    private int id;

    //for random movement and others TODO: move to enemy class? Should also change collision manager in this case
    private boolean isInCollision;

    //the position on the matrix
    private int posX;
    private int posY;

    private SpriteAnimation animation;

    protected GameMovableObjectImpl(int setTranslateX, int setTranslateY){
        this.setTranslateX(setTranslateX);
        this.setTranslateY(setTranslateY);
        this.setIsInCollision(true);
    }

    public int getSpriteCount() {
        return this.spriteCount;
    }

    protected void setSpriteCount(int spriteCount) {
        this.spriteCount = spriteCount;
    }

    public int getSpriteColumns() {
        return this.spriteColumns;
    }

    protected void setSpriteColumns(int spriteColumns) {
        this.spriteColumns = spriteColumns;
    }

    public int getSpriteOffsetX() {
        return this.spriteOffsetX;
    }

    protected void setSpriteOffsetX(int spriteOffsetX) {
        this.spriteOffsetX = spriteOffsetX;
    }

    public int getSpriteOffsetY() {
        return this.spriteOffsetY;
    }

    protected void setSpriteOffsetY(int spriteOffsetY) {
        this.spriteOffsetY = spriteOffsetY;
    }

    public int getSpriteWidth() {
        return this.spriteWidth;
    }

    protected void setSpriteWidth(int spriteSpriteWidth) {
        this.spriteWidth = spriteSpriteWidth;
    }

    public int getSpriteHeight() {
        return this.spriteHeight;
    }

    protected void setSpriteHeight(int spriteSpriteHeight) {
        this.spriteHeight = spriteSpriteHeight;
    }

    public Shape getBoundingBox() {
        return this.boundingBox;
    }

    protected void setBoundingBox(Shape boundingBox) {
        this.boundingBox = boundingBox;
    }

    public boolean getIsInCollision() {
        return this.isInCollision;
    }

    protected void setIsInCollision(boolean inCollision) {
        this.isInCollision = inCollision;
    }

    protected void setId(int id) {
        this.id = id;
    }

    public int getPosX() {
        return this.posX;
    }

    protected void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return this.posY;
    }

    protected void setPosY(int posY) {
        this.posY = posY;
    }

    public SpriteAnimation getAnimation() {
        return this.animation;
    }

    protected void setAnimation(SpriteAnimation animation) {
        this.animation = animation;
    }

    public int getObjectSize() {
        return this.objectSize;
    }

    protected void setObjectSize(int objectSize) {
        this.objectSize = objectSize;
    }

    //TODO make a method to use this from outside this class
    public Shape calcBoundingBox(int size) {

        Circle circleBBox = new Circle(this.getTranslateX() + size/2, this.getTranslateY() + size/2, size/2+4);
        circleBBox.setOpacity(0);

        return circleBBox;
    }

    public void changeSpriteCount(int spriteCount){
        this.setSpriteCount(spriteCount);
    }

    public void changeSpriteColumns(int spriteColumns){
        this.setSpriteColumns(spriteColumns);
    }

    public void changeSpriteOffsetX(int spriteOffsetX){
        this.setSpriteOffsetX(spriteOffsetX);
    }

    public void changeSpriteOffsetY(int spriteOffsetY){
        this.setSpriteOffsetX(spriteOffsetY);
    }

    public void changeSpriteWidth(int spriteSpriteWidth){
        this.setSpriteWidth(spriteSpriteWidth);
    }

    public void changeSpriteHeight(int spriteSpriteHeight){
        this.setSpriteHeight(spriteSpriteHeight);
    }

    public void changeBoundingBox(Shape boundingBox){
        this.setBoundingBox(boundingBox);
    }

    public void isInCollision(boolean inCollision){
        this.setIsInCollision(inCollision);
    }

    public void changeId(int id){
        this.setId(id);
    }

    public void changePosXGrid(int posX){
        this.setPosX(posX);
    }

    public void changePosYGrid(int posY){
        this.setPosY(posY);
    }

    public void changeAnimation(SpriteAnimation animation){
        this.setAnimation(animation);
    }

    public void changeObjectSize(int objectSize){
        this.setObjectSize(objectSize);
    }
}
