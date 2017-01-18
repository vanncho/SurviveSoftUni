package game.weapons;

public abstract class Weapon {
    private WeaponType weaponType;
    private int bulletsInClip;
    private int totalBullets;

    protected Weapon(WeaponType weaponType) {
        this.setWeaponType(weaponType);
        this.setBulletsInClip(weaponType.getClipCapacity());
        this.setTotalBullets(weaponType.getMaxBulletsCapacity());
    }

    public WeaponType getWeaponType() {
        return this.weaponType;
    }

    private void setWeaponType(WeaponType weaponType) {
        this.weaponType = weaponType;
    }

    public int getBulletsInClip() {
        return this.bulletsInClip;
    }

    public void setBulletsInClip(int bulletsInClip) {
        this.bulletsInClip = bulletsInClip;
    }

    public int getTotalBullets() {
        return totalBullets;
    }

    public void setTotalBullets(int totalBullets) {
        this.totalBullets = totalBullets;
        if (totalBullets > this.getWeaponType().getMaxBulletsCapacity()) {
            this.totalBullets = this.getWeaponType().getMaxBulletsCapacity();
        }
        //TODO this is no longer needed, recheck reload calc before removing failsafe
        if (totalBullets < 0) {
            this.totalBullets = 0;
        }
    }

    public boolean shoot() {
        if (this.getBulletsInClip() == 0) {
            return false;
        }
        this.setBulletsInClip(this.getBulletsInClip() - 1);
        return true;
    }

    public boolean addClip() {
        if (this.getTotalBullets() == this.getWeaponType().getMaxBulletsCapacity()) {
            return false;
        }
        this.setTotalBullets(this.getTotalBullets() + this.getWeaponType().getClipCapacity());
        return true;
    }

    //TODO rare case drops total bullets to negative amounts
    public boolean reload() {
        if (this.getBulletsInClip() == this.getWeaponType().getClipCapacity() || this.getTotalBullets() <= 0) {
            return false;
        }
        int bulletsNeeded = (this.getWeaponType().getClipCapacity() - this.getBulletsInClip());
        if (this.getTotalBullets() >= bulletsNeeded) {
            this.setTotalBullets(this.getTotalBullets() - bulletsNeeded);
            this.setBulletsInClip(this.getWeaponType().getClipCapacity());
            return true;
        }

        this.setBulletsInClip(this.getBulletsInClip()+this.getTotalBullets());
        this.setTotalBullets(0);
        return true;
    }
}