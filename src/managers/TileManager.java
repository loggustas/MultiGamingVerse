package managers;

import helperMethods.Constants;
import helperMethods.ImageFix;
import helperMethods.LoadSave;
import objects.Tile;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class TileManager {
    public Tile GRASS,WATER,V_ROAD,H_ROAD, BL_WATER_CORNER, TL_WATER_CORNER, TR_WATER_CORNER, BR_WATER_CORNER,
                T_WATER_SIDE, L_WATER_SIDE, B_WATER_SIDE, R_WATER_SIDE, TR_WATER_SCORNER, TL_WATER_SCORNER, BR_WATER_SCORNER,BL_WATER_SCORNER,
                TL_ROAD_CORNER, TR_ROAD_CORNER, BR_ROAD_CORNER, BL_ROAD_CORNER ;       //all possible tiles, will store them in an arrayList
    public BufferedImage atlas;
    public ArrayList<Tile> tiles = new ArrayList<>();

    public ArrayList<Tile> roads = new ArrayList<>();
    public ArrayList<Tile> roadCorners = new ArrayList<>();
    public ArrayList<Tile> waterCorners = new ArrayList<>();
    public ArrayList<Tile> waterSides = new ArrayList<>();
    public ArrayList<Tile> waterIslands = new ArrayList<>();


    public TileManager() {
        loadAtlas();
        createTiles();
    }

    private void createTiles() {
        int id = 0;
        tiles.add(GRASS = new Tile(getSprite(9, 0), id++, Constants.Tiles.GRASS));
        tiles.add(WATER = new Tile(getAnimationSprites(0, 0), id++, Constants.Tiles.WATER));

        roads.add(H_ROAD = new Tile(getSprite(8, 0), id++, Constants.Tiles.ROAD));
        roads.add(V_ROAD = new Tile(ImageFix.getRotImg(getSprite(8, 0), 90), id++, Constants.Tiles.ROAD));

        roadCorners.add(TL_ROAD_CORNER = new Tile(getSprite(7, 0),id++, Constants.Tiles.ROAD));
        roadCorners.add(TR_ROAD_CORNER = new Tile(ImageFix.getRotImg(getSprite(7, 0), 90), id++,Constants.Tiles.ROAD));
        roadCorners.add(BR_ROAD_CORNER = new Tile(ImageFix.getRotImg(getSprite(7, 0), 180), id++, Constants.Tiles.ROAD));
        roadCorners.add(BL_ROAD_CORNER = new Tile(ImageFix.getRotImg(getSprite(7, 0), 270), id++, Constants.Tiles.ROAD));

        waterCorners.add(BL_WATER_CORNER = new Tile(ImageFix.getBuildRotImg( getAnimationSprites(0, 0), getSprite(5, 0), 0), id++, Constants.Tiles.WATER));
        waterCorners.add(BR_WATER_CORNER = new Tile(ImageFix.getBuildRotImg( getAnimationSprites(0, 0), getSprite(5, 0), 90), id++, Constants.Tiles.WATER));
        waterCorners.add(TR_WATER_CORNER = new Tile(ImageFix.getBuildRotImg( getAnimationSprites(0, 0), getSprite(5, 0), 180), id++, Constants.Tiles.WATER));
        waterCorners.add(TL_WATER_CORNER = new Tile(ImageFix.getBuildRotImg( getAnimationSprites(0, 0), getSprite(5, 0),270), id++, Constants.Tiles.WATER));

        waterSides.add(T_WATER_SIDE = new Tile(ImageFix.getBuildRotImg( getAnimationSprites(0, 0), getSprite(6, 0), 0), id++, Constants.Tiles.WATER));
        waterSides.add(R_WATER_SIDE = new Tile(ImageFix.getBuildRotImg( getAnimationSprites(0, 0), getSprite(6, 0), 90), id++, Constants.Tiles.WATER));
        waterSides.add(B_WATER_SIDE = new Tile(ImageFix.getBuildRotImg( getAnimationSprites(0, 0), getSprite(6, 0), 180), id++, Constants.Tiles.WATER));
        waterSides.add(L_WATER_SIDE = new Tile(ImageFix.getBuildRotImg( getAnimationSprites(0, 0), getSprite(6, 0), 270), id++, Constants.Tiles.WATER));

        waterIslands.add(TR_WATER_SCORNER = new Tile(ImageFix.getBuildRotImg( getAnimationSprites(0, 0), getSprite(4, 0), 0), id++, Constants.Tiles.WATER));
        waterIslands.add(TL_WATER_SCORNER = new Tile(ImageFix.getBuildRotImg( getAnimationSprites(0, 0), getSprite(4, 0), 90), id++, Constants.Tiles.WATER));
        waterIslands.add(BR_WATER_CORNER = new Tile(ImageFix.getBuildRotImg( getAnimationSprites(0, 0), getSprite(4, 0), 180), id++, Constants.Tiles.WATER));
        waterIslands.add(BL_WATER_CORNER = new Tile(ImageFix.getBuildRotImg( getAnimationSprites(0, 0), getSprite(4, 0), 270), id++, Constants.Tiles.WATER));

        tiles.addAll(roads);
        tiles.addAll(roadCorners);
        tiles.addAll(waterCorners);
        tiles.addAll(waterSides);
        tiles.addAll(waterIslands);
    }

    private BufferedImage[] getImgs(int firstX, int firstY, int secondX, int secondY) {
        return new BufferedImage[]{getSprite(firstX, firstY), getSprite(secondX, secondY)};
    }

    private void loadAtlas() {   //uzkraunu atlasa is LoadSave klases
        this.atlas = LoadSave.getSpriteAtlas();
    }

    public BufferedImage getSprite(int id){

        return tiles.get(id).getSprite();
    }
    public BufferedImage getAniSprite(int id, int animationIndex){

        return tiles.get(id).getSprite(animationIndex);
    }

    private BufferedImage[] getAnimationSprites(int xCord, int yCord) {
        BufferedImage[] arr = new BufferedImage[4];
        for(int i = 0 ; i < 4 ; i++) {
            arr[i] = getSprite(xCord + i, yCord);
        }

        return arr;
    }

    private BufferedImage getSprite(int xCord, int yCord) {

        return this.atlas.getSubimage(xCord * 32, yCord * 32, 32, 32);
    }
    public Tile getTile(int id){
        return tiles.get(id);
    }

    public boolean isSpriteAnimation(int spriteID) {
        return tiles.get(spriteID).isAnimation();
    }

    public int[][] getTypeArr() {
        int[][] idArr;
        if(Constants.Level.LEVEL == 1) {
            idArr = LoadSave.GetLevelData("first");
        } else if(Constants.Level.LEVEL == 2) {
            idArr = LoadSave.GetLevelData("second");
        } else if(Constants.Level.LEVEL == 3){
            idArr = LoadSave.GetLevelData("third");
        } else if(Constants.Level.LEVEL == 4){
            idArr = LoadSave.GetLevelData("fourth");
        } else if(Constants.Level.LEVEL == 5){
            idArr = LoadSave.GetLevelData("fifth");
        } else if(Constants.Level.LEVEL == 6){
            idArr = LoadSave.GetLevelData("sixth");
        } else if(Constants.Level.LEVEL == 7){
            idArr = LoadSave.GetLevelData("seventh");
        } else if(Constants.Level.LEVEL == 8){
            idArr = LoadSave.GetLevelData("eight");
        } else if(Constants.Level.LEVEL == 9){
            idArr = LoadSave.GetLevelData("ninth");
        } else {
            idArr = LoadSave.GetLevelData("tenth");
        }
        int[][] typeArr = new int[idArr.length][idArr[0].length];

        for(int j = 0 ; j < idArr.length ; j++) {
            for(int i = 0 ; i < idArr[j].length; i++) {
                int id = idArr[j][i];
                typeArr[j][i] = tiles.get(id).getTileType();
            }
        }
        return typeArr;
    }

    public ArrayList<Tile> getRoads() {
        return roads;
    }

    public ArrayList<Tile> getRoadCorners() {
        return roadCorners;
    }

    public ArrayList<Tile> getWaterCorners() {
        return waterCorners;
    }

    public ArrayList<Tile> getWaterSides() {
        return waterSides;
    }

    public ArrayList<Tile> getWaterIslands() {
        return waterIslands;
    }
}



