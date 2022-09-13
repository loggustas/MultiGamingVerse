package Scenes;

import main.Game;
import main.GameScreen;

import java.awt.image.BufferedImage;

public class GameScene {   //this will be a superclass

    public Game game;
    public GameScreen gameScreen;

    protected int animationIndex;
    protected final int GAME_TICK = 20;
    protected int tick;
    public GameScene(Game game, GameScreen gameScreen) {

        this.game = game;
        this.gameScreen = gameScreen;
    }

    public Game getGame() {

        return this.game;
    }

    //adding these methods here, because we use them both in playing and editing
    protected void updateAnimationTick() {
        tick++;
        if(tick >= GAME_TICK) {
            tick = 0;
            animationIndex++;
            if(animationIndex >= 4) {
                animationIndex = 0;
            }
        }
    }

    protected boolean isAnimation(int spriteID) {
        return game.getTileManager().isSpriteAnimation(spriteID);
    }
    protected BufferedImage getSprite(int spriteID, int animationIndex) {
        return game.getTileManager().getAniSprite(spriteID, animationIndex);
    }
    protected BufferedImage getSprite(int spriteID) {
        return game.getTileManager().getSprite(spriteID);
    }
}
