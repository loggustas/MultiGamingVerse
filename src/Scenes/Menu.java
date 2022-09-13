package Scenes;

import helperMethods.LoadSave;
import main.Game;

import main.GameScreen;
import main.GameStates;


import ui.MyButton;


import java.awt.*;
import java.awt.image.BufferedImage;

import java.util.Random;



public class Menu extends GameScene implements SceneMethods{

    private BufferedImage img;
    private Random random;

    private MyButton buttonPlay, buttonAbout, buttonQuit;
//    private MyButton  buttonSettings;
//    private MyButton buttonEdit;

    public Menu(Game game, GameScreen gameScreen) {
        super(game, gameScreen);
        importImg();

        initButtons();
        this.random = new Random();
    }

    private void initButtons() {
        this.buttonPlay = new MyButton("Play", 350, 200, 180, 30);
        this.buttonAbout = new MyButton("About" , 350, 250, 180, 30);
        this.buttonQuit = new MyButton("Quit", 350, 300, 180, 30);
//        this.buttonEdit = new MyButton("Edit", 350, 400, 180, 30);
//        this.buttonSettings = new MyButton("Settings", 350, 350, 180, 30);

    }

    @Override
    public void render(Graphics g) {

        g.drawImage(img, 0, 0, null);

        g.setColor(new Color(153,0,0));
        g.setFont(new Font("Lucida Sans", Font.BOLD, 60));
        g.drawString("MultiGamingVerse", 160, 130);
        drawButtons(g);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if(buttonPlay.getBounds().contains(x, y)) {
            GameStates.setGameState(GameStates.CHOOSE_PLAYING);
        } else if(buttonQuit.getBounds().contains(x, y)) {
            System.exit(0);

        } else if(buttonAbout.getBounds().contains(x, y)) {
            GameStates.setGameState(GameStates.ABOUT);
        }
//        else if(buttonEdit.getBounds().contains(x, y)) {
//            GameStates.setGameState(GameStates.EDIT);
//        }
//        else if(buttonSettings.getBounds().contains(x, y)) {
//            GameStates.setGameState(GameStates.SETTINGS);
//        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        buttonPlay.setMouseOver(false);
        buttonAbout.setMouseOver(false);
        buttonQuit.setMouseOver(false);
//        buttonEdit.setMouseOver(false);
        //        buttonSettings.setMouseOver(false);


        if(buttonPlay.getBounds().contains(x, y)) {
            buttonPlay.setMouseOver(true);
        }
        if(buttonAbout.getBounds().contains(x, y)) {
            buttonAbout.setMouseOver(true);
        }
        if(buttonQuit.getBounds().contains(x, y)) {
            buttonQuit.setMouseOver(true);
        }
//        if(buttonEdit.getBounds().contains(x, y)) {
//            buttonEdit.setMouseOver(true);
//        }
//        if(buttonSettings.getBounds().contains(x, y)) {
//            buttonSettings.setMouseOver(true);
//        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if(buttonPlay.getBounds().contains(x, y)) {
            buttonPlay.setMousePressed(true);
        }
        if(buttonAbout.getBounds().contains(x, y)) {
            buttonAbout.setMousePressed(true);
        }
        if(buttonQuit.getBounds().contains(x, y)) {
            buttonQuit.setMousePressed(true);
        }
//        if(buttonEdit.getBounds().contains(x, y)) {
//            buttonEdit.setMousePressed(true);
//        }
//        if(buttonSettings.getBounds().contains(x, y)) {
//            buttonSettings.setMousePressed(true);
//        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        resetButtons();
    }

    @Override
    public void mouseDragged(int x, int y) {

    }

    private void resetButtons() {   //add any new buttons for resest
        buttonPlay.resetBooleans();
        buttonAbout.resetBooleans();
        buttonQuit.resetBooleans();
//        buttonEdit.resetBooleans();
//        buttonSettings.resetBooleans();
    }

    private void drawButtons(Graphics g) {
        this.buttonPlay.draw(g);
        this.buttonAbout.draw(g);
        this.buttonQuit.draw(g);
//        this.buttonEdit.draw(g);
//        this.buttonSettings.draw(g);

    }

    private void importImg() {   ///uzdeti menu kokia nuotrauka idomia
        img = LoadSave.getMenuScreen();
    }

}
