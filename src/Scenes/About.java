package Scenes;

import helperMethods.LoadSave;
import main.Game;
import main.GameScreen;
import main.GameStates;
import ui.MyButton;

import java.awt.*;
import java.awt.image.BufferedImage;

public class About extends GameScene implements SceneMethods{

    private BufferedImage menuScreen;
    private MyButton buttonMenu;

    public About(Game game, GameScreen gameScreen) {
        super(game, gameScreen);
        menuScreen = LoadSave.getMenuScreen();
        intiButtons();
    }
    private void intiButtons() {
        buttonMenu = new MyButton("Menu", 350, 390, 180, 30);

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(menuScreen, 0, 0, null);

        g.setColor(new Color(153,0,0));
        g.setFont(new Font("Lucida Sans", Font.BOLD, 60));
        g.drawString("MultiGamingVerse", 160, 130);

        drawButtons(g);
        drawInfo(g);

    }

    private void drawButtons(Graphics g) {
        buttonMenu.draw(g);
    }
    private void drawInfo(Graphics g) {
        g.setColor(new Color(241, 229, 206));
        g.fillRect(290, 160, 300, 200);

        g.setColor(Color.BLACK);
        g.drawRect(290, 160, 300, 200);

        g.setFont(new Font("", Font.BOLD, 15));

        //intro
        g.drawString("Hello!", 420, 180);
        g.drawString("This is a simple and short tower defense", 298, 210);
        g.drawString("game called MultiGamingVerse, because ", 298, 230);
        g.drawString("it's characters are teleported from other", 298, 250);
        g.drawString("gaming universes! Hope you enjoy it, and", 298, 270);
        g.drawString("see you at the end!", 298, 290);

        g.drawString("Sincerely,", 298, 320);
        g.drawString("The dev :)", 298, 350);


    }

    @Override
    public void mouseClicked(int x, int y) {
        if(buttonMenu.getBounds().contains(x, y)) {
            GameStates.setGameState(GameStates.MENU);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        buttonMenu.setMouseOver(false);
        if(buttonMenu.getBounds().contains(x, y)) {
            buttonMenu.setMouseOver(true);
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if(buttonMenu.getBounds().contains(x, y)) {
            buttonMenu.setMousePressed(true);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        buttonMenu.resetBooleans();

    }

    @Override
    public void mouseDragged(int x, int y) {

    }
}
