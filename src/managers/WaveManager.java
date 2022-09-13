package managers;

import Scenes.Playing;
import events.Wave;
import helperMethods.Constants;

import java.util.ArrayList;
import java.util.Arrays;

public class WaveManager {

    private Playing playing;
    private ArrayList<Wave> waves = new ArrayList<>();

    private int enemySpawnTickLimit; //1sec
    private int enemySpawnTick;
    private int waveTickLimit;
    private int waveTick;
    private int enemyIndex, waveIndex;


    private boolean waveStartTimer = false, waveTickTimerOver;


    public WaveManager(Playing playing) {
        this.playing = playing;
        enemySpawnTickLimit = (int)(playing.getGame().getUPS_SET() * 1);  //one sec
        enemySpawnTick = enemySpawnTickLimit; //spawn enemy right away
        waveTickLimit = (int)(playing.getGame().getUPS_SET() * 5);     //5sec
        waveTick = 0;
        createWaves();
    }

    public void update() {

        if(enemySpawnTick < enemySpawnTickLimit) {
            enemySpawnTick++;
        }
        if(waveStartTimer) {  //only if wave counter start variable is true
            waveTick++;
            if(waveTick >= waveTickLimit) {
                waveTickTimerOver = true;   //next wave timer has ended
            }
        }
    }

    public void increaseWaveIndex() {
        waveIndex++;
        waveTick = 0;
        waveTickTimerOver = false;  //restarting the wave timer
        waveStartTimer = false;
    }
    public void startWaveTimer() {  //this variable starts the wave timer count
        waveStartTimer = true;
    }
    public int getNextEnemy() {
        enemySpawnTick = 0; //resetting the enemySpawns tick
        return waves.get(waveIndex).getEnemyList().get(enemyIndex++);
    }
    public void loadWaves() {
        createWaves();
    }
    private void createWaves() {
        waves.clear();  //just to make sure, there are no wave left at any point, when creating waves
        if(Constants.Level.LEVEL == 1) {
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList())));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0)))); //20
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, 0, 0, 0)))); //20
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0))));  //15
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1)))); //20
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 1, 1, 1, 1, 0, 1, 1, 1, 1))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 1, -1, 1, 1, -1, 1, 1, -1, 1, 1, -1, 1, 1, -1, 1, 1, -1, 1, 1, -1, 1, 1, -1, 1, 1, -1, 1)))); //20
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 0, -1, 1, 0, -1, 1, 0, -1, 1, 0, -1, 1, 0, -1, 1, 0, -1, 1, 0, -1, 1, 0, -1, 1, 0, -1, 1, 0)))); //20
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 1, 1, 1)))); //20
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0)))); //20
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 0, 1, 1, 1, 2, 0, 1, 1, 1, 1, 1, 2, 1, 1))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 1, 1, 1, 2, 1, 1, 1, 1, 2, 2, 2, 1, 0))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 2, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 2))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 2, 3, 0, 0, 1, 1, 3, 0))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 2, 3, 3, 0, 1, 1, 3, 0, 3, 2, 1, 1, 2))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(3, 3, 3, 2, 2, 2, 3, 0, 0, 3, 1, 1, 1, 1, 3, 2, 3, 3, 2))));

        } else if(Constants.Level.LEVEL == 2) {
            System.out.println(2);
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList())));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0)))); //20
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, 0, 0, 0)))); //20
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0))));  //15
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1)))); //20
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 1, 1, 1, 1, 0, 1, 1, 1, 1))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 1, -1, 1, 1, -1, 1, 1, -1, 1, 1, -1, 1, 1, -1, 1, 1, -1, 1, 1, -1, 1, 1, -1, 1, 1, -1, 1)))); //20
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 0, -1, 1, 0, -1, 1, 0, -1, 1, 0, -1, 1, 0, -1, 1, 0, -1, 1, 0, -1, 1, 0, -1, 1, 0, -1, 1, 0)))); //20
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 1, 0, 1, 1, 0, 1, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 0)))); //20
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 2)))); //20
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 0, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 2, 1, 2))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 1, 1, 1, 2, 1, 2, 1, 2, 2, 2, 2, 1, 0))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 2, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 2))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 2, 3, 1, 1, 1, 1, 3, 0, 1, 1, 1, 1, 1, 3))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 2, 2, 3, 0, 1, 1, 1, 1, 3, 2, 3, 2, 1))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(3, 3, 3, 2, 2, 2, 3, 0, 0, 3, 1, 1, 1, 1, 3, 2, 3, 3, 2))));


        } else if(Constants.Level.LEVEL == 3){
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList())));

            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0)))); //20
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, 0, 0, 0)))); //20
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0))));  //15
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1)))); //20
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 1, 1, 1, 1, 0, 1, 1, 1, 1))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 1, -1, 1, 1, -1, 1, 1, -1, 1, 1, -1, 1, 1, -1, 1, 1, -1, 1, 1, -1, 1, 1, -1, 1, 1, -1, 1)))); //20
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 0, -1, 1, 0, -1, 1, 0, -1, 1, 0, -1, 1, 0, -1, 1, 0, -1, 1, 0, -1, 1, 0, -1, 1, 0, -1, 1, 0)))); //20
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 1, 0, 1, 1, 0, 1, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 0)))); //20
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 2)))); //20
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 0, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 2, 1, 2))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 1, 1, 1, 2, 1, 2, 1, 2, 2, 2, 2, 1, 0))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 2, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 2))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 2, 3, 0, 0, 1, 1, 3, 0, 1, 1, 1, 1, 1, 3))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 2, 0, 3, 0, 1, 1, 0, 0, 3, 2, 1, 1, 2))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(3, 3, 3, 2, 2, 2, 3, 0, 0, 3, 1, 1, 1, 1, 3, 2, 3, 3, 2))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(3, 3, 3, 2, 2, 2, 3, 0, 0, 3, 1, 1, 1, 1, 3, 2, 3, 3, 2, 3, 1, 1, 1, 1, 3, 2, 3, 3, 2))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 2, 3, 3, 3, 2, 2, 2, 2, 1))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 1, 1, 2, 1, 0, 4, 1, 1, 1, 4, 0, 4, 0, 0, 0, 0))));


        } else if(Constants.Level.LEVEL == 4) {
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList())));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0)))); //20
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, 0, 0, 0)))); //20
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0))));  //15
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1)))); //20
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 1, 1, 1, 1, 0, 1, 1, 1, 1))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 1, -1, 1, 1, -1, 1, 1, -1, 1, 1, -1, 1, 1, -1, 1, 1, -1, 1, 1, -1, 1, 1, -1, 1, 1, -1, 1)))); //20
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 0, -1, 1, 0, -1, 1, 0, -1, 1, 0, -1, 1, 0, -1, 1, 0, -1, 1, 0, -1, 1, 0, -1, 1, 0, -1, 1, 0)))); //20
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 1, 0, 1, 1, 0, 1, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 0)))); //20
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 2)))); //20
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 0, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 2, 1, 2))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 1, 1, 1, 2, 1, 2, 1, 2, 2, 2, 2, 1, 0))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 2, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 2))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 2, 3, 0, 0, 1, 1, 3, 0, 1, 1, 1, 1, 1, 3))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 2, 0, 3, 0, 1, 1, 0, 0, 3, 2, 1, 1, 2))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(3, 3, 3, 2, 2, 2, 3, 0, 0, 3, 1, 1, 1, 1, 3, 2, 3, 3, 2))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(3, 3, 3, 2, 2, 2, 3, 0, 0, 3, 1, 1, 1, 1, 3, 2, 3, 3, 2, 3, 1, 1, 1, 1, 3, 2, 3, 3, 2))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 2, 3, 3, 3, 2, 2, 2, 2, 1))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 4, 0, 4, 0, 0, 0, 0))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(4, 4, 1, 1, 1, 4, 0, 3, 1, 1, 3, 0, 4, 3, 3, 4, 0))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(4, 4, 3, 1, 1, 4, 3, 3, 1, 1, 3, 0, 4, 3, 3, 4, 2, 2, 2))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 4, 4, 3, 1, 1, 4, 3, 3, 1, 1, 3, 0, 4, 3, 3, 4, 2, 2, 2))));


        } else if(Constants.Level.LEVEL == 5) {
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList())));

            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList())));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0)))); //20
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, 0, 0, 0)))); //20
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0))));  //15
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1)))); //20
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 1, 1, 1, 1, 0, 1, 1, 1, 1))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 1, -1, 1, 1, -1, 1, 1, -1, 1, 1, -1, 1, 1, -1, 1, 1, -1, 1, 1, -1, 1, 1, -1, 1, 1, -1, 1)))); //20
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 0, -1, 1, 0, -1, 1, 0, -1, 1, 0, -1, 1, 0, -1, 1, 0, -1, 1, 0, -1, 1, 0, -1, 1, 0, -1, 1, 0)))); //20
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 1, 0, 1, 1, 0, 1, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 0)))); //20
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 2)))); //20
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 0, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 2, 1, 2))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 1, 1, 1, 2, 1, 2, 1, 2, 2, 2, 2, 1, 0))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 2, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 2))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 2, 3, 0, 0, 1, 1, 3, 0, 1, 1, 1, 1, 1, 3))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 2, 0, 3, 0, 1, 1, 0, 0, 3, 2, 1, 1, 2))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(3, 3, 3, 2, 2, 2, 3, 0, 0, 3, 1, 1, 1, 1, 3, 2, 3, 3, 2))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(3, 3, 3, 2, 2, 2, 3, 0, 0, 3, 1, 1, 1, 1, 3, 2, 3, 3, 2, 3, 1, 1, 1, 1, 3, 2, 3, 3, 2))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 2, 3, 3, 3, 2, 2, 2, 2, 1))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 4, 0, 4, 0, 0, 0, 0))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(4, 4, 3, 1, 1, 4, 3, 3, 1, 1, 3, 0, 4, 3, 3, 4, 2, 2, 2))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 4, 4, 3, 1, 1, 4, 3, 3, 1, 1, 3, 0, 4, 3, 3, 4, 2, 2, 2))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 4, 4, 3, 1, 1, 4, 3, 3, 1, 1, 3, 0, 4, 3, 3, 4, 2, 2, 2, 1, 0, 1, 1, 1, 1, 4, 4, 3, 1, 1, 4, 3, 3, 1, 1, 3, 0, 4, 3))));


        } else if(Constants.Level.LEVEL == 6) {
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList())));

            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList())));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0)))); //20
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, 0, 0, 0)))); //20
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0))));  //15
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1)))); //20
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 1, 1, 1, 1, 0, 1, 1, 1, 1))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 1, -1, 1, 1, -1, 1, 1, -1, 1, 1, -1, 1, 1, -1, 1, 1, -1, 1, 1, -1, 1, 1, -1, 1, 1, -1, 1)))); //20
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 0, -1, 1, 0, -1, 1, 0, -1, 1, 0, -1, 1, 0, -1, 1, 0, -1, 1, 0, -1, 1, 0, -1, 1, 0, -1, 1, 0)))); //20
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 0)))); //20
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 2)))); //20
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 0, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 2, 1, 2))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 1, 1, 1, 2, 1, 2, 1, 2, 2, 2, 2, 1, 1))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 2, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 2))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 2, 3, 0, 0, 1, 1, 3, 0, 1, 1, 1, 1, 1, 3))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 2, 0, 3, 0, 1, 1, 0, 0, 3, 2, 1, 1, 2))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(3, 3, 3, 2, 2, 2, 3, 0, 0, 3, 1, 1, 1, 1, 3, 2, 3, 3, 2))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(3, 3, 3, 2, 2, 2, 3, 0, 0, 3, 1, 1, 1, 1, 3, 2, 3, 3, 2, 3, 1, 1, 1, 1, 3, 2, 3, 3, 2))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 2, 3, 3, 3, 2, 2, 2, 2, 1))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 4, 0, 4, 0, 0, 0, 0))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(4, 4, 3, 1, 1, 4, 3, 3, 1, 1, 3, 0, 4, 3, 3, 4, 2, 2, 2))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 4, 4, 3, 1, 1, 4, 3, 3, 1, 1, 3, 0, 4, 3, 3, 4, 2, 2, 2))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 4, 4, 3, 1, 1, 4, 3, 3, 1, 1, 3, 0, 4, 3, 3, 4, 2, 2, 2, 1, 0, 1, 1, 1, 1, 4, 4, 3, 1, 1, 4, 3, 3, 1, 1, 3, 0, 4, 3))));



        } else if(Constants.Level.LEVEL == 7) {
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList())));

            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0)))); //20
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, 0, 0, 0)))); //20
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0))));  //15
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1)))); //20
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 1, 1, 1, 1, 0, 1, 1, 1, 1))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 1, -1, 1, 1, -1, 1, 1, -1, 1, 1, -1, 1, 1, -1, 1, 1, -1, 1, 1, -1, 1, 1, -1, 1, 1, -1, 1)))); //20
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 0, -1, 1, 0, -1, 1, 0, -1, 1, 0, -1, 1, 0, -1, 1, 0, -1, 1, 0, -1, 1, 0, -1, 1, 0, -1, 1, 0)))); //20
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 0)))); //20
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 2)))); //20
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 0, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 2, 1, 2))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 1, 1, 1, 2, 1, 2, 1, 2, 2, 2, 2, 1, 1))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 2, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 2))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 2, 3, 0, 0, 1, 1, 3, 0, 1, 1, 1, 1, 1, 3))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 2, 0, 3, 0, 1, 1, 0, 0, 3, 2, 1, 1, 2))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(3, 3, 3, 2, 2, 2, 3, 0, 0, 3, 1, 1, 1, 1, 3, 2, 3, 3, 2))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(3, 3, 3, 2, 2, 2, 3, 0, 0, 3, 1, 1, 1, 1, 3, 2, 3, 3, 2, 3, 1, 1, 1, 1, 3, 2, 3, 3, 2))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 2, 3, 3, 3, 2, 2, 2, 2, 1))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 4, 0, 4, 0, 0, 0, 0))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(4, 4, 3, 1, 1, 4, 3, 3, 1, 1, 3, 0, 4, 3, 3, 4, 2, 2, 2))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(4, 4, 3, 1, 1, 4, 1, 1, 1, 1, 1, 4, 4, 3, 1, 1, 4, 3, 3, 1, 1, 3, 0, 4, 3, 3, 4, 2, 2, 2))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(4, 4, 3, 1, 1, 4, 2, 4, 4, 1, 1, 4, 4, 3, 1, 1, 4, 3, 3, 1, 1, 3, 1, 4, 3, 3, 4, 1, 1, 1, 1, 1, 1, 1))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(4, 4, 3, 1, 1, 4, 1, 1, 1, 1, 4, 4, 3, 1, 1, 4, 3, 3, 1, 1, 3, 0, 4, 3, 3, 4, 2, 2, 2, 1, 0, 1, 1, 1, 1, 4, 4, 3, 1, 1, 4, 3, 3, 1, 1, 3, 0, 4, 3))));


        } else if(Constants.Level.LEVEL == 8) {
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList())));

            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0)))); //20
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, 0, 0, 0)))); //20
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0))));  //15
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1)))); //20
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 1, 1, 1, 1, 0, 1, 1, 1, 1))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 1, -1, 1, 1, -1, 1, 1, -1, 1, 1, -1, 1, 1, -1, 1, 1, -1, 1, 1, -1, 1, 1, -1, 1, 1, -1, 1)))); //20
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 0, -1, 1, 0, -1, 1, 0, -1, 1, 0, -1, 1, 0, -1, 1, 0, -1, 1, 0, -1, 1, 0, -1, 1, 0, -1, 1, 0)))); //20
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 0)))); //20
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 2)))); //20
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 1, 1, 1, 2, 1, 2, 1, 2, 2, 2, 2, 1, 1))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 2, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 2))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 2, 3, 0, 0, 1, 1, 3, 0, 1, 1, 1, 1, 1, 3))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(3, 3, 3, 2, 2, 2, 3, 0, 0, 3, 1, 1, 1, 1, 3, 2, 3, 3, 2))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(3, 3, 3, 2, 2, 2, 3, 0, 0, 3, 1, 1, 1, 1, 3, 2, 3, 3, 2, 3, 1, 1, 1, 1, 3, 2, 3, 3, 2))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 2, 3, 3, 3, 2, 2, 2, 2, 1))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 4, 1, 4, 1, 1, 1, 1))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(4, 4, 3, 1, 1, 4, 3, 3, 1, 1, 3, 0, 4, 3, 3, 4, 2, 2, 2))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(4, 4, 3, 1, 1, 4, 1, 1, 1, 1, 1, 4, 4, 3, 1, 1, 4, 3, 3, 1, 1, 3, 0, 4, 3, 3, 4, 2, 2, 2))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(4, 4, 3, 1, 1, 4, 2, 4, 4, 1, 1, 4, 4, 3, 1, 1, 4, 3, 3, 1, 1, 3, 1, 4, 3, 3, 4, 1, 1, 1, 1, 1, 1, 1))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(4, 4, 3, 1, 1, 4, 1, 1, 1, 1, 4, 4, 3, 1, 1, 4, 3, 3, 1, 1, 3, 0, 4, 3, 3, 4, 2, 2, 2, 1, 0, 1, 1, 1, 1, 4, 4, 3, 1, 1, 4, 3, 3, 1, 1, 3, 0, 4, 3))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(4, 4, 3, 1, 1, 4, 1, 4, 4, 4, 4, 4, 3, 1, 1, 4, 3, 3, 1, 1, 3, 4, 4, 3, 3, 4, 2, 2, 2, 4, 4, 1, 1, 1, 1, 4, 4, 4, 1, 1, 4, 3, 3, 1, 1, 3, 4, 4, 4, 4))));



        } else if(Constants.Level.LEVEL == 9) {
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList())));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0)))); //20
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, 0, 0, 0)))); //20
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0))));  //15
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1)))); //20
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 1, 1, 1, 1, 0, 1, 1, 1, 1))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 1, -1, 1, 1, -1, 1, 1, -1, 1, 1, -1, 1, 1, -1, 1, 1, -1, 1, 1, -1, 1, 1, -1, 1, 1, -1, 1)))); //20
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 0, -1, 1, 0, -1, 1, 0, -1, 1, 0, -1, 1, 0, -1, 1, 0, -1, 1, 0, -1, 1, 0, -1, 1, 0, -1, 1, 0)))); //20
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 0)))); //20
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 2)))); //20
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 1, 1, 1, 2, 1, 2, 1, 2, 2, 2, 2, 1, 1))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 2, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 2))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 2, 3, 0, 0, 1, 1, 3, 0, 1, 1, 1, 1, 1, 3))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(3, 3, 3, 4, 2, 4, 4, 0, 0, 3, 1, 1, 1, 1, 4, 2, 3, 3, 2))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(3, 3, 3, 2, 2, 2, 4, 0, 0, 3, 1, 1, 1, 1, 3, 2, 4, 4, 2, 3, 1, 1, 1, 1, 3, 2, 3, 3, 2))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 2, 3, 3, 3, 2, 2, 2, 2, 1))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 4, 1, 4, 1, 1, 1, 1))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(4, 4, 3, 1, 1, 4, 3, 3, 1, 1, 3, 0, 4, 3, 3, 4, 2, 2, 2))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(4, 4, 3, 1, 1, 4, 1, 1, 1, 1, 1, 4, 4, 3, 1, 1, 4, 3, 3, 1, 1, 3, 0, 4, 3, 3, 4, 2, 2, 2))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(4, 5, 3, 1, 1, 5, 2, 4, 4, 1, 1, 5, 4, 5, 5, 1, 5, 5, 3, 1, 1, 5, 1, 5, 5, 3, 5, 4, 4, 1, 1, 1, 1, 1))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(5, 5, 3, 1, 1, 0, 5, 1, 1, 1, 5, 4, 3, 1, 1, 0, 3, 3, 1, 1, 3, 0, 4, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 4, 4, 3, 1, 1, 4, 3, 3, 1, 1, 3, 0, 4, 3))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(5, 5, 3, 1, 1, 4, 1, 4, 4, 4, 4, 4, 5, 1, 1, 4, 3, 3, 1, 1, 3, 4, 4, 3, 3, 4, 4, 2, 2, 4, 4, 1, 1, 1, 1, 4, 4, 4, 1, 1, 4, 3, 3, 1, 1, 3, 4, 4, 4, 4))));



        } else {
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList())));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList())));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0)))); //20
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, -1, -1, 0, 0, 0, 0)))); //20
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0))));  //15
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1)))); //20
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 1, 1, 1, 1, 0, 1, 1, 1, 1))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 1, -1, 1, 1, -1, 1, 1, -1, 1, 1, -1, 1, 1, -1, 1, 1, -1, 1, 1, -1, 1, 1, -1, 1, 1, -1, 1)))); //20
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 0, -1, 1, 0, -1, 1, 0, -1, 1, 0, -1, 1, 0, -1, 1, 0, -1, 1, 0, -1, 1, 0, -1, 1, 0, -1, 1, 0)))); //20
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 0)))); //20
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 2)))); //20
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 1, 1, 1, 2, 1, 2, 1, 2, 2, 2, 2, 1, 1))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 2, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 2))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 2, 3, 0, 0, 1, 1, 3, 0, 1, 1, 1, 1, 1, 3))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(3, 3, 3, 4, 2, 4, 4, 0, 0, 3, 1, 1, 1, 1, 4, 2, 3, 3, 2))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(3, 3, 3, 2, 2, 2, 4, 0, 0, 3, 1, 1, 1, 1, 3, 2, 4, 4, 2, 3, 1, 1, 1, 1, 3, 2, 3, 3, 2))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 2, 3, 3, 3, 2, 2, 2, 2, 1))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 4, 1, 4, 1, 1, 1, 1))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(4, 4, 3, 1, 1, 4, 3, 3, 1, 1, 3, 0, 4, 3, 3, 4, 2, 2, 2))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(4, 4, 3, 1, 1, 4, 1, 1, 1, 1, 1, 4, 4, 3, 1, 1, 4, 3, 3, 1, 1, 3, 0, 4, 3, 3, 4, 2, 2, 2))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(4, 5, 3, 1, 1, 5, 2, 4, 4, 1, 1, 5, 4, 5, 5, 1, 5, 5, 3, 1, 1, 5, 1, 5, 5, 3, 5, 4, 4, 1, 1, 1, 1, 1))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(5, 5, 3, 1, 1, 4, 5, 1, 1, 1, 5, 4, 3, 1, 1, 5, 3, 3, 1, 1, 3, 0, 4, 5, 5, 5, 2, 2, 2, 1, 1, 1, 1, 1, 1, 4, 4, 3, 1, 1, 4, 3, 3, 1, 1, 3, 0, 4, 3))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(5, 5, 3, 1, 1, 4, 1, 4, 4, 4, 4, 4, 5, 1, 1, 4, 3, 3, 1, 1, 3, 4, 4, 3, 3, 4, 5, 5, 2, 4, 4, 1, 1, 1, 1, 4, 4, 4, 1, 1, 4, 3, 3, 1, 1, 3, 4, 4, 4, 4))));
            waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(5, 5, 3, 5, 5, 4, 1, 5, 5, 5, 4, 4, 5, 1, 1, 5, 5, 5, 1, 1, 5, 5, 5, 5, 5, 5, 5, 5, 2, 4, 4, 1, 1, 1, 1, 4, 4, 4, 1, 1, 4, 3, 3, 1, 1, 3, 4, 4, 4, 4, 5, 5, 3, 5, 5, 4, 1, 4, 4, 4, 4, 4, 5, 1, 1, 1, 1, 1, 1, 1, 5, 4, 4, 5, 5, 5, 5, 5, 2, 4, 4, 1))));

        }
    }

    public ArrayList<Wave> getWaves() {
        return waves;
    }

    public boolean isTimeForNewEnemy() {
        if(enemySpawnTick >= enemySpawnTickLimit) {
            return true;
        }
        return false;
    }
    public boolean isWaveTimeOver() {
        return waveTickTimerOver;
    }
    public boolean isThereEnemiesLeftInWave() {
        return enemyIndex < waves.get(waveIndex).getEnemyList().size();
    }

    public boolean isThereMoreWaves() {
        return waveIndex + 1 < waves.size();    //plus 1, because index and size not equal
    }

    public void resetEnemyIndex() {
        enemyIndex = 0;
    }
    public int getWaveIndex() {
        return waveIndex;
    }
    public float getTimeLeft() {
        float ticksLeft = waveTickLimit - waveTick;
        return ticksLeft / (float)playing.getGame().getUPS_SET();  //return time left in seconds
    }

    public boolean isWaveTimerStarted() {
        return waveStartTimer;
    }

    public void reset() {
        waves.clear();
        createWaves();

        enemyIndex = 0;
        waveIndex = 0;
        waveStartTimer = false;
        waveTickTimerOver = false;
        waveTick = 0;
        enemySpawnTick = enemySpawnTickLimit;
    }
}
