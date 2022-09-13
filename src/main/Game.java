package main;

import Scenes.*;
import helperMethods.LoadSave;
import managers.TileManager;


import javax.swing.*;


public class Game extends JFrame implements Runnable{


    //thread
    private Thread gameThread;

    private final double FPS_SET = 60.0;
    private final double UPS_SET = 60.0;



    //classes
    private GameScreen gameScreen;
    private Render render;
    private Menu menu;
    private Playing playing;
//    private Settings settings;
//    private Editing editing;
    private GameOver gameOver;
    private choosePlaying choosePlaying;
    private youWon youWon;
    private About about;

    private TileManager tileManager;


    public Game() {
        initClasses();
        createDefaultLevel();

        setTitle("MultiGamingVerse");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);    //centering
        setResizable(false);

        this.add(gameScreen);    ///adds the panel to the frame
        pack();

        setVisible(true);
    }

    private void initClasses() {
        this.tileManager = new TileManager();
        this.render = new Render(this);
        this.gameScreen = new GameScreen(this);
        this.menu = new Menu(this, gameScreen);
        this.playing = new Playing(this, gameScreen);
//        this.settings = new Settings(this, gameScreen);
//        this.editing = new Editing(this, gameScreen);
        this.gameOver = new GameOver(this, gameScreen);
        this.choosePlaying = new choosePlaying(this, gameScreen);
        this.youWon = new youWon(this, gameScreen);
        this.about = new About(this, gameScreen);
    }

    private void createDefaultLevel() {
        int[] arr = new int[400];
        for(int i = 0 ; i < arr.length ; i++) {
            arr[i] = 0;
        }
        LoadSave.CreateLevel("defaultLevel", arr);
    }

    private void start(){
        gameThread = new Thread(this);

        gameThread.start();
    }

    private void updateGame() {
        switch (GameStates.gameState) {
            case EDIT:
//                editing.update();
                break;
            case SETTINGS:
                break;
            case PLAYING:
                playing.update();  //updating game (enemies, towers and everything else)
                break;
            case MENU:
                break;
            default:
                break;
        }
    }

    public static void main(String[] args) {

        Game game = new Game();
        game.gameScreen.initInputs();
        game.start();
    }

    @Override
    public void run() {
        //game loop

        double timePerFrame = 1000000000.0 / FPS_SET;    //laikas kiek uztruka 1 frame
        double timePerUpdate = 1000000000.0 / UPS_SET;

        long lastUpdate = System.nanoTime();
        long lastFrame = System.nanoTime();         //paskutinio frame laikas

        long lastTimeCheck = System.currentTimeMillis();

        int frames = 0;
        int updates = 0;

        long now;

        while (true) {
            //render
            now = System.nanoTime();

            if(now - lastFrame >= timePerFrame){
                this.gameScreen.repaint();
                lastFrame = now;
                frames++;
            }

            //update
            if(now - lastUpdate >= timePerUpdate) {
                lastUpdate = now;
                updateGame();
                updates++;

            }

            if(System.currentTimeMillis() - lastTimeCheck >= 1000) {
                frames = 0;
                updates = 0;
                lastTimeCheck = System.currentTimeMillis();
            }

        }
    }

    //Getters and setter
    public Render getRender() {
        return this.render;
    }

    public Playing getPlaying() {
        return playing;
    }

//    public Settings getSettings() {
//        return settings;
//    }

//    public Editing getEditing() {
//        return editing;
//    }

    public Menu getMenu() {
        return menu;
    }
    public TileManager getTileManager() {
        return tileManager;
    }

    public double getUPS_SET() {
        return UPS_SET;
    }

    public GameOver getGameOver() {
        return gameOver;
    }

    public choosePlaying getChoosePlaying() {
        return choosePlaying;
    }

    public youWon getYouWon() {
        return youWon;
    }

    public About getAbout() {
        return about;
    }
}
