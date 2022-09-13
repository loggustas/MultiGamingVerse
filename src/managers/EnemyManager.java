package managers;

import Enemies.*;
import Scenes.Playing;
import helperMethods.Constants;
import helperMethods.LoadSave;
import helperMethods.Utilz;
import objects.PathPoint;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static helperMethods.Constants.Enemies.*;
import static helperMethods.Constants.Direction.*;

public class EnemyManager {
    private final int ENEMY_AMOUNT = 6;

    private Playing playing;
    private BufferedImage[] enemyImages;
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private PathPoint start, finish;
    private int HPBarWidth = 20;
    private int[][] roadDirArr;


    public EnemyManager(Playing playing, PathPoint start, PathPoint finish) {
        this.playing = playing;
        enemyImages = new BufferedImage[ENEMY_AMOUNT];
        this.start = start;
        this.finish = finish;

        loadEnemyImages();

    }

    public void loadRoadDirArr() {
        roadDirArr = Utilz.GetRoadDirArr(playing.getGame().getTileManager().getTypeArr(), start, finish);

    }

    public void setStartFinish(PathPoint start, PathPoint finish) {
        this.start = start;
        this.finish = finish;
    }
    private void loadEnemyImages() {
        BufferedImage atlas = LoadSave.getSpriteAtlas();

        for(int i = 0 ; i < ENEMY_AMOUNT ; i++) {
            enemyImages[i] = atlas.getSubimage(i * 32, 32, 32, 32); //getting all enemy images
        }
    }

    public void update() {
        for(Enemy e : enemies) {
            if(e.isAlive()) {
                updateEnemyMoveNew(e);
            }
        }
    }

    private void updateEnemyMoveNew(Enemy e) {

        PathPoint currTile = getEnemyTile(e);

        int dir = roadDirArr[currTile.getyCord()][currTile.getxCord()];

        e.move(GetSpeed(e.getEnemyType()), dir);

        PathPoint newTile = getEnemyTile(e);
        if(isTileTheSame(newTile, finish)) {
            e.kill();
            playing.looseHealth(e);
        }

        if(!isTileTheSame(currTile, newTile)) {
            int newDir = roadDirArr[currTile.getyCord()][currTile.getxCord()];

            if(newDir != dir) {  //if new direction

                e.setPos(newTile.getxCord() * 32, newTile.getyCord() * 32);
                e.setLastDir(newDir);

            }
        }

    }

    private PathPoint getEnemyTile(Enemy e) {
        switch (e.getLastDir()) {
            case LEFT:
                return new PathPoint((int) ((e.getX() + 31) / 32) , (int) (e.getY() / 32));
            case UP:
                return new PathPoint((int) (e.getX() / 32) , (int) ((e.getY() + 31) / 32));
            case RIGHT:
                return new PathPoint((int) (e.getX() / 32) , (int) (e.getY() / 32));
            case DOWN:
                return new PathPoint((int) (e.getX() / 32) , (int) (e.getY() / 32));
        }
        return new PathPoint((int) (e.getX() / 32) , (int) (e.getY() / 32));
    }

    private boolean isTileTheSame(PathPoint currTile, PathPoint newTile) {
        if(currTile.getxCord() == newTile.getxCord()) {
            if(currTile.getyCord() == newTile.getyCord()) {
                return true;
            }
        }
        return false;
    }

    public void draw(Graphics g) {
        for(Enemy e : enemies){
            if(e.isAlive()){
                drawEnemy(e, g);
                drawHealthBar(e, g);
            }
        }

    }

    private void drawHealthBar(Enemy e, Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int)e.getX() + (32 - getNewHPBarWidth(e)) / 2, (int)e.getY() - 9, getNewHPBarWidth(e), 3);
    }
    private int getNewHPBarWidth(Enemy e) {
        return (int)(HPBarWidth * e.getHealthBarValue());
    }

    private void drawEnemy(Enemy e, Graphics g) {
        g.drawImage(enemyImages[e.getEnemyType()], (int)e.getX(), (int)e.getY(), null);
    }

    private void setNewDirectionAndMove(Enemy e) {
        int dir = e.getLastDir();

        //move into the current tile 100% to avoid bugs in if statements
        int xCord = (int)(e.getX() / 32);
        int yCord = (int)(e.getY() / 32);
        
        fixEnemyOffsetTile(e, dir, xCord, yCord);

        if(isAtEnd(e)) {
            return;
        }

        if(dir == Constants.Direction.LEFT || dir == Constants.Direction.RIGHT) {

            int newY = (int)(e.getY() + getSpeedYAndHeight(Constants.Direction.UP, e.getEnemyType()));
            if(getTileType((int) e.getX(), newY) == Constants.Tiles.ROAD) {
                e.move(GetSpeed(e.getEnemyType()), Constants.Direction.UP);
            }
            else {
                e.move(GetSpeed(e.getEnemyType()), Constants.Direction.DOWN);
            }
        } else {
            int newX = (int)(e.getX() + getSpeedXAndWidth(Constants.Direction.RIGHT, e.getEnemyType()));
            if(getTileType(newX , (int) e.getY()) == Constants.Tiles.ROAD){
                e.move(GetSpeed(e.getEnemyType()), Constants.Direction.RIGHT);
            } else {
                e.move(GetSpeed(e.getEnemyType()), Constants.Direction. LEFT);
            }

        }
    }

    private void fixEnemyOffsetTile(Enemy e, int dir, int xCord, int yCord) {
        switch (dir) {

            case Constants.Direction.RIGHT:
                if(xCord < 19){
                    xCord++;
                }
                break;
            case Constants.Direction.DOWN:
                if(yCord < 19) {
                    yCord++;
                }
                break;
        }

        e.setPos(xCord *32,yCord*32);
    }

    private boolean isAtEnd(Enemy e) {
        if(e.getX() == finish.getxCord() * 32) {
            if(e.getY() == finish.getyCord() * 32) {
                return true;
            }
        }
        return false;
    }

    private int getTileType(int x, int y) {  //returnins tile tip
        return playing.getTileType(x, y);
    }

    private float getSpeedYAndHeight(int dir, int enemyType) {
        if(dir == Constants.Direction.DOWN) {
            return GetSpeed(enemyType) + 32;
        } else if( dir == Constants.Direction.UP) {
            return -GetSpeed(enemyType);
        }

        return 0;
    }

    private float getSpeedXAndWidth(int dir, int enemyType) {
        if(dir == Constants.Direction.LEFT) {
            return -GetSpeed(enemyType);
        } else if( dir == Constants.Direction.RIGHT) {
            return GetSpeed(enemyType) + 32;
        }

        return 0;  //if no, then x-axis speed is 0
    }

    public void spawnEnemy(int nextEnemy) {
        addEnemy(nextEnemy);
    }
    public void addEnemy(int enemyType) {

        int x = start.getxCord() * 32;
        int y = start.getyCord() * 32;

        switch (enemyType) {
            case GNOME:
                enemies.add(new Gnome(x, y, 0, this));
                break;
            case GUARD:
                enemies.add(new Guard(x, y, 0, this));
                break;
            case ZOMBIE:
                enemies.add(new Zombie(x, y, 0, this));
                break;
            case ALIEN:
                enemies.add(new Alien(x, y, 0, this));
                break;
            case GHOST:
                enemies.add(new Ghost(x, y, 0, this));
                break;
            case CREEPER:
                enemies.add(new Creeper(x, y, 0, this));
                break;

        }
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void rewardPlayer(int enemyType) {
        playing.rewardPlayer(enemyType);
    }
    public void reset() {
        enemies.clear();
    }
}
