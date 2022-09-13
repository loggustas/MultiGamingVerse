package Enemies;

import managers.EnemyManager;

import static helperMethods.Constants.Enemies.GUARD;

public class Guard extends Enemy{

    public Guard(float x, float y, int id, EnemyManager enemyManager) {
        super(x, y, id, GUARD, enemyManager);
    }

}
