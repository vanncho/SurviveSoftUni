package game.models;
import game.models.interfaces.Enemy;
import javafx.scene.image.ImageView;

public abstract class EnemyImpl extends GameMovableObjectImpl implements Enemy{

    // zombie position on the "matrix"...
    private int currentCellRow;
    private int currentCellCol;
    private int posXReal; //actual pixel position
    private int posYReal;
    private boolean isCentered;
    private boolean allowNextCellMove;
    private int health;
    private ImageView enemyImageView;

    protected EnemyImpl(int setTranslateX, int setTranslateY) {
        super(setTranslateX, setTranslateY);
    }

    public int getHealth() {
        return this.health;
    }

    protected void setHealth(int health) {
        this.health = health;
    }

    protected void dealDamage(int damage){
        this.health -= damage;
    }

    public int getPosYReal() {
        return this.posYReal;
    }

    protected void setPosYReal(int posYReal) {
        this.posYReal = posYReal;
    }

    public int getPosXReal() {
        return this.posXReal;
    }

    protected void setPosXReal(int posXReal) {
        this.posXReal = posXReal;
    }

    public boolean getIsCentered() {
        return this.isCentered;
    }

    protected void setIsCentered(boolean centered) {
        this.isCentered = centered;
    }

    public boolean getAllowNextCellMove() {
        return this.allowNextCellMove;
    }

    protected void setAllowNextCellMove(boolean allowNextCellMove) {
        this.allowNextCellMove = allowNextCellMove;
    }

    public ImageView getEnemyImageView() {
        return this.enemyImageView;
    }

    protected void setEnemyImageView(ImageView enemyImageView) {
        this.enemyImageView = enemyImageView;
    }

    public int getCurrentCellRow() {
        return this.currentCellRow;
    }

    protected void setCurrentCellRow(int currentCellRow) {
        this.currentCellRow = currentCellRow;
    }

    public int getCurrentCellCol() {
        return this.currentCellCol;
    }

    protected void setCurrentCellCol(int currentCellCol) {
        this.currentCellCol = currentCellCol;
    }

    public void changeHealth(int health){
        this.setHealth(health);
    }

    public void changeDealDamage(int damage){
        this.dealDamage(damage);
    }

    public  void changePosXPixel(int posXReal){
        this.setPosXReal(posXReal);
    }

    public void changePosYPixel(int posYReal){
        this.setPosYReal(posYReal);
    }

    public void changeCurrentCellRow(int currentCellRow){
        this.setCurrentCellRow(currentCellRow);
    }

    public void changeCurrentCellCol(int currentCellCol){
        this.setCurrentCellCol(currentCellCol);
    }

    public void isCentered(boolean centered){
        this.setIsCentered(centered);
    }

    public void changeAllowNextCellMove(boolean allowNextCellMove){
        this.setAllowNextCellMove(allowNextCellMove);
    }

    public void changeEnemyImageView(ImageView enemyImageView){
        this.setEnemyImageView(enemyImageView);
    }
}
