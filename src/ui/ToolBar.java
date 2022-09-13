package ui;

import Scenes.Editing;
import helperMethods.LoadSave;
import main.GameStates;
import objects.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ToolBar extends Bar{

    private Editing editing;

    private MyButton buttonMenu, buttonSave;

    private Tile chosenTile;

//    private ArrayList<MyButton> tileButtons = new ArrayList<>();
    private Map<MyButton, ArrayList<Tile>> hashMap = new HashMap<MyButton, ArrayList<Tile>>();
    private MyButton bGrass, bWater, bRoads, bRoadCorners, bWaterCorners, bWaterSides, bWaterIslands;
    private MyButton currentButton; //for rotations
    private MyButton bStart, bFinish;
    private BufferedImage start, finish;
    private int currentRotationIndex = 0; //for rotations

    public ToolBar(int x, int y, int width, int height, Editing editing) {
        super(x,y, width, height);
        this.editing = editing;
        initButtons();  //creating buttons
        initStartFinishImgs();
    }

    private void initStartFinishImgs() {
        start = LoadSave.getSpriteAtlas().getSubimage(0, 4*32, 32, 32);
        finish = LoadSave.getSpriteAtlas().getSubimage(32, 4*32, 32, 32);
    }

    private void initButtons() {
        //regular buttons
        this.buttonMenu = new MyButton("Menu", 805 , 615 , 70, 20);
        this.buttonSave = new MyButton("Save", 730, 615, 70, 20);
        //tile Buttons
        int w = 50;
        int h = 50;
        int xStart = 680;
        int yStart = 20;
        int yOffset = (int) (h * 1.1f);
//        for(int i = 0 ; i < editing.getGame().getTileManager().tiles.size() ; i++) {
//            tileButtons.add(new MyButton(editing.getGame().getTileManager().tiles.get(i).getName(), xStart, yStart + yOffset * i, w, h, i));
//        }
        int i = 0;

        bGrass = new MyButton("Grass", xStart, yStart, w, h, i++);
        bWater = new MyButton("Water", xStart, yStart + yOffset * i, w, h, i++);


        initMapButton(bRoads, editing.getGame().getTileManager().roads, xStart, yStart, yOffset, w, h, i++);
        initMapButton(bRoadCorners, editing.getGame().getTileManager().roadCorners, xStart, yStart, yOffset, w, h, i++);
        initMapButton(bWaterCorners, editing.getGame().getTileManager().waterCorners, xStart, yStart, yOffset, w, h, i++);
        initMapButton(bWaterSides, editing.getGame().getTileManager().waterSides, xStart, yStart, yOffset, w, h, i++);
        initMapButton(bWaterIslands, editing.getGame().getTileManager().waterIslands, xStart, yStart, yOffset, w, h, i++);

        bStart = new MyButton("Start", xStart, yStart + yOffset * i, w, h, i++);
        bFinish = new MyButton("Finish", xStart, yStart + yOffset * i, w, h, i++);


    }
    private void initMapButton(MyButton b, ArrayList<Tile> list, int x, int y, int yOff, int w, int h, int number) {
        b = new MyButton("", x, y + yOff * number, w, h, number);
        hashMap.put(b, list);
    }

    private void saveLevel() {
        editing.saveLevel();

    }

    public void rotateSprite() {
        if(currentButton != bGrass && currentButton != bWater) {
            currentRotationIndex++;
            if(currentRotationIndex >= hashMap.get(currentButton).size()) {
                currentRotationIndex = 0;
            }
            chosenTile = hashMap.get(currentButton).get(currentRotationIndex);
            System.out.println(chosenTile.getId());
            editing.setChosenTile(chosenTile);
        }
    }

    private void drawButtons(Graphics g) {
        this.buttonMenu.draw(g);
        this.buttonSave.draw(g);

        drawNormalButtons(g,bGrass);
        drawNormalButtons(g, bWater);
//        drawTileButtons(g);
        drawChosenTile(g);

        drawMapButtons(g);
        drawPathButtons(g, bStart, start);
        drawPathButtons(g, bFinish, finish);
    }

    private void drawPathButtons(Graphics g, MyButton b, BufferedImage img) {
        g.drawImage(img, b.getX(), b.getY(), b.getWidth(), b.getHeight(), null);
        drawButtonFeedback(g,b);
    }

    private void drawNormalButtons(Graphics g, MyButton b) {
        g.drawImage(getButtImg(b.getId()), b.getX(), b.getY(), b.getWidth(), b.getHeight(), null);
        drawButtonFeedback(g, b);
    }


    private void drawMapButtons(Graphics g) {
        for(Map.Entry<MyButton, ArrayList<Tile>> entry : hashMap.entrySet()) {
            MyButton b = entry.getKey();
            BufferedImage img = entry.getValue().get(0).getSprite();

            g.drawImage(img, b.getX(), b.getY(), b.getWidth(), b.getHeight(), null);

            drawButtonFeedback(g, b);
        }
    }

    private void drawChosenTile(Graphics g) {
        if(chosenTile != null) {   //if initialized
            g.drawImage(chosenTile.getSprite(), 780, 20, 50, 50, null);
            g.setColor(Color.blue);
            g.drawRect(780, 20, 50, 50);
        }
    }

    private BufferedImage getButtImg(int id) {
        return editing.getGame().getTileManager().getSprite(id);
    }

    public void draw(Graphics g) {
        g.drawImage(LoadSave.getActionBarImage(), 640, 0, null);

        //buttons
        drawButtons(g);
    }


    public void mouseClicked(int x, int y) {
        if(buttonMenu.getBounds().contains(x, y)) {
            GameStates.setGameState(GameStates.MENU);
        } else if(buttonSave.getBounds().contains(x, y)) {
            saveLevel();
        } else if(bWater.getBounds().contains(x, y)) {
            chosenTile = editing.getGame().getTileManager().getTile(bWater.getId());
            editing.setChosenTile(chosenTile);
            currentButton = bWater;
            return;

        } else if(bGrass.getBounds().contains(x, y)) {
            chosenTile = editing.getGame().getTileManager().getTile(bGrass.getId());
            editing.setChosenTile(chosenTile);
            currentButton = bGrass;
            return;
        } else if(bStart.getBounds().contains(x, y)) {
            chosenTile = new Tile(start, -1, -1); ///temporary chosen tile
            editing.setChosenTile(chosenTile);
        } else if(bFinish.getBounds().contains(x, y)) {
            chosenTile = new Tile(finish, -2, -2); ///temporary chosen tile
            editing.setChosenTile(chosenTile);
        }

        else {
            for(MyButton b : hashMap.keySet()) {
                if(b.getBounds().contains(x,y)) {
                    chosenTile = hashMap.get(b).get(0);
                    editing.setChosenTile(chosenTile);
                    currentButton = b;
                    currentRotationIndex = 0; //resetting the index, because new button was clicked
                    return;
                }
            }

        }
    }

    public void mouseMoved(int x, int y) {
        buttonMenu.setMouseOver(false);  //resetting all buttons
        buttonSave.setMouseOver(false);
        bWater.setMouseOver(false);
        bGrass.setMouseOver(false);
        bStart.setMouseOver(false);
        bFinish.setMouseOver(false);

        for(MyButton b : hashMap.keySet()) {
            b.setMouseOver(false);
        }

        if(buttonMenu.getBounds().contains(x, y)) {
            buttonMenu.setMouseOver(true);

        } else if(buttonSave.getBounds().contains(x, y)) {
            buttonSave.setMouseOver(true);
        } else if(bWater.getBounds().contains(x, y)) {
            bWater.setMouseOver(true);
        } else if(bGrass.getBounds().contains(x, y)) {
            bGrass.setMouseOver(true);
        } else if(bStart.getBounds().contains(x, y)) {
            bStart.setMouseOver(true);
        } else if(bFinish.getBounds().contains(x, y)) {
            bFinish.setMouseOver(true);
        }
        else {
            for(MyButton b : hashMap.keySet()) {
                if(b.getBounds().contains(x,y)) {
                    b.setMouseOver(true);
                    return;
                }
            }
        }
    }

    public void mousePressed(int x, int y) {
        if(buttonMenu.getBounds().contains(x, y)) {
            buttonMenu.setMousePressed(true);
        } else if(buttonSave.getBounds().contains(x, y)) {
            buttonSave.setMousePressed(true);
        } else if(bWater.getBounds().contains(x, y)) {
            bWater.setMousePressed(true);
        } else if(bGrass.getBounds().contains(x, y)) {
            bGrass.setMousePressed(true);
        } else if(bStart.getBounds().contains(x, y)) {
            bStart.setMousePressed(true);
        } else if(bFinish.getBounds().contains(x, y)) {
            bFinish.setMousePressed(true);
        }
        else {
            for(MyButton b : hashMap.keySet()) {
                if(b.getBounds().contains(x,y)) {
                    b.setMousePressed(true);
                    return;
                }
            }
        }
    }

    public void mouseReleased(int x, int y) {
        resetButtons();
    }

    private void resetButtons() {     ///add any new buttons
        buttonMenu.resetBooleans();
        buttonSave.resetBooleans();
        bGrass.resetBooleans();
        bWater.resetBooleans();
        bStart.resetBooleans();
        bFinish.resetBooleans();
        for(MyButton b : hashMap.keySet()) {
            b.resetBooleans();
        }
    }

    //to access the images from editing
    public BufferedImage getStart() {
        return start;
    }
    public BufferedImage getFinish() {
        return finish;
    }

}

