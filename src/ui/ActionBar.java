package ui;

import Scenes.Playing;
import helperMethods.Constants;
import helperMethods.LoadSave;
import main.GameStates;
import objects.Tower;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import static managers.TowerManager.TOWER_AMOUNT;

public class ActionBar extends Bar{

    private Playing playing;
    private MyButton buttonMenu, buttonCancel, buttonPause;
    private MyButton[] towerButtons;
    private Tower selectedTower;   //placeholder for selected tower(id will be -1 and type will be according to tower
    private Tower displayedTower;
    private MyButton sellTower, upgradeTower;

    private DecimalFormat formatter;

    private final int maxHealth = 500, startingGoldAmount = 100;
    private int gold = startingGoldAmount;
    private int health;
    private boolean showTowerCost;
    private int towerPriceType;


    public ActionBar(int x, int y, int width, int height, Playing playing) {
        super(x,y, width, height);
        this.playing = playing;
        formatter = new DecimalFormat("0.0");
        health = maxHealth;
        initButtons();  //creating buttons
    }

    public void resetEverythingForReplay() {
        health = maxHealth;
        towerPriceType = 0;
        showTowerCost = false;
        gold = startingGoldAmount;
        selectedTower = null;
        displayedTower = null;
    }

    public void looseHealth(int amount) {
        health = health - amount;
        if(health <= 0) {
            health = 0;
            GameStates.setGameState(GameStates.GAME_OVER);
        }
    }
    private void initButtons() {
        //regular buttons
        this.buttonMenu = new MyButton("Menu", 800 , 615 , 70, 20);
        this.buttonPause = new MyButton("Pause", 725, 615, 70, 20);
        this.buttonCancel = new MyButton("Cancel", 650, 615, 70, 20);

        towerButtons = new MyButton[TOWER_AMOUNT];

        int w = 50;
        int h = 50;
        int xStart = 680;
        int yStart = 40;
        int yOffset = (int) (h * 1.1f);

        for(int i = 0 ; i < towerButtons.length ; i++) {
            //i will be id and it will be matching towerID
            towerButtons[i] = new MyButton("", xStart, yStart + yOffset * i, w, h, i);
        }

        sellTower = new MyButton("Sell", 730, 470, 60, 20);  //530 buvo
        upgradeTower = new MyButton("Upgrade", 800, 470, 60, 20);
    }



    private void drawButtons(Graphics g) {
        this.buttonMenu.draw(g);
        if(!playing.isLevelOver()) {
            this.buttonPause.draw(g);
        }
        BufferedImage[] imgs = playing.getTowerManager().getTowerImgs();
        for(MyButton b : towerButtons) {
            g.setColor(new Color(208, 206, 241));
            g.fillRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());

            g.drawImage(imgs[b.getId()], b.getX(), b.getY(), b.getWidth(), b.getHeight(), null);

            drawButtonFeedback(g, b);
        }
        if(playing.getSelectedTower() != null) {
            this.buttonCancel.draw(g);
        }
    }


    public void draw(Graphics g) {

        g.drawImage(LoadSave.getActionBarImage(), 640, 0, null);

        //buttons
        drawButtons(g);

        //displayed tower
        drawDisplayedTower(g);

        // wave info
        drawWaveInfo(g);

        //draw gold
        drawGoldAmount(g);

        //draw tower cost info
        drawTowerCost(g);

        //health display
        drawHealth(g);
    }

    private void drawHealth(Graphics g) {
        g.setFont(new Font("", Font.BOLD, 20));  //so all info has the same font
        g.setColor(Color.black);
        g.drawString("Health: " + health + " / " + maxHealth, 660,530);
    }

    private void drawTowerCost(Graphics g) {
        if(showTowerCost) {
            g.setColor(new Color(241, 229, 206));
            g.fillRect(755, 5, 120, 80);
            g.setColor(Color.black);
            g.drawRect(755, 5, 120, 80);

            g.setFont(new Font("", Font.BOLD, 17));  //so all info has the same font
            g.drawString("" + getTowerPriceName(), 760, 25);
            g.drawString("Price: " + getTowerPrice() + "g",760, 50);
            g.drawString(getTowerSpecification(), 760, 75);

            if(!hasEnoughGold(towerPriceType)) {
                g.setColor(Color.red);
                g.drawString("No gold!", 770, 110);
            }
        }

    }

    private String getTowerSpecification() {
        return Constants.Towers.GetSpecification(towerPriceType);
    }

    private int getTowerPrice() {
        return Constants.Towers.GetTowerPrice(towerPriceType);
    }

    private String getTowerPriceName() {
        return Constants.Towers.GetName(towerPriceType);
    }

    private void drawGoldAmount(Graphics g) {
        g.setColor(new Color(255, 233, 0));
        g.drawString("Gold: " + gold + "g",645,25);
    }

    private void drawWaveInfo(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("", Font.BOLD, 20));  //so all info has the same font
        drawWaveTimerInfo(g);
        drawWavesLeftInfo(g);
        drawLevelInfo(g);
    }

    private void drawLevelInfo(Graphics g) {
        g.drawString("Level " + Constants.Level.LEVEL + " / " + Constants.Level.AMOUNT, 660, 555);
    }

    private void drawWavesLeftInfo(Graphics g) {
        int current = playing.getWaveManager().getWaveIndex();
        int size = playing.getWaveManager().getWaves().size() - 1;
        if(current == 0) {
            g.drawString("Wave " + (current + 1) + " / " + size, 660, 580);
        } else {
            g.drawString("Wave " + current + " / " + size, 660, 580);
        }
    }

    private void drawWaveTimerInfo(Graphics g) {
        if(playing.getWaveManager().isWaveTimerStarted()) {
            float timeLeft = playing.getWaveManager().getTimeLeft();
            String formattedText = formatter.format(timeLeft);
            g.setColor(Color.black);

            g.drawString("Time Left: " + formattedText, 660, 605);
        }
    }

    public void displayTowerInfo(Tower t) {
        displayedTower = t;

    }

    private void drawDisplayedTower(Graphics g) {
        if(displayedTower != null) {

            g.setColor(new Color(241, 229, 206));
            g.fillRect(645, 405, 225, 90);

            g.setColor(Color.BLACK);
            g.drawRect(645, 405, 225, 90);
            g.drawRect(655, 415, 60, 60);
            g.drawImage(playing.getTowerManager().getTowerImgs()[displayedTower.getTowerType()], 655, 415, 60, 60, null);

            g.setFont(new Font("", Font.BOLD, 15));
            g.drawString("" + Constants.Towers.GetName(displayedTower.getTowerType()), 725, 425);
            g.drawString("Tier: " + displayedTower.getTier(), 725, 445);

            drawSelectedTowerBorder(g);
            drawDisplayedTowerRange(g);

            //sell button
            sellTower.draw(g);

            //upgrade button
            if(displayedTower.getTier() < 3  && gold >= getUpgradeAmount(displayedTower)) {
                upgradeTower.draw(g);
            }

            if(sellTower.isMouseOver()) {
                //sell for x amount
                g.setColor(Color.RED);
                g.drawString("Sell for: " + getSellAmount(displayedTower) + "g", 725, 465);
            } else if(upgradeTower.isMouseOver() && gold >= getUpgradeAmount(displayedTower)) {
                //upgrade for x amount
                g.setColor(Color.blue);
                g.drawString("Upgrade for: " + getUpgradeAmount(displayedTower) + "g", 725, 465);

            }
        }
    }

    private int getUpgradeAmount(Tower displayedTower) {
        return (int)(Constants.Towers.GetTowerPrice(displayedTower.getTowerType()) * 0.5f);
    }

    private int getSellAmount(Tower displayedTower) {
        int upgradeCost = (int)((displayedTower.getTier() - 1) * getUpgradeAmount(displayedTower) * 0.5f);  //want to increase price by half the upgraded price
        return (int)(Constants.Towers.GetTowerPrice(displayedTower.getTowerType()) / 2) + upgradeCost;
    }

    private void drawDisplayedTowerRange(Graphics g) {
        g.setColor(Color.red);
        g.drawOval(displayedTower.getX() + 16 - (int)displayedTower.getRange(), displayedTower.getY() + 16 - (int)displayedTower.getRange(), (int)displayedTower.getRange() * 2, (int)displayedTower.getRange() * 2);
    }

    private void drawSelectedTowerBorder(Graphics g) {
        g.setColor(Color.RED);
        g.drawRect(displayedTower.getX(), displayedTower.getY(), 32, 32);
    }

    private void sellTowerClicked() {
        int upgradeCost = (int)((displayedTower.getTier() - 1) * getUpgradeAmount(displayedTower) * 0.5f);  //want to increase price by half the upgraded price

        playing.removeTower(displayedTower);
        gold = gold + upgradeCost + Constants.Towers.GetTowerPrice(displayedTower.getTowerType()) / 2;  //adding upgradeCosts together with initial tower value
        displayedTower = null;  //tower destroyed,displayed tower must be null
    }

    private void upgradeTowerClicked() {
        playing.upgradeTower(displayedTower);
        gold = gold - getUpgradeAmount(displayedTower);
    }

    private void togglePauseGame() {
        //stops update method from being executed in playing object
        //playing.setGamePaused(true);

        playing.setGamePaused(!playing.isGamePaused());
        if(playing.isGamePaused()) {
            buttonPause.setText("Unpause");
        } else {
            buttonPause.setText("Pause");
        }
    }

    public void mouseClicked(int x, int y) {
        if(buttonMenu.getBounds().contains(x, y)) {
            playing.resetEverythingForReplay();
            GameStates.setGameState(GameStates.MENU);
        } else if(buttonCancel.getBounds().contains(x, y) && playing.getSelectedTower() != null) {
            playing.setSelectedTower(null);  //selected tower in playing set to null
        }  else if(buttonPause.getBounds().contains(x, y) && !playing.isLevelOver()) { // need tho check if level is not over, only then we can pause
            togglePauseGame();
        }
        else {
            if(playing.isGamePaused() || playing.isLevelOver()) { //if game is paused don't go further
                return;
            }
            if(displayedTower != null) {  //only if displayed tower is available
                if(sellTower.getBounds().contains(x, y)) {
                    sellTowerClicked();
                    return;
                } else if(upgradeTower.getBounds().contains(x, y) && displayedTower.getTier() < 3 && gold >= getUpgradeAmount(displayedTower)) {
                    upgradeTowerClicked();
                    return;
                }
            }

            for(MyButton b : towerButtons) {
                if(b.getBounds().contains(x, y)) {

                    if(!hasEnoughGold(b.getId())) {  //if doesn't have enough gold just don't do anything and instantly return
                        return;
                    }

                    selectedTower = new Tower(0, 0, -1, b.getId()); //id -1 ant tower type according to the type constant, BUTTON ID IS THE SAME AS TOWER TYPE
                    playing.setSelectedTower(selectedTower);  //updating selected tower in the playing class
                    return;
                }
            }
        }
    }

    public void mouseMoved(int x, int y) {
        buttonMenu.setMouseOver(false);  //resetting all buttons
        buttonCancel.setMouseOver(false);
        buttonPause.setMouseOver(false);
        showTowerCost = false;
        sellTower.setMouseOver(false);
        upgradeTower.setMouseOver(false);
        for (MyButton b : towerButtons) {
            b.setMouseOver(false);
        }

        if(buttonMenu.getBounds().contains(x, y)) {
            buttonMenu.setMouseOver(true);
        } else if(buttonCancel.getBounds().contains(x, y)) {
            buttonCancel.setMouseOver(true);
        } else if(buttonPause.getBounds().contains(x, y)) {
            buttonPause.setMouseOver(true);
        }
        else {
            if(displayedTower != null) { //check only if displayedTower is true
                if(sellTower.getBounds().contains(x, y)) {
                    sellTower.setMouseOver(true);
                    return;
                } else if(upgradeTower.getBounds().contains(x, y) && displayedTower.getTier() < 3) {
                    upgradeTower.setMouseOver(true);
                    return;
                }
            }
            for (MyButton b : towerButtons) {
                if(b.getBounds().contains(x, y)){

                    b.setMouseOver(true);
                    showTowerCost = true;   //showing towerPrice info
                    towerPriceType = b.getId();
                    return;
                }
            }
        }
    }

    private boolean hasEnoughGold(int towerType) {
        if(this.gold >= Constants.Towers.GetTowerPrice(towerType)) {  //if has enough gold for the tower, return true
            return true;
        }
        return false;  //else return false
    }

    public void mousePressed(int x, int y) {
        if(buttonMenu.getBounds().contains(x, y)) {
            buttonMenu.setMousePressed(true);
        } else if(buttonCancel.getBounds().contains(x, y)) {
            buttonCancel.setMousePressed(true);
        } else if(buttonPause.getBounds().contains(x, y)) {
            buttonPause.setMousePressed(true);
        }
        else {
            if(displayedTower != null) {
                if(sellTower.getBounds().contains(x, y)) {
                    sellTower.setMousePressed(true);
                    return;
                } else if(upgradeTower.getBounds().contains(x, y)  && displayedTower.getTier() < 3) {
                    upgradeTower.setMousePressed(true);
                    return;
                }
            }
            for(MyButton b : towerButtons) {
                if(b.getBounds().contains(x, y)) {
                    b.setMousePressed(true);
                    return;
                }
            }
        }
    }

    public void mouseReleased(int x, int y) {
        resetButtons();
    }

    private void resetButtons() {     ///add any new buttons
        buttonMenu.resetBooleans();
        buttonCancel.resetBooleans();
        buttonPause.resetBooleans();

        sellTower.resetBooleans();
        upgradeTower.resetBooleans();

        for (MyButton b : towerButtons) {
            b.resetBooleans();
        }
    }


    public void payForTower(int towerType) {
        this.gold = this.gold - Constants.Towers.GetTowerPrice(towerType);  //spend gold
    }

    public void addGold(int goldReward) {
        this.gold = this.gold + goldReward;
    }
    public int getHealth() {
        return health;
    }


}
