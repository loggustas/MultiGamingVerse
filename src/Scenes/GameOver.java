package Scenes;


import helperMethods.LoadSave;
import main.Game;
import main.GameScreen;
import main.GameStates;
import ui.MyButton;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameOver extends GameScene implements SceneMethods{

    private MyButton buttonReplay, buttonMenu, buttonQuit;
    private BufferedImage gameOverScreen;

    public GameOver(Game game, GameScreen gameScreen) {
        super(game, gameScreen);
        gameOverScreen = LoadSave.getGameOverScreen();
        intiButtons();

    }

    private void intiButtons() {
        buttonMenu = new MyButton("Menu", 360, 300, 160, 50);
        buttonReplay = new MyButton("Replay level", 360, 400, 160, 50);
        buttonQuit = new MyButton("Quit", 360, 500, 160, 50);
    }

    @Override
    public void render(Graphics g) {
        //game over screen
        g.drawImage(gameOverScreen, 0, 0, null);
        //buttons
        buttonReplay.draw(g);
        buttonMenu.draw(g);
        buttonQuit.draw(g);


    }
    private void replayGame() {
        //reset everything
        resetEverything();
        //change stat to playing
        GameStates.setGameState(GameStates.PLAYING);
    }
    private void resetEverything() {
        game.getPlaying().resetEverythingForReplay();
    }

    @Override
    public void mouseClicked(int x, int y) {
        if(buttonMenu.getBounds().contains(x, y)) {
            resetEverything();
            GameStates.setGameState(GameStates.MENU);
        } else if(buttonReplay.getBounds().contains(x, y)) {
            replayGame();
        } else if(buttonQuit.getBounds().contains(x, y)) {
            System.exit(0);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        buttonMenu.setMouseOver(false);
        buttonReplay.setMouseOver(false);
        buttonQuit.setMouseOver(false);
        if(buttonMenu.getBounds().contains(x, y)) {
            buttonMenu.setMouseOver(true);
        } else if(buttonReplay.getBounds().contains(x, y)) {
            buttonReplay.setMouseOver(true);
        } else if(buttonQuit.getBounds().contains(x, y)) {
            buttonQuit.setMouseOver(true);
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if(buttonMenu.getBounds().contains(x, y)) {
            buttonMenu.setMousePressed(true);
        } else if(buttonReplay.getBounds().contains(x, y)) {
            buttonReplay.setMousePressed(true);
        } else if(buttonQuit.getBounds().contains(x, y)) {
            buttonQuit.setMousePressed(true);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        buttonMenu.resetBooleans();
        buttonReplay.resetBooleans();
        buttonQuit.resetBooleans();
    }

    @Override
    public void mouseDragged(int x, int y) {

    }
}
