package Scenes;

import helperMethods.Constants;
import helperMethods.LoadSave;
import main.Game;
import main.GameScreen;
import main.GameStates;
import ui.MyButton;

import java.awt.*;
import java.awt.image.BufferedImage;

public class choosePlaying extends GameScene implements SceneMethods{

    private BufferedImage img;
    private MyButton newGame, continueProgress;

    public choosePlaying(Game game, GameScreen gameScreen) {
        super(game, gameScreen);
        importImg();
        initButtons();
    }

    private void initButtons() {
        this.newGame = new MyButton("New Game", 350, 250, 180, 30);
        this.continueProgress = new MyButton("Continue", 350, 300, 180, 30);
    }

    private void importImg() {   ///uzdeti menu kokia nuotrauka idomia
        img = LoadSave.getMenuScreen();
    }
    @Override
    public void render(Graphics g) {
        g.drawImage(img, 0, 0, null);

        g.setColor(new Color(153,0,0));
        g.setFont(new Font("Lucida Sans", Font.BOLD, 60));
        g.drawString("MultiGamingVerse", 160, 130);
        drawButtons(g);
    }

    private void drawButtons(Graphics g) {
        newGame.draw(g);
        continueProgress.draw(g);
    }

    @Override
    public void mouseClicked(int x, int y) {

        if(newGame.getBounds().contains(x, y)) {

            LoadSave.NewGame();
            Constants.Level.setLEVEL(1);
            game.getPlaying().loadLevelAfterSave();

            GameStates.setGameState(GameStates.PLAYING);

        } else if(continueProgress.getBounds().contains(x, y)) {

            Constants.Level.setLEVEL(LoadSave.GetProgress());
            game.getPlaying().loadLevelAfterSave();

            GameStates.setGameState(GameStates.PLAYING);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        newGame.setMouseOver(false);
        continueProgress.setMouseOver(false);

        if(newGame.getBounds().contains(x, y)) {
            newGame.setMouseOver(true);
        } else if(continueProgress.getBounds().contains(x, y)) {
            continueProgress.setMouseOver(true);
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if(newGame.getBounds().contains(x, y)) {
            newGame.setMousePressed(true);
        } else if(continueProgress.getBounds().contains(x, y)) {
            continueProgress.setMousePressed(true);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        newGame.resetBooleans();
        continueProgress.resetBooleans();
    }

    @Override
    public void mouseDragged(int x, int y) {

    }
}
