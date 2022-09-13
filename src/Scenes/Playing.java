package Scenes;

import Enemies.Enemy;
import helperMethods.Constants;
import helperMethods.LoadSave;
import main.Game;
import main.GameScreen;
import main.GameStates;
import managers.EnemyManager;
import managers.ProjectileManager;
import managers.TowerManager;
import managers.WaveManager;
import objects.PathPoint;

import objects.Tower;
import ui.ActionBar;
import ui.MyButton;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static helperMethods.Constants.Tiles.GRASS;

public class Playing extends GameScene implements SceneMethods{

    private final int GOLD_ADDITION_IN_SEC = 5; //every 5 sec gain 1 gold
    private final int LEVEL_AMOUNT = 10;

    private int goldTick;

    private int[][] level;

    private ActionBar actionBar;
    private int mouseX, mouseY;     //needed for editing
    private PathPoint start, finish;
    private BufferedImage opacityScreen;
    private MyButton nextLevel;

    private EnemyManager enemyManager;
    private TowerManager towerManager;
    private WaveManager waveManager;

    private ProjectileManager projectileManager;

    private Tower selectedTower;
    private boolean gamePaused = false, levelOver = false;


    public Playing(Game game, GameScreen gameScreen) {
        super(game, gameScreen);
        goldTick = 0;

        //managers
        enemyManager = new EnemyManager(this, start, finish);
        this.actionBar = new ActionBar(640, 0, 240, 640, this);
        this.nextLevel = new MyButton("Next level", 280, 350, 80, 30);
        towerManager = new TowerManager(this);
        projectileManager = new ProjectileManager(this);
        waveManager = new WaveManager(this);
        opacityScreen = LoadSave.getPauseScreen();


        loadLevel();

    }
    public void loadLevelAfterSave() {
        loadLevel();

    }
    private void loadLevel() {
        //first, load progress from file
        int lvl = LoadSave.GetProgress();
        Constants.Level.setLEVEL(lvl);

        ArrayList<PathPoint> points;

        if(Constants.Level.LEVEL == 1) {
            level = LoadSave.GetLevelData("first");
            points = LoadSave.GetLevelPathPoints("first");
        } else if(Constants.Level.LEVEL == 2) {
            level = LoadSave.GetLevelData("second");
            points = LoadSave.GetLevelPathPoints("second");
        } else if(Constants.Level.LEVEL == 3){
            level = LoadSave.GetLevelData("third");
            points = LoadSave.GetLevelPathPoints("third");
        } else if(Constants.Level.LEVEL == 4){
            level = LoadSave.GetLevelData("fourth");
            points = LoadSave.GetLevelPathPoints("fourth");
        } else if(Constants.Level.LEVEL == 5){
            level = LoadSave.GetLevelData("fifth");
            points = LoadSave.GetLevelPathPoints("fifth");
        } else if(Constants.Level.LEVEL == 6){
            level = LoadSave.GetLevelData("sixth");
            points = LoadSave.GetLevelPathPoints("sixth");
        } else if(Constants.Level.LEVEL == 7){
            level = LoadSave.GetLevelData("seventh");
            points = LoadSave.GetLevelPathPoints("seventh");
        } else if(Constants.Level.LEVEL == 8){
            level = LoadSave.GetLevelData("eight");
            points = LoadSave.GetLevelPathPoints("eight");
        } else if(Constants.Level.LEVEL == 9){
            level = LoadSave.GetLevelData("ninth");
            points = LoadSave.GetLevelPathPoints("ninth");
        } else {
            level = LoadSave.GetLevelData("tenth");
            points = LoadSave.GetLevelPathPoints("tenth");
        }

        start = points.get(0);
        finish = points.get(1);
        enemyManager.setStartFinish(start, finish);
        enemyManager.loadRoadDirArr();
        waveManager.loadWaves();


    }

    public void setLevel(int[][] lvl, PathPoint start, PathPoint finish) {
        this.level = lvl;
        this.start = start;
        this.finish = finish;
    }

    public void update() {   ///updating the game
        if(!gamePaused && !levelOver) {

            updateAnimationTick();

            waveManager.update();

            //goldAddition tick update
            goldTick++;
            if(goldTick % (getGame().getUPS_SET() * GOLD_ADDITION_IN_SEC) == 0) { //check if divides without reminder, that means goldAdditionInSec amount of sec passed
                actionBar.addGold(1);
            }

            if(isAllEnemiesDead()) {  //check if all enemies are spawned and killed
                if(isThereMoreWaves()) {
                    waveManager.startWaveTimer();
                    //check wave timer
                    if(isWaveTimerOver()) {
                        //increase wave index and start next wave
                        waveManager.increaseWaveIndex();
                        enemyManager.getEnemies().clear();  //clearing the enemy list
                        waveManager.resetEnemyIndex();  //resetting the index, that points to enemy
                    }

                } else {
                    levelOver = true;
                }
            }
            if(isTimeForNewEnemy()) {
                spawnEnemy();
            }

            enemyManager.update();
            towerManager.update();
            projectileManager.update();
        }

    }

    public void setGamePaused(boolean gamePaused) {
        this.gamePaused = gamePaused;
    }
    private boolean isWaveTimerOver() {
        return waveManager.isWaveTimeOver();
    }

    private boolean isThereMoreWaves() {
        return waveManager.isThereMoreWaves();
    }

    private boolean isAllEnemiesDead() {
        if(waveManager.isThereEnemiesLeftInWave()) {
            return false;
        }
        for(Enemy e : enemyManager.getEnemies()) {
            if(e.isAlive()) {  //if found one alive return false
                return false;
            }
        }
        return true;
    }

    private void spawnEnemy() {
        enemyManager.spawnEnemy(waveManager.getNextEnemy());
//        enemyManager.addEnemy(waveManager.getNextEnemy());
    }

    private boolean isTimeForNewEnemy() {
        if(waveManager.isTimeForNewEnemy()) { //check if is it time for enemy to spawn
            if(waveManager.isThereEnemiesLeftInWave()) {  //check if there are enemies left to spawn in wave
                return true;
            }
        }

        return false;
    }
    public void attackEnemy(Tower t, Enemy e) {
        projectileManager.newProjectile(t, e);
    }

    public void looseHealth(Enemy e) {
        int amount = Constants.Enemies.GetHPLost(e.getEnemyType());
        actionBar.looseHealth(amount);          //send amount to the action bar
    }

    public void setSelectedTower(Tower selectedTower) {
        this.selectedTower = selectedTower;
    }

    public int getTileType(int x, int y) {   //return the constant, what type of tile it is
        int xCord = x / 32;
        int yCord = y / 32;

        if(xCord < 0 || xCord > 19){
            return Constants.Tiles.WATER;
        }
        if(yCord < 0 || yCord > 19){
            return Constants.Tiles.WATER; //TAS PATS
        }
        int id = level[y / 32][x / 32];
        return game.getTileManager().getTile(id).getTileType();
    }

    @Override
    public void render(Graphics g) {

        for(int y = 0 ; y < level.length ; y++){
            for(int x = 0 ; x < level[y].length ; x++) {

                if(isAnimation(level[y][x])) {
                    g.drawImage(getSprite(level[y][x], animationIndex), x * 32, y * 32, null);
                } else {
                    g.drawImage(getSprite(level[y][x]), x * 32, y * 32, null);
                }
            }
        }
        this.actionBar.draw(g);
        enemyManager.draw(g);
        towerManager.draw(g);
        projectileManager.draw(g);

        drawSelectedTower(g);
        drawHighlight(g);

        if(gamePaused) {
            drawPauseScreen(g);
        } if(levelOver) {
            if(Constants.Level.LEVEL == LEVEL_AMOUNT) {
                levelOver = false;
                GameStates.setGameState(GameStates.YOU_WON);
            } else {
                drawLevelOver(g);
            }
        }

    }

    private void drawLevelOver(Graphics g) {
        g.drawImage(opacityScreen, 0, 0, null);
        g.setFont(new Font("", Font.BOLD, 40));
        g.setColor(Color.WHITE);
        g.drawString("LEVEL OVER", 200, 320);

        nextLevel.draw(g);
    }

    private void drawPauseScreen(Graphics g) {
        g.drawImage(opacityScreen, 0, 0, null);
        //pause string in the middle of the screen
        g.setFont(new Font("", Font.BOLD, 40));
        g.setColor(Color.WHITE);
        g.drawString("GAME PAUSED", 200, 320);
    }

    private void drawHighlight(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawRect(mouseX, mouseY, 32, 32);
    }

    private void drawSelectedTower(Graphics g) {  //draws the selected tower in the corner
        if(selectedTower != null) {
            g.drawImage(towerManager.getTowerImgs()[selectedTower.getTowerType()], mouseX, mouseY, null);
        }
    }

    @Override
    public void mouseClicked(int x, int y) {
        if(x >= 640) {  //check if only the mouse is on the sidebar
            this.actionBar.mouseClicked(x, y);
        } else if (!gamePaused && !levelOver){    //map area if game is not paused
            if(selectedTower != null) {   //clicked on empty grass
                if(isTileGrass(mouseX, mouseY)) { //checks if tower can be placed here
                    if(getTowerAt(mouseX, mouseY) == null){
                        towerManager.addTower(selectedTower, mouseX, mouseY); //passing what tower needs to be initialized

                        spendGold(selectedTower.getTowerType());  //take gold for the place tower

                        selectedTower = null;  //removing selected tile, so only one tower can be placed


                    }
                }
            } else {  //clicked on tower
                //get tower if exists on x y
                Tower t = getTowerAt(mouseX, mouseY);  //getting tower if exists

                actionBar.displayTowerInfo(t);

            }
        } else if (levelOver) {
            if(nextLevel.getBounds().contains(x, y)) {
                Constants.Level.setLEVEL(Constants.Level.LEVEL+1);
                LoadSave.SaveGame(Constants.Level.LEVEL);  //auto-save
                loadLevel();

                resetEverythingForNextLevel();
            }
        }

    }

    private void spendGold(int towerType) {
        actionBar.payForTower(towerType);
    }

    private Tower getTowerAt(int x, int y) {
        return towerManager.getTowerAt(x,y);
    }
    public void upgradeTower(Tower displayedTower) {
        towerManager.upgradeTower(displayedTower);
    }
    public void removeTower(Tower displayedTower) {
        towerManager.removeTower(displayedTower);
    }

    private boolean isTileGrass(int x, int y) {
        int id = level[y / 32][x / 32]; //get id of the 2D level array
        int tileType = game.getTileManager().getTile(id).getTileType();  //getting tile-type
        if(tileType == GRASS) {  //if grass return true
            return true;
        }

        return false;

    }

    @Override
    public void mouseMoved(int x, int y) {
//        buttonMenu.setMouseOver(false);
//
//        if(buttonMenu.getBounds().contains(x, y)) {
//            buttonMenu.setMouseOver(true);
//        }

        if(x >= 640) {  //check if only the mouse is on the sidebar
            this.actionBar.mouseMoved(x, y);
//            this.drawChosen = false;

        } else if (!gamePaused && !levelOver){  //map area if gam is not paused
//            this.drawChosen = true;
            mouseX = (x / 32)*32;
            mouseY = (y / 32)*32;

        } else if(levelOver) {
            nextLevel.setMouseOver(false);
            if(nextLevel.getBounds().contains(x, y)) {
                nextLevel.setMouseOver(true);
            }
        }
    }

    @Override
    public void mousePressed(int x, int y) {
//        if(buttonMenu.getBounds().contains(x, y)) {
//            buttonMenu.setMousePressed(true);
//        }
        if(x >= 640) {  //check if only the mouse is on the sidebar
            this.actionBar.mousePressed(x, y);
        } else if (levelOver){
            if(nextLevel.getBounds().contains(x,y)) {
                nextLevel.setMousePressed(true);
            }
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
//        resetButtons();
            this.actionBar.mouseReleased(x, y);  //no matter were the mouse was released, we need
        nextLevel.resetBooleans();
        //to reset the booleans
    }

    @Override
    public void mouseDragged(int x, int y) {
        if(x >= 640) {

        }
    }

    public TowerManager getTowerManager() {
        return towerManager;
    }
    public Tower getSelectedTower() {
        return selectedTower;
    }
    public EnemyManager getEnemyManager() {
        return enemyManager;
    }

    public ProjectileManager getProjectileManager() {
        return projectileManager;
    }

    public WaveManager getWaveManager() {
        return waveManager;
    }

    public void rewardPlayer(int enemyType) {
        this.actionBar.addGold(Constants.Enemies.GetGoldReward(enemyType));
    }

    public boolean isGamePaused() {
        return gamePaused;
    }


    public void resetEverythingForReplay() {
        //resetting everything in actionbar
        actionBar.resetEverythingForReplay();

        //resetting everything in managers
        enemyManager.reset();
        towerManager.reset();
        waveManager.reset();
        projectileManager.reset();

        //resetting everything in playing
        mouseX = 0;
        mouseY = 0;
        selectedTower = null;
        gamePaused = false;
    }
    public void resetEverythingForNextLevel() {
        //resetting everything in actionbar
        actionBar.resetEverythingForReplay();

        //resetting everything in managers
        enemyManager.reset();
        towerManager.reset();
        waveManager.reset();
        projectileManager.reset();

        //resetting everything in playing
        mouseX = 0;
        mouseY = 0;
        selectedTower = null;
        gamePaused = false;
        levelOver = false;
    }

    public boolean isLevelOver() {
        return levelOver;
    }


}
