package objects;

import helperMethods.Constants;

import static helperMethods.Constants.Towers.*;

public class Tower {

    private int x, y, id, towerType, damage, CDTick;
    private float range, attackSpeed;
    private int tier;

    public Tower(int x, int y, int id, int towerType) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.towerType = towerType;
        tier = 1;
        setDefaultDamage();
        setDefaultRange();
        setDefaultAttackSpeed();

    }

    public void updateCD() {  //Ticks are calculated in UPDATES, so attackSpeed is certain amaount of UPDATES
        CDTick++;
    }
    public void resetAttackSpeedCD() {
        CDTick = 0;
    }

    public void upgradeTower() {
        this.tier++;

        switch (towerType) {
            case HUNTER:
                damage = damage + 2;
                range = range + 5;
                attackSpeed = attackSpeed - 2;
                break;
            case MONKEY:
                damage = damage + 2;
                range = range + 5;
                attackSpeed = attackSpeed - 2;
                break;
            case FIREMAN:
                damage = damage + 5;
                range = range + 5;
                attackSpeed = attackSpeed - 2;
                break;
            case MARIO:
                damage = damage + 3;
                range = range + 2;
                attackSpeed = attackSpeed - 2;
                break;
            case PACMAN:
                damage = damage + 3;
                range = range + 5;
                attackSpeed = attackSpeed - 2;
            case SONIC:
                damage = damage + 12;
                range = range + 2;
                attackSpeed = attackSpeed - 3;
        }
    }

    public boolean isAttackSpeedCDOver() {
        if(CDTick >= attackSpeed) {
            return true;
        }
        return false;
    }
    private void setDefaultAttackSpeed() {
        attackSpeed = Constants.Towers.GetDefaultAttackSpeed(this.towerType);
    }

    private void setDefaultRange() {
        range = Constants.Towers.GetDefaultRange(this.towerType);

    }

    private void setDefaultDamage() {
        damage = Constants.Towers.GetStartDmg(this.towerType);

    }


    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTowerType(int towerType) {
        this.towerType = towerType;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getId() {
        return id;
    }
    public int getTowerType() {
        return towerType;
    }

    public int getDamage() {
        return damage;
    }

    public float getRange() {
        return range;
    }

    public float getAttackSpeed() {
        return attackSpeed;
    }

    public int getTier() {
        return tier;
    }
}
