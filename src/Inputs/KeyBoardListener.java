package Inputs;

import main.Game;
import main.GameStates;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static main.GameStates.*;  //visas constantas importina

public class KeyBoardListener implements KeyListener {
    private Game game;
    public KeyBoardListener(Game game) {
        this.game = game;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    // developer feature
    @Override
    public void keyPressed(KeyEvent e) {
//        if(GameStates.gameState == EDIT) {
//            game.getEditing().keyPressed(e);
//        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
