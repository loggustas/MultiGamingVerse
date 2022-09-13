package Enemies;

import managers.EnemyManager;

import static helperMethods.Constants.Enemies.GHOST;

public class Ghost extends Enemy{
    public Ghost(float x, float y, int id, EnemyManager enemyManager) {
        super(x, y, id, GHOST, enemyManager);
    }
}
