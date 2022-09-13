package managers;

import Enemies.Enemy;
import Scenes.Playing;
import helperMethods.LoadSave;
import helperMethods.Utilz;
import objects.Tower;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class TowerManager {

    public static final int TOWER_AMOUNT = 6;    ///CHANGE IF NEW ENEMIES ARE ADDED
    private Playing playing;
    private BufferedImage[] towerImgs;
    private ArrayList<Tower> towers = new ArrayList<>(); //array list of placed towers
    private int towerAmount = 0;

    public TowerManager(Playing playing) {
        this.playing = playing;

        loadTowerImages();

    }

    private void loadTowerImages() {
        BufferedImage atlas = LoadSave.getSpriteAtlas();
        towerImgs = new BufferedImage[TOWER_AMOUNT];
        for(int i = 0 ; i < TOWER_AMOUNT ; i++){
            towerImgs[i] = atlas.getSubimage(32 * i , 32 * 2 , 32, 32);
        }
    }
    public void addTower(Tower selectedTower, int xPos, int yPos) {
        towers.add(new Tower(xPos, yPos, towerAmount++, selectedTower.getTowerType()));
    }

    public void upgradeTower(Tower displayedTower) {
        for(Tower t : towers) {
            if(t.getId() == displayedTower.getId()) {
                t.upgradeTower();
            }
        }
    }

    public void removeTower(Tower displayedTower) {
        for(int i = 0 ; i < towers.size() ; i++) {
            if(towers.get(i).getId() == displayedTower.getId()) {
                towers.remove(i);
            }
        }
    }

    public void draw(Graphics g) {
        for (Tower t : towers) {
            g.drawImage(towerImgs[t.getTowerType()], t.getX(), t.getY(), null);//drawwing towers from array list
        }
    }

    public Tower getTowerAt(int x, int y) {   //goes through tower array and check if there is a tower at x and y position
        for(Tower t : towers) {
            if(t.getX() == x && t.getY() == y) {
                return t;
            }
        }
        return null;
    }


    public void update() {
        for(Tower t : towers) {   //looping through every tower
            t.updateCD();           //updates attack CD every update
            attackEnemyIfInRange(t);
        }
    }

    private void attackEnemyIfInRange(Tower t) {

        for(Enemy e : playing.getEnemyManager().getEnemies()) {  //looping through every enemy
            if(e.isAlive()){
                if(isEnemyInRange(t, e)) {

                    if(t.isAttackSpeedCDOver()) {   //CHECKS IF ATTACK CD IS OVER
                        //attack the enemy

                        playing.attackEnemy(t, e);
                        t.resetAttackSpeedCD();  //RESETS ATTACK SPEED CD
                    }
                } else {
                    //do nothing
                }
            }
        }
    }

    private boolean isEnemyInRange(Tower t, Enemy e) {
        int distance = Utilz.GetDistance(t.getX(), t.getY(), e.getX(), e.getY());
        if(distance < t.getRange()) {
            return true;
        }
        return false;
    }

    public BufferedImage[] getTowerImgs() {
        return towerImgs;
    }

    public void reset() {
        towers.clear();
        towerAmount = 0;
    }
}
