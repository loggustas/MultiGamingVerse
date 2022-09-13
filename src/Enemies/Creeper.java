package Enemies;

import managers.EnemyManager;

import static helperMethods.Constants.Enemies.CREEPER;

public class Creeper extends Enemy{
    public Creeper(float x, float y, int id, EnemyManager enemyManager) {
        super(x, y, id, CREEPER, enemyManager);
    }
}
