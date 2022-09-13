package main;

import java.awt.*;

public class Render {   //responsibble for all the rendering

    private Game game;

    public Render(Game game) {
        this.game = game;
    }

    public void render(Graphics g) {
        switch (GameStates.gameState) {
            case MENU:
                this.game.getMenu().render(g);
                break;
            case PLAYING:
                this.game.getPlaying().render(g);
                break;
//            case SETTINGS:
//                this.game.getSettings().render(g);
//                break;
            case EDIT:
//                this.game.getEditing().render(g);
                break;
            case GAME_OVER:
                this.game.getGameOver().render(g);
                break;
            case CHOOSE_PLAYING:
                this.game.getChoosePlaying().render(g);
                break;
            case YOU_WON:
                this.game.getYouWon().render(g);
                break;
            case ABOUT:
                this.game.getAbout().render(g);
                break;
        }
    }


}
