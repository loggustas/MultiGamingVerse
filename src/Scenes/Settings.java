package Scenes;

import main.Game;
import main.GameScreen;

import java.awt.*;

public class Settings extends GameScene implements SceneMethods{
    public Settings(Game game, GameScreen gameScreen) {
        super(game, gameScreen);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 880, 640);
        g.setColor(Color.blue);
        g.fillRect(440, 320, 320, 320);
    }

    @Override
    public void mouseClicked(int x, int y) {

    }

    @Override
    public void mouseMoved(int x, int y) {

    }

    @Override
    public void mousePressed(int x, int y) {

    }

    @Override
    public void mouseReleased(int x, int y) {

    }

    @Override
    public void mouseDragged(int x, int y) {

    }
}
