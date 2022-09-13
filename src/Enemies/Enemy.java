package Enemies;

import helperMethods.Constants;
import managers.EnemyManager;

import java.awt.*;

import static helperMethods.Constants.Direction.UP;


public abstract class Enemy {

    protected float x, y;

    protected Rectangle bounds;   // for hit box
    protected int health, maxHealth;
    protected int ID;   //unique to every enemy
    protected int enemyType;   //unique to type of enemy
    protected int lastDir;
    protected EnemyManager enemyManager;
    private boolean alive;

    public Enemy(float x, float y, int id , int enemyType, EnemyManager enemyManager) {
        this.x = x;
        this.y = y;
        this.ID = id;
        this.enemyType = enemyType;
        bounds = new Rectangle((int)x, (int)y, 32, 32);   //initiating bounds
        lastDir = -1;  // at first last direction is -1
        alive = true;
        this.enemyManager = enemyManager;
        setStartHealth();

    }

    private void setStartHealth() {
        this.health = Constants.Enemies.GetStartHealth(enemyType);
        maxHealth = health;
    }

    public float getHealthBarValue() {
        return (float)health / maxHealth;
    }

    public void hurt(int damage) {
        this.health = this.health - damage;
        if(health <= 0) {
            alive = false;

            //give gold to the player
            enemyManager.rewardPlayer(enemyType);
        }
    }
    public void kill() {
        //is for killing enemy, when it reaches the end
        alive = false;
        health = 0;
    }

    public void move(float speed, int dir) {

        lastDir = dir;
        switch (dir) {
            case Constants.Direction.LEFT:
                this.x = this.x - speed;
                break;
            case UP:
                this.y = this.y - speed;
                break;
            case Constants.Direction.RIGHT:
                this.x = this.x + speed;
                break;
            case Constants.Direction.DOWN:
                this.y = this.y + speed;
                break;

        }
        updateHitBox();
    }

    private void updateHitBox() {
        bounds.x = (int) x;   //updating coordinates, width and height is always the same
        bounds.y = (int) y;
    }

    public void setPos(int x, int y) {
        //used only for position fix
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
    public int getEnemyType() {
        return enemyType;
    }
    public int getHealth() {
        return health;
    }
    public int getID() {
        return ID;
    }
    public Rectangle getBounds() {
        return bounds;
    }
    public int getLastDir() {
        return lastDir;
    }

    public boolean isAlive() {
        return alive;
    }


    public void setLastDir(int newDir) {
        this.lastDir = newDir;
    }
}
