package Scenes;

import helperMethods.LoadSave;
import main.Game;
import main.GameScreen;
import objects.PathPoint;
import objects.Tile;
import ui.ToolBar;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import static helperMethods.Constants.Tiles.ROAD;

public class Editing extends GameScene implements SceneMethods{

    private int[][] level;

    private int mouseX, mouseY;     //needed for editing
    private ToolBar toolBar;
    private Tile chosenTile;
    private boolean drawChosen;
    private int lastTileX, lastTileY, lastTileId;
    private PathPoint start, finish;


    public Editing(Game game, GameScreen gameScreen) {
        super(game, gameScreen);
        loadDefaultLevel();     //ta lvl uzkraus

        this.drawChosen = false;

        this.toolBar = new ToolBar(640, 0, 240, 640, this);
    }

    public void saveLevel() {
//        LoadSave.SaveLevel("first", level, start, finish);
//        LoadSave.SaveLevel("second", level, start, finish);
//        LoadSave.SaveLevel("third", level, start, finish);
//        LoadSave.SaveLevel("fourth", level, start, finish);
//        LoadSave.SaveLevel("fifth", level, start, finish);
//        LoadSave.SaveLevel("sixth", level, start, finish);
//          LoadSave.SaveLevel("seventh", level, start, finish);
//        LoadSave.SaveLevel("eight", level, start, finish);
//        LoadSave.SaveLevel("ninth", level, start, finish);
//        LoadSave.SaveLevel("tenth", level, start, finish);


        //game.getPlaying().setLevel(level, start, finish);   //seting the playing level as the same
    }
    private void loadDefaultLevel() {
//        level = LoadSave.GetLevelData("third");
        String name = "fifth";
//        ArrayList<PathPoint> points = LoadSave.GetLevelPathPoints("third");
        level = LoadSave.GetLevelData(name);
        ArrayList<PathPoint> points = LoadSave.GetLevelPathPoints(name);
//        level = LoadSave.GetLevelData("second");
//        ArrayList<PathPoint> points = LoadSave.GetLevelPathPoints("second");
        start = points.get(0);
        finish = points.get(1);

    }

    public void update() {
        updateAnimationTick();
    }

    public void setChosenTile(Tile tile) {
        this.chosenTile = tile;
        drawChosen = true;
    }
    private void drawChosenTile(Graphics g) {
        if(chosenTile != null && drawChosen) {
            g.drawImage(chosenTile.getSprite(), mouseX, mouseY, 32, 32, null);
        }
    }

    private void changeTile(int x, int y) {
        if(chosenTile != null) {
            int tileX = x / 32;
            int tileY = y / 32;

            if(chosenTile.getId() >= 0) {  //if tile is a normal tile

                if(lastTileX == tileX && lastTileY == tileY && lastTileId == chosenTile.getId()) {
                    return;
                }
                lastTileX = tileX;
                lastTileY = tileY;
                lastTileId = chosenTile.getId();

                this.level[tileY][tileX] = chosenTile.getId();
            } else {
                int id = level[tileY][tileX];  //the tile that is to be overdrawn by chosen tile
                if(game.getTileManager().getTile(id).getTileType() == ROAD){  //only can place on the roads

                    if(chosenTile.getId() == -1) {    //if chosen tile is start, set start coordinates
                        start = new PathPoint(tileX, tileY);
                    } else {
                        finish = new PathPoint(tileX, tileY);
                    }
                }
            }

        }

    }

    @Override
    public void render(Graphics g) {

        for(int y = 0 ; y < level.length ; y++){
            for(int x = 0 ; x < level[y].length ; x++) {

                if(isAnimation(level[y][x])) {
                    g.drawImage(getSprite(level[y][x], animationIndex), x * 32, y * 32, null);
                } else {
                    g.drawImage(getSprite(level[y][x]), x * 32, y * 32, null);
                }
            }
        }
        this.toolBar.draw(g);
        drawChosenTile(g);
        drawPathPoints(g);
    }

    private void drawPathPoints(Graphics g) {
        if(start != null) {
            g.drawImage(toolBar.getStart(), start.getxCord()*32 ,start.getyCord()*32, 32, 32, null);
        }
        if(finish != null) {
            g.drawImage(toolBar.getFinish(), finish.getxCord()*32 ,finish.getyCord()*32, 32, 32, null);

        }
    }


    @Override
    public void mouseClicked(int x, int y) {
        if(x >= 640) {  //check if only the mouse is on the sidebar
            this.toolBar.mouseClicked(x, y);
        } else {
            changeTile(mouseX, mouseY);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        if(x >= 640) {  //check if only the mouse is on the sidebar
            this.toolBar.mouseMoved(x, y);
            this.drawChosen = false;
        } else {
            this.drawChosen = true;
            mouseX = (x / 32)*32;
            mouseY = (y / 32)*32;
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if(x >= 640) {  //check if only the mouse is on the sidebar
            this.toolBar.mousePressed(x, y);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        this.toolBar.mouseReleased(x, y);  //no matter were the mouse was released, we need
    }

    @Override
    public void mouseDragged(int x, int y) {
        if(x >= 640) {

        } else if(x <= 640 && x >= 0 && y >= 0 && y <= this.gameScreen.height) {
            changeTile(x, y);
        }
    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_R) {
            toolBar.rotateSprite();
        }
    }

}
