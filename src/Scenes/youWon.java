package Scenes;

import helperMethods.Constants;
import helperMethods.LoadSave;
import main.Game;
import main.GameScreen;
import main.GameStates;
import ui.MyButton;

import java.awt.*;
import java.awt.image.BufferedImage;

public class youWon extends GameScene implements SceneMethods{

    private MyButton buttonMenu, buttonQuit, buttonNewGame;
    private BufferedImage youWonScreen;

    public youWon(Game game, GameScreen gameScreen) {
        super(game, gameScreen);
        youWonScreen = LoadSave.getYouWonScreen();
        initButtons();
    }

    private void initButtons() {
        buttonMenu = new MyButton("Menu", 360, 300, 160, 50);
        buttonNewGame = new MyButton("New game", 360, 400, 160, 50);
        buttonQuit = new MyButton("Quit", 360, 500, 160, 50);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(youWonScreen, 0, 0, null);

        buttonMenu.draw(g);
        buttonNewGame.draw(g);
        buttonQuit.draw(g);

    }

    @Override
    public void mouseClicked(int x, int y) {
        if(buttonMenu.getBounds().contains(x, y)) {
            resetEverything();
            GameStates.setGameState(GameStates.MENU);
        } else if(buttonNewGame.getBounds().contains(x, y)) {
            newGame();
        } else if(buttonQuit.getBounds().contains(x, y)) {
            System.exit(0);
        }
    }

    private void newGame() {
        resetEverything();
        //reset progress

        LoadSave.NewGame();
        Constants.Level.setLEVEL(1);
        game.getPlaying().loadLevelAfterSave();

        GameStates.setGameState(GameStates.PLAYING);
    }

    private void resetEverything() {
        game.getPlaying().resetEverythingForReplay();
    }

    @Override
    public void mouseMoved(int x, int y) {
        buttonMenu.setMouseOver(false);
        buttonNewGame.setMouseOver(false);
        buttonQuit.setMouseOver(false);
        if(buttonMenu.getBounds().contains(x, y)) {
            buttonMenu.setMouseOver(true);
        } else if(buttonNewGame.getBounds().contains(x, y)) {
            buttonNewGame.setMouseOver(true);
        } else if(buttonQuit.getBounds().contains(x, y)) {
            buttonQuit.setMouseOver(true);
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if(buttonMenu.getBounds().contains(x, y)) {
            buttonMenu.setMousePressed(true);
        } else if(buttonNewGame.getBounds().contains(x, y)) {
            buttonNewGame.setMousePressed(true);
        } else if(buttonQuit.getBounds().contains(x, y)) {
            buttonQuit.setMousePressed(true);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        buttonMenu.resetBooleans();
        buttonNewGame.resetBooleans();
        buttonQuit.resetBooleans();
    }

    @Override
    public void mouseDragged(int x, int y) {

    }
}
