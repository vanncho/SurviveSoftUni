package game.level.enums;

import game.sprites.ImageLoader;
import javafx.scene.image.Image;

public enum BlockType {

    PLATFORM(ImageLoader.BLOCKS_IMG, 160, 224, 16, 16, 3, DestructionType.IMPASSABLE),
    BRICK(ImageLoader.BLOCKS_IMG, 320, 192, 16, 16, 4,DestructionType.DESTRUCTIBLE),
    EXIT(ImageLoader.EXIT_SIGN, 0, 0, 32, 32, 2,DestructionType.PASSABLE),
    BLOOD_TABLE_1(ImageLoader.BLOOD_TABLE, 185, 100, 20, 30, 11,DestructionType.IMPASSABLE),
    BLOOD_TABLE_2(ImageLoader.BLOOD_TABLE, 205, 100, 20, 30, 12,DestructionType.IMPASSABLE),
    DESK_1(ImageLoader.DESKS, 395, 12, 65, 95, 5,DestructionType.IMPASSABLE),
    DESK_2(ImageLoader.DESKS, 395, 107, 65, 95, 6,DestructionType.IMPASSABLE),
    DESK_3(ImageLoader.DESKS, 10, 120, 96, 80, 7,DestructionType.IMPASSABLE),
    DESK_4(ImageLoader.DESKS, 106, 120, 96, 80, 8,DestructionType.IMPASSABLE),
    BILLIARD(ImageLoader.BILLIARD, 0, 0, 145, 105, 9,DestructionType.IMPASSABLE),
    WINDOWS(ImageLoader.WINDOWS, 292, 35, 90, 45, 10,DestructionType.IMPASSABLE),
    SINK(ImageLoader.SINK, 200, 80, 25, 50, 13,DestructionType.IMPASSABLE),
    TOILET(ImageLoader.HOUSE_STUFF, 780, 965, 190, 185, 18,DestructionType.IMPASSABLE),
    COUCH_1(ImageLoader.COUCH, 383, 128, 34, 32, 14,DestructionType.IMPASSABLE),
    COUCH_2(ImageLoader.COUCH, 417, 128, 34, 32, 15,DestructionType.IMPASSABLE),
    COUCH_3(ImageLoader.COUCH, 383, 164, 34, 32, 16,DestructionType.IMPASSABLE),
    COUCH_4(ImageLoader.COUCH, 417, 164, 34, 32, 17,DestructionType.IMPASSABLE),
    TABLE_1(ImageLoader.TABLE, 295, 510, 85, 95, 19,DestructionType.IMPASSABLE),
    TABLE_2(ImageLoader.TABLE, 380, 510, 85, 95, 20,DestructionType.IMPASSABLE);

    private Image blockImage;
    private int offsetX;
    private int offsetY;
    private int sizeX;
    private int sizeY;
    private int id;
    private DestructionType destructionType;

    BlockType(Image image, int offsetX, int offsetY, int sizeX, int sizeY, int id, DestructionType destructionType) {
        this.blockImage = image;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.id = id;
        this.destructionType = destructionType;
    }


    public Image getBlockImage() {
        return this.blockImage;
    }

    public int getOffsetX() {
        return this.offsetX;
    }

    public int getOffsetY() {
        return this.offsetY;
    }

    public int getSizeX() {
        return this.sizeX;
    }

    public int getSizeY() {
        return this.sizeY;
    }

    public int getId() {
        return this.id;
    }

    public DestructionType getDestructionType() {
        return this.destructionType;
    }
}