package helperMethods;

import objects.PathPoint;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class LoadSave {

    public static BufferedImage getSpriteAtlas() {
        BufferedImage img = null;
        InputStream is = LoadSave.class.getClassLoader().getResourceAsStream("spriteatlas.png"); //sito nelabai suprantu, bet del to kad statinis methodas reikia pradzia pakei

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    public static BufferedImage getPauseScreen() {
        BufferedImage img = null;
        InputStream is = LoadSave.class.getClassLoader().getResourceAsStream("PauseScreen.png");
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;

    }

    public static BufferedImage getMenuScreen() {
        BufferedImage img = null;
        InputStream is = LoadSave.class.getClassLoader().getResourceAsStream("menu.png");
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    public static BufferedImage getYouWonScreen() {
        BufferedImage img = null;
        InputStream is = LoadSave.class.getClassLoader().getResourceAsStream("youWon.png");
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    public static BufferedImage getGameOverScreen() {
        BufferedImage img = null;
        InputStream is = LoadSave.class.getClassLoader().getResourceAsStream("GameOver.png");
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    public static BufferedImage getActionBarImage() {
        BufferedImage img = null;
        InputStream is = LoadSave.class.getClassLoader().getResourceAsStream("actionbar2.png");

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    public static void CreateLevel(String name, int[] idArr) {
        File newLevel = new File("res/levels/" + name + ".txt");

        if(newLevel.exists()) {

        } else {
            try {
                newLevel.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            WriteToFile(newLevel, idArr, new PathPoint(0,0), new PathPoint(0, 0));
        }
    }

    private static void WriteToFile(File f, int[] idArr, PathPoint start, PathPoint finish) {

        try {
            PrintWriter printWriter = new PrintWriter(f);

            for (Integer i : idArr){
                printWriter.println(i);
            }
            printWriter.println(start.getxCord());
            printWriter.println(start.getyCord());
            printWriter.println(finish.getxCord());
            printWriter.println(finish.getyCord());

            printWriter.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void NewGame() {
        try {
            File file = new File("res/levels/progress.txt");
            PrintWriter printWriter = new PrintWriter(file);
            printWriter.println(1); // start from 1 lvl
            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void SaveGame(int level) {

        try {
            File file = new File("res/levels/progress.txt");
            PrintWriter printWriter = new PrintWriter(file);
            printWriter.println(level); // start from 1 lvl
            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static int GetProgress() {
        int level = 1;
        try {
            File file = new File("res/levels/progress.txt");
            Scanner scanner = new Scanner(file);

            level = Integer.valueOf(scanner.nextLine());

            scanner.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return level;
    }

    public static void SaveLevel(String name, int[][] idArr, PathPoint start, PathPoint finish) {
        File levelFile = new File("res/levels/" + name + ".txt");

        if(levelFile.exists()) {
            WriteToFile(levelFile, Utilz.TwoDto1DintArr(idArr), start, finish);
        } else {
            return;
        }

    }

    private static ArrayList<Integer> ReadFromFile(File file) {
        ArrayList<Integer> list = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                list.add(Integer.valueOf(scanner.nextLine()));
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static ArrayList<PathPoint> GetLevelPathPoints(String name) {
        File lvlFile = new File("res/levels/" + name + ".txt");

        if(lvlFile.exists()) {
            ArrayList<Integer> list = ReadFromFile(lvlFile);
            ArrayList<PathPoint> points = new ArrayList<>();
            points.add(new PathPoint(list.get(400),list.get(401)));
            points.add(new PathPoint(list.get(402), list.get(403)));

            return points;

        } else {
            return null;
        }
    }

    //save 2d int array into file
    public static int[][] GetLevelData(String name) {
        File lvlFile = new File("res/levels/" + name + ".txt");

        if(lvlFile.exists()) {
            ArrayList<Integer> list = ReadFromFile(lvlFile);
            return Utilz.ArrayListTo2Dint(list, 20, 20);

        } else {

            return null;
        }
    }
}
