package game.core;

import game.gui.GUIDrawer;
import game.interfaces.InputManager;
import game.models.interfaces.HumanObject;
import game.moveLogic.Axis;
import game.moveLogic.interfaces.Movable;
import game.staticData.Constants;
import game.weapons.WeaponType;
import javafx.scene.input.KeyCode;

public class InputHandler implements InputManager {
    private Movable movePlayerManager;
    private HumanObject player;
    private GUIDrawer guiDrawer;

    public InputHandler(HumanObject player, Movable movePlayerManager, GUIDrawer guiDrawer) {
        this.setPlayer(player);
        this.setMovePlayerManager(movePlayerManager);
        this.setGuiDrawer(guiDrawer);
    }

    public HumanObject getPlayer() {
        return this.player;
    }

    private void setPlayer(HumanObject player) {
        this.player = player;
    }

    public Movable getMovePlayerManager() {
        return this.movePlayerManager;
    }

    private void setMovePlayerManager(Movable movePlayerManager) {
        this.movePlayerManager = movePlayerManager;
    }

    public GUIDrawer getGuiDrawer() {
        return this.guiDrawer;
    }

    private void setGuiDrawer(GUIDrawer guiDrawer) {
        this.guiDrawer = guiDrawer;
    }

    public void handleInput(KeyCode kc) {
        switch (kc) {
            case W:
                this.getPlayer().getPlayerImageView().setRotate(270);
                this.getPlayer().getAnimation().play();
                this.getMovePlayerManager().move(-Constants.PLAYER_VELOCITY, Axis.Y);
                break;
            case S:
                this.getPlayer().getPlayerImageView().setRotate(90);
                this.getPlayer().getAnimation().play();
                this.getMovePlayerManager().move(Constants.PLAYER_VELOCITY, Axis.Y);
                break;
            case A:
                this.getPlayer().getPlayerImageView().setRotate(180);
                this.getPlayer().getAnimation().play();
                this.getMovePlayerManager().move(-Constants.PLAYER_VELOCITY, Axis.X);
                break;
            case D:
                this.getPlayer().getPlayerImageView().setRotate(0);
                this.getPlayer().getAnimation().play();
                this.getMovePlayerManager().move(Constants.PLAYER_VELOCITY, Axis.X);
                break;
            case R:
                this.getPlayer().getCurrentWeapon().reload();
                break;
            case DIGIT1:
                if (this.getPlayer().changeWeapon(WeaponType.PISTOL)) {
                    this.getPlayer().changePlayerState("PistolState");
                    this.getGuiDrawer().changeWeaponImage(WeaponType.PISTOL);
                }
                break;
            case DIGIT2:
                if (this.getPlayer().changeWeapon(WeaponType.MACHINE_GUN)) {
                    this.getPlayer().changePlayerState("MachineGunState");
                    this.getGuiDrawer().changeWeaponImage(WeaponType.MACHINE_GUN);
                }
                break;
            case DIGIT3:
                if (this.getPlayer().changeWeapon(WeaponType.SHOTGUN)) {
                    this.getPlayer().changePlayerState("ShotgunState");
                    this.getGuiDrawer().changeWeaponImage(WeaponType.SHOTGUN);
                }
                break;
            default:
                break;
        }
    }
}
